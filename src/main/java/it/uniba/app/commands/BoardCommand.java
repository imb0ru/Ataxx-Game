package it.uniba.app.commands;

import it.uniba.app.boundaries.GamePrinter;
import it.uniba.app.controls.AppController;

import it.uniba.app.utils.Strings;

/**
 * Classe << Boundary >> che si occupa di eseguire il comando `/tavoliere`.
 * Stampa a video il tavoliere nel caso in cui ci fosse una partita in corso,
 * altrimenti suggerisce l'uso del comando /gioca per crearne una.
 */
public final class BoardCommand {

    private BoardCommand() {
    }

    /**
     * Esegue il comando.
     * @param app riferimento al contesto dell'applicazione
     */
    public static void run(final AppController app) {
        final var game = app.getGame();
        if (game == null) {
            System.out.println(Strings.BoardCommand.NO_RUNNING_GAME);
        } else {
            GamePrinter.print(game, GamePrinter.PrintMoves.NO);
        }
    }
}
