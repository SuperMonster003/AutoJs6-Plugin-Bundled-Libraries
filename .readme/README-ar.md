<!--suppress HtmlDeprecatedAttribute, HttpUrlsUsage -->

<div align="center">
  <p>
    <img src="https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/app/src/main/res/mipmap/ic_launcher.png?raw=true" alt="autojs6-plugin-bundled-libraries-ic-launcher" border="0" width="128" />
  </p>

  <p>مكتبات JavaScript وطبقات توافق Node لاستخدامها مع require في سكربتات AutoJs6</p>

  <p>
    <a href="https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/releases"><img alt="GitHub release (latest by date)" src="https://img.shields.io/github/v/release/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries?label=Release"/></a>
    <a href="https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/issues"><img alt="GitHub closed issues" src="https://img.shields.io/github/issues/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries?color=A24232&label=Issues"/></a>
    <a href="https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/LICENSE"><img alt="GitHub License" src="https://img.shields.io/github/license/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries?color=534BAE&label=License"/></a>
  </p>
</div>

******

### اللغات (Languages)

******

يدعم README.md الحالي اللغات التالية:

- [简体中文 [zh-Hans]](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/.readme/README-zh-Hans.md)
- [繁體中文 (香港) [zh-Hant-HK]](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/.readme/README-zh-Hant-HK.md)
- [繁體中文 (台灣) [zh-Hant-TW]](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/.readme/README-zh-Hant-TW.md)
- [English [en]](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/.readme/README-en.md)
- [Français [fr]](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/.readme/README-fr.md)
- [Español [es]](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/.readme/README-es.md)
- [日本語 [ja]](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/.readme/README-ja.md)
- [한국어 [ko]](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/.readme/README-ko.md)
- [Русский [ru]](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/.readme/README-ru.md)
- العربية [ar] # الحالية

******

### مقدمة

******

يضم Bundled Libraries مكتبات السكربتات الـ 13 التي كانت مدمجة سابقا في AutoJs6: axios و cheerio و dayjs و i18n و pangu و lodash, إضافة إلى طبقات توافق Node وهي buffer و events و fs و os و path و process و nodejs. بدءا من AutoJs6 6.8.0 لم تعد حزمة AutoJs6 تتضمنها; وبعد تثبيت هذا المكون الإضافي وتفعيله تعمل `require('axios')` و `require('fs')` وغيرها, وكذلك الكائنات العامة `axios` و `cheerio` و `dayjs` و `i18n` و `pangu`, تماما كما كانت من قبل.

المكون الإضافي مجرد حزمة موارد: يقرأ AutoJs6 ملفات المكتبات مباشرة من حزمة المكون, ولا تشغل السكربتات عملية المكون أبدا, ولا يحتاج المكون إلى أي إذن للتخزين أو الشبكة أو غيرهما (طلبات الشبكة في axios يرسلها AutoJs6 الذي يشغل السكربت). يمكن إيقاف المكتبات غير المطلوبة واحدة تلو الأخرى من صفحة المكون.

******

### المكتبات المضمنة

******

أسماء الوحدات هي وسائط `require`; ويمكن استخدام المكتبة التي لها كائن عام دون `require`. مصدر كل مكتبة وترخيصها مذكوران في [THIRD_PARTY_NOTICES.md](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/THIRD_PARTY_NOTICES.md).

#### المكتبات

| المكتبة | الوحدات | الكائن العام | الإصدار | الترخيص |
|---|---|---|---|---|
| axios | `axios` | `axios` | - | MIT |
| cheerio | `cheerio` | `cheerio` | - | MIT |
| dayjs | `dayjs` | `dayjs` | - | MIT |
| i18n | `i18n`, `banana-i18n` | `i18n` | - | MIT |
| pangu | `pangu` | `pangu` | 10.1.0 | MIT |
| lodash | `lodash` | - | 4.17.15 | MIT |

#### طبقات توافق Node

| المكتبة | الوحدات | الكائن العام | الإصدار | الترخيص |
|---|---|---|---|---|
| buffer | `buffer` | - | - | MPL-2.0 |
| events | `events` | - | - | MPL-2.0 |
| fs | `fs` | - | - | MPL-2.0 |
| os | `os` | - | - | MPL-2.0 |
| path | `path` | - | - | MPL-2.0 |
| process | `process` | - | - | MPL-2.0 |
| nodejs | `nodejs` | - | - | MPL-2.0 |

