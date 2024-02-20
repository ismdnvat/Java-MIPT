import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The {@code Pier} class represents a pier where ships can be loaded.
 *
 * <p>Ships are enqueued in a {@link BlockingQueue} and can be loaded one by one.
 *
 * @see Ship
 */

public class Pier {
    private static final Logger logger = Logger.getLogger(Pier.class.getName());

    private final String name;
    private final BlockingQueue<Ship> shipQueue;

    /**
     * Constructs a new Pier with the specified name.
     *
     * @param name The name of the pier.
     */

    public Pier(String name) {
        this.name = name;
        this.shipQueue = new LinkedBlockingQueue<>();
    }

    /**
     * Performs the loading of ships from the pier.
     *
     * <p>Ships are taken from the queue and loaded with simulated delays based on their capacity.
     *
     * <p>This method runs in a loop until the thread is interrupted.
     *
     * @throws InterruptedException If the thread is interrupted while waiting for a ship.
     *
     * @see Ship
     */

    public void loadShip() {
        try {
            while (!Thread.interrupted()) {
                Ship ship = shipQueue.take();
                logger.log(Level.INFO, name + " Pier is loading Ship " + ship.getType() + " with capacity " + ship.getCapacity());
                TimeUnit.SECONDS.sleep(ship.getCapacity() / 10);
                logger.log(Level.INFO, "Ship " + ship.getType() + " with capacity " + ship.getCapacity() + " loaded at " + name + " Pier.");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Enqueues a ship at the pier for later loading.
     *
     * @param ship The ship to be enqueued.
     * @throws InterruptedException If the thread is interrupted while enqueuing the ship.
     *
     * @see Ship
     */

    public void enqueueShip(Ship ship) {
        try {
            shipQueue.put(ship);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Gets the current inventory size of ships waiting to be loaded.
     *
     * @return The number of ships in the pier's inventory.
     */

    public int getInventory() {
        return shipQueue.size();
    }
}
