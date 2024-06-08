package it.uniba.app.commands;

import it.uniba.app.controls.AppController;
import it.uniba.app.entities.Board;
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
     * @param position coordinate della casella da bloccare
     */
    public static void run(final AppController app, final String position) {
        Board board = app.getBoard();
        Board.Position p;
        try {
             p = Board.Position.fromString(position);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return;
        }

        if (app.getGame() != null) {
            System.out.println(Strings.BlockCommand.GAME_RUNNING_EXCEPTION);
        } else if (board.getBlockedCellsSize() >= board.getMaxBlockedCells()) {
            System.out.println(Strings.BlockCommand.MAX_BLOCKED_CELLS_EXCEPTION);
        } else if (board.isCellBlocked(board.getCell(p))) {
            System.out.println(Strings.BlockCommand.CELL_ALREADY_BLOCKED_EXCEPTION);
        } else if (board.isStartingCell(p) || board.isAdjacentToStartingCell(p)) {
            System.out.println(Strings.BlockCommand.CELL_STARTING_EXCEPTION);
        } else {
            board.addBlockedCell(p);
            System.out.println(Strings.BlockCommand.CELL_BLOCKED + p.toString());
        }
    }
}
