package it.uniba.app;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import java.util.Scanner;

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
     * Metodo che pulisce la console.
     * Utilizzato per nascondere i comandi
     * precedenti dell'utente.
     */
    public static void clearScreen() {
        final int numLines = 50;
        for (int i = 0; i < numLines; ++i) {
            System.out.println();
        }
    }


    /**
     * Metodo che gestisce l'avvio dell'applicazione.
     * Se non vengono passati argomenti, viene visualizzato
     * il banner di intro dell'applicazione.
     * Se vengono passati argomenti, vengono gestiti
     * i comandi da linea di comando passati dall'utente.
     *
     * SuppressFBWarnings: DM_DEFAULT_ENCODING
     * per la gestione della codifica di default
     * della console.
     *
     * @param args argomenti della riga di comando
     */
    @SuppressFBWarnings("DM_DEFAULT_ENCODING")
    public void run(final String[] args) {
        Scanner k = new Scanner(System.in);
        System.out.print(INTRO);
        if (args.length != 0) {
            if (args[0].equalsIgnoreCase("--help")
                    || args[0].equalsIgnoreCase("-h")) {
                new Help();
            } else {
                System.out.println("Opzione non riconosciuta. "
                        + "Usa --help o -h "
                        + "per visualizzare le opzioni disponibili.");
            }
        } else {
            System.out.println("Benvenuto in Ataxx!");
            System.out.print("Inserisci un comando "
                    + "per iniziare a giocare.\n> ");
            String command = k.nextLine();
            clearScreen();
            System.out.print(INTRO);
            switch (command) {
                case "/help":
                    new Help();
                    break;
                default:
                    System.out.println("Comando non riconosciuto."
                            + " Usa /help per visualizzare i "
                            + "comandi disponibili.");
            }
        }
    }
}
