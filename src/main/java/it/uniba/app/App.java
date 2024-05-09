package it.uniba.app;

/**
 * Classe App
 * << Control >>
 *
 * <p>Classe principale dell'applicazione.</p>
 */
public final class App {
    /**
     * Costruttore della classe App.
     * Avvia l'applicazione.
     *
     * @param args argomenti della riga di comando
     */
    private App(final String[] args) {
        new AppController(args);
    }

    /**
     * Metodo main dell'applicazione.
     *
     * @param args argomenti della riga di comando
     */
    public static void main(final String[] args) {
        new App(args);
    }


}
