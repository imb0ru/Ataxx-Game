package it.uniba.app.controls;

import it.uniba.app.entities.Board;
import it.uniba.app.entities.Move;
import it.uniba.app.exceptions.InvalidBoardException;
import it.uniba.app.exceptions.InvalidGameException;
import it.uniba.app.exceptions.InvalidMoveException;
import it.uniba.app.utils.Strings;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Classe << Control >> che gestisce la logica del gioco.
 */
public final class GameController {
    /**
     * Enumerazione che rappresenta gli stati in cui si può trovare la partita.
     */
    public enum GameState {
        /**
         * La partita è ancora in corso.
         */
        IN_PROGRESS,
        /**
         * Il giocatore nero ha vinto.
         */
        BLACK_WINS,
        /**
         * Il giocatore bianco ha vinto.
         */
        WHITE_WINS;

        /**
         * Restituisce una rappresentazione testuale dello stato della partita.
         *
         * @return una stringa che rappresenta lo stato della partita
         */
        @Override
        public String toString() {
            return switch (this) {
                case IN_PROGRESS -> Strings.GameController.IN_PROGRESS_STATE;
                case BLACK_WINS -> Strings.GameController.BLACK_WINS_STATE;
                case WHITE_WINS -> Strings.GameController.WHITE_WINS_STATE;
            };
        }
    }

    /**
     * Il tavoliere del gioco.
     */
    private final Board board;
    /**
     * Il giocatore che deve effettuare la prossima mossa.
     */
    private Board.Cell currentPlayer;
    /**
     * Lo stato attuale della partita.
     */
    private GameState gameState;

    /**
     * Istante in cui è iniziata la partita.
     */
    private Instant startTime;

    /**
     * Lista delle mosse effettuate.
     */
    private final List<Move> moves = new ArrayList<Move>();

    /**
     * Costruttore di default che inizializza il tavoliere a quello iniziale
     * e il giocatore corrente al nero.
     */
    public GameController(final Board b) {
        this.board = Objects.requireNonNullElseGet(b, Board::new);
        this.currentPlayer = Board.Cell.BLACK;
        this.gameState = GameState.IN_PROGRESS;
        this.startTime = Instant.now();
    }

    /**
     * Costruttore che inizializza una partita a partire da una stringa.
     * La stringa deve essere nel seguente formato:
     * <code>currentPlayer - boardString</code>
     * dove <code>currentPlayer</code> è il giocatore che deve effettuare
     * la prossima mossa (uno di 'B', 'W') e <code>boardString</code> è una
     * stringa che rappresenta il tavoliere (vedi {@link Board#Board(String)}).
     *
     * @param gameString la stringa che rappresenta la partita
     * @throws InvalidGameException se la stringa non rappresenta una partita
     * @throws InvalidBoardException se la stringa contiene una stringa per il
     *                               tavoliere non valida
     */
    public GameController(final String gameString) throws InvalidGameException, InvalidBoardException {
        final var parts = gameString.split(" - ");
        if (parts.length != 2) {
            throw new InvalidGameException(Strings.GameController.INVALID_GAME_STRING_EXCEPTION);
        }

        if (parts[0].length() != 1) {
            throw new InvalidGameException(Strings.GameController.NO_CURRENT_PLAYER_EXCEPTION);
        }

        this.currentPlayer = switch (parts[0].charAt(0)) {
            case 'B' -> Board.Cell.BLACK;
            case 'W' -> Board.Cell.WHITE;
            default -> throw new InvalidGameException(Strings.GameController.INVALID_CURRENT_PLAYER_EXCEPTION);
        };

        this.board = new Board(parts[1]);
        updateGameState();
    }

    /**
     * Restituisce lo stato attuale della partita.
     *
     * @return lo stato attuale della partita
     */
    public GameState getGameState() {
        return this.gameState;
    }

    /**
     * Restituisce il giocatore che deve effettuare la prossima mossa.
     *
     * @return il giocatore che deve effettuare la prossima mossa
     */
    public Board.Cell getCurrentPlayer() {
        return this.currentPlayer;
    }

    /**
     * Restituisce la cella nella posizione specificata del tavoliere.
     *
     * @param position la posizione della cella
     * @return la cella nella posizione specificata
     */
    public Board.Cell getBoardCell(final Board.Position position) {
        return this.board.getCell(position);
    }

    /**
     * Restituisce l'istante in cui è iniziata la partita.
     *
     * @return l'istante in cui è iniziata la partita
     */
    public Instant getStartTime() {
        return this.startTime;
    }

    /**
     * Restituisce la lista delle mosse effettuate.
     *
     * @return la lista delle mosse effettuate
     */
    public List<Move> getMoves() {
        return new ArrayList<Move>(this.moves);
    }

