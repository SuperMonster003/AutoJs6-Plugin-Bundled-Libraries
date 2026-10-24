<!--suppress HtmlDeprecatedAttribute, HttpUrlsUsage -->

<div align="center">
  <p>
    <img src="https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/app/src/main/res/mipmap/ic_launcher.png?raw=true" alt="autojs6-plugin-bundled-libraries-ic-launcher" border="0" width="128" />
  </p>

  <p>AutoJs6 스크립트의 require 용 JavaScript 라이브러리와 Node 호환 심</p>

  <p>
    <a href="https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/releases"><img alt="GitHub release (latest by date)" src="https://img.shields.io/github/v/release/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries?label=Release"/></a>
    <a href="https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/issues"><img alt="GitHub closed issues" src="https://img.shields.io/github/issues/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries?color=A24232&label=Issues"/></a>
    <a href="https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/LICENSE"><img alt="GitHub License" src="https://img.shields.io/github/license/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries?color=534BAE&label=License"/></a>
  </p>
</div>

******

### 언어 (Languages)

******

현재 README.md 는 다음 언어를 지원합니다:

- [简体中文 [zh-Hans]](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/.readme/README-zh-Hans.md)
- [繁體中文 (香港) [zh-Hant-HK]](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/.readme/README-zh-Hant-HK.md)
- [繁體中文 (台灣) [zh-Hant-TW]](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/.readme/README-zh-Hant-TW.md)
- [English [en]](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/.readme/README-en.md)
- [Français [fr]](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/.readme/README-fr.md)
- [Español [es]](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/.readme/README-es.md)
- [日本語 [ja]](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/.readme/README-ja.md)
- 한국어 [ko] # 현재
- [Русский [ru]](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/.readme/README-ru.md)
- [العربية [ar]](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/.readme/README-ar.md)

******

### 소개

******

Bundled Libraries 는 예전에 AutoJs6 에 내장되어 있던 13 개의 스크립트 라이브러리 (axios, cheerio, dayjs, i18n, pangu, lodash) 와 Node 호환 심 (buffer, events, fs, os, path, process, nodejs) 을 담고 있습니다. AutoJs6 6.8.0 부터는 AutoJs6 패키지에 이 라이브러리들이 포함되지 않습니다. 이 플러그인을 설치하고 활성화하면 `require('axios')`, `require('fs')` 등과 전역 객체 `axios`, `cheerio`, `dayjs`, `i18n`, `pangu` 를 예전과 똑같이 사용할 수 있습니다.

플러그인 자체는 리소스 패키지일 뿐입니다. AutoJs6 가 플러그인 패키지에서 라이브러리 파일을 직접 읽으므로 스크립트를 실행할 때 플러그인 프로세스가 시작되지 않으며, 플러그인은 저장소, 네트워크 등의 권한도 필요하지 않습니다 (axios 의 네트워크 요청은 스크립트를 실행하는 AutoJs6 가 보냅니다). 필요 없는 라이브러리는 플러그인 페이지에서 하나씩 끌 수 있습니다.

******

### 포함된 라이브러리

******

모듈 이름이 `require` 의 인수입니다. 전역 객체가 있는 라이브러리는 `require` 없이 바로 사용할 수도 있습니다. 각 라이브러리의 출처와 라이선스는 [THIRD_PARTY_NOTICES.md](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/THIRD_PARTY_NOTICES.md) 를 참고하세요.

#### 라이브러리

| 라이브러리 | 모듈 이름 | 전역 객체 | 버전 | 라이선스 |
|---|---|---|---|---|
| axios | `axios` | `axios` | - | MIT |
| cheerio | `cheerio` | `cheerio` | - | MIT |
| dayjs | `dayjs` | `dayjs` | - | MIT |
| i18n | `i18n`, `banana-i18n` | `i18n` | - | MIT |
| pangu | `pangu` | `pangu` | 10.1.0 | MIT |
| lodash | `lodash` | - | 4.17.15 | MIT |

#### Node 호환 심

| 라이브러리 | 모듈 이름 | 전역 객체 | 버전 | 라이선스 |
|---|---|---|---|---|
| buffer | `buffer` | - | - | MPL-2.0 |
| events | `events` | - | - | MPL-2.0 |
| fs | `fs` | - | - | MPL-2.0 |
| os | `os` | - | - | MPL-2.0 |
| path | `path` | - | - | MPL-2.0 |
| process | `process` | - | - | MPL-2.0 |
| nodejs | `nodejs` | - | - | MPL-2.0 |

