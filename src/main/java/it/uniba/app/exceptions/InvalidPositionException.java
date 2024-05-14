package it.uniba.app.exceptions;

/**
 * Eccezione non controllata che gestisce una posizione non valida.
 */
public class InvalidPositionException extends RuntimeException {
    /**
     * Costruttore dell'eccezione.
     *
     * @param message il messaggio associato all'eccezione
     */
    public InvalidPositionException(final String message) {
        super(message);
    }
}
