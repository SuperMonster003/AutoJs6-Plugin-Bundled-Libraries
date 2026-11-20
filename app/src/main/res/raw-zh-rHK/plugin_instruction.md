Bundled Libraries 為 AutoJs6 腳本提供 13 個庫: axios, dayjs 等 JavaScript 庫, 以及 fs, path 等 Node 相容墊片. 啟用後 `require('axios')` 與 `dayjs` 等全域物件照常可用, 現有腳本無需修改.

### 開始使用

1. 確認 AutoJs6 插件中心裏本插件已啟用.
2. 在腳本中用 `require` 載入庫, 或直接使用全域物件 `axios`, `cheerio`, `dayjs`, `i18n` 與 `pangu`, 示例見下文.
3. 用不到的庫可以在插件頁面關閉; 插件頁面從插件中心或桌面上的 Bundled Libraries 圖示開啟.

### 主要功能

- **庫**: axios, cheerio, dayjs, i18n, lodash 與 pangu; 除 lodash 外都可以直接作為全域物件使用.
- **Node 相容墊片**: buffer, events, fs, os, path, process 與 nodejs, 用 `require` 載入.
- **逐庫開關**: 插件頁面為每個庫提供一個開關, 預設全部開啟; 關閉的庫不能被 `require`, 它的全域物件也不能使用.

### 在腳本中使用

以下示例用 `require` 載入 lodash 與 path, 並直接使用全域物件 dayjs 與 cheerio:

```js
var _ = require('lodash');
var path = require('path');

console.log(dayjs(0).valueOf());
console.log(cheerio.load('<p>AutoJs6</p>')('p').text());
console.log(_.map([1, 2], function (x) { return x * 2; }).join());
console.log(path.isAbsolute('/sdcard/Scripts'));
```

插件不可用或庫被關閉時, `require` 會拋出錯誤, 可以這樣處理:

```js
try {
    var axios = require('axios');
} catch (e) {
    console.log(e.code, e.library, e.reason);
}
```

- 錯誤的 `code` 為 `MODULE_NOT_FOUND`, `library` 為庫名, `reason` 為原因, 例如 `library-disabled` 表示庫被關閉.
- 開關只影響之後的 `require`, 已經載入的模組不受影響.
- `fs` 依賴 `buffer`, `nodejs` 依賴 `process` 與 `buffer`; 關閉被依賴的庫後, 依賴它的模組也無法載入.
- 本插件服務於 AutoJs6 預設的 JavaScript 腳本; Node.js 運行時插件中的腳本使用 Node.js 內置的模組.

### 運行要求

- Android 7.0 或更高版本, AutoJs6 6.8.0 或更高版本.
- 本插件只是資源包, 不需要任何權限; axios 的網絡請求由運行腳本的 AutoJs6 發出.

### 常見問題

**腳本提示需要本插件時的處理**

從 AutoJs6 6.8.0 起, 這些庫不再隨 AutoJs6 安裝包提供. 安裝並在插件中心啟用本插件, 並確認插件頁面中對應庫的開關已開啟.

**現有腳本的寫法**

無需改寫. `axios` 等全域物件與 `require('axios')` 等寫法都照常可用, 庫從本插件載入.

各庫的版本, 模組名稱與授權見 [項目主頁](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries).
