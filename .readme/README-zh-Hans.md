<!--suppress HtmlDeprecatedAttribute, HttpUrlsUsage -->

<div align="center">
  <p>
    <img src="https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/app/src/main/res/mipmap/ic_launcher.png?raw=true" alt="autojs6-plugin-bundled-libraries-ic-launcher" border="0" width="128" />
  </p>

  <p>供 AutoJs6 脚本 require 使用的 JavaScript 库与 Node 兼容垫片</p>

  <p>
    <a href="https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/releases"><img alt="GitHub release (latest by date)" src="https://img.shields.io/github/v/release/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries?label=Release"/></a>
    <a href="https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/issues"><img alt="GitHub closed issues" src="https://img.shields.io/github/issues/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries?color=A24232&label=Issues"/></a>
    <a href="https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/LICENSE"><img alt="GitHub License" src="https://img.shields.io/github/license/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries?color=534BAE&label=License"/></a>
  </p>
</div>

******

### 语言 (Languages)

******

当前 README.md 支持以下语言:

- 简体中文 [zh-Hans] # 当前
- [繁體中文 (香港) [zh-Hant-HK]](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/.readme/README-zh-Hant-HK.md)
- [繁體中文 (台灣) [zh-Hant-TW]](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/.readme/README-zh-Hant-TW.md)
- [English [en]](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/.readme/README-en.md)
- [Français [fr]](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/.readme/README-fr.md)
- [Español [es]](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/.readme/README-es.md)
- [日本語 [ja]](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/.readme/README-ja.md)
- [한국어 [ko]](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/.readme/README-ko.md)
- [Русский [ru]](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/.readme/README-ru.md)
- [العربية [ar]](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/.readme/README-ar.md)

******

### 简介

******

Bundled Libraries 收纳了 AutoJs6 过去内置的 13 个脚本库: axios, cheerio, dayjs, i18n, pangu, lodash, 以及 Node 兼容垫片 buffer, events, fs, os, path, process 和 nodejs. 自 AutoJs6 6.8.0 起, 这些库不再随 AutoJs6 安装包提供; 安装并启用本插件后, 脚本中的 `require('axios')`, `require('fs')` 等, 以及全局对象 `axios`, `cheerio`, `dayjs`, `i18n`, `pangu` 照常可用, 写法与以前相同.

插件本身只是一个资源包: AutoJs6 直接从插件的安装包读取库文件, 脚本运行时不会启动插件进程, 插件也不需要存储, 网络等权限 (axios 的网络请求由运行脚本的 AutoJs6 发出). 用不到的库可以在插件页面逐个关闭.

******

### 收录的库

******

模块名就是 `require` 的参数; 有全局对象的库也可以不写 `require` 直接使用. 各库的来源与许可证详见 [THIRD_PARTY_NOTICES.md](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/THIRD_PARTY_NOTICES.md).

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

******

### 使用方法

******

1. 使用 AutoJs6 6.8.0 (5935) 或更高版本.
2. 在 AutoJs6 的插件中心安装 Bundled Libraries (也可以从 [Releases](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/releases) 下载 APK 安装), 并确认插件处于启用状态.
3. 在脚本中照常 `require` 需要的库, 或直接使用全局对象, 例如:

```javascript
var _ = require('lodash');
var path = require('path');

console.log(dayjs(0).valueOf());
console.log(cheerio.load('<p>AutoJs6</p>')('p').text());
console.log(_.map([1, 2], function (x) { return x * 2; }).join());
console.log(path.isAbsolute('/sdcard/Scripts'));
```

> 插件只有一个通用安装包, 不含原生库, 支持 Android 7.0 (API 24) 及以上的设备.

******

### 逐库开关

******

插件页面 (从 AutoJs6 插件中心或桌面上的 Bundled Libraries 图标打开) 为每个库提供一个开关, 默认全部开启:

- 关闭的库不能再被 `require`, 它的全局对象也不能使用; 重新打开即可恢复.
- 开关对之后的 `require` 生效: 正在运行的脚本已经加载的模块会保留到脚本结束.
- `fs` 依赖 `buffer`, `nodejs` 依赖 `process` 与 `buffer`: 关闭 `buffer` 或 `process` 后, 依赖它们的模块也无法加载.
- 页面底部的 "发行历史" 列出各版本的更新内容.

******

### 插件不可用时

******

插件未安装, 未授权, 在插件中心被停用, 版本不兼容, 或某个库被关闭时, `require` 该库或读取它的全局对象都会抛出错误:

