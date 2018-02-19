# AM_008_Alert Dialog

- A differenza di quanto accadeva nelle prime versioni di Android oramai Dialog vengono creati all'interno di Frament i `DialogFragment` dei quali, per i dialog non custam si implementa il metodo
```
public Dialog onCreateDialog(Bundle savedInstanceState)
```
- Veniamo quindi ai primi due esempi: data e ora; rimandiamo al classico tutorial di Android Developers: [qui](https://developer.android.com/guide/topics/ui/controls/pickers.html).
- Gli `Alert` sono stati costruiti tutti senza `Fragment`. 
- Nel terzo esempio abbiamo mostrato alla vecchia maniera un dialog - pratca da evitare ma che qui usiamo solo per ragioni di spazio - con stile custom (vedi anche [qui](https://developer.android.com/guide/topics/ui/look-and-feel/themes.html)).
`
new ContextThemeWrapper(this, R.style.MyAlertDialogStyle)
`
Abbiamo sostituito i vecchi listener che qui ripresentiamo
```
DialogInterface.OnClickListener listener_OK = new DialogInterface.OnClickListener() {
  @Override
  public void onClick(DialogInterface dialogInterface, int i) {
    Toast.makeText(MainActivity.this, "Pressed OK", Toast.LENGTH_SHORT).show();
  }
};

DialogInterface.OnMultiChoiceClickListener listener = new DialogInterface.OnMultiChoiceClickListener() {
  @Override
  public void onClick(DialogInterface dialogInterface, int i, boolean b) {
    Toast.makeText(MainActivity.this, i + " is " + b, Toast.LENGTH_SHORT).show();
  }
};
```


Qui di seguito il dettaglio dell'`Alert` più customizzato
```
builder = new AlertDialog.Builder(this, R.style.MyAlertDialogStyle);
```
Al `Builder` (vedi API (qui) applichiamo uno stile. Aggiungiamo un widget - `View` - al tutto rispettando lo stile dell'alert.
```
final EditText etNickName = new EditText(new ContextThemeWrapper(this, R.style.MyAlertDialogStyle));
builder.setView(etNickName);
```
e quindi i bottoni
```
builder.setPositiveButton("Enter user name", new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
```
Per terminare mostriamo l'`Alert`
```
builder.show();
```
Suggeriamo come al solito di andare a leggerse le altre API.


