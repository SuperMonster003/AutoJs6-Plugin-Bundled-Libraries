"""Device check of the host appearance (host roadmap appendix C Q115 (2)): the settings page and the release history
page follow the language, night mode and theme colors of AutoJs6, and the system while AutoJs6 is disabled.

    py -3.12 -I .python/device_host_appearance_check.py -s SERIAL --apk app/build/outputs/apk/debug/<name>.apk

Needs a debuggable AutoJs6 on the device (its preferences are edited with run-as while it is stopped) and the debug build
of this plugin (signed with the official key, so AutoJs6 answers it; ReleaseHistoryActivity is exported to adb in debug
builds only). Never taps: the pages start with am start, the results come from screencap pixels and uiautomator dump
text. Prints one line per check and PASS / FAIL. The host preferences file is restored byte for byte and AutoJs6 is
enabled again at the end; the plugin is uninstalled unless it was installed before (then it stays on the APK given).
"""
import argparse
import hashlib
import pathlib
import re
import struct
import subprocess
import sys
import time
import xml.etree.ElementTree as ET
import zlib

HOST = "org.autojs.autojs6"
PLUGIN = "io.github.supermonster003.autojs6.plugin.bundled.libraries"
SETTINGS = f"{PLUGIN}/.SettingsActivity"
RELEASE_HISTORY = f"{PLUGIN}/.ReleaseHistoryActivity"
PREFS = f"shared_prefs/{HOST}_preferences.xml"
DUMP = "/data/local/tmp/bundled-libraries-appearance.xml"
SETTLE_SECONDS = 5

# Host settings of each scenario and what the plugin pages must show. zh-CN: 各场景的宿主设置与插件页面应有的显示.
JA_DARK = {"language": "key_$_app_language_ja", "night": True, "primary": 0xFF3F51B5, "primary_dark": 0xFF303F9F, "accent": 0xFFFF4081}
TW_LIGHT = {"language": "key_$_app_language_zh_tw", "night": False, "primary": 0xFFFFDEAD, "primary_dark": 0xFFFFDEAD, "accent": 0xFFFFDEAD}


class Device:
    def __init__(self, serial):
        self.serial = serial

    def run(self, *args, check=True, stdin=None):
        result = subprocess.run(["adb", "-s", self.serial, *args], input=stdin, capture_output=True, check=False)
        if check and result.returncode != 0:
            raise RuntimeError(f"adb {' '.join(args)}: {result.stderr.decode(errors='replace').strip()}")
        return result.stdout

    def shell(self, command, check=True):
        return self.run("shell", command, check=check).decode(errors="replace")

    def read_prefs(self):
        return self.run("exec-out", "run-as", HOST, "cat", PREFS)

    def write_prefs(self, data):
        # exec-in passes its arguments without a shell, so the redirection goes through adb shell (stdin forwarded).
        self.run("shell", f'run-as {HOST} sh -c "cat > {PREFS}"', stdin=data)
        if self.read_prefs() != data:
            raise RuntimeError("the host preferences were not written")

    def screen(self):
        raw = self.run("exec-out", "screencap")
        width, height, _ = struct.unpack_from("<III", raw)
        header = len(raw) - width * height * 4
        return Screen(width, height, raw[header:])

    def dump(self):
        for _ in range(3):
            self.shell(f"uiautomator dump {DUMP}", check=False)
            text = self.run("exec-out", "cat", DUMP, check=False).decode(errors="replace")
            self.shell(f"rm -f {DUMP}", check=False)
            if text.startswith("<?xml"):
                return ET.fromstring(text)
            time.sleep(1)
        raise RuntimeError("uiautomator dump failed")

    def start(self, component, *flags):
        self.shell(f"am start -W {' '.join(flags)} -n {component}")
        time.sleep(SETTLE_SECONDS)

    def stop_all(self):
        self.shell(f"am force-stop {PLUGIN}")
        self.shell(f"am force-stop {HOST}")


class Screen:
    def __init__(self, width, height, pixels):
        self.width, self.height, self.pixels = width, height, pixels

    def at(self, x, y):
        offset = (y * self.width + x) * 4
        r, g, b = self.pixels[offset:offset + 3]
        return 0xFF000000 | r << 16 | g << 8 | b

    def png(self, path):
        rows = b"".join(b"\x00" + self.pixels[y * self.width * 4:(y + 1) * self.width * 4] for y in range(self.height))

        def chunk(kind, data):
            return struct.pack(">I", len(data)) + kind + data + struct.pack(">I", zlib.crc32(kind + data))

        header = struct.pack(">IIBBBBB", self.width, self.height, 8, 6, 0, 0, 0)
        path.write_bytes(b"\x89PNG\r\n\x1a\n" + chunk(b"IHDR", header) + chunk(b"IDAT", zlib.compress(rows, 6)) + chunk(b"IEND", b""))


