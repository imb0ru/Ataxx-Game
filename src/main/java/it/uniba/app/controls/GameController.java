package it.uniba.app.controls;

import it.uniba.app.entities.Board;
import it.uniba.app.entities.Move;
import it.uniba.app.exceptions.InvalidBoardException;
import it.uniba.app.exceptions.InvalidGameException;
import it.uniba.app.exceptions.InvalidMoveException;

import java.util.ArrayList;
import java.util.List;

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
                case IN_PROGRESS -> "In corso";
                case BLACK_WINS -> "Vince il nero";
                case WHITE_WINS -> "Vince il bianco";
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
     * Costruttore di default che inizializza il tavoliere a quello iniziale
     * e il giocatore corrente al nero.
     */
    public GameController() {
        this.board = new Board();
        this.currentPlayer = Board.Cell.BLACK;
        this.gameState = GameState.IN_PROGRESS;
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
            throw new InvalidGameException("La stringa di gioco non è nel formato corretto");
        }

        if (parts[0].length() != 1) {
            throw new InvalidGameException("La stringa di gioco non contiene il giocatore corrente");
        }

        this.currentPlayer = switch (parts[0].charAt(0)) {
            case 'B' -> Board.Cell.BLACK;
            case 'W' -> Board.Cell.WHITE;
            default -> throw new InvalidGameException("Il giocatore corrente non è valido");
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
                if (board.getCell(to) != Board.Cell.EMPTY) {
                    continue;
                }

                legalMoves.add(new Move(from, to));
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
            throw new InvalidMoveException("La cella di partenza non contiene il giocatore corrente");
        }

        board.setCell(move.to(), current);
        if (move.getType() == Move.Type.JUMP) {
            board.setCell(move.from(), Board.Cell.EMPTY);
        }

        final var enemy = switch (current) {
            case BLACK -> Board.Cell.WHITE;
            case WHITE -> Board.Cell.BLACK;
            // NOTE: This can never happen
            case EMPTY -> null;
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

        // In case the game is still in progress and the current player has
        // no legal moves we pass the turn to the enemy
        this.currentPlayer = enemy;
        if (this.gameState == GameState.IN_PROGRESS
            && getLegalMovesForCurrentPlayer().isEmpty()) {
            this.currentPlayer = current;
        }
    }

    /**
     * Aggiorna lo stato della partita in base al tavoliere.
     */
    public void updateGameState() {
        int whiteCells = 0;
        int blackCells = 0;
        int emptyCells = 0;

        for (int row = 0; row < Board.SIZE; ++row) {
            for (int column = 0; column < Board.SIZE; ++column) {
                final var position = new Board.Position(row, column);
                switch (board.getCell(position)) {
                    case BLACK -> ++blackCells;
                    case WHITE -> ++whiteCells;
                    default -> ++emptyCells;
                }
            }
        }

        if (whiteCells == 0) {
            this.gameState = GameState.BLACK_WINS;
        } else if (blackCells == 0) {
            this.gameState = GameState.WHITE_WINS;
        } else if (emptyCells != 0) {
            this.gameState = GameState.IN_PROGRESS;
        } else if (whiteCells > blackCells) {
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

