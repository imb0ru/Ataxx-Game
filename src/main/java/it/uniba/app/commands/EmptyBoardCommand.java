package it.uniba.app.commands;

import it.uniba.app.boundaries.GamePrinter;
import it.uniba.app.controls.GameController;
import it.uniba.app.exceptions.InvalidBoardException;
import it.uniba.app.exceptions.InvalidGameException;

/**
 * Classe << Boundary >> che si occupa di eseguire il comando `/vuoto`.
 * Stampa a video il tavoliere del gioco senza pedine.
 */
public final class EmptyBoardCommand {
    private EmptyBoardCommand() {
    }

    /**
     * Esegue il comando.
     */
    public static void run() {
        GameController game = null;

        try {
             game = new GameController("W - 49E");
        } catch (InvalidGameException | InvalidBoardException exception) {
            // NOTE: This should never happen.
            throw new AssertionError(exception);
        }

        GamePrinter.print(game, GamePrinter.PrintMoves.NO);

    }
}
