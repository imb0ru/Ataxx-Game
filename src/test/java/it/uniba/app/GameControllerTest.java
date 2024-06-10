package it.uniba.app;

import it.uniba.app.controls.GameController;
import it.uniba.app.entities.Board;
import it.uniba.app.entities.Move;
import it.uniba.app.exceptions.InvalidGameException;
import it.uniba.app.exceptions.InvalidMoveException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
        Board board = new Board();
        final var game = new GameController(board);
        final var moves = game.getLegalMovesForCurrentPlayer();
        assertTrue(
            game.toString().equals("B - B5EW35EW5EB") && !moves.isEmpty(),
            "Partita iniziale non corretta.");
    }

    /**
     * Testa che venga lanciata un'eccezione quando la stringa di inizializzazione non è corretta.
     */
    @Test
    void invalidGameStringFormatTest() {
        assertThrows(
            InvalidGameException.class,
            () -> {
                final var game = new GameController("B - 49E - asdf");
            },
            "Dovrebbe lanciare un'eccezione se la stringa della partita non e' corretta.");
    }

    /**
     * Testa che venga lanciata un'eccezione quando la stringa di inizializzazione contiene un carattere non valido.
     */
    @Test
    void invalidCharacterInsideGameStringTest() {
        assertThrows(
            InvalidGameException.class,
            () -> {
                final var game = new GameController("X - 49E");
            },
            "Dovrebbe lanciare un'eccezione se un carattere non è valido nella stringa della partita.");
    }

    /**
     * Testa la vittoria del nero nel caso in cui il bianco non possieda pedine.
     */
    @Test
    void noWhiteCellsWinTest() throws InvalidGameException {
        final var game = new GameController("B - B48E");
        assertEquals(GameController.GameState.BLACK_WINS, game.getGameState(), "Dovrebbe vincere il nero.");
    }

    /**
     * Testa la vittoria del bianco nel caso in cui il nero non possieda pedine.
     */
    @Test
    void noBlackCellsWinTest() throws InvalidGameException {
        final var game = new GameController("B - W48E");
        assertEquals(GameController.GameState.WHITE_WINS, game.getGameState(), "Dovrebbe vincere il bianco.");
    }

    /**
     * Testa il caso in cui un giocatore non ha mosse disponibili.
     */
    @Test
    void noLegalMovesTest() throws InvalidGameException {
        final var game = new GameController("B - B2W4E3W4E3W4E28E");
        assertTrue(
            game.getLegalMovesForCurrentPlayer().isEmpty(),
            "Il nero non dovrebbe avere alcuna mossa legale.");
    }

    /**
     * Testa se eseguendo una mossa di tipo
     * {@link it.uniba.app.entities.Move.Type#JUMP_AND_REPLICATE}
     * la pedina viene spostata e replicata correttamente.
     */
    @Test
    void correctJumpAndReplicateMoveTest() throws InvalidGameException {
        final var fromRow = 4;
        final var fromColumn = 3;
        final var toRow = 3;
        final var toColumn = 3;

        final var game = new GameController("B - 31EB17E");
        game.executeMove(new Move(
            new Board.Position(fromRow, fromColumn),
            new Board.Position(toRow, toColumn),
            Board.Cell.BLACK));
        assertEquals("W - 24EB6EB17E", game.toString(), "Stato del gioco non corretto.");
    }

    /**
     * Testa se eseguendo una mossa di tipo
     * {@link it.uniba.app.entities.Move.Type#JUMP}
     * la pedina viene spostata correttamente.
     */
    @Test
    void correctJumpMoveTest() throws InvalidGameException {
        final var fromRow = 4;
        final var fromColumn = 3;
        final var toRow = 2;
        final var toColumn = 3;

        final var game = new GameController("B - 31EB17E");
        game.executeMove(new Move(
            new Board.Position(fromRow, fromColumn),
            new Board.Position(toRow, toColumn),
            Board.Cell.BLACK));
        assertEquals("W - 17EB31E", game.toString(), "Stato del gioco non corretto.");
    }

    /**
     * Testa che quando il giocatore si sposta in una pedina adiacente a delle
     * celle nemiche queste vengano convertite.
     */
    @Test
    void moveConvertsAdjacentEnemyCellsTest() throws InvalidGameException {
        final var fromRow = 4;
        final var fromColumn = 3;
        final var toRow = 3;
        final var toColumn = 3;

        final var game = new GameController("B - 16E3W12EB17E");
        game.executeMove(new Move(
            new Board.Position(fromRow, fromColumn),
            new Board.Position(toRow, toColumn),
            Board.Cell.BLACK));
        assertEquals("W - 16E3B5EB6EB17E", game.toString(), "Stato del gioco non corretto.");
    }

    /**
     * Testa che quando il giocatore prova a fare una mossa che ha come cella di destinazione una cella bloccata dia
     * errore.
     */
    @Test
    void moveToBlockedCellTest() throws InvalidGameException {
        final var game = new GameController("W - B5EW17EL13EW10E");
        assertThrows(
            InvalidMoveException.class, () -> {
            game.executeMove(new Move(
                Board.Position.fromString("d6"),
                Board.Position.fromString("d4"),
                Board.Cell.WHITE));
            },
            "Dovrebbe lanciare un'eccezione se si prova a spostarsi su una cella bloccata.");
    }
}
