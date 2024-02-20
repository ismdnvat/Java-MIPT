import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The {@code Ship} class represents a ship entity that can enter a tunnel, wait for some time,
 * and then exit the tunnel to enqueue itself at a pier.
 *
 * <p>This class implements the {@link Runnable} interface, allowing instances of Ship to be
 * executed in a separate thread.
 *
 * <p>Each ship has a type, capacity, associated tunnel, and pier.
 *
 * @see Tunnel
 * @see Pier
 */

public class Ship implements Runnable {
    private static final Logger logger = Logger.getLogger(Ship.class.getName());

    private final String type;
    private final int capacity;
    private final Tunnel tunnel;
    private final Pier pier;

    /**
     * Constructs a new Ship with the specified type, capacity, tunnel, and pier.
     *
     * @param type The type of the ship (e.g., "bread", "banana", "clothing").
     * @param capacity The capacity of the ship.
     * @param tunnel The tunnel associated with the ship.
     * @param pier The pier where the ship will be enqueued.
     */

    public Ship(String type, int capacity, Tunnel tunnel, Pier pier) {
        this.type = type;
        this.capacity = capacity;
        this.tunnel = tunnel;
        this.pier = pier;
    }

    /**
     * Executes the ship's behavior, entering the tunnel, waiting, and then enqueuing itself at the pier.
     *
     * <p>The ship logs its actions using a {@link Logger}.
     */

    @Override
    public void run() {
        try {
            logger.log(Level.INFO, "Ship with " + type + " with capacity " + capacity + " is going to the tunnel.");
            tunnel.enterTunnel(this);
            logger.log(Level.INFO, "Ship with " + type + " with capacity " + capacity + " is in the tunnel.");

            TimeUnit.SECONDS.sleep(1);

            logger.log(Level.INFO, "Ship " + type + " with capacity " + capacity + " has exited the tunnel.");

            pier.enqueueShip(this);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Gets the type of the ship.
     *
     * @return The type of the ship.
     */

    public String getType() {
        return type;
    }

    /**
     * Gets the capacity of the ship.
     *
     * @return The capacity of the ship.
     */

    public int getCapacity() {
        return capacity;
    }
}
