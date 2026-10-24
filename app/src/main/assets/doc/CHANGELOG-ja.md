******

### リリース履歴

******

# v1.0.0

###### 2026/10/24

* `ヒント` AutoJs6 6.8.0 (5935) 以降が必要です
* `ヒント` AutoJs6 6.8.0 以降, 以下のライブラリは AutoJs6 に同梱されなくなります. このプラグインをインストールすれば require とグローバルオブジェクトは以前どおり使え, 既存のスクリプトを変更する必要はありません. プラグインが未インストールまたは無効のときは, これらのライブラリを require するとインストールまたは有効化の方法を示すエラーになります
* `機能` axios, cheerio, dayjs, i18n (banana-i18n を含む), pangu, lodash を require で利用可能. axios, cheerio, dayjs, i18n, pangu はグローバルオブジェクトとしても利用可能
* `機能` Node 互換シム buffer, events, fs, os, path, process, nodejs を require で利用可能
* `機能` プラグインのページでライブラリごとにオン / オフを切り替え (既定はすべてオン), リリース履歴を表示. オフにしたライブラリは以降の require で利用不可
* `依存関係` AutoJs6 Common Plugin API を追加
