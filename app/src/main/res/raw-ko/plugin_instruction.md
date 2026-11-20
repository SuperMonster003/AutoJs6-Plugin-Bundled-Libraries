Bundled Libraries 는 AutoJs6 스크립트에 13 개의 라이브러리를 제공합니다. axios, cheerio, dayjs 등의 JavaScript 라이브러리와 fs, path 등의 Node 호환 심입니다. 활성화하면 `require('axios')` 등의 방식과 `dayjs` 등의 전역 객체를 예전처럼 사용할 수 있어 기존 스크립트를 고칠 필요가 없습니다.

### 시작하기

1. AutoJs6 플러그인 센터에서 이 플러그인이 활성화되어 있는지 확인합니다.
2. 스크립트에서 `require` 로 라이브러리를 불러오거나 전역 객체 `axios`, `cheerio`, `dayjs`, `i18n`, `pangu` 를 바로 사용합니다. 예제는 아래를 참고하세요.
3. 사용하지 않는 라이브러리는 플러그인 페이지에서 끌 수 있습니다. 플러그인 페이지는 플러그인 센터 또는 홈 화면의 Bundled Libraries 아이콘으로 엽니다.

### 주요 기능

- **라이브러리**: axios, cheerio, dayjs, i18n, lodash, pangu. lodash 를 제외한 모두를 전역 객체로 바로 사용할 수도 있습니다.
- **Node 호환 심**: buffer, events, fs, os, path, process, nodejs. `require` 로 불러옵니다.
- **라이브러리별 스위치**: 플러그인 페이지에 라이브러리마다 스위치가 있으며 기본값은 모두 켜짐입니다. 꺼진 라이브러리는 `require` 할 수 없고 전역 객체도 사용할 수 없습니다.

### 스크립트에서 사용하기

아래 예제는 `require` 로 lodash 와 path 를 불러오고 전역 객체 dayjs 와 cheerio 를 바로 사용합니다:

```js
var _ = require('lodash');
var path = require('path');

console.log(dayjs(0).valueOf());
console.log(cheerio.load('<p>AutoJs6</p>')('p').text());
console.log(_.map([1, 2], function (x) { return x * 2; }).join());
console.log(path.isAbsolute('/sdcard/Scripts'));
```

플러그인을 사용할 수 없거나 라이브러리가 꺼져 있으면 `require` 가 오류를 던집니다. 다음과 같이 처리할 수 있습니다:

```js
try {
    var axios = require('axios');
} catch (e) {
    console.log(e.code, e.library, e.reason);
}
```

- 오류의 `code` 는 `MODULE_NOT_FOUND` 이고, `library` 에는 라이브러리 이름, `reason` 에는 원인이 들어 있습니다. 예를 들어 라이브러리가 꺼져 있으면 `library-disabled` 입니다.
- 스위치는 이후의 `require` 에만 영향을 주며, 이미 불러온 모듈에는 영향이 없습니다.
- `fs` 는 `buffer` 에, `nodejs` 는 `process` 와 `buffer` 에 의존합니다. 의존 대상 라이브러리를 끄면 그것에 의존하는 모듈도 불러올 수 없습니다.
- 이 플러그인은 AutoJs6 의 기본 JavaScript 스크립트를 위한 것입니다. Node.js 런타임 플러그인의 스크립트는 Node.js 에 내장된 모듈을 사용합니다.

### 요구 사항

- Android 7.0 이상, AutoJs6 6.8.0 이상.
- 이 플러그인은 리소스 패키지일 뿐이며 권한이 필요하지 않습니다. axios 의 네트워크 요청은 스크립트를 실행하는 AutoJs6 가 보냅니다.

### 자주 묻는 질문

**스크립트가 이 플러그인이 필요하다고 알릴 때**

AutoJs6 6.8.0 부터 이 라이브러리들은 AutoJs6 패키지에 포함되지 않습니다. 이 플러그인을 설치하고 플러그인 센터에서 활성화한 뒤, 플러그인 페이지에서 해당 라이브러리의 스위치가 켜져 있는지 확인하세요.

**기존 스크립트**

고칠 필요가 없습니다. `axios` 등의 전역 객체와 `require('axios')` 등의 호출이 그대로 동작하며, 라이브러리는 이 플러그인에서 불러옵니다.

각 라이브러리의 버전, 모듈 이름, 라이선스는 [프로젝트 페이지](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries) 를 참고하세요.
