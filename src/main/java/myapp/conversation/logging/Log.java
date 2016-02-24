/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myapp.conversation.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author te071784
 */
public class Log {
    /**
     * Logger category for logging audit, e.g. checking access rights.
     */
    public static Logger AUDIT = createLogger("AUDIT");

    /**
     * Logger category for logging request processing, e.g. request validation,
     * request processing, response preparation.
     */
    public static Logger REQUEST_PROCESSING = createLogger("REQUEST_PROCESSING");

    /**
     * Logger category for logging lifecycle events, e.g. start/stop of
     * application, file system monitoring, connection checking.
     */
    public static Logger LIFECYCLE = createLogger("LIFECYCLE");

    private static Logger createLogger(String name) {
        return LoggerFactory.getLogger("intranet3." + name);
    }
    
}
