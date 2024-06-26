package it.uniba.app.exceptions;

/**
 * Eccezione lanciata quando il tavoliere non è valido.
 */
public class InvalidBoardException extends Exception {
    /**
     * Costruttore dell'eccezione.
     *
     * @param message messaggio che descrive l'errore
     * @param cause il motivo dell'eccezione
     */
    public InvalidBoardException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * Costruttore dell'eccezione.
     *
     * @param message messaggio che descrive l'errore
     */
    public InvalidBoardException(final String message) {
        super(message, null);
    }
}