******

### الاستخدام

******

1. استخدم AutoJs6 6.8.0 (5935) أو أحدث.
2. ثبت Bundled Libraries من مركز الاضافات في AutoJs6 (أو نزل ملف APK من [Releases](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/releases)) وتأكد من أن المكون مفعل.
3. استخدم `require` للمكتبات التي تحتاجها كالمعتاد, أو استخدم الكائنات العامة مباشرة, مثلا:

```javascript
var _ = require('lodash');
var path = require('path');

console.log(dayjs(0).valueOf());
console.log(cheerio.load('<p>AutoJs6</p>')('p').text());
console.log(_.map([1, 2], function (x) { return x * 2; }).join());
console.log(path.isAbsolute('/sdcard/Scripts'));
```

> توجد حزمة عامة واحدة فقط بلا مكتبات أصلية, للأجهزة التي تعمل بنظام Android 7.0 (API 24) أو أحدث.

******

### مفاتيح المكتبات

******

في صفحة المكون (تفتح من مركز الاضافات في AutoJs6 أو من أيقونة Bundled Libraries على الشاشة الرئيسية) مفتاح لكل مكتبة, وكلها مفعلة افتراضيا:

- لا يمكن تحميل المكتبة الموقوفة عبر `require` ولا استخدام كائنها العام; أعد تفعيلها لاستعادتها.
- ينطبق المفتاح على `require` التالي: تبقى الوحدات التي حملها سكربت جار حتى ينتهي السكربت.
- تحتاج `fs` إلى `buffer`, وتحتاج `nodejs` إلى `process` و `buffer`: عند إيقاف `buffer` أو `process` لا يمكن تحميل الوحدات التي تعتمد عليهما أيضا.
- يعرض "سجل الإصدارات" أسفل الصفحة تغييرات كل إصدار.

******

### عندما يكون المكون غير متاح

******

عندما يكون المكون غير مثبت أو غير مصرح به أو معطلا في مركز الاضافات أو غير متوافق, أو تكون مكتبة موقوفة, فإن `require` لتلك المكتبة وقراءة كائنها العام يرميان خطأ:

- الخطأ هو `Error` قيمة `code` فيه `MODULE_NOT_FOUND`, وتوضح رسالته هل يجب تثبيت المكون أو أين يتم تفعيله, ويمكن للسكربت معالجته بـ `try...catch`.
- لكائن الخطأ أيضا `library` (اسم المكتبة) و `reason` (`not-installed` أو `unauthorized` أو `plugin-disabled` أو `library-disabled` أو `incompatible`).
- لا يقطع تغير حالة المكون أي سكربت جار, بل يؤثر فقط في استدعاءات `require` اللاحقة; ولا تتأثر وحدات AutoJs6 المدمجة الأخرى ولا ملفات السكربت نفسه.

******

### التوافق

******

- يتطلب AutoJs6 6.8.0 (البناء 5935) أو أحدث; الإصدارات الأقدم من AutoJs6 تتضمن هذه المكتبات بنفسها ولا تحتاج إلى المكون.
- يدعم Android 7.0 (API 24) أو أحدث, بحزمة واحدة لجميع معماريات المعالجات.
- ملفات المكتبات مطابقة تماما لتلك التي كانت مدمجة في AutoJs6, لذا لا تحتاج السكربتات الحالية إلى أي تعديل.
- يخدم المكون سكربتات JavaScript الافتراضية في AutoJs6; أما سكربتات مكون بيئة تشغيل Node.js فتستخدم وحدات Node.js نفسه.

******

### الأسئلة الشائعة

******

#### لماذا لم يعد AutoJs6 يتضمن هذه المكتبات?

لأن جزءا فقط من السكربتات يستخدمها. بنقلها إلى مكون إضافي تصغر حزمة AutoJs6, ويثبت المكون من يحتاجه فقط, ويمكن إبقاء المكتبات المستخدمة وحدها مفعلة. الملفات في المكون هي نفسها التي كانت مدمجة.

