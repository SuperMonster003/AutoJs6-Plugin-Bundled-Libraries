Bundled Libraries regroupe les 13 bibliothèques de script autrefois intégrées à AutoJs6: axios, cheerio, dayjs, i18n, pangu et lodash, ainsi que les couches de compatibilité Node buffer, events, fs, os, path, process et nodejs. À partir d'AutoJs6 6.8.0, le paquet d'AutoJs6 ne les contient plus; une fois ce plugin installé et activé, `require('axios')`, `require('fs')` et les autres, ainsi que les objets globaux `axios`, `cheerio`, `dayjs`, `i18n` et `pangu`, fonctionnent exactement comme avant.

Le plugin n'est qu'un paquet de ressources: AutoJs6 lit les fichiers des bibliothèques directement dans le paquet du plugin, les scripts ne démarrent jamais le processus du plugin et le plugin n'a besoin d'aucune autorisation de stockage, de réseau ou autre (les requêtes réseau d'axios sont envoyées par AutoJs6, qui exécute le script). Les bibliothèques inutiles peuvent être désactivées une par une sur la page du plugin.

#### Bibliothèques

| Bibliothèque | Modules | Objet global | Version | Licence |
|---|---|---|---|---|
| axios | `axios` | `axios` | - | MIT |
| cheerio | `cheerio` | `cheerio` | - | MIT |
| dayjs | `dayjs` | `dayjs` | - | MIT |
| i18n | `i18n`, `banana-i18n` | `i18n` | - | MIT |
| pangu | `pangu` | `pangu` | 10.1.0 | MIT |
| lodash | `lodash` | - | 4.17.15 | MIT |

#### Couches de compatibilité Node

| Bibliothèque | Modules | Objet global | Version | Licence |
|---|---|---|---|---|
| buffer | `buffer` | - | - | MPL-2.0 |
| events | `events` | - | - | MPL-2.0 |
| fs | `fs` | - | - | MPL-2.0 |
| os | `os` | - | - | MPL-2.0 |
| path | `path` | - | - | MPL-2.0 |
| process | `process` | - | - | MPL-2.0 |
| nodejs | `nodejs` | - | - | MPL-2.0 |

### Activation par bibliothèque

La page du plugin (ouverte depuis le Centre des plugins d'AutoJs6 ou l'icône Bundled Libraries de l'écran d'accueil) propose un interrupteur par bibliothèque, tous activés par défaut:

- Une bibliothèque désactivée ne peut plus être chargée par `require` et son objet global n'est plus utilisable; réactivez-la pour la retrouver.
- Un interrupteur s'applique au `require` suivant: les modules déjà chargés par un script en cours restent disponibles jusqu'à la fin du script.
- `fs` a besoin de `buffer`, et `nodejs` de `process` et de `buffer`: si `buffer` ou `process` est désactivé, les modules qui en dépendent ne peuvent pas se charger non plus.
- "Historique des versions", en bas de la page, liste les changements de chaque version.

### Quand le plugin est indisponible

Quand le plugin n'est pas installé, n'est pas autorisé, est désactivé dans le Centre des plugins ou n'est pas compatible, ou qu'une bibliothèque est désactivée, `require` de cette bibliothèque et la lecture de son objet global lèvent une erreur:

- L'erreur est une `Error` dont le `code` vaut `MODULE_NOT_FOUND`; son message indique s'il faut installer le plugin ou où l'activer, et les scripts peuvent la traiter avec `try...catch`.
- L'objet d'erreur porte aussi `library` (le nom de la bibliothèque) et `reason` (`not-installed`, `unauthorized`, `plugin-disabled`, `library-disabled` ou `incompatible`).
- Un changement d'état du plugin n'interrompt jamais un script en cours, il ne concerne que les `require` suivants; les autres modules intégrés d'AutoJs6 et les fichiers propres au script ne sont pas concernés.

Le code source, l'historique des versions et les licences tierces se trouvent sur la [page du projet](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries).
