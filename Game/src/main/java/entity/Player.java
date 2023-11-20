package entity;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;
import tile.TileManager;

public class Player extends Entity{

	GamePanel gp;
	KeyHandler keyH;
	
	//Jauge de vie
	private int vie ;
	private final int MAX_VIE=500;
	
	//constructor
	public Player(GamePanel gp, KeyHandler keyH, TileManager tileManager) {
		
		this.gp = gp;
		this.keyH = keyH;
		this.vie=MAX_VIE;
		
		//instantiate solidArea
		solidArea = new Rectangle();
		solidArea.x = 8;
		solidArea.y = 16;
		solidArea.width = 32;
		solidArea.height = 32;
		
		setDefaultValues(tileManager);
		getPlayerImage();
	}
	
	public void setDefaultValues(TileManager tileManager) {
	    int mapWidth = tileManager.mapTileNum.length;
	    int mapHeight = tileManager.mapTileNum[0].length;
	    boolean foundEmptySpace = false;

	    while (!foundEmptySpace) {
	        int randomX = (int) (Math.random() * mapWidth);
	        int randomY = (int) (Math.random() * mapHeight);

	        // Vérifier si la case aléatoire est un espace vide (sans collision)
	        if (!tileManager.tile[tileManager.mapTileNum[randomX][randomY]].collision) {
	            x = randomX * gp.tileSize;
	            y = randomY * gp.tileSize;
	            foundEmptySpace = true;
	        }
	    }

	    speed = 4;
	    direction = "down";
	}

	
	
	public void getPlayerImage() {
		try {
			
			up1  = ImageIO.read(getClass().getResourceAsStream("/player/player_up_1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/player/player_up_2.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/player/player_down_1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/player/player_down_2.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/player/player_left_1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/player/player_left_2.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/player/player_right_1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/player/player_right_2.png"));
			
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
			
			//CHECK TILE COLLISION
			collisionOn = false;
			gp.cChecker.checkTile(this);
			
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
		
	//Jauge de vie
		if (collisionOn) {
			double reduction = 0.1 ;
			double redutctionAmount = (MAX_VIE*reduction)/100.0;
			vie-=redutctionAmount;
		}
		if (vie<=0) {
			//GAME OVER
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
		
		//Dessiner la jauge de vie
		 int healthBarWidth = (int) ((double) vie / MAX_VIE * 100); // Largeur de la barre de vie proportionnelle à la vie restante

		 g2.setColor(Color.BLACK);
		 g2.fillRoundRect(10, 10, 100, 20, 10, 10); // Rectangle arrondi pour la barre de vie

		    // Dessiner le dégradé pour la jauge de vie
		 GradientPaint gradient = new GradientPaint(0, 0, Color.RED, healthBarWidth, 0, Color.GREEN); 
		 g2.setPaint(gradient);
		 g2.fillRoundRect(12, 12, healthBarWidth - 4, 16, 8, 8); //
		
	}
	

	
}
