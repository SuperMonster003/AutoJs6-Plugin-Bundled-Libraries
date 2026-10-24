<!--suppress HtmlDeprecatedAttribute, HttpUrlsUsage -->

<div align="center">
  <p>
    <img src="https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/app/src/main/res/mipmap/ic_launcher.png?raw=true" alt="autojs6-plugin-bundled-libraries-ic-launcher" border="0" width="128" />
  </p>

  <p>Библиотеки JavaScript и прослойки совместимости с Node для require в скриптах AutoJs6</p>

  <p>
    <a href="https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/releases"><img alt="GitHub release (latest by date)" src="https://img.shields.io/github/v/release/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries?label=Release"/></a>
    <a href="https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/issues"><img alt="GitHub closed issues" src="https://img.shields.io/github/issues/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries?color=A24232&label=Issues"/></a>
    <a href="https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/LICENSE"><img alt="GitHub License" src="https://img.shields.io/github/license/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries?color=534BAE&label=License"/></a>
  </p>
</div>

******

### Языки (Languages)

******

Текущий README.md поддерживает следующие языки:

- [简体中文 [zh-Hans]](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/.readme/README-zh-Hans.md)
- [繁體中文 (香港) [zh-Hant-HK]](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/.readme/README-zh-Hant-HK.md)
- [繁體中文 (台灣) [zh-Hant-TW]](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/.readme/README-zh-Hant-TW.md)
- [English [en]](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/.readme/README-en.md)
- [Français [fr]](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/.readme/README-fr.md)
- [Español [es]](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/.readme/README-es.md)
- [日本語 [ja]](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/.readme/README-ja.md)
- [한국어 [ko]](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/.readme/README-ko.md)
- Русский [ru] # текущий
- [العربية [ar]](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/.readme/README-ar.md)

******

### Введение

******

Bundled Libraries содержит 13 библиотек для скриптов, которые раньше были встроены в AutoJs6: axios, cheerio, dayjs, i18n, pangu и lodash, а также прослойки совместимости с Node buffer, events, fs, os, path, process и nodejs. Начиная с AutoJs6 6.8.0, пакет AutoJs6 их больше не содержит; если этот плагин установлен и включён, `require('axios')`, `require('fs')` и другие, а также глобальные объекты `axios`, `cheerio`, `dayjs`, `i18n` и `pangu` работают точно так же, как раньше.

Плагин - это только пакет ресурсов: AutoJs6 читает файлы библиотек прямо из пакета плагина, скрипты никогда не запускают процесс плагина, а сам плагин не требует разрешений на хранилище, сеть или что-либо ещё (сетевые запросы axios отправляет AutoJs6, который выполняет скрипт). Ненужные библиотеки можно по одной отключить на странице плагина.

******

### Включённые библиотеки

******

Имена модулей - это аргументы `require`; библиотеку с глобальным объектом можно использовать и без `require`. Происхождение и лицензии библиотек указаны в [THIRD_PARTY_NOTICES.md](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/THIRD_PARTY_NOTICES.md).

#### Библиотеки

| Библиотека | Модули | Глобальный объект | Версия | Лицензия |
|---|---|---|---|---|
| axios | `axios` | `axios` | - | MIT |
| cheerio | `cheerio` | `cheerio` | - | MIT |
| dayjs | `dayjs` | `dayjs` | - | MIT |
| i18n | `i18n`, `banana-i18n` | `i18n` | - | MIT |
| pangu | `pangu` | `pangu` | 10.1.0 | MIT |
| lodash | `lodash` | - | 4.17.15 | MIT |

#### Прослойки совместимости с Node

| Библиотека | Модули | Глобальный объект | Версия | Лицензия |
|---|---|---|---|---|
| buffer | `buffer` | - | - | MPL-2.0 |
| events | `events` | - | - | MPL-2.0 |
| fs | `fs` | - | - | MPL-2.0 |
| os | `os` | - | - | MPL-2.0 |
| path | `path` | - | - | MPL-2.0 |
| process | `process` | - | - | MPL-2.0 |
| nodejs | `nodejs` | - | - | MPL-2.0 |

******

### Использование

******

1. Используйте AutoJs6 6.8.0 (5935) или новее.
2. Установите Bundled Libraries из Центра плагинов AutoJs6 (или скачайте APK из [Releases](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/releases)) и убедитесь, что плагин включён.
3. В скрипте, как обычно, подключайте нужные библиотеки через `require` или используйте глобальные объекты напрямую, например:

```javascript
var _ = require('lodash');
var path = require('path');

console.log(dayjs(0).valueOf());
console.log(cheerio.load('<p>AutoJs6</p>')('p').text());
console.log(_.map([1, 2], function (x) { return x * 2; }).join());
console.log(path.isAbsolute('/sdcard/Scripts'));
```

> Есть только один универсальный пакет без нативных библиотек для устройств с Android 7.0 (API 24) или новее.

******

### Переключатели библиотек

******

На странице плагина (открывается из Центра плагинов AutoJs6 или значком Bundled Libraries на главном экране) для каждой библиотеки есть переключатель, по умолчанию все включены:

- Отключённую библиотеку больше нельзя подключить через `require`, и её глобальный объект недоступен; включите её снова, чтобы вернуть.
- Переключатель действует на следующий `require`: модули, уже загруженные выполняющимся скриптом, остаются до его завершения.
- `fs` требует `buffer`, а `nodejs` требует `process` и `buffer`: если `buffer` или `process` отключены, зависящие от них модули тоже не загрузятся.
- "История выпусков" внизу страницы показывает изменения каждой версии.

