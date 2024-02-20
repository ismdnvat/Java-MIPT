import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The {@code ShipGenerator} class represents a generator for creating ships with different types and capacities.
 *
 * <p>This class implements the {@link Runnable} interface, allowing instances of ShipGenerator to be executed in a separate thread.
 *
 * @see Ship
 * @see Tunnel
 * @see Pier
 */

public class ShipGenerator implements Runnable {
    private static final Logger logger = Logger.getLogger(ShipGenerator.class.getName());

    private final ExecutorService executorService;
    private final Tunnel tunnel;
    private final Pier breadPier;
    private final Pier bananaPier;
    private final Pier clothingPier;

    private final int minutesToGenerate;
    private final long startTime;

    /**
     * Constructs a new ShipGenerator with the specified tunnel, and piers for bread, banana, and clothing,
     * along with the duration in minutes for ship generation.
     *
     * @param tunnel The tunnel for ships to pass through.
     * @param breadPier The pier for bread shipments.
     * @param bananaPier The pier for banana shipments.
     * @param clothingPier The pier for clothing shipments.
     * @param minutesToGenerate The duration in minutes for ship generation.
     */

    public ShipGenerator(Tunnel tunnel, Pier breadPier, Pier bananaPier, Pier clothingPier, int minutesToGenerate) {
        this.executorService = Executors.newFixedThreadPool(10);
        this.tunnel = tunnel;
        this.breadPier = breadPier;
        this.bananaPier = bananaPier;
        this.clothingPier = clothingPier;
        this.minutesToGenerate = minutesToGenerate;
        this.startTime = System.currentTimeMillis();
    }

    /**
     * Generates ships at regular intervals until the specified duration is reached.
     *
     * <p>Ships are submitted to the executor service for parallel processing.
     *
     * <p>This method runs in a loop until the thread is interrupted or the specified duration is reached.
     *
     * @see Ship
     */

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                Ship ship = generateShip();
                executorService.submit(ship);

                long elapsedTime = System.currentTimeMillis() - startTime;
                if (TimeUnit.MILLISECONDS.toMinutes(elapsedTime) >= minutesToGenerate) {
                    break;
                }
            }
        } finally {
            executorService.shutdownNow();
        }
    }

    /**
     * Generates a ship with a random type and capacity.
     *
     * <p>The ship type is randomly selected from "bread," "banana," or "clothing."
     * The capacity of the ship depends on its type.
     *
     * @return The generated ship.
     *
     * @see Ship
     */

    private Ship generateShip() {
        Random random = new Random();
        String[] types = {"bread", "banana", "clothing"};
        String type = types[random.nextInt(types.length)];

        int capacity = switch (type) {
            case "bread" -> 10;
            case "banana" -> 50;
            case "clothing" -> 100;
            default -> 10;
        };

        logger.log(Level.INFO, "Generated ship: Type - " + type + ", capacity - " + capacity);

        switch (type) {
            case "bread":
                return new Ship("bread", capacity, tunnel, breadPier);
            case "banana":
                return new Ship("banana", capacity, tunnel, bananaPier);
            case "clothing":
                return new Ship("clothing", capacity, tunnel, clothingPier);
            default:
                return new Ship("bread", capacity, tunnel, breadPier);
        }
    }
}
