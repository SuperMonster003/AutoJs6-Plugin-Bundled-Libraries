# Bundled Libraries roadmap

Host side: AutoJs6 `docs/augment/ROADMAP.md` P4.3 (term T11, appendix C Q5 / Q6 / Q100-Q103) and the design `docs/augment/P4-3-bundled-libraries-design.md`. Decisions are made there; this file tracks the plugin repository.

宿主侧: AutoJs6 `docs/augment/ROADMAP.md` P4.3 (术语 T11, 附录 C Q5 / Q6 / Q100-Q103) 与设计 `docs/augment/P4-3-bundled-libraries-design.md`. 决策在宿主文档中, 本文件跟踪插件仓库.

## Decisions (from the host roadmap) / 决策

- Q5 / Q6 (2026-10-20): the on-demand libraries and the Node compatibility shims leave the AutoJs6 APK for this plugin, every library can be switched off, default on.
- Q100 (2026-10-24): the per-library switch is the enabled state of an `activity-alias` per library; AutoJs6 reads it with one PackageManager query, without starting the plugin.
- Q101 (2026-10-24): AutoJs6 reads the catalog and the files straight from the APK (package context), no ContentProvider.
- Q102 / Q103: host verification and getter behavior, see the host roadmap.

## Status / 状态

- [x] P1 Repository, catalog `script-modules.json`, files copied from AutoJs6 `assets/modules`, build check of the catalog, info service, launch page with switches, debug APK (2026-10-24, host session augment-p4-3).
- [x] P2 Host integration verified on G8441 in the four states (not installed, enabled, disabled in the Plugin Center or per library, uninstalled), both states of the host module layer; see the host roadmap appendix A "P4.3" (2026-10-24: 5 states x 22 items, both layer states the same, local files first on the normal build; the debug build gained `DebugLibrarySwitchReceiver` for the per-library switches from adb).
- [x] P3 Before the first release (host roadmap appendix C Q104, the maintainer chose (1) on 2026-10-24: make the repository releasable now, release with AutoJs6 6.8.0; on 2026-10-29 the maintainer chose to release now, before 6.8.0 is final, see Q180):
  - [x] Remote repository `SuperMonster003/AutoJs6-Plugin-Bundled-Libraries` (default branch `master` like the other official plugins, no tag) and push authorization (2026-10-24).
  - [x] Icon Studio icon: three books on a shelf, `.icons/recipe.json` with the portable runtime 1.3.0, `generate_icon_studio.py --check` verifies 15 resources; the placeholder vector is removed.
  - [x] 10-language `.readme` / `.changelog` sources and `.python/generate_markdown.py` (36 generated files: README x11, CHANGELOG x14, plugin instruction x11); the library tables are generated from `script-modules.json`; the settings page has "Release history".
  - [x] `THIRD_PARTY_NOTICES.md` checked against the files (axios 1.1.2, pangu 10.1.0, lodash 4.17.15, the packages inside the cheerio build) and `LICENSE` (MPL-2.0, the official text).
  - [x] CI (`.github/workflows/build.yml`, `markdown.yml`, `icon-studio.yml`), `.python/check_repository.py` (the alias / catalog / info service contract, the minimum host build in the build script and the documents, the index routing values, ASCII punctuation), `prepush_check.py`.
  - [x] Routing values for the official index: `plugin_id` `bundled-libraries`, `plugin_engine` `script-modules`, `plugin_variant` `default`, `plugin_requires_host_version` (the index generator resolves them to `5935`).
  - [x] `assembleDebug`, `assembleRelease`, unit tests and lint pass locally; the release build tasks `verifySignedReleaseArtifacts` / `appendDigestToReleasedFiles` check the signed APK (one universal APK, no native code, every module file stored uncompressed).
  - [x] Install smoke test on G8441 with the debug APK of `bd8b6ef` and a host debug 5935 without the module files (host `tools/augment/device_plugin_modules_check.py`, 2026-10-24): not installed, enabled, disabled in the Plugin Center, axios / buffer switched off, uninstalled, 22 items each, the same in both states of the host module layer, PASS.
  - [x] The settings page and "Release history" follow AutoJs6's language, night mode and theme colors (host roadmap appendix C Q115, the maintainer chose (2) on 2026-10-24): `HostAppearanceActivity` reads the versioned host settings contract (`AutoJs6HostSettingsContract`; `libs/common-plugin-api.aar` updated to the build that has it), the manifest queries `org.autojs.autojs6`, the system appearance when AutoJs6 is not installed, disabled or refuses the call; no new strings. G8441 (debug 5935 without the module files, 2026-10-24): `.python/device_host_appearance_check.py` 24 checks PASS (Japanese / dark / indigo on a cold start, Traditional Chinese (Taiwan) / light / navajo white after returning to the page, the release history page, the system appearance with AutoJs6 disabled), and `device_plugin_modules_check.py` again 5 states x 22 items, PASS.
  - [x] `requiresHostVersion`: released with build 5935 (the 6.8.0 development build that reads the catalog), not the final 6.8.0 build (2026-10-29, Q180).
  - [x] Signed release, official index entry (engine `script-modules`), documentation site entry (2026-10-29, host session augment-bl-publish): `v1.0.0` = `d287681` (versionCode 7, one universal APK of 1125943 bytes, `releases/autojs6-plugin-bundled-libraries-v1.0.0-universal-b45b2448.apk`), GitHub Release with the APK and SHA256SUMS; the signed build first failed because four non-script files (`.md`, `.ts`, `LICENSE`, `.txt`) were compressed, fixed by `d287681` (`noCompress`); official index `9b5976f` (release manifest `7.json`, Icon Studio catalog icon); documentation site page `bundledLibraries` (`9d6af71`, generated files `3f6abdf`).

