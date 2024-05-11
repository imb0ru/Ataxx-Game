package it.uniba.app;

import it.uniba.app.commands.HelpCommand;

/**
 * Classe AppController
 * << Control >>
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
                new HelpCommand();
                break;
            default:
                /* todo: gestire comando errato o non previsto */
                break;
        }
    }
}
