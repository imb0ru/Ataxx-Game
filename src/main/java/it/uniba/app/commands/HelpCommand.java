package it.uniba.app.commands;

/**
 * Classe Help
 * << Boundary >>
 *
 * <p>Classe che visualizza le informazioni di aiuto
 * per l'utilizzo dell'applicazione.</p>
 */
public class HelpCommand {

        /**
         * Costruttore della classe Help.
         * Visualizza le informazioni di aiuto per l'utilizzo
         */
        public HelpCommand() {
            System.out.println("ATAXX 1.0.0 Team Berners-Lee\n");
            System.out.println("""
                    Prodotto da:
                      - Marco Ferrara
                      - Davide Carella
                      - Maria Teresa Di Chio
                      - Luca Ardito
                      - Yuri Colangelo""");

            System.out.println("\nUtilizzo: java -jar <jarfile> [opzioni]\n");

            System.out.println("Opzioni:");
            System.out.println("  -h, --help\t\t"
                    + "Visualizza questo messaggio di aiuto\n");

            System.out.println("Comandi disponibili durante"
                    + " l'esecuzione:");
            System.out.println("  /help\t\t\t\t"
                    + "Visualizza questo messaggio di aiuto");
            System.out.println("  /info\t\t\t\t"
                    + "Visualizza le informazioni dell'applicazione");
            System.out.println("  /gioca\t\t\t"
                    + "Avvia il gioco se nessuna partita è in corso");
            System.out.println("  /vuoto\t\t\t"
                    + "Mostra il tavoliere vuoto");
            System.out.println("  /tavoliere\t\t"
                    + "In gioco, mostra la posizione di tutte le"
                    + " pedine sul tavoliere");
            System.out.println("  /qualimosse\t\t"
                    + "In gioco, mostra tutte le mosse disponibili"
                    + " per il giocatore di turno");
            System.out.println("  /abbandona\t\t"
                    + "In gioco, abbandona la partita in corso,"
                    + " previa conferma");
            System.out.println("  /esci\t\t\t\t"
                    + "Esce dall'applicazione previa conferma");
        }
}
