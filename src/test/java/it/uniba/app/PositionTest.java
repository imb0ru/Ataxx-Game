package it.uniba.app;

import it.uniba.app.entities.Board;
import it.uniba.app.exceptions.InvalidPositionException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test per la classe {@link it.uniba.app.entities.Board.Position}.
 */
class PositionTest {
    /**
     * Testa che, dopo la chiamata al costruttore, le righe e le colonne siano valide.
     */
    @DisplayName("constructorTest: Costruttore con parametri validi")
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
    @DisplayName("constructorThrowsTest: Costruttore con parametri non validi")
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
    @DisplayName("fromStringTest: Creazione posizione da una stringa valida")
    @Test
    void fromStringTest() {
        final var row = 2;
        final var column = 0;
        var position = Board.Position.fromString("a3");
        assertTrue(position.row() == row && position.column() == column,
            "La posizione dovrebbe essere a3");
    }

    /**
     * Testa che, dopo aver creato la posizione partendo dalla
     * stringa, venga sollevata un'eccezione nel caso in cui la posizione
     * non sia valida.
     */
    @DisplayName("fromStringThrowsTest: Creazione posizione da una stringa non valida")
    @Test
    void fromStringThrowsTest() {
        assertThrows(InvalidPositionException.class, () -> Board.Position.fromString("h9"),
            "La posizione non dovrebbe essere valida");
    }

    /**
     * Testa che, dopo aver passato come stringhe la posizione di partenza e
     * di arrivo, la distanza tra esse sia valida.
     */
    @DisplayName("distanceTest: Calcolo distanza tra due posizioni")
    @Test
    void distanceTest() {
        record TestCase(Board.Position from, Board.Position to, int expectedDistance) { }
        final var testCases = new TestCase[] {
            new TestCase(
                Board.Position.fromString("a3"),
                Board.Position.fromString("a1"),
                2),
            new TestCase(
                Board.Position.fromString("c7"),
                Board.Position.fromString("c7"),
                0),
            new TestCase(
                Board.Position.fromString("f7"),
                Board.Position.fromString("f2"),
                5),
            new TestCase(
                Board.Position.fromString("g6"),
                Board.Position.fromString("d3"),
                3),
        };

        for (TestCase testCase : testCases) {
            assertEquals(testCase.expectedDistance, Board.Position.distance(testCase.from, testCase.to),
                "La distanza tra le posizioni dovrebbe essere " + testCase.expectedDistance);
        }
    }
}