#### يستخدم السكربت الكائن العام `axios` مباشرة. هل يجب التحول إلى `require`?

لا. الكائنات العامة `axios` و `cheerio` و `dayjs` و `i18n` و `pangu` هي الوحدات نفسها التي يعيدها `require`, وكلاهما يحملها من هذا المكون.

#### هل يتصل المكون بالإنترنت أو يعمل في الخلفية?

لا. ليس للمكون إذن شبكة, وتشغيل السكربت لا يشغل عملية المكون أبدا. طلبات الشبكة في axios يرسلها AutoJs6 الذي يشغل السكربت.

#### يقول الخطأ إن المكون يحتاج إلى تصريح. ماذا أفعل?

الإصدار المنشور المثبت من مركز الاضافات في AutoJs6 أو من Releases لهذا المشروع لا يحتاج إلى تصريح. أما النسخة التي تبنيها بنفسك فلها توقيع مختلف ويجب التصريح لها أولا في مركز الاضافات حسب التعليمات.

******

### واجهة المكون الإضافي

******

المعلومات التالية موجهة لمطوري مضيف AutoJs6 والمكونات الإضافية; يستخدم المضيف هذه المعرفات لاكتشاف المكون وقراءة ملفات المكتبات:

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

### خارطة الطريق

******

قائمة التحقق قبل النشر وخطوات النشر مسجلة في ROADMAP.md; والقرارات المتعلقة بها في `docs/augment/ROADMAP.md` ضمن مستودع AutoJs6.

- [عرض ROADMAP.md](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/ROADMAP.md)

******

### سجل الإصدارات

******

#### v1.0.0

_2026/10/24_

- `تلميح` يتطلب AutoJs6 6.8.0 (5935) أو أحدث
- `تلميح` بدءا من AutoJs6 6.8.0 لم تعد المكتبات التالية تأتي مع AutoJs6: بعد تثبيت هذا المكون يعمل require والكائنات العامة كما كانت ولا تحتاج السكربتات الحالية إلى تعديل; ومن دون المكون أو عند تعطيله يعطي require لهذه المكتبات خطأ يوضح كيفية تثبيته أو تفعيله
- `ميزة` axios و cheerio و dayjs و i18n (مع banana-i18n) و pangu و lodash عبر require, وتتوفر axios و cheerio و dayjs و i18n و pangu أيضا ككائنات عامة
- `ميزة` طبقات توافق Node وهي buffer و events و fs و os و path و process و nodejs عبر require
- `ميزة` صفحة المكون بمفتاح لكل مكتبة (كلها مفعلة افتراضيا) وسجل الإصدارات; المكتبة الموقوفة غير متاحة لاستدعاءات require اللاحقة
- `ميزة` صفحة المكون تتبع لغة AutoJs6 ووضع الليل ولون الموضوع فيه, وتتبع النظام إذا لم يكن AutoJs6 مثبتا أو تعذرت قراءة إعداداته
- `تبعية` إرفاق AutoJs6 Common Plugin API

##### لمزيد من سجل الإصدارات

* [CHANGELOG.md](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/app/src/main/assets/doc/CHANGELOG-ar.md)

******

### البناء

******

هذا القسم موجه للمطورين الراغبين في بناء المكون من المصدر.

```powershell
.\gradlew.bat :app:assembleDebug :app:testDebugUnitTest
py .python\generate_markdown.py --check
py .python\generate_icon_studio.py --check
py .python\check_repository.py
```

******

### الترخيص

******

شيفرة المكون والوحدات المكتوبة لـ AutoJs6 (طبقات توافق Node وغلاف i18n ومحول axios) مرخصة بموجب [Mozilla Public License 2.0](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/LICENSE). تحتفظ المكتبات الخارجية المضمنة بتراخيصها الخاصة (MIT وغيرها), راجع [THIRD_PARTY_NOTICES.md](https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/THIRD_PARTY_NOTICES.md).

******

### روابط

******

- مشروع AutoJs6: https://github.com/SuperMonster003/AutoJs6
- إشعارات الأطراف الخارجية: https://github.com/SuperMonster003/AutoJs6-Plugin-Bundled-Libraries/blob/master/THIRD_PARTY_NOTICES.md
