package main;

import entity.Player;
import tile.TileManager;

import org.junit.Before;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class GamePanelTest {

    private GamePanel gamePanel;

    @Before
    public void setUp() {
        gamePanel = new GamePanel();
        gamePanel.setPreferredSize(new Dimension(800, 600)); 
    }

    @Test
    public void testConstructor() {
        assertNotNull(gamePanel);
        assertEquals(Color.BLACK, gamePanel.getBackground());
        assertEquals(true, gamePanel.isDoubleBuffered());
        assertEquals(true, gamePanel.isFocusable());
    }

    @Test
    public void testSetupGame() {
        gamePanel.setupGame(); 
    }

    
    @Test
    public void testResetGame() {
        gamePanel.niveau = "3"; // Set a level for testing
        gamePanel.resetGame();
        assertEquals("1", gamePanel.niveau); // Assuming resetGame sets the level back to 1
    }

    

    @Test
    public void testPaintComponent() {
        
        BufferedImage image = new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = image.createGraphics();
        gamePanel.paintComponent(g2);
    }

    @Test
    public void testGetTileSize() {
        assertEquals(gamePanel.originalTileSize * gamePanel.scale, gamePanel.getTileSize());
    }
}
