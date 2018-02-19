# AM_008_Alert Dialog

- A differenza di quanto accadeva nelle prime versioni di Android oramai Dialog vengono creati all'interno di Frament i `DialogFragment` dei quali, per i dialog non custam si implementa il metodo
```
public Dialog onCreateDialog(Bundle savedInstanceState)
```
- Veniamo quindi ai primi due esempi: data e ora; rimandiamo al classico tutorial di Android Developers: [qui](https://developer.android.com/guide/topics/ui/controls/pickers.html).
- Gli `Alert` sono stati costruiti tutti senza `Fragment`. Nel caso 3 abbiamo usato la strategia di riconfigurare il `Context` a partire dal preesistente. 
`
new ContextThemeWrapper(this, R.style.MyAlertDialogStyle)
`
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