******

### 사용 방법

******

1. AutoJs6 6.8.0 (5935) 이상을 사용합니다.
2. AutoJs6 의 플러그인 센터에서 Bundled Libraries 를 설치하고 ([Releases](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/releases) 에서 APK 를 내려받아 설치해도 됩니다), 플러그인이 활성화되어 있는지 확인합니다.
3. 스크립트에서 평소처럼 필요한 라이브러리를 `require` 하거나 전역 객체를 바로 사용합니다. 예:

```javascript
var _ = require('lodash');
var path = require('path');

console.log(dayjs(0).valueOf());
console.log(cheerio.load('<p>AutoJs6</p>')('p').text());
console.log(_.map([1, 2], function (x) { return x * 2; }).join());
console.log(path.isAbsolute('/sdcard/Scripts'));
```

> 패키지는 네이티브 라이브러리가 없는 범용 패키지 하나뿐이며, Android 7.0 (API 24) 이상의 기기를 지원합니다.

******

### 라이브러리별 스위치

******

플러그인 페이지 (AutoJs6 플러그인 센터 또는 홈 화면의 Bundled Libraries 아이콘으로 엽니다) 에는 라이브러리마다 스위치가 있으며, 기본값은 모두 켜짐입니다:

- 끈 라이브러리는 더 이상 `require` 할 수 없고 전역 객체도 사용할 수 없습니다. 다시 켜면 복구됩니다.
- 스위치는 이후의 `require` 에 적용됩니다. 실행 중인 스크립트가 이미 불러온 모듈은 스크립트가 끝날 때까지 유지됩니다.
- `fs` 는 `buffer` 가, `nodejs` 는 `process` 와 `buffer` 가 필요합니다. `buffer` 나 `process` 를 끄면 이에 의존하는 모듈도 불러올 수 없습니다.
- 페이지 하단의 "릴리스 기록" 에서 버전별 변경 내용을 볼 수 있습니다.

******

### 플러그인을 사용할 수 없을 때

******

플러그인이 설치되지 않았거나, 승인되지 않았거나, 플러그인 센터에서 비활성화되었거나, 호환되지 않거나, 라이브러리가 꺼져 있으면 해당 라이브러리의 `require` 와 전역 객체 읽기는 오류를 던집니다:

- 오류는 `code` 가 `MODULE_NOT_FOUND` 인 `Error` 이며, 메시지는 플러그인을 설치해야 하는지 어디에서 켜야 하는지 알려 줍니다. 스크립트는 `try...catch` 로 처리할 수 있습니다.
- 오류 객체에는 `library` (라이브러리 이름) 와 `reason` (`not-installed`, `unauthorized`, `plugin-disabled`, `library-disabled`, `incompatible` 중 하나) 도 있습니다.
- 플러그인 상태가 바뀌어도 실행 중인 스크립트는 중단되지 않고 이후의 `require` 만 영향을 받습니다. AutoJs6 의 다른 내장 모듈과 스크립트 자신의 파일은 영향을 받지 않습니다.

******

### 호환성

******

- AutoJs6 6.8.0 (빌드 5935) 이상이 필요합니다. 그 이전 버전의 AutoJs6 는 이 라이브러리들을 내장하고 있어 플러그인이 필요 없습니다.
- Android 7.0 (API 24) 이상을 지원하며, 패키지 하나로 모든 프로세서 아키텍처를 지원합니다.
- 라이브러리 파일은 예전에 AutoJs6 에 내장되어 있던 것과 완전히 같으므로 기존 스크립트를 수정할 필요가 없습니다.
- 이 플러그인은 AutoJs6 의 기본 JavaScript 스크립트를 위한 것입니다. Node.js 런타임 플러그인의 스크립트는 Node.js 자체 모듈을 사용합니다.

******

### 자주 묻는 질문

******

#### AutoJs6 가 이 라이브러리들을 더 이상 내장하지 않는 이유는 무엇인가요?

