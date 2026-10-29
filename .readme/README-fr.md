<!--suppress HtmlDeprecatedAttribute, HttpUrlsUsage -->

<div align="center">
  <p>
    <img src="https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/app/src/main/res/mipmap/ic_launcher.png?raw=true" alt="autojs6-plugin-bundled-libraries-ic-launcher" border="0" width="128" />
  </p>

  <p>Bibliothèques JavaScript et couches de compatibilité Node pour require dans les scripts AutoJs6</p>

  <p>
    <a href="https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/releases"><img alt="GitHub release (latest by date)" src="https://img.shields.io/github/v/release/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries?label=Release"/></a>
    <a href="https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/issues"><img alt="GitHub closed issues" src="https://img.shields.io/github/issues/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries?color=A24232&label=Issues"/></a>
    <a href="https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/LICENSE"><img alt="GitHub License" src="https://img.shields.io/github/license/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries?color=534BAE&label=License"/></a>
  </p>
</div>

******

### Langues (Languages)

******

Le README.md actuel prend en charge les langues suivantes:

- [简体中文 [zh-Hans]](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/.readme/README-zh-Hans.md)
- [繁體中文 (香港) [zh-Hant-HK]](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/.readme/README-zh-Hant-HK.md)
- [繁體中文 (台灣) [zh-Hant-TW]](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/.readme/README-zh-Hant-TW.md)
- [English [en]](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/.readme/README-en.md)
- Français [fr] # actuel
- [Español [es]](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/.readme/README-es.md)
- [日本語 [ja]](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/.readme/README-ja.md)
- [한국어 [ko]](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/.readme/README-ko.md)
- [Русский [ru]](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/.readme/README-ru.md)
- [العربية [ar]](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/.readme/README-ar.md)

******

### Introduction

******

Bundled Libraries regroupe les 13 bibliothèques de script autrefois intégrées à AutoJs6: axios, cheerio, dayjs, i18n, pangu et lodash, ainsi que les couches de compatibilité Node buffer, events, fs, os, path, process et nodejs. À partir d'AutoJs6 6.8.0, le paquet d'AutoJs6 ne les contient plus; une fois ce plugin installé et activé, `require('axios')`, `require('fs')` et les autres, ainsi que les objets globaux `axios`, `cheerio`, `dayjs`, `i18n` et `pangu`, fonctionnent exactement comme avant.

Le plugin n'est qu'un paquet de ressources: AutoJs6 lit les fichiers des bibliothèques directement dans le paquet du plugin, les scripts ne démarrent jamais le processus du plugin et le plugin n'a besoin d'aucune autorisation de stockage, de réseau ou autre (les requêtes réseau d'axios sont envoyées par AutoJs6, qui exécute le script). Les bibliothèques inutiles peuvent être désactivées une par une sur la page du plugin.

******

### Bibliothèques incluses

******

Les noms de module sont les arguments de `require`; une bibliothèque qui a un objet global peut aussi s'utiliser sans `require`. L'origine et la licence de chaque bibliothèque figurent dans [THIRD_PARTY_NOTICES.md](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/THIRD_PARTY_NOTICES.md).

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

******

### Utilisation

******

1. Utilisez AutoJs6 6.8.0 (build 5935) ou ultérieur.
2. Installez Bundled Libraries depuis le Centre des plugins d'AutoJs6 (ou téléchargez l'APK depuis [Releases](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/releases)) et vérifiez que le plugin est activé.
3. Utilisez `require` pour les bibliothèques nécessaires comme d'habitude, ou directement les objets globaux, par exemple:

```javascript
var _ = require('lodash');
var path = require('path');

console.log(dayjs(0).valueOf());
console.log(cheerio.load('<p>AutoJs6</p>')('p').text());
console.log(_.map([1, 2], function (x) { return x * 2; }).join());
console.log(path.isAbsolute('/sdcard/Scripts'));
```

> Il n'existe qu'un seul paquet universel, sans bibliothèque native, pour les appareils sous Android 7.0 (API 24) ou ultérieur.

******

### Activation par bibliothèque

******

La page du plugin (ouverte depuis le Centre des plugins d'AutoJs6 ou l'icône Bundled Libraries de l'écran d'accueil) propose un interrupteur par bibliothèque, tous activés par défaut:

- Une bibliothèque désactivée ne peut plus être chargée par `require` et son objet global n'est plus utilisable; réactivez-la pour la retrouver.
- Un interrupteur s'applique au `require` suivant: les modules déjà chargés par un script en cours restent disponibles jusqu'à la fin du script.
- `fs` a besoin de `buffer`, et `nodejs` de `process` et de `buffer`: si `buffer` ou `process` est désactivé, les modules qui en dépendent ne peuvent pas se charger non plus.
- "Historique des versions", en bas de la page, liste les changements de chaque version.

******

### Quand le plugin est indisponible

******

Quand le plugin n'est pas installé, n'est pas autorisé, est désactivé dans le Centre des plugins ou n'est pas compatible, ou qu'une bibliothèque est désactivée, `require` de cette bibliothèque et la lecture de son objet global lèvent une erreur:

