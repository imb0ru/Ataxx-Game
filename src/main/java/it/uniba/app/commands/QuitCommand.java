package it.uniba.app.commands;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import it.uniba.app.controls.AppController;
import it.uniba.app.utils.Strings;
import it.uniba.app.entities.Board;

/**
 * Classe << Boundary >> che si occupa di eseguire il comando `/abbandona`.
 * Chiede conferma all'utente nel caso di abbandono.
 * Se la conferma è positiva, la partita termina enunciando il giocatore e il punteggio.
 * Se la conferma è negativa, ci si predispone a ricevere nuovi tentativi o comandi.
 */
public final class QuitCommand {
    private QuitCommand() {
    }

    /**
     * Esegue il comando.
     *
     * @param app riferimento al contesto dell'applicazione.
     */
    public static void run(final AppController app) {
        final var game = app.getGame();

        if (game == null) {
            System.out.println(Strings.WhatMovesCommand.NO_RUNNING_GAME);
            return;
        }

        System.out.println(Strings.QuitCommand.QUIT_CONFIRM);
        Scanner keyboard = new Scanner(System.in, StandardCharsets.UTF_8);
        final String confirm = keyboard.nextLine().trim();

        if (!confirm.equalsIgnoreCase("s")) {
            return;
        }

        Board.Cell currentPlayer = game.getCurrentPlayer();
        int index;
        Board.Cell enemy;
        if (currentPlayer == Board.Cell.WHITE) {
            enemy = Board.Cell.BLACK;
            index = 1;
        } else {
            enemy = Board.Cell.WHITE;
            index = 0;
        }

        int pieceCount = game.countCells()[index];
        System.out.printf(Strings.QuitCommand.QUIT_WIN_FORMAT, enemy, pieceCount, currentPlayer);
        app.setGame(null);
    }
}