- 错误是 `code` 为 `MODULE_NOT_FOUND` 的 `Error`, 消息会说明需要安装插件还是到哪里开启, 脚本可以用 `try...catch` 处理.
- 错误对象还带有 `library` (库名) 与 `reason` (原因: `not-installed`, `unauthorized`, `plugin-disabled`, `library-disabled` 或 `incompatible`).
- 插件状态的变化不会中断正在运行的脚本, 只影响之后的 `require`; AutoJs6 的其他内置模块与脚本自己的文件不受影响.

******

### 兼容性

******

- 需要 AutoJs6 6.8.0 (构建 5935) 或更高版本; 更早的 AutoJs6 自带这些库, 不需要本插件.
- 支持 Android 7.0 (API 24) 及以上, 一个安装包适用于所有处理器架构.
- 库文件与 AutoJs6 过去内置的完全相同, 原有脚本无需修改.
- 本插件服务于 AutoJs6 默认的 JavaScript 脚本; Node.js 运行时插件中的脚本使用 Node.js 自带的模块.

******

### 常见问题

******

#### 为什么 AutoJs6 不再内置这些库?

这些库只有一部分脚本会用到. 移到插件后 AutoJs6 安装包更小, 需要的人安装插件即可, 还可以只开启用得到的库. 插件里的文件与原先内置的相同.

#### 脚本里直接用全局对象 `axios`, 需要改成 `require` 吗?

不需要. 全局对象 `axios`, `cheerio`, `dayjs`, `i18n`, `pangu` 与 `require` 得到的是同一个模块, 都从本插件加载.

#### 插件会联网或在后台运行吗?

不会. 插件没有网络权限, 脚本运行时也不会启动插件进程. axios 的网络请求由运行脚本的 AutoJs6 发出.

#### 错误提示需要授权插件怎么办?

从 AutoJs6 插件中心或本项目 Releases 安装的正式版无需授权. 自行构建的版本签名不同, 需要先在插件中心按提示授权.

******

### 插件接口

******

以下信息面向 AutoJs6 宿主与插件开发者, 宿主通过这些标识发现插件并读取库文件:

```text
application id: io.github.supermonster003.autojs6.plugin.bundled.libraries
plugin id: bundled-libraries
engine: script-modules
info service: org.autojs.plugin.INFO (category script-modules)
catalog: meta-data org.autojs.plugin.scriptModules.CATALOG -> assets/script-modules.json (schema 1)
library switch: activity-alias .library.<id>, action org.autojs.plugin.SCRIPT_MODULE_LIBRARY
wake action: org.autojs.plugin.action.WAKE
minimum host build: 5935
native library: none (all ABIs)
```

******

### 开发路线图

******

发布前清单与发布步骤记录在 ROADMAP.md 中; 相关决策位于 AutoJs6 仓库的 `docs/augment/ROADMAP.md`.

- [查看 ROADMAP.md](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/ROADMAP.md)

******

### 发行历史

******

#### v1.0.0

_2026/10/24_

- `提示` 需要 AutoJs6 6.8.0 (5935) 或更高版本
- `提示` 自 AutoJs6 6.8.0 起, 下列库不再随 AutoJs6 提供: 安装本插件后 require 与全局对象照常可用, 原有脚本无需修改; 未安装或停用本插件时, require 这些库会得到说明如何安装或开启的错误
- `新增` axios, cheerio, dayjs, i18n (含 banana-i18n), pangu 与 lodash, 可通过 require 使用, 其中 axios, cheerio, dayjs, i18n 与 pangu 也可直接作为全局对象使用
- `新增` Node 兼容垫片 buffer, events, fs, os, path, process 与 nodejs, 可通过 require 使用
- `新增` 插件页面可逐个开关各库 (默认全部开启) 并查看发行历史, 关闭的库在之后的 require 中不可用
- `依赖` 附加 AutoJs6 Common Plugin API

##### 更多发行历史可参阅

* [CHANGELOG.md](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/app/src/main/assets/doc/CHANGELOG-zh-Hans.md)

******

### 构建

******

本节面向希望从源码构建插件的开发者.

```powershell
.\gradlew.bat :app:assembleDebug :app:testDebugUnitTest
py .python\generate_markdown.py --check
py .python\generate_icon_studio.py --check
py .python\check_repository.py
```

******

### 许可

******

插件代码与 AutoJs6 编写的模块 (Node 兼容垫片, i18n 封装与 axios 适配层) 使用 [Mozilla Public License 2.0](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/LICENSE). 收纳的第三方库保留各自的许可证 (MIT 等), 详见 [THIRD_PARTY_NOTICES.md](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/THIRD_PARTY_NOTICES.md).

******

### 相关链接

******

- AutoJs6 项目: https://github.com/SuperMonster003/AutoJs6
- 第三方声明: https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/THIRD_PARTY_NOTICES.md
