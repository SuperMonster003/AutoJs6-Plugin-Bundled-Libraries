Bundled Libraries 는 예전에 AutoJs6 에 내장되어 있던 13 개의 스크립트 라이브러리 (axios, cheerio, dayjs, i18n, pangu, lodash) 와 Node 호환 심 (buffer, events, fs, os, path, process, nodejs) 을 담고 있습니다. AutoJs6 6.8.0 부터는 AutoJs6 패키지에 이 라이브러리들이 포함되지 않습니다. 이 플러그인을 설치하고 활성화하면 `require('axios')`, `require('fs')` 등과 전역 객체 `axios`, `cheerio`, `dayjs`, `i18n`, `pangu` 를 예전과 똑같이 사용할 수 있습니다.

플러그인 자체는 리소스 패키지일 뿐입니다. AutoJs6 가 플러그인 패키지에서 라이브러리 파일을 직접 읽으므로 스크립트를 실행할 때 플러그인 프로세스가 시작되지 않으며, 플러그인은 저장소, 네트워크 등의 권한도 필요하지 않습니다 (axios 의 네트워크 요청은 스크립트를 실행하는 AutoJs6 가 보냅니다). 필요 없는 라이브러리는 플러그인 페이지에서 하나씩 끌 수 있습니다.

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

### 라이브러리별 스위치

플러그인 페이지 (AutoJs6 플러그인 센터 또는 홈 화면의 Bundled Libraries 아이콘으로 엽니다) 에는 라이브러리마다 스위치가 있으며, 기본값은 모두 켜짐입니다:

- 끈 라이브러리는 더 이상 `require` 할 수 없고 전역 객체도 사용할 수 없습니다. 다시 켜면 복구됩니다.
- 스위치는 이후의 `require` 에 적용됩니다. 실행 중인 스크립트가 이미 불러온 모듈은 스크립트가 끝날 때까지 유지됩니다.
- `fs` 는 `buffer` 가, `nodejs` 는 `process` 와 `buffer` 가 필요합니다. `buffer` 나 `process` 를 끄면 이에 의존하는 모듈도 불러올 수 없습니다.
- 페이지 하단의 "릴리스 기록" 에서 버전별 변경 내용을 볼 수 있습니다.

### 플러그인을 사용할 수 없을 때

플러그인이 설치되지 않았거나, 승인되지 않았거나, 플러그인 센터에서 비활성화되었거나, 호환되지 않거나, 라이브러리가 꺼져 있으면 해당 라이브러리의 `require` 와 전역 객체 읽기는 오류를 던집니다:

- 오류는 `code` 가 `MODULE_NOT_FOUND` 인 `Error` 이며, 메시지는 플러그인을 설치해야 하는지 어디에서 켜야 하는지 알려 줍니다. 스크립트는 `try...catch` 로 처리할 수 있습니다.
- 오류 객체에는 `library` (라이브러리 이름) 와 `reason` (`not-installed`, `unauthorized`, `plugin-disabled`, `library-disabled`, `incompatible` 중 하나) 도 있습니다.
- 플러그인 상태가 바뀌어도 실행 중인 스크립트는 중단되지 않고 이후의 `require` 만 영향을 받습니다. AutoJs6 의 다른 내장 모듈과 스크립트 자신의 파일은 영향을 받지 않습니다.

소스 코드, 릴리스 기록, 서드파티 라이선스는 [프로젝트 페이지](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries) 에 있습니다.
