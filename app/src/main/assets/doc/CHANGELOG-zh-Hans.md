******

### 发行历史

******

# v1.0.0

###### 2026/10/24

* `提示` 需要 AutoJs6 6.8.0 (5935) 或更高版本
* `提示` 自 AutoJs6 6.8.0 起, 下列库不再随 AutoJs6 提供: 安装本插件后 require 与全局对象照常可用, 原有脚本无需修改; 未安装或停用本插件时, require 这些库会得到说明如何安装或开启的错误
* `新增` axios, cheerio, dayjs, i18n (含 banana-i18n), pangu 与 lodash, 可通过 require 使用, 其中 axios, cheerio, dayjs, i18n 与 pangu 也可直接作为全局对象使用
* `新增` Node 兼容垫片 buffer, events, fs, os, path, process 与 nodejs, 可通过 require 使用
* `新增` 插件页面可逐个开关各库 (默认全部开启) 并查看发行历史, 关闭的库在之后的 require 中不可用
* `依赖` 附加 AutoJs6 Common Plugin API
