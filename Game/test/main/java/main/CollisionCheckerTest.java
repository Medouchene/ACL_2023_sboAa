package main;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.awt.Rectangle;
import entity.Entity;
import entity.Monstre;
import main.GamePanel;

public class CollisionCheckerTest {
    private GamePanel gp;
    private CollisionChecker collisionChecker;

    @Before
    public void setUp() {
        gp = new GamePanel();
        collisionChecker = new CollisionChecker(gp);
    }

    @Test
    public void testCheckTile() {
        Entity entity = new Entity(gp);
        entity.x = 100;
        entity.y = 100;
        entity.solidArea = new Rectangle(10, 10, 20, 20);
        entity.direction = "up";
        entity.speed = 5;

        gp.tileM.mapTileNum[3][3] = 1; // Assuming tile 1 is collision-enabled
        gp.tileM.mapTileNum[3][2] = 2; // Assuming tile 2 is collision-enabled

        collisionChecker.checkTile(entity);
        assertTrue(entity.collisionOn);

    
    }

    
}
