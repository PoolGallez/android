# AM311_Conductor

Questo è il primo esempio d'uso dei `Conductor` - [qui](https://github.com/bluelinelabs/Conductor) la pagina del progetto su GitHub -che costituisce un'interessante alternativa ai `Fragment`.

```
router = Conductor.attachRouter(this, container, savedInstanceState);
        if (!router.hasRootController()) {
            router.setRoot(RouterTransaction.with(MyController.get(bundle))));
        }
```
Il metodo crea il `Router` per il `ViwGroup` associato ad un'activity, vedi [api](http://javadoc.io/doc/com.bluelinelabs/conductor/2.1.4). Come nei `Fragment` abbiamo il metodo 
```
onCreateView(LayoutInflater inflater, ViewGroup container)
```
AL post della `Transaction` viste con i `Fragment` abbiamo
```
router.setRoot(RouterTransaction.with(MyController.get(bundle)));
```
in tal modo impostiamo il controller nel container; una `RouterTransaction` è
>Metadata used for adding Controllers to a Router. Possiamo naturalmente articolare la transizione 
```
router.pushController(RouterTransaction
                .with(MyController.get(bundle))
                .pushChangeHandler(new FadeChangeHandler())
                .popChangeHandler(new FadeChangeHandler()));
```
`ControllerChangeHandler` possiede le seguenti sottoclassi: `AnimatorChangeHandler`, `NoOpControllerChangeHandler`, `SimpleSwapChangeHandler`, `TransitionChangeHandler` e `TransitionChangeHandlerCompat`. 

## AnimatorChangeHandler

La prima possibilità fa uso degli `Animator`. Vi sono le seguenti sottoclassi: `FadeChangeHandler`, `HorizontalChangeHandler`, `VerticalChangeHandler`. Costruendoli si può lavorare sulla durata. Naturalmente possiamo customizzare il tutto creando la nostra classe riscrivendo il metodo
```
rotected abstract android.animation.Animator getAnimator(android.view.ViewGroup container,
                                                          android.view.View from,
                                                          android.view.View to,
                                                          boolean isPush,
                                                          boolean toAddedToContainer)
```
rimandiamo alla [api](http://javadoc.io/doc/com.bluelinelabs/conductor/2.1.4).

## TransitionChangeHandler

Questa è la seconda possibilità e fa uso delle `Transition`.
