
package com.cookiehandler;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class CookieAnalyzerTest {

    private final String path = "data/cookie_log.csv";

    @Test
    void testMostActiveOn2018_12_09() {
        List<String> result = CookieAnalyzer.getMostActiveCookies(path, "2018-12-09");
        assertEquals(List.of("AtY0laUfhglK3lC7"), result);
    }

    @Test
    void testMostActiveOn2018_12_08() {
        List<String> result = CookieAnalyzer.getMostActiveCookies(path, "2018-12-08");
        assertTrue(result.contains("SAZuXPGUrfbcn5UA"));
        assertTrue(result.contains("4sMM2LxV07bPJzwf"));
        assertTrue(result.contains("fbcn5UAVanZf6UtG"));
        assertEquals(3, result.size());
    }

    @Test
    void testNoResultsForEmptyDate() {
        List<String> result = CookieAnalyzer.getMostActiveCookies(path, "2018-12-06");
        assertTrue(result.isEmpty()); 
    }
}
