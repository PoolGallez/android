# AM023_threading

Questo esercizio introduttivo fa riferimento alla documentazione su processi e Thread: [qui](https://developer.android.com/guide/components/processes-and-threads.html); in partocolare sulla possibilità che un **worker thread** possa comunicare con l'**UI-thread** rispettando le due regole

1) Do not block the UI thread
2) Do not access the Android UI toolkit from outside the UI thread

In questo esempio, in particolare, abbiamo fatto uso del metodo `post()` vedasi le api di `View`.
