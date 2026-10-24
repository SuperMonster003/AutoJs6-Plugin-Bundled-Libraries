******

### Historial De Versiones

******

# v1.0.0

###### 2026/10/24

* `Aviso` Requiere AutoJs6 6.8.0 (5935) o posterior
* `Aviso` A partir de AutoJs6 6.8.0, las bibliotecas siguientes ya no vienen con AutoJs6: con este plugin instalado, require y los objetos globales funcionan como antes y los scripts existentes no necesitan cambios; sin el plugin, o con él desactivado, require de estas bibliotecas da un error que indica cómo instalarlo o activarlo
* `Función` axios, cheerio, dayjs, i18n (con banana-i18n), pangu y lodash para require; axios, cheerio, dayjs, i18n y pangu también están disponibles como objetos globales
* `Función` Capas de compatibilidad con Node buffer, events, fs, os, path, process y nodejs para require
* `Función` Página del plugin con un interruptor por biblioteca (todos activados de forma predeterminada) y el historial de versiones; una biblioteca desactivada no está disponible para los require posteriores
* `Función` La página del plugin sigue el idioma, el modo nocturno y el color del tema de AutoJs6, y el sistema cuando AutoJs6 no está instalado o no se pueden leer sus ajustes
* `Dependencia` Se agrega AutoJs6 Common Plugin API
