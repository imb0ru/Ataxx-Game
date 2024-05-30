package it.uniba.app.commands;

import it.uniba.app.controls.AppController;
import it.uniba.app.utils.Strings;

import java.time.Duration;
import java.time.Instant;

/**
 * Classe TimeCommand
 * << Boundary >>
 *
 * <p>Classe che gestisce il comando `/time`.</p>
 */
public final class TimeCommand {
    private TimeCommand() {
    }

    /**
     * Esegue il comando.
     *
     * @param app riferimento al contesto dell'applicazione
     */
    public static void run(final AppController app) {
        if (app.getGame() == null) {
            System.out.println(Strings.Common.NO_RUNNING_GAME);
        } else {
            Instant startTime = app.getGame().getStartTime();
            Duration gameTime = Duration.between(startTime, Instant.now());
            long hours = gameTime.toHours();
            long minutes = gameTime.toMinutesPart();
            long seconds = gameTime.toSecondsPart();
            System.out.printf(Strings.TimeCommand.TIME, hours, minutes, seconds);
        }
    }
}
