<!--suppress HtmlDeprecatedAttribute, HttpUrlsUsage -->

<div align="center">
  <p>
    <img src="https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/app/src/main/res/mipmap/ic_launcher.png?raw=true" alt="autojs6-plugin-bundled-libraries-ic-launcher" border="0" width="128" />
  </p>

  <p>Bibliotecas de JavaScript y capas de compatibilidad con Node para require en scripts de AutoJs6</p>

  <p>
    <a href="https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/releases"><img alt="GitHub release (latest by date)" src="https://img.shields.io/github/v/release/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries?label=Release"/></a>
    <a href="https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/issues"><img alt="GitHub closed issues" src="https://img.shields.io/github/issues/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries?color=A24232&label=Issues"/></a>
    <a href="https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/LICENSE"><img alt="GitHub License" src="https://img.shields.io/github/license/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries?color=534BAE&label=License"/></a>
  </p>
</div>

******

### Idiomas (Languages)

******

El README.md actual admite los siguientes idiomas:

- [简体中文 [zh-Hans]](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/.readme/README-zh-Hans.md)
- [繁體中文 (香港) [zh-Hant-HK]](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/.readme/README-zh-Hant-HK.md)
- [繁體中文 (台灣) [zh-Hant-TW]](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/.readme/README-zh-Hant-TW.md)
- [English [en]](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/.readme/README-en.md)
- [Français [fr]](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/.readme/README-fr.md)
- Español [es] # actual
- [日本語 [ja]](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/.readme/README-ja.md)
- [한국어 [ko]](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/.readme/README-ko.md)
- [Русский [ru]](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/.readme/README-ru.md)
- [العربية [ar]](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/.readme/README-ar.md)

******

### Introducción

******

Bundled Libraries reúne las 13 bibliotecas de script que antes venían integradas en AutoJs6: axios, cheerio, dayjs, i18n, pangu y lodash, además de las capas de compatibilidad con Node buffer, events, fs, os, path, process y nodejs. A partir de AutoJs6 6.8.0, el paquete de AutoJs6 ya no las incluye; con este plugin instalado y activado, `require('axios')`, `require('fs')` y similares, así como los objetos globales `axios`, `cheerio`, `dayjs`, `i18n` y `pangu`, siguen funcionando exactamente igual que antes.

El plugin es solo un paquete de recursos: AutoJs6 lee los archivos de las bibliotecas directamente del paquete del plugin, los scripts nunca inician el proceso del plugin y el plugin no necesita permisos de almacenamiento, red ni de otro tipo (las solicitudes de red de axios las envía AutoJs6, que ejecuta el script). Las bibliotecas que no necesite se pueden desactivar una a una en la página del plugin.

******

### Bibliotecas incluidas

******

Los nombres de módulo son los argumentos de `require`; una biblioteca con objeto global también se puede usar sin `require`. El origen y la licencia de cada biblioteca están en [THIRD_PARTY_NOTICES.md](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/THIRD_PARTY_NOTICES.md).

#### Bibliotecas

| Biblioteca | Módulos | Objeto global | Versión | Licencia |
|---|---|---|---|---|
| axios | `axios` | `axios` | - | MIT |
| cheerio | `cheerio` | `cheerio` | - | MIT |
| dayjs | `dayjs` | `dayjs` | - | MIT |
| i18n | `i18n`, `banana-i18n` | `i18n` | - | MIT |
| pangu | `pangu` | `pangu` | 10.1.0 | MIT |
| lodash | `lodash` | - | 4.17.15 | MIT |

#### Capas de compatibilidad con Node

| Biblioteca | Módulos | Objeto global | Versión | Licencia |
|---|---|---|---|---|
| buffer | `buffer` | - | - | MPL-2.0 |
| events | `events` | - | - | MPL-2.0 |
| fs | `fs` | - | - | MPL-2.0 |
| os | `os` | - | - | MPL-2.0 |
| path | `path` | - | - | MPL-2.0 |
| process | `process` | - | - | MPL-2.0 |
| nodejs | `nodejs` | - | - | MPL-2.0 |

******

### Uso

******

1. Use AutoJs6 6.8.0 (compilación 5935) o posterior.
2. Instale Bundled Libraries desde el Centro de plugins de AutoJs6 (o descargue el APK desde [Releases](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/releases)) y compruebe que el plugin está activado.
3. Use `require` con las bibliotecas que necesite como siempre, o use directamente los objetos globales, por ejemplo:

```javascript
var _ = require('lodash');
var path = require('path');

console.log(dayjs(0).valueOf());
console.log(cheerio.load('<p>AutoJs6</p>')('p').text());
console.log(_.map([1, 2], function (x) { return x * 2; }).join());
console.log(path.isAbsolute('/sdcard/Scripts'));
```

> Hay un único paquete universal, sin bibliotecas nativas, para dispositivos con Android 7.0 (API 24) o posterior.

******

### Interruptores por biblioteca

******

La página del plugin (que se abre desde el Centro de plugins de AutoJs6 o con el icono Bundled Libraries de la pantalla de inicio) tiene un interruptor por biblioteca, todos activados de forma predeterminada:

- Una biblioteca desactivada ya no se puede cargar con `require` y su objeto global no se puede usar; vuelva a activarla para recuperarla.
- Un interruptor se aplica al siguiente `require`: los módulos que un script en ejecución ya cargó se mantienen hasta que termina el script.
- `fs` necesita `buffer`, y `nodejs` necesita `process` y `buffer`: con `buffer` o `process` desactivado, los módulos que dependen de ellos tampoco se pueden cargar.
- "Historial de versiones", al final de la página, muestra los cambios de cada versión.

