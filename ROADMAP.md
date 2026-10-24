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
- [ ] P3 Before the first release:
  - [ ] Remote repository (`SuperMonster003/AutoJs6-Plugin-Bundled-Libraries`) and push authorization.
  - [ ] Icon Studio icon (the launcher icon is a placeholder vector), 10-language `.changelog` / `.readme` sources with the generator used by the other official plugins, CI workflow, `check_repository` contract.
  - [ ] `requiresHostVersion` set to the published AutoJs6 build with plugin script modules.
  - [ ] Signed release, official index entry (engine `script-modules`), documentation site entry.
- [ ] P4 After the release: AutoJs6 removes its copies of the files (host roadmap P5.2).
