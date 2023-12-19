package main;

import java.awt.Rectangle;

import entity.Entity;
import entity.Monstre;
import entity.Player;

public class CollisionChecker{
	
	GamePanel gp;
	
	//constructeur
	public CollisionChecker(GamePanel gp) {
		
		this.gp = gp;
	}

	
	public void checkTile(Entity entity) {
		
		//find out leftX rightX topY bottomY
		int entityLeftWorldX = entity.x + entity.solidArea.x; // ATTENTION entity.worldX si worldMap)
		int entityRightWorldX = entity.x + entity.solidArea.x + entity.solidArea.width;
		int entityTopWorldY = entity.y + entity.solidArea.y; // ATTENTION entity.worldY si worldMap)
		int entityBottomWorldY = entity.y + entity.solidArea.y + entity.solidArea.height;
		
		
		//find col et row via these coordinates
		int entityLeftCol = entityLeftWorldX/gp.tileSize;
		int entityRightCol = entityRightWorldX/gp.tileSize;
		int entityTopRow = entityTopWorldY/gp.tileSize;
		int entityBottomRow = entityBottomWorldY/gp.tileSize;
		
		
		int tileNum1, tileNum2;
		
		switch(entity.direction) {
		case "up":
			entityTopRow = (entityTopWorldY - entity.speed)/gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				entity.collisionOn = true;
			}
			break;
			
		case "down":
			entityBottomRow = (entityBottomWorldY + entity.speed)/gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				entity.collisionOn = true;
			}
			break;
			
		case "left":
			entityLeftCol = (entityLeftWorldX - entity.speed)/gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				entity.collisionOn = true;
			}
			break;
			
		case "right":
			entityRightCol = (entityRightWorldX + entity.speed)/gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				entity.collisionOn = true;
			}
			break;
			
		}
	}
	public void checkCollisionPM(Entity entity, Player player, Monstre monstre) {
	    // Get the bounding rectangles for the player, entity, and monster

	    Rectangle entityBounds = new Rectangle(entity.x + entity.solidArea.x, entity.y + entity.solidArea.y, entity.solidArea.width, entity.solidArea.height);
	    Rectangle monstreBounds = new Rectangle(monstre.getMonstreX() + monstre.solidArea.x, monstre.getMonstreY() + monstre.solidArea.y, monstre.solidArea.width, monstre.solidArea.height);

	    // Check if the entity's bounding rectangle intersects with the monster's bounding rectangle
	    if (entityBounds.intersects(monstreBounds)) {
	        // Collision detected with the monster (not the player)
	        monstre.setCollisionPM(true);
	    } else {
	        // No collision or collision with the player
	        monstre.setCollisionPM(false);
	    }

	    //RUN AWAY FROM MONSTRE
		if (gp.keyH.isAttackPressed()) {
			monstre.setCollisionPM(false);;
	    }else if (monstre.getCollisionPM() || entityBounds.intersects(monstreBounds)) {
        	
            entity.collisionOn = true;
        }

	}

	public int checkObject(Entity entity, boolean player) {
	      int index = 999;

	      for(int i = 0; i < this.gp.obj.length; ++i) {
	         if (this.gp.obj[i] != null) {
	            entity.solidArea.x += entity.x;
	            entity.solidArea.y += entity.y;
	            this.gp.obj[i].solidArea.x += this.gp.obj[i].x;
	            this.gp.obj[i].solidArea.y += this.gp.obj[i].y;
	            Rectangle var10000;
	            switch (entity.direction) {
	               case "up":
	                  var10000 = entity.solidArea;
	                  var10000.y -= entity.speed;
	                  if (entity.solidArea.intersects(this.gp.obj[i].solidArea)) {
	                     if (this.gp.obj[i].collision) {
	                        entity.collisionOn = true;
	                     }

	                     if (player) {
	                        index = i;
	                     }
	                  }
	                  break;
	               case "down":
	                  var10000 = entity.solidArea;
	                  var10000.y += entity.speed;
	                  if (entity.solidArea.intersects(this.gp.obj[i].solidArea)) {
	                     if (this.gp.obj[i].collision) {
	                        entity.collisionOn = true;
	                     }

	                     if (player) {
	                        index = i;
	                     }
	                  }
	                  break;
	               case "left":
	                  var10000 = entity.solidArea;
	                  var10000.x -= entity.speed;
	                  if (entity.solidArea.intersects(this.gp.obj[i].solidArea)) {
	                     if (this.gp.obj[i].collision) {
	                        entity.collisionOn = true;
	                     }

	                     if (player) {
	                        index = i;
	                     }
	                  }
	                  break;
	               case "right":
	                  var10000 = entity.solidArea;
	                  var10000.x += entity.speed;
	                  if (entity.solidArea.intersects(this.gp.obj[i].solidArea)) {
	                     if (this.gp.obj[i].collision) {
	                        entity.collisionOn = true;
	                     }

	                     if (player) {
	                        index = i;
	                     }
	                  }
	            }

	            entity.solidArea.x = entity.solidAreaDefaultX;
	            entity.solidArea.y = entity.solidAreaDefaultY;
	            this.gp.obj[i].solidArea.x = this.gp.obj[i].solidAreaDefaultX;
	            this.gp.obj[i].solidArea.y = this.gp.obj[i].solidAreaDefaultY;
	         }
	      }

	      return index;
	   }
}
