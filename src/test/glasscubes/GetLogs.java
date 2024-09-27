package test.glasscubes;

import java.io.*;
import java.util.*;
import java.util.logging.Logger;

public class GetLogs {

	private static Map<String, Integer> errors = new HashMap<>();
	private static Map<String, Integer> warns = new HashMap<>();
	private static Map<String, Integer> infos = new HashMap<>();

	// Takes the individual logs from the .log file.
	private static void processLogs(File file) {

		BufferedReader reader = null;

		try {
			reader = new BufferedReader(new FileReader(file));
			String line = null;
			while ((line = reader.readLine()) != null && line.length() != 0) {
				String type = null;
				String message = null;
				if (line.contains("ERROR")) {
					type = line.substring(15, 20);
					message = line.substring(21);
				} else {
					type = line.substring(15, 19);
					message = line.substring(20);
				}
				if (type.equals("ERROR"))
					addToMap(errors, message);
				else if (type.equals("INFO"))
					addToMap(infos, message);
				else if (type.equals("WARN"))
					addToMap(warns, message);
			}
		} catch (Exception e) {
			System.out.println("Error in reading file: " + file.getName());
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				System.out.println(e);
			}
		}
	}

	// Add logs to the respective Map as per there type.
	private static void addToMap(Map<String, Integer> map, String message) {
		if (map.containsKey(message)) {
			map.put(message, map.get(message) + 1);
		} else {
			map.put(message, 1);
		}
	}

	// Prints the log in the console.
	private static void printLogs(String type, Map<String, Integer> map) {

		Logger logger = Logger.getLogger(GetLogs.class.getName());

		List<Map.Entry<String, Integer>> messageList = new ArrayList<>(map.entrySet());
		messageList.sort((x, y) -> y.getValue().compareTo(x.getValue()));

		System.out.println("\nTop 10 " + type + " Logs:");

		int count = 0;
		for (Map.Entry<String, Integer> entry : messageList) {
			if (count >= 10) {
				break;
			}

			if (type.equals("ERROR"))
				logger.severe(entry.getValue() + " occurences: " + entry.getKey());
			else if (type.equals("WARN"))
				logger.warning(entry.getValue() + " occurences: " + entry.getKey());
			else if (type.equals("INFO"))
				logger.info(entry.getValue() + " occurences: " + entry.getKey());

			count++;
		}
	}

	// Main method.
	public static void main(String[] args) {

		// Change the path as per the Log Directory location..
		// SampleLogFiles folder is used to test the program.
		String path = "./SampleLogFiles";

		File logDir = new File(path);

		File[] logFiles = logDir.listFiles();

		for (File logFile : logFiles) {
			if (logFile.isFile() && logFile.getName().endsWith(".log")) {
				processLogs(logFile);
			}
		}

		printLogs("INFO", infos);
		printLogs("WARN", warns);
		printLogs("ERROR", errors);

	}
}
