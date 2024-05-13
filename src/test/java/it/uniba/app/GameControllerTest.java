package it.uniba.app;

import it.uniba.app.controls.GameController;
import it.uniba.app.entities.Board;
import it.uniba.app.entities.Move;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test
 */
class GameControllerTest {
    /**
     * Testa che inizialmente la partita sia in corso, che il giocatore
     * corrente sia il nero, che il tavoliere sia quello iniziale e che ci siano
     * mosse legali disponibili per il giocatore corrente.
     */
    @Test
    void initialGameTest() {
        final var game = new GameController();
        assertEquals("B - B5EW35EW5EB", game.toString(), "Initial game is not correct");

        final var moves = game.getLegalMovesForCurrentPlayer();
        assertFalse(moves.isEmpty());
    }

    /**
     * Testa la vittoria di entrambi i giocatori nel caso in cui l'avversario
     * non possiede pedine.
     */
    @Test
    void noEnemyCellsWinTest() {
        try {
            final var game = new GameController("B - B48E");
            assertEquals(GameController.GameState.BLACK_WINS, game.getGameState(), "Black should win");
        } catch (Exception e) {
            fail(e);
        }

        try {
            final var game = new GameController("B - W48E");
            assertEquals(GameController.GameState.WHITE_WINS, game.getGameState(), "White should win");
        } catch (Exception e) {
            fail(e);
        }
    }

    /**
     * Testa il caso in cui un giocatore non ha mosse disponibili
     */
    @Test
    void noLegalMovesTest() {
        try {
            final var game = new GameController("B - B2W4E3W4E3W4E28E");
            assertTrue(game.getLegalMovesForCurrentPlayer().isEmpty(), "Black should have no legal moves");
        } catch (Exception e) {
            fail(e);
        }
    }

    /**
     * Testa se eseguendo una mossa di tipo
     * {@link it.uniba.app.entities.Move.Type#JUMP_AND_REPLICATE}
     * la pedina viene spostata e replicata correttamente
     */
    @Test
    void correctJumpAndReplicateMoveTest() {
        try {
            final var game = new GameController("B - 31EB17E");
            game.executeMove(new Move(
                    new Board.Position(4, 3),
                    new Board.Position(3, 3)));
            assertEquals("W - 24EB6EB17E", game.toString(), "Game state is not correct");
        } catch (Exception e) {
            fail(e);
        }
    }

    /**
     * Testa se eseguendo una mossa di tipo
     * {@link it.uniba.app.entities.Move.Type#JUMP}
     * la pedina viene spostata correttamente
     */
    @Test
    void correctJumpMoveTest() {
        try {
            final var game = new GameController("B - 31EB17E");
            game.executeMove(new Move(
                    new Board.Position(4, 3),
                    new Board.Position(2, 3)));
            assertEquals("W - 17EB31E", game.toString(), "Game state is not correct");
        } catch (Exception e) {
            fail(e);
        }

    }

    /**
     * Testa che quando il giocatore si sposta in una pedina adiacente a delle
     * celle nemiche queste vengano convertite.
     */
    @Test
    void moveConvertsAdjacentEnemyCellsTest() {
        try {
            final var game = new GameController("B - 16E3W12EB17E");
            game.executeMove(new Move(
                    new Board.Position(4, 3),
                    new Board.Position(3, 3)));
            assertEquals("W - 16E3B5EB6EB17E", game.toString(), "Game state is not correct");
        } catch (Exception e) {
            fail(e);
        }
    }
}
