package main;

import java.awt.Rectangle;

import java.util.ArrayList;

public class EventHandler {
	
	GamePanel gp;
	Rectangle eventRect;
	int eventRectDefaultX,eventRectDefaultY;
	
	public EventHandler(GamePanel gp) {
		this.gp = gp;
		
		eventRect = new Rectangle();
		eventRect.x = 7;
		eventRect.y = 7;
		eventRect.height = 2;
		eventRect.width = 2;
		eventRectDefaultX = eventRect.x;
		eventRectDefaultY = eventRect.y;
			
	}
	
	public void checkEvent() {
		if(hit(7, 7, "up") == true) {
			damagePit(gp.dialogueState);
		}
	}
	
	public boolean hit(int eventCol, int eventRow, String reqDirection) {
		boolean hit = false;
		gp.player.solidArea.x += gp.player.x;
		gp.player.solidArea.y += gp.player.y;
		eventRect.x += eventCol*gp.tileSize;
		eventRect.y += eventRow*gp.tileSize;
		
		if(gp.player.solidArea.intersects(eventRect)) {
			if(gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
				hit = true;
			}
		}
		gp.player.solidArea.x = gp.player.solidAreaDefaultX;
		gp.player.solidArea.y = gp.player.solidAreaDefaultY;
		eventRect.x = eventRectDefaultX;
		eventRect.y = eventRectDefaultY;
		
		return hit;
	}
	
	public void damagePit(int gameState) {
		gp.gameState = gameState;
		gp.ui.currentDialogue = "F off";
		gp.player.setVie();
		
	}
	
	
}
