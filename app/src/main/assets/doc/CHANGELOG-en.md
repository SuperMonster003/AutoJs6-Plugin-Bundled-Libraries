******

### Release history

******

# v1.0.0

###### 2026/10/24

* `Hint` Requires AutoJs6 6.8.0 (5935) or later
* `Hint` Starting with AutoJs6 6.8.0, the libraries below no longer come with AutoJs6: with this plugin installed, require and the global objects work as before and existing scripts need no change; without the plugin, or with it disabled, require of these libraries gives an error that says how to install or enable it
* `Feature` axios, cheerio, dayjs, i18n (with banana-i18n), pangu and lodash for require, with axios, cheerio, dayjs, i18n and pangu also available as global objects
* `Feature` Node compatibility shims buffer, events, fs, os, path, process and nodejs for require
* `Feature` Plugin page with a switch per library (all on by default) and the release history; a library switched off is unavailable to later require calls
* `Feature` The plugin page follows the language, night mode and theme color of AutoJs6, and the system when AutoJs6 is not installed or its settings cannot be read
* `Dependency` Attach AutoJs6 Common Plugin API
