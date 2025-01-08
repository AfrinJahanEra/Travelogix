package Test;

import org.junit.jupiter.api.Test;

import Traveler.TransportBrowser;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TransportBrowserTest {

    @Test
    public void testSearchTransports_PartialMatch() {
        TransportBrowser browser = new TransportBrowser();
        String testBusFile = "src\\TXT_Files\\bus.txt"; 

        List<String[]> results = browser.searchTransports("Dhaka", "Khulna", testBusFile);

        // Assert that the result matches the expected bus data
        assertEquals(3, results.size());
        assertEquals("Green Line", results.get(0)[0].trim());
        assertEquals("Dhaka", results.get(0)[1].trim());
        assertEquals("Khulna", results.get(0)[2].trim());
    }

    @Test
    public void testSearchTransports_NoMatch() {
        TransportBrowser browser = new TransportBrowser();
        String testBusFile = "src\\TXT_Files\\bus.txt"; // Provide a test file path

        List<String[]> results = browser.searchTransports("du", "cu", testBusFile);

        // Assert no results found
        assertTrue(results.isEmpty());
    }
}
