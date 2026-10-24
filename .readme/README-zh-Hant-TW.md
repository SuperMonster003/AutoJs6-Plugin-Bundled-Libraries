<!--suppress HtmlDeprecatedAttribute, HttpUrlsUsage -->

<div align="center">
  <p>
    <img src="https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/app/src/main/res/mipmap/ic_launcher.png?raw=true" alt="autojs6-plugin-bundled-libraries-ic-launcher" border="0" width="128" />
  </p>

  <p>供 AutoJs6 腳本 require 使用的 JavaScript 函式庫與 Node 相容墊片</p>

  <p>
    <a href="https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/releases"><img alt="GitHub release (latest by date)" src="https://img.shields.io/github/v/release/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries?label=Release"/></a>
    <a href="https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/issues"><img alt="GitHub closed issues" src="https://img.shields.io/github/issues/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries?color=A24232&label=Issues"/></a>
    <a href="https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/LICENSE"><img alt="GitHub License" src="https://img.shields.io/github/license/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries?color=534BAE&label=License"/></a>
  </p>
</div>

******

### 語言 (Languages)

******

目前 README.md 支援以下語言:

- [简体中文 [zh-Hans]](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/.readme/README-zh-Hans.md)
- [繁體中文 (香港) [zh-Hant-HK]](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/.readme/README-zh-Hant-HK.md)
- 繁體中文 (台灣) [zh-Hant-TW] # 目前
- [English [en]](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/.readme/README-en.md)
- [Français [fr]](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/.readme/README-fr.md)
- [Español [es]](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/.readme/README-es.md)
- [日本語 [ja]](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/.readme/README-ja.md)
- [한국어 [ko]](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/.readme/README-ko.md)
- [Русский [ru]](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/.readme/README-ru.md)
- [العربية [ar]](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/.readme/README-ar.md)

******

### 簡介

******

Bundled Libraries 收納了 AutoJs6 過去內建的 13 個腳本函式庫: axios, cheerio, dayjs, i18n, pangu, lodash, 以及 Node 相容墊片 buffer, events, fs, os, path, process 和 nodejs. 自 AutoJs6 6.8.0 起, 這些函式庫不再隨 AutoJs6 安裝套件提供; 安裝並啟用本外掛後, 腳本中的 `require('axios')`, `require('fs')` 等, 以及全域物件 `axios`, `cheerio`, `dayjs`, `i18n`, `pangu` 照常可用, 寫法與以前相同.

外掛本身只是一個資源套件: AutoJs6 直接從外掛的安裝套件讀取函式庫檔案, 腳本執行時不會啟動外掛行程, 外掛也不需要儲存空間, 網路等權限 (axios 的網路請求由執行腳本的 AutoJs6 發出). 用不到的函式庫可以在外掛頁面逐一關閉.

******

### 收錄的函式庫

******

模組名稱就是 `require` 的參數; 有全域物件的函式庫也可以不寫 `require` 直接使用. 各函式庫的來源與授權詳見 [THIRD_PARTY_NOTICES.md](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/THIRD_PARTY_NOTICES.md).

#### 函式庫

| 函式庫 | 模組名稱 | 全域物件 | 版本 | 授權 |
|---|---|---|---|---|
| axios | `axios` | `axios` | - | MIT |
| cheerio | `cheerio` | `cheerio` | - | MIT |
| dayjs | `dayjs` | `dayjs` | - | MIT |
| i18n | `i18n`, `banana-i18n` | `i18n` | - | MIT |
| pangu | `pangu` | `pangu` | 10.1.0 | MIT |
| lodash | `lodash` | - | 4.17.15 | MIT |

#### Node 相容墊片

| 函式庫 | 模組名稱 | 全域物件 | 版本 | 授權 |
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

1. 使用 AutoJs6 6.8.0 (5935) 或更新版本.
2. 在 AutoJs6 的外掛中心安裝 Bundled Libraries (也可以從 [Releases](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/releases) 下載 APK 安裝), 並確認外掛處於啟用狀態.
3. 在腳本中照常 `require` 需要的函式庫, 或直接使用全域物件, 例如:

```javascript
var _ = require('lodash');
var path = require('path');

console.log(dayjs(0).valueOf());
console.log(cheerio.load('<p>AutoJs6</p>')('p').text());
console.log(_.map([1, 2], function (x) { return x * 2; }).join());
console.log(path.isAbsolute('/sdcard/Scripts'));
```

> 外掛只有一個通用安裝套件, 不含原生函式庫, 支援 Android 7.0 (API 24) 以上的裝置.

******

### 逐一開關函式庫

******

外掛頁面 (從 AutoJs6 外掛中心或桌面上的 Bundled Libraries 圖示開啟) 為每個函式庫提供一個開關, 預設全部開啟:

- 關閉的函式庫不能再被 `require`, 它的全域物件也不能使用; 重新開啟即可恢復.
- 開關對之後的 `require` 生效: 正在執行的腳本已經載入的模組會保留到腳本結束.
- `fs` 相依於 `buffer`, `nodejs` 相依於 `process` 與 `buffer`: 關閉 `buffer` 或 `process` 後, 相依於它們的模組也無法載入.
- 頁面底部的 "發行記錄" 列出各版本的更新內容.

