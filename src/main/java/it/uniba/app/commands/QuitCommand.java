package it.uniba.app.commands;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import it.uniba.app.controls.GameController.GameState;
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
        } else {
            System.out.println(Strings.QuitCommand.QUIT_CONFIRM);
            Scanner keyboard = new Scanner(System.in, StandardCharsets.UTF_8);
            final String confirm = keyboard.nextLine().trim();

            if (confirm.equalsIgnoreCase("s")) {
                if (game.getCurrentPlayer() == Board.Cell.BLACK) {
                    game.setGameState(GameState.WHITE_WINS);
                    System.out.println(Strings.QuitCommand.QUIT_WHITE_WIN);
                    int pieceCount = game.countCells()[0];
                    System.out.printf(Strings.QuitCommand.NUMBER_PIECES_WHITE, pieceCount);
                } else {
                    game.setGameState(GameState.BLACK_WINS);
                    System.out.println(Strings.QuitCommand.QUIT_BLACK_WIN);
                    int pieceCount = game.countCells()[1];
                    System.out.printf(Strings.QuitCommand.NUMBER_PIECES_BLACK, pieceCount);
                }
                app.setGame(null);
            }
        }
    }
}
