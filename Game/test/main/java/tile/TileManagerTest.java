package tile;
import org.junit.Test;

import main.GamePanel;

import static org.junit.Assert.*;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.image.DataBuffer;
import javax.imageio.ImageIO;
import java.io.IOException;

public class TileManagerTest {

    @Test
    public void testLoadMap() {
        GamePanel gp = new GamePanel();
        TileManager tileManager = new TileManager(gp);
        

        // Vérifiez si la carte a été chargée correctement en vérifiant une valeur de tuile spécifique
        assertEquals(5, tileManager.mapTileNum[tileManager.DoorX][tileManager.DoorY]);
    }

    
}
