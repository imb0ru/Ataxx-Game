package it.uniba.app;

/**
 * Classe AppController
 * << Boundary >>
 *
 * <p>Controller principale dell'applicazione.
 * Gestisce le chiamate dei comandi di gioco
 * in base alle azioni dell'utente.</P>
 */
public final class AppController {
    /**
     * Costruttore della classe AppController.
     * Gestisce l'avvio dell'applicazione
     * e la chiamata dei comandi di gioco.
     *
     * @param command comando inserito dall'utente
     */
    AppController(final String command) {
        switch (command) {
            case "/help":
                new Help();
                break;
            default:
                System.out.println("Comando non riconosciuto."
                        + " Usa /help per visualizzare i "
                        + "comandi disponibili.");
                break;
        }
    }
}
