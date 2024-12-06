package com.keyin.logger;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CustomLogger {

    private static final String RESET = "\u001B[0m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String RED = "\u001B[31m";
    private static final String UNDERLINE = "\u001B[4m";

    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public void logInfo(String msg) {
        System.out.println(formatLog("INFO", msg, GREEN));
    }

    public void logWarning(String msg) {
        System.out.println(formatLog("WARNING", msg, YELLOW));
    }

    public void logError(String msg) {
        System.err.println(formatLog("ERROR", msg, RED));
    }

    public void logAction(String action) {
        System.out.println(UNDERLINE + GREEN + "ACTION: " + action + " triggered" + RESET);
    }

    private String formatLog(String level, String msg, String color) {
        String timestamp = LocalDateTime.now().format(dateTimeFormatter);
        return color + "[" + level + " - " + timestamp + "] " + msg + RESET;
    }
}
