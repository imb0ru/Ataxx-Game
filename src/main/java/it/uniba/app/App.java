package it.uniba.app;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.uniba.app.commands.HelpCommand;

import java.util.Scanner;
/**
 * Classe App
 * << Boundary >>
 *
 * <p>Classe principale dell'applicazione.</p>
 */
public final class App {

    /**
     * Costruttore della classe App.
     */
    private App() { }

    /**
     * Banner di intro dell'applicazione.
     */
    private static final String INTRO = """
                                         \
            ___   ______    ___    _  __   _  __
                                        /   | /_  _\
            _/   /   |  | |/ /  | |/ /
                                       / /| |  / /\
                 / /| |  |   /   |   /\s
                                      / ___ | / / \
                / ___ | /   |   /   | \s
                                     /_/  |_|/_/  \
               /_/  |_|/_/|_|  /_/|_| \s
               ___    ____   ___    _  __   ____ \
              ___    ____                  __    _\
            ___   ____
              / _ )  / __/  / _ \\  / |/ /  / __/\
              / _ \\  / __/      ____       / /   /\
             __/  / __/
             / _  | / _/   / , _/ /    /  / _/   \
            / , _/ _\\ \\       /___/      / /__ \
            / _/   / _/ \s
            /____/ /___/  /_/|_| /_/|_/  /___/\
              /_/|_| /___/                 /____/\
            /___/  /___/ \s
                                             \
                                           \
                                \s
            """;

    /**
     * Metodo main dell'applicazione.
     *
     * @param args argomenti della riga di comando
     */
    @SuppressFBWarnings("DM_DEFAULT_ENCODING")
    public static void main(final String[] args) {
        Scanner keyboard = new Scanner(System.in);

        System.out.print(INTRO);
        if (args.length != 0) {
            if (args[0].equalsIgnoreCase("--help")
                    || args[0].equalsIgnoreCase("-h")) {
                new HelpCommand();
            } else {
                System.out.println("Opzione non riconosciuta. "
                        + "Usa --help o -h "
                        + "per visualizzare le opzioni disponibili.");
            }
        } else {
            System.out.println("Benvenuto in Ataxx!");
            System.out.print("Inserisci un comando "
                    + "per iniziare a giocare.\n> ");
            final String command = keyboard.nextLine();
            clearScreen();
            System.out.print(INTRO);
            new AppController(command);
        }
        keyboard.close();
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
}
