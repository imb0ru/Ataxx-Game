# Report

## Indice
1. [Introduzione](#1-introduzione)
2. [Modello di dominio](#2-modello-di-dominio)
3. [Requisiti specifici](#3-requisiti-specifici) <br>
   3.1 [Requisiti funzionali](#31-requisiti-funzionali) <br>
   3.2 [Requisiti non funzionali](#32-requisiti-non-funzionali) <br>
4. [System design](#4-system-design)<br>
    4.1 [Stile architetturale](#41-stile-architetturale)<br>
    4.2 [Diagramma dei Package](#42-diagramma-dei-package)<br>
    4.3 [Diagramma delle componenti](#43-diagramma-delle-componenti)<br>
5. _TDB_
6. [Riepilogo dei casi di Test](#6-riepilogo-dei-casi-di-test)
7. [Manuale utente](#7-manuale-utente)
8. [Processo di sviluppo e organizzazione del lavoro](#8-processo-di-sviluppo-e-organizzazione-del-lavoro) <br>
    8.1 [Processo di sviluppo adotatto](#81-processo-di-sviluppo-adottato) <br>
    8.2 [Organizzazione del Lavoro](#82-organizzazione-del-lavoro) <br>
    8.3 [Utilizzo di piattaforme per la comunicazione](#83-utilizzo-di-piattaforme-per-la-comunicazione) <br>
    8.4 [Tool Utilizzati](#84-tool-utilizzati) <br>
9. [Analisi Retrospettiva](#9-analisi-retrospettiva) <br>
9.1 [Sprint 0](#91-sprint-0) <br>
9.2 [Sprint 1](#92-sprint-1) <br>

## **(1) Introduzione**

_Benvenuti_ nel progetto di *Ataxx!* del team **Berners-Lee**. Il
nostro **Ataxx** offre un'esperienza di gioco da tavolo contro un
avversario in locale.

Il gioco si svolge su una griglia di dimensione `7x7`. Le celle sono
identificate da coordinate che combinano **lettere** per le colonne e
**numeri** per le righe. Ad esempio, la cella `C5` si riferisce alla
colonna C e alla riga 5.

Il gioco inizia con due pedine bianche del giocatore 1 e due pedine
nere del giocatore 2 posizionate sulle quattro celle agli angoli
del tavoliere. Il giocatore può effettuare due tipi di mosse: la
duplicazione in una cella
adiacente vuota o il salto in una cella vuota situata a distanza di una
casella. Quando una
pedina si muove in una cella adiacente a una pedina avversaria, queste
ultime vengono convertite nel colore del giocatore che ha effettuato la
mossa.

**Dopo aver posizionato le pedine iniziali, il gioco inizia**. Ogni
turno, il giocatore sceglie una mossa da effettuare, come ad esempio
duplicare in `D4` per occupare la quarta riga della colonna D.
L'obiettivo del gioco è occupare la maggior parte delle celle della
griglia con le proprie pedine entro la fine del gioco.

Dopo ogni mossa, la griglia viene aggiornata in base all'esito dell'
azione. Le pedine convertite cambiano colore per riflettere il controllo
del territorio da parte del giocatore.

La _partita termina_ quando *tutte* le celle sono occupate o quando
nessun giocatore può più effettuare una mossa. Vince chi ha il maggior
numero di pedine del proprio colore sulla griglia.

_Durante la partita, è possibile abbandonare il gioco in qualsiasi
momento_. In tal caso, il giocatore che abbandona perderà la partita
per x a 0 dove x è il numero di pedine rimaste all'avversario.

**Siete pronti a dimostrare il vostro talento strategico? Entrate nel
mondo di Ataxx e mostrate la vostra abilità nel conquistare la griglia
di gioco!**

## (2) Modello di Dominio

Diagramma costruito con [StarUML v6.0.1](https://staruml.io/)

![Modello di Dominio](/docs/img/modello_dominio.png)

I concetti più importanti a cui fa riferimento sono i seguenti:

| Concetto di appartenenza | Attributo   | Descrizione                                                                                                                                   |
|--------------------------|-------------|-----------------------------------------------------------------------------------------------------------------------------------------------|
| `Board`                  | `board`     | È il riferimento al tavoliere che contiene le pedine della partita.                                                                           |
| `Cell`                   | `cells`     | È l'array che contiene tutte le celle del tavoliere. Possono essere vuote (`EMPTY`), nere (`BLACK`), bianche (`WHITE`) o bloccate (`LOCKED`). |

Inoltre ogni partita può essere vista come una sequenza di mosse 
(rappresentate dal concetto `Move`).

Ogni mossa può essere di due tipi che abbiamo deciso di chiamare
* `ReplicateAndJump`: quando un giocatore decide di replicarsi in una cella
                      adiacente;
* `Jump`: quando un giocatore decide di saltare in una cella distante 2.

Inoltre ogni mossa è composta da una posizione di partenza e arrivo
(rappresentate dal concetto `Position`).
A loro volta queste sono composte da
* `row`: la riga sul tavoliere
* `column`: la colonna sul tavoliere

Infine abbiamo il concetto di `Player` che rappresenta i giocatori della partita.
Ogni giocatore è identificato da tutte le celle del suo colore assegnato 
(cella `BLACK` per un giocatore, cella `WHITE` per l'altro).


## (3) Requisiti Specifici

### (3.1) Requisiti funzionali
- (RF1) Il sistema deve permettere di iniziare una nuova partita.
  - (RF1.1) Al comando `/gioca` se nessuna partita è in corso l'app
    mostra il tavoliere con le pedine in posizione iniziale e si
    predispone a ricevere la prima mossa di gioco del nero o altri
    comandi.
- (RF2) Il sistema deve permettere di terminare l'applicazione.
  - (RF2.1) Al comando `/esci` l'applicazione chiede conferma. Se la
    conferma è positiva, l'applicazione si chiude restituendo il
    controllo al sistema operativo altrimenti si predispone a ricevere nuovi tentativi o comandi.
- (RF3) Il sistema deve permettere al giocatore di abbandonare una partita in corso.
  - (RF3.1) Al comando `/abbandona` l'applicazione chiede conferma.
    Se la conferma è positiva, l'app comunica che il Bianco (o il
    Nero) ha perso per abbandono e dichiara come vincitore
    l'avversario per x a 0 dove x è il numero di pedine rimaste
    dell'avversario. Se la conferma è negativa, l'app si predispone
    a ricevere nuove mosse o comandi.
- (RF4) Il sistema deve permettere al giocatore di visualizzare 
  tutti i comandi disponibili.
  - (RF4.1) Al comando `/help` o invocando l'app con flag `--help` o `-h` 
    l'applicazione mostra a schermo tutti i comandi disponibili. Il 
    risultato è una descrizione concisa seguita dalla lista di 
    comandi disponibili, uno per riga.
- (RF5) Il sistema deve permettere di visualizzare il tavoliere di 
  gioco vuoto.
  - (RF5.1) Al comando `/vuoto` l'applicazione mostra a schermo
    il tavoliere di gioco vuoto di 49 caselle quadrate con le righe 
    numerate da 1 a 7 e le colonne numerate da `a` a `g`.
- (RF6) Il sistema deve permettere di visualizzare il tavoliere di
     gioco con le pedine in posizione attuale.
    - (RF6.1) Al comando `/tavoliere` se il gioco non è iniziato 
      l'app suggerisce il comando `gioca`, altrimenti mostra la 
      posizione di tutte le pedine sul tavoliere.
- (RF7) Il sistema deve permettere di visualizzare le mosse 
  possibili di una pedina.
  - (RF7.1) Al comando `/qualimosse` se il gioco non è iniziato 
    l'app suggerisce il comando `gioca`, altrimenti mostra le 
    mosse possibili per il giocatore di turno, evidenziando
    1) in giallo le caselle raggiungibili con mosse che generano una 
      nuova pedina
    2) in arancione raggiungibili con mosse che consentono un salto
    3) in rosa le caselle raggiungibili con mosse di tipo a o b
- (RF8) Il sistema deve permettere di visualizzare le mosse giocate.
  - (RF8.1) Al comando `/mosse` l'applicazione mostra a schermo la storia delle mosse con notazione algebrica `k. xn ym (p)` dove `k` è il numero della mossa, `xn` è la posizione di partenza e `ym` è la posizione di arrivo e `p` è il giocatore che ha effettuato la mossa.
- (RF9) Il sistema deve permettere di visualizzare il tempo di gioco.
  - (RF9.1) Al comando `/tempo` l'applicazione mostra a schermo il tempo trascorso dall'inizio della partita in formato `ore:minuti:secondi`.
- (RF10) Il sistema deve permettere di impostare caselle non accessibili.
  - (RF10.1) Al comando `/blocca xn`, se nessuna partita è in corso, l'applicazione blocca la cella che si trova alla colonna `x` e riga `n` del tavoliere, la cella viene mostrata sul tavoliere con un segnale di divieto rosso e non può essere occupata da nessuna pedina.
  - (RF10.2) Non è possibile bloccare:
    - (RF10.2.1) le celle di partenza dei giocatori
    - (RF10.2.2) tutte le caselle adiacenti ad'una casella di partenza del gioco, rendendo impossibile la mossa di espansione di una pedina a inizio gioco
    - (RF10.2.3) tutte le caselle a distanza 2 da una casella di partenza del gioco, rendendo impossibile la mossa di salto di una pedina a inizio gioco
    - (RF10.2.4) più di 9 celle
- (RF11) Il sistema deve permettere di effettuare una mossa.
  - (RF11.1) Utilizzando la notazione algebrica `xn-ym` dove `xn` è la cella di partenza e `ym` è la cella di arrivo, il giocatore deve poter effettuare una mossa valida:
    - (RF11.1.1) Se nella cella di partenza non c'è una pedina del giocatore di turno, la mossa non è valida.
    - (RF11.1.2) Se la cella di arrivo è occupata da una pedina, la mossa non è valida.
    - (RF11.1.3) Se la cella di arrivo è bloccata, la mossa non è valida.
    - (RF11.1.4) Se la cella di partenza è occupata da una pedina del giocatore di turno, la cella di arrivo è vuota, non bloccata e adiacente alla cella di partenza, la mossa è valida e la pedina viene duplicata nella cella di arrivo.
    - (RF11.1.5) Se la cella di partenza è occupata da una pedina del giocatore di turno, la cella di arrivo è vuota, non bloccata e distante 2 dalla cella di partenza, la mossa è valida e la pedina viene spostata nella cella di arrivo.
    - (RF11.1.6) Se la mossa è valida, e nelle celle adiacenti alla cella di arrivo c'è almeno una pedina avversaria, queste vengono convertite nel colore del giocatore di turno.
- (RF12) Il sistema deve permettere di passare il turno in caso di impossibilità di movimento.
  - (RF12.1) Se il giocatore di turno non può effettuare nessuna mossa valida, il sistema passa il turno all'avversario.
- (RF13) Il sistema deve permettere di visualizzare il vincitore della partita.
  - (RF13.1) Quando tutte le celle del tavoliere sono occupate il sistema deve dichiarare il vincitore e riportare il punteggio finale.
    
### (3.2) Requisiti non funzionali
- (RNF1) Il container docker dell'app deve essere eseguito da terminali che supportano Unicode con encoding UTF-8 e UTF-16.
  - (RNF1.1) Per Linux e MacOS si consiglia di utilizzare il 
    terminale di default.
  - (RNF1.2) Per Windows si consiglia di utilizzare Powershell o Git 
    Bash (in questo caso il comando Docker ha come prefisso winpty).
- (RNF2) Per eseguire il container docker dell'app è necessario:
  - (RNF2.1) Avere installato docker sul proprio sistema operativo.
  - (RNF2.2) Eseguire il comando `docker pull ghcr.
    io/softeng2324-inf-uniba/ataxx-berners:latest`.
  - (RNF2.3) Eseguire il container docker con il comando `docker run 
    --rm -it ghcr.io/softeng2324-inf-uniba/ataxx-berners:latest`.

## **(4) System Design**

### (4.1) Stile architetturale
La suddivisione in package è stata effettuata accomunando le varie classi in base alle loro responsabilità e compiti svolti.
Perciò si è voluto optare per lo stile architetturale Entity Control Boundary (ECB) che prevede la
classificazione delle classi in tre categorie:

- ENTITY: Rappresenta gli oggetti del dominio, contenenti i dati e la logica di business. Per Ataxx, le entità
  includono il tabellone di gioco e le mosse.
- CONTROL: Gestisce il flusso e la logica dell'applicazione, orchestrando le
  interazioni tra Boundary ed Entity. Per Ataxx, i controlli includono la gestione delle mosse dei giocatori
  e l'applicazione delle regole del gioco.
- BOUNDARY: sono le classi che si occupano di interfacciarsi con l'utente e di gestire le logiche di presentazione.
  In particolare si occupano di ricevere i comandi dell'utente e di mostrare i risultati delle operazioni.

L'elenco dei package e delle classi in essi contenuti è il seguente:
- il package **Boundaries** contenente:
    - ***GamePrinter***: classe che si occupa di stampare lo stato della partita;


- il package **Commands**, package cha raggruppa le classi che si occupano di eseguire i commandi inseriti dall'utente:
    - ***BlockCommand***: classe esegue il comando ```  "/blocca" ``` permettendo all'utente di bloccare una cella del tavoliere;
    - ***BoardCommand***: classe esegue il comando ```  "/tavoliere" ``` perciò stampa il tavoliere della partita in corso;
    - ***EmptyBoard***: classe esegue il comando ```  "/vuoto" ``` che stampa un tavoliere vuoto;
    - ***ExitCommand***: classe esegue il comando ```  "/exit" ``` di conseguenza uscendo dall'applicazione;
    - ***HelpCommand***: classe esegue il comando ```  "/help" ``` percui vengono stampate le informazioni di aiuto per l'utilizzo del gioco;
    - ***MoveCommand***: classe esegue il comando ``` /mosse ``` e stampa a video le mosse effettuate durante la partita;
    - ***MoveListCommand***: classe esegue il comando ```  "/mosse" ``` e stampa a video le mosse effettuate durante la partita ;
    - ***PlayCommand***: classe esegue il comando ```  "/gioca" ```  che comincia una nuova partita nel caso in cui non ce ne fosse già una in corso;
    - ***QuitCommand***: classe esegue il comando ```  "/abbandona" ``` il quale permette all'utente di abbandonare la partita in corso.
    - ***TimeCommand***: classe esegue il comando ```  "/tempo" ``` che stampa a video il tempo di gioco della partita;
    - ***WhatMovesCommand***: classe che esegue il comando ```  "/qualimosse" ``` il quale stampa a video il tavoliere con le mosse che può effettuare il giocatore corrente;
      <br><br>
- il package **Controls** contenente:
    - ***AppController***: classe principale dell'applicazione.
      Gestisce le chiamate dei comandi di gioco in base alle azioni dell'utente;
    - ***GameController***: classe che gestisce la logica di gioco;
      <br><br>
- il package **Entities** contenente:
    - ***Board***: classe che rappresenta il tavoliere del gioco;
    - ***Move***: classe che rappresenta una mossa all'interno della partita;
- il package **Exceptions** raggruppa tutte le classi che si occupano delle eccezioni durante l'esecuzione del gioco, contiene le seguenti classi:
    - ***InvalidBoardException***: eccezione lanciata quando il tavoliere non è valido;
    - ***InvalidGameException***: eccezione lanciata quando la stringa che rappresenta lo stato della partita non è valida;
    - ***InvalidMoveException***: eccezione non controllata che rappresenta una mossa non valida;
    - ***InvalidPositionException***: eccezione non controllata che gestisce una posizione non valida;
      <br><br>
- il package **Utils** contentente le classi:
    - ***Color***: classe che si occupa dell'enumerazione dei colori disponibili per la stampa.
    - ***Strings***: classe contenente le stringhe utilizzate nell'applicazione.
      <br><br>
- Infine, situata nel package di default(it.uniba.app), la classe **App** che si occupa dell'inizializzazione e avvio del software, come definito dal workflow utilizzato.

### (4.2) Diagramma dei Package
Tra i vari package del progetto esistono delle dipendenze. Una dipendenza indica che un modulo o un pacchetto
richiede l'accesso a un altro modulo o pacchetto per funzionare correttamente.<br>
Questo è come si mostra il diagramma dei package ad un livello alto di astrazione: <br><br>
![Diagramma_package_alto_livello](/docs/img/diagramma_dei_package_alto_livello.png)

Ad un livello più basso di astrazione in `it.uniba.app.` il diagramma è il seguente:<br><br>
![Diagramma_package_basso_livello](/docs/img/diagramma_dei_package_basso_livello.png)

Considerando anche il package dedicato al testing, questo è il diagramma che ne segue:<br><br>
![Diagramma_package_alto_livello_2](/docs/img/diagramma_dei_package_alto_livello_2.png)

### (4.3) Diagramma delle componenti
Il sistema è costituito da due componenti:
- ***Command Line Interface***: fornisce servizi per giocare ad Ataxx attraverso una linea di comando.
- ***Ataxx***:  fornisce servizi per gestire partite di ataxx e di manipolare gli elementi del gioco. <br><br>

![Diagramma delle componenti](/docs/img/diagramma_delle_componenti.png)

Queste due componenti comunicano tra loro tramite l'interfaccia **Ataxx API**.

**Flusso delle operazioni**:
1) L'utente inserisce un comando tramite la ***Command Line Interface***.
2) La ***Command Line Interface*** invia questo comando all'***Ataxx API***.
3) L'***Ataxx API*** comunica con la componente ***Ataxx***, trasmettendo il comando per essere elaborato.
4) La componente ***Ataxx*** elabora il comando utilizzando la logica di gioco e aggiorna lo stato del gioco di conseguenza.
5) La risposta o l'aggiornamento dello stato del gioco viene inviato dall'***Ataxx API*** alla ***Command Line Interface***.
6) La ***Command Line Interface*** visualizza l'output o lo stato aggiornato del gioco all'utente.

Il diagramma illustra chiaramente la relazione tra l'**interfaccia utente a linea di comando** e il sistema di gioco **Ataxx**,
con l'**API** che funge da intermediario per la comunicazione. Questo tipo di architettura modularizzata permette una chiara separazione tra la
logica del gioco e l'interfaccia utente, facilitando la manutenzione e l'aggiornamento di ciascuna componente indipendentemente.

## (6) Riepilogo dei casi di Test

In questa sezione analizzeremo i casi di Test effettuati su differenti classi.

Per effettuare i test sono stati utilizzati i seguenti strumenti:
* [JUnit](https://junit.org/junit5/)
* [CheckStyle](https://checkstyle.sourceforge.io/)
* [Spotbugs](https://spotbugs.github.io/)
* [PMD](https://pmd.github.io/)

In particolare abbiamo utilizzato gli ultime 3 per un'analisi del codice statica e la prima per la creazione ed esecuzione
di casi di test per diverse classi, analizziamoli nel dettaglio:

### Test per la classe `Move`

Di seguito i test selezionati:
* `constructorTest`: questo metodo testa che la mossa che contiene cella di partenza e cella di arrivo siano correttamente specificate;
* `constructorThrowsTest`: questo metodo è stato creato per sollevare un'eccezione nel caso in cui le celle specificate vadano oltre la distanza consentita;
* `moveTypeTest`: questo metodo è stato definito per verificare la tipologia della mossa effettuata da parte del giocatore. Restituisce, in base alla mossa effettuata, la tipologia corretta.

### Test per la classe `Board.Position`

Di seguito i test selezionati:
* `constructorTest`: questo metodo testa che le righe e le colonne corrispondenti alla posizione siano valide, cioè all'interno del tavoliere;
* `constructorThrowsTest`: questo metodo serve per sollevare un'eccezione nel caso in cui la posizione non sia valida, ad esempio fuori dal tavoliere;
* `fromStringTest`: questo metodo testa che la cella di partenza e di arrivo creata a partire dalla stringa siano valide;
* `fromStringThrowsTest`: questo metodo solleva un'eccezione nel caso in cui la cella di arriva e di partenza create a partire da una stringa non siano valide;
* `distanceTest`: questo metodo verifica se la distanza tra la cella di partenza e la cella di arrivo sia valida.  

### Test per la classe `Board`

Di seguito i test selezionati:
* `initialBoardTest`: testa che il tavoliere iniziale sia correttamente inizializzato.
* `invalidCharacterInsideStringTest`: testa che se la stringa del tavoliere contiene caratteri non validi allora venga sollevata un'eccezione che segnala l'errore.
* `tooLongStringTest`, `tooShortStringTest`: testa che se la stringa del tavoliere contiene un numero di celle non valido venga sollevata un'eccezione che segnala l'errore.
* `invalidLockedCellInStringTest`: testa che se la stringa del tavoliere contiene celle bloccate in posizioni non valide allora venga sollevata un'eccezione che segnala l'errore.
* `tooManyLockedCellsInStringTest`: testa che se la stringa del tavoliere contiene più di 9 celle bloccate allora venga sollevata un'eccezione che segnala l'errore.
* `lockedCellsTest`: testa che la configurazione del tavoliere sia corretta dopo aver bloccato alcune celle.
* `resetLockedCellsTest`: testa che la configurazione del tavoliere sia corretta dopo aver bloccato alcune celle e successivamente sbloccate.
* `blockInitialCellsTest`: testa che venga lanciata un'eccezione se si prova a bloccare le celle di partenza dei giocatori.
* `blockAdjacentInitialCellsTest`: testa che venga lanciata un'eccezione se si prova a bloccare le celle adiacenti alle celle di partenza dei giocatori.
* `maxLockedCellsTest`: testa che venga lanciata un'eccezione se si prova a bloccare più di 9 celle.

### Test per la classe `GameController`

Di seguito i test selezionati:
* `initialGameTest`: testa che ogni partita cominciata da zero abbia sempre la stessa configurazione,
  ovvero che cominci il nero e il tavoliere sia quello specificato nelle regole di gioco.
* `invalidGameStringFormatTest`: testa che se la stringa della partita non è valida allora venga sollevata un'eccezione che segnala l'errore.
* `invalidCharacterInsideGameStringTest`: testa che se la stringa della partita contiene caratteri non validi allora venga sollevata un'eccezione che segnala l'errore.
* `noWhiteCellsWinTest`, `noBlackCellsWinTest`: in questi due test viene controllata una condizione simile, ovvero che se
  uno dei due giocatori dovesse arrivare a non avere più celle allora lo stato della partita viene impostato alla vittoria
  dell'altro giocatore.
* `noLegalMovesTest`: in questo test si controlla il caso in cui un giocatore non abbia alcun mossa legale.
* `correctJumpAndReplicateMoveTest`: in questo test ci assicuriamo che con una mossa di tipo 1 la pedina di partenza non viene
  spostata e ne venga creata un'altra nella posizione di arrivo.
* `correctJumpMoveTest`: in questo test ci assicuriamo che con una mossa di tipo 2 la pedina di partenza venga spostata
  nella posizione d'arrivo.
* `moveConvertsAdjacentEnemyCellsTest`: in questo test ci assicuriamo che eseguendo una mossa le pedine del nemico adiacenti
  alla casella di arrivo vengano convertite in pedine del giocatore che ha effettuato la mossa.
* `moveToBlockedCellTest`: in questo test ci assicuriamo che quando il giocatore prova a fare una mossa che ha come cella
  di destinazione una cella bloccata viene lanciata un'eccezione.

### Motivazioni dei test

I test sopra descritti contribuiscono a rendere le modifiche al gioco semplici e meno prone ad errori in quanto in questa
maniera possiamo sempre controllare che i comportamenti base del gioco rimangano invariati.

Infatti, senza di essi, si rischierebbe che una modifica non prudente del codice, potrebbe portare alla creazione di un 
errore nel codice difficile da rintracciare e che di conseguenza rallenterebbe la produzione di nuovo codice.

### Esiti dei casi di test

![Esiti casi di test](/docs/img/esiti_casi_di_test.png)

## (7) Manuale Utente

### Introduzione
Benvenuti nel mondo avvincente di Ataxx! Questo manuale utente è stato creato per guidarvi attraverso le regole, le strategie e le funzionalità del gioco, assicurandovi un'esperienza di gioco ottimale. Ataxx è un gioco di strategia a turni che combina elementi di tattica e pianificazione, sfidando i giocatori a pensare diversi passi avanti per conquistare il tabellone. Che siate nuovi al gioco o veterani in cerca di affinare le vostre abilità, questo manuale vi fornirà tutte le informazioni necessarie per padroneggiare Ataxx. Prendete posto, preparatevi a espandere il vostro dominio e che la sfida abbia inizio!

### Scopo del gioco
L'obiettivo di Ataxx è semplice: conquistare il maggior numero possibile di caselle del tabellone trasformandole nel proprio colore. Due giocatori si alternano nei turni, cercando di espandere il loro controllo e impedire all'avversario di fare lo stesso. Alla fine del gioco, vince il giocatore che possiede il maggior numero di caselle del proprio colore. La strategia, la pianificazione e l'abilità di prevedere le mosse dell'avversario sono fondamentali per ottenere la vittoria.

### Avvio del gioco
Per avviare il gioco Ataxx, seguite le istruzioni riportate di seguito in base al tipo di esecuzione desiderata. Poiché Ataxx è un'applicazione Java, assicuratevi di avere Java installato sul vostro sistema.

#### Avvio normale

1. **Aprire il Terminale (cmd)**
   - Su Windows, premere `Win + R`, digitare `cmd` e premere `Invio`.
   - Su macOS, premere `Cmd + Spazio`, digitare `Terminal` e premere `Invio`.
   - Su Linux, utilizzare la combinazione di tasti `Ctrl + Alt + T`.

2. **Navigare alla Directory del Gioco**
   - Utilizzare il comando `cd` seguito dal percorso della directory in cui è installato Ataxx. Ad esempio:
     ```sh
     cd C:\Percorso\Al\Gioco\Ataxx
     ```

3. **Eseguire l'Applicazione**
   - Digitare il comando per eseguire l'applicazione senza flag e premere `Invio`:
     ```sh
     java -jar ataxx-all.jar
     ```
   - Il gioco si avvierà normalmente e potrete iniziare a giocare.

![Avvio Standard](/docs/img/avvio_standard.png)

#### Avvio con menu di aiuto

1. **Aprire il Terminale (cmd)**
   - Su Windows, premere `Win + R`, digitare `cmd` e premere `Invio`.
   - Su macOS, premere `Cmd + Spazio`, digitare `Terminal` e premere `Invio`.
   - Su Linux, utilizzare la combinazione di tasti `Ctrl + Alt + T`.

2. **Navigare alla Directory del Gioco**
   - Utilizzare il comando `cd` seguito dal percorso della directory in cui è installato Ataxx. Ad esempio:
     ```sh
     cd C:\Percorso\Al\Gioco\Ataxx
     ```

3. **Eseguire l'Applicazione con il Menu di Aiuto**
   - Digitare uno dei seguenti comandi per visualizzare il menu di aiuto e premere `Invio`:
     ```sh
     java -jar ataxx-all.jar -h
     ```
     oppure
     ```sh
     java -jar ataxx-all.jar --help
     ```
![Avvio Con Help](/docs/img/avvio_con_help.png)

Utilizzando queste istruzioni, sarete in grado di avviare Ataxx facilmente, sia per iniziare a giocare immediatamente che per ottenere assistenza sui comandi disponibili.

### Utilizzo del prompt dei comandi
Una volta avviato il gioco, vi troverete di fronte a un prompt dei comandi dove potrete inserire varie istruzioni per controllare il gioco.

![Prompt dei Comandi](/docs/img/avvio_standard.png)

### Invio dei comandi e rispettivo funzionamento
Gli unici comandi utilizzabili subito dopo l'esecuzione dell'applicazione sono:

- `/help`: mostra l'elenco dei comandi disponibili come nell'esecuzione con le flag `-h` o `--help`.
![Comando Help](/docs/img/comando_help.png)

- `/gioca`: inizia una nuova partita di Ataxx.
![Comando Gioca](/docs/img/comando_gioca.png)

- `/esci`: termina l'applicazione chiedendo conferma. Se si risponde si l'applicazione si chiude, altrimenti si predispone a ricevere nuovi comandi.
![Comando Esci](/docs/img/comando_esci.png)
  - Per confermare l'uscita, digitare `s` e premere `Invio`.
  ![Conferma Uscita](/docs/img/conferma_esci.png)
  - Per annullare l'uscita, digitare `n` e premere `Invio`
  ![Annulla Uscita](/docs/img/annulla_esci.png)

- `/vuoto`: mostra il tavoliere di gioco vuoto, ovvero senza pedine di gioco.
![Comando Vuoto](/docs/img/comando_vuoto.png)

- `/blocca xn`: blocca la cella alla colonna `x` e riga `n` del tavoliere, impedendo a qualsiasi pedina di occuparla.
![Comando Blocca](/docs/img/comando_blocca.png)


### Comandi disponibili durante una partita
Dopo aver avviato una partita con il comando `/gioca`, avrai accesso a una serie di comandi aggiuntivi specifici per la gestione e il progresso della partita stessa. Di seguito sono elencati i principali comandi disponibili durante una partita:

- `/tavoliere` : mostra il tavoliere di gioco con le pedine in posizione attuale.
![Comando Tavoliere](/docs/img/comando_tavoliere.png)

- `/qualimosse` : mostra le mosse possibili per il giocatore di turno, evidenziando le caselle raggiungibili con mosse che generano una nuova pedina, le caselle raggiungibili con mosse che consentono un salto e le caselle raggiungibili con entrambe le mosse.
![Comando Quali Mosse](/docs/img/comando_qualimosse.png)

- `/abbandona` : permette al giocatore di abbandonare la partita in corso. Se confermato, il giocatore che abbandona perde la partita per x a 0 dove x è il numero di pedine rimaste all'avversario.
![Comando Abbandona](/docs/img/comando_abbandona.png)
  - Per confermare l'abbandono, digitare `s` e premere `Invio`.
  ![Conferma Abbandono](/docs/img/conferma_abbandona.png)
  - Per annullare l'abbandono, digitare `n` e premere `Invio`.
  ![Annulla Abbandono](/docs/img/annulla_abbandona.png)

- `/mosse` : mostra la storia delle mosse con notazione algebrica `k. xn ym (p)` dove `k` è il numero della mossa, `xn` è la posizione di partenza, `ym` è la posizione di arrivo e `p` è il giocatore che ha effettuato la mossa.
![Comando Mosse](/docs/img/comando_mosse.png)

- `/tempo` : mostra il tempo trascorso dall'inizio della partita in formato `ore:minuti:secondi`.
![Comando Tempo](/docs/img/comando_tempo.png)

- `xn-ym` : permette di effettuare una mossa valida utilizzando la notazione algebrica `xn-ym` dove `xn` è la cella di partenza e `ym` è la cella di arrivo.
  - Se la mossa è valida e la distanza tra la cella di partenza e quella di arrivo è di 1, la pedina viene duplicata nella cella di arrivo.
  ![Comando Mossa Duplica](/docs/img/comando_mossa_duplica.png)
  - Se la mossa è valida e la distanza tra la cella di partenza e quella di arrivo è di 2, la pedina viene spostata nella cella di arrivo.
  ![Comando Mossa Salto](/docs/img/comando_mossa_salto.png)

    
### Possibili messaggi di errore

Durante l'esecuzione dell'applicazione si possono presentare delle situazioni non previste. Per ora ne esistono solo due
e vengono entrambe gestite comunicando all'utente l'errore.

#### Comando non previsto
Nel caso in cui non venisse inserito nessun comando tra quelli previsti nell `/help`, 
l'utente sarà avvisato dell'errore e gli verrà consigliato di digitare il comando `/help` per avere una lista dei comandi accettati.

![Comando non previsto](/docs/img/comando_non_previsto.png)

#### Nessuna partita in corso
Nel caso in cui venisse avviato uno dei comandi (`/tavoliere`, `/qualimosse`, `/abbandona`, `/tempo`, `/mosse`, `xn-ym`) prima di aver creato una partita, 
verrà segnalato all'utente con un messaggio di avviso.

![Nessuna partita in corso](/docs/img/nessuna_partita_in_corso.png)

#### Mossa non valida - Formato errato
Se la mossa inserita non è valida perché il formato non è corretto, verrà segnalato all'utente con un messaggio di errore.

![Mossa non valida - Formato errato](/docs/img/mossa_non_valida_formato_errato.png)

#### Mossa non valida - Celle non adiacenti
Se la mossa inserita non è valida perché le celle di partenza e arrivo non sono adiacenti, verrà segnalato all'utente con un messaggio di errore.

![Mossa non valida - Celle non adiacenti](/docs/img/mossa_non_valida_celle_non_adiacenti.png)

#### Mossa non valida - Cella già occupata
Se la mossa inserita non è valida perché la cella di arrivo è già occupata, verrà segnalato all'utente con un messaggio di errore.

![Mossa non valida - Cella già occupata](/docs/img/mossa_non_valida_cella_occupata.png)

#### Mossa non valida - Cella bloccata
Se la mossa inserita non è valida perché la cella di arrivo è bloccata, verrà segnalato all'utente con un messaggio di errore.

![Mossa non valida - Cella bloccata](/docs/img/mossa_non_valida_cella_bloccata.png)

#### Mossa non valida - Cella non appartenente al giocatore di turno
Se la mossa inserita non è valida perché la cella di partenza non appartiene al giocatore di turno, verrà segnalato all'utente con un messaggio di errore.

![Mossa non valida - Cella non appartenente al giocatore di turno](/docs/img/mossa_non_valida_cella_non_appartenente.png)

#### Mossa non valida - Riga o colonna non esistente
Se la mossa inserita non è valida perché la riga o la colonna non esistono, verrà segnalato all'utente con un messaggio di errore.

![Mossa non valida - Riga o colonna non esistente](/docs/img/mossa_non_valida_riga_colonna_non_esistente.png)

#### Lista delle mosse effettuate - Nessuna mossa effettuata
Se si tenta di visualizzare la lista delle mosse effettuate prima di aver effettuato alcuna mossa, verrà segnalato all'utente con un messaggio di avviso.

![Lista delle mosse effettuate - Nessuna mossa effettuata](/docs/img/lista_mosse_nessuna_mossa.png)

#### Blocco di una cella - Gioco in corso
Se si tenta di bloccare una cella mentre una partita è in corso, verrà segnalato all'utente con un messaggio di errore.

![Blocco di una cella - Gioco in corso](/docs/img/blocco_cella_gioco_in_corso.png)

#### Blocco di una cella - Cella già bloccata
Se si tenta di bloccare una cella già bloccata, verrà segnalato all'utente con un messaggio di errore.

![Blocco di una cella - Cella già bloccata](/docs/img/blocco_cella_gia_bloccata.png)

#### Blocco di una cella - Cella di partenza o adiacenti
Se si tenta di bloccare una delle celle di partenza o una delle celle adiacenti ad'una cella di partenza dei giocatori, verrà segnalato all'utente con un messaggio di errore.

![Blocco di una cella - Cella di partenza](/docs/img/blocco_cella_partenza_adiacenti.png)


#### Blocco di una cella - Numero massimo di celle bloccate raggiunto
Se si tenta di bloccare più di 9 celle, verrà segnalato all'utente con un messaggio di errore.

![Blocco di una cella - Numero massimo di celle bloccate raggiunto](/docs/img/blocco_celle_massimo_raggiunto.png)

#### Blocco di una cella - Formato cella errato
Se si tenta di bloccare una cella con un formato errato, verrà segnalato all'utente con un messaggio di errore.

![Blocco di una cella - Formato cella errato](/docs/img/blocco_cella_formato_errato.png)

## (8) Processo di sviluppo e organizzazione del lavoro
In questa sezione analizzeremo il processo di sviluppo utilizzato e la pianificazione del lavoro.

### (8.1) Processo di sviluppo adottato
Il gruppo Berners-Lee ha scelto, per la realizzazione di questo progetto, di adottare un processo di sviluppo agile basato sul framework Scrum; questo è stato scelto per gestire al meglio il ciclo di sviluppo del progetto Ataxx.

Il Product Owner ci ha assegnato diversi Sprint.
All'inizio di ogni Sprint si è svolto un Meeting con l'obiettivo di revisionare lo Sprint precedente e migliorare eventuali aspetti.
Ogni Sprint rappresenta un periodo di tempo (solitamente della durata di due settimane) in cui ogni componente del team sviluppa le funzionalità che sono state richieste.

### (8.2) Organizzazione del Lavoro
Lavorare all'interno di un team è fondamentale per il successo di un progetto. La collaborazione, il rispetto reciproco, la chiarezza dei ruoli e una comunicazione efficace sono i pilastri su cui si basa una squadra vincente.

Ogni membro del team ha contribuito con idee e soluzioni innovative. E' stato molto importante il supporto e il rispetto reciproco per superare le difficoltà e raggiungere gli obiettivi comuni.
Abbiamo sin da subito definito i compiti di ogni componente del team per evitare sovrapposizioni e confusioni.
I meeting periodici erano un elemento chiave della nostra organizzazione. Questi meeting si svolgevano alla fine di ogni task e avevano lo scopo di supervisionare il lavoro svolto, verificare che il lavoro fosse completato correttamente (secondo gli standard prefissati) e controllare l'assenza di problematiche, cioè identificare eventuali problemi o ostacoli che potevano rallentare il progresso del progetto e trovare soluzioni adeguate.

### (8.3) Utilizzo di piattaforme per la comunicazione
Per facilitare la comunicazione e l'organizzazione del lavoro, abbiamo utilizzato la piattaforma Discord.
La piattaforma Discord è stata utilizzata per organizzare meeting regolari, in cui discutevamo dei progressi del progetto, delle problematiche riscontrate e delle strategie future.
Durante i meeting, abbiamo suddiviso le issue tra i membri del team, assicurandoci che ognuno avesse chiari i propri compiti.
La piattaforma è stata utilizzata anche per le sessioni di review, in cui abbiamo controllato il lavoro svolto e fornito feedback per migliorare ulteriormente le funzionalità del progetto.
Anche in caso di supporto e di difficoltà, Discord è stato il nostro principale strumento di supporto, permettendoci di risolvere rapidamente qualsiasi inconveniente.

Grazie a questa struttura organizzativa e all'utilizzo efficace di Discord, siamo riusciti a mantenere un flusso di lavoro fluido e produttivo, garantendo la qualità e la puntualità delle scadenze.

### (8.4) Tool Utilizzati
Per lo svolgimento delle varie attività abbiamo utilizzato differenti tool per rappresentarle al meglio.
Uno di questi è stato **draw.io**, il quale è stato utilizzato per la realizzazione dell'analisi retrospettiva e quindi per realizzare lo schema arrabbiato/triste/felice.

Invece, per la creazione di bozze relative ai diagrammi delle classi abbiamo utilizzato **StarUML** per schematizzare meglio i concetti.

## (9) Analisi Retrospettiva

All'interno di questa sezione si trovano le relazioni dei vari Sprint
che verranno eseguiti durante lo sviluppo del progetto.

### (9.1) Sprint 0
![Retrospettiva Sprint 0](/docs/img/retrospettiva_sprint_0.png)

### (9.2) Sprint 1
![Retrospettiva Sprint 1](/docs/img/retrospettiva_sprint_1.png)

Nell'immagine precedente, abbiamo analizzato il modello di retrospettiva "Arrabbiato, Triste, Felice" per identificare 
gli aspetti da migliorare relativi allo Sprint 1. In particolare, esamineremo le soluzioni da adottare per affrontare eventuali problematiche emerse.

### Arrabbiato
La sezione "**Arrabbiato**" si riferisce a un errore grave emerso nello Sprint 1 nella creazione del modello di dominio. 
Come team, avremmo dovuto approfondire maggiormente la conoscenza del modello di dominio e delle sue caratteristiche. La formazione continua è cruciale per un team di progetto; una volta individuato l'errore, è necessario correggerlo e risolverlo tempestivamente.

### Triste
Nella sezione "Triste" emergono diverse problematiche:

- Difficoltà nell'implementazione: Alcuni membri del team hanno incontrato difficoltà nell'implementare alcune funzionalità a causa della scarsa familiarità con il linguaggio di programmazione. Possibili soluzioni includono il supporto da parte dei membri più esperti, la consultazione di materiale online e ulteriori sessioni di formazione interna.
- Ritardi nelle funzionalità: Alcuni ritardi sono dovuti ad altri impegni, come lo studio di altri corsi. La soluzione a questo problema è una pianificazione dettagliata delle attività, che tenga conto degli impegni universitari e personali, permettendo di adattare i carichi di lavoro in base alle esigenze individuali. È fondamentale incoraggiare una comunicazione aperta e trasparente riguardo agli impegni personali, consentendo al team di riorganizzare le attività e supportarsi a vicenda.

### Felice
L'ultima sezione evidenzia alcuni aspetti positivi del team:

- Distribuzione del carico di lavoro: Durante ogni Sprint, abbiamo mantenuto una distribuzione equilibrata del carico di lavoro.
- Ambiente collaborativo: Abbiamo creato un ambiente di lavoro collaborativo e di sostegno, in cui i membri del team si sentono a proprio agio nel chiedere aiuto e nel fornire assistenza.

Concludendo, è essenziale continuare a migliorare la nostra conoscenza tecnica, affinare la pianificazione delle attività e mantenere un ambiente di lavoro positivo e collaborativo.