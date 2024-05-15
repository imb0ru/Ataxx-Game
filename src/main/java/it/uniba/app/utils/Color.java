package it.uniba.app.utils;

/**
 * Enumerazione dei colori disponibili per la stampa.
 */
public enum Color {
    /**
     * Nessun colore.
     */
    NO_COLOR,
    /**
     * Bianco.
     */
    WHITE,
    /**
     * Nero.
     */
    BLACK,
    /**
     * Giallo.
     */
    YELLOW,
    /**
     * Arancione.
     */
    ORANGE,
    /**
     * Rosa.
     */
    PINK,
    /**
     * Reset del colore.
     */
    RESET;

    /**
     * Restituisce il codice ANSI del colore.
     *
     * @return il codice ANSI del colore
     */
    public String toANSI() {
        return switch (this) {
            case NO_COLOR -> "";
            case BLACK -> "\u001B[38;5;240m";
            case WHITE -> "\u001B[38;5;255m";
            case YELLOW -> "\u001B[38;5;184m";
            case ORANGE -> "\u001B[38;5;166m";
            case PINK -> "\u001B[38;5;199m";
            case RESET -> "\u001B[0m";
        };
    }
}
