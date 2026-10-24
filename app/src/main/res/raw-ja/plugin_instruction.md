Bundled Libraries は, かつて AutoJs6 に内蔵されていた 13 個のスクリプトライブラリ (axios, cheerio, dayjs, i18n, pangu, lodash) と Node 互換シム (buffer, events, fs, os, path, process, nodejs) を収録しています. AutoJs6 6.8.0 以降, これらは AutoJs6 のパッケージに含まれません. このプラグインをインストールして有効にすれば, `require('axios')` や `require('fs')` など, およびグローバルオブジェクト `axios`, `cheerio`, `dayjs`, `i18n`, `pangu` が以前とまったく同じ書き方で使えます.

プラグイン自体はリソースパッケージにすぎません. AutoJs6 はプラグインのパッケージからライブラリファイルを直接読み込み, スクリプトの実行時にプラグインのプロセスが起動することはありません. プラグインはストレージやネットワークなどの権限も必要としません (axios のネットワーク要求はスクリプトを実行する AutoJs6 が送信します). 不要なライブラリはプラグインのページで個別にオフにできます.

#### ライブラリ

| ライブラリ | モジュール名 | グローバルオブジェクト | バージョン | ライセンス |
|---|---|---|---|---|
| axios | `axios` | `axios` | - | MIT |
| cheerio | `cheerio` | `cheerio` | - | MIT |
| dayjs | `dayjs` | `dayjs` | - | MIT |
| i18n | `i18n`, `banana-i18n` | `i18n` | - | MIT |
| pangu | `pangu` | `pangu` | 10.1.0 | MIT |
| lodash | `lodash` | - | 4.17.15 | MIT |

#### Node 互換シム

| ライブラリ | モジュール名 | グローバルオブジェクト | バージョン | ライセンス |
|---|---|---|---|---|
| buffer | `buffer` | - | - | MPL-2.0 |
| events | `events` | - | - | MPL-2.0 |
| fs | `fs` | - | - | MPL-2.0 |
| os | `os` | - | - | MPL-2.0 |
| path | `path` | - | - | MPL-2.0 |
| process | `process` | - | - | MPL-2.0 |
| nodejs | `nodejs` | - | - | MPL-2.0 |

### ライブラリごとのスイッチ

プラグインのページ (AutoJs6 のプラグインセンター, またはホーム画面の Bundled Libraries アイコンから開きます) にはライブラリごとのスイッチがあり, 既定ではすべてオンです:

- オフにしたライブラリは `require` できなくなり, グローバルオブジェクトも使えません. オンに戻せば元どおり使えます.
- スイッチはそれ以降の `require` に適用されます. 実行中のスクリプトが読み込み済みのモジュールはスクリプト終了まで残ります.
- `fs` は `buffer` を, `nodejs` は `process` と `buffer` を必要とします. `buffer` または `process` をオフにすると, それに依存するモジュールも読み込めません.
- ページ下部の "リリース履歴" で各バージョンの変更内容を確認できます.

### プラグインを使えないとき

プラグインが未インストール, 未承認, プラグインセンターで無効, 互換性なし, またはライブラリがオフのとき, そのライブラリの `require` やグローバルオブジェクトの読み取りはエラーをスローします:

- エラーは `code` が `MODULE_NOT_FOUND` の `Error` で, メッセージはプラグインのインストールが必要か, どこでオンにするかを示します. スクリプトは `try...catch` で処理できます.
- エラーオブジェクトには `library` (ライブラリ名) と `reason` (`not-installed`, `unauthorized`, `plugin-disabled`, `library-disabled`, `incompatible` のいずれか) もあります.
- プラグインの状態が変わっても実行中のスクリプトは中断されず, 以降の `require` だけが影響を受けます. AutoJs6 のほかの内蔵モジュールやスクリプト自身のファイルには影響しません.

ソースコード, リリース履歴, サードパーティのライセンスは [プロジェクトページ](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries) にあります.
