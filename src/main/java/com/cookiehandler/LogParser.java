package com.cookiehandler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility class for parsing the cookie log CSV file.
 */
public class LogParser {

    /**
     * Reads the log file and returns a list of log entries.
     * Each entry is a String array: [cookie, timestamp].
     *
     * @param filename the path to the log file
     * @return a list of cookie entries from the file
     * @throws RuntimeException if an I/O error occurs while reading the file
     */
    public static List<String[]> readLog(String filename) {
        List<String[]> entries = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line = reader.readLine(); // Skip header
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", 2);
                if (parts.length == 2) {
                    entries.add(parts);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading log file: " + filename, e);
        }
        return entries;
    }
}