******

### Когда плагин недоступен

******

Если плагин не установлен, не авторизован, отключён в Центре плагинов или несовместим, либо библиотека отключена, `require` этой библиотеки и чтение её глобального объекта выбрасывают ошибку:

- Ошибка - это `Error` с `code` `MODULE_NOT_FOUND`; её сообщение говорит, нужно ли установить плагин или где его включить, а скрипт может обработать её через `try...catch`.
- У объекта ошибки есть также `library` (имя библиотеки) и `reason` (`not-installed`, `unauthorized`, `plugin-disabled`, `library-disabled` или `incompatible`).
- Изменение состояния плагина никогда не прерывает выполняющийся скрипт и влияет только на последующие вызовы `require`; другие встроенные модули AutoJs6 и собственные файлы скрипта не затрагиваются.

******

### Совместимость

******

- Требуется AutoJs6 6.8.0 (сборка 5935) или новее; более ранние версии AutoJs6 содержат эти библиотеки сами и в плагине не нуждаются.
- Поддерживается Android 7.0 (API 24) или новее, один пакет для всех архитектур процессора.
- Файлы библиотек полностью совпадают с теми, что раньше были встроены в AutoJs6, поэтому существующие скрипты менять не нужно.
- Плагин обслуживает обычные JavaScript-скрипты AutoJs6; скрипты плагина среды выполнения Node.js используют модули самого Node.js.

******

### Частые Вопросы

******

#### Почему AutoJs6 больше не содержит эти библиотеки?

Ими пользуется лишь часть скриптов. В виде плагина они не увеличивают пакет AutoJs6, плагин ставят только те, кому он нужен, и включёнными можно оставить только используемые библиотеки. Файлы в плагине те же, что были встроены.

#### Мой скрипт использует глобальный объект `axios` напрямую. Нужно ли переходить на `require`?

Нет. Глобальные объекты `axios`, `cheerio`, `dayjs`, `i18n` и `pangu` - это те же модули, что возвращает `require`, и оба способа загружают их из этого плагина.

#### Плагин выходит в сеть или работает в фоне?

Нет. У плагина нет разрешения на сеть, а запуск скрипта никогда не запускает процесс плагина. Сетевые запросы axios отправляет AutoJs6, который выполняет скрипт.

#### Ошибка сообщает, что плагин нужно авторизовать. Что делать?

Выпущенная версия, установленная из Центра плагинов AutoJs6 или из Releases этого проекта, авторизации не требует. Собственная сборка имеет другую подпись, и её сначала нужно авторизовать в Центре плагинов по подсказке.

******

### Интерфейс Плагина

******

Следующая информация предназначена разработчикам хоста AutoJs6 и плагинов; хост использует эти идентификаторы для обнаружения плагина и чтения файлов библиотек:

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

### Дорожная Карта

******

Контрольный список перед выпуском и шаги выпуска ведутся в ROADMAP.md; соответствующие решения находятся в `docs/augment/ROADMAP.md` репозитория AutoJs6.

- [Открыть ROADMAP.md](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/ROADMAP.md)

******

### История Выпусков

******

#### v1.0.0

_2026/10/24_

- `Подсказка` Требуется AutoJs6 6.8.0 (5935) или новее
- `Подсказка` Начиная с AutoJs6 6.8.0, перечисленные ниже библиотеки больше не входят в AutoJs6: с установленным плагином require и глобальные объекты работают как раньше, и существующие скрипты менять не нужно; без плагина или при отключённом плагине require этих библиотек выдаёт ошибку с пояснением, как его установить или включить
- `Функция` axios, cheerio, dayjs, i18n (вместе с banana-i18n), pangu и lodash для require; axios, cheerio, dayjs, i18n и pangu доступны и как глобальные объекты
- `Функция` Прослойки совместимости с Node buffer, events, fs, os, path, process и nodejs для require
- `Функция` Страница плагина с переключателем для каждой библиотеки (по умолчанию все включены) и историей выпусков; отключённая библиотека недоступна для последующих require
- `Функция` Страница плагина следует языку, ночному режиму и цвету темы AutoJs6, а если AutoJs6 не установлен или его настройки не удаётся прочитать, то системе
- `Зависимость` Добавлен AutoJs6 Common Plugin API

##### Больше истории выпусков

* [CHANGELOG.md](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/app/src/main/assets/doc/CHANGELOG-ru.md)

******

### Сборка

******

Этот раздел предназначен разработчикам, желающим собрать плагин из исходников.

```powershell
.\gradlew.bat :app:assembleDebug :app:testDebugUnitTest
py .python\generate_markdown.py --check
py .python\generate_icon_studio.py --check
py .python\check_repository.py
```

******

### Лицензия

******

Код плагина и модули, написанные для AutoJs6 (прослойки совместимости с Node, обёртка i18n и адаптер axios), распространяются по [Mozilla Public License 2.0](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/LICENSE). Включённые сторонние библиотеки сохраняют свои лицензии (MIT и другие), см. [THIRD_PARTY_NOTICES.md](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/THIRD_PARTY_NOTICES.md).

******

### Ссылки

******

- Проект AutoJs6: https://github.com/SuperMonster003/AutoJs6
- Сторонние уведомления: https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/THIRD_PARTY_NOTICES.md
