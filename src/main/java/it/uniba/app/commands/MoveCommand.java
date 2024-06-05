package it.uniba.app.commands;

import it.uniba.app.boundaries.GamePrinter;
import it.uniba.app.controls.AppController;
import it.uniba.app.entities.Board;
import it.uniba.app.entities.Move;
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

        final var parts = moveString.split("-");
        if (parts.length != 2)
            System.out.println(Strings.MoveCommand.INVALID_MOVE_FORMAT);

        try {
            final var fromPosition = Board.Position.fromString(parts[0].trim());
            final var toPosition = Board.Position.fromString(parts[1].trim());
            final var move = new Move(fromPosition, toPosition, game.getCurrentPlayer());
            game.executeMove(move);
            GamePrinter.print(game, GamePrinter.PrintMoves.NO, GamePrinter.PrintGameState.YES);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }
}
