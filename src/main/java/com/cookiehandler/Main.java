package com.cookiehandler;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/**
 * Entry point for the Most Active Cookie CLI application.
 * Parses command-line arguments and prints the most active cookie(s) for a given date.
 */
public class Main {
    public static void main(String[] args) {
        Options options = new Options();
        options.addOption("f", "file", true, "Log file to process");
        options.addOption("d", "date", true, "Date to search (YYYY-MM-DD)");

        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine cmd = parser.parse(options, args);

            if (!cmd.hasOption("f") || !cmd.hasOption("d")) {
                throw new ParseException("Missing required options: -f and -d");
            }

            String filename = cmd.getOptionValue("f");
            String date = cmd.getOptionValue("d");

            for (String cookie : CookieAnalyzer.getMostActiveCookies(filename, date)) {
                System.out.println(cookie);
            }
        } catch (ParseException e) {
            System.err.println("Error parsing command-line arguments: " + e.getMessage());
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("MostActiveCookie", options);
        }
    
    }
}