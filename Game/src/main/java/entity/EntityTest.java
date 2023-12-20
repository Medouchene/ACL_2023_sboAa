package entity;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import main.GamePanel;
import entity.Entity;

public class EntityTest {
    private Entity entity;
    private GamePanel gp;

    @Before
    public void setUp() {
        gp = new GamePanel();
        entity = new Entity(gp);
    }

    @Test
    public void testGetPlayerX() {
        // Test de la méthode getPlayerX
        entity.x = 100;
        int playerX = entity.getPlayerX();
        System.out.println("Test de la méthode testGetPlayerX :");
        System.out.println("Valeur attendue pour la position X du joueur : 100");
        System.out.println("Valeur réelle de la position X du joueur : " + playerX);
        assertEquals(100, playerX);
    }

    @Test
    public void testGetPlayerY() {
        // Test de la méthode getPlayerY
        entity.y = 200;
        int playerY = entity.getPlayerY();
        System.out.println("Test de la méthode testGetPlayerY :");
        System.out.println("Valeur attendue pour la position Y du joueur : 200");
        System.out.println("Valeur réelle de la position Y du joueur : " + playerY);
        assertEquals(200, playerY);
    }

    @Test
    public void testDefaultValues() {
        // Test des valeurs par défaut des attributs de l'entité
        System.out.println("Test de la méthode testDefaultValues :");
        assertEquals(0, entity.x);
        assertEquals(0, entity.y);
        assertEquals(0, entity.speed);
        assertFalse(entity.collision4);
        
        assertEquals(0, entity.spriteCounter);
        assertEquals(1, entity.spriteNum);
        
        assertEquals(0, entity.solidAreaDefaultX);
        assertEquals(0, entity.solidAreaDefaultY);
        assertFalse(entity.collisionOn);
    }
}
