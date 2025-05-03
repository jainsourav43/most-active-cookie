# Most Active Cookie

A Java command-line tool that identifies the most active cookie(s) for a specified date from a CSV log file.

## Features

- Parses large cookie logs efficiently.
- Handles ISO 8601 timestamps in UTC.
- Supports ties by returning all equally most frequent cookies.
- Tested with JUnit 5.

## Prerequisites

- Java 17 or higher
- [Gradle](https://gradle.org/)

## Build

Use the Gradle wrapper to build the project:

```bash
./gradlew build
```

This compiles the source code and runs all tests.

## Run

To run the CLI tool with arguments:

```bash
./gradlew run --args='-f path/to/cookie_log.csv -d YYYY-MM-DD'
```

### Example

```bash
./gradlew run --args='-f data/cookie_log.csv -d 2018-12-09'
```

### Output

```
AtY0laUfhglK3lC7
```

Each cookie listed is among the most active for the given date.

## Arguments

- `-f`, `--file`: Path to the cookie log CSV file.
- `-d`, `--date`: Date in `YYYY-MM-DD` format to search for.

## Assumptions

- The log file is sorted in reverse chronological order.
- All timestamps are in **UTC** time zone.
- Each log entry is in the format:  
  `<cookie>,<timestamp>`  
  e.g. `AtY0laUfhglK3lC7,2018-12-09T14:19:00+00:00`

## Testing

Run unit tests with:

```bash
./gradlew test
```

Test results will be available at:  
`build/reports/tests/test/index.html`

## Project Structure

```
.
├── build.gradle          # Gradle build configuration
├── data/                 # Sample input files
├── src/
│   ├── main/java/com/cookiehandler/
│   │   ├── Main.java                # CLI and entry point
│   │   ├── CookieAnalyzer.java     # Logic for finding most active cookies
│   │   └── LogParser.java          # Parses and filters the log file
│   └── test/java/com/cookiehandler/
│       └── CookieAnalyzerTest.java # Unit tests using JUnit 5
└── README.md             # Project documentation
```
