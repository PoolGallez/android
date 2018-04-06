# AM310_Handler

In questo esempio sottoponiamo all coda di UI rhread un `Message`.

> Defines a message containing a description and arbitrary data object that can be sent to a Handler. This object contains two extra int fields and an extra object field that allow you to not do allocations in many cases.   
...  
arg1 and arg2 are lower-cost alternatives to using setData() if you only need to store a few integer values.

nel nostro esempio associamo al messaggio un oggetto - un `Bundle`
```
Message msg = mHandler.obtainMessage();
Bundle bundle = new Bundle();
bundle.putString(MSG_KEY, getCurrentTime());
msg.setData(bundle);
```
per consegnarlo poi al suo `Handler`
```
mHandler.sendMessage(msg);
```
tale azione viene eseguita all'interno di un worker thread realizzando così una possibilità di comunicazione fra un worker thread ed UI thread tramite: il messaggio veine posto nella **messagge queue** come abbiamo visto per i **task** in precedenza.

I messaggi vengono ottenuti dall'handler con metodi factory in modi seguenti
```
obtainMessage()
obtainMessage(int what, int arg1, int arg2)
obtainMessage(int what, int arg1, int arg2, Object obj)
obtainMessage(int what)
```
dove `what`, `arg1` e `arg2` sono i due argomenti interi di cui supra.
