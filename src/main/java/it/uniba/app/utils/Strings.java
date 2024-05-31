package it.uniba.app.utils;

/**
 * Classe contenente le stringhe utilizzate nell'applicazione.
 */
public final class Strings {
    private Strings() {
    }

    /**
     * Stringhe utilizzate in {@link it.uniba.app.App}.
     */
    public static final class App {
        private App() {
        }

        /**
         * Banner di intro dell'applicazione.
         */
        public static final String INTRO = """
                                         \
            ___   ______    ___    _  __   _  __
                                        /   | /_  _\
            _/   /   |  | |/ /  | |/ /
                                       / /| |  / /\
                 / /| |  |   /   |   /\s
                                      / ___ | / / \
                / ___ | /   |   /   | \s
                                     /_/  |_|/_/  \
               /_/  |_|/_/|_|  /_/|_| \s
               ___    ____   ___    _  __   ____ \
              ___    ____                  __    _\
            ___   ____
              / _ )  / __/  / _ \\  / |/ /  / __/\
              / _ \\  / __/      ____       / /   /\
             __/  / __/
             / _  | / _/   / , _/ /    /  / _/   \
            / , _/ _\\ \\       /___/      / /__ \
            / _/   / _/ \s
            /____/ /___/  /_/|_| /_/|_/  /___/\
              /_/|_| /___/                 /____/\
            /___/  /___/ \s
                                             \
                                           \
                                \s
            """;

        /**
         * Prompt di inserimento utente.
         */
        public static final String PROMPT = "> ";

        /**
         * Messaggio di benvenuto.
         */
        public static final String WELCOME = "Benvenuto in Ataxx!";

        /**
         * Messaggio di inout per l'utente.
         */
        public static final String INPUT = "Inserisci un comando per iniziare a giocare.";

        /**
         * Opzione (lunga) per visualizzare l'aiuto.
         */
        public static final String HELP_LONG_OPTION = "--help";

        /**
         * Opzione (corta) per visualizzare l'aiuto.
         */
        public static final String HELP_SHORT_OPTION = "-h";

        /**
         * Messaggio per un'opzione non riconosciuta.
         */
        public static final String UNRECOGNIZED_OPTION = "Opzione non riconosciuta.\n"
                                                         + "Usa " + HELP_LONG_OPTION + " o " + HELP_SHORT_OPTION
                                                         + " per visualizzare le opzioni disponibili.";
    }

    /**
     * Stringhe utilizzate in {@link it.uniba.app.controls.AppController}.
     */
    public static final class AppController {
        private AppController() {
        }

        /**
         * Comando per {@link it.uniba.app.commands.HelpCommand}.
         */
        public static final String HELP_COMMAND = "/help";

        /**
         * Comando per {@link it.uniba.app.commands.EmptyBoardCommand}.
         */
        public static final String EMPTY_BOARD_COMMAND = "/vuoto";

        /**
         * Comando per {@link it.uniba.app.commands.PlayCommand}.
         */
        public static final String PLAY_COMMAND = "/gioca";

        /**
         * Comando per {@link it.uniba.app.commands.WhatMovesCommand}.
         */
        public static final String WHAT_MOVES_COMMAND = "/qualimosse";

        /**
         * Comando per {@link it.uniba.app.commands.BoardCommand}.
         */
        public static final String BOARD_COMMAND = "/tavoliere";

        /**
         * Comando per {@link it.uniba.app.commands.QuitCommand}.
         */
        public static final String QUIT_COMMAND = "/abbandona";

        /**
         * Comando per {@link it.uniba.app.commands.ExitCommand}.
         */
        public static final String EXIT_COMMAND = "/esci";

        /**
         * Comando per {@link it.uniba.app.commands.TimeCommand}.
         */
        public static final String TIME_COMMAND = "/tempo";

        /**
         * Comando per {@link it.uniba.app.commands.BlockCommand}.
         */
        public static final String BLOCK_COMMAND = "/blocca";

        /**
         * Messaggio di errore per un comando non riconosciuto.
         */
        public static final String UNRECOGNIZED_COMMAND = "Comando non riconosciuto. "
                                                          + "Digita "
                                                          + HELP_COMMAND
                                                          + " per visualizzare i comandi disponibili.";
    }

