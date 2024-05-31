package it.uniba.app.commands;

import it.uniba.app.controls.AppController;
import it.uniba.app.entities.Board;
import it.uniba.app.utils.Strings;

import java.util.HashSet;
import java.util.Set;


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
     * @param coordinate coordinate della casella da bloccare
     */
    public static void run(final AppController app, final String coordinate) {
        if (app.getGame() != null) {
            System.out.println(Strings.BlockCommand.GAME_RUNNING_EXCEPTION);
            return;
        }

        if (board.getBlockedCellsSize() >= board.getMaxBlockedCells()) {
            System.out.println(Strings.BlockCommand.MAX_BLOCKED_CELLS_EXCEPTION);
            return;
        }

        if (coordinate == null || coordinate.isEmpty()) {
            System.out.println(Strings.BlockCommand.INVALID_COORDINATES_EXCEPTION);
            return;
        }

        // Estrae le coordinate dal comando
        if (coordinate.length() != 2) {
            System.out.println(Strings.BlockCommand.INVALID_COORDINATES_EXCEPTION);
            return;
        }

        char colChar = coordinate.charAt(0);
        char rowChar = coordinate.charAt(1);

        if (colChar < 'a' || colChar > 'h' || rowChar < '1' || rowChar > '8') {
            System.out.println(Strings.BlockCommand.OUT_OF_BOUNDS_COORDINATES_EXCEPTION);
            return;
        }

        int x = colChar - 'a';
        int y = rowChar - '1';

        //ADDME: Implementare il controllo delle celle non bloccabili
        //ADDME: restituire la Board aggiornata, gestire questa Board in AppController

    }
}
