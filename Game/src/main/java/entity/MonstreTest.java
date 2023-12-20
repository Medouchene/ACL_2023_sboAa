package entity;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import main.GamePanel;
import tile.TileManager;

public class MonstreTest {
    private Monstre monstre;
    
    @Before
    public void setUp() {
        // Créez une instance de Monstre avant chaque test
        GamePanel gp = new GamePanel(/* passez les arguments nécessaires ici */);
        TileManager tileManager = new TileManager(gp);
        monstre = new Monstre(gp, tileManager);
    }
    
    @Test
    public void testDefaultValues() {
        // Testez les valeurs par défaut après la création de Monstre
        assertEquals("down", monstre.direction);
        assertEquals(500, monstre.getMonsterVie()); // Vous devrez peut-être ajuster cette valeur en fonction de votre configuration
        assertEquals(false, monstre.getCollisionPM()); // Assurez-vous que collisionPM est initialisé à false
        // Ajoutez d'autres vérifications ici en fonction de vos besoins
    }
    
    @Test
    public void testMonsterLife() {
        // Testez la vie du monstre
        int initialLife = monstre.getMonsterVie();
        int damage = 50;
        monstre.setMonsterVie(damage);
        assertEquals(initialLife - damage, monstre.getMonsterVie());
    }
    
    @Test
    public void testMonsterPosition() {
        // Testez la position du monstre
        // Modifiez la position du monstre en utilisant setX et setY, puis vérifiez si getX et getY retournent les bonnes valeurs
        int newX = 100;
        int newY = 200;
        monstre.setX(newX);
        monstre.setY(newY);
        assertEquals(newX, monstre.getX());
        assertEquals(newY, monstre.getY());
    }
    
    // Ajoutez d'autres méthodes de test pour d'autres fonctionnalités de Monstre
}
