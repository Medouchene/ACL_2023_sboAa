package LabyrinthePack;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class GamePanel {
	public static void main(String[] args) {
        LectureCommand lec = new LectureCommand();
        JFrame frame = new JFrame("Labyrinthe Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        LectureCommand control = new LectureCommand();
        Hero hero = new Hero();
        control.setPreferredSize(new Dimension(400, 400)); 
        frame.add(control);

        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                hero.movePoint(e.getKeyCode());
            }
        });

        frame.pack();
        frame.setResizable(false); 
        frame.setVisible(true);
        frame.setFocusable(true);
        frame.requestFocus();

        LectureCommand.update();
    }
}

class LectureCommand extends JPanel  {

	  Plateau p = new Plateau();
	  static Personnage pers = new Personnage();
	  static Hero hero = new Hero();
	//les murs
	
	
	  
	  
	  private BufferedImage heroImage;
	  private BufferedImage monstreImage;
	  private BufferedImage murImage;
	  private BufferedImage solImage;
	    public LectureCommand() {
	        try {
	            heroImage = ImageIO.read(new File("/home/ouchene/Bureau/GIT/mon_projet/ACL_2023_sboAa/Documents/images/hero.png"));
	            monstreImage = ImageIO.read(new File("/home/ouchene/Bureau/GIT/mon_projet/ACL_2023_sboAa/Documents/images/monstre.png"));
	            murImage = ImageIO.read(new File("/home/ouchene/Bureau/GIT/mon_projet/ACL_2023_sboAa/Documents/images/mur.jpg"));
	            solImage = ImageIO.read(new File("/home/ouchene/Bureau/GIT/mon_projet/ACL_2023_sboAa/Documents/images/sol.jpg"));
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	  
	  
	  
	
	  protected void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        int minX=p.minX;
	    	int maxX=p.maxX;
	    	int minY=p.minY;
	    	int maxY=p.maxY;
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

	        // Dessiner le héro
	        if (heroImage != null) {
	            g.drawImage(heroImage, hero.getNewPointX(), hero.getNewPointY(), 30 , 30, null);
	        }
	        if (monstreImage != null) {
	            g.drawImage(monstreImage, pers.monstreX1, pers.monstreY1, 30 , 30, null);
	        }
	  
	        repaint();
	        update();
	    }
	  
	  public static void update() {
		  
		  pers.moveMonstre1();
		
		  //repaint();
	  }
}
