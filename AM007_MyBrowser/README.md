# AM007_MyBrowser

Qui creiamo un'app è molto semplice. Notiamo quanto segue.
```
<uses-permission android:name="android.permission.INTERNET" />
```
sul manifest, in realtà a noi non serve per ora ma sta ad indicare la possibilità che questa App ha di andare in Internet come indicato [qui](https://developer.android.com/reference/android/Manifest.permission.html#INTERNET).

Questa app possiede una sola Activity significativa e tale Activity **deve** essere lanciata con un intent implicito
```
<activity android:name=".Browser">
            <intent-filter>
                <action android:name="android.intent.action.VIEW" />
                <category android:name="android.intent.category.DEFAULT" />
                <data android:scheme="http" />
            </intent-filter>
        </activity>
```
L'azione è standard
```
<action android:name="android.intent.action.VIEW"
```
al solito è obbligatorio
```
 <category android:name="android.intent.category.DEFAULT"
```
un ultimo dettaglio; filtriamo gli URI
```
<data android:scheme="http"
```
a tal proposito rimandiamo alla documentazione: [qui](https://developer.android.com/guide/topics/manifest/data-element.html).

Per terminare facciamo osservare che potevamo lavorare solo con la MainActivity con due **intent filter**.

Un ultimo dettaglio:
```
tools:ignore="AppLinkUrlError"
```
è ad uso dell'IDE.