## Release steps / 发布步骤

Followed for 1.0.0 on 2026-10-29 with build 5935 in step 1 (see P3 and Q180); the steps also apply to later releases.
zh-CN: 1.0.0 已于 2026-10-29 按此发布, 第 1 步用构建 5935 (见 P3 与 Q180); 之后的版本照此执行.

1. Set `requiredHostVersionCode` in `app/build.gradle.kts` and `required_host_version_code` in `.readme/common.json` to the build number of AutoJs6 6.8.0 (`check_repository` keeps them equal), check `required_host_version_name`, then run `.python/generate_markdown.bat`.
   zh-CN: 把 `app/build.gradle.kts` 的 `requiredHostVersionCode` 与 `.readme/common.json` 的 `required_host_version_code` 改为 6.8.0 的构建号 (两处由 `check_repository` 保持一致), 核对 `required_host_version_name`, 再运行 `.python/generate_markdown.bat`.
2. Set the release date of v1.0.0 in the 10 `.changelog/lang_*.json` files, regenerate, and commit with `VERSION_BUILD` close to the commit count.
   zh-CN: 改 10 个 `.changelog/lang_*.json` 中 v1.0.0 的发布日期, 重新生成, 提交时 `VERSION_BUILD` 与提交数接近.
3. Build the signed release with the official key (`sign.properties`, never committed): `gradlew :app:appendDigestToReleasedFiles`; it runs `verifySignedReleaseArtifacts` and writes `releases/autojs6-plugin-bundled-libraries-v1.0.0-universal-<crc32>.apk`.
   zh-CN: 用官方密钥 (`sign.properties`, 不入库) 构建签名发行包: `gradlew :app:appendDigestToReleasedFiles`, 它先运行 `verifySignedReleaseArtifacts`, 输出到 `releases/`.
4. Run `py D:/idea-projects/prepush_check.py`, push `master`, tag `v1.0.0` and publish the GitHub release with the APK and the release notes from `app/src/main/assets/doc/CHANGELOG-en.md` / `CHANGELOG-zh-Hans.md`.
   zh-CN: 运行 `prepush_check.py`, 推送 `master`, 打 `v1.0.0` 标签并发布 GitHub Release (附 APK 与 changelog).
5. Register the release in AutoJs6-Official-Plugins-Index (release manifest, catalog icons; engine `script-modules`, variant `default`, plugin id `bundled-libraries`, package `io.github.supermonster003.autojs6.plugin.bundled.libraries`) and add the repository to `official-repositories.json` if needed.
   zh-CN: 在官方插件索引登记 (发行清单, 目录图标; engine `script-modules`), 需要时把仓库加入 `official-repositories.json`.
6. Add the plugin page to the documentation site (10 languages).
   zh-CN: 文档站增加插件页 (10 种语言).
7. AutoJs6 removes its own copies of the files (host roadmap P5.2), see P4 below.
   zh-CN: 宿主删除自带的模块文件 (宿主 roadmap P5.2), 见下方 P4.

## Open questions / 待决事项

