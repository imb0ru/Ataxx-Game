package it.uniba.app;

import it.uniba.app.entities.Board;
import it.uniba.app.exceptions.UnblockableCellException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * CLasse di test per la classe {@link it.uniba.app.entities.Board}.
 */
class BoardTest {
    /**
     * Testa che la configurazione iniziale del tavoliere sia corretta.
     */
    @Test
    void initialBoardTest() {
        final var board = new Board();
        assertEquals(board.toString(), "B5EW35EW5EB", "Initial board is not correct");
    }

    /**
     * Testa che la configurazione del tavoliere dopo aver bloccato alcune celle sia corretta.
     */
    @Test
    void lockedCellsTest() throws UnblockableCellException {
        final var board = new Board();
        board.blockCell(Board.Position.fromString("c4"));
        board.blockCell(Board.Position.fromString("d3"));
        board.blockCell(Board.Position.fromString("d5"));
        board.blockCell(Board.Position.fromString("e4"));

        assertEquals(
            board.toString(),
            "B5EW10EL5ELEL5EL10EW5EB",
            "Board after locking cells is not correct");
    }

    /**
     * Testa che la configurazione del tavoliere dopo aver bloccato alcune celle e averle sbloccate sia quella iniziale.
     */
    @Test
    void resetLockedCellsTest() throws UnblockableCellException {
        final var board = new Board();
        board.blockCell(Board.Position.fromString("c4"));
        board.blockCell(Board.Position.fromString("d3"));
        board.blockCell(Board.Position.fromString("d5"));
        board.blockCell(Board.Position.fromString("e4"));
        board.clearBlockedCells();

        assertEquals(
            board.toString(),
            "B5EW35EW5EB",
            "Board after clearing locked cells is not correct");
    }
}
