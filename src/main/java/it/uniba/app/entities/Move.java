package it.uniba.app.entities;

import it.uniba.app.exceptions.InvalidMoveException;

/**
 * Classe << Entity >> che rappresenta una mossa all'interno della partita.
 *
 * @param from la posizione di partenza
 * @param to la posizione di arrivo
 */
public record Move(Board.Position from, Board.Position to) {
    /**
     * Enumerazione che rappresenta il tipo di mossa.
     */
    public enum Type {
        /**
         * La mossa consiste nel saltare nella posizione di arrivo e replicare
         * la pedina.
         */
        JUMP_AND_REPLICATE,
        /**
         * La mossa consiste solamente nel saltare nella posizione di arrivo.
         */
        JUMP
    }

    /**
     * La distanza massima della mossa.
     */
    public static final int MAX_DISTANCE = 2;

    /**
     * La distanza della mossa di tipo {@link Type#JUMP_AND_REPLICATE}.
     */
    public static final int JUMP_AND_REPLICATE_DISTANCE = 1;

    /**
     * Costruttore della mossa.
     *
     * @param from la posizione di partenza
     * @param to la posizione di arrivo
     */
    public Move {
        final var rowDistance = Math.abs(to.row() - from.row());
        final var columnDistance = Math.abs(to.column() - from.column());

        if (rowDistance > MAX_DISTANCE || columnDistance > MAX_DISTANCE) {
            throw new InvalidMoveException("La distanza tra le posizioni deve essere <= 2");
        }
    }

    /**
     * Restituisce il tipo di mossa.
     *
     * @return il tipo della mossa
     */
    public Type getType() {
        final var rowDistance = Math.abs(this.to.row() - this.from.row());
        final var columnDistance = Math.abs(this.to.column() - this.from.column());

        if (rowDistance <= JUMP_AND_REPLICATE_DISTANCE
            && columnDistance <= JUMP_AND_REPLICATE_DISTANCE) {
            return Type.JUMP_AND_REPLICATE;
        }

        return Type.JUMP;
    }

    /**
     * Restituisce una rappresentazione testuale della mossa.
     *
     * @return una stringa che rappresenta la mossa
     */
    @Override
    public String toString() {
        return String.format("%s -> %s", this.from, this.to);
    }
}