Registered in the host roadmap appendix C: Q115 (the settings-page specification for standalone plugins; the maintainer chose (2) on 2026-10-24: follow AutoJs6's appearance, done, see P3), Q116 (the icon design), Q117 (default branch `master` instead of `main`). The recommended options of Q116 / Q117 are applied.

在宿主 roadmap 附录 C 登记: Q115 (独立插件设置页规范做到哪一步; 维护者 2026-10-24 选 (2): 跟随 AutoJs6 外观, 已完成, 见 P3), Q116 (图标设计), Q117 (默认分支用 `master` 而不是 `main`). Q116 / Q117 推荐项已先行.

Q180 (2026-10-29, host session augment-bl-publish): 1.0.0 was released before AutoJs6 6.8.0 is final, with `requiresHostVersion` 5935 (the maintainer's choice). When 6.8.0 is final: (1) keep 5935 and publish 1.0.1 only when a change needs it (recommended, applied); (2) publish 1.0.1 that raises the minimum host to the final 6.8.0 build (one more release: build, GitHub Release, index manifest, README).

Q180 (2026-10-29): 1.0.0 在 6.8.0 定版前以最低宿主 5935 发布 (维护者选择). 6.8.0 定版后: (1) 保持 5935, 需要时再出 1.0.1 (推荐, 已先行); (2) 出 1.0.1 把最低宿主抬到 6.8.0 定版构建 (多一次发布: 构建, Release, 索引清单, README).

- [x] P4 After the release: AutoJs6 removes its copies of the files (host roadmap P5.2). Done on 2026-10-29 in host `67ef4c1a5` (P5.2 stage 5, host session augment-bl-publish): AutoJs6 and the apps it packages no longer carry the 32 files, so this plugin is their only source. Packaged apps do not include them until a separate host project copies the libraries a packaged script uses from the installed plugin (host appendix C Q181, the maintainer chose (3)). The host keeps test copies of pangu and path (Q182) and its `tools/pangu` writes to them, so a pangu upgrade is copied here by hand (until P5).
- [x] P5 The module tests that read pangu and path move here from AutoJs6 (host appendix C Q182, the maintainer chose (2) on 2026-10-29, host session augment-q182): `PanguModuleTest` (the 4 host cases) and `PathModuleTest` (89 cases against `path.posix` of Node.js 24.15) load `src/main/assets/modules` through `RhinoModules` the way AutoJs6 does (ES6, interpreted mode, sandboxed `require`) and run in `:app:testDebugUnitTest`, so CI (`build.yml`) and `prepush_check.py` run them. They use a copy of AutoJs6's Rhino build in `libs/` (Q195 below). AutoJs6 deleted its test copies and the tests that read them in host `dbc5eb0f5`; it keeps the Rhino const regression and the path device cases, and its `tools/pangu` writes `pangu.min.js` / `pangu.LICENSE.txt` here (`--out <dir>`, `AUTOJS6_BUNDLED_LIBRARIES_MODULES`, by default the checkout next to AutoJs6). No release: tests only.
  zh-CN: 读 pangu 与 path 的模块测试从 AutoJs6 移到这里 (宿主附录 C Q182, 维护者 2026-10-29 选 (2)): `PanguModuleTest` (宿主原 4 例) 与 `PathModuleTest` (对照 Node.js 24.15 `path.posix` 的 89 条), 经 `RhinoModules` 按 AutoJs6 的方式加载 `src/main/assets/modules`, 随 `:app:testDebugUnitTest` 由 CI 与 `prepush_check.py` 运行; 引擎是 `libs/` 中 AutoJs6 Rhino 构建的副本 (见下方 Q195). 宿主 `dbc5eb0f5` 删除测试副本与读副本的测试, 保留 Rhino const 回归与 path 设备用例, `tools/pangu` 改为输出到本仓库. 只改测试, 不发版.

Q195 (2026-10-29, host session augment-q182): which Rhino runs the module tests. path.js relies on a const in a loop body being bound per iteration; mozilla/rhino fixed it on master only (2026-10-13), and `org.mozilla:rhino` 1.9.1 fails 21 of the 89 path cases (join / normalize / resolve / relative). (1) Keep a copy of AutoJs6's `libs/org-mozilla-rhino-2_0_0-SNAPSHOT.jar` in `libs/` (3.1 MB, test classpath only, copied again when AutoJs6 updates it; recommended, applied); (2) `org.mozilla:rhino` 1.9.1 from Maven Central with the 21 cases skipped until a release has the fix.

Q195 (2026-10-29): 模块测试用哪个 Rhino. path.js 依赖循环体中的 const 每轮重新绑定, 上游只在 master 修复 (2026-10-13), `org.mozilla:rhino` 1.9.1 跑 89 条 path 用例失败 21 条. (1) `libs/` 保留 AutoJs6 Rhino jar 的副本 (3.1 MB, 只进测试类路径, AutoJs6 更新时重新复制; 推荐, 已先行); (2) 用 Maven Central 的 1.9.1, 跳过这 21 条直到发布版含修复.
