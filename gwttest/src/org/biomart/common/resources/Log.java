/*
 
 This library is free software; you can redistribute it and/or
 modify it under the terms of the GNU Lesser General Public
 License as published by the Free Software Foundation; either
 version 2.1 of the License, or (at your option) any later version.
 
 This library is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the itmplied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 Lesser General Public License for more details.
 
 You should have received a copy of the GNU Lesser General Public
 License along with this library; if not, write to the Free Software
 Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */

package org.biomart.common.resources;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.apache.log4j.Appender;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.Layout;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.RollingFileAppender;
import org.biomart.common.constants.Constants;

/**
 * Logs messages. This static class is configured by the
 * {@link Settings#setApplication(String)} method.
 * 
 */
public class Log {
	private static final Level DEFAULT_LOGGER_LEVEL = Level.INFO;

	private static final Layout defaultLayout = new PatternLayout(
			"%d{" + Constants.UTC_DATE_FORMAT + "} %-5p : %m%n");

	private static final Appender defaultAppender = new ConsoleAppender(
			Log.defaultLayout, ConsoleAppender.SYSTEM_ERR);

	private static Logger logger;

	static {
        String level = System.getProperty("log.level", "info");
        Level levelObj;

        if (level != null) {
            levelObj = Level.toLevel(level);
        } else {
            levelObj = Log.DEFAULT_LOGGER_LEVEL;
        }
		// Create the default logger.
		Log.logger = Logger.getRootLogger();
        Log.logger.removeAllAppenders();
		Log.logger.setLevel(levelObj);
		Log.logger.addAppender(Log.defaultAppender);
	}

	/**
	 * Configures the logger for the given application, which has an application
	 * settings directory in the given location. The logger will log at
	 * {@link Level#INFO} or above by default, using a
	 * {@link RollingFileAppender}. You can override these settings and any
	 * others by placing a file named <tt>log4j.properties</tt> in the
	 * application settings directory. Settings in that file will be used in
	 * preference to the defaults. The logger name will be the same as the
	 * application name in lower-case, eg. <tt>martbuilder</tt>.
	 * <p>
	 * Until this method is called, the default root logger is used. The default
	 * logging level is {@link Level#INFO} and logs to STDERR.
	 * 
	 * @param app
	 *            the name of the application we are logging for.
	 * @param appDir
	 *            the application settings directory for that application.
	 */
	public static void configure(final String app, final File appDir) {
		// Make the log directory.
		final File logDir = new File(appDir, "logs");
		if (!logDir.exists())
			logDir.mkdir();
		// Remove the default appender from the root logger.
		Logger.getRootLogger().removeAppender(Log.defaultAppender);
		// Set up the default logger.
		Log.logger = Logger.getLogger(app);
		Log.logger.setLevel(Log.DEFAULT_LOGGER_LEVEL);
		try {
			Log.logger.addAppender(new DailyRollingFileAppender(defaultLayout, 
                    new File(logDir, "application.log").getCanonicalPath(), ".yyyy-MM-dd"));
//			Log.logger.addAppender(new RollingFileAppender(Log.defaultLayout,
//					(new File(logDir, app + ".%d{yyyy_MM_dd}.log")).getPath(), true));
		} catch (final Throwable t) {
			// Fall-back to the defaults if we can't write to file.
			Log.logger.addAppender(Log.defaultAppender);
			Log.warn("No rolling logger", t);
		}
		// Attempt to load any user-defined settings.
		try {
			//final File log4jPropsFile = new File(appDir, "log4j.properties");
			final File log4jPropsFile = new File("./conf/xml/log4j.properties");
			final Properties log4jProps = new Properties();
			log4jProps.load(new FileInputStream(log4jPropsFile));
			PropertyConfigurator.configure(log4jProps);
		} catch (final Throwable t) {
			Log.warn("No custom logger", t);
		}
	}

