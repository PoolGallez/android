# AM301_lambda

L'uso della sintassi **lambda**, ora pienamente supportata grazie anche alle nuove API, oltre ad alleggerire la scrittura del codice permette di lavorare con librerie che ne fanno largo uso tipo **RXJava** per programmare ad eventi ovvero in modo **asincrono**. Ad esempio
```
 button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ...
            }
        });
```
diventa
```
 button.setOnClickListener((view) -> {
            ...
        });
```
Per alcuni riferimenti:  
[1] Tutorial Oracle: [qui](https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html).  
[2] Java Lambda Expression di Jenkov: [qui](utorials.jenkov.com/java/lambda-expressions.html).  
Ricordiamo che le espressioni lambda sono solo **lexically scoped**.