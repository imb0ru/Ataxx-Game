package it.uniba.app.commands;

import it.uniba.app.utils.Strings;
/**
 * Classe Help
 * << Boundary >>
 *
 * <p>Classe che visualizza le informazioni di aiuto
 * per l'utilizzo dell'applicazione.</p>
 */
public final class HelpCommand {
    private HelpCommand() {
    }

    /**
     * Esegue il comando.
     */
    public static void run() {
        System.out.println(Strings.HelpCommand.GAME_VERSION);
        System.out.println();

        System.out.println(Strings.HelpCommand.DEVELOPERS);
        System.out.println();

        System.out.println(Strings.HelpCommand.HOW_TO);
        System.out.println();

        System.out.println(Strings.HelpCommand.OPTIONS);
        System.out.println(Strings.HelpCommand.HELP_OPTION);
        System.out.println();

        System.out.println(Strings.HelpCommand.IN_GAME_COMMANDS);
        System.out.println(Strings.HelpCommand.HELP_COMMAND);
        System.out.println(Strings.HelpCommand.PLAY_COMMAND);
        System.out.println(Strings.HelpCommand.EMPTY_BOARD_COMMAND);
        System.out.println(Strings.HelpCommand.BOARD_COMMAND);
        System.out.println(Strings.HelpCommand.WHAT_MOVES_COMMAND);
        System.out.println(Strings.HelpCommand.QUIT_COMMAND);
        System.out.println(Strings.HelpCommand.EXIT_COMMAND);
        System.out.println(Strings.HelpCommand.TIME_COMMAND);
        System.out.println(Strings.HelpCommand.BLOCK_COMMAND);
    }
}
