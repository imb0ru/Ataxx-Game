package it.uniba.app.boundaries;

import it.uniba.app.controls.GameController;
import it.uniba.app.entities.Board;
import it.uniba.app.entities.Move;
import it.uniba.app.utils.Color;
import it.uniba.app.utils.Strings;

/**
 * Classe << Boundary >> che si occupa di stampare lo stato della partita.
 */
public final class GamePrinter {
    private GamePrinter() {
    }

    /**
     * Flag che indica se stampare o meno le mosse possibili.
     */
    public enum PrintMoves { YES, NO };

    /**
     * Classe che contiene i dati di una cella del tavoliere per la sua stampa.
     * In particolare contiene il tipo della cella e i tipi di mossa con le quali è possibile raggiungere questa cella.
     */
    private static final class CellData {
        /**
         * Tipo della cella.
         */
        private Board.Cell type = Board.Cell.EMPTY;

        /**
         * Flag che indica se la cella è una cella di arrivo per una mossa di tipo
         * {@link it.uniba.app.entities.Move.Type#JUMP}.
         */
        private boolean hasJump = false;

        /**
         * Flag che indica se la cella è una cella di arrivo per una mossa di tipo
         * {@link it.uniba.app.entities.Move.Type#JUMP_AND_REPLICATE}.
         */
        private boolean hasJumpAndReplicate = false;

        /**
         * Imposta il tipo della cella.
         *
         * @param newType il tipo della cella
         */
        public void setType(final Board.Cell newType) {
            this.type = newType;
        }

        /**
         * Imposta il tipo di mossa sulla cella.
         *
         * @param moveType il tipo di mossa
         */
        public void setMoveType(final Move.Type moveType) {
            switch (moveType) {
                case JUMP -> {
                    this.hasJump = true;
                }
                case JUMP_AND_REPLICATE -> {
                    this.hasJumpAndReplicate = true;
                }
                default -> {
                }
            }
        }

        /**
         * Restituisce il carattere da stampare per la cella.
         *
         * @return il carattere da stampare
         */
        public char getCharacter() {
            if (this.hasJumpAndReplicate || this.hasJump) {
                return Strings.GamePrinter.MOVE_CHARACTER;
            }

            return switch (this.type) {
                case EMPTY -> Strings.GamePrinter.EMPTY_CELL_CHARACTER;
                case BLACK, WHITE -> Strings.GamePrinter.FULL_CELL_CHARACTER;
            };
        }

        /**
         * Restituisce il colore da utilizzare per la stampa della cella.
         *
         * @return il colore da utilizzare
         */
        public Color getColor() {
            if (this.hasJumpAndReplicate && this.hasJump) {
                return Color.PINK;
            }

            if (this.hasJumpAndReplicate) {
                return Color.YELLOW;
            }

            if (this.hasJump) {
                return Color.ORANGE;
            }

            return switch (type) {
                case EMPTY -> Color.NO_COLOR;
                case BLACK -> Color.BLACK;
                case WHITE -> Color.WHITE;
            };
        }
    }

    /**
     * Restituisce un array di <code>CellData</code> contenente i dati delle celle del tavoliere.
     *
     * @param game la partita di cui si vogliono ottenere i dati delle celle
     * @param shouldPrintMoves se stampare o meno le mosse possibili
     * @return un array di <code>CellData</code> contenente i dati delle celle del tavoliere
     */
    private static CellData[][] getCellsData(final GameController game, final PrintMoves shouldPrintMoves) {
        final var cellsData = new CellData[Board.SIZE][Board.SIZE];
        for (int row = 0; row < Board.SIZE; ++row) {
            for (int column = 0; column < Board.SIZE; ++column) {
                final var position = new Board.Position(row, column);
                final var cell = game.getBoardCell(position);
                cellsData[row][column] = new CellData();
                cellsData[row][column].setType(cell);
            }
        }

        if (shouldPrintMoves == PrintMoves.NO) {
            return cellsData;
        }

        for (final var move : game.getLegalMovesForCurrentPlayer()) {
            cellsData[move.to().row()][move.to().column()].setMoveType(move.getType());
        }

        return cellsData;
    }

    /**
     * Stampa il tavoliere della partita.
     *
     * @param game la partita di cui si vuole stampare il tavoliere
     * @param shouldPrintMoves se stampare o meno le mosse possibili
     */
    public static void printBoard(final GameController game, final PrintMoves shouldPrintMoves) {
        final var cellsData = getCellsData(game, shouldPrintMoves);

        for (int row = 0; row < Board.SIZE; ++row) {
            System.out.printf("%d | ", row + 1);
            for (int column = 0; column < Board.SIZE; ++column) {
                final var cellData = cellsData[row][column];
                final var color = cellData.getColor();
                final var character = cellData.getCharacter();

                System.out.print(color.toANSI());
                System.out.print(' ');
                System.out.print(character);
                System.out.print(' ');
                System.out.print(Color.RESET.toANSI());
                System.out.print(' ');
            }
            System.out.printf("| %d", row + 1);
            System.out.println();
        }
    }

    /**
     * Stampa il bordo superiore/inferiore del tavoliere.
     */
    private static void printVerticalBorder() {
        final int charactersPerCell = 4;

        System.out.print("  ");
        System.out.print("+");
        for (int i = 0; i < Board.SIZE * charactersPerCell + 1; ++i) {
            System.out.print('-');
        }
        System.out.print("+");
        System.out.println();
    }

    /**
     * Stampa le colonne del tavoliere.
     */
    private static void printColumns() {
        System.out.print("  ");
        for (char c = 'a'; c < 'a' + Board.SIZE; ++c) {
            System.out.printf("   %c", c);
        }
        System.out.println();
    }

    /**
     * Stampa lo stato della partita.
     *
     * @param game la partita da stampare
     * @param shouldPrintMoves se stampare o meno le mosse possibili
     */
    public static void print(final GameController game, final PrintMoves shouldPrintMoves) {
        System.out.printf(Strings.GamePrinter.GAME_STATE_FORMAT,
            game.getGameState().toString(),
            game.getCurrentPlayer().toString().toLowerCase());

        printColumns();
        printVerticalBorder();

        printBoard(game, shouldPrintMoves);

        printVerticalBorder();
        printColumns();
    }
}
