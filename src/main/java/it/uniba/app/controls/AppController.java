package it.uniba.app.controls;

import it.uniba.app.commands.HelpCommand;
import it.uniba.app.commands.PlayCommand;
import it.uniba.app.commands.WhatMovesCommand;
import it.uniba.app.commands.BoardCommand;
import it.uniba.app.commands.QuitCommand;
import it.uniba.app.commands.ExitCommand;
import it.uniba.app.commands.EmptyBoardCommand;
import it.uniba.app.commands.BlockCommand;
import it.uniba.app.commands.TimeCommand;
import it.uniba.app.commands.MoveListCommand;
import it.uniba.app.commands.MoveCommand;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.uniba.app.entities.Board;
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
     * Tabellone di gioco.
     */
    private Board board = new Board();


    /**
     * Costruttore della classe AppController.
     * Gestisce l'avvio dell'applicazione
     * e la chiamata dei comandi di gioco.
     *
     * @param command comando inserito dall'utente
     */
    public void run(final String command) {
        String[] parts = command.trim().split("\\s+", 2);
        String mainCommand = parts[0];
        String argument = parts.length > 1 ? parts[1] : "";
        switch (mainCommand) {
            case Strings.AppController.HELP_COMMAND -> HelpCommand.run();
            case Strings.AppController.PLAY_COMMAND -> PlayCommand.run(this);
            case Strings.AppController.WHAT_MOVES_COMMAND -> WhatMovesCommand.run(this);
            case Strings.AppController.BOARD_COMMAND -> BoardCommand.run(this);
            case Strings.AppController.QUIT_COMMAND -> QuitCommand.run(this);
            case Strings.AppController.EXIT_COMMAND -> ExitCommand.run(this);
            case Strings.AppController.EMPTY_BOARD_COMMAND -> EmptyBoardCommand.run();
            case Strings.AppController.BLOCK_COMMAND -> BlockCommand.run(this, argument);
            case Strings.AppController.TIME_COMMAND -> TimeCommand.run(this);
            case Strings.AppController.MOVE_LIST_COMMAND -> MoveListCommand.run(this);
            default -> {
                if (mainCommand.isEmpty()) {
                    System.out.println(Strings.AppController.EMPTY_COMMAND);
                } else if (mainCommand.matches(Strings.AppController.COMMAND_REGEX)) {
                    System.out.println(Strings.AppController.UNRECOGNIZED_COMMAND);
                } else {
                    MoveCommand.run(this, mainCommand);
                }
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

    /**
     * Metodo per ottenere il tabellone di gioco.
     *
     * @return il tabellone di gioco
     */
    @SuppressFBWarnings("EI_EXPOSE_REP")
    public Board getBoard() {
        return board;
    }
}
