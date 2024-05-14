package it.uniba.app.exceptions;

/**
 * Eccezione non controllata che rappresenta una mossa non valida.
 */
public class InvalidMoveException extends RuntimeException {
    /**
     * Costruttore dell'eccezione.
     *
     * @param message il messaggio associato all'eccezione
     */
    public InvalidMoveException(final String message) {
        super(message);
    }
}
