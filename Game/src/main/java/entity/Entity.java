package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.GamePanel;

//Entity class : this stores variables that will be used in player, monster and NPS classes

public class Entity {
	GamePanel gp;
	public Entity(GamePanel gp) {
		// TODO Auto-generated constructor stub
		this.gp = gp;
	}
	public int x, y;
	public int speed;
	
	//BufferedImage describes an image with an accessible buffer of image data; we use this to store our images files
	public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
	public String direction;
	
	public int spriteCounter = 0;
	public int spriteNum = 1;
	
	//collison detection
	public Rectangle solidArea;
	   public int solidAreaDefaultX;
	   public int solidAreaDefaultY;
	public boolean collisionOn = false;
	
}
