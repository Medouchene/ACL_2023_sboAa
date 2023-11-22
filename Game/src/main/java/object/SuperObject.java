package object;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

import main.GamePanel;

public class SuperObject {
	public BufferedImage image;
	public String name ;
	public boolean collision = false;
	public int x, y;
	 public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
	   public int solidAreaDefaultX = 0;
	   public int solidAreaDefaultY = 0;
	
	public SuperObject() {
	   }

	   public void draw(Graphics2D g2, GamePanel gp) {
	      int screenX = this.x;
	      int screenY = this.y;
	      
	      g2.drawImage(image, screenX, screenY, 48, 48, (ImageObserver)null);
	       

	   }
	
}