    /**
     * Restituisce le mosse che può fare il giocatore attuale dalla posizione
     * <code>from</code>.
     *
     * @param from la posizione dalla quale si vuole effettuare la mossa
     * @return un'<code>List</code> che contiene le mosse effettuabili dalla
     *         posizione <code>from</code>
     */
    public List<Move> getLegalMovesForCurrentPlayer(final Board.Position from) {
        final var legalMoves = new ArrayList<Move>();
        if (board.getCell(from) != this.currentPlayer) {
            return legalMoves;
        }

       final int m = Move.MAX_DISTANCE;
        for (int rowOffset = -m; rowOffset <= m; ++rowOffset) {
            for (int columnOffset = -m; columnOffset <= m; ++columnOffset) {
                if (rowOffset == 0 && columnOffset == 0) {
                    continue;
                }

                final var toRow = from.row() + rowOffset;
                final var toColumn = from.column() + columnOffset;

                if (toRow < 0 || toRow >= Board.SIZE
                    || toColumn < 0 || toColumn >= Board.SIZE) {
                    continue;
                }

                final var to = new Board.Position(toRow, toColumn);
                if (board.getCell(to) == Board.Cell.EMPTY) {
                    legalMoves.add(new Move(from, to, this.currentPlayer));
                }
            }
        }

        return legalMoves;
    }

    /**
     * Restituisce le mosse che può fare il giocatore attuale.
     *
     * @return un'<code>ArrayList</code> che contiene tutte le mosse
     * effettuabili dal giocatore attuale
     */
    public List<Move> getLegalMovesForCurrentPlayer() {
        final var legalMoves = new ArrayList<Move>();

        for (int row = 0; row < Board.SIZE; ++row) {
            for (int column = 0; column < Board.SIZE; ++column) {
                legalMoves.addAll(getLegalMovesForCurrentPlayer(new Board.Position(row, column)));
            }
        }

        return legalMoves;
    }

    /**
     * Esegue la mossa <code>move</code> sul tavoliere e aggiorna lo stato
     * della partita.
     *
     * @param move la mossa da eseguire
     */
    public void executeMove(final Move move) {
        final var current = this.currentPlayer;

        if (board.getCell(move.from()) != current) {
            throw new InvalidMoveException(Strings.GameController.INVALID_STARTING_CELL_EXCEPTION);
        }

        if (board.getCell(move.to()) == Board.Cell.LOCKED) {
            throw new InvalidMoveException(Strings.GameController.DESTINATION_CELL_LOCKED_EXCEPTION);
        }

        if (board.getCell(move.to()) != Board.Cell.EMPTY) {
            throw new InvalidMoveException(Strings.GameController.DESTINATION_CELL_OCCUPIED_EXCEPTION);
        }

        board.setCell(move.to(), current);
        if (move.getType() == Move.Type.JUMP) {
            board.setCell(move.from(), Board.Cell.EMPTY);
        }

        final var enemy = switch (current) {
            case BLACK -> Board.Cell.WHITE;
            case WHITE -> Board.Cell.BLACK;
            // NOTE: This can never happen
            default -> null;

        };

        for (int rowOffset = -1; rowOffset <= 1; ++rowOffset) {
            for (int columnOffset = -1; columnOffset <= 1; ++columnOffset) {
                if (rowOffset == 0 && columnOffset == 0) {
                    continue;
                }

                final var adjacentRow = move.to().row() + rowOffset;
                final var adjacentColumn = move.to().column() + columnOffset;
                if (adjacentRow < 0 || adjacentRow >= Board.SIZE
                    || adjacentColumn < 0 || adjacentColumn >= Board.SIZE) {
                    continue;
                }

                final var adjacent = new Board.Position(adjacentRow, adjacentColumn);
                if (board.getCell(adjacent) == enemy) {
                    board.setCell(adjacent, current);
                }
            }
        }

        updateGameState();

        moves.add(move);

        // In case the game is still in progress and the current player has
        // no legal moves we pass the turn to the enemy
        this.currentPlayer = enemy;
        if (this.gameState == GameState.IN_PROGRESS
            && getLegalMovesForCurrentPlayer().isEmpty()) {
            this.currentPlayer = current;
            System.out.println(Strings.GameController.PASS_TURN);
        }
    }

     /**
      * Conta il numero di celle bianche, celle nere e celle vuote.
      *
      * @return un vettore contenente in ordine il numero di celle bianche, nere e vuote.
      */
    public int[] countCells() {
        int[] cells = {0, 0, 0};
        for (int row = 0; row < Board.SIZE; ++row) {
            for (int column = 0; column < Board.SIZE; ++column) {
                final var position = new Board.Position(row, column);
                switch (board.getCell(position)) {
                    case BLACK -> ++cells[1];
                    case WHITE -> ++cells[0];
                    case EMPTY -> ++cells[2];
                    default -> { }
                }
            }
        }
        return cells;
    }

    /**
     * Aggiorna lo stato della partita in base al tavoliere.
     */
    public void updateGameState() {
        int[] cells = countCells();

        if (cells[0] == 0) {
            this.gameState = GameState.BLACK_WINS;
        } else if (cells[1] == 0) {
            this.gameState = GameState.WHITE_WINS;
        } else if (cells[2] != 0) {
            this.gameState = GameState.IN_PROGRESS;
        } else if (cells[0] > cells[1]) {
            this.gameState = GameState.WHITE_WINS;
        } else {
            this.gameState = GameState.BLACK_WINS;
        }
    }

    /**
     * Restituisce una rappresentazione testuale della partita.
     *
     * @return una stringa che rappresenta la partita
     */
    @Override
    public String toString() {
        return String.format("%s - %s",
            this.currentPlayer.toCharacter(),
            this.board.toString());
    }
}
