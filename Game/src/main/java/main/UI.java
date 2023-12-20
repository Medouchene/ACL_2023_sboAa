package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

//this class handles all the screen on-screen UI
// so we can display text message, item icons etc
public class UI {
	
	GamePanel gp;
	Font arial_40;
	
	public String currentDialogue;

	public UI(GamePanel gp) {
		this.gp=gp;
		
		arial_40 = new Font("Arial", Font.PLAIN, 40);
		
	}
	
	public void drawGameOverScreen(Graphics2D g2){
		
		g2.setFont(arial_40);
		g2.setColor(Color.white);
		g2.drawString("Game Over", gp.screenWidth / 2 - 100, gp.screenHeight / 2 - 20);
		
	}
	public void drawGameWinScreen(Graphics2D g2){
		
		g2.setFont(arial_40);
		g2.setColor(Color.white);
		g2.drawString("Congratulation", gp.screenWidth / 2 - 100, gp.screenHeight / 2 - 20);
		
	}
	

}
