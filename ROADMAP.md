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
- [ ] P3 Before the first release (host roadmap appendix C Q104, the maintainer chose (1) on 2026-10-24: make the repository releasable now, release with AutoJs6 6.8.0):
  - [x] Remote repository `SuperMonster003/AutoJs6-Plugin-Bundled-Libraries` (default branch `master` like the other official plugins, no tag) and push authorization (2026-10-24).
  - [x] Icon Studio icon: three books on a shelf, `.icons/recipe.json` with the portable runtime 1.3.0, `generate_icon_studio.py --check` verifies 15 resources; the placeholder vector is removed.
  - [x] 10-language `.readme` / `.changelog` sources and `.python/generate_markdown.py` (36 generated files: README x11, CHANGELOG x14, plugin instruction x11); the library tables are generated from `script-modules.json`; the settings page has "Release history".
  - [x] `THIRD_PARTY_NOTICES.md` checked against the files (axios 1.1.2, pangu 10.1.0, lodash 4.17.15, the packages inside the cheerio build) and `LICENSE` (MPL-2.0, the official text).
  - [x] CI (`.github/workflows/build.yml`, `markdown.yml`, `icon-studio.yml`), `.python/check_repository.py` (the alias / catalog / info service contract, the minimum host build in the build script and the documents, the index routing values, ASCII punctuation), `prepush_check.py`.
  - [x] Routing values for the official index: `plugin_id` `bundled-libraries`, `plugin_engine` `script-modules`, `plugin_variant` `default`, `plugin_requires_host_version` (the index generator resolves them to `5935`).
  - [x] `assembleDebug`, `assembleRelease`, unit tests and lint pass locally; the release build tasks `verifySignedReleaseArtifacts` / `appendDigestToReleasedFiles` check the signed APK (one universal APK, no native code, every module file stored uncompressed).
  - [x] Install smoke test on G8441 with the debug APK of `bd8b6ef` and a host debug 5935 without the module files (host `tools/augment/device_plugin_modules_check.py`, 2026-10-24): not installed, enabled, disabled in the Plugin Center, axios / buffer switched off, uninstalled, 22 items each, the same in both states of the host module layer, PASS.
  - [ ] `requiresHostVersion` set to the published AutoJs6 6.8.0 build (see "Release steps").
  - [ ] Signed release, official index entry (engine `script-modules`), documentation site entry.

## Release steps (after AutoJs6 6.8.0 is final) / 发布步骤 (6.8.0 定版后)

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

Registered in the host roadmap appendix C: Q115 (the full settings-page specification for standalone plugins, recommended: this resource-only plugin keeps its simple switch page plus "Release history"), Q116 (the icon design), Q117 (default branch `master` instead of `main`). The recommended options are applied.

在宿主 roadmap 附录 C 登记: Q115 (独立插件设置页完整规范是否适用, 推荐: 本资源插件保持简单开关页加 "发行历史"), Q116 (图标设计), Q117 (默认分支用 `master` 而不是 `main`). 推荐项已先行.

- [ ] P4 After the release: AutoJs6 removes its copies of the files (host roadmap P5.2).
