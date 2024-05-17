package it.uniba.app.controls;

import it.uniba.app.commands.BoardCommand;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.uniba.app.commands.PlayCommand;
import it.uniba.app.commands.QuitCommand;
import it.uniba.app.commands.HelpCommand;
import it.uniba.app.commands.WhatMovesCommand;
import it.uniba.app.commands.ExitCommand;
import it.uniba.app.commands.EmptyBoardCommand;
import it.uniba.app.utils.Strings;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

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
     * Scanner per l'input dell'utente.
     */
    private final Scanner keyboard = new Scanner(System.in, StandardCharsets.UTF_8);

    /**
     * Getter per lo scanner.
     *
     * @return lo scanner per l'input dell'utente
     */
    @SuppressFBWarnings("EI_EXPOSE_REP")
    public Scanner getKeyboard() {
        return keyboard;
    }


    /**
     * Partita corrente (se esiste).
     */
    private GameController currentGame = null;

    /**
     * Variabile per la gestione del ciclo di gioco.
     */
    private boolean running = true;

    /**
     * Costruttore della classe AppController.
     * Gestisce l'avvio dell'applicazione
     * e la chiamata dei comandi di gioco.
     *
     * @param command comando inserito dall'utente
     */
    public void run(final String command) {
        switch (command) {
            case Strings.AppController.HELP_COMMAND -> HelpCommand.run();
            case Strings.AppController.PLAY_COMMAND -> PlayCommand.run(this);
            case Strings.AppController.WHAT_MOVES_COMMAND -> WhatMovesCommand.run(this);
            case Strings.AppController.BOARD_COMMAND -> BoardCommand.run(this);
            case Strings.AppController.QUIT_COMMAND -> QuitCommand.run(this);
            case Strings.AppController.EXIT_COMMAND -> ExitCommand.run(this);
            case Strings.AppController.EMPTY_BOARD_COMMAND -> EmptyBoardCommand.run();
            default -> {
                // FIXME: Handle moves
                System.out.println(Strings.AppController.UNRECOGNIZED_COMMAND);
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

    /**
     * Getter per la variabile di controllo del ciclo di gioco.
     *
     * @return true se il gioco è in esecuzione, false altrimenti
     */
    public boolean isRunning() {
        return running;
    }

    /**
     * Setter per la variabile di controllo del ciclo di gioco.
     *
     * @param newRunning true se il gioco è in esecuzione, false altrimenti
     */
    public void setRunning(final boolean newRunning) {
        this.running = newRunning;
    }


}
