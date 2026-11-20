Bundled Libraries ofrece 13 bibliotecas para los scripts de AutoJs6: bibliotecas JavaScript como axios, cheerio y dayjs, además de capas de compatibilidad con Node como fs y path. Una vez activado, `require('axios')` y similares, así como objetos globales como `dayjs`, funcionan como siempre, por lo que los scripts existentes no necesitan cambios.

### Primeros pasos

1. Compruebe que este plugin está activado en el Centro de plugins de AutoJs6.
2. En un script, cargue una biblioteca con `require` o use directamente los objetos globales `axios`, `cheerio`, `dayjs`, `i18n` y `pangu`; consulte los ejemplos más abajo.
3. Las bibliotecas que no necesite se pueden desactivar en la página del plugin, que se abre desde el Centro de plugins o con el icono Bundled Libraries de la pantalla de inicio.

### Funciones principales

- **Bibliotecas**: axios, cheerio, dayjs, i18n, lodash y pangu; todas salvo lodash también se pueden usar directamente como objetos globales.
- **Capas de compatibilidad con Node**: buffer, events, fs, os, path, process y nodejs, que se cargan con `require`.
- **Interruptor por biblioteca**: la página del plugin tiene un interruptor por biblioteca, todos activados de forma predeterminada; una biblioteca desactivada no se puede cargar con `require` y su objeto global tampoco se puede usar.

### Uso en scripts

El ejemplo siguiente carga lodash y path con `require` y usa directamente los objetos globales dayjs y cheerio:

```js
var _ = require('lodash');
var path = require('path');

console.log(dayjs(0).valueOf());
console.log(cheerio.load('<p>AutoJs6</p>')('p').text());
console.log(_.map([1, 2], function (x) { return x * 2; }).join());
console.log(path.isAbsolute('/sdcard/Scripts'));
```

Cuando el plugin no está disponible o una biblioteca está desactivada, `require` lanza un error, que puede tratar así:

```js
try {
    var axios = require('axios');
} catch (e) {
    console.log(e.code, e.library, e.reason);
}
```

- El error tiene `code` igual a `MODULE_NOT_FOUND`, el nombre de la biblioteca en `library` y la causa en `reason`, por ejemplo `library-disabled` cuando la biblioteca está desactivada.
- Un interruptor solo afecta a los `require` posteriores; los módulos ya cargados no se ven afectados.
- `fs` depende de `buffer`, y `nodejs` depende de `process` y `buffer`; si se desactiva una biblioteca de la que dependen, tampoco se pueden cargar.
- Este plugin sirve a los scripts JavaScript predeterminados de AutoJs6; los scripts del plugin de entorno de ejecución Node.js usan los módulos integrados en Node.js.

### Requisitos

- Android 7.0 o posterior, y AutoJs6 6.8.0 o posterior.
- Este plugin es solo un paquete de recursos y no necesita ningún permiso; las solicitudes de red de axios las envía AutoJs6, que ejecuta el script.

### Preguntas frecuentes

**Un script indica que necesita este plugin**

A partir de AutoJs6 6.8.0, el paquete de AutoJs6 ya no incluye estas bibliotecas. Instale este plugin, actívelo en el Centro de plugins y compruebe que el interruptor de la biblioteca está activado en la página del plugin.

**Scripts existentes**

No necesitan cambios. Los objetos globales como `axios` y las llamadas como `require('axios')` siguen funcionando, y las bibliotecas se cargan desde este plugin.

La versión, los nombres de módulo y la licencia de cada biblioteca están en la [página del proyecto](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries).
