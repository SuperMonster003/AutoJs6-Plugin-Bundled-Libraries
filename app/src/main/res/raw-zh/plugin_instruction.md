Bundled Libraries 为 AutoJs6 脚本提供 13 个库: axios, dayjs 等 JavaScript 库, 以及 fs, path 等 Node 兼容垫片. 启用后 `require('axios')` 与 `dayjs` 等全局对象照常可用, 已有脚本无需修改.

### 开始使用

1. 确认 AutoJs6 插件中心里本插件已启用.
2. 在脚本中用 `require` 加载库, 或直接使用全局对象 `axios`, `cheerio`, `dayjs`, `i18n` 与 `pangu`, 示例见下文.
3. 用不到的库可以在插件页面关闭; 插件页面从插件中心或桌面上的 Bundled Libraries 图标打开.

### 主要功能

- **库**: axios, cheerio, dayjs, i18n, lodash 与 pangu; 除 lodash 外都可以直接作为全局对象使用.
- **Node 兼容垫片**: buffer, events, fs, os, path, process 与 nodejs, 用 `require` 加载.
- **逐库开关**: 插件页面为每个库提供一个开关, 默认全部开启; 关闭的库不能被 `require`, 它的全局对象也不能使用.

### 在脚本中使用

下面的示例用 `require` 加载 lodash 与 path, 并直接使用全局对象 dayjs 与 cheerio:

```js
var _ = require('lodash');
var path = require('path');

console.log(dayjs(0).valueOf());
console.log(cheerio.load('<p>AutoJs6</p>')('p').text());
console.log(_.map([1, 2], function (x) { return x * 2; }).join());
console.log(path.isAbsolute('/sdcard/Scripts'));
```

插件不可用或库被关闭时, `require` 会抛出错误, 可以这样处理:

```js
try {
    var axios = require('axios');
} catch (e) {
    console.log(e.code, e.library, e.reason);
}
```

- 错误的 `code` 为 `MODULE_NOT_FOUND`, `library` 为库名, `reason` 为原因, 例如 `library-disabled` 表示库被关闭.
- 开关只影响之后的 `require`, 已经加载的模块不受影响.
- `fs` 依赖 `buffer`, `nodejs` 依赖 `process` 与 `buffer`; 关闭被依赖的库后, 依赖它的模块也无法加载.
- 本插件服务于 AutoJs6 默认的 JavaScript 脚本; Node.js 运行时插件中的脚本使用 Node.js 自带的模块.

### 运行要求

- Android 7.0 或更高版本, AutoJs6 6.8.0 或更高版本.
- 本插件只是资源包, 不需要任何权限; axios 的网络请求由运行脚本的 AutoJs6 发出.

### 常见问题

**脚本提示需要本插件时的处理**

从 AutoJs6 6.8.0 起, 这些库不再随 AutoJs6 安装包提供. 安装并在插件中心启用本插件, 并确认插件页面中对应库的开关已开启.

**已有脚本的写法**

无需改写. `axios` 等全局对象与 `require('axios')` 等写法都照常可用, 库从本插件加载.

各库的版本, 模块名与许可证见 [项目主页](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries).
