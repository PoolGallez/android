# AM12_Fragment

Questo è un esercizio abbastanza ricco di elementi interessanti, segue idealmente il primo esercizio sui fragment (ove questi vengono inseriti in modo "programmatico").

## Motion Event

In precedenza ci siamo limitati a gestire semplici touch sui bottoni qui andiamo a vedere le gesture; il riferimento che proponiamo è [qui](https://developer.android.com/training/graphics/opengl/touch.html) sempre su AD; per l'API: [qui](https://developer.android.com/reference/android/view/View.html#onTouchEvent(android.view.MotionEvent)). Per testare possiamo proporre il codice seguente
```
@Override
    public boolean onTouchEvent(MotionEvent event) {
        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x1 = event.getX();
                break;
            case MotionEvent.ACTION_UP:
                x2 = event.getX();
                float deltaX = x2 - x1;

                if (Math.abs(deltaX) > MIN_DISTANCE) {
                    // Left to Right swipe action
                    if (x2 > x1)
                    {
                        Toast.makeText(this, "Left to Right swipe [Next]", Toast.LENGTH_SHORT).show ();
                    }

                    // Right to left swipe action
                    else
                    {
                        Toast.makeText(this, "Right to Left swipe [Previous]", Toast.LENGTH_SHORT).show ();
                    }

                }
                else
                {
                    // consider as something else - a screen tap for example
                }
                break;
        }
        return super.onTouchEvent(event);
    }
```
nella nostra app già abbiamo la versione definitiva.

# Animation using Animators

Andiamo ad effettuare una `FragmentTransaction` più raffinata cercando degli effetti di animazione: il metodo che usi è
```
setCustomAnimation(int enter, int exit)
```
per la cui documentazione rimandiamo alle API: [qui](https://developer.android.com/reference/android/app/FragmentTransaction.html). 
Andando sulle res possiamo aggiungere un file di tipo Animator, precisamente sono `AnimatorSet`, [qui](https://developer.android.com/reference/android/animation/AnimatorSet.html) per le API, il quale è composto da `ObjectAnimator`, [qui](https://developer.android.com/reference/android/animation/ObjectAnimator.html) per le API.
```
<?xml version="1.0" encoding="utf-8"?>
<set xmlns:android="http://schemas.android.com/apk/res/android">
   // added using menu
</set>
```
Per una guida: [qui](https://developer.android.com/guide/topics/graphics/prop-animation.html#object-animator).
 