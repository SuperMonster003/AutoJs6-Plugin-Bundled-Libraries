# Third-party notices

The files under `app/src/main/assets/modules` were copied unchanged from AutoJs6 (`app/src/main/assets/modules`, as of 2026-10-24). Versions are given where the files state them.

`app/src/main/assets/modules` 下的文件原样复制自 AutoJs6 (`app/src/main/assets/modules`, 2026-10-24). 文件中写明版本的给出版本.

## Module files / 模块文件

| Files | Project | Version | License | Notes |
|---|---|---|---|---|
| `axios/axios.min.js` | [axios](https://github.com/axios/axios) | 1.1.2 (the `VERSION` constant of the file) | MIT, Copyright (c) 2014-present Matt Zabriskie & Collaborators | Rhino adaptation from [AutoX](https://github.com/AutoX) by [aiselp](https://github.com/aiselp) (see `axios/remark.md`) |
| `axios/browser-libs/*`, `axios/utils/*`, `axios.js` | AutoX / AutoJs6 | - | MPL-2.0 | browser API shims for axios on Rhino |
| `cheerio.js` | [cheerio](https://github.com/cheeriojs/cheerio) | not stated | MIT, Copyright (c) 2022 The Cheerio contributors | single-file build that includes the packages listed under "Packages bundled in cheerio.js" |
| `dayjs/*`, `dayjs.js` | [Day.js](https://github.com/iamkun/dayjs) | not stated | MIT, Copyright (c) 2018-present iamkun (`dayjs/LICENSE`) | |
| `banana-i18n.js` | [banana-i18n](https://github.com/wikimedia/banana-i18n) | not stated | MIT, Copyright (c) Wikimedia Foundation and contributors | |
| `i18n.js` | AutoJs6 | - | MPL-2.0 | wrapper over banana-i18n |
| `pangu.min.js`, `pangu.js` | [pangu.js](https://github.com/vinta/pangu.js) | 10.1.0 | MIT, Copyright (c) 2013 Vinta (`pangu.LICENSE.txt`) | transpiled for Rhino by AutoJs6 `tools/pangu/build.cjs` |
| `lodash.js` | [lodash](https://lodash.com/) core build | 4.17.15 | MIT, Copyright OpenJS Foundation and other contributors | |
| `buffer.js`, `events.js`, `fs.js`, `os.js`, `path.js`, `process.js`, `nodejs.js` | AutoJs6 | - | MPL-2.0 | Node compatibility shims (AutoJs6 `feat(node): add CommonJS compatibility modules`, 2026-06-14) |

## Packages bundled in cheerio.js / cheerio.js 内含的软件包

The cheerio build contains code of these packages (the names appear in the build; it states no versions). License and copyright holder as published by each package:

cheerio 构建包含以下软件包的代码 (名称见于构建文件, 未写明版本). 许可证与版权人以各软件包发布的为准:

| Package | License | Copyright |
|---|---|---|
| [parse5](https://github.com/inikulin/parse5) (with its htmlparser2 tree adapter) | MIT | Copyright (c) 2013-2019 Ivan Nikulin |
| [htmlparser2](https://github.com/fb55/htmlparser2) | MIT | Copyright 2010, 2011, Chris Winberg |
| [dom-serializer](https://github.com/cheeriojs/dom-serializer) | MIT | Copyright (c) 2014 The cheeriojs contributors |
| [domhandler](https://github.com/fb55/domhandler) | BSD-2-Clause | Copyright (c) Felix Böhm |
| [domutils](https://github.com/fb55/domutils) | BSD-2-Clause | Copyright (c) Felix Böhm |
| [domelementtype](https://github.com/fb55/domelementtype) | BSD-2-Clause | Copyright (c) Felix Böhm |
| [entities](https://github.com/fb55/entities) | BSD-2-Clause | Copyright (c) Felix Böhm |
| [css-select](https://github.com/fb55/css-select) | BSD-2-Clause | Copyright (c) Felix Böhm |
| [css-what](https://github.com/fb55/css-what) | BSD-2-Clause | Copyright (c) Felix Böhm |
| [cheerio-select](https://github.com/cheeriojs/cheerio-select) | BSD-2-Clause | Copyright (c) Felix Böhm |
| [nth-check](https://github.com/fb55/nth-check) | BSD-2-Clause | Copyright (c) Felix Böhm |
| [boolbase](https://github.com/fb55/boolbase) | ISC | Copyright (c) Felix Böhm |

## Build dependency / 构建依赖

| File | Project | License | SHA-256 |
|---|---|---|---|
| `libs/common-plugin-api.aar` | AutoJs6 host module `plugin-api/common-plugin-api` (https://github.com/SuperMonster003/AutoJs6) | MPL-2.0 | `6d75eb2350aa56ed412dabf84b34c0f65b9a6f6cfce3f3c23f79b9b198c24a63` |

## MIT License

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

## BSD 2-Clause License

Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:

1. Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.

2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

## ISC License

Permission to use, copy, modify, and/or distribute this software for any purpose with or without fee is hereby granted, provided that the above copyright notice and this permission notice appear in all copies.

THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES WITH REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF OR IN CONNECTION WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.
