package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity{

	GamePanel gp;
	KeyHandler keyH;
	Random random = new Random();
	
	
	public final int screenX;
	 public final int screenY;
	 int hasKey = 0;
	 int porteFeuille = 0;
	
	//constructor
	public Player(GamePanel gp, KeyHandler keyH) {
		
		this.gp = gp;
		this.keyH = keyH;
		//
		gp.getClass();
	      int var10001 = 768 / 2;
	      gp.getClass();
	      this.screenX = var10001 - 48 / 2;
	      gp.getClass();
	      var10001 = 576 / 2;
	      gp.getClass();
	      this.screenY = var10001 - 48 / 2;
		
		//instantiate solidArea
	      this.solidArea = new Rectangle();
	      this.solidArea.x = 8;
	      this.solidArea.y = 16;
	      this.solidAreaDefaultX = this.solidArea.x;
	      this.solidAreaDefaultY = this.solidArea.y;
		solidArea.width = 32;
		solidArea.height = 32;
		
		setDefaultValues();
		getPlayerImage();
	}
	
	public void setDefaultValues() {
	    // Répétez la génération de valeurs jusqu'à ce qu'il n'y ait pas de collision
		x = gp.setValues().get(0);
		y = gp.setValues().get(1);
		System.out.print("   x est " + x);
		System.out.print("   y est " + y);
	    speed = 4; //ie 4 pixels
	    direction = "down"; //set a default direction
	}
	

	public void getPlayerImage() {
		try {
			
			up1  = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_2.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_2.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_2.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_2.png"));
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void update() {
		
		//the character is moving when we are not pressing any keys, to fix this:
		if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true ) {
			//we change the player position here
			if(keyH.upPressed == true) {
				direction = "up";
			}
			else if(keyH.downPressed == true) {
				direction = "down";
			}
			else if(keyH.leftPressed == true) {
				direction = "left";
			}
			else if(keyH.rightPressed == true) {
				direction = "right";
			}
			
			//CHECK COLLISION
			collisionOn = false;
			gp.cChecker.checkTile(this);
			
			int objIndex = gp.cChecker.checkObject(this, true);
			pickUpObject(objIndex);
			
			//IF COLLISION IS FALSE, PLAYER CAN MOVE
			if(collisionOn == false) {
				
				switch(direction) {
				//ATTENTION CHNAGER CES x et y en worldX et worldY si mise en place de WorldMap
				case "up": y -= speed;// y = y - speed
					break;
				case "down": y += speed; break;
				case "left": x -= speed; break;
				case "right": x += speed; break;
				}
				System.out.printf("   x act est " + x/48);
				System.out.printf("   y act est " + y/48);
			}
			
			//create a simple sprite changer
			spriteCounter++;
			if(spriteCounter > 12) { //means the player image changes in every 12 frames
				if(spriteNum == 1) {
					spriteNum = 2;
				}else if(spriteNum == 2) {
					spriteNum = 1;
				}
				spriteCounter = 0;//reseat
			}
		}
	}
	
	public void pickUpObject(int i){
		if (i != 999) {
			String objectName = gp.obj[i].name;
			
			switch(objectName) {
			case "Key":
				hasKey++;
				gp.obj[i] = null;
				break;
			case "Door":
				hasKey--;
				//passe au next level
				break;
			case "Money":
				gp.obj[i] = null;
				porteFeuille++;
			}
		}
	}
	
	public void draw(Graphics2D g2) {
		
//		g2.setColor(Color.white);
//		g2.fillRect(x, y, gp.tileSize, gp.tileSize);// fillRect(x, y, width, height) draw a rectangle and fills it with the specified color
	
		BufferedImage image = null;
		
		switch(direction) {
		case "up":
			if(spriteNum == 1) {
				image = up1;
			}
			if(spriteNum == 2) {
				image = up2;
			}
			break;
		case "down":
			if(spriteNum == 1) {
				image = down1;
			}
			if(spriteNum == 2) {
				image = down2;
			}
			break;
		case "left":
			if(spriteNum == 1) {
				image = left1;
			}
			if(spriteNum == 2) {
				image = left2;
			}
			break;
		case "right":
			if(spriteNum == 1) {
				image = right1;
			}
			if(spriteNum == 2) {
				image = right2;
			}
			break;
		}
		g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);//Draw an image on the screen
		
	}
}
