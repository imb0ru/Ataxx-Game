package it.uniba.app.commands;

import it.uniba.app.controls.AppController;
import it.uniba.app.utils.Strings;

import java.util.HashSet;
import java.util.Set;


/**
 * Classe << Boundary >> che si occupa di eseguire il comando `/blocca`.
 */
public class BlockCommand {
    /**
     * Numero massimo di caselle bloccate.
     */
    private static final int MAX_BLOCKED_CELLS = 9;
    /**
     * Elenco delle caselle bloccate.
     */
    private static final Set<String> blockedCells = new HashSet<>();

    /**
     * Elenco delle caselle di partenza.
     */
    private final Set<String> startingCells = Set.of("a1", "h8");
    private BlockCommand() {
    }

    /**
     * Esegue il comando.
     * @param app riferimento al contesto dell'applicazione
     * @param coordinate coordinate della casella da bloccare
     */
    public static void run(final AppController app, final String coordinate) {
        if (app.getGame() != null) {
            System.out.println(Strings.BlockCommand.GAME_RUNNING_EXCEPTION);
            return;
        }

        if (getBlockedCellsSize() >= MAX_BLOCKED_CELLS) {
            System.out.println(Strings.BlockCommand.MAX_BLOCKED_CELLS_EXCEPTION);
            return;
        }

        if (coordinate == null || coordinate.isEmpty()) {
            System.out.println(Strings.BlockCommand.INVALID_COORDINATES_EXCEPTION);
            return;
        }

        // Estrae le coordinate dal comando
        if (coordinate.length() != 2) {
            System.out.println(Strings.BlockCommand.INVALID_COORDINATES_EXCEPTION);
            return;
        }

        char colChar = coordinate.charAt(0);
        char rowChar = coordinate.charAt(1);

        if (colChar < 'a' || colChar > 'h' || rowChar < '1' || rowChar > '8') {
            System.out.println(Strings.BlockCommand.OUT_OF_BOUNDS_COORDINATES_EXCEPTION);
            return;
        }

        int x = colChar - 'a';
        int y = rowChar - '1';

        //ADDME: Implementare il controllo della cella adiacente
    }

    /**
     * Verifica se una cella è bloccata.
     *
     * @param cell la cella da verificare
     * @return true se la cella è bloccata, false altrimenti
     */
    public boolean isCellBlocked(final String cell) {
        return blockedCells.contains(cell);
    }

    /**
     * Aggiunge una cella all'elenco delle celle bloccate.
     *
     * @param cell la cella da bloccare
     * @return true se la cella è stata bloccata correttamente, false se il numero massimo di celle bloccate è già stato raggiunto o se la cella è una cella di partenza
     */
    public boolean addBlockedCell(final String cell) {
        if (blockedCells.size() >= MAX_BLOCKED_CELLS || startingCells.contains(cell)) {
            return false;
        }
        return blockedCells.add(cell);
    }

    /**
     * Rimuove una cella dall'elenco delle celle bloccate.
     *
     * @param cell la cella da sbloccare
     * @return true se la cella è stata sbloccata correttamente, false altrimenti
     */
    public boolean removeBlockedCell(final String cell) {
        return blockedCells.remove(cell);
    }

    /**
     * Svuota l'elenco delle celle bloccate.
     */
    public void clearBlockedCells() {
        blockedCells.clear();
    }

    /**
     * Restituisce il numero di celle bloccate.
     *
     * @return il numero di celle bloccate
     */
    public static int getBlockedCellsSize() {
        return blockedCells.size();
    }
}
