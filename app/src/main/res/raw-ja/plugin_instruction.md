Bundled Libraries は AutoJs6 のスクリプトに 13 個のライブラリを提供します: axios, cheerio, dayjs などの JavaScript ライブラリと, fs, path などの Node 互換シムです. 有効にすると `require('axios')` などの書き方と `dayjs` などのグローバルオブジェクトがこれまでどおり使え, 既存のスクリプトを変更する必要はありません.

### はじめに

1. AutoJs6 のプラグインセンターでこのプラグインが有効になっていることを確認します.
2. スクリプトで `require` を使ってライブラリを読み込むか, グローバルオブジェクト `axios`, `cheerio`, `dayjs`, `i18n`, `pangu` を直接使います. 例は下記を参照してください.
3. 使わないライブラリはプラグインのページでオフにできます. このページはプラグインセンター, またはホーム画面の Bundled Libraries アイコンから開きます.

### 主な機能

- **ライブラリ**: axios, cheerio, dayjs, i18n, lodash, pangu. lodash 以外はグローバルオブジェクトとして直接使うこともできます.
- **Node 互換シム**: buffer, events, fs, os, path, process, nodejs. `require` で読み込みます.
- **ライブラリごとのスイッチ**: プラグインのページにライブラリごとのスイッチがあり, 既定ではすべてオンです. オフにしたライブラリは `require` できず, そのグローバルオブジェクトも使えません.

### スクリプトでの使い方

次の例では `require` で lodash と path を読み込み, グローバルオブジェクト dayjs と cheerio を直接使います:

```js
var _ = require('lodash');
var path = require('path');

console.log(dayjs(0).valueOf());
console.log(cheerio.load('<p>AutoJs6</p>')('p').text());
console.log(_.map([1, 2], function (x) { return x * 2; }).join());
console.log(path.isAbsolute('/sdcard/Scripts'));
```

プラグインが使えないときやライブラリがオフのとき, `require` はエラーをスローします. 次のように処理できます:

```js
try {
    var axios = require('axios');
} catch (e) {
    console.log(e.code, e.library, e.reason);
}
```

- エラーの `code` は `MODULE_NOT_FOUND` で, `library` にライブラリ名, `reason` に原因が入ります. 例えばライブラリがオフのときは `library-disabled` です.
- スイッチが影響するのはその後の `require` だけで, 読み込み済みのモジュールには影響しません.
- `fs` は `buffer` に, `nodejs` は `process` と `buffer` に依存します. 依存先のライブラリをオフにすると, それに依存するモジュールも読み込めません.
- このプラグインは AutoJs6 の既定の JavaScript スクリプト向けです. Node.js ランタイムプラグインのスクリプトは Node.js 組み込みのモジュールを使います.

### 動作要件

- Android 7.0 以降, AutoJs6 6.8.0 以降.
- このプラグインはリソースパッケージにすぎず, 権限は必要ありません. axios のネットワークリクエストはスクリプトを実行する AutoJs6 が送信します.

### よくある質問

**スクリプトがこのプラグインを必要とすると表示する場合**

AutoJs6 6.8.0 以降, これらのライブラリは AutoJs6 のパッケージに含まれません. このプラグインをインストールしてプラグインセンターで有効にし, プラグインのページで該当ライブラリのスイッチがオンになっていることを確認してください.

**既存のスクリプト**

変更は不要です. `axios` などのグローバルオブジェクトも `require('axios')` などの呼び出しもそのまま使え, ライブラリはこのプラグインから読み込まれます.

各ライブラリのバージョン, モジュール名, ライセンスは [プロジェクトページ](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries) を参照してください.
