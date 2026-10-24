"""Read-only checks for the repository's Android/plugin engineering contract."""
from pathlib import Path
import argparse
import json
import re
import subprocess
import sys
import xml.etree.ElementTree as ET
import unicodedata

def non_ascii_punctuation(text):
    """Non-ASCII punctuation, arrows, the ideographic space and full-width forms (COMMON_AGENTS.md);
    U+00B7 stays allowed as a numeric separator. Covers every language, not only CJK marks."""
    return [ch for ch in text if ord(ch) > 0x7F and ch != '\u00b7' and (
        unicodedata.category(ch).startswith('P') or 0x2190 <= ord(ch) <= 0x21FF
        or ord(ch) == 0x3000 or 0xFF01 <= ord(ch) <= 0xFF65)]

ROOT = Path(__file__).resolve().parents[1]
ANDROID = '{http://schemas.android.com/apk/res/android}'
BUILD_TOLERANCE = 10
LOCALES = ['values', 'values-en', 'values-ar', 'values-es', 'values-fr', 'values-ja',
           'values-ko', 'values-ru', 'values-zh', 'values-zh-rHK', 'values-zh-rTW']

def check(root, pending_commit=False):
    errors = []
    def require(condition, message):
        if not condition: errors.append(message)
    props = dict(re.findall(r'^([A-Z_]+)=(.*)$', (root/'version.properties').read_text(encoding='utf-8-sig'), re.M))
    count = int(subprocess.check_output(['git','-C',str(root),'rev-list','--count','HEAD']))
    # VERSION_BUILD follows the commit count within a shared tolerance (D:/idea-projects/COMMON_AGENTS.md).
    drift = int(props['VERSION_BUILD']) - (count + int(pending_commit))
    require(abs(drift) <= BUILD_TOLERANCE, f'VERSION_BUILD must stay within {BUILD_TOLERANCE} of the commit count (off by {drift})')
    settings = (root/'settings.gradle.kts').read_text(encoding='utf-8-sig')
    # Any published release is accepted; shared plugins declared here must use one version.
    versions = re.findall(r'autojs6-(platform-versions|native-alignment)"\) version "(\d+\.\d+\.\d+)"', settings)
    require('platform-versions' in [plugin for plugin, _ in versions], 'platform-versions must come from a published release')
    require(len({version for _, version in versions}) == 1, 'Shared plugin versions must match')
    require('mavenLocal(' not in settings and 'autojs.buildPlugins.includeBuild' not in settings, 'Local platform substitution is forbidden')
    root_name = re.search(r'rootProject.name\s*=\s*"([^"]+)"', settings)
    require(root_name and re.fullmatch('[a-z0-9-]+', root_name[1]), 'rootProject.name must be lowercase kebab-case')
    require(not (root/'gradle/data').exists(), 'Consumer compatibility overrides must be removed')
    for relative in ['LICENSE','app/src/main/res/mipmap/ic_launcher.png', '.python/check_markdown.bat']:
        require((root/relative).is_file(), 'Missing ' + relative)
    for relative in ['AGENTS.md','CLAUDE.md','sign.properties','app/sm003.jks','local.properties','migration.pre-platform-versions.bak']:
        require(subprocess.run(['git','-C',str(root),'check-ignore','-q',relative]).returncode == 0, 'Not ignored: ' + relative)
    manifest = ET.parse(root/'app/src/main/AndroidManifest.xml').getroot()
    app = manifest.find('application')
    require(app.get(ANDROID+'allowBackup') == 'false', 'Backup must be disabled')
    require(app.get(ANDROID+'label') == '@string/app_name', 'Application label must use app_name')
    metadata = {e.get(ANDROID+'name'): e.get(ANDROID+'value') for e in app.findall('meta-data')}
    wake = metadata.get('org.autojs.plugin.WAKE_ACTIVITY')
    activities = [e for e in app.findall('activity') if e.get(ANDROID+'name') == wake]
    require(len(activities) == 1, 'Wake metadata must identify one activity')
    if activities:
        activity = activities[0]
        require(activity.get(ANDROID+'exported') == 'true', 'Wake must be exported')
        require(activity.get(ANDROID+'permission') == 'org.autojs.permission.PLUGIN', 'Wake must require plugin permission')
        require(activity.get(ANDROID+'theme') == '@android:style/Theme.NoDisplay', 'Wake must use NoDisplay')
        filters = activity.findall('intent-filter')
        require(any(any(a.get(ANDROID+'name') == 'org.autojs.plugin.action.WAKE' for a in f.findall('action'))
                    and any(c.get(ANDROID+'name') == 'android.intent.category.DEFAULT' for c in f.findall('category')) for f in filters), 'Wake action/category missing')
    english = None
    for locale in LOCALES:
        path = root/'app/src/main/res'/locale/'strings.xml'
        require(path.is_file(), 'Missing language ' + locale)
        if not path.exists(): continue
        resource = ET.parse(path).getroot()
        strings = resource.findall('string')
        names = [e.get('name') for e in strings]
        require(names == sorted(names), 'Unsorted strings: ' + locale)
        values = {e.get('name'): ''.join(e.itertext()) for file in path.parent.glob('*.xml')
                  for e in ET.parse(file).getroot().findall('string') if e.get('translatable') != 'false'}
        require('plugin_description' in values, 'Missing description: ' + locale)
        require(not any(e.get('translatable') == 'false' or e.get('name') == 'app_name' for e in strings), 'Nontranslatable identity in strings.xml: ' + locale)
        require(not resource.findall('plurals') and not resource.findall('string-array'), 'Mixed resource types: ' + locale)
        require(not any(non_ascii_punctuation(v) for v in values.values()), 'Non-ASCII punctuation: ' + locale)
        require(not re.search(r'[.!?;,:]$', values.get('plugin_description','')), 'Description punctuation: ' + locale)
        if locale == 'values': english = values
        elif locale == 'values-en': require(values == english, 'Default and explicit English differ')
    require('简体中文' in (root/'README.md').read_text(encoding='utf-8-sig'), 'Root README must identify Simplified Chinese')
    require(not list((root/'.changelog').glob('*.md')) or all(p.name.startswith('template') for p in (root/'.changelog').glob('*.md')), 'Generated changelogs must not live in .changelog')
    errors.extend(check_script_modules(root, app, metadata))
    errors.extend(check_punctuation(root))
    return errors

