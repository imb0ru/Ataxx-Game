package it.uniba.app;

/**
 * Classe AppController
 * << Boundary >>
 *
 * Controller principale dell'applicazione.
 * Gestisce le chiamate dei comandi di gioco in base alle azioni dell'utente.
 */
public class AppController {
        private static final String intro = "                             ___   ______    ___    _  __   _  __\n" +
                "                            /   | /_  __/   /   |  | |/ /  | |/ /\n" +
                "                           / /| |  / /     / /| |  |   /   |   / \n" +
                "                          / ___ | / /     / ___ | /   |   /   |  \n" +
                "                         /_/  |_|/_/     /_/  |_|/_/|_|  /_/|_|  \n" +
                "   ___    ____   ___    _  __   ____   ___    ____                  __    ____   ____\n" +
                "  / _ )  / __/  / _ \\  / |/ /  / __/  / _ \\  / __/      ____       / /   / __/  / __/\n" +
                " / _  | / _/   / , _/ /    /  / _/   / , _/ _\\ \\       /___/      / /__ / _/   / _/  \n" +
                "/____/ /___/  /_/|_| /_/|_/  /___/  /_/|_| /___/                 /____//___/  /___/  \n" +
                "                                                                                     \n";


        public AppController(String[] args) {
            run(args);
        }


        public void run(String[] args) {
            System.out.print(intro);
            if(args.length != 0) {
                if (args[0].equalsIgnoreCase("--help") || args[0].equalsIgnoreCase("-h")) {
                    System.out.println("Utilizzo: java -jar <jarfile> [opzioni]");
                    System.out.println("Opzioni:");
                    System.out.println("  Help\t\tVisualizza questo messaggio di aiuto e esce");
                    System.out.println("  Info\t\tVisualizza le informazioni dell'applicazione e esce");
                    //Aggiungere lista e descrizione degli altri comandi
                } else {
                    System.out.println("Opzione non riconosciuta. Usa --help o -h per visualizzare le opzioni disponibili.");
                }
            } else {
                System.out.println("Benvenuto in Ataxx!");
                //gestione input utente con chiamate a classi dedicate per ogni comando
            }
        }


}