def with_host_settings(prefs, settings):
    """[prefs] (the XML of the host preferences) with the language, night mode and theme colors of [settings]."""
    text = prefs.decode("utf-8")
    entries = {
        "key_$_app_language": ("string", settings["language"]),
        "key_$_night_mode": ("string", "key_$_night_mode_always_on" if settings["night"] else "key_$_night_mode_always_off"),
        "key_$_auto_night_mode_enabled": ("boolean", "false"),
        "key_$_night_mode_enabled": ("boolean", "true" if settings["night"] else "false"),
        "key_$_theme_color_primary": ("int", str(settings["primary"] - (1 << 32))),
        "key_$_theme_color_primary_dark": ("int", str(settings["primary_dark"] - (1 << 32))),
        "key_$_theme_color_accent": ("int", str(settings["accent"] - (1 << 32))),
    }
    for name, (kind, value) in entries.items():
        pattern = re.compile(r'\s*<(string|int|boolean) name="' + re.escape(name) + r'"(?: value="[^"]*" />|>[^<]*</string>)')
        text = pattern.sub("", text)
        element = f'<string name="{name}">{value}</string>' if kind == "string" else f'<{kind} name="{name}" value="{value}" />'
        text = text.replace("</map>", f"    {element}\n</map>")
    return text.encode("utf-8")


def bounds(node):
    left, top, right, bottom = map(int, re.findall(r"\d+", node.get("bounds")))
    return left, top, right, bottom


def texts(root):
    return [node.get("text") for node in root.iter("node") if node.get("text")]


def luminance(color):
    def channel(value):
        value /= 255
        return value / 12.92 if value <= 0.03928 else ((value + 0.055) / 1.055) ** 2.4

    return 0.2126 * channel(color >> 16 & 0xFF) + 0.7152 * channel(color >> 8 & 0xFF) + 0.0722 * channel(color & 0xFF)


def close(first, second, tolerance=6):
    return all(abs((first >> shift & 0xFF) - (second >> shift & 0xFF)) <= tolerance for shift in (16, 8, 0))


class Checks:
    def __init__(self):
        self.failed = 0

    def check(self, name, ok, detail):
        self.failed += not ok
        print(f"{'PASS' if ok else 'FAIL'} {name}: {detail}", flush=True)


