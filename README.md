# LoggerApp README

## Overview

`GetLogs` is a simple Java program designed to parse log files and categorize them into three types of log entries: `INFO`, `WARN`, and `ERROR`. The program reads `.log` files from a specified directory, processes the logs, and prints the top 10 occurrences of each log type in the console. This can help in quickly identifying the most common issues or messages in the logs.

## Features

- **Log Parsing**: Reads log entries from `.log` files.
- **Categorization**: Categorizes logs into `INFO`, `WARN`, and `ERROR`.
- **Frequency Count**: Tracks the number of occurrences of each log message.
- **Top 10 Logs**: Outputs the top 10 most frequent log messages of each type.

## Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/GetLogs.git

## Sample Output
Top 10 INFO Logs:
INFO: 2 occurrences: Application started

Top 10 WARN Logs:
WARNING: 1 occurrence: Low memory warning

Top 10 ERROR Logs:
SEVERE: 1 occurrence: Failed to connect to database

