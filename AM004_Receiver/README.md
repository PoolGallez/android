# AM004_Receiver

In questo esercizio continuiamo a vedere il secondo (in ordine di esposizione) dei componenti: il `BoradcastReceiver`. 

## Manifest-declared receivers

E' la classe `MyBoroadCastReceiverXML`, nel manifest abbiamo la registrazione
```
 <receiver android:name="MyBroadcastReceiverXML" >
            <intent-filter>
                <action android:name="MY_ACTION_02" />
            </intent-filter>
        </receiver>
```

## Context-registered receivers

Qui lavoriamo in modo programmatico. Facciamo osservare la riscrittura dei metodi
```
@Override
    public void onResume() {
        super.onResume();
        registerReceiver(myReceiver, intentFilter);
    }
    @Override
    public void onPause() {
        super.onPause();
        unregisterReceiver(myReceiver);
    }
```
Invitiamo ad andare alla API di `Context`: [qui](https://developer.android.com/reference/android/content/Context.html).

