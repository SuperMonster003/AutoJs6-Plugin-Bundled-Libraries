******

### Release history

******

# v1.0.1

###### 2026/11/20

* `Improvement` The release history page now expands and collapses by version: a collapsed version shows its date and the number of entries per category, an expanded one names each category once; all versions can be expanded or collapsed at once, the newest one opens expanded and the subtitle shows the plugin name
* `Improvement` Rewrote the instructions shown in Plugin Center in five parts: getting started, main features, using it in scripts, requirements and FAQ
* `Dependency` Added Jetpack Compose BOM 2026.09.00 (release history page)
* `Dependency` Upgraded shared build plugins version 1.9.0 -> 1.9.7

# v1.0.0

###### 2026/10/29

* `Hint` Requires AutoJs6 6.8.0 (build 5935) or later
* `Hint` Starting with AutoJs6 6.8.0, the libraries below no longer come with AutoJs6: with this plugin installed, require and the global objects work as before and existing scripts need no change; without the plugin, or with it disabled, require of these libraries gives an error that says how to install or enable it
* `Feature` axios, cheerio, dayjs, i18n (with banana-i18n), pangu and lodash for require, with axios, cheerio, dayjs, i18n and pangu also available as global objects
* `Feature` Node compatibility shims buffer, events, fs, os, path, process and nodejs for require
* `Feature` Plugin page with a switch per library (all on by default) and the release history; a library switched off is unavailable to later require calls
* `Feature` The plugin page follows the language, night mode and theme color of AutoJs6, and the system when AutoJs6 is not installed or its settings cannot be read
* `Dependency` Attach AutoJs6 Common Plugin API
