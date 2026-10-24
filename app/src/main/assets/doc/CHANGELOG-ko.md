******

### 릴리스 기록

******

# v1.0.0

###### 2026/10/24

* `힌트` AutoJs6 6.8.0 (5935) 이상이 필요합니다
* `힌트` AutoJs6 6.8.0 부터 아래 라이브러리는 AutoJs6 에 포함되지 않습니다. 이 플러그인을 설치하면 require 와 전역 객체를 예전처럼 사용할 수 있어 기존 스크립트를 수정할 필요가 없습니다. 플러그인이 없거나 비활성화되어 있으면 이 라이브러리를 require 할 때 설치 또는 활성화 방법을 알려 주는 오류가 발생합니다
* `기능` axios, cheerio, dayjs, i18n (banana-i18n 포함), pangu, lodash 를 require 로 사용 가능, 그중 axios, cheerio, dayjs, i18n, pangu 는 전역 객체로도 사용 가능
* `기능` Node 호환 심 buffer, events, fs, os, path, process, nodejs 를 require 로 사용 가능
* `기능` 플러그인 페이지에서 라이브러리별 켜기 / 끄기 (기본값 모두 켜짐) 와 릴리스 기록 보기, 끈 라이브러리는 이후 require 에서 사용 불가
* `기능` 플러그인 페이지는 AutoJs6 의 언어, 야간 모드, 테마 색상을 따름, AutoJs6 이 설치되지 않았거나 설정을 읽을 수 없으면 시스템을 따름
* `의존성` AutoJs6 Common Plugin API 추가
