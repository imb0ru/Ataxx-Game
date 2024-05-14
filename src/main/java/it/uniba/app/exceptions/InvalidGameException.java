package it.uniba.app.exceptions;

/**
 * Eccezione lanciata quando la stringa che rappresenta lo stato della partita
 * non è valida.
 */
public class InvalidGameException extends Exception {
    /**
     * Costruttore dell'eccezione.
     *
     * @param message messaggio che descrive l'errore
     */
    public InvalidGameException(final String message) {
        super(message);
    }
}
