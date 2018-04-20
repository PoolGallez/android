# AM311_Conductor

Questo è il primo esempio d'uso dei `Conductor` che costituisce un'interessante alternativa ai `Fragment`.

```
router = Conductor.attachRouter(this, container, savedInstanceState);
        if (!router.hasRootController()) {
            router.setRoot(RouterTransaction.with(new MyController()));
        }
```
Il metodo crea il `Router` per il `ViwGroup` associato ad un'activity, vedi [api](http://javadoc.io/doc/com.bluelinelabs/conductor/2.1.4). Come nei `Fragment` abbiamo il metodo 
```
onCreateView(LayoutInflater inflater, ViewGroup container)
```