    /**
     * Stringhe utilizzate in {@link it.uniba.app.boundaries.GamePrinter}.
     */
    public static class GamePrinter {
        /**
         * Carattere che rappresenta una mossa possibile.
         */
        public static final char MOVE_CHARACTER = '#';

        /**
         * Carattere che rappresenta una cella vuota.
         */
        public static final char EMPTY_CELL_CHARACTER = '.';

        /**
         * Carattere che rappresenta una cella piena.
         */
        public static final char FULL_CELL_CHARACTER = '\u26c0';

        /**
         * Stringa di formato per lo stato della partita.
         */
        public static final String GAME_STATE_FORMAT = "%s - E' il turno del %s%n%n";
    }

    /**
     * Stringhe utilizzate in {@link it.uniba.app.entities.Board}.
     */
    public static final class Board {
        private Board() {
        }

        /**
         * Stringa (lunga) per {@link it.uniba.app.entities.Board.Cell#EMPTY}.
         */
        public static final String EMPTY = "Vuoto";
        /**
         * Stringa (corta) per {@link it.uniba.app.entities.Board.Cell#EMPTY}.
         */
        public static final char SHORT_EMPTY = 'E';

        /**
         * Stringa (lunga) per {@link it.uniba.app.entities.Board.Cell#BLACK}.
         */
        public static final String BLACK = "Nero";
        /**
         * Stringa (corta) per {@link it.uniba.app.entities.Board.Cell#BLACK}.
         */
        public static final char SHORT_BLACK = 'B';

        /**
         * Stringa (lunga) per {@link it.uniba.app.entities.Board.Cell#WHITE}.
         */
        public static final String WHITE = "Bianco";

        /**
         * Stringa (corta) per {@link it.uniba.app.entities.Board.Cell#WHITE}.
         */
        public static final char SHORT_WHITE = 'W';

        /**
         * Stringa (lunga) per {@link it.uniba.app.entities.Board.Cell#LOCKED}.
         */
        public static final String LOCKED = "Bloccato";

        /**
         * Stringa (corta) per {@link it.uniba.app.entities.Board.Cell#LOCKED}.
         */
        public static final char SHORT_LOCKED = 'L';

        /**
         * Messaggio di errore per il numero di righe non valido.
         */
        public static final String INVALID_ROW_EXCEPTION_FORMAT = "Riga non valida %d (0 <= riga < %d)";

        /**
         * Messaggio di errore per riga non valida.
         */
        public static final String INVALID_ROW_WHEN_PARSING_EXCEPTION_FORMAT = "Riga non valida: %c";

        /**
         * Messaggio di errore per il numero di colonne non valido.
         */
        public static final String INVALID_COLUMN_EXCEPTION_FORMAT = "Colonna non valida %d (0 <= colonna < %d)";

        /**
         * Messaggio di errore per colonna non valida.
         */
        public static final String INVALID_COLUMN_WHEN_PARSING_EXCEPTION_FORMAT = "Colonna non valida: %c";

        /**
         * Messaggio di errore per un formato di posizione non valido.
         */
        public static final String INVALID_POSITION_EXCEPTION_FORMAT = "Formato non valido: %s";

        /**
         * Messaggio di errore per carattere di cella non valido.
         */
        public static final String UNKNOWN_CHARACTER_WHEN_PARSING_BOARD_EXCEPTION = "Carattere non valido: ";

        /**
         * Messaggio di errore per tavoliere troppo lungo.
         */
        public static final String BOARD_TOO_LONG_EXCEPTION = "Tavoliere troppo lungo";
    }

    /**
     * Stringhe utilizzate in {@link it.uniba.app.entities.Move}.
     */
    public static final class Move {
        private Move() {
        }

        /**
         * Messaggio per l'eccezione lanciata se una mossa ha distanza troppo grande.
         */
        public static final String DISTANCE_TOO_BIG_EXCEPTION = "La distanza tra le posizioni deve essere <= 2";
    }

    /**
     * Stringhe utilizzate in {@link it.uniba.app.controls.GameController}.
     */
    public static final class GameController {
        private GameController() {
        }

        /**
         * Stringa per lo stato di una partita in corso.
         */
        public static final String IN_PROGRESS_STATE = "In corso";

