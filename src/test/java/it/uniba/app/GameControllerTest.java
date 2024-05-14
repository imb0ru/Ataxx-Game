package it.uniba.app;

import it.uniba.app.controls.GameController;
import it.uniba.app.entities.Board;
import it.uniba.app.entities.Move;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Test per la classe {@link GameController}.
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
        final var moves = game.getLegalMovesForCurrentPlayer();
        assertTrue(game.toString().equals("B - B5EW35EW5EB") && !moves.isEmpty(), "Initial game is not correct");
    }

    /**
     * Testa la vittoria del nero nel caso in cui il bianco non possieda pedine.
     */
    @Test
    void noWhiteCellsWinTest() {
        try {
            final var game = new GameController("B - B48E");
            assertEquals(GameController.GameState.BLACK_WINS, game.getGameState(), "Black should win");
        } catch (Exception e) {
            fail(e);
        }
    }

    /**
     * Testa la vittoria del bianco nel caso in cui il nero non possieda pedine.
     */
    @Test
    void noBlackCellsWinTest() {
        try {
            final var game = new GameController("B - W48E");
            assertEquals(GameController.GameState.WHITE_WINS, game.getGameState(), "White should win");
        } catch (Exception e) {
            fail(e);
        }
    }

    /**
     * Testa il caso in cui un giocatore non ha mosse disponibili.
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
     * la pedina viene spostata e replicata correttamente.
     */
    @Test
    void correctJumpAndReplicateMoveTest() {
        try {
            final var fromRow = 4;
            final var fromColumn = 3;
            final var toRow = 3;
            final var toColumn = 3;

            final var game = new GameController("B - 31EB17E");
            game.executeMove(new Move(
                    new Board.Position(fromRow, fromColumn),
                    new Board.Position(toRow, toColumn)));
            assertEquals("W - 24EB6EB17E", game.toString(), "Game state is not correct");
        } catch (Exception e) {
            fail(e);
        }
    }

    /**
     * Testa se eseguendo una mossa di tipo
     * {@link it.uniba.app.entities.Move.Type#JUMP}
     * la pedina viene spostata correttamente.
     */
    @Test
    void correctJumpMoveTest() {
        try {
            final var fromRow = 4;
            final var fromColumn = 3;
            final var toRow = 2;
            final var toColumn = 3;

            final var game = new GameController("B - 31EB17E");
            game.executeMove(new Move(
                    new Board.Position(fromRow, fromColumn),
                    new Board.Position(toRow, toColumn)));
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
            final var fromRow = 4;
            final var fromColumn = 3;
            final var toRow = 3;
            final var toColumn = 3;

            final var game = new GameController("B - 16E3W12EB17E");
            game.executeMove(new Move(
                    new Board.Position(fromRow, fromColumn),
                    new Board.Position(toRow, toColumn)));
            assertEquals("W - 16E3B5EB6EB17E", game.toString(), "Game state is not correct");
        } catch (Exception e) {
            fail(e);
        }
    }
}
