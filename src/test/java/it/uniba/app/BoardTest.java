package it.uniba.app;

import it.uniba.app.entities.Board;
import it.uniba.app.exceptions.InvalidBoardException;
import it.uniba.app.exceptions.UnblockableCellException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
        assertEquals(board.toString(), "B5EW35EW5EB", "Tavoliere iniziale non corretto");
    }

    /**
     * Testa che venga lanciata un'eccezione quando c'è un carattere non valido nella stringa del tavoliere.
     */
    @Test
    void invalidCharacterInsideStringTest() {
        assertThrows(
            InvalidBoardException.class,
            () -> { final var board = new Board("B5EW35EW5EX"); },
            "Dovrebbe lanciare un'eccezione quando viene inserito un carattere non valido nella stringa.");
    }

    /**
     * Testa che venga lanciata un'eccezione quando il numero di celle della stringa non è valido.
     */
    @Test
    void tooLongStringTest() {
        assertThrows(
            InvalidBoardException.class,
            () -> { final var board = new Board("B - 50E"); },
            "Dovrebbe lanciare un'eccezione quando viene usata una stringa con un numero di celle invalido.");
    }

    /**
     * Testa che venga lanciata un'eccezione quando il numero di celle della stringa non è valido.
     */
    @Test
    void tooShortStringTest() {
        assertThrows(
            InvalidBoardException.class,
            () -> { final var board = new Board("B - 48E"); },
            "Dovrebbe lanciare un'eccezione quando viene usata una stringa con un numero di celle invalido.");
    }

    /**
     * Testa che venga lanciata un'eccezione quando c'è una cella bloccata nella stringa non valida.
     */
    @Test
    void invalidLockedCellInStringTest() {
        assertThrows(
            InvalidBoardException.class,
            () -> { final var board = new Board("B5EW35EW5EL"); },
            "Dovrebbe lanciare un'eccezione quando viene bloccata una cella non valida nella stringa.");
    }

    /**
     * Testa che venga lanciata un'eccezione quando il numero di celle bloccate supera il massimo nella stringa.
     */
    @Test
    void tooManyLockedCellsInStringTest() {
        assertThrows(
            InvalidBoardException.class,
            () -> { final var board = new Board("B5EW10EL3E7L3EL6EL3EW5EB"); },
            "Dovrebbe lanciare un'eccezione quando il numero di celle bloccate supera il massimo.");
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
            "Dopo aver bloccato le celle il tavoliere non e' corretto");
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
            "Dopo aver rimosso le celle bloccate il tavoliere non e' corretto.");
    }

    /**
     * Testa che venga lanciata un'eccezione quando si tenta di bloccare una cella iniziale.
     */
    @Test
    void blockInitialCellTest() {
        final var board = new Board();
        assertThrows(
            UnblockableCellException.class,
            () -> board.blockCell(Board.Position.fromString("a1")),
            "Dovrebbe lanciare un'eccezione quando si tenta di bloccare una cella iniziale."
        );
    }

    /**
     * Testa che venga lanciata un'eccezione quando si tenta di bloccare una cella adiacente a una iniziale.
     */
    @Test
    void blockAdjacentToInitialCellTest() {
        final var board = new Board();
        assertThrows(
            UnblockableCellException.class,
            () -> board.blockCell(Board.Position.fromString("a3")),
            "Dovrebbe lanciare un'eccezione quando si tenta di bloccare una cella adiacente a una iniziale."
        );
    }

    /**
     * Testa che venga lanciata un'eccezione quando si tenta di eccedere il numero massimo di celle bloccabili
     */
    @Test
    void maxLockedCellsTest() throws UnblockableCellException {
        final var board = new Board();
        board.blockCell(Board.Position.fromString("a4"));
        board.blockCell(Board.Position.fromString("b4"));
        board.blockCell(Board.Position.fromString("c4"));
        board.blockCell(Board.Position.fromString("d4"));
        board.blockCell(Board.Position.fromString("e4"));
        board.blockCell(Board.Position.fromString("f4"));
        board.blockCell(Board.Position.fromString("g4"));
        board.blockCell(Board.Position.fromString("d3"));
        board.blockCell(Board.Position.fromString("d5"));
        assertThrows(
            UnblockableCellException.class,
            () -> board.blockCell(Board.Position.fromString("d6")),
            "Dovrebbe lanciare un'eccezione quando viene superata il numero massimo di celle bloccabili.");
    }
}
