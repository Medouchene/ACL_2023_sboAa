package main;
 
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;


import entity.Entity;
import entity.Monstre;
import entity.Player;
import object.SuperObject;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable{
	
	//niveau
	public String niveau="0";
	
	
	//SCREEN SETTINGS
	final int originalTileSize = 16; // 16x16 tile
	final int scale = 3;
	
	public final int tileSize = originalTileSize * scale; //48x48 tile
	//How many tiles can be displayed on a single screen horizontally and vertically ?
	public final int maxScreenCol = 16;
	public final int maxScreenRow = 12 ;
	public final int screenWidth = tileSize * maxScreenCol;//768 pixels
	public final int screenHeight = tileSize *( maxScreenRow+1);//576 pixels
	
	private boolean Win=false;
	
	//FPS 
	int FPS;
	
	//instantiate TileManager
	public TileManager tileM = new TileManager(this);
	
	//instantiate this KeyHandler
	public KeyHandler keyH = new KeyHandler();

	
	
	//instantiate CollisionChecker class
	public CollisionChecker cChecker = new CollisionChecker(this);
	public AssetSetter aSetter = new AssetSetter(this);
	public UI ui = new UI(this);
	
	public EventHandler eHandler = new EventHandler(this);
	Sound music = new Sound();
	Sound se = new Sound();
	//Time
	Thread gameThread; //Thread is something you can start and stop and once a thread started, it keeps your program running until you stop it
	
	
	//instantiate Player class 
	public Entity entity = new Entity(this);
	public Player player = new Player(this,keyH,tileM); //pass this GamePanel class and KeyHandler
	public Monstre monstre = new Monstre(this,tileM);
	public Monstre monstre1 = new Monstre(this,tileM);
	public Monstre monstre2 = new Monstre(this,tileM);

	public SuperObject obj[] = new SuperObject[10];
/*	On en a plus besoin car on la defini dans la class Player
	//Set player's default position
	int playerX = 100;
	int playerY = 100;
	int playerSpeed = 4; //ie 4 pixels
*/
	public int gameOverState = 0;
	
	public int dialogueState;	
	int gameState;
	
	//create a constructor of this GamePanel
	public GamePanel() {
		
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));//Set the size of this class (JPanel)
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);//if set true, all the drawing from this component will be done in an offscreen painting buffer
		this.addKeyListener(keyH);//add the keyH into the GamePanel --> this GamePanel can recognize key input
		this.setFocusable(true);//with this, this GamePanel can be 'focused' to receive key input
		 
	        
		
	}
	public void setupGame() {
		FPS = 60;
		aSetter.setObject();
		monstre.setMonsterVieMax();
		monstre1.setMonsterVieMax();
		monstre2.setMonsterVieMax();
		monstre.setDefaultValues(tileM);

	}

	public void startGameThread() {
		
		//instantiate this game Thread
		gameThread = new Thread(this); // 'this' means this class, so GamePanel
		//so basically we are passing GamePanel class to this thread's constructor that's how you instantiate a thread
		gameThread.start(); //it's gonna automatically call this run method
		//run();
		playMusic(0);
	}
	
	public void switchLevel() {
		int currentLevel = Integer.parseInt(niveau);
		if (currentLevel == 0) {
			currentLevel=1;
		}
		if (currentLevel < 3 && player.playerWin()) {
			currentLevel++;
			System.out.println("niveau: " + niveau);
	        niveau = Integer.toString(currentLevel);
	        

	        tileM.loadMap("/maps/map0" + niveau + ".txt");
	        
	        setupGame();
	      
	        
		}else if (player.playerWin()){
	        // Player has completed all levels
	        this.Win = true;
	        resetGame();
	    }
	}
	void resetGame() {
	 
	    niveau = "1"; // Reset level to 1
	
	    gameThread = null;
	}


//"DELTA/ACCUMILATOR" METHOD	
	public void run() {
	    // Initialisation des variables de temps
	    double drawInterval = 1000000000.0 / FPS;
	    double delta = 0;
	    long lastTime = System.nanoTime();
	    long currentTime;

	    // Variables pour afficher les FPS
	    long timer = 0;
	    int drawCount = 0;

	    while (gameThread != null) {
	        currentTime = System.nanoTime();

	        // Calcul du temps écoulé depuis la dernière mise à jour
	        delta += (currentTime - lastTime) / drawInterval;
	        timer += (currentTime - lastTime);
	        lastTime = currentTime;

	        // Mise à jour à une fréquence constante
	        if (delta >= 1) {
	            update();
	            repaint();
	            delta--;
	            drawCount++;
	        }

	        // Affichage des FPS chaque seconde
	        if (timer >= 1000000000) {
	            System.out.println("FPS: " );
	            drawCount = 0;
	            timer = 0;
	        }
	    }
	}

	

	
	public void update() {
		
		
		player.update();
		
		monstre.update();
		monstre1.update();
		monstre2.update();
		
		switchLevel();
		
		
	}

	public void paintComponent(Graphics g) {
		//built-in method in Java, standard method to draw things on Jpanel
		int monsterIconX = screenWidth - 160; // Same as player icon
		int monsterIconY = screenHeight - 40; 
		int monsterIconSize = 30;
		
		//Graphics is a class that has many functions to draw objects on the screen
		super.paintComponent(g);//a format you need to write when using paintComponent, super refers to JPanel
		
		//convert this Graphics to Graphics2D class
		//Graphics2D class extends the Graphics class to provide more sophisticated control over geometry, coordinate transformations, color management, and text layout
		Graphics2D g2 =(Graphics2D)g;
		tileM.draw(g2);
		if (gameOverState ==0) {
			//call draw method inside of this tile manager --- tjrs lecrire avant player.draw()
			
			
			for(int i = 0; i < this.obj.length; ++i) {
		         if (this.obj[i] != null) {
		            this.obj[i].draw(g2, this);
		         }
		      }
			
			player.draw(g2);
			if(monstre.getMonsterVie()>0) {
				monstre.draw(g2);
				g2.drawImage(monstre.Monster_icon, monsterIconX, monsterIconY, monsterIconSize, monsterIconSize, null);
			}
			if(monstre1.getMonsterVie()>0) {
				monstre1.draw(g2);
				g2.drawImage(monstre.Monster_icon, monsterIconX+40, monsterIconY, monsterIconSize, monsterIconSize, null);
			}
			if(monstre2.getMonsterVie()>0) {
				monstre2.draw(g2);
				g2.drawImage(monstre.Monster_icon, monsterIconX+80, monsterIconY, monsterIconSize, monsterIconSize, null);
			}
			if (this.Win == true) {
				ui.drawGameWinScreen(g2);
		
			}
		}else if (gameOverState ==1 ) {
			ui.drawGameOverScreen(g2);
			stopMusic();
			
		}
		
		
		g2.dispose();//dispose of the graphics context and release any system resources that is using (save some memories)
		
	}
    public int getTileSize() {
        return tileSize;
    }
    
    public void playMusic(int i ) {
    	
    	music.setFile(i);
    	music.play();
    	music.loop();
    }
    public void stopMusic() {
    	
    	music.stop();
    }
    public void playSE(int i) {
    	
    	se.setFile(i);
    	se.play();
    }
}