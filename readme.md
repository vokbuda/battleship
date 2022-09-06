# Battaglia Navale

Questa è una possibile implementazione di Battaglia Navale.
## Description
Questo gioco è stato implementato con l'idea di creare una base per gli altri sviluppatori. Sostanzialmente il gioco contiene soltanto una modalità, ma questa implementazione è molto flessibile. Questa flessibilita ci permette di creare quanti giocatori ne vogliamo, tabelle con diversi Status di caselle(possibilità di creare i board personalizzati). Tra le funzionalità in aggiunta a quelle necessarie che sono state realizzate ci sono: 
1)aggiunta di una nave su board che si base sia sulla quantità di caselle sia sul tipo di nave;
2)rimozione di una nave durante preparazione;
3)modifica di una nave in base di casella aggiunta durante preparazione per il gioco;
4)mostra la quantita delle navi di giocatore.

## Installation

Per installare progetto è necessario fare import del progetto dentro WorkSpace di Eclipse o IntelliJ. Prima di lanciare i test(com.battagliaNavale.tests) dovrebbe essere aggiunta libreria JUnit 5 dentro BuildPath del progetto.

## Usage

Per vedere lancio del programma si utilizza 'Run Program'. Oppure facendo a mano(prima si fa la compilazione come nella prima riga di tutte classi e poi si lancia il programma):

bash
javac Main.java
java Main.java

Per lanciare i test è necessario scegliere la classe su quale si vuole eseguire dei test e di spostarsi dentro package 'com.battagliaNavale.tests', dopodiche trovare il nome della classe aggiungendo la parola 'Test'(classe 'Main.java' avrà i metodi per eseguire i test dentro 'MainTest.java').Per eseguire test su parecchie classi è necessario spostarsi su appropriati file *Test.java
## Roadmap
Si può estendere questo gioco aggiungendo più giocatori(questo in futuro potrebbe dare la possibilità di creare un eventuale multiplayer). Questa implementazione permette anche di generare un board 100*100. E aggiungendo alcuni Status si può anche generare board con le isole.
## Contributing
Le richieste di pull sono benvenute. In caso di modifiche importanti, aprire prima un problema per discutere di ciò che si desidera modificare.

Per favore assicurati di fare import dei test in modo appropriato
## Authors and aknowledgment
Dubkov Andrei
## Licenza
[MIT](https://choosealicense.com/licenses/mit/)