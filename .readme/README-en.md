<!--suppress HtmlDeprecatedAttribute, HttpUrlsUsage -->

<div align="center">
  <p>
    <img src="https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/app/src/main/res/mipmap/ic_launcher.png?raw=true" alt="autojs6-plugin-bundled-libraries-ic-launcher" border="0" width="128" />
  </p>

  <p>JavaScript libraries and Node compatibility shims for require in AutoJs6 scripts</p>

  <p>
    <a href="https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/releases"><img alt="GitHub release (latest by date)" src="https://img.shields.io/github/v/release/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries?label=Release"/></a>
    <a href="https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/issues"><img alt="GitHub closed issues" src="https://img.shields.io/github/issues/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries?color=A24232&label=Issues"/></a>
    <a href="https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/LICENSE"><img alt="GitHub License" src="https://img.shields.io/github/license/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries?color=534BAE&label=License"/></a>
  </p>
</div>

******

### Languages

******

The README.md file is currently available in the following languages:

- [简体中文 [zh-Hans]](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/.readme/README-zh-Hans.md)
- [繁體中文 (香港) [zh-Hant-HK]](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/.readme/README-zh-Hant-HK.md)
- [繁體中文 (台灣) [zh-Hant-TW]](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/.readme/README-zh-Hant-TW.md)
- English [en] # current
- [Français [fr]](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/.readme/README-fr.md)
- [Español [es]](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/.readme/README-es.md)
- [日本語 [ja]](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/.readme/README-ja.md)
- [한국어 [ko]](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/.readme/README-ko.md)
- [Русский [ru]](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/.readme/README-ru.md)
- [العربية [ar]](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/.readme/README-ar.md)

******

### Introduction

******

Bundled Libraries holds the 13 script libraries that used to be built into AutoJs6: axios, cheerio, dayjs, i18n, pangu and lodash, plus the Node compatibility shims buffer, events, fs, os, path, process and nodejs. Starting with AutoJs6 6.8.0, the AutoJs6 package no longer includes them; with this plugin installed and enabled, `require('axios')`, `require('fs')` and the like, as well as the global objects `axios`, `cheerio`, `dayjs`, `i18n` and `pangu`, keep working exactly as before.

The plugin is only a resource package: AutoJs6 reads the library files straight from the plugin package, scripts never start the plugin process, and the plugin needs no storage, network or other permission (the network requests of axios are made by AutoJs6, which runs the script). Libraries you do not need can be switched off one by one on the plugin page.

******

### Included libraries

******

The module names are the arguments of `require`; a library with a global object can also be used without `require`. Sources and licenses of the libraries are listed in [THIRD_PARTY_NOTICES.md](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/THIRD_PARTY_NOTICES.md).

#### Libraries

| Library | Modules | Global object | Version | License |
|---|---|---|---|---|
| axios | `axios` | `axios` | - | MIT |
| cheerio | `cheerio` | `cheerio` | - | MIT |
| dayjs | `dayjs` | `dayjs` | - | MIT |
| i18n | `i18n`, `banana-i18n` | `i18n` | - | MIT |
| pangu | `pangu` | `pangu` | 10.1.0 | MIT |
| lodash | `lodash` | - | 4.17.15 | MIT |

#### Node compatibility shims

| Library | Modules | Global object | Version | License |
|---|---|---|---|---|
| buffer | `buffer` | - | - | MPL-2.0 |
| events | `events` | - | - | MPL-2.0 |
| fs | `fs` | - | - | MPL-2.0 |
| os | `os` | - | - | MPL-2.0 |
| path | `path` | - | - | MPL-2.0 |
| process | `process` | - | - | MPL-2.0 |
| nodejs | `nodejs` | - | - | MPL-2.0 |

******

### Usage

******

1. Use AutoJs6 6.8.0 (5935) or later.
2. Install Bundled Libraries from the Plugin Center of AutoJs6 (or download the APK from [Releases](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/releases)) and make sure the plugin is enabled.
3. Use `require` for the libraries you need as usual, or use the global objects directly, for example:

```javascript
var _ = require('lodash');
var path = require('path');

console.log(dayjs(0).valueOf());
console.log(cheerio.load('<p>AutoJs6</p>')('p').text());
console.log(_.map([1, 2], function (x) { return x * 2; }).join());
console.log(path.isAbsolute('/sdcard/Scripts'));
```

> There is a single universal package without native libraries, for devices with Android 7.0 (API 24) or later.

******

### Per-library switches

******

The plugin page (opened from the Plugin Center of AutoJs6 or the Bundled Libraries icon on the home screen) has one switch per library, all on by default:

- A library that is switched off can no longer be required and its global object cannot be used; switch it on again to restore it.
- A switch applies to the next `require`: modules that a running script has already loaded stay until the script ends.
- `fs` needs `buffer`, and `nodejs` needs `process` and `buffer`: with `buffer` or `process` switched off, the modules that need them cannot load either.
- "Release history" at the bottom of the page lists the changes of each version.

******

### When the plugin is unavailable

******

When the plugin is not installed, not authorized, disabled in the Plugin Center or incompatible, or a library is switched off, `require` of that library and reading its global object throw an error:

- The error is an `Error` with `code` `MODULE_NOT_FOUND`; its message says whether to install the plugin or where to switch it on, and scripts can handle it with `try...catch`.
- The error object also has `library` (the library name) and `reason` (`not-installed`, `unauthorized`, `plugin-disabled`, `library-disabled` or `incompatible`).
- A change of the plugin state never interrupts a running script, it only affects later `require` calls; the other built-in modules of AutoJs6 and the script's own files are not affected.

******

### Compatibility

******

- Requires AutoJs6 6.8.0 (build 5935) or later; earlier versions of AutoJs6 include these libraries themselves and do not need the plugin.
- Supports Android 7.0 (API 24) or later, one package for all processor architectures.
- The library files are identical to the ones that used to be built into AutoJs6, so existing scripts need no change.
- The plugin serves the default JavaScript scripts of AutoJs6; scripts of the Node.js runtime plugin use the modules of Node.js itself.

******

### FAQ

******

#### Why does AutoJs6 no longer include these libraries?

Only some scripts use them. As a plugin they keep the AutoJs6 package smaller, whoever needs them installs the plugin, and only the libraries in use need to stay on. The files in the plugin are the same as the built-in ones.

#### My script uses the global object `axios` directly. Do I have to switch to `require`?

No. The global objects `axios`, `cheerio`, `dayjs`, `i18n` and `pangu` are the same modules that `require` returns, and both load them from this plugin.

#### Does the plugin go online or run in the background?

No. The plugin has no network permission, and running a script never starts the plugin process. The network requests of axios are made by AutoJs6, which runs the script.

#### The error says the plugin needs to be authorized. What should I do?

The release from the Plugin Center of AutoJs6 or from the Releases of this project needs no authorization. A build of your own has a different signature and must first be authorized in the Plugin Center as prompted.

******

### Plugin interface

******

The following information targets AutoJs6 host and plugin developers; the host uses these identifiers to discover the plugin and read the library files:

```text
application id: io.github.supermonster003.autojs6.plugin.bundled.libraries
plugin id: bundled-libraries
engine: script-modules
info service: org.autojs.plugin.INFO (category script-modules)
catalog: meta-data org.autojs.plugin.scriptModules.CATALOG -> assets/script-modules.json (schema 1)
library switch: activity-alias .library.<id>, action org.autojs.plugin.SCRIPT_MODULE_LIBRARY
wake action: org.autojs.plugin.action.WAKE
minimum host build: 5935
native library: none (all ABIs)
```

******

### Roadmap

******

The pre-release checklist and the release steps are tracked in ROADMAP.md; the decisions behind them are in `docs/augment/ROADMAP.md` of the AutoJs6 repository.

- [View ROADMAP.md](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/ROADMAP.md)

******

### Release history

******

#### v1.0.0

_2026/10/24_

- `Hint` Requires AutoJs6 6.8.0 (5935) or later
- `Hint` Starting with AutoJs6 6.8.0, the libraries below no longer come with AutoJs6: with this plugin installed, require and the global objects work as before and existing scripts need no change; without the plugin, or with it disabled, require of these libraries gives an error that says how to install or enable it
- `Feature` axios, cheerio, dayjs, i18n (with banana-i18n), pangu and lodash for require, with axios, cheerio, dayjs, i18n and pangu also available as global objects
- `Feature` Node compatibility shims buffer, events, fs, os, path, process and nodejs for require
- `Feature` Plugin page with a switch per library (all on by default) and the release history; a library switched off is unavailable to later require calls
- `Feature` The plugin page follows the language, night mode and theme color of AutoJs6, and the system when AutoJs6 is not installed or its settings cannot be read
- `Dependency` Attach AutoJs6 Common Plugin API

##### For more release history, refer to

* [CHANGELOG.md](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/app/src/main/assets/doc/CHANGELOG-en.md)

******

### Build

******

This section targets developers who want to build the plugin from source.

```powershell
.\gradlew.bat :app:assembleDebug :app:testDebugUnitTest
py .python\generate_markdown.py --check
py .python\generate_icon_studio.py --check
py .python\check_repository.py
```

******

### License

******

The plugin code and the modules written for AutoJs6 (the Node compatibility shims, the i18n wrapper and the axios adapter) are licensed under the [Mozilla Public License 2.0](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/LICENSE). The bundled third-party libraries keep their own licenses (MIT and others), see [THIRD_PARTY_NOTICES.md](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/THIRD_PARTY_NOTICES.md).

******

### Related links

******

- AutoJs6 project: https://github.com/SuperMonster003/AutoJs6
- Third-party notices: https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/THIRD_PARTY_NOTICES.md
