import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;

/**
 * The {@code Main} class represents the main entry point for the shipping simulation application.
 *
 * <p>It initializes the loggers, creates instances of the tunnel, piers, and ship generator, and manages the execution of threads.
 *
 * @see LoggerUtil
 * @see Tunnel
 * @see Pier
 * @see ShipGenerator
 */

public class Main {

    /**
     * The main method to start the shipping simulation.
     *
     * <p>It sets up loggers, creates instances of the tunnel, piers, and ship generator.
     * It then manages the execution of threads for loading ships into piers and generating ships through the generator.
     *
     * @param args Command-line arguments (not used in this application).
     */

    public static void main(String[] args) {
        LoggerUtil.setupLogger();

        Tunnel tunnel = new Tunnel();

        Pier breadPier = new Pier("bread");
        Pier bananaPier = new Pier("banana");
        Pier clothingPier = new Pier("clothing");

        ShipGenerator shipGenerator = new ShipGenerator(tunnel, breadPier, bananaPier, clothingPier, 5);

        ExecutorService pierExecutorService = Executors.newFixedThreadPool(3);
        pierExecutorService.submit(breadPier::loadShip);
        pierExecutorService.submit(bananaPier::loadShip);
        pierExecutorService.submit(clothingPier::loadShip);

        ExecutorService generatorExecutorService = Executors.newSingleThreadExecutor();
        generatorExecutorService.submit(shipGenerator);

        try {
            generatorExecutorService.awaitTermination(Long.MAX_VALUE, java.util.concurrent.TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            LoggerUtil.getMainLogger().log(Level.SEVERE, "Interrupted while waiting for generator to finish.", e);
            Thread.currentThread().interrupt();
        }

        pierExecutorService.shutdown();

        try {
            pierExecutorService.awaitTermination(Long.MAX_VALUE, java.util.concurrent.TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            LoggerUtil.getMainLogger().log(Level.SEVERE, "Interrupted while waiting for piers to finish.", e);
            Thread.currentThread().interrupt();
        }
    }
}
