import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The {@code LoggerUtil} class provides utility methods for configuring and accessing loggers in the application.
 *
 * <p>It configures loggers for various classes using {@link ConsoleHandler} to output log messages to the console.
 *
 * @see Logger
 * @see ConsoleHandler
 */

public class LoggerUtil {
    private static final Logger tunnelLogger = Logger.getLogger(Tunnel.class.getName());
    private static final Logger pierLogger = Logger.getLogger(Pier.class.getName());
    private static final Logger shipLogger = Logger.getLogger(Ship.class.getName());
    private static final Logger shipGeneratorLogger = Logger.getLogger(ShipGenerator.class.getName());
    private static final Logger mainLogger = Logger.getLogger(Main.class.getName());

    /**
     * Configures the application's loggers, setting up console handlers and log levels.
     *
     * <p>This method removes existing handlers from the root logger and adds new console handlers
     * with a log level of {@link Level#ALL} for each individual logger in the application.
     */

    public static void setupLogger() {
        Logger rootLogger = Logger.getLogger("");
        Handler[] handlers = rootLogger.getHandlers();
        for (Handler handler : handlers) {
            rootLogger.removeHandler(handler);
        }

        ConsoleHandler tunnelHandler = new ConsoleHandler();
        tunnelHandler.setLevel(Level.ALL);
        tunnelLogger.addHandler(tunnelHandler);
        tunnelLogger.setLevel(Level.ALL);

        ConsoleHandler pierHandler = new ConsoleHandler();
        pierHandler.setLevel(Level.ALL);
        pierLogger.addHandler(pierHandler);
        pierLogger.setLevel(Level.ALL);

        ConsoleHandler shipHandler = new ConsoleHandler();
        shipHandler.setLevel(Level.ALL);
        shipLogger.addHandler(shipHandler);
        shipLogger.setLevel(Level.ALL);

        ConsoleHandler shipGeneratorHandler = new ConsoleHandler();
        shipGeneratorHandler.setLevel(Level.ALL);
        shipGeneratorLogger.addHandler(shipGeneratorHandler);
        shipGeneratorLogger.setLevel(Level.ALL);

        ConsoleHandler mainHandler = new ConsoleHandler();
        mainHandler.setLevel(Level.ALL);
        mainLogger.addHandler(mainHandler);
        mainLogger.setLevel(Level.ALL);
    }

    /**
     * Gets the logger for the tunnel component.
     *
     * @return The logger for the tunnel.
     */

    public static Logger getTunnelLogger() {
        return tunnelLogger;
    }

    /**
     * Gets the logger for the pier component.
     *
     * @return The logger for the pier.
     */

    public static Logger getPierLogger() {
        return pierLogger;
    }

    /**
     * Gets the logger for the ship component.
     *
     * @return The logger for the ship.
     */

    public static Logger getShipLogger() {
        return shipLogger;
    }

    /**
     * Gets the logger for the ship generator component.
     *
     * @return The logger for the ship generator.
     */

    public static Logger getShipGeneratorLogger() {
        return shipGeneratorLogger;
    }

    /**
     * Gets the logger for the main application class.
     *
     * @return The logger for the main application.
     */

    public static Logger getMainLogger() {
        return mainLogger;
    }
}