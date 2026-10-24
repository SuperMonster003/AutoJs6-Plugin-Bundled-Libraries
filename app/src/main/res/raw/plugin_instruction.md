Bundled Libraries holds the 13 script libraries that used to be built into AutoJs6: axios, cheerio, dayjs, i18n, pangu and lodash, plus the Node compatibility shims buffer, events, fs, os, path, process and nodejs. Starting with AutoJs6 6.8.0, the AutoJs6 package no longer includes them; with this plugin installed and enabled, `require('axios')`, `require('fs')` and the like, as well as the global objects `axios`, `cheerio`, `dayjs`, `i18n` and `pangu`, keep working exactly as before.

The plugin is only a resource package: AutoJs6 reads the library files straight from the plugin package, scripts never start the plugin process, and the plugin needs no storage, network or other permission (the network requests of axios are made by AutoJs6, which runs the script). Libraries you do not need can be switched off one by one on the plugin page.

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

### Per-library switches

The plugin page (opened from the Plugin Center of AutoJs6 or the Bundled Libraries icon on the home screen) has one switch per library, all on by default:

- A library that is switched off can no longer be required and its global object cannot be used; switch it on again to restore it.
- A switch applies to the next `require`: modules that a running script has already loaded stay until the script ends.
- `fs` needs `buffer`, and `nodejs` needs `process` and `buffer`: with `buffer` or `process` switched off, the modules that need them cannot load either.
- "Release history" at the bottom of the page lists the changes of each version.

### When the plugin is unavailable

When the plugin is not installed, not authorized, disabled in the Plugin Center or incompatible, or a library is switched off, `require` of that library and reading its global object throw an error:

- The error is an `Error` with `code` `MODULE_NOT_FOUND`; its message says whether to install the plugin or where to switch it on, and scripts can handle it with `try...catch`.
- The error object also has `library` (the library name) and `reason` (`not-installed`, `unauthorized`, `plugin-disabled`, `library-disabled` or `incompatible`).
- A change of the plugin state never interrupts a running script, it only affects later `require` calls; the other built-in modules of AutoJs6 and the script's own files are not affected.

Source code, release history and third-party licenses are on the [project page](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries).
