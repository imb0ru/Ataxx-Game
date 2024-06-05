package it.uniba.app.commands;

import it.uniba.app.controls.AppController;
import it.uniba.app.utils.Strings;

/**
 * Classe << Boundary >> che si occupa di eseguire una mossa inserita dall'utente.
 */
public class MoveCommand {
    private MoveCommand() {
    }

    /**
     * Esegue il comando.
     *
     * @param app riferimento al contesto dell'applicazione
     * @param moveString la stringa che contiene la mossa da eseguire
     */
    public static void run(final AppController app, final String moveString) {
        final var game = app.getGame();
        if (game == null) {
            System.out.println(Strings.Common.NO_RUNNING_GAME);
            return;
        }
    }
}
