package it.uniba.app;

/**
 * Classe AppController
 * << Boundary >>
 *
 * <p>Controller principale dell'applicazione.
 * Gestisce le chiamate dei comandi di gioco
 * in base alle azioni dell'utente.</P>
 */
public class AppController {
    /**
     * Banner di intro dell'applicazione.
     */
    private static final String INTRO = "                             "
            + "___   ______    ___    _  __   _  __\n"
            + "                            /   | /_  _"
            + "_/   /   |  | |/ /  | |/ /\n"
            + "                           / /| |  / /"
            + "     / /| |  |   /   |   / \n"
            + "                          / ___ | / / "
            + "    / ___ | /   |   /   |  \n"
            + "                         /_/  |_|/_/  "
            + "   /_/  |_|/_/|_|  /_/|_|  \n"
            + "   ___    ____   ___    _  __   ____ "
            + "  ___    ____                  __    _"
            + "___   ____\n"
            + "  / _ )  / __/  / _ \\  / |/ /  / __/"
            + "  / _ \\  / __/      ____       / /   /"
            + " __/  / __/\n"
            + " / _  | / _/   / , _/ /    /  / _/   "
            + "/ , _/ _\\ \\       /___/      / /__ "
            + "/ _/   / _/  \n"
            + "/____/ /___/  /_/|_| /_/|_/  /___/"
            + "  /_/|_| /___/                 /____/"
            + "/___/  /___/  \n"
            + "                                 "
            + "                               "
            + "                     \n";

    /**
     * Costruttore della classe AppController.
     *
     * @param args argomenti della riga di comando
     *             passati all'avvio dell'applicazione
     */
    public AppController(final String[] args) {
        run(args);
    }

    /**
     * Metodo che gestisce l'avvio dell'applicazione.
     * Se non vengono passati argomenti, viene visualizzato
     * il banner di intro dell'applicazione.
     * Se vengono passati argomenti, vengono gestiti
     * i comandi da linea di comando passati dall'utente.
     *
     * @param args argomenti della riga di comando
     */
    public void run(final String[] args) {
        System.out.print(INTRO);
        if (args.length != 0) {
            if (args[0].equalsIgnoreCase("--help")
                    || args[0].equalsIgnoreCase("-h")) {
                System.out.println("Utilizzo: java -jar <jarfile> [opzioni]");
                System.out.println("Opzioni:");
                System.out.println("  Help\t\t"
                        + "Visualizza questo messaggio di aiuto");
                System.out.println("  Info\t\t"
                        + "Visualizza le informazioni dell'applicazione");
                //Aggiungere lista e descrizione degli altri comandi
            } else {
                System.out.println("Opzione non riconosciuta. "
                        + "Usa --help o -h "
                        + "per visualizzare le opzioni disponibili.");
            }
        } else {
            System.out.println("Benvenuto in Ataxx!");
            //gestione input utente con chiamate
            // a classi dedicate per ogni comando
        }
    }


}
