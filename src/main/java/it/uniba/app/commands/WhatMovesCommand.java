package it.uniba.app.commands;

import it.uniba.app.controls.AppController;
import it.uniba.app.boundaries.GamePrinter;
import it.uniba.app.utils.Strings;

/**
 * Classe << Boundary >> che si occupa di eseguire il comando `/qualimosse`.
 * Stampa a video il tavoliere con le mosse che può effettuare il giocatore corrente.
 */
public final class WhatMovesCommand {
    private WhatMovesCommand() {
    }

    /**
     * Esegue il comando.
     *
     * @param app riferimento al contesto dell'applicazione
     */
    public static void run(final AppController app) {
        final var game = app.getGame();
        if (game == null) {
            System.out.println(Strings.Common.NO_RUNNING_GAME);
        } else {
            GamePrinter.print(game, GamePrinter.PrintMoves.YES, GamePrinter.PrintGameState.YES);
        }
    }
}
