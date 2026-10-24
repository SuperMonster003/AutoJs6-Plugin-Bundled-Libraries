<!--suppress HtmlDeprecatedAttribute, HttpUrlsUsage -->

<div align="center">
  <p>
    <img src="https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/app/src/main/res/mipmap/ic_launcher.png?raw=true" alt="autojs6-plugin-bundled-libraries-ic-launcher" border="0" width="128" />
  </p>

  <p>供 AutoJs6 腳本 require 使用的 JavaScript 庫與 Node 相容墊片</p>

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
- 繁體中文 (香港) [zh-Hant-HK] # 目前
- [繁體中文 (台灣) [zh-Hant-TW]](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/.readme/README-zh-Hant-TW.md)
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

Bundled Libraries 收納了 AutoJs6 過去內置的 13 個腳本庫: axios, cheerio, dayjs, i18n, pangu, lodash, 以及 Node 相容墊片 buffer, events, fs, os, path, process 和 nodejs. 自 AutoJs6 6.8.0 起, 這些庫不再隨 AutoJs6 安裝包提供; 安裝並啟用本插件後, 腳本中的 `require('axios')`, `require('fs')` 等, 以及全域物件 `axios`, `cheerio`, `dayjs`, `i18n`, `pangu` 照常可用, 寫法與以前相同.

插件本身只是一個資源包: AutoJs6 直接從插件的安裝包讀取庫檔案, 腳本運行時不會啟動插件進程, 插件也不需要儲存, 網絡等權限 (axios 的網絡請求由運行腳本的 AutoJs6 發出). 用不到的庫可以在插件頁面逐個關閉.

******

### 收錄的庫

******

模組名稱就是 `require` 的參數; 有全域物件的庫也可以不寫 `require` 直接使用. 各庫的來源與授權詳見 [THIRD_PARTY_NOTICES.md](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/THIRD_PARTY_NOTICES.md).

#### 庫

| 庫 | 模組名稱 | 全域物件 | 版本 | 授權 |
|---|---|---|---|---|
| axios | `axios` | `axios` | - | MIT |
| cheerio | `cheerio` | `cheerio` | - | MIT |
| dayjs | `dayjs` | `dayjs` | - | MIT |
| i18n | `i18n`, `banana-i18n` | `i18n` | - | MIT |
| pangu | `pangu` | `pangu` | 10.1.0 | MIT |
| lodash | `lodash` | - | 4.17.15 | MIT |

#### Node 相容墊片

| 庫 | 模組名稱 | 全域物件 | 版本 | 授權 |
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
2. 在 AutoJs6 的插件中心安裝 Bundled Libraries (也可以從 [Releases](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/releases) 下載 APK 安裝), 並確認插件處於啟用狀態.
3. 在腳本中照常 `require` 需要的庫, 或直接使用全域物件, 例如:

```javascript
var _ = require('lodash');
var path = require('path');

console.log(dayjs(0).valueOf());
console.log(cheerio.load('<p>AutoJs6</p>')('p').text());
console.log(_.map([1, 2], function (x) { return x * 2; }).join());
console.log(path.isAbsolute('/sdcard/Scripts'));
```

> 插件只有一個通用安裝包, 不含原生庫, 支援 Android 7.0 (API 24) 及以上的裝置.

******

### 逐庫開關

******

插件頁面 (從 AutoJs6 插件中心或桌面上的 Bundled Libraries 圖示開啟) 為每個庫提供一個開關, 預設全部開啟:

- 關閉的庫不能再被 `require`, 它的全域物件也不能使用; 重新開啟即可恢復.
- 開關對之後的 `require` 生效: 正在運行的腳本已經載入的模組會保留到腳本結束.
- `fs` 依賴 `buffer`, `nodejs` 依賴 `process` 與 `buffer`: 關閉 `buffer` 或 `process` 後, 依賴它們的模組也無法載入.
- 頁面底部的 "發佈記錄" 列出各版本的更新內容.

******

### 插件不可用時

******

插件未安裝, 未授權, 在插件中心被停用, 版本不相容, 或某個庫被關閉時, `require` 該庫或讀取它的全域物件都會拋出錯誤:

