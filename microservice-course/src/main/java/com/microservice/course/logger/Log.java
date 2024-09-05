package com.microservice.course.logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log {

    public static final Logger logger = LogManager.getLogger("com.courseorganizationapp.course");

    public static void logError(String message, Exception e) {
        logger.error(message, e);
    }

    public static void logDebug(String message) {
        logger.debug(message);
    }

    public static void logInfo(String message) {
        logger.info(message);
    }
    public static void logWarn(String message) {
        logger.warn(message);
    }
    public static void logFatal(String message) {
        logger.fatal(message);
    }
}
