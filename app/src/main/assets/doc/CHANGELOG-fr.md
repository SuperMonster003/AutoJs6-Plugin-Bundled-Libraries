******

### Historique Des Versions

******

# v1.0.1

###### 2026/11/20

* `Amélioration` La page d'historique des versions se développe ou se réduit désormais par version: une version réduite affiche sa date et le nombre d'entrées par catégorie, une version développée ne nomme chaque catégorie qu'une fois; toutes les versions peuvent être développées ou réduites d'un coup, la plus récente s'ouvre développée et le sous-titre affiche le nom du plugin
* `Amélioration` Réécriture des instructions affichées dans le Centre des plugins en cinq parties: prise en main, fonctionnalités principales, utilisation dans les scripts, prérequis et questions fréquentes
* `Dépendance` Ajout de Jetpack Compose BOM 2026.09.00 (page Historique des versions)
* `Dépendance` Mise à niveau des plugins de compilation partagés version 1.9.0 -> 1.9.7

# v1.0.0

###### 2026/10/29

* `Note` Nécessite AutoJs6 6.8.0 (build 5935) ou ultérieur
* `Note` À partir d'AutoJs6 6.8.0, les bibliothèques ci-dessous ne sont plus fournies avec AutoJs6: avec ce plugin installé, require et les objets globaux fonctionnent comme avant et les scripts existants n'ont rien à changer; sans le plugin, ou s'il est désactivé, require de ces bibliothèques renvoie une erreur qui indique comment l'installer ou l'activer
* `Fonctionnalité` axios, cheerio, dayjs, i18n (avec banana-i18n), pangu et lodash pour require, axios, cheerio, dayjs, i18n et pangu étant aussi disponibles comme objets globaux
* `Fonctionnalité` Couches de compatibilité Node buffer, events, fs, os, path, process et nodejs pour require
* `Fonctionnalité` Page du plugin avec un interrupteur par bibliothèque (tous activés par défaut) et l'historique des versions; une bibliothèque désactivée n'est plus disponible pour les require suivants
* `Fonctionnalité` La page du plugin suit la langue, le mode nuit et la couleur du thème d'AutoJs6, et le système quand AutoJs6 n'est pas installé ou que ses réglages ne peuvent pas être lus
* `Dépendance` Ajout de AutoJs6 Common Plugin API
