# Third-party notices

The files under `app/src/main/assets/modules` were copied unchanged from AutoJs6 (`app/src/main/assets/modules`, as of 2026-10-24). Versions are given where the files state them.

`app/src/main/assets/modules` 下的文件原样复制自 AutoJs6 (`app/src/main/assets/modules`, 2026-10-24). 文件中写明版本的给出版本.

| Files | Project | Version | License | Notes |
|---|---|---|---|---|
| `axios/axios.min.js` | [axios](https://github.com/axios/axios) | not stated | MIT, Copyright (c) 2014-present Matt Zabriskie & Collaborators | Rhino adaptation from [AutoX](https://github.com/AutoX) by [aiselp](https://github.com/aiselp) (see `axios/remark.md`) |
| `axios/browser-libs/*`, `axios/utils/*`, `axios.js` | AutoX / AutoJs6 | - | MPL-2.0 | browser API shims for axios on Rhino |
| `cheerio.js` | [cheerio](https://github.com/cheeriojs/cheerio) | not stated | MIT, Copyright (c) 2022 The Cheerio contributors | bundled build, with its dependencies (parse5, htmlparser2, css-select and others, MIT / BSD-2-Clause) |
| `dayjs/*`, `dayjs.js` | [Day.js](https://github.com/iamkun/dayjs) | not stated | MIT, Copyright (c) 2018-present iamkun (`dayjs/LICENSE`) | |
| `banana-i18n.js` | [banana-i18n](https://github.com/wikimedia/banana-i18n) | not stated | MIT, Copyright (c) Wikimedia Foundation and contributors | |
| `i18n.js` | AutoJs6 | - | MPL-2.0 | wrapper over banana-i18n |
| `pangu.min.js`, `pangu.js` | [pangu.js](https://github.com/vinta/pangu.js) | 10.1.0 | MIT, Copyright (c) 2013 Vinta (`pangu.LICENSE.txt`) | transpiled for Rhino by AutoJs6 `tools/pangu/build.cjs` |
| `lodash.js` | [lodash](https://lodash.com/) core build | 4.17.15 | MIT, Copyright OpenJS Foundation and other contributors | |
| `buffer.js`, `events.js`, `fs.js`, `os.js`, `path.js`, `process.js`, `nodejs.js` | AutoJs6 | - | MPL-2.0 | Node compatibility shims (AutoJs6 `feat(node): add CommonJS compatibility modules`, 2026-06-14) |

## MIT License

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
