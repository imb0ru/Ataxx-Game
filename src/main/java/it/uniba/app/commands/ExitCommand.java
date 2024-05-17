package it.uniba.app.commands;

import it.uniba.app.controls.AppController;
import it.uniba.app.utils.Strings;

/**
 * Classe ExitCommand
 * << Boundary >>
 *
 * <p>Classe che gestisce l'uscita dell'applicazione.</p>
 */
public final class ExitCommand {
    private ExitCommand() {
    }

    /**
     * Esegue il comando.
     */
    public static void run(final AppController app) {
        System.out.println(Strings.ExitCommand.EXIT_CONFIRMATION);
        final String command = app.getKeyboard().nextLine().trim();
        if (command.equalsIgnoreCase("s")) {
            app.setRunning(false);
        }
    }
}
