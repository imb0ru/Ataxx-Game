package it.uniba.app.controls;

import it.uniba.app.commands.PlayCommand;
import it.uniba.app.commands.HelpCommand;
import it.uniba.app.commands.WhatMovesCommand;

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
     * Partita corrente (se esiste).
     */
    private GameController currentGame = null;

    /**
     * Costruttore della classe AppController.
     * Gestisce l'avvio dell'applicazione
     * e la chiamata dei comandi di gioco.
     *
     * @param command comando inserito dall'utente
     */
    public void run(final String command) {
        switch (command) {
            case "/help" -> HelpCommand.run();
            case "/gioca" -> PlayCommand.run(this);
            case "/qualimosse" -> WhatMovesCommand.run(this);
            default -> {
                // FIXME: Handle moves
                System.out.println("Comando non riconosciuto. Digita /help per visualizzare i comandi disponibili.");
            }
        }
    }

    /**
     * Metodo per impostare la partita corrente.
     *
     * @param game nuova partita corrente
     */
    public void setGame(final GameController game) {
        this.currentGame = game;
    }

    /**
     * Metodo per ottenere la partita corrente.
     *
     * @return la partita corrente
     */
    public GameController getGame() {
        return this.currentGame;
    }
}
