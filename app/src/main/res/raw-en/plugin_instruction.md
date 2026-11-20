Bundled Libraries provides 13 libraries for AutoJs6 scripts: JavaScript libraries such as axios, cheerio and dayjs, plus Node compatibility shims such as fs and path. Once it is enabled, `require('axios')` and the like, as well as global objects such as `dayjs`, work as usual, so existing scripts need no changes.

### Getting started

1. Make sure this plugin is enabled in the Plugin Center of AutoJs6.
2. In a script, load a library with `require`, or use the global objects `axios`, `cheerio`, `dayjs`, `i18n` and `pangu` directly; see the examples below.
3. Libraries you do not need can be switched off on the plugin page, which opens from the Plugin Center or the Bundled Libraries icon on the home screen.

### Main features

- **Libraries**: axios, cheerio, dayjs, i18n, lodash and pangu; all except lodash can also be used directly as global objects.
- **Node compatibility shims**: buffer, events, fs, os, path, process and nodejs, loaded with `require`.
- **Per-library switches**: the plugin page has one switch per library, all on by default; a library that is switched off cannot be loaded with `require`, and its global object cannot be used either.

### Using it in scripts

The example below loads lodash and path with `require` and uses the global objects dayjs and cheerio directly:

```js
var _ = require('lodash');
var path = require('path');

console.log(dayjs(0).valueOf());
console.log(cheerio.load('<p>AutoJs6</p>')('p').text());
console.log(_.map([1, 2], function (x) { return x * 2; }).join());
console.log(path.isAbsolute('/sdcard/Scripts'));
```

When the plugin is unavailable or a library is switched off, `require` throws an error, which you can handle like this:

```js
try {
    var axios = require('axios');
} catch (e) {
    console.log(e.code, e.library, e.reason);
}
```

- The error has `code` set to `MODULE_NOT_FOUND`, the library name in `library` and the cause in `reason`, for example `library-disabled` when the library is switched off.
- A switch only affects later `require` calls; modules that are already loaded are not affected.
- `fs` depends on `buffer`, and `nodejs` depends on `process` and `buffer`; when a library they depend on is switched off, they cannot be loaded either.
- This plugin serves the default JavaScript scripts of AutoJs6; scripts in the Node.js runtime plugin use the modules built into Node.js.

### Requirements

- Android 7.0 or later, and AutoJs6 6.8.0 or later.
- This plugin is only a resource package and needs no permissions; network requests made with axios are sent by AutoJs6, which runs the script.

### FAQ

**A script says this plugin is required**

Starting with AutoJs6 6.8.0, the AutoJs6 package no longer includes these libraries. Install this plugin, enable it in the Plugin Center, and check that the switch of the library is on in the plugin page.

**Existing scripts**

No changes are needed. Global objects such as `axios` and calls such as `require('axios')` keep working, and the libraries are loaded from this plugin.

The version, module names and license of each library are on the [project page](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries).
