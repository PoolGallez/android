# AM305 DownloadManager

La seguente app è in fase alpha ma funzionante. Va sistemata e scritta meglio in alcune sue parti. Si parta dal seguente tutorial [Saving Files](https://developer.android.com/training/data-storage/files.html) ed inoltre [Open Files using Storage Access Framework](https://developer.android.com/guide/topics/providers/document-provider.html), ma soprattutto, con le nuove versioni di Android
[Requesting Permissions Runtime](https://developer.android.com/training/permissions/requesting.html).

## Request

Abbiamo organizzato il tutto in un metodo:
```
private void enqueueRequest(DownloadManager.Request request)
```
Segono i dettagli dell'implementazione.

i) Abbiamo scelto `VISIBILITY_VISIBLE` per far in modo che appaia la notifica solo durante il download, avremmo potuto scegliere tranquillamente anche `VISIBILITY_VISIBLE_NOTIFY_COMPLETED` in modo da rendere visibile anche (in automatico) la notifica del download completo (qui customizzata). Completiamo quindi la descrizione della notifica:
```
request.setTitle("Downloading data");
request.setDescription(fileName);
```

ii) Scegliamo quindi la directory dove effettuare il download.
> the default download destination is a shared volume where the system might delete your file if it needs to reclaim space for system use
Noi usiamo
```
request.setDestinationInExternalFilesDir(this, Environment.DIRECTORY_DOWNLOADS, fileName );
```
garantendo `WRITE_EXTERNAL_STORAGE`cioè la cartella `Download` all'interno della directory riservata in esterno alla nostra app. Invitiamo a visitare la pagina della API di `Enviroment` [qui](https://developer.android.com/reference/android/os/Environment.html#getExternalStoragePublicDirectory(java.lang.String)). 
Oppure si può optare per il seguente codice in modo da scaricare il file nella cartella Download
```
setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, filename
```
ma a partire da Android 6 bisogna procedere alla richiesta dei permessi **runtime**.
```
public boolean enabledPermission() {
        if (Build.VERSION.SDK_INT >= 23) { // from Android 6.0
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                return true;
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        }
        else {
            return true;
        }
    }
```
(vedasi API).

iv) Infine abbiamo impostato le opzioni di rete:
```
request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);
```
Con l'istruzione
```
reference = downloadManager.enqueue(request);
```
otteniamo l'**id** che poi potremo utilizzare.

## Broadcast

Abbiamo operato con due broadcast: uno lavora a download terminato ed uno sulla notifica del download in esecuzione.
> Apps that request downloads through this API should register a broadcast receiver for ACTION_NOTIFICATION_CLICKED to appropriately handle when the user clicks on a running download in a notification or from the downloads UI. (from API)

```
private BroadcastReceiver getDownloadReceiver()
private BroadcastReceiver getNotificationReceiver()
```
Ci dedichiamo solo al primo.

i) Lavoriamo solo con l'id di interesse `if (reference == complete)`.

ii) L'oggetto `Query`, [qui](https://developer.android.com/reference/android/app/DownloadManager.Query.html) per le API, va puntato sugli id di interesse usando il metodo
```
DownloadManager.Query setFilterById (long... ids)
```

iii) A questo punto possiamo lavorare con un **cursor**
```
Cursor cursor = downloadManager.query(query);
cursor.moveToFirst();
```
prima il cursor verrà posizionato sulla colonna con il metodo `int columnIndex = getColumnIndex(...)` e quindi potremo estrarre il valore con `getString(columnIndex); in particolar modo a noi interessa il valore dello stato `STATUS_SUCCESSFULL`. Lo gestiamo aprendo un file manager:
```
// open in a file manager
Intent i = new Intent();
/* i.setAction(DownloadManager.ACTION_VIEW_DOWNLOADS);
startActivity(i)
 */
i.setAction(Intent.ACTION_GET_CONTENT);
i.setType("file/*");
startActivity(i);
```
commentata la possibilità di visualizzarlo sui download.







