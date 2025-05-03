package com.cookiehandler;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Analyzes a cookie log file to find the most active cookie(s) for a given day.
 */
public class CookieAnalyzer {
    private static final DateTimeFormatter TIMESTAMP_FORMATTER = DateTimeFormatter.ISO_OFFSET_DATE_TIME;

    /**
     * Finds the most active cookies for the specified date.
     *
     * @param filename The path to the log file.
     * @param targetDateStr The target date in YYYY-MM-DD format (UTC).
     * @return A list of the most active cookie(s) for that date.
     * @throws IllegalArgumentException if the target date format is invalid.
     */
    public static List<String> getMostActiveCookies(String filename, String targetDateStr) {
        List<String[]> logEntries = LogParser.readLog(filename);
        Map<String, Integer> frequency = new HashMap<>();
        int max = 0;

        LocalDate targetDate;
        try {
            targetDate = LocalDate.parse(targetDateStr);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format. Please use YYYY-MM-DD.", e);
        }

        for (String[] entry : logEntries) {
            String cookie = entry[0];
            String timestamp = entry[1];

            try {
                ZonedDateTime zdt = ZonedDateTime.parse(timestamp, TIMESTAMP_FORMATTER);
                if (zdt.toLocalDate().equals(targetDate)) {
                    int count = frequency.merge(cookie, 1, Integer::sum);
                    max = Math.max(max, count);
                }
            } catch (DateTimeParseException e) {
                System.err.println("Skipping invalid timestamp: " + timestamp);
            }
        }

        List<String> result = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : frequency.entrySet()) {
            if (entry.getValue() == max) {
                result.add(entry.getKey());
            }
        }

        return result;
    }
}