        /**
         * Stringa per lo stato di una partita vinta dal nero.
         */
        public static final String BLACK_WINS_STATE = "Vince il nero";

        /**
         * Stringa per lo stato di una partita vinta dal bianco.
         */
        public static final String WHITE_WINS_STATE = "Vince il bianco";

        /**
         * Stringa per l'eccezione lanciata se la stringa di gioco non è nel formato corretto.
         */
        public static final String INVALID_GAME_STRING_EXCEPTION = "La stringa di gioco non e' nel formato corretto";

        /**
         * Stringa per l'eccezione lanciata se la stringa di gioco non contiene il giocatore corrente.
         */
        public static final String NO_CURRENT_PLAYER_EXCEPTION =
            "La stringa di gioco non contiene il giocatore corrente";

        /**
         * Stringa per l'eccezione lanciata se il giocatore corrente non è valido.
         */
        public static final String INVALID_CURRENT_PLAYER_EXCEPTION = "Il giocatore corrente non e' valido";

        /**
         * Stringa per l'eccezione lanciata se il giocatore presente nella cella di partenza non è valido.
         */
        public static final String INVALID_STARTING_CELL_EXCEPTION =
            "La cella di partenza non contiene il giocatore corrente";
    }

    /**
     * Stringhe utilizzate in {@link it.uniba.app.commands.HelpCommand}.
     */
    public static final class HelpCommand {
        private HelpCommand() {
        }

        /**
         * Stringa che contiene la versione del gioco.
         */
        public static final String GAME_VERSION = "ATAXX 1.0.0 Team Berners-Lee";

        /**
         * Stringa che contiene gli sviluppatori del gioco.
         */
        public static final String DEVELOPERS = """
                Prodotto da:
                  - Marco Ferrara
                  - Davide Carella
                  - Maria Teresa Di Chio
                  - Luca Ardito
                  - Yuri Colangelo""";

        /**
         * Stringa di utilizzo.
         */
        public static final String HOW_TO = "\nUtilizzo: java -jar <jarfile> [opzioni]";

        /**
         * Header la lista di opzioni.
         */
        public static final String OPTIONS = "Opzioni:";

        /**
         * Descrizione delle opzioni `-h` e `--help`.
         */
        public static final String HELP_OPTION = "  " + App.HELP_SHORT_OPTION + ", " + App.HELP_LONG_OPTION
                                                 + "\t\tVisualizza questo messaggio di aiuto";

        /**
         * Header per la lista di comandi disponibili.
         */
        public static final String IN_GAME_COMMANDS = "Comandi disponibili in gioco";

        /**
         * Descrizione del comando `/help`.
         */
        public static final String HELP_COMMAND = "  " + AppController.HELP_COMMAND
                                                  + "\t\t\t\tVisualizza questo messaggio di aiuto";

        /**
         * Descrizione del comando `/gioca`.
         */
        public static final String PLAY_COMMAND = "  " + AppController.PLAY_COMMAND
                                                  + "\t\t\tAvvia il gioco se nessuna partita e' in corso";

        /**
         * Descrizione del comando `/vuoto`.
         */
        public static final String EMPTY_BOARD_COMMAND = "  " + AppController.EMPTY_BOARD_COMMAND
                                                         + "\t\t\tMostra il tavoliere vuoto";

        /**
         * Descrizione del comando `/tavoliere`.
         */
        public static final String BOARD_COMMAND = "  " + AppController.BOARD_COMMAND
                                                   + "\t\tIn gioco, "
                                                   + "mostra la posizione di tutte le pedine sul tavoliere";

        /**
         * Descrizione del comando `/qualimosse`.
         */
        public static final String WHAT_MOVES_COMMAND = "  " + AppController.WHAT_MOVES_COMMAND
                                                        + "\t\tIn gioco, "
                                                        + "mostra tutte le mosse disponibili per il giocatore di turno";
        /**
         * Descrizione del comando `/abbandona`.
         */
        public static final String QUIT_COMMAND = "  " + AppController.QUIT_COMMAND
                                                  + "\t\tIn gioco, "
                                                  + "abbandona la partita in corso, previa conferma";

