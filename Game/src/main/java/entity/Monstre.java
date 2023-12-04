package entity;

import main.GamePanel;
import tile.TileManager;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;




public class Monstre extends Entity {
	Random rand = new Random();
	private int speed = 1;
	int x;
	int y;

	public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
	public String direction;
	
	public int spriteCounter = 0;
	public int spriteNum = 1;
	
	public Monstre(GamePanel gp, TileManager tileManager) {
		super(gp);
	
		setDefaultValues(tileManager);
		getMonstreImage();
		
	}
	
	public void setDefaultValues(TileManager tileManager) {
		int mapWidth = tileManager.mapTileNum.length;
	    int mapHeight = tileManager.mapTileNum[0].length;
	    int randomX = (int) (Math.random() * mapWidth);
	    int randomY = (int) (Math.random() * mapHeight);

	    x = randomX * gp.tileSize;
	    y = randomY * gp.tileSize;
	            
	    direction = "down";
	}
	
	public void getMonstreImage() {
		try {
			
			up1  = ImageIO.read(getClass().getResourceAsStream("/monster/monstre_down_1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/monster/monstre_down_2.png"));
			down1  = ImageIO.read(getClass().getResourceAsStream("/monster/monstre_down_1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/monster/monstre_down_2.png"));
			left1  = ImageIO.read(getClass().getResourceAsStream("/monster/monstre_down_1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/monster/monstre_down_2.png"));
			right1  = ImageIO.read(getClass().getResourceAsStream("/monster/monstre_down_1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/monster/monstre_down_2.png"));
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void update() {

			int i = rand.nextInt(1000000);
			//we change the monstre position here
			if (i < 50000 && i > 950000) {
		        direction = "up";
		    } else if (i >= 100000 && i < 200000) {
		        direction = "down";
		    } else if (i >= 300000 && i < 350000) {
		        direction = "left";
		    } else if (i >= 650000 && i < 700000) {
		        direction = "right";
		    }
			
			
			// Ensure the monster stays within the bounds of the game panel
		    x = Math.max(0, Math.min(x, gp.getWidth() - gp.tileSize));
		    y = Math.max(0, Math.min(y, gp.getHeight() - 2*gp.tileSize));
				
				switch(direction) {
				
				case "up": y -= speed;// y = y - speed
					break;
				case "down": y += speed; break;
				case "left": x -= speed; break;
				case "right": x += speed; break;
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
