package it.uniba.app.entities;

import it.uniba.app.exceptions.InvalidBoardException;
import it.uniba.app.exceptions.InvalidPositionException;
import it.uniba.app.utils.Strings;

import java.util.Arrays;

/**
 * Classe << Entity >> che rappresenta il tavoliere del gioco.
 */
public final class Board {
    /**
     * Numero massimo di caselle bloccate.
     */
    public final int MAX_BLOCKED_CELLS = 9;

    /**
     * Contatore delle caselle bloccate.
     */
    private int blockedCellsCounter = 0;

    /**
     * Enumerazione che rappresenta i possibili contenuti di una cella del
     * tavoliere.
     */
    public enum Cell {
        /**
         * Cella vuota.
         */
        EMPTY,
        /**
         * Cella occupata da una pedina nera.
         */
        BLACK,
        /**
         * Cella occupata da una pedina bianca.
         */
        WHITE,
        /**
         * Cella bloccata.
         */
        LOCKED;

        /**
         * Restituisce una rappresentazione testuale del contenuto della cella.
         *
         * @return una stringa che rappresenta il contenuto della cella
         */
        @Override
        public String toString() {
            return switch (this) {
                case EMPTY -> Strings.Board.EMPTY;
                case BLACK -> Strings.Board.BLACK;
                case WHITE -> Strings.Board.WHITE;
                case LOCKED -> Strings.Board.LOCKED;
            };
        }

        /**
         * Restituisce il carattere associato al contenuto della cella.
         *
         * @return il carattere associato al contenuto della cella
         */
        public char toCharacter() {
            return switch (this) {
                case EMPTY -> Strings.Board.SHORT_EMPTY;
                case BLACK -> Strings.Board.SHORT_BLACK;
                case WHITE -> Strings.Board.SHORT_WHITE;
                case LOCKED -> Strings.Board.SHORT_LOCKED;
            };
        }
    }

    /**
     * Classe che rappresenta una posizione all'interno del tavoliere.
     *
     * @param row la riga della posizione
     * @param column la colonna della posizione
     */
    public record Position(int column, int row) {
        /**
         * Costruttore che crea la posizione verificando che sia valida.
         */
        public Position {
            if (row < 0 || row >= SIZE) {
                throw new InvalidPositionException(
                    String.format(Strings.Board.INVALID_ROW_EXCEPTION_FORMAT,
                        row,
                        SIZE
                    )
                );
            }

            if (column < 0 || column >= SIZE) {
                throw new InvalidPositionException(
                    String.format(Strings.Board.INVALID_COLUMN_EXCEPTION_FORMAT,
                        column,
                        SIZE
                    )
                );
            }
        }

        /**
         * Crea una posizione a partire da una stringa che deve seguire il seguente formato:
         * ```<colonna><riga>```
         * dove `riga` è un numero intero compreso tra 1 e 7 e
         * `colonna` è una lettera minuscola compresa tra 'a' e 'g'.
         *
         * @param positionString la stringa dalla quale si vuole creare una posizione
         * @return la posizione creata dalla stringa
         */
        public static Position fromString(final String positionString) {
            if (positionString.length() != 2) {
                throw new InvalidPositionException(String.format(
                    Strings.Board.INVALID_POSITION_EXCEPTION_FORMAT,
                    positionString
                ));
            }

            char rowCharacter = positionString.charAt(0);
            if (!Character.isDigit(rowCharacter)) {
                throw new InvalidPositionException(String.format(
                    Strings.Board.INVALID_ROW_WHEN_PARSING_EXCEPTION_FORMAT,
                    rowCharacter
                ));
            }

            char columnCharacter = positionString.charAt(1);
            if (!Character.isLowerCase(columnCharacter)) {
                throw new InvalidPositionException(String.format(
                    Strings.Board.INVALID_COLUMN_WHEN_PARSING_EXCEPTION_FORMAT,
                    columnCharacter
                ));
            }

            return new Position(
                Character.toLowerCase(columnCharacter) - 'a',
                Character.getNumericValue(rowCharacter) - 1
                );
        }

        /**
         * Restituisce una rappresentazione testuale della posizione.
         *
         * @return una stringa che rappresenta la posizione
         */
        @Override
        public String toString() {
            return String.format("%c%d", 'a' + column, row);
        }
    }

    /**
     * Dimensione del tavoliere.
     */
    public static final int SIZE = 7;

    /**
     * Array che contiene le celle del tavoliere.
     */
    private final Cell[] cells = new Cell[SIZE * SIZE];

