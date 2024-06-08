package it.uniba.app;

import it.uniba.app.entities.Board;
import it.uniba.app.exceptions.InvalidPositionException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test per la classe {@link it.uniba.app.entities.Board.Position}.
 */
class PositionTest {
    /**
     * Testa che, dopo la chiamata al costruttore, le righe e le colonne siano valide.
     */
    @Test
    void constructorTest() {
        final var row = 0;
        final var column = 3;
        var position = new Board.Position(row, column);
        assertTrue(position.row() == row && position.column() == column,
            "La riga dovrebbe avere indice 0 e la colonna 3");
    }

    /**
     * Testa che, dopo la chiamata al costruttore, venga sollevata
     * un'eccezione nel caso in cui la riga non sia valida.
     */
    @Test
    void constructorThrowsTest() {
        final var row = 10;
        final var column = 3;
        assertThrows(InvalidPositionException.class, () -> new Board.Position(row, column),
            "La riga non dovrebbe essere valida");
    }

    /**
     * Testa che, dopo aver creato la posizione partendo dalla
     * stringa, questa abbia riga e colonna valide.
     */
    @Test
    void fromStringTest() {
        final var row = 0;
        final var column = 3;
        var position = Board.Position.fromString("a3");
        assertTrue(position.row() == row && position.column() == column,
            "La posizione dovrebbe essere a3");
    }

    /**
     * Testa che, dopo aver creato la posizione partendo dalla
     * stringa, venga sollevata un'eccezione nel caso in cui la posizione
     * non sia valida.
     */
    @Test
    void fromStringThrowsTest() {
        assertThrows(InvalidPositionException.class, () -> Board.Position.fromString("h9"),
            "La posizione non dovrebbe essere valida");
    }

    /**
     * Testa che, dopo aver passato come stringhe la posizione di partenza e
     * di arrivo, la distanza tra esse sia valida.
     */
    @Test
    void distanceTest() {
        assertAll(() -> {
            assertEquals(2, Board.Position.distance(
                    Board.Position.fromString("a3"),
                    Board.Position.fromString("a1")
                ),
                "La distanza dovrebbe essere 2");

            assertEquals(0, Board.Position.distance(
                    Board.Position.fromString("c7"),
                    Board.Position.fromString("c7")
                ),
                "La distanza dovrebbe essere 0");

            assertEquals(5, Board.Position.distance(
                    Board.Position.fromString("f7"),
                    Board.Position.fromString("f2")
                ),
                "La distanza dovrebbe essere 5");

            assertEquals(3, Board.Position.distance(
                    Board.Position.fromString("g6"),
                    Board.Position.fromString("g3")
                ),
                "La distanza dovrebbe essere 3");
        });
    }
}
