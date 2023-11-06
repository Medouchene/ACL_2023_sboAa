package GamePack;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class GamePanel {
	public static void main(String[] args) {

		JFrame frame = new JFrame("Labyrinthe Game");
        frame.setSize(400, 400);
        frame.setResizable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        LectureCommand control= new LectureCommand();
        frame.add(control);
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                control.movePoint(e.getKeyCode());
            }
        });
        frame.pack();
        frame.setVisible(true);
        frame.setFocusable(true);
        frame.requestFocus();
	}
}

class LectureCommand extends JPanel  {

	  Plateau p = new Plateau();
	  
	//les murs
	private int minX=0;
	private int maxX=p.largeur-10;
	private int minY=0;
	private int maxY=p.longueur-10;
	
	  double r=Math.random();
	  double r1=Math.random();
	  private int newPointX = (int) (r*(p.largeur-20));
	  private int newPointY = (int) (r1*(p.longueur-20));
	  
	  private BufferedImage heroImage;
	  private BufferedImage murImage;
	  private BufferedImage solImage;
	    public LectureCommand() {
	        try {
	            heroImage = ImageIO.read(new File("/home/ouchene/Bureau/GIT/mon_projet/ACL_2023_sboAa/Documents/images/hero.png"));
	            murImage = ImageIO.read(new File("/home/ouchene/Bureau/GIT/mon_projet/ACL_2023_sboAa/Documents/images/mur.jpg"));
	            solImage = ImageIO.read(new File("/home/ouchene/Bureau/GIT/mon_projet/ACL_2023_sboAa/Documents/images/sol.jpg"));
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	  
	  public void movePoint(int keyCode) {
	        int stepSize = 10; // c la vitesse de controle
	        int pointX = newPointX;
	        int pointY = newPointY;
	        if (keyCode == KeyEvent.VK_LEFT) {
	            pointX -= stepSize;
	        } else if (keyCode == KeyEvent.VK_RIGHT) {
	            pointX += stepSize;
	        } else if (keyCode == KeyEvent.VK_UP) {
	            pointY -= stepSize;
	        } else if (keyCode == KeyEvent.VK_DOWN) {
	            pointY += stepSize;
	        }
	        if ((pointX<=maxX-30 && pointX>=minX) && (pointY<=maxY-20 && pointY>=minY+10) ) {
		        
	        	if (!checkObs(pointX, pointY)) {
	        		newPointX=pointX;
			        newPointY=pointY;
		        	repaint(); // pour actualiser la pos du point
	        	}
	        	

	        }
	    }
	// Fonction pour vérifier les collisions avec les obstacles
	  private boolean checkObs(int x, int y) {
	      // Coordonnées des obstacles
	      int obstacle1X = (maxX / 2) + 50;
	      int obstacle1Y = maxY - 60;
	      int obstacle1Width = 40;
	      int obstacle1Height = 40;

	      int obstacle2X = (maxX / 2) - 50;
	      int obstacle2Y = maxY / 2;
	      int obstacle2Width = 30;
	      int obstacle2Height = 30;

	      int obstacle3X = (maxX / 2) + 50;
	      int obstacle3Y = (maxY / 2) - 100;
	      int obstacle3Width = 20;
	      int obstacle3Height = 20;

	      if ((x + 30 >= obstacle1X && x <= obstacle1X + obstacle1Width && y + 30 >= obstacle1Y && y <= obstacle1Y + obstacle1Height) ||
	          (x + 30 >= obstacle2X && x <= obstacle2X + obstacle2Width && y + 30 >= obstacle2Y && y <= obstacle2Y + obstacle2Height) ||
	          (x + 30 >= obstacle3X && x <= obstacle3X + obstacle3Width && y + 30 >= obstacle3Y && y <= obstacle3Y + obstacle3Height)) {
	          return true; 
	      }

	      return false; 
	  }
	  
	
	  protected void paintComponent(Graphics g) {
	        super.paintComponent(g);

	        // Dessiner les murs
	      // g.setColor(Color.gray);
	       
	       /* g.fillRect(minX, minY, minX + 10, maxY);
	        g.fillRect(minX, minY, maxX, minY + 10);
	        g.fillRect(maxX, minY, minX + 10, maxY + 10);
	        g.fillRect(minX, maxY, maxX + 10, minY + 10);
	        */
	     
	       g.drawImage(murImage, minX, minY, maxX - minX, 10, null);
	       g.drawImage(murImage, minX, minY, 10, maxY - minY, null);
	       g.drawImage(murImage, maxX, minY, minX+10, maxY + 10, null);
	       g.drawImage(murImage, minX, maxY, maxX + 10, minY + 10, null);

	        // Dessiner le sol
	        g.drawImage(solImage, minX+10, minY+10, maxX - minX - 10 , maxY - minY- 10, null);


	        // Dessiner les obstacles
	     /*   g.fillRect((maxX / 2) + 50, maxY - 60, 40, 40); // obs 1
	        g.fillRect((maxX / 2) - 50, maxY / 2, 30, 30); // obs 2
	        g.fillRect((maxX / 2) + 50, (maxY / 2) - 100, 20, 20); // obs 3
	        */
	        g.drawImage(murImage, (maxX / 2) + 50, maxY - 60, 40, 40, null);
	        g.drawImage(murImage, (maxX / 2) - 50, maxY / 2, 30, 30, null);
	        g.drawImage(murImage, (maxX / 2) + 50, (maxY / 2) - 100, 20, 20, null);

	        // Dessiner le héros (image "hero.png")
	        if (heroImage != null) {
	            g.drawImage(heroImage, newPointX, newPointY, 30 , 30, null);
	        }
	    }
}
