package it.uniba.app;

import it.uniba.app.controls.AppController;
import it.uniba.app.commands.HelpCommand;
import it.uniba.app.controls.GameController;
import it.uniba.app.entities.Board;
import it.uniba.app.utils.Strings;

import java.util.Scanner;

/**
 * Classe App
 * << Boundary >>
 *
 * <p>Classe principale dell'applicazione.</p>
 */
public final class App {

    /**
     * Costruttore della classe App.
     */
    private App() { }

    /**
     * Metodo main dell'applicazione.
     *
     * @param args argomenti della riga di comando
     */
    public static void main(final String[] args) {
        System.out.print(Strings.App.INTRO);
        if (args.length != 0) {
            if (args[0].equalsIgnoreCase(Strings.App.HELP_LONG_OPTION)
                || args[0].equalsIgnoreCase(Strings.App.HELP_SHORT_OPTION)) {
                HelpCommand.run();
            } else {
                System.out.println(Strings.App.UNRECOGNIZED_OPTION);
            }

            return;
        }

        AppController appController = new AppController();
        Scanner keyboard = appController.getKeyboard();

        System.out.println(Strings.App.WELCOME);
        System.out.println(Strings.App.INPUT);

        while (appController.isRunning()) {
            System.out.print(Strings.App.PROMPT);
            final String command = keyboard.nextLine().trim();
            appController.run(command);

            final var game = appController.getGame();
            if (game != null && game.getGameState() != GameController.GameState.IN_PROGRESS) {
                var winner = switch (game.getGameState()) {
                    case BLACK_WINS -> Board.Cell.BLACK;
                    case WHITE_WINS -> Board.Cell.WHITE;
                    default -> throw new IllegalStateException("La partita è ancora in corso.");
                };

                final var cellCounts = appController.getGame().countCells();
                System.out.printf(Strings.App.END_GAME_FORMAT, winner, cellCounts[0], cellCounts[1]);

                appController.setGame(null);
            }
        }

        keyboard.close();
    }
}
