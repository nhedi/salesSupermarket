package se.kth.ict.nextgenpos.util;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;


/**
 * This class is responsible for the log.
 */
public class LogHandler {
	
	private static final String LOG_FILE_NAME = "supermarketsales-log.txt";
	private PrintWriter logFile;
	
	public LogHandler() throws IOException { 
		logFile = new PrintWriter(new FileWriter(LOG_FILE_NAME), true);
	}
	
	/**
	 * Writes log entries.
	 * @param exception The exception that will be logged.
	 */
	public void logException(Exception exception) { 
		StringBuilder logMsgBuilder = new StringBuilder(); 
		logMsgBuilder.append(createTime()); 
		logMsgBuilder.append(", Exception was thrown: "); 
		logMsgBuilder.append(exception.getMessage());
		logFile.println(logMsgBuilder);
	    exception.printStackTrace(logFile);
	}
	
	private String createTime() {
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
		return now.format(formatter);
	}
}
