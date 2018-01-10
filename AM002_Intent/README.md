# AM002_Intent

Approfondiamo usando Intent espliciti quanto visto nell'esercitazione precedente. Il metodo `putExtra()` ci permette di aggiungere coppie nome→valore ad un intent
```
Intent i = new Intent(MainActivity.this, ActivityTwo.class);
i.putExtra("str1", "Message Example");
i.putExtra("n1", 25);
```
Possiamo anche racchiudere tutta l'informazione in un **Bundle**
```
Bundle extras = new Bundle();
extras.putString("str2", "This is another string");
extras.putInt("n2", 666);
i.putExtras(extras);
```
Recupereremo tali informazioni usando il metodo 
```
getIntent()
```
nella successiva Activity.

Sempre in questa esercitazione vediamo l'uso del metodo
```
startActivityForResult(i, 1);
```
per ottenere un risultato di ritorno
```
public void onActivityResult(int requestCode,
                                 int resultCode, Intent data) {
        // check if the request code is 1 (we came from 1 ... )
        if (requestCode == 1) {
            //---if the result is OK---
        }
        if (resultCode == RESULT_OK) {
            // data passed in using getStringExtra() via Intent data
            Toast.makeText(this, data.getStringExtra("NOK"),
                    Toast.LENGTH_SHORT).show();
        }
    }
```
Lasciamo come esercizio lo studio del seguente segmento di codice.
```
@Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        // Save UI state changes to the savedInstanceState.
        // This bundle will be passed to onCreate if the process is
        // killed and restarted.
        savedInstanceState.putString("str4", "state saved");
        savedInstanceState.putInt("n4", 1111);
    }
```