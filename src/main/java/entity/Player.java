package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity{

	GamePanel gp;
	KeyHandler keyH;
	
	//constructor
	public Player(GamePanel gp, KeyHandler keyH) {
		
		this.gp = gp;
		this.keyH = keyH;
		
		setDefaultValues();
		getPlayerImage();
	}
	
	public void setDefaultValues() {
		
		x = 100;
		y = 100;
		speed = 4;
		direction = "down"; //set a default direction
	}
	
	public void getPlayerImage() {
		try {
			
			up1 = ImageIO.read(getClass().getResourceAsStream("/HajPro/My2DGame/bin/player/boy_up_1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/HajPro/My2DGame/bin/player/boy_up_2.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/HajPro/My2DGame/bin/player/boy_down_1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/HajPro/My2DGame/bin/player/boy_down_2.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/HajPro/My2DGame/bin/player/boy_left_1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/HajPro/My2DGame/bin/player/boy_left_2.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/HajPro/My2DGame/bin/player/boy_right_1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/HajPro/My2DGame/bin/player/boy_right_2.png"));
			
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
				y -= speed;// y = y - speed
			}
			else if(keyH.downPressed == true) {
				direction = "down";
				y += speed;
			}
			else if(keyH.leftPressed == true) {
				direction = "left";
				x -= speed;
			}
			else if(keyH.rightPressed == true) {
				direction = "right";
				x += speed;
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
