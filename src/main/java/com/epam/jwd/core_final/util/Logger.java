package com.epam.jwd.core_final.util;

public class Logger {
    private static org.apache.log4j.Logger logger= org.apache.log4j.Logger.getLogger(Logger.class);

    public static void info(String message){
        logger.info(message);
    }

    public static void error(String message){
        logger.error(message);
    }
}
