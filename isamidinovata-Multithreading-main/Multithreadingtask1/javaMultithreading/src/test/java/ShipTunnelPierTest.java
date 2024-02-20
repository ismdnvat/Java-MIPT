import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ShipTunnelPierTest {

    @Test
    public void testShipTunnelPierInteraction() throws InterruptedException {
        Tunnel tunnel = new Tunnel();
        Pier breadPier = new Pier("bread");
        Pier bananaPier = new Pier("banana");
        Pier clothingPier = new Pier("clothing");
        ShipGenerator shipGenerator = new ShipGenerator(tunnel, breadPier, bananaPier, clothingPier, 0);

        Thread breadPierThread = new Thread(breadPier::loadShip);

        Thread bananaPierThread = new Thread(bananaPier::loadShip);

        Thread clothingPierThread = new Thread(clothingPier::loadShip);

        breadPierThread.start();
        bananaPierThread.start();
        clothingPierThread.start();

        Thread.sleep(100);
        Thread shipGeneratorThread = new Thread(shipGenerator);
        shipGeneratorThread.start();
        shipGeneratorThread.join();

        assertEquals(10, breadPier.getInventory());
        assertEquals(50, bananaPier.getInventory());
        assertEquals(100, clothingPier.getInventory());

        assertEquals(0, tunnel.getShipsInTunnel());
    }
}
