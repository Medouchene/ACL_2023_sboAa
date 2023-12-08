package entity;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import main.GamePanel;
import main.KeyHandler;
import tile.TileManager;


public class PlayerTest {

    private Player player;
    GamePanel gp = new GamePanel();
    KeyHandler ky = new KeyHandler();
    TileManager tileM = new TileManager(gp);

    @Before
    public void setUp() {
        // Initialize the player before each test
        player = new Player(gp,ky,tileM);
    }

    @Test
    public void testInitialization() {
        assertNotNull(player);
        assertEquals(500, player.getVie()); // Assuming MAX_VIE is 500
    }
}
