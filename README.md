# AutoJs6 Plugin: Bundled Libraries

Official AutoJs6 plugin that provides JavaScript libraries and Node compatibility shims for `require` in AutoJs6 scripts.

AutoJs6 官方插件, 为 AutoJs6 脚本的 `require` 提供 JavaScript 库与 Node 兼容垫片.

| | |
|---|---|
| Package | `io.github.supermonster003.autojs6.plugin.bundled.libraries` |
| Plugin Center id / category | `bundled-libraries` / `script-modules` |
| Required AutoJs6 | build 5935 or later with plugin script modules (host roadmap T11 / P4.3) |
| License | MPL-2.0 (plugin code and the AutoJs6 shims); the bundled libraries keep their own licenses, see [THIRD_PARTY_NOTICES.md](THIRD_PARTY_NOTICES.md) |

## Libraries

| Group | Library | Modules | License |
|---|---|---|---|
| Libraries | axios | `axios` | MIT |
| Libraries | cheerio | `cheerio` | MIT |
| Libraries | dayjs | `dayjs` | MIT |
| Libraries | i18n | `i18n`, `banana-i18n` | MIT (banana-i18n), MPL-2.0 (i18n wrapper) |
| Libraries | pangu | `pangu` | MIT |
| Libraries | lodash | `lodash` (lodash core 4.17.15) | MIT |
| Node compatibility shims | buffer, events, fs, os, path, process, nodejs | one module each | MPL-2.0 |

The files are the ones AutoJs6 carried in `assets/modules` up to 6.8.0; AutoJs6 removes its copies once this plugin is published (host roadmap P5.2).

文件与 AutoJs6 6.8.0 及之前在 `assets/modules` 中携带的相同; 本插件发布后 AutoJs6 删除自身的副本 (宿主 ROADMAP P5.2).

## How AutoJs6 uses it / 宿主如何使用

- The catalog `app/src/main/assets/script-modules.json` (meta-data `org.autojs.plugin.scriptModules.CATALOG`) lists each library, its module names and the files it owns under `assets/modules`. The build checks it against the files (`verifyScriptModuleCatalog`).
- AutoJs6 reads the catalog and the module files straight from this APK and never starts the plugin process for a script: `require('axios')` and the global `axios` getter load the module from the plugin when AutoJs6 itself has no local copy.
- Each library can be switched off on the launch page (default on). A library is switched off by disabling its `activity-alias` (`.library.<id>`, action `org.autojs.plugin.SCRIPT_MODULE_LIBRARY`), which AutoJs6 sees through `PACKAGE_CHANGED`. A switch takes effect for the next `require` of a module that a running script has not loaded yet.
- When the plugin is not installed, disabled in the Plugin Center or a library is switched off, `require` throws an `Error` with `code` `MODULE_NOT_FOUND` and a message that says where to install or enable it.

- 清单 `app/src/main/assets/script-modules.json` (meta-data `org.autojs.plugin.scriptModules.CATALOG`) 列出每个库, 其模块名及其在 `assets/modules` 下拥有的文件; 构建时由 `verifyScriptModuleCatalog` 与文件核对.
- AutoJs6 直接从本 APK 读取清单与模块文件, 脚本不会启动插件进程: AutoJs6 自身没有本地副本时, `require('axios')` 与全局 `axios` getter 从插件加载模块.
- 每个库都可在启动页关闭 (默认开启). 关闭即禁用该库的 `activity-alias` (`.library.<id>`, action `org.autojs.plugin.SCRIPT_MODULE_LIBRARY`), AutoJs6 经 `PACKAGE_CHANGED` 得知; 开关对运行中的脚本尚未加载的模块在下一次 `require` 时生效.
- 插件未安装, 在插件中心被禁用或某个库被关闭时, `require` 抛出 `code` 为 `MODULE_NOT_FOUND` 的 `Error`, 消息说明到哪里安装或启用.

## Build / 构建

```bash
./gradlew assembleDebug
```

Signing reads the ignored `sign.properties`; without it the build uses the debug key, and AutoJs6 then asks to authorize the plugin in the Plugin Center.

签名读取不入库的 `sign.properties`; 没有时使用 debug 密钥, AutoJs6 会要求在插件中心授权本插件.
