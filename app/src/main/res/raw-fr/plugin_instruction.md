Bundled Libraries fournit 13 bibliothèques aux scripts AutoJs6: des bibliothèques JavaScript comme axios, cheerio et dayjs, ainsi que des couches de compatibilité Node comme fs et path. Une fois le plugin activé, `require('axios')` et les autres, ainsi que les objets globaux comme `dayjs`, fonctionnent comme d'habitude: les scripts existants n'ont pas besoin d'être modifiés.

### Prise en main

1. Vérifiez que ce plugin est activé dans le Centre des plugins d'AutoJs6.
2. Dans un script, chargez une bibliothèque avec `require`, ou utilisez directement les objets globaux `axios`, `cheerio`, `dayjs`, `i18n` et `pangu`; voir les exemples ci-dessous.
3. Les bibliothèques inutiles peuvent être désactivées sur la page du plugin, qui s'ouvre depuis le Centre des plugins ou l'icône Bundled Libraries de l'écran d'accueil.

### Fonctionnalités principales

- **Bibliothèques**: axios, cheerio, dayjs, i18n, lodash et pangu; toutes sauf lodash s'utilisent aussi directement comme objets globaux.
- **Couches de compatibilité Node**: buffer, events, fs, os, path, process et nodejs, chargées avec `require`.
- **Interrupteur par bibliothèque**: la page du plugin propose un interrupteur par bibliothèque, tous activés par défaut; une bibliothèque désactivée ne peut pas être chargée avec `require`, et son objet global n'est plus utilisable non plus.

### Utilisation dans les scripts

L'exemple ci-dessous charge lodash et path avec `require` et utilise directement les objets globaux dayjs et cheerio:

```js
var _ = require('lodash');
var path = require('path');

console.log(dayjs(0).valueOf());
console.log(cheerio.load('<p>AutoJs6</p>')('p').text());
console.log(_.map([1, 2], function (x) { return x * 2; }).join());
console.log(path.isAbsolute('/sdcard/Scripts'));
```

Quand le plugin n'est pas disponible ou qu'une bibliothèque est désactivée, `require` lève une erreur, que vous pouvez traiter ainsi:

```js
try {
    var axios = require('axios');
} catch (e) {
    console.log(e.code, e.library, e.reason);
}
```

- L'erreur a `code` égal à `MODULE_NOT_FOUND`, le nom de la bibliothèque dans `library` et la cause dans `reason`, par exemple `library-disabled` quand la bibliothèque est désactivée.
- Un interrupteur n'agit que sur les `require` suivants; les modules déjà chargés ne sont pas concernés.
- `fs` dépend de `buffer`, et `nodejs` dépend de `process` et de `buffer`; si une bibliothèque dont ils dépendent est désactivée, ils ne peuvent pas être chargés non plus.
- Ce plugin sert les scripts JavaScript par défaut d'AutoJs6; les scripts du plugin d'exécution Node.js utilisent les modules intégrés à Node.js.

### Prérequis

- Android 7.0 ou version ultérieure, et AutoJs6 6.8.0 ou version ultérieure.
- Ce plugin n'est qu'un paquet de ressources et ne demande aucune autorisation; les requêtes réseau d'axios sont envoyées par AutoJs6, qui exécute le script.

### Questions fréquentes

**Un script indique que ce plugin est nécessaire**

À partir d'AutoJs6 6.8.0, le paquet d'AutoJs6 ne contient plus ces bibliothèques. Installez ce plugin, activez-le dans le Centre des plugins et vérifiez que l'interrupteur de la bibliothèque est activé sur la page du plugin.

**Scripts existants**

Aucune modification n'est nécessaire. Les objets globaux comme `axios` et les appels comme `require('axios')` fonctionnent toujours, et les bibliothèques sont chargées depuis ce plugin.

La version, les noms de modules et la licence de chaque bibliothèque se trouvent sur la [page du projet](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries).