def page_appearance(device, checks, name, shots, expect_texts, settings):
    """Checks the page on screen; [settings] None means the system appearance (AutoJs6 unavailable)."""
    root = device.dump()
    screen = device.screen()
    if shots:
        screen.png(shots / f"{name}.png")
    found = texts(root)
    for expected in expect_texts:
        # "text*" is a part of a longer text, anything else a whole text. zh-CN: "text*" 为长文本的一部分, 其余为完整文本.
        shown = any(expected[:-1] in text if expected.endswith("*") else expected == text for text in found)
        checks.check(f"{name} text", shown, f"{expected.rstrip('*')!r} {'shown' if shown else 'missing'}")
    bar = next((node for node in root.iter("node") if node.get("resource-id") == "android:id/action_bar"), None)
    if bar is None:
        checks.check(f"{name} action bar", False, "android:id/action_bar not in the dump")
        return
    left, top, right, bottom = bounds(bar)
    bar_color = screen.at(right - 4, (top + bottom) // 2)
    # Just above the action bar: the top rows may carry the pointer location overlay of the developer options.
    status_color = screen.at(screen.width // 2, max(top - 4, 0))
    background = screen.at(screen.width - 3, (bottom + screen.height) // 2)
    if settings is None:
        checks.check(f"{name} action bar", bar_color not in (JA_DARK["primary"], TW_LIGHT["primary"]), f"#{bar_color & 0xFFFFFF:06X} (theme default)")
        checks.check(f"{name} background", luminance(background) > 0.5, f"#{background & 0xFFFFFF:06X} light (system)")
        return
    checks.check(f"{name} action bar", close(bar_color, settings["primary"]), f"#{bar_color & 0xFFFFFF:06X} want #{settings['primary'] & 0xFFFFFF:06X}")
    checks.check(f"{name} status bar", close(status_color, settings["primary_dark"]), f"#{status_color & 0xFFFFFF:06X} want #{settings['primary_dark'] & 0xFFFFFF:06X}")
    dark = luminance(background) < 0.2
    checks.check(f"{name} background", dark == settings["night"], f"#{background & 0xFFFFFF:06X} {'dark' if dark else 'light'}")
    switch = next((node for node in root.iter("node") if node.get("class") == "android.widget.Switch" and node.get("checked") == "true"), None)
    if switch is not None:
        left, top, right, bottom = bounds(switch)
        thumb = screen.at(right - (bottom - top) // 3, (top + bottom) // 2)
        accent = settings["accent"]
        # On a light page a pale accent is darkened for contrast (AppearanceColors.readable). zh-CN: 亮色页面上浅色强调色会加深.
        ok = close(thumb, accent, 40) if settings["night"] else luminance(thumb) < luminance(accent)
        checks.check(f"{name} switch", ok, f"thumb #{thumb & 0xFFFFFF:06X}, accent #{accent & 0xFFFFFF:06X}")


def main():
    parser = argparse.ArgumentParser(description=__doc__, formatter_class=argparse.RawDescriptionHelpFormatter)
    parser.add_argument("-s", "--serial", required=True)
    parser.add_argument("--apk", required=True, type=pathlib.Path, help="the debug APK of this plugin")
    parser.add_argument("--shots", type=pathlib.Path, help="directory for PNG screenshots of each page")
    args = parser.parse_args()
    sys.stdout.reconfigure(encoding="utf-8", errors="replace")
    device = Device(args.serial)
    checks = Checks()
    if args.shots:
        args.shots.mkdir(parents=True, exist_ok=True)

    package = device.shell(f"dumpsys package {HOST}")
    if "DEBUGGABLE" not in package:
        sys.exit(f"{HOST} must be a debuggable build on {args.serial}")
    if device.shell(f"run-as {HOST} ls shared_prefs", check=False).count(f"{HOST}_preferences.xml.bak"):
        sys.exit("the host preferences have a pending .bak file")
    plugin_before = PLUGIN in device.shell(f"pm list packages {PLUGIN}")
    device.stop_all()
    original = device.read_prefs()
    print(f"host preferences md5 {hashlib.md5(original).hexdigest()[:8]}, plugin installed before: {plugin_before}", flush=True)
    try:
        device.run("install", "-r", "-d", str(args.apk))

        # Cold start with the host in Japanese, dark, indigo; the host is stopped before every change of its preferences
        # (the install may have started it). zh-CN: 冷启动, 宿主为日语, 深色, 靛蓝; 每次改宿主偏好前先停止宿主 (安装可能已启动它).
        device.shell(f"am force-stop {HOST}")
        device.write_prefs(with_host_settings(original, JA_DARK))
        device.start(SETTINGS)
        page_appearance(device, checks, "settings-ja-dark", args.shots, ["ライブラリ"], JA_DARK)

        # Back to the page after a change of the host settings (onResume). zh-CN: 宿主设置改变后回到页面 (onResume).
        device.shell("am start -W -a android.intent.action.MAIN -c android.intent.category.HOME")
        device.shell(f"am force-stop {HOST}")
        device.write_prefs(with_host_settings(original, TW_LIGHT))
        device.start(SETTINGS, "--activity-reorder-to-front")
        page_appearance(device, checks, "settings-resume-zh-hant-tw-light", args.shots, ["函式庫"], TW_LIGHT)

        device.start(RELEASE_HISTORY)
        page_appearance(device, checks, "release-history-zh-hant-tw-light", args.shots, ["發行記錄", "外掛頁面跟隨 AutoJs6*"], TW_LIGHT)

        # AutoJs6 disabled: the system appearance (en-US, light). zh-CN: 停用 AutoJs6: 系统外观 (en-US, 亮色).
        device.shell(f"am force-stop {PLUGIN}")
        device.shell(f"pm disable-user --user 0 {HOST}")
        try:
            device.start(SETTINGS)
            page_appearance(device, checks, "settings-host-disabled", args.shots, ["Libraries"], None)
            device.start(RELEASE_HISTORY)
            page_appearance(device, checks, "release-history-host-disabled", args.shots, ["Release history", "The plugin page follows*"], None)
        finally:
            device.shell(f"pm enable {HOST}")
    finally:
        device.stop_all()
        device.write_prefs(original)
        restored = device.read_prefs()
        checks.check("restore host preferences", restored == original, f"md5 {hashlib.md5(restored).hexdigest()[:8]}")
        enabled = f"package:{HOST}" in device.shell(f"pm list packages -e {HOST}").split()
        checks.check("restore host enabled", enabled, "enabled" if enabled else "disabled")
        if not plugin_before:
            device.run("uninstall", PLUGIN, check=False)
    print("PASS" if not checks.failed else f"FAIL ({checks.failed})")
    return 1 if checks.failed else 0


if __name__ == "__main__":
    sys.exit(main())
