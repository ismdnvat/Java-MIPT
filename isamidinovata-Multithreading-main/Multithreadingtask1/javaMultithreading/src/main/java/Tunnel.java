import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The {@code Tunnel} class represents a tunnel that ships can enter for processing.
 *
 * <p>It uses a {@link Semaphore} to control the number of ships allowed in the tunnel simultaneously.
 *
 * @see Ship
 */

public class Tunnel {
    private static final Logger logger = Logger.getLogger(Tunnel.class.getName());

    private final Semaphore semaphore;
    private int shipsInTunnel;

    /**
     * Constructs a new Tunnel with an initial capacity of allowing 5 ships concurrently.
     */

    public Tunnel() {
        this.semaphore = new Semaphore(5);
        this.shipsInTunnel = 0;
    }

    /**
     * Allows a ship to enter the tunnel, process, and then exit.
     *
     * <p>Ships are logged as they wait to enter, enter, sleep for a simulated processing time, and exit the tunnel.
     *
     * <p>The semaphore is used to control the number of ships allowed in the tunnel simultaneously.
     *
     * @param ship The ship entering the tunnel.
     *
     * @see Ship
     */

    public void enterTunnel(Ship ship) {
        try {
            logger.log(Level.INFO, "Ship with " + ship.getType() + " with capacity " + ship.getCapacity() + " is waiting to enter the tunnel.");
            semaphore.acquire();
            ++shipsInTunnel;
            logger.log(Level.INFO, "Ship with " + ship.getType() + " with capacity " + ship.getCapacity() + " entered the tunnel.");

            TimeUnit.SECONDS.sleep(1);

            logger.log(Level.INFO, "Ship with " + ship.getType() + " with capacity " + ship.getCapacity() + " exited the tunnel.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            semaphore.release();
            --shipsInTunnel;
        }
    }

    /**
     * Gets the current number of ships in the tunnel.
     *
     * @return The number of ships currently in the tunnel.
     */

    public int getShipsInTunnel() {
        return shipsInTunnel;
    }
}
