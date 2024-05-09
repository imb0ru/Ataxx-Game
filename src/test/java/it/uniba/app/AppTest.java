package it.uniba.app;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Main test class of the application.
 */
class AppTest {

        /**
        * Test of the main method.
        */
        @Test
         void mainTest() {
            String [] args = {""};
            App.main(args);
            assertNotNull(Arrays.toString(args), "args should not be null");
        }

}