******

### Cuando el plugin no está disponible

******

Cuando el plugin no está instalado, no está autorizado, está desactivado en el Centro de plugins o no es compatible, o una biblioteca está desactivada, `require` de esa biblioteca y la lectura de su objeto global lanzan un error:

- El error es un `Error` con `code` `MODULE_NOT_FOUND`; su mensaje indica si hay que instalar el plugin o dónde activarlo, y los scripts pueden tratarlo con `try...catch`.
- El objeto de error también tiene `library` (el nombre de la biblioteca) y `reason` (`not-installed`, `unauthorized`, `plugin-disabled`, `library-disabled` o `incompatible`).
- Un cambio de estado del plugin nunca interrumpe un script en ejecución, solo afecta a los `require` posteriores; los demás módulos integrados de AutoJs6 y los archivos propios del script no se ven afectados.

******

### Compatibilidad

******

- Requiere AutoJs6 6.8.0 (compilación 5935) o posterior; las versiones anteriores de AutoJs6 incluyen estas bibliotecas y no necesitan el plugin.
- Compatible con Android 7.0 (API 24) o posterior, con un solo paquete para todas las arquitecturas de procesador.
- Los archivos de las bibliotecas son idénticos a los que venían integrados en AutoJs6, así que los scripts existentes no necesitan cambios.
- El plugin sirve a los scripts JavaScript predeterminados de AutoJs6; los scripts del plugin de entorno de ejecución Node.js usan los módulos del propio Node.js.

******

### Preguntas Frecuentes

******

#### Por qué AutoJs6 ya no incluye estas bibliotecas?

Solo algunos scripts las usan. Como plugin, el paquete de AutoJs6 es más pequeño, solo quien las necesita instala el plugin y solo las bibliotecas en uso tienen que estar activadas. Los archivos del plugin son los mismos que los integrados.

#### Mi script usa directamente el objeto global `axios`. Tengo que cambiar a `require`?

No. Los objetos globales `axios`, `cheerio`, `dayjs`, `i18n` y `pangu` son los mismos módulos que devuelve `require`, y ambos los cargan desde este plugin.

#### El plugin se conecta a Internet o se ejecuta en segundo plano?

No. El plugin no tiene permiso de red y ejecutar un script nunca inicia el proceso del plugin. Las solicitudes de red de axios las envía AutoJs6, que ejecuta el script.

#### El error dice que hay que autorizar el plugin. Qué hago?

La versión publicada, instalada desde el Centro de plugins de AutoJs6 o desde las Releases de este proyecto, no necesita autorización. Una compilación propia tiene otra firma y primero hay que autorizarla en el Centro de plugins según las indicaciones.

******

### Interfaz Del Plugin

******

La siguiente información está dirigida a los desarrolladores del host AutoJs6 y de plugins; el host usa estos identificadores para descubrir el plugin y leer los archivos de las bibliotecas:

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

### Hoja De Ruta

******

La lista de comprobación previa a la publicación y los pasos de publicación se registran en ROADMAP.md; las decisiones correspondientes están en `docs/augment/ROADMAP.md` del repositorio de AutoJs6.

- [Ver ROADMAP.md](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/ROADMAP.md)

******

### Historial De Versiones

******

#### v1.0.0

_2026/10/29_

- `Aviso` Requiere AutoJs6 6.8.0 (compilación 5935) o posterior
- `Aviso` A partir de AutoJs6 6.8.0, las bibliotecas siguientes ya no vienen con AutoJs6: con este plugin instalado, require y los objetos globales funcionan como antes y los scripts existentes no necesitan cambios; sin el plugin, o con él desactivado, require de estas bibliotecas da un error que indica cómo instalarlo o activarlo
- `Función` axios, cheerio, dayjs, i18n (con banana-i18n), pangu y lodash para require; axios, cheerio, dayjs, i18n y pangu también están disponibles como objetos globales
- `Función` Capas de compatibilidad con Node buffer, events, fs, os, path, process y nodejs para require
- `Función` Página del plugin con un interruptor por biblioteca (todos activados de forma predeterminada) y el historial de versiones; una biblioteca desactivada no está disponible para los require posteriores
- `Función` La página del plugin sigue el idioma, el modo nocturno y el color del tema de AutoJs6, y el sistema cuando AutoJs6 no está instalado o no se pueden leer sus ajustes
- `Dependencia` Se agrega AutoJs6 Common Plugin API

##### Para más historial de versiones

* [CHANGELOG.md](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/app/src/main/assets/doc/CHANGELOG-es.md)

******

### Compilación

******

Esta sección está dirigida a desarrolladores que quieran compilar el plugin desde el código fuente.

```powershell
.\gradlew.bat :app:assembleDebug :app:testDebugUnitTest
py .python\generate_markdown.py --check
py .python\generate_icon_studio.py --check
py .python\check_repository.py
```

******

### Licencia

******

El código del plugin y los módulos escritos para AutoJs6 (las capas de compatibilidad con Node, el envoltorio de i18n y el adaptador de axios) se distribuyen bajo la [Mozilla Public License 2.0](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/LICENSE). Las bibliotecas de terceros incluidas conservan sus propias licencias (MIT y otras), consulte [THIRD_PARTY_NOTICES.md](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/THIRD_PARTY_NOTICES.md).

******

### Enlaces

******

- Proyecto AutoJs6: https://github.com/SuperMonster003/AutoJs6
- Avisos de terceros: https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/THIRD_PARTY_NOTICES.md