- L'erreur est une `Error` dont le `code` vaut `MODULE_NOT_FOUND`; son message indique s'il faut installer le plugin ou où l'activer, et les scripts peuvent la traiter avec `try...catch`.
- L'objet d'erreur porte aussi `library` (le nom de la bibliothèque) et `reason` (`not-installed`, `unauthorized`, `plugin-disabled`, `library-disabled` ou `incompatible`).
- Un changement d'état du plugin n'interrompt jamais un script en cours, il ne concerne que les `require` suivants; les autres modules intégrés d'AutoJs6 et les fichiers propres au script ne sont pas concernés.

******

### Compatibilité

******

- Nécessite AutoJs6 6.8.0 (build 5935) ou ultérieur; les versions antérieures d'AutoJs6 intègrent ces bibliothèques et n'ont pas besoin du plugin.
- Prend en charge Android 7.0 (API 24) ou ultérieur, avec un seul paquet pour toutes les architectures de processeur.
- Les fichiers des bibliothèques sont identiques à ceux autrefois intégrés à AutoJs6: les scripts existants n'ont rien à changer.
- Le plugin sert les scripts JavaScript par défaut d'AutoJs6; les scripts du plugin d'exécution Node.js utilisent les modules de Node.js lui-même.

******

### FAQ

******

#### Pourquoi AutoJs6 n'intègre-t-il plus ces bibliothèques?

Seule une partie des scripts les utilise. Sous forme de plugin, elles allègent le paquet d'AutoJs6, seuls ceux qui en ont besoin installent le plugin, et seules les bibliothèques utilisées restent activées. Les fichiers du plugin sont les mêmes que ceux qui étaient intégrés.

#### Mon script utilise directement l'objet global `axios`. Dois-je passer à `require`?

Non. Les objets globaux `axios`, `cheerio`, `dayjs`, `i18n` et `pangu` sont les mêmes modules que ceux renvoyés par `require`, et tous sont chargés depuis ce plugin.

#### Le plugin se connecte-t-il à Internet ou tourne-t-il en arrière-plan?

Non. Le plugin n'a pas d'autorisation réseau et l'exécution d'un script ne démarre jamais son processus. Les requêtes réseau d'axios sont envoyées par AutoJs6, qui exécute le script.

#### L'erreur indique que le plugin doit être autorisé. Que faire?

La version publiée, installée depuis le Centre des plugins d'AutoJs6 ou depuis les Releases de ce projet, n'a besoin d'aucune autorisation. Une version compilée soi-même a une autre signature et doit d'abord être autorisée dans le Centre des plugins, comme indiqué.

******

### Interface Du Plugin

******

Les informations suivantes s'adressent aux développeurs de l'hôte AutoJs6 et de plugins; l'hôte utilise ces identifiants pour découvrir le plugin et lire les fichiers des bibliothèques:

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

### Feuille De Route

******

La liste de contrôle avant publication et les étapes de publication sont suivies dans ROADMAP.md; les décisions correspondantes figurent dans `docs/augment/ROADMAP.md` du dépôt AutoJs6.

- [Voir ROADMAP.md](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/ROADMAP.md)

******

### Historique Des Versions

******

#### v1.0.0

_2026/10/29_

- `Note` Nécessite AutoJs6 6.8.0 (build 5935) ou ultérieur
- `Note` À partir d'AutoJs6 6.8.0, les bibliothèques ci-dessous ne sont plus fournies avec AutoJs6: avec ce plugin installé, require et les objets globaux fonctionnent comme avant et les scripts existants n'ont rien à changer; sans le plugin, ou s'il est désactivé, require de ces bibliothèques renvoie une erreur qui indique comment l'installer ou l'activer
- `Fonctionnalité` axios, cheerio, dayjs, i18n (avec banana-i18n), pangu et lodash pour require, axios, cheerio, dayjs, i18n et pangu étant aussi disponibles comme objets globaux
- `Fonctionnalité` Couches de compatibilité Node buffer, events, fs, os, path, process et nodejs pour require
- `Fonctionnalité` Page du plugin avec un interrupteur par bibliothèque (tous activés par défaut) et l'historique des versions; une bibliothèque désactivée n'est plus disponible pour les require suivants
- `Fonctionnalité` La page du plugin suit la langue, le mode nuit et la couleur du thème d'AutoJs6, et le système quand AutoJs6 n'est pas installé ou que ses réglages ne peuvent pas être lus
- `Dépendance` Ajout de AutoJs6 Common Plugin API

##### Pour plus d'historique des versions

* [CHANGELOG.md](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/app/src/main/assets/doc/CHANGELOG-fr.md)

******

### Compilation

******

Cette section s'adresse aux développeurs souhaitant compiler le plugin depuis les sources.

```powershell
.\gradlew.bat :app:assembleDebug :app:testDebugUnitTest
py .python\generate_markdown.py --check
py .python\generate_icon_studio.py --check
py .python\check_repository.py
```

******

### Licence

******

Le code du plugin et les modules écrits pour AutoJs6 (les couches de compatibilité Node, l'enveloppe i18n et l'adaptateur axios) sont distribués sous la [Mozilla Public License 2.0](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/LICENSE). Les bibliothèques tierces incluses conservent leurs propres licences (MIT et autres), voir [THIRD_PARTY_NOTICES.md](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/THIRD_PARTY_NOTICES.md).

******

### Liens

******

- Projet AutoJs6: https://github.com/SuperMonster003/AutoJs6
- Mentions des tiers: https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/THIRD_PARTY_NOTICES.md
