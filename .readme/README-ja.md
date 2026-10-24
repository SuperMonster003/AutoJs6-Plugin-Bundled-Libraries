<!--suppress HtmlDeprecatedAttribute, HttpUrlsUsage -->

<div align="center">
  <p>
    <img src="https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/app/src/main/res/mipmap/ic_launcher.png?raw=true" alt="autojs6-plugin-bundled-libraries-ic-launcher" border="0" width="128" />
  </p>

  <p>AutoJs6 スクリプトの require 用 JavaScript ライブラリと Node 互換シム</p>

  <p>
    <a href="https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/releases"><img alt="GitHub release (latest by date)" src="https://img.shields.io/github/v/release/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries?label=Release"/></a>
    <a href="https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/issues"><img alt="GitHub closed issues" src="https://img.shields.io/github/issues/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries?color=A24232&label=Issues"/></a>
    <a href="https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/LICENSE"><img alt="GitHub License" src="https://img.shields.io/github/license/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries?color=534BAE&label=License"/></a>
  </p>
</div>

******

### 言語 (Languages)

******

現在の README.md は次の言語に対応しています:

- [简体中文 [zh-Hans]](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/.readme/README-zh-Hans.md)
- [繁體中文 (香港) [zh-Hant-HK]](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/.readme/README-zh-Hant-HK.md)
- [繁體中文 (台灣) [zh-Hant-TW]](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/.readme/README-zh-Hant-TW.md)
- [English [en]](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/.readme/README-en.md)
- [Français [fr]](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/.readme/README-fr.md)
- [Español [es]](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/.readme/README-es.md)
- 日本語 [ja] # 現在
- [한국어 [ko]](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/.readme/README-ko.md)
- [Русский [ru]](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/.readme/README-ru.md)
- [العربية [ar]](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/.readme/README-ar.md)

******

### 概要

******

Bundled Libraries は, かつて AutoJs6 に内蔵されていた 13 個のスクリプトライブラリ (axios, cheerio, dayjs, i18n, pangu, lodash) と Node 互換シム (buffer, events, fs, os, path, process, nodejs) を収録しています. AutoJs6 6.8.0 以降, これらは AutoJs6 のパッケージに含まれません. このプラグインをインストールして有効にすれば, `require('axios')` や `require('fs')` など, およびグローバルオブジェクト `axios`, `cheerio`, `dayjs`, `i18n`, `pangu` が以前とまったく同じ書き方で使えます.

プラグイン自体はリソースパッケージにすぎません. AutoJs6 はプラグインのパッケージからライブラリファイルを直接読み込み, スクリプトの実行時にプラグインのプロセスが起動することはありません. プラグインはストレージやネットワークなどの権限も必要としません (axios のネットワーク要求はスクリプトを実行する AutoJs6 が送信します). 不要なライブラリはプラグインのページで個別にオフにできます.

******

### 収録ライブラリ

******

モジュール名が `require` の引数です. グローバルオブジェクトがあるライブラリは `require` を書かずにそのまま使えます. 各ライブラリの出所とライセンスは [THIRD_PARTY_NOTICES.md](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/THIRD_PARTY_NOTICES.md) を参照してください.

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

******

### 使用方法

******

1. AutoJs6 6.8.0 (5935) 以降を使用します.
2. AutoJs6 のプラグインセンターから Bundled Libraries をインストールし ([Releases](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/releases) から APK をダウンロードしても構いません), プラグインが有効になっていることを確認します.
3. スクリプトでは従来どおり必要なライブラリを `require` するか, グローバルオブジェクトをそのまま使います. 例:

```javascript
var _ = require('lodash');
var path = require('path');

console.log(dayjs(0).valueOf());
console.log(cheerio.load('<p>AutoJs6</p>')('p').text());
console.log(_.map([1, 2], function (x) { return x * 2; }).join());
console.log(path.isAbsolute('/sdcard/Scripts'));
```

> パッケージはネイティブライブラリを含まない汎用版の 1 つだけで, Android 7.0 (API 24) 以降の端末に対応します.

******

### ライブラリごとのスイッチ

******

プラグインのページ (AutoJs6 のプラグインセンター, またはホーム画面の Bundled Libraries アイコンから開きます) にはライブラリごとのスイッチがあり, 既定ではすべてオンです:

- オフにしたライブラリは `require` できなくなり, グローバルオブジェクトも使えません. オンに戻せば元どおり使えます.
- スイッチはそれ以降の `require` に適用されます. 実行中のスクリプトが読み込み済みのモジュールはスクリプト終了まで残ります.
- `fs` は `buffer` を, `nodejs` は `process` と `buffer` を必要とします. `buffer` または `process` をオフにすると, それに依存するモジュールも読み込めません.
- ページ下部の "リリース履歴" で各バージョンの変更内容を確認できます.

******

### プラグインを使えないとき

******

プラグインが未インストール, 未承認, プラグインセンターで無効, 互換性なし, またはライブラリがオフのとき, そのライブラリの `require` やグローバルオブジェクトの読み取りはエラーをスローします:

