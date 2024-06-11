package it.uniba.app.commands;

import it.uniba.app.controls.AppController;
import it.uniba.app.entities.Board;
import it.uniba.app.exceptions.UnblockableCellException;
import it.uniba.app.utils.Strings;


/**
 * Classe << Boundary >> che si occupa di eseguire il comando `/blocca`.
 */
public final class BlockCommand {
    private BlockCommand() {
    }

    /**
     * Esegue il comando.
     * @param app riferimento al contesto dell'applicazione
     * @param positionString coordinate della casella da bloccare
     */
    public static void run(final AppController app, final String positionString) {
        Board board = app.getBoard();
        Board.Position position;
        try {
             position = Board.Position.fromString(positionString);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return;
        }

        if (app.getGame() != null) {
            System.out.println(Strings.BlockCommand.GAME_RUNNING_EXCEPTION);
            return;
        }

        if (board.getCell(position) == Board.Cell.LOCKED) {
            System.out.println(Strings.BlockCommand.CELL_ALREADY_BLOCKED_EXCEPTION);
            return;
        }

        try {
            board.blockCell(position);
            System.out.printf(Strings.BlockCommand.CELL_BLOCKED_FORMAT, position);
        } catch (UnblockableCellException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