	/**
	 * Are we debugging?
	 * 
	 * @return <tt>true</tt> if we are.
	 */
	public static boolean isDebug() {
		return Log.logger.getLevel().isGreaterOrEqual(Level.DEBUG);
	}

	/**
	 * See {@link Logger#debug(Object)}.
	 * 
	 * @param message
	 *            the message to log.
	 */
	public static void debug(final Object message) {
		Log.logger.debug(getCallerClassName() + " - " + message);
	}

	/**
	 * See {@link Logger#debug(Object,Throwable)}.
	 * 
	 * @param message
	 *            the message to log.
	 * @param t
	 *            the error stack trace.
	 */
	public static void debug(final Object message, final Throwable t) {
		Log.logger.debug(getCallerClassName() + " - " + message, t);
	}

	/**
	 * See {@link Logger#info(Object)}.
	 * 
	 * @param message
	 *            the message to log.
	 */
	public static void info(final Object message) {
		Log.logger.info(getCallerClassName() + " - " + message);
	}

	/**
	 * See {@link Logger#info(Object,Throwable)}.
	 * 
	 * @param message
	 *            the message to log.
	 * @param t
	 *            the error stack trace.
	 */
	public static void info(final Object message, final Throwable t) {
		Log.logger.info(getCallerClassName() + " - " + message, t);
	}

	/**
	 * See {@link Logger#warn(Object)}.
	 * 
	 * @param message
	 *            the message to log.
	 */
	public static void warn(final Object message) {
		Log.logger.warn(getCallerClassName() + " - " + message);
	}

	/**
	 * See {@link Logger#warn(Object,Throwable)}.
	 * 
	 * @param message
	 *            the message to log.
	 * @param t
	 *            the error stack trace.
	 */
	public static void warn(final Object message, final Throwable t) {
		Log.logger.warn(getCallerClassName() + " - " + message, t);
	}

	/**
	 * See {@link Logger#error(Object)}.
	 * 
	 * @param message
	 *            the message to log.
	 */
	public static void error(final Object message) {
		Log.logger.error(getCallerClassName() + " - " + message);
	}

	/**
	 * See {@link Logger#error(Object,Throwable)}.
	 * 
	 * @param message
	 *            the message to log.
	 * @param t
	 *            the error stack trace.
	 */
	public static void error(final Object message, final Throwable t) {
		Log.logger.error(getCallerClassName() + " - " + message, t);
	}

	/**
	 * See {@link Logger#fatal(Object)}.
	 * 
	 * @param message
	 *            the message to log.
	 */
	public static void fatal(final Object message) {
		Log.logger.info(getCallerClassName() + " - " + message);
	}

	/**
	 * See {@link Logger#fatal(Object,Throwable)}.
	 * 
	 * @param message
	 *            the message to log.
	 * @param t
	 *            the error stack trace.
	 */
	public static void fatal(final Object message, final Throwable t) {
		Log.logger.info(getCallerClassName() + " - " + message, t);
	}

	// Private means that this class is a static singleton.
	private Log() {
	}

    /**
     * Info about the logger caller
     */
    static class CallInfo {

        public String className;
        public String methodName;

        public CallInfo() {
        }

        public CallInfo(String className, String methodName) {
            this.className = className;
            this.methodName = methodName;
        }
    }

    /**
     * @return the className of the class actually logging the message
     */
    static String getCallerClassName() {
        final int level = 5;
        return getCallerClassName(level);
    }
    
    /**
     * @return the className of the class actually logging the message
     */
    static String getCallerClassName(final int level) {
        CallInfo ci = getCallerInformations(level);
        return ci.className;
    }

    /**
     * Examine stack trace to get caller
     * @param level method stack depth
     * @return who called the logger
     */
    static CallInfo getCallerInformations(int level) {
        StackTraceElement[] callStack = Thread.currentThread().getStackTrace();
        StackTraceElement caller = callStack[level];
        return new CallInfo(caller.getClassName(), caller.getMethodName());
    }
}