    /**
     * Costruttore che inizializza un tavoliere da una stringa specificata che
     * lo rappresenta.
     * Il formato previsto per <code>boardString</code> è una sequenza di
     * un carattere che rappresenta il contenuto della cella preceduto dal
     * numero di volte che esso compare.
     * Il numero di volte può essere omesso se è 1.
     *
     * @param boardString la stringa che rappresenta il tavoliere
     * @throws InvalidBoardException se la stringa non rappresenta un tavoliere
     */
    public Board(final String boardString) throws InvalidBoardException {
        final int base = 10;

        int boardIndex = 0;
        int index = 0;
        while (index < boardString.length()) {
            var character = boardString.charAt(index);
            int count = 0;
            while (Character.isDigit(character)) {
                count = count * base + Character.getNumericValue(character);
                character = boardString.charAt(++index);
            }

            if (count == 0) {
                count = 1;
            }

            final var cell = switch (character) {
                case Strings.Board.SHORT_EMPTY -> Cell.EMPTY;
                case Strings.Board.SHORT_BLACK -> Cell.BLACK;
                case Strings.Board.SHORT_WHITE -> Cell.WHITE;
                case Strings.Board.SHORT_LOCKED -> Cell.LOCKED;
                default -> throw new InvalidBoardException(
                    Strings.Board.UNKNOWN_CHARACTER_WHEN_PARSING_BOARD_EXCEPTION + character
                );
            };

            if (boardIndex + count > SIZE * SIZE) {
                throw new InvalidBoardException(Strings.Board.BOARD_TOO_LONG_EXCEPTION);
            }

            Arrays.fill(
                this.cells,
                boardIndex,
                boardIndex + count,
                cell
            );

            boardIndex += count;
            ++index;
        }
    }

    /**
     * Costruttore che inizializza il tavoliere con le pedine iniziali.
     */
    public Board() {
        Arrays.fill(this.cells, Cell.EMPTY);

        setCell(new Position(0, 0), Cell.BLACK);
        setCell(new Position(SIZE - 1, SIZE - 1), Cell.BLACK);
        setCell(new Position(0, SIZE - 1), Cell.WHITE);
        setCell(new Position(SIZE - 1, 0), Cell.WHITE);
    }

    /**
     * Restituisce l'indice associato a una posizione nel tavoliere.
     *
     * @param position la posizione che si vuole convertire in un indice
     * @return l'indice associato alla posizione
     */
    private static int getIndex(final Position position) {
        return position.row * SIZE + position.column;
    }

    /**
     * Restituisce il contenuto della cella nella posizione specificata.
     *
     * @param position la posizione della quale si vuole sapere il contenuto
     * @return il contenuto della cella nella posizione specificata
     */
    public Cell getCell(final Position position) {
        return this.cells[getIndex(position)];
    }

    /**
     * Imposta il contenuto della cella nella posizione specificata.
     *
     * @param position la posizione della cella della quale si vuole cambiare
     *                 il contenuto
     * @param cell il nuovo contenuto della cella
     */
    public void setCell(final Position position, final Cell cell) {
        this.cells[getIndex(position)] = cell;
    }

    /**
     * Restituisce una rappresentazione testuale del tavoliere.
     *
     * @return la rappresentazione testuale del tavoliere
     */
    @Override
    public String toString() {
        final var stringBuilder = new StringBuilder();
        int count = 0;
        Cell lastCell = null;

        for (int index = 0; index < SIZE * SIZE; ++index) {
            final var cell = this.cells[index];
            if (lastCell == null) {
                lastCell = cell;
                ++count;
                continue;
            }

            if (cell == lastCell) {
                ++count;
                continue;
            }

            if (count > 1) {
                stringBuilder.append(count);
            }

            stringBuilder.append(lastCell.toCharacter());
            lastCell = cell;
            count = 1;
        }

        if (count != 0) {
            if (count > 1) {
                stringBuilder.append(count);
            }

            stringBuilder.append(lastCell.toCharacter());
        }

        return stringBuilder.toString();
    }

    /**
     * Verifica se una cella è bloccata.
     *
     * @param cell la cella da verificare
     * @return true se la cella è bloccata, false altrimenti
     */
    public boolean isCellBlocked(final Cell cell) {
        return cell == Cell.LOCKED;
    }

    /**
     * Aggiunge una cella all'elenco delle celle bloccate.
     *
     * @param position posizione della cella da bloccare
     */
    public void addBlockedCell(final Position position) {
        setCell(position, Cell.LOCKED);
        blockedCellsCounter++;
    }

    /**
     * Setta la cella bloccata a libera.
     *
     * @param position posizione della cella da sbloccare
     */
    public void removeBlockedCell(final Position position) {
        setCell(position, Cell.EMPTY);
        blockedCellsCounter--;
    }

    /**
     * Setta le celle bloccate a libere.
     */
    public void clearBlockedCells() {
        for (Cell cell: cells) {
            if (cell == Cell.LOCKED) {
                cell = Cell.EMPTY;
            }
        }
        blockedCellsCounter = 0;
    }

    /**
     * Restituisce il numero di celle bloccate.
     *
     * @return il numero di celle bloccate
     */
    public int getBlockedCellsSize() {
        return blockedCellsCounter;
    }

    /**
     * Restituisce il numero massimo di celle bloccabili.
     *
     * @return il numero di celle bloccate
     */
    public int getMaxBlockedCells() {
        return MAX_BLOCKED_CELLS;
    }
}