- 錯誤是 `code` 為 `MODULE_NOT_FOUND` 的 `Error`, 訊息會說明需要安裝插件還是到哪裏開啟, 腳本可以用 `try...catch` 處理.
- 錯誤物件還帶有 `library` (庫名稱) 與 `reason` (原因: `not-installed`, `unauthorized`, `plugin-disabled`, `library-disabled` 或 `incompatible`).
- 插件狀態的變化不會中斷正在運行的腳本, 只影響之後的 `require`; AutoJs6 的其他內置模組與腳本自己的檔案不受影響.

******

### 相容性

******

- 需要 AutoJs6 6.8.0 (構建 5935) 或更高版本; 更早的 AutoJs6 自帶這些庫, 不需要本插件.
- 支援 Android 7.0 (API 24) 及以上, 一個安裝包適用於所有處理器架構.
- 庫檔案與 AutoJs6 過去內置的完全相同, 原有腳本無需修改.
- 本插件服務於 AutoJs6 預設的 JavaScript 腳本; Node.js 運行時插件中的腳本使用 Node.js 自帶的模組.

******

### 常見問題

******

#### 為甚麼 AutoJs6 不再內置這些庫?

這些庫只有一部分腳本會用到. 移到插件後 AutoJs6 安裝包更小, 需要的人安裝插件即可, 還可以只開啟用得到的庫. 插件裏的檔案與原先內置的相同.

#### 腳本裏直接用全域物件 `axios`, 需要改成 `require` 嗎?

不需要. 全域物件 `axios`, `cheerio`, `dayjs`, `i18n`, `pangu` 與 `require` 得到的是同一個模組, 都從本插件載入.

#### 插件會連網或在背景運行嗎?

不會. 插件沒有網絡權限, 腳本運行時也不會啟動插件進程. axios 的網絡請求由運行腳本的 AutoJs6 發出.

#### 錯誤提示需要授權插件怎麼辦?

從 AutoJs6 插件中心或本項目 Releases 安裝的正式版無需授權. 自行構建的版本簽名不同, 需要先在插件中心按提示授權.

******

### 插件接口

******

以下資訊面向 AutoJs6 宿主與插件開發者, 宿主透過這些標識發現插件並讀取庫檔案:

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

發佈前清單與發佈步驟記錄在 ROADMAP.md 中; 相關決策位於 AutoJs6 倉庫的 `docs/augment/ROADMAP.md`.

- [查看 ROADMAP.md](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/ROADMAP.md)

******

### 發行歷史

******

#### v1.0.0

_2026/10/24_

- `提示` 需要 AutoJs6 6.8.0 (5935) 或更高版本
- `提示` 自 AutoJs6 6.8.0 起, 下列庫不再隨 AutoJs6 提供: 安裝本插件後 require 與全域物件照常可用, 原有腳本無需修改; 未安裝或停用本插件時, require 這些庫會得到說明如何安裝或開啟的錯誤
- `新增` axios, cheerio, dayjs, i18n (含 banana-i18n), pangu 與 lodash, 可透過 require 使用, 其中 axios, cheerio, dayjs, i18n 與 pangu 也可直接作為全域物件使用
- `新增` Node 相容墊片 buffer, events, fs, os, path, process 與 nodejs, 可透過 require 使用
- `新增` 插件頁面可逐個開關各庫 (預設全部開啟) 並查看發佈記錄, 關閉的庫在之後的 require 中不可用
- `新增` 插件頁面跟隨 AutoJs6 的語言, 夜間模式與主題色, 未安裝 AutoJs6 或無法讀取其設定時跟隨系統
- `依賴` 附加 AutoJs6 Common Plugin API

##### 更多發行歷史可參閱

* [CHANGELOG.md](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/app/src/main/assets/doc/CHANGELOG-zh-Hant-HK.md)

******

### 構建

******

本節面向希望從源碼構建插件的開發者.

```powershell
.\gradlew.bat :app:assembleDebug :app:testDebugUnitTest
py .python\generate_markdown.py --check
py .python\generate_icon_studio.py --check
py .python\check_repository.py
```

******

### 許可

******

插件代碼與 AutoJs6 編寫的模組 (Node 相容墊片, i18n 封裝與 axios 適配層) 使用 [Mozilla Public License 2.0](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/LICENSE). 收納的第三方庫保留各自的授權 (MIT 等), 詳見 [THIRD_PARTY_NOTICES.md](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/THIRD_PARTY_NOTICES.md).

******

### 相關連結

******

- AutoJs6 項目: https://github.com/SuperMonster003/AutoJs6
- 第三方聲明: https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/THIRD_PARTY_NOTICES.md
