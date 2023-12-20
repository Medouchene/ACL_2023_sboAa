package entity;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import main.GamePanel;
import main.KeyHandler;
import tile.TileManager;

public class PlayerTest {
    private Player player;
    private final static double TOLERANCE = 0.001; // Marge d'erreur pour les comparaisons de doubles

    @Before
    public void setUp() {
        GamePanel gp = new GamePanel(/* passez les arguments nécessaires ici */);
        KeyHandler keyH = new KeyHandler();
        TileManager tileManager = new TileManager(gp);
        player = new Player(gp, keyH, tileManager);
    }

    @Test
    public void testSetVieReduction() {
        int MAX_VIE = 500;
        int initialLife = player.getVie();
        System.out.println("Vie initiale (réduction) : " + initialLife);
        double reduction=0.1;
        player.setVie(-1);
        double expectedReduction = initialLife-2*(MAX_VIE * 0.1) / 100.0;
        
        System.out.println("Vie attendue après réduction : " + expectedReduction);
        System.out.println("Vie actuelle après réduction : " + player.getVie());

        assertEquals(expectedReduction, player.getVie(), TOLERANCE);
    }

    @Test
    public void testSetVieIncrease() {
        int MAX_VIE = 500;
        int initialLife = player.getVie();
        System.out.println("Vie initiale (augmentation) : " + initialLife);

        player.setVie(1);
        double expectedIncrease = initialLife + MAX_VIE / 2.0;
        System.out.println("Vie attendue après augmentation : " + expectedIncrease);
        System.out.println("Vie actuelle après augmentation : " + player.getVie());

        assertEquals(expectedIncrease, player.getVie(), TOLERANCE);
    }

    // Ajoutez d'autres tests et instructions print ici si nécessaire
}

