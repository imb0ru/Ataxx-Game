package it.uniba.app;

import it.uniba.app.controls.AppController;
import it.uniba.app.commands.HelpCommand;
import it.uniba.app.utils.Strings;

import java.nio.charset.StandardCharsets;
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
     * Metodo main dell'applicazione.
     *
     * @param args argomenti della riga di comando
     */
    public static void main(final String[] args) {
        System.out.print(Strings.App.INTRO);
        if (args.length != 0) {
            if (args[0].equalsIgnoreCase(Strings.App.HELP_LONG_OPTION)
                || args[0].equalsIgnoreCase(Strings.App.HELP_SHORT_OPTION)) {
                HelpCommand.run();
            } else {
                System.out.println(Strings.App.UNRECOGNIZED_OPTION);
            }

            return;
        }

        Scanner keyboard = new Scanner(System.in, StandardCharsets.UTF_8);
        AppController appController = new AppController();

        System.out.println(Strings.App.WELCOME);
        System.out.println(Strings.App.INPUT);

        while (appController.isRunning()) {
            System.out.print(Strings.App.PROMPT);
            final String command = keyboard.nextLine().trim();
            appController.run(command);
        }

        keyboard.close();
    }
}
