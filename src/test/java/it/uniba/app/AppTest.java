package it.uniba.app;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Main test class of the application.
 */
class AppTest {
    /**
     * Main test method.
     */
    @Test
    void mainTest() {
        String [] args = {""};
        App.main(args);
        assertNotNull(Arrays.toString(args), "args should not be null");
    }
}
