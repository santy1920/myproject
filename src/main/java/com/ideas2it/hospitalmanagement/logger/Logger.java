package com.ideas2it.hospitalmanagement.logger;

import org.apache.log4j.xml.DOMConfigurator;

/**
 * This class is used to log the error messages.
 *
 */
public class Logger {
    
    private static org.apache.log4j.Logger logger;

    static {
        logger = org.apache.log4j.Logger.getLogger(Logger.class);
        DOMConfigurator.configure("log4j.xml");
    }

    public static void trace(Object message) {
        logger.trace(message);
    }

    public static void debug(Object message) {
        logger.debug(message);
    }

    public static void info(Object message) {
        logger.info(message);
    }
   
    public static void warn(Object message) {
        logger.warn(message);
    }

    public static void error(Object message) {
        logger.error(message);
    }
    
    public static void fatal(Object message) {
        logger.fatal(message);
    }
}
