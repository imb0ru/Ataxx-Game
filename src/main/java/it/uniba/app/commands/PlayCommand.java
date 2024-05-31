package it.uniba.app.commands;

import it.uniba.app.controls.AppController;
import it.uniba.app.boundaries.GamePrinter;
import it.uniba.app.controls.GameController;
import it.uniba.app.utils.Strings;

/**
 * Classe << Boundary >> che si occupa di eseguire il comando `/play`.
 * Comincia una nuova partita se non c'è già una in corso e stampa il tavoliere
 * iniziale.
 */
public final class PlayCommand {
    private PlayCommand() {
    }

    /**
     * Esegue il comando.
     *
     * @param app riferimento al contesto dell'applicazione.
     */
    public static void run(final AppController app) {
        if (app.getGame() == null) {
            app.setGame(new GameController(app.getBoard()));
            GamePrinter.print(app.getGame(), GamePrinter.PrintMoves.NO, GamePrinter.PrintGameState.YES);
        } else {
            System.out.println(Strings.PlayCommand.GAME_ALREADY_STARTED);
        }
    }
}
