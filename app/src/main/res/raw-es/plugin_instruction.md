Bundled Libraries reúne las 13 bibliotecas de script que antes venían integradas en AutoJs6: axios, cheerio, dayjs, i18n, pangu y lodash, además de las capas de compatibilidad con Node buffer, events, fs, os, path, process y nodejs. A partir de AutoJs6 6.8.0, el paquete de AutoJs6 ya no las incluye; con este plugin instalado y activado, `require('axios')`, `require('fs')` y similares, así como los objetos globales `axios`, `cheerio`, `dayjs`, `i18n` y `pangu`, siguen funcionando exactamente igual que antes.

El plugin es solo un paquete de recursos: AutoJs6 lee los archivos de las bibliotecas directamente del paquete del plugin, los scripts nunca inician el proceso del plugin y el plugin no necesita permisos de almacenamiento, red ni de otro tipo (las solicitudes de red de axios las envía AutoJs6, que ejecuta el script). Las bibliotecas que no necesite se pueden desactivar una a una en la página del plugin.

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

### Interruptores por biblioteca

La página del plugin (que se abre desde el Centro de plugins de AutoJs6 o con el icono Bundled Libraries de la pantalla de inicio) tiene un interruptor por biblioteca, todos activados de forma predeterminada:

- Una biblioteca desactivada ya no se puede cargar con `require` y su objeto global no se puede usar; vuelva a activarla para recuperarla.
- Un interruptor se aplica al siguiente `require`: los módulos que un script en ejecución ya cargó se mantienen hasta que termina el script.
- `fs` necesita `buffer`, y `nodejs` necesita `process` y `buffer`: con `buffer` o `process` desactivado, los módulos que dependen de ellos tampoco se pueden cargar.
- "Historial de versiones", al final de la página, muestra los cambios de cada versión.

### Cuando el plugin no está disponible

Cuando el plugin no está instalado, no está autorizado, está desactivado en el Centro de plugins o no es compatible, o una biblioteca está desactivada, `require` de esa biblioteca y la lectura de su objeto global lanzan un error:

- El error es un `Error` con `code` `MODULE_NOT_FOUND`; su mensaje indica si hay que instalar el plugin o dónde activarlo, y los scripts pueden tratarlo con `try...catch`.
- El objeto de error también tiene `library` (el nombre de la biblioteca) y `reason` (`not-installed`, `unauthorized`, `plugin-disabled`, `library-disabled` o `incompatible`).
- Un cambio de estado del plugin nunca interrumpe un script en ejecución, solo afecta a los `require` posteriores; los demás módulos integrados de AutoJs6 y los archivos propios del script no se ven afectados.

El código fuente, el historial de versiones y las licencias de terceros están en la [página del proyecto](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries).