******

### 外掛無法使用時

******

外掛未安裝, 未授權, 在外掛中心被停用, 版本不相容, 或某個函式庫被關閉時, `require` 該函式庫或讀取它的全域物件都會擲出錯誤:

- 錯誤是 `code` 為 `MODULE_NOT_FOUND` 的 `Error`, 訊息會說明需要安裝外掛還是到哪裡開啟, 腳本可以用 `try...catch` 處理.
- 錯誤物件還帶有 `library` (函式庫名稱) 與 `reason` (原因: `not-installed`, `unauthorized`, `plugin-disabled`, `library-disabled` 或 `incompatible`).
- 外掛狀態的變化不會中斷正在執行的腳本, 只影響之後的 `require`; AutoJs6 的其他內建模組與腳本自己的檔案不受影響.

******

### 相容性

******

- 需要 AutoJs6 6.8.0 (建置 5935) 或更新版本; 更早的 AutoJs6 自帶這些函式庫, 不需要本外掛.
- 支援 Android 7.0 (API 24) 以上, 一個安裝套件適用於所有處理器架構.
- 函式庫檔案與 AutoJs6 過去內建的完全相同, 原有腳本無需修改.
- 本外掛服務於 AutoJs6 預設的 JavaScript 腳本; Node.js 執行環境外掛中的腳本使用 Node.js 內建的模組.

******

### 常見問題

******

#### 為什麼 AutoJs6 不再內建這些函式庫?

這些函式庫只有一部分腳本會用到. 移到外掛後 AutoJs6 安裝套件更小, 需要的人安裝外掛即可, 還可以只開啟用得到的函式庫. 外掛裡的檔案與原先內建的相同.

#### 腳本裡直接用全域物件 `axios`, 需要改成 `require` 嗎?

不需要. 全域物件 `axios`, `cheerio`, `dayjs`, `i18n`, `pangu` 與 `require` 得到的是同一個模組, 都從本外掛載入.

#### 外掛會連網或在背景執行嗎?

不會. 外掛沒有網路權限, 腳本執行時也不會啟動外掛行程. axios 的網路請求由執行腳本的 AutoJs6 發出.

#### 錯誤提示需要授權外掛怎麼辦?

從 AutoJs6 外掛中心或本專案 Releases 安裝的正式版無需授權. 自行建置的版本簽章不同, 需要先在外掛中心依提示授權.

******

### 外掛介面

******

以下資訊面向 AutoJs6 主程式與外掛開發者, 主程式透過這些識別碼發現外掛並讀取函式庫檔案:

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

### 開發路線圖

******

發行前清單與發行步驟記錄在 ROADMAP.md 中; 相關決策位於 AutoJs6 儲存庫的 `docs/augment/ROADMAP.md`.

- [檢視 ROADMAP.md](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/ROADMAP.md)

******

### 發行歷史

******

#### v1.0.0

_2026/10/24_

- `提示` 需要 AutoJs6 6.8.0 (5935) 或更新版本
- `提示` 自 AutoJs6 6.8.0 起, 下列函式庫不再隨 AutoJs6 提供: 安裝本外掛後 require 與全域物件照常可用, 原有腳本無需修改; 未安裝或停用本外掛時, require 這些函式庫會得到說明如何安裝或開啟的錯誤
- `新增` axios, cheerio, dayjs, i18n (含 banana-i18n), pangu 與 lodash, 可透過 require 使用, 其中 axios, cheerio, dayjs, i18n 與 pangu 也可直接作為全域物件使用
- `新增` Node 相容墊片 buffer, events, fs, os, path, process 與 nodejs, 可透過 require 使用
- `新增` 外掛頁面可逐一開關各函式庫 (預設全部開啟) 並檢視發行記錄, 關閉的函式庫在之後的 require 中無法使用
- `新增` 外掛頁面跟隨 AutoJs6 的語言, 夜間模式與主題色, 未安裝 AutoJs6 或無法讀取其設定時跟隨系統
- `相依性` 附加 AutoJs6 Common Plugin API

##### 更多發行歷史可參閱

* [CHANGELOG.md](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/app/src/main/assets/doc/CHANGELOG-zh-Hant-TW.md)

******

### 建置

******

本節面向希望從原始碼建置外掛的開發者.

```powershell
.\gradlew.bat :app:assembleDebug :app:testDebugUnitTest
py .python\generate_markdown.py --check
py .python\generate_icon_studio.py --check
py .python\check_repository.py
```

******

### 授權

******

外掛程式碼與 AutoJs6 撰寫的模組 (Node 相容墊片, i18n 封裝與 axios 轉接層) 採用 [Mozilla Public License 2.0](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/LICENSE). 收納的第三方函式庫保留各自的授權 (MIT 等), 詳見 [THIRD_PARTY_NOTICES.md](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/THIRD_PARTY_NOTICES.md).

******

### 相關連結

******

- AutoJs6 專案: https://github.com/SuperMonster003/AutoJs6
- 第三方聲明: https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/THIRD_PARTY_NOTICES.md