- エラーは `code` が `MODULE_NOT_FOUND` の `Error` で, メッセージはプラグインのインストールが必要か, どこでオンにするかを示します. スクリプトは `try...catch` で処理できます.
- エラーオブジェクトには `library` (ライブラリ名) と `reason` (`not-installed`, `unauthorized`, `plugin-disabled`, `library-disabled`, `incompatible` のいずれか) もあります.
- プラグインの状態が変わっても実行中のスクリプトは中断されず, 以降の `require` だけが影響を受けます. AutoJs6 のほかの内蔵モジュールやスクリプト自身のファイルには影響しません.

******

### 互換性

******

- AutoJs6 6.8.0 (ビルド 5935) 以降が必要です. それより前の AutoJs6 はこれらのライブラリを内蔵しているため, このプラグインは不要です.
- Android 7.0 (API 24) 以降に対応し, 1 つのパッケージですべてのプロセッサアーキテクチャに対応します.
- ライブラリファイルはかつて AutoJs6 に内蔵されていたものとまったく同じなので, 既存のスクリプトを変更する必要はありません.
- このプラグインは AutoJs6 の既定の JavaScript スクリプト向けです. Node.js ランタイムプラグインのスクリプトは Node.js 自体のモジュールを使います.

******

### よくある質問

******

#### AutoJs6 がこれらのライブラリを内蔵しなくなったのはなぜですか?

使うのは一部のスクリプトだけだからです. プラグインにすることで AutoJs6 のパッケージが小さくなり, 必要な人だけがプラグインを入れ, 使うライブラリだけをオンにできます. プラグイン内のファイルは内蔵版と同じです.

#### スクリプトでグローバルオブジェクト `axios` を直接使っています. `require` に書き換える必要はありますか?

ありません. グローバルオブジェクト `axios`, `cheerio`, `dayjs`, `i18n`, `pangu` は `require` が返すモジュールと同じもので, どちらもこのプラグインから読み込まれます.

#### プラグインはネットワークに接続したり, バックグラウンドで動いたりしますか?

しません. プラグインにはネットワーク権限がなく, スクリプトを実行してもプラグインのプロセスは起動しません. axios のネットワーク要求はスクリプトを実行する AutoJs6 が送信します.

#### プラグインの承認が必要というエラーが出たらどうすればよいですか?

AutoJs6 のプラグインセンターまたはこのプロジェクトの Releases からインストールした正式版は承認不要です. 自分でビルドした版は署名が異なるため, 先にプラグインセンターで案内に従って承認してください.

******

### プラグインインターフェース

******

以下の情報は AutoJs6 ホストとプラグイン開発者向けです. ホストはこれらの識別子でプラグインを検出し, ライブラリファイルを読み込みます:

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

### 開発ロードマップ

******

リリース前チェックリストとリリース手順は ROADMAP.md に記録しています. 関連する決定は AutoJs6 リポジトリの `docs/augment/ROADMAP.md` にあります.

- [ROADMAP.md を見る](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/ROADMAP.md)

******

### リリース履歴

******

#### v1.0.0

_2026/10/24_

- `ヒント` AutoJs6 6.8.0 (5935) 以降が必要です
- `ヒント` AutoJs6 6.8.0 以降, 以下のライブラリは AutoJs6 に同梱されなくなります. このプラグインをインストールすれば require とグローバルオブジェクトは以前どおり使え, 既存のスクリプトを変更する必要はありません. プラグインが未インストールまたは無効のときは, これらのライブラリを require するとインストールまたは有効化の方法を示すエラーになります
- `機能` axios, cheerio, dayjs, i18n (banana-i18n を含む), pangu, lodash を require で利用可能. axios, cheerio, dayjs, i18n, pangu はグローバルオブジェクトとしても利用可能
- `機能` Node 互換シム buffer, events, fs, os, path, process, nodejs を require で利用可能
- `機能` プラグインのページでライブラリごとにオン / オフを切り替え (既定はすべてオン), リリース履歴を表示. オフにしたライブラリは以降の require で利用不可
- `機能` プラグインのページは AutoJs6 の言語, 夜間モード, テーマカラーに従う. AutoJs6 が未インストール, または設定を読み取れない場合はシステムに従う
- `依存関係` AutoJs6 Common Plugin API を追加

##### その他のリリース履歴は以下を参照

* [CHANGELOG.md](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/app/src/main/assets/doc/CHANGELOG-ja.md)

******

### ビルド

******

この節はソースからプラグインをビルドしたい開発者向けです.

```powershell
.\gradlew.bat :app:assembleDebug :app:testDebugUnitTest
py .python\generate_markdown.py --check
py .python\generate_icon_studio.py --check
py .python\check_repository.py
```

******

### ライセンス

******

プラグインのコードと AutoJs6 向けに書かれたモジュール (Node 互換シム, i18n ラッパー, axios アダプター) は [Mozilla Public License 2.0](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/LICENSE) で提供されます. 収録したサードパーティライブラリはそれぞれのライセンス (MIT など) に従います. 詳細は [THIRD_PARTY_NOTICES.md](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/THIRD_PARTY_NOTICES.md) を参照してください.

******

### 関連リンク

******

- AutoJs6 プロジェクト: https://github.com/SuperMonster003/AutoJs6
- サードパーティ通知: https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/THIRD_PARTY_NOTICES.md
