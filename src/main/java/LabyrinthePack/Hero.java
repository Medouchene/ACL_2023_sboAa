package LabyrinthePack;

import java.awt.event.KeyEvent;
import javax.swing.JComponent;

public class Hero extends Personnage{
	
	Plateau p = new Plateau();
	int minX=p.minX;
	int maxX=p.maxX;
	int minY=p.minY;
	int maxY=p.maxY;
	double r=Math.random();
	  double r1=Math.random();
	 private int newPointX = (int) (r*(p.largeur-20));
	  private int newPointY = (int) (r1*(p.longueur-20));
	public void Hero() {
		
	}
	public int getNewPointX() {
		return newPointX;
	}
	public void setNewPointX(int newPointX) {
		this.newPointX = newPointX;
	}
	public int getNewPointY() {
		return newPointY;
	}
	public void setNewPointY(int newPointY) {
		this.newPointY = newPointY;
	}
	public void movePoint(int keyCode) {
        int stepSize = 10; // c la vitesse de controle
        int pointX = getNewPointX();
        int pointY = getNewPointY();
        if (keyCode == KeyEvent.VK_LEFT) {
            pointX -= stepSize;
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            pointX += stepSize;
        } else if (keyCode == KeyEvent.VK_UP) {
            pointY -= stepSize;
        } else if (keyCode == KeyEvent.VK_DOWN) {
            pointY += stepSize;
        }
        if ((pointX<=p.maxX-30 && pointX>=p.minX) && (pointY<=p.maxY-20 && pointY>=p.minY+10) ) {
	        
        	if (!checkObs(pointX, pointY)) {
        		setNewPointX(pointX);
		        setNewPointY(pointY);
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
}