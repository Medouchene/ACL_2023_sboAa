package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;

public class GamePanel extends JPanel implements Runnable{

	//SCREEN SETTINGS
	final int originalTileSize = 16; // 16x16 tile
	final int scale = 3;
	
	public final int tileSize = originalTileSize * scale; //48x48 tile
	//How many tiles can be displayed on a single screen horizontally and vertically ?
	final int maxScreenCol = 16;
	final int maxScreenRow = 12 ;
	final int screenWidth = tileSize * maxScreenCol;//768 pixels
	final int screenHeight = tileSize * maxScreenRow;//576 pixels
	
	//FPS 
	int FPS = 60;
	
	//instantiate this KeyHandler
	KeyHandler keyH = new KeyHandler();
	//Time
	Thread gameThread; //Thread is something you can start and stop and once a thread started, it keeps your program running until you stop it
	//instantiate Player class
	Player player = new Player(this,keyH); //pass this GamePanel class and KeyHandler
	
	
	//Set player's default position
	int playerX = 100;
	int playerY = 100;
	int playerSpeed = 4; //ie 4 pixels
	
	
	//create a constructor of this GamePanel
	public GamePanel() {
		
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));//Set the size of this class (JPanel)
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);//if set true, all the drawing from this component will be done in an offscreen painting buffer
		this.addKeyListener(keyH);//add the keyH into the GamePanel --> this GamePanel can recognize key input
		this.setFocusable(true);//with this, this GamePanel can be 'focused' to receive key input
		
	}

	public void startGameThread() {
		
		//instantiate this game Thread
		gameThread = new Thread(this); // 'this' means this class, so GamePanel
		//so basically we are passing GamePanel class to this thread's constructor that's how you instantiate a thread
		gameThread.start(); //it's gonna automatically call this run method
	}
	
	
	@Override
	//when we start this gameThread it automatically calls this run method
//"SLEEP" METHOD	
//	public void run() {
//		
//		//'sleep' method
//		double drawInterval = 1000000000/FPS; // =1billionnanoseconds/FPS=16 666 666 or 1s/60 = 0.01666 seconds
//		double nextDrawTime = System.nanoTime() + drawInterval;
//		
//		//create a game loop, will be the core of the game
//		while(gameThread != null) {
//			
//			//check the current time
//			//long currentTime = System.nanoTime();//returns the current value of the running Java Virtual Machine's high-resolution time source, in nanoseconds, 1 000 000 000 nanoseconds = 1 second
//			//System.out.println("current Time : " +currentTime);
//			//long currentTime2 = System.currentTimeMillis();//in milliseconds 1 000 milliseconds = 1 second --> moins précis
//			
////test1			System.out.println("The game loop is running");
//			// 1 UPDATE : update information such as character positions
//			update();
//	
//			// 2 DRAW : draw the screen with the updated information
//			repaint();
//			
//			try {
//				double remainingTime = nextDrawTime - System.nanoTime();
//				remainingTime = remainingTime/1000000;//in milliseconds
//				
//				if(remainingTime < 0) {
//					remainingTime = 0;
//				}
//				
//				Thread.sleep((long)remainingTime);//pause the game loop until the sleep time is over
//				
//				nextDrawTime += drawInterval;
//				
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		
//	}
	
//"DELTA/ACCUMILATOR" METHOD	
	public void run() {
		
		double drawInterval = 1000000000/FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		//display FPS
		long timer = 0;
		int drawCount = 0;
		
		
		while(gameThread != null) {
			
			currentTime = System.nanoTime();
			
			delta += (currentTime - lastTime) / drawInterval; // =(how much time has passed)/drawInterval
			timer += (currentTime - lastTime);
			lastTime = currentTime;
			
			//when delta reach this drawInterval ie 1, why update, repaint and reset this delta
			if(delta >= 1) {
				update();
				repaint();
				delta--;
				drawCount++;//increase by 1
			}
			
			if(timer >= 1000000000) {
				System.out.println("FPS: "+drawCount);
				drawCount = 0;
				timer = 0;
			}
			
		}
	}
	
	public void update() {
		
		player.update();
		
	}
	public void paintComponent(Graphics g) {
		//built-in method in Java, standard method to draw things on Jpanel
		
		//Graphics is a class that has many functions to draw objects on the screen
		super.paintComponent(g);//a format you need to write when using paintComponent, super refers to JPanel
		
		//convert this Graphics to Graphics2D class
		//Graphics2D class extends the Graphics class to provide more sophisticated control over geometry, coordinate transformations, color management, and text layout
		Graphics2D g2 =(Graphics2D)g;
		
		player.draw(g2);
		
		g2.dispose();//dispose of the graphics context and release any system resources that is using (save some memories)
		
	}
}