PLUGIN_PERMISSION = 'org.autojs.permission.PLUGIN'
LIBRARY_ACTION = 'org.autojs.plugin.SCRIPT_MODULE_LIBRARY'
LIBRARY_META_DATA = 'org.autojs.plugin.scriptModules.LIBRARY'
CATALOG_META_DATA = 'org.autojs.plugin.scriptModules.CATALOG'
# Text that is not ours to rewrite: the module files of AutoJs6 (byte for byte), the Icon Studio runtime shared by every
# plugin, Gradle's own scripts and the license text (COMMON_AGENTS.md, exceptions of the ASCII punctuation rule).
PUNCTUATION_EXEMPT = ('app/src/main/assets/modules/', '.icons/assets/', '.python/icon_studio_runtime.py', 'gradlew', 'gradlew.bat', 'LICENSE')
TEXT_SUFFIXES = {'.md', '.json', '.kt', '.kts', '.xml', '.py', '.yml', '.toml', '.properties', '.pro', '.svg', '.bat', ''}

def check_script_modules(root, app, metadata):
    """The contract with AutoJs6 (host roadmap T11 / P4.3): one enabled, protected activity-alias per catalog library,
    the catalog meta-data, the info service, and one minimum host build in the build script and the documents."""
    errors = []
    def require(condition, message):
        if not condition: errors.append(message)
    catalog = json.loads((root/'app/src/main/assets/script-modules.json').read_text(encoding='utf-8'))
    ids = [library['id'] for library in catalog['libraries']]
    require(metadata.get(CATALOG_META_DATA) == 'script-modules.json', 'Catalog meta-data must name script-modules.json')
    require(metadata.get('requiresHostVersion') == '${requiredHostVersion}', 'Application requiresHostVersion must use the build placeholder')
    aliases = {}
    for alias in app.findall('activity-alias'):
        name = alias.get(ANDROID+'name', '')
        library = {e.get(ANDROID+'name'): e.get(ANDROID+'value') for e in alias.findall('meta-data')}.get(LIBRARY_META_DATA)
        require(name == '.library.' + str(library), f'Alias {name} must be named after its library {library}')
        require(alias.get(ANDROID+'enabled') == 'true' and alias.get(ANDROID+'exported') == 'true', f'Alias {name} must be enabled and exported')
        require(alias.get(ANDROID+'permission') == PLUGIN_PERMISSION, f'Alias {name} must require the plugin permission')
        require(alias.get(ANDROID+'targetActivity') == '.WakeActivity', f'Alias {name} must target the wake activity')
        require(any(any(a.get(ANDROID+'name') == LIBRARY_ACTION for a in f.findall('action'))
                    and any(c.get(ANDROID+'name') == 'android.intent.category.DEFAULT' for c in f.findall('category'))
                    for f in alias.findall('intent-filter')), f'Alias {name} must answer {LIBRARY_ACTION}')
        aliases[library] = name
    require(sorted(aliases) == sorted(ids) and len(aliases) == len(app.findall('activity-alias')),
            f'Aliases {sorted(aliases)} must match the catalog libraries {sorted(ids)}')
    services = [s for s in app.findall('service') if s.get(ANDROID+'name') == '.BundledLibrariesInfoService']
    require(len(services) == 1, 'Missing the info service')
    for service in services:
        require(service.get(ANDROID+'permission') == PLUGIN_PERMISSION and service.get(ANDROID+'exported') == 'true', 'The info service must be exported with the plugin permission')
        require(any(any(a.get(ANDROID+'name') == 'org.autojs.plugin.INFO' for a in f.findall('action'))
                    and any(c.get(ANDROID+'name') == 'script-modules' for c in f.findall('category'))
                    for f in service.findall('intent-filter')), 'The info service must answer org.autojs.plugin.INFO in category script-modules')
    for component in [*app.findall('activity'), *app.findall('service'), *app.findall('receiver'), *app.findall('provider')]:
        launcher = any(any(c.get(ANDROID+'name') == 'android.intent.category.LAUNCHER' for c in f.findall('category')) for f in component.findall('intent-filter'))
        if component.get(ANDROID+'exported') == 'true' and not launcher:
            require(component.get(ANDROID+'permission') == PLUGIN_PERMISSION, f'Exported {component.get(ANDROID+"name")} must require the plugin permission')
    build = (root/'app/build.gradle.kts').read_text(encoding='utf-8')
    host = re.search(r'val requiredHostVersionCode = (\d+)L', build)
    common = json.loads((root/'.readme/common.json').read_text(encoding='utf-8'))
    require(host and common['required_host_version_code'] == host[1], 'common.json required_host_version_code must match requiredHostVersionCode of app/build.gradle.kts')
    require(common['library_count'] == str(len(ids)), 'common.json library_count must match the catalog')
    contract = (root/'app/src/main/java/io/github/supermonster003/autojs6/plugin/bundled/libraries/BundledLibrariesContract.kt').read_text(encoding='utf-8')
    for constant, key in (('PLUGIN_ID', 'plugin_id'), ('ENGINE', 'plugin_engine')):
        require(f'const val {constant} = "{common[key]}"' in contract, f'BundledLibrariesContract.{constant} must match common.json {key}')
        require(f'resValue("string", "{key}", "{common[key]}")' in build, f'resValue {key} of app/build.gradle.kts must match common.json {key}')
    require('resValue("string", "plugin_variant", "default")' in build, 'resValue plugin_variant must be "default"')
    require('resValue("string", "plugin_requires_host_version", "$requiredHostVersionCode")' in build, 'resValue plugin_requires_host_version must use requiredHostVersionCode')
    return errors

def check_punctuation(root):
    """ASCII punctuation in every tracked text file of ours, in all languages (COMMON_AGENTS.md)."""
    errors = []
    files = subprocess.check_output(['git', '-C', str(root), 'ls-files', '--cached', '--others', '--exclude-standard'], text=True).splitlines()
    for relative in files:
        path = root/relative
        if relative.startswith(PUNCTUATION_EXEMPT) or relative in PUNCTUATION_EXEMPT or path.suffix not in TEXT_SUFFIXES or not path.is_file():
            continue
        try:
            text = path.read_text(encoding='utf-8')
        except UnicodeDecodeError:
            continue
        found = sorted(set(non_ascii_punctuation(text)))
        if found:
            errors.append(f'Non-ASCII punctuation in {relative}: ' + ' '.join(f'U+{ord(ch):04X}' for ch in found))
    return errors

def main():
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument('--pending-commit', action='store_true', help='expect the next commit number during review')
    args = parser.parse_args()
    errors = check(ROOT, args.pending_commit)
    print(json.dumps({'repository': ROOT.name, 'errors': errors}, ensure_ascii=False, indent=2))
    return bool(errors)

if __name__ == '__main__':
    sys.exit(main())
