package it.uniba.app.commands;

import it.uniba.app.controls.AppController;
import it.uniba.app.entities.Board;
import it.uniba.app.utils.Strings;


/**
 * Classe << Boundary >> che si occupa di eseguire il comando `/blocca`.
 */
public class BlockCommand {
    /**
     * Riferimento al tabellone di gioco.
     */
    private static Board board = new Board();

    private BlockCommand() {
    }

    /**
     * Esegue il comando.
     * @param app riferimento al contesto dell'applicazione
     * @param position coordinate della casella da bloccare
     */
    public static Board run(final AppController app, final String position) {
        Board.Position p = Board.Position.fromString(position);

        if (app.getGame() != null)
            System.out.println(Strings.BlockCommand.GAME_RUNNING_EXCEPTION);
        else if (board.getBlockedCellsSize() >= board.getMaxBlockedCells())
            System.out.println(Strings.BlockCommand.MAX_BLOCKED_CELLS_EXCEPTION);
        else if(board.isCellBlocked(board.getCell(p)))
            System.out.println(Strings.BlockCommand.CELL_ALREADY_BLOCKED_EXCEPTION);
        //ADDME: Implementare il controllo delle celle non bloccabili

        else{
            board.addBlockedCell(p);
            System.out.println(Strings.BlockCommand.CELL_BLOCKED + p.toString());
        }

        return board;
    }
}
