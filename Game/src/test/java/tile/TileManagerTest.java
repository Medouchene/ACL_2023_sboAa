package tile;

import main.GamePanel;
import tile.TileManager;

import org.junit.Before;
import org.junit.Test;

import java.awt.image.BufferedImage;

import static org.junit.Assert.*;

import org.junit.Test;

public class TileManagerTest {

	private GamePanel gamePanel;
    private TileManager tileManager;

    @Before
    public void setUp() {
        // Initialize GamePanel and TileManager before each test
        gamePanel = new GamePanel();
        tileManager = new TileManager(gamePanel);
    }

    @Test
    public void testGetTileImage() {
        // Assuming tile images are loaded correctly
        BufferedImage[] tileImages = new BufferedImage[6]; // Update the array size based on the number of tiles
        tileManager.getTileImage();

        // Assert that the images are not null
        for (int i = 0; i < tileImages.length; i++) {
            assertNotNull(tileManager.tile[i].image);
        }

    }

    @Test
    public void testLoadMap() {
        // Assuming a specific map file, test the loadMap method
        tileManager.loadMap("/maps/map01.txt"); 

    
        assertEquals(1, tileManager.DoorX);
        assertEquals(0, tileManager.DoorY);
    }

}
