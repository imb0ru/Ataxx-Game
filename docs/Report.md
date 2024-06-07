# Report

## Indice
1. [Introduzione](#1-introduzione)
2. [Modello di dominio](#2-modello-di-dominio)
3. [Requisiti specifici](#3-requisiti-specifici) <br>
   3.1 [Requisiti funzionali](#31-requisiti-funzionali) <br>
   3.2 [Requisiti non funzionali](#32-requisiti-non-funzionali) <br>
4. _TDB_
5. _TDB_
6. _TDB_
7. [Manuale utente](#7-manuale-utente)
8. _TDB_
9. [Analisi Retrospettiva](#9-analisi-retrospettiva) <br>
9.1 [Sprint 0](#91-sprint-0) <br>

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
![Modello di Dominio](/docs/img/modello_dominio.png)

La logica del dominio di interesse del sistema software ruota attorno 
al concetto di `GameController`.

I concetti più importanti a cui fa riferimento sono i seguenti:

| Concetto di appartenenza | Attributo   | Descrizione                                                                                                                                  |
|--------------------------|-------------|----------------------------------------------------------------------------------------------------------------------------------------------|
| `Board`                  | `board`     | È il riferimento al tavoliere che contiene le pedine della partita.                                                                          |
| `Cell`                   | `cells`     | È l'array che contiene tutte le celle del tavoliere. Possono essere vuote (`EMPTY`), nere (`BLACK`) o bianche (`WHITE`).                     |
| `GameState`              | `gameState` | È lo stato in cui la partita si può trovare. Può essere in corso (`IN_PROGRESS`), vinta dal nero (`BLACK_WINS`) o dal bianco (`WHITE_WINS`). |

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
  - (RF8.1) Al comando `/mosse` l'applicazione mostra a schermo la storia delle mosse con notazione algebrica `m. x1n1 x2n2 (p)` dove `m` è il numero della mossa, `x1n1` è la posizione di partenza e `x2n2` è la posizione di arrivo e `p` è il giocatore che ha effettuato la mossa.
- (RF9) Il sistema deve permettere di visualizzare il tempo di gioco.
  - (RF9.1) Al comando `/tempo` l'applicazione mostra a schermo il tempo trascorso dall'inizio della partita in formato `ore:minuti:secondi`.
- (RF10) Il sistema deve permettere di impostare caselle non accessibili.
  - (RF10.1) Al comando `/blocca xn`, se nessuna partita è in corso, l'applicazione blocca la cella che si trova alla riga `x` e colonna `n` del tavoliere, la cella viene mostrata con sul tavoliere con uno sfondo grigio e non può essere occupata da nessuna pedina.
  - (RF10.2) Non è possibile bloccare:
    - (RF10.2.1) le celle di partenza dei giocatori
    - (RF10.2.2) tutte le caselle adiacenti ad una casella di partenza del gioco, rendendo impossibile la mossa di espansione di una pedina a inizio gioco
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

### Possibili messaggi di errore

Durante l'esecuzione dell'applicazione si possono presentare delle situazioni non previste. Per ora ne esistono solo due
e vengono entrambe gestite comunicando all'utente l'errore.

#### Comando non previsto

Nel caso in cui non venisse inserito nessun comando tra quelli previsti nell `/help`, 
l'utente sarà avvisato dell'errore e gli verrà consigliato di digitare il comando `/help` per avere una lista dei comandi accettati.

![Comando non previsto](/docs/img/comando_non_previsto.png)

#### Nessuna partita in corso

Nel caso in cui venisse avviato uno dei comandi (`/tavoliere`, `/qualimosse`, `/abbandona`) prima di aver creato una partita, 
verrà segnalato all'utente con un messaggio di avviso.

![Nessuna partita in corso](/docs/img/nessuna_partita_in_corso.png)

## (9) Analisi Retrospettiva

All'interno di questa sezione si trovano le relazioni dei vari Sprint
che verranno eseguiti durante lo sviluppo del progetto.

### (9.1) Sprint 0
![Retrospettiva Sprint 0](/docs/img/retrospettiva_sprint_0.png)
