package it.uniba.app;

import it.uniba.app.controls.AppController;
import it.uniba.app.commands.HelpCommand;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * Classe App
 * << Boundary >>
 *
 * <p>Classe principale dell'applicazione.</p>
 */
public final class App {
    private App() {
    }

    /**
     * Banner di intro dell'applicazione.
     */
    public static final String INTRO = """
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
    public static void main(final String[] args) {
        System.out.print(INTRO);
        if (args.length != 0) {
            if (args[0].equalsIgnoreCase("--help")
                || args[0].equalsIgnoreCase("-h")) {
                HelpCommand.run();
            } else {
                System.out.println("Opzione non riconosciuta.\n"
                                   + "Usa --help o -h per visualizzare le opzioni disponibili.");
            }

            return;
        }

        Scanner keyboard = new Scanner(System.in, StandardCharsets.UTF_8);
        AppController appController = new AppController();

        System.out.println("Benvenuto in Ataxx!");
        System.out.println("Inserisci un comando per iniziare a giocare.");

        while (true) {
            System.out.print("> ");
            final String command = keyboard.nextLine().trim();
            // FIXME: Remove this when we have '/esci'
            if (command.isEmpty()) {
                break;
            }

            appController.run(command);
        }

        keyboard.close();
    }
}
