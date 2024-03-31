# Assegnazione Progetto

## ATAXX


![ATAXX](./img/ataxx.png)

Le regole del gioco sono le seguenti:
* si gioca su una griglia 7x7;
* partecipatono due giocatori: uno rosso e uno blu;
* ogni giocatore comincia con due pedine:
    * le pedine rosse si trovano nell'angolo in alto a sinistra e nell'angolo in basso a destra;
    * le pedine blu si trovano nell'angolo in alto a destra e nell'angolo in basso a sinistra.
* a turno, i giocatori spostano uno dei loro pedine di una o due caselle in qualsiasi direzione.
    1. se la casella di destinazione è adiacente a quella di origine allora la pedina viene duplicato, altrimenti viene solo spostata;
    2. dopo la mossa, tutti le pedine dell'altro giocatore adiacenti alla casella di destinazione vengono convertite nel colore del giocatore che ha fatto la mossa.
* la partita prosegue in questo modo finché non vengono riempite tutte le caselle o finché uno dei giocatori non sia impossibilitato a muovere;
* vince il giocatore che al termine della partita ha più pedine del proprio colore, la partita non può terminare con un pareggio.