        /**
         * Descrizione del comando `/esci`.
         */
        public static final String EXIT_COMMAND = "  " + AppController.EXIT_COMMAND
                                                  + "\t\t\t\tEsce dall'applicazione, previa conferma";

    }

    /**
     * Stringhe utilizzate in {@link it.uniba.app.commands.WhatMovesCommand}.
     */
    public static final class WhatMovesCommand {
        private WhatMovesCommand() {
        }

        /**
         * Messaggio mostrato se non c'è nessuna partita in corso.
         */
        public static final String NO_RUNNING_GAME = "Nessuna partita in corso. "
                                                     + "Avvia una nuova partita con il comando "
                                                     + AppController.PLAY_COMMAND
                                                     + ".";
    }

    /**
     * Stringhe utilizzate in {@link it.uniba.app.commands.PlayCommand}.
     */
    public static final class PlayCommand {
        private PlayCommand() {
        }

        /**
         * Messaggio mostrato se una partita è già in corso.
         */
        public static final String GAME_ALREADY_STARTED = "Partita già in corso. Completala o terminala"
                                                          + " prima di iniziarne una nuova.\n";
    }

    /**
     * Stringhe utilizzate in {@link it.uniba.app.commands.BoardCommand}.
     */
    public static final class BoardCommand {
        private BoardCommand() {
        }

        /**
         * Messaggio mostrato se nessuna partita è ancora in corso.
         */
        public static final String NO_RUNNING_GAME = "Nessuna partita in corso. "
                                                     + "Avvia una nuova partita con il comando "
                                                     + AppController.PLAY_COMMAND
                                                     + ".";
    }

    /**
     * Stringhe utilizzate in {@link it.uniba.app.commands.QuitCommand}.
     */
    public static final class QuitCommand {
        private QuitCommand() {
        }

        /**
         * Messaggio mostrato per chiedere conferma dell'abbandono partita.
         */
        public static final String QUIT_CONFIRM = "Sei sicuro di voler abbandonare la partita? (s/n)";

        /**
         * Messaggio mostrato per definire il giocatore che ha vinto e con quale punteggio.
         */
        public static final String QUIT_WIN_FORMAT = "Il %s ha vinto %d a 0 per l'abbandono del %s!%n";
    }

    /**
     * Stringhe utilizzate in {@link it.uniba.app.commands.ExitCommand}.
     */
    public static final class ExitCommand {

        private ExitCommand() {
        }

        /**
         * Messaggio di conferma per l'uscita.
         */
        public static final String EXIT_CONFIRMATION = "Sei sicuro di voler uscire dal gioco? (s/n)";
    }

    /**
     * Stringhe utilizzate in {@link it.uniba.app.commands.TimeCommand}.
     */
    public static final class TimeCommand {

        private TimeCommand() {
        }

        /**
         * Messaggio per stampare il tempo di gioco.
         */
        public static final String TIME_FORMAT = "Tempo di gioco: %02d:%02d:%02d%n";
    }

    /**
     * Stringhe utilizzate in più comandi.
     */
    public static final class Common {

        private Common() {
        }

        /**
         * Messaggio mostrato se nessuna partita è ancora in corso.
         */
        public static final String NO_RUNNING_GAME = "Nessuna partita in corso. "
                                                     + "Avvia una nuova partita con il comando "
                                                     + Strings.AppController.PLAY_COMMAND
                                                     + ".";
    }


    public static final class BlockCommand {
        private BlockCommand() {
        }

        /**
         * Messaggio di errore per il tentativo di blocco di celle a partita in corso.
         */
        public static final String GAME_RUNNING_EXCEPTION = "Impossibile bloccare celle durante una partita in corso.";

        /**
         * Messaggio di errore per il tentativo di bloccare troppe celle.
         */
        public static final String MAX_BLOCKED_CELLS_EXCEPTION = "Hai raggiunto il numero massimo di celle bloccate.";

        /**
         * Messaggio di errore per il tentativo di bloccare una cella già bloccata.
         */
        public static final String CELL_ALREADY_BLOCKED_EXCEPTION = "La cella selezionata è già bloccata.";

        /**
         * Messaggio di avvenuto blocco di una cella.
         */
        public static final String CELL_BLOCKED = "Cella bloccata: ";

    }
}
