Bundled Libraries 收納了 AutoJs6 過去內置的 13 個腳本庫: axios, cheerio, dayjs, i18n, pangu, lodash, 以及 Node 相容墊片 buffer, events, fs, os, path, process 和 nodejs. 自 AutoJs6 6.8.0 起, 這些庫不再隨 AutoJs6 安裝包提供; 安裝並啟用本插件後, 腳本中的 `require('axios')`, `require('fs')` 等, 以及全域物件 `axios`, `cheerio`, `dayjs`, `i18n`, `pangu` 照常可用, 寫法與以前相同.

插件本身只是一個資源包: AutoJs6 直接從插件的安裝包讀取庫檔案, 腳本運行時不會啟動插件進程, 插件也不需要儲存, 網絡等權限 (axios 的網絡請求由運行腳本的 AutoJs6 發出). 用不到的庫可以在插件頁面逐個關閉.

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

### 逐庫開關

插件頁面 (從 AutoJs6 插件中心或桌面上的 Bundled Libraries 圖示開啟) 為每個庫提供一個開關, 預設全部開啟:

- 關閉的庫不能再被 `require`, 它的全域物件也不能使用; 重新開啟即可恢復.
- 開關對之後的 `require` 生效: 正在運行的腳本已經載入的模組會保留到腳本結束.
- `fs` 依賴 `buffer`, `nodejs` 依賴 `process` 與 `buffer`: 關閉 `buffer` 或 `process` 後, 依賴它們的模組也無法載入.
- 頁面底部的 "發佈記錄" 列出各版本的更新內容.

### 插件不可用時

插件未安裝, 未授權, 在插件中心被停用, 版本不相容, 或某個庫被關閉時, `require` 該庫或讀取它的全域物件都會拋出錯誤:

- 錯誤是 `code` 為 `MODULE_NOT_FOUND` 的 `Error`, 訊息會說明需要安裝插件還是到哪裏開啟, 腳本可以用 `try...catch` 處理.
- 錯誤物件還帶有 `library` (庫名稱) 與 `reason` (原因: `not-installed`, `unauthorized`, `plugin-disabled`, `library-disabled` 或 `incompatible`).
- 插件狀態的變化不會中斷正在運行的腳本, 只影響之後的 `require`; AutoJs6 的其他內置模組與腳本自己的檔案不受影響.

源碼, 發行歷史與第三方授權見 [項目主頁](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries).
