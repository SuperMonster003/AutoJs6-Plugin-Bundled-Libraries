Bundled Libraries 收納了 AutoJs6 過去內建的 13 個腳本函式庫: axios, cheerio, dayjs, i18n, pangu, lodash, 以及 Node 相容墊片 buffer, events, fs, os, path, process 和 nodejs. 自 AutoJs6 6.8.0 起, 這些函式庫不再隨 AutoJs6 安裝套件提供; 安裝並啟用本外掛後, 腳本中的 `require('axios')`, `require('fs')` 等, 以及全域物件 `axios`, `cheerio`, `dayjs`, `i18n`, `pangu` 照常可用, 寫法與以前相同.

外掛本身只是一個資源套件: AutoJs6 直接從外掛的安裝套件讀取函式庫檔案, 腳本執行時不會啟動外掛行程, 外掛也不需要儲存空間, 網路等權限 (axios 的網路請求由執行腳本的 AutoJs6 發出). 用不到的函式庫可以在外掛頁面逐一關閉.

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

### 逐一開關函式庫

外掛頁面 (從 AutoJs6 外掛中心或桌面上的 Bundled Libraries 圖示開啟) 為每個函式庫提供一個開關, 預設全部開啟:

- 關閉的函式庫不能再被 `require`, 它的全域物件也不能使用; 重新開啟即可恢復.
- 開關對之後的 `require` 生效: 正在執行的腳本已經載入的模組會保留到腳本結束.
- `fs` 相依於 `buffer`, `nodejs` 相依於 `process` 與 `buffer`: 關閉 `buffer` 或 `process` 後, 相依於它們的模組也無法載入.
- 頁面底部的 "發行記錄" 列出各版本的更新內容.

### 外掛無法使用時

外掛未安裝, 未授權, 在外掛中心被停用, 版本不相容, 或某個函式庫被關閉時, `require` 該函式庫或讀取它的全域物件都會擲出錯誤:

- 錯誤是 `code` 為 `MODULE_NOT_FOUND` 的 `Error`, 訊息會說明需要安裝外掛還是到哪裡開啟, 腳本可以用 `try...catch` 處理.
- 錯誤物件還帶有 `library` (函式庫名稱) 與 `reason` (原因: `not-installed`, `unauthorized`, `plugin-disabled`, `library-disabled` 或 `incompatible`).
- 外掛狀態的變化不會中斷正在執行的腳本, 只影響之後的 `require`; AutoJs6 的其他內建模組與腳本自己的檔案不受影響.

原始碼, 發行歷史與第三方授權見 [專案首頁](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries).
