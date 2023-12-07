package entity;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;
import tile.TileManager;

public class Player extends Entity{

	//GamePanel gp;
	KeyHandler keyH;
	Random random = new Random();
	
	
	//Jauge de vie
	private int vie ;
	private final int MAX_VIE=500;
	
	private int speed = 4;
	
	public final int screenX;
	 public final int screenY;
	 int hasKey = 0;
	 int porteFeuille = 0;
	 private boolean attacking = false;
	 private int attackCounter = 0;
	
	//New constructor OG 
	public Player(GamePanel gp, KeyHandler keyH, TileManager tileManager) {
		

		super(gp);
		
		this.keyH = keyH;
		this.vie=MAX_VIE;
		
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
		
		setDefaultValues(tileManager);
		getPlayerImage();
	}
	
	//OG
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

	    direction = "down";
	}
	

	public void getPlayerImage() {
		try {
			//WALKING SPRITES
			up1  = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_2.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_2.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_2.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_2.png"));
			
			
			attack_up1  = ImageIO.read(getClass().getResourceAsStream("/attack/boy_attack_up_1.png"));
			attack_up2 = ImageIO.read(getClass().getResourceAsStream("/attack/boy_attack_up_2.png"));
			attack_down1 = ImageIO.read(getClass().getResourceAsStream("/attack/boy_attack_down_1.png"));
			attack_down2 = ImageIO.read(getClass().getResourceAsStream("/attack/boy_attack_down_2.png"));
			attack_left1 = ImageIO.read(getClass().getResourceAsStream("/attack/boy_attack_left_1.png"));
			attack_left2 = ImageIO.read(getClass().getResourceAsStream("/attack/boy_attack_left_2.png"));
			attack_right1 = ImageIO.read(getClass().getResourceAsStream("/attack/boy_attack_right_1.png"));
			attack_right2 = ImageIO.read(getClass().getResourceAsStream("/attack/boy_attack_right_2.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}	
	}
	
	
	
	
	
	
	public int getHasKey() {
		return hasKey;
	}

	public void setHasKey(int hasKey) {
		this.hasKey = hasKey;
	}

	public void update() {
		
        if (keyH.attackPressed == true) {
            attacking = true;
 
        }else {
        	attacking = false;
        }

		//the character is moving when we are not pressing any keys, to fix this:
		if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true )  {
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
			collisionPM  = false;
			gp.cChecker.checkTile(this);
			
			//CHECK EVENT
			gp.eHandler.checkEvent();
			
			
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
				//System.out.println("   x act est " + x);
				//System.out.println("   y act est " + y);
				//System.out.println(playerWin());
				
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

		
		System.out.println(" xP : " + x);
		System.out.println(" yP : " + y);
		
		System.out.println("BAaaaa3 : "+collisionPM);
		
		//Jauge de vie
		if (collisionPM == true) {
			setVie(-1);
			System.out.println("BAaaaa3333333333 : "+collisionPM);

		}
		if (vie<=0) {
			//GAME OVER
		}
		if (porteFeuille>0 && vie<MAX_VIE/2) {
			setVie(1);
			porteFeuille--;
		}
		
	}
	
	
	
	public int getVie() {
		return vie;
	}

	public void setVie(int a) {
		if (a==-1) {
			double reduction = 0.1 ;
			double redutctionAmount = (MAX_VIE*reduction)/100.0;
			vie-=redutctionAmount;
		}else if (a==1) {
			
			vie+=MAX_VIE/2;
		}
		//System.out.println("   key act est " + hasKey);
		//System.out.println("   Wallet act est " + porteFeuille);
		
	}
	public boolean playerWin() {
		int playerX = this.x;
		int playerY = this.y;
		int treasureX = gp.obj[1].x;
		int treasureY = gp.obj[1].y;

		// Définissez une distance minimale considérée comme "trop proche" (à ajuster selon vos besoins)
		double distanceLimite = 20.0;

		// Calculez la distance entre le joueur et le trésor (the key)
		double distance = Math.sqrt(Math.pow(playerX - treasureX, 2) + Math.pow(playerY - treasureY, 2));
		//System.out.println(distance);
		if(distance < distanceLimite && this.hasKey == 1) {
			this.hasKey--;
			return true;
		}else {
			return false;
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
				
				//passe au next level
				break;
			case "Money":
				gp.obj[i] = null;
				porteFeuille++;
			}
		}
	}
	
	public int getPlayerX() {
		return x;
	}
	public int getPlayerY() {
		return y;
	}
	
	public void draw(Graphics2D g2) {
		    BufferedImage image = null;

		    
		        switch (direction) {
		            case "up":
		                if (getSpriteNumber() == 1)
		                    image = up1;
		                else if (getSpriteNumber() == 2)
		                    image = up2;
		                break;
		            case "down":
		                if (getSpriteNumber() == 1)
		                    image = down1;
		                else if (getSpriteNumber() == 2)
		                    image = down2;
		                break;
		            case "left":
		                if (getSpriteNumber() == 1)
		                    image = left1;
		                else if (getSpriteNumber() == 2)
		                    image = left2;
		                break;
		            case "right":
		                if (getSpriteNumber() == 1)
		                    image = right1;
		                else if (getSpriteNumber() == 2)
		                    image = right2;
		                break;
		            default:
		            	break;}
		            
		            if (attacking) {
		    		        switch (direction) {
		    		            case "up":
		    		                if (getSpriteNumber() == 1)
		    		                    image = attack_up1;
		    		                else if (getSpriteNumber() == 2)
		    		                    image = attack_up2;
		    		                break;
		    		            case "down":
		    		                if (getSpriteNumber() == 1)
		    		                    image = attack_down1;
		    		                else if (getSpriteNumber() == 2)
		    		                    image = attack_down2;
		    		                break;
		    		            case "left":
		    		                if (getSpriteNumber() == 1)
		    		                    image = attack_left1;
		    		                else if (getSpriteNumber() == 2)
		    		                    image = attack_left2;
		    		                break;
		    		            case "right":
		    		                if (getSpriteNumber() == 1)
		    		                    image = attack_right1;
		    		                else if (getSpriteNumber() == 2)
		    		                    image = attack_right2;
		    		                break;
		    		            default:
		    		            	break;}
		    		        }
		    		     
		        

		
		
		g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);//Draw an image on the screen
		
		//Dessiner la jauge de vie
		 int healthBarWidth = (int) ((double) vie / MAX_VIE * 100); // Largeur de la barre de vie proportionnelle à la vie restante

		 g2.setColor(Color.BLACK);
	        g2.fillRoundRect(10, gp.screenHeight - 30, 100, 20, 10, 10);

	        GradientPaint gradient = new GradientPaint(0, 0, Color.RED, healthBarWidth, 0, Color.GREEN);
	        g2.setPaint(gradient);
	        g2.fillRoundRect(12, gp.screenHeight- 28, healthBarWidth - 4, 16, 8, 8);
		
	}
	
	
	 
	    public int getSpriteCounter() {
	        return spriteCounter;
	    }


	    public Entity setSpriteCounter(int spriteCounter) {
	        this.spriteCounter = spriteCounter;
	        return this;
	    }

	    public int getSpriteNumber() {
	        return spriteNum;
	    }
	    
	    public String getDirection() {
	        return direction;
	    }

		
}