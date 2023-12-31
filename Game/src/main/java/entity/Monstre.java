package entity;

import main.GamePanel;
import tile.TileManager;


import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;




public class Monstre extends Entity {
	Random rand = new Random();
	private int speed = 1;
	int x;
	int y;
	//instantiate solidArea
	public Rectangle solidArea = new Rectangle();
	int solidAreaDefaultX;
	int solidAreaDefaultY;
	   
    
   

	public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
	public BufferedImage Monster_icon, died;
	public String direction;
	
	public int spriteCounter = 0;
	public int spriteNum = 1;
	
	public int monsterVie ;
	private final int MAX_MONSTER_VIE=500;
    
	private boolean collisionPM;
	
	public boolean getCollisionPM() {
		return collisionPM;
	}

	public void setCollisionPM(boolean collisionPM) {
		this.collisionPM = collisionPM;
	}

	public Monstre(GamePanel gp, TileManager tileManager) {
		super(gp);
		this.monsterVie=MAX_MONSTER_VIE;
		//instantiate solidArea
	      this.solidArea = new Rectangle();
	      this.solidArea.x = 8;
	      this.solidArea.y = 16;
	      this.solidAreaDefaultX = this.solidArea.x;
	      this.solidAreaDefaultY = this.solidArea.y;
	      
	      
		solidArea.width = 32;
		solidArea.height = 32;
		
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
			
			Monster_icon=ImageIO.read(getClass().getResourceAsStream("/monster/Monster_icon.png"));
			
			died = ImageIO.read(getClass().getResourceAsStream("/monster/died.png"));
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void update() {
/*		
				int targetX =getPlayerX();
			    int targetY = getPlayerY();

			    // Calculate the direction towards the target coordinates
			    int xDirection = Integer.compare(targetX, x);
			    int yDirection = Integer.compare(targetY, y);
			  //we change the monstre position here
				if ( xDirection == -1 && yDirection == 0) {
			        direction = "left";
			    } else if ( xDirection == 1 && yDirection == 0) {
			        direction = "right";
			    } else if ( xDirection == 0 && yDirection == -1) {
			        direction = "up";
			    } else if ( xDirection == 0 && yDirection == 1) {
			        direction = "down";
			    }
			    // Update the monster position based on the direction
			    x += xDirection * speed;
			    y += yDirection * speed;
*/
			
		int i = rand.nextInt(10000000);

		if ((i < 15000 && i> 9500) || (i >= 50000 && i<60000)) {
		    direction = "up";
		} else if ((i >= 25000 && i < 30000) || (i<90000 && i>950000)) {
		    direction = "down";
		} else if ((i >= 65000 && i < 75000) || (i<200000 && i>250000)) {
		    direction = "left";
		} else if((i>80000 && i<90000) || (i>40000 && i<50000)){
		    direction = "right";
		}

			

			
				
				switch(direction) {
				
				case "up": y -= speed;// y = y - speed
					break;
				case "down": y += speed; break;
				case "left": x -= speed; break;
				case "right": x += speed; break;
				}
				// Ensure the monster stays within the bounds of the game panel
			    x = Math.max(gp.tileSize, Math.min(x, gp.getWidth() - 2*gp.tileSize));
			    y = Math.max(gp.tileSize, Math.min(y, gp.getHeight() - 3*gp.tileSize));
			    
			    if(this.monsterVie<=0) {
			    	x=0;
			    	y=0;
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
		
		
	
	
	public int getMonstreX() {
		return x;
	}

	public int getMonstreY() {
		return y;
	}

	

	public void draw(Graphics2D g2) {
		
		
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

	
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getMonsterVie() {
		return monsterVie;
	}

	public void setMonsterVie(int damage) {
	    monsterVie -= damage;
	    if (monsterVie < 0) {
	        monsterVie = 0; // Pour éviter les valeurs négatives de la vie du monstre
	    }
	}
	
	public void setMonsterVieMax() {
		this.monsterVie = this.MAX_MONSTER_VIE;
	}
	
	
	
	
}
