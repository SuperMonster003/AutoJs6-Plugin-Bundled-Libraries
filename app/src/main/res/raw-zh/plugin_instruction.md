Bundled Libraries 收纳了 AutoJs6 过去内置的 13 个脚本库: axios, cheerio, dayjs, i18n, pangu, lodash, 以及 Node 兼容垫片 buffer, events, fs, os, path, process 和 nodejs. 自 AutoJs6 6.8.0 起, 这些库不再随 AutoJs6 安装包提供; 安装并启用本插件后, 脚本中的 `require('axios')`, `require('fs')` 等, 以及全局对象 `axios`, `cheerio`, `dayjs`, `i18n`, `pangu` 照常可用, 写法与以前相同.

插件本身只是一个资源包: AutoJs6 直接从插件的安装包读取库文件, 脚本运行时不会启动插件进程, 插件也不需要存储, 网络等权限 (axios 的网络请求由运行脚本的 AutoJs6 发出). 用不到的库可以在插件页面逐个关闭.

#### 库

| 库 | 模块名 | 全局对象 | 版本 | 许可证 |
|---|---|---|---|---|
| axios | `axios` | `axios` | - | MIT |
| cheerio | `cheerio` | `cheerio` | - | MIT |
| dayjs | `dayjs` | `dayjs` | - | MIT |
| i18n | `i18n`, `banana-i18n` | `i18n` | - | MIT |
| pangu | `pangu` | `pangu` | 10.1.0 | MIT |
| lodash | `lodash` | - | 4.17.15 | MIT |

#### Node 兼容垫片

| 库 | 模块名 | 全局对象 | 版本 | 许可证 |
|---|---|---|---|---|
| buffer | `buffer` | - | - | MPL-2.0 |
| events | `events` | - | - | MPL-2.0 |
| fs | `fs` | - | - | MPL-2.0 |
| os | `os` | - | - | MPL-2.0 |
| path | `path` | - | - | MPL-2.0 |
| process | `process` | - | - | MPL-2.0 |
| nodejs | `nodejs` | - | - | MPL-2.0 |

### 逐库开关

插件页面 (从 AutoJs6 插件中心或桌面上的 Bundled Libraries 图标打开) 为每个库提供一个开关, 默认全部开启:

- 关闭的库不能再被 `require`, 它的全局对象也不能使用; 重新打开即可恢复.
- 开关对之后的 `require` 生效: 正在运行的脚本已经加载的模块会保留到脚本结束.
- `fs` 依赖 `buffer`, `nodejs` 依赖 `process` 与 `buffer`: 关闭 `buffer` 或 `process` 后, 依赖它们的模块也无法加载.
- 页面底部的 "发行历史" 列出各版本的更新内容.

### 插件不可用时

插件未安装, 未授权, 在插件中心被停用, 版本不兼容, 或某个库被关闭时, `require` 该库或读取它的全局对象都会抛出错误:

- 错误是 `code` 为 `MODULE_NOT_FOUND` 的 `Error`, 消息会说明需要安装插件还是到哪里开启, 脚本可以用 `try...catch` 处理.
- 错误对象还带有 `library` (库名) 与 `reason` (原因: `not-installed`, `unauthorized`, `plugin-disabled`, `library-disabled` 或 `incompatible`).
- 插件状态的变化不会中断正在运行的脚本, 只影响之后的 `require`; AutoJs6 的其他内置模块与脚本自己的文件不受影响.

源码, 发行历史与第三方许可证见 [项目主页](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries).
