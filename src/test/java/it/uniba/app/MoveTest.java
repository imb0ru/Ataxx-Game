package it.uniba.app;

import it.uniba.app.entities.Board;
import it.uniba.app.entities.Move;
import it.uniba.app.exceptions.InvalidMoveException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Test per la classe {@link it.uniba.app.entities.Move}.
 */
class MoveTest {
    /**
     * Testa che, dopo aver chiamato il costruttore, la mossa
     * contenga cella di partenza e cella di arrivo che sono stati
     * specificati.
     */
    @Test
    void constructorTest() {
        final var move = new Move(Board.Position.fromString("a1"), Board.Position.fromString("a3"), Board.Cell.WHITE);
        assertEquals("a1-a3 (W)", move.toString(), "La mossa dovrebbe essere a1-a3 (W)");
    }

    /**
     * Testa che, dopo aver chiamato il costruttore, la distanza tra la cella di
     * partenza e la cella di arrivo è maggiore di quella consentita e solleva, di
     * conseguenza, un'eccezione.
     */
    @Test
    void constructorThrowsTest() {
        assertThrows(InvalidMoveException.class, () -> {
            final var move = new Move(
                Board.Position.fromString("a1"),
                Board.Position.fromString("a5"),
                Board.Cell.WHITE);
        }, "La mossa non dovrebbe essere valida a causa della distanza");
    }

    /**
     * Testa che, dopo aver passato la cella di partenza e la cella di
     * arrivo, la tipologia della mossa sia quella corretta.
     */
    @Test
    void moveTypeTest() {
        record TestCase(Move move, Move.Type expectedType) { }
        final var testCases = new TestCase[] {
            new TestCase(new Move(
                Board.Position.fromString("a1"),
                Board.Position.fromString("a3"),
                Board.Cell.WHITE
            ), Move.Type.JUMP),
            new TestCase(new Move(
                Board.Position.fromString("a1"),
                Board.Position.fromString("a2"),
                Board.Cell.WHITE
            ), Move.Type.JUMP_AND_REPLICATE)
        };

        for (final TestCase testCase : testCases) {
            assertEquals(testCase.expectedType, testCase.move.getType(),
                "La tipologia della mossa dovrebbe essere " + testCase.expectedType);
        }
    }
}
