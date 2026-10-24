******

### 發行歷史

******

# v1.0.0

###### 2026/10/24

* `提示` 需要 AutoJs6 6.8.0 (5935) 或更新版本
* `提示` 自 AutoJs6 6.8.0 起, 下列函式庫不再隨 AutoJs6 提供: 安裝本外掛後 require 與全域物件照常可用, 原有腳本無需修改; 未安裝或停用本外掛時, require 這些函式庫會得到說明如何安裝或開啟的錯誤
* `新增` axios, cheerio, dayjs, i18n (含 banana-i18n), pangu 與 lodash, 可透過 require 使用, 其中 axios, cheerio, dayjs, i18n 與 pangu 也可直接作為全域物件使用
* `新增` Node 相容墊片 buffer, events, fs, os, path, process 與 nodejs, 可透過 require 使用
* `新增` 外掛頁面可逐一開關各函式庫 (預設全部開啟) 並檢視發行記錄, 關閉的函式庫在之後的 require 中無法使用
* `相依性` 附加 AutoJs6 Common Plugin API