일부 스크립트만 사용하기 때문입니다. 플러그인으로 옮기면 AutoJs6 패키지가 작아지고, 필요한 사람만 플러그인을 설치하며, 사용하는 라이브러리만 켜 둘 수 있습니다. 플러그인의 파일은 내장되어 있던 것과 같습니다.

#### 스크립트에서 전역 객체 `axios` 를 바로 사용합니다. `require` 로 바꿔야 하나요?

아니요. 전역 객체 `axios`, `cheerio`, `dayjs`, `i18n`, `pangu` 는 `require` 가 반환하는 모듈과 같으며, 둘 다 이 플러그인에서 불러옵니다.

#### 플러그인이 인터넷에 연결하거나 백그라운드에서 실행되나요?

아니요. 플러그인에는 네트워크 권한이 없고, 스크립트를 실행해도 플러그인 프로세스가 시작되지 않습니다. axios 의 네트워크 요청은 스크립트를 실행하는 AutoJs6 가 보냅니다.

#### 플러그인을 승인해야 한다는 오류가 나오면 어떻게 하나요?

AutoJs6 플러그인 센터나 이 프로젝트의 Releases 에서 설치한 정식 버전은 승인이 필요 없습니다. 직접 빌드한 버전은 서명이 다르므로 먼저 플러그인 센터에서 안내에 따라 승인해야 합니다.

******

### 플러그인 인터페이스

******

다음 정보는 AutoJs6 호스트와 플러그인 개발자를 위한 것으로, 호스트는 이 식별자들로 플러그인을 발견하고 라이브러리 파일을 읽습니다:

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

### 개발 로드맵

******

출시 전 점검 목록과 출시 절차는 ROADMAP.md 에 기록되어 있으며, 관련 결정은 AutoJs6 저장소의 `docs/augment/ROADMAP.md` 에 있습니다.

- [ROADMAP.md 보기](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/ROADMAP.md)

******

### 릴리스 기록

******

#### v1.0.0

_2026/10/24_

- `힌트` AutoJs6 6.8.0 (5935) 이상이 필요합니다
- `힌트` AutoJs6 6.8.0 부터 아래 라이브러리는 AutoJs6 에 포함되지 않습니다. 이 플러그인을 설치하면 require 와 전역 객체를 예전처럼 사용할 수 있어 기존 스크립트를 수정할 필요가 없습니다. 플러그인이 없거나 비활성화되어 있으면 이 라이브러리를 require 할 때 설치 또는 활성화 방법을 알려 주는 오류가 발생합니다
- `기능` axios, cheerio, dayjs, i18n (banana-i18n 포함), pangu, lodash 를 require 로 사용 가능, 그중 axios, cheerio, dayjs, i18n, pangu 는 전역 객체로도 사용 가능
- `기능` Node 호환 심 buffer, events, fs, os, path, process, nodejs 를 require 로 사용 가능
- `기능` 플러그인 페이지에서 라이브러리별 켜기 / 끄기 (기본값 모두 켜짐) 와 릴리스 기록 보기, 끈 라이브러리는 이후 require 에서 사용 불가
- `의존성` AutoJs6 Common Plugin API 추가

##### 더 많은 릴리스 기록은 다음을 참고

* [CHANGELOG.md](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/app/src/main/assets/doc/CHANGELOG-ko.md)

******

### 빌드

******

이 절은 소스에서 플러그인을 빌드하려는 개발자를 위한 것입니다.

```powershell
.\gradlew.bat :app:assembleDebug :app:testDebugUnitTest
py .python\generate_markdown.py --check
py .python\generate_icon_studio.py --check
py .python\check_repository.py
```

******

### 라이선스

******

플러그인 코드와 AutoJs6 를 위해 작성된 모듈 (Node 호환 심, i18n 래퍼, axios 어댑터) 은 [Mozilla Public License 2.0](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/LICENSE) 으로 배포됩니다. 포함된 서드파티 라이브러리는 각자의 라이선스 (MIT 등) 를 따르며, 자세한 내용은 [THIRD_PARTY_NOTICES.md](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/THIRD_PARTY_NOTICES.md) 를 참고하세요.

******

### 관련 링크

******

- AutoJs6 프로젝트: https://github.com/SuperMonster003/AutoJs6
- 서드파티 고지: https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/THIRD_PARTY_NOTICES.md
