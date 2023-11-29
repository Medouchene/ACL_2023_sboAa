package main;

import object.OBJ_Box;
import object.OBJ_Door;
import object.OBJ_Key;
import object.OBJ_Money;

public class AssetSetter{
	GamePanel gp;
	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}
	public void setObject() {
		gp.obj[0] = new OBJ_Key();
		gp.obj[0].x= 8*gp.tileSize; // 9 est entre 1 et 16
		gp.obj[0].y = 10*gp.tileSize;
		
		gp.obj[1] = new OBJ_Door();
		gp.obj[1].x= gp.tileM.DoorX * gp.tileSize; 
		gp.obj[1].y = gp.tileM.DoorY * gp.tileSize;
		
		
		gp.obj[2] = new OBJ_Money();
		gp.obj[2].x= 5*gp.tileSize; 
		gp.obj[2].y = 6*gp.tileSize;
		gp.obj[3] = new OBJ_Money();
		gp.obj[3].x= 6*gp.tileSize; 
		gp.obj[3].y = 6*gp.tileSize;
		
		
		
	}
}
