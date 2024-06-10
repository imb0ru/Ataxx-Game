package it.uniba.app.exceptions;

/**
 * Eccezione che viene lanciata quando una cella non può essere bloccata.
 */
public class UnblockableCellException extends Exception {
    /**
     * Costruttore dell'eccezione.
     *
     * @param message messaggio che descrive l'errore.
     */
    public UnblockableCellException(final String message) {
        super(message);
    }
}
