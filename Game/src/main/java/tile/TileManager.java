package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager {

	GamePanel gp;
	public Tile[] tile; //array
	public int mapTileNum [][];	
	public int DoorX;
	public int DoorY;
	//constructeur
	public TileManager(GamePanel gp) {
		
		this.gp =gp;
		//size of this tile array
		tile = new Tile[10]; //we re gonnna create 10 kind of tiles 
		//instantiate mapTileNum
		mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];
		
		getTileImage();
		
		//niveaux
		if (gp.niveau.equals("0")) {
			loadMap("/maps/map01.txt");
		}else if (gp.niveau.equals("1")) {
			loadMap("/maps/map01.txt");
		}else if (gp.niveau.equals("2")) {
			loadMap("/maps/map02.txt");
		}else if (gp.niveau.equals("3")) {
			loadMap("/maps/map03.txt");
		}
	}
	
	public void getTileImage() {
		
		try {
			
			//instantiate tile array
			tile[0] = new Tile();
			tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/ground0.png"));
			
			tile[1] = new Tile();
			tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/block0.png"));
			tile[1].collision = true;
			
			tile[2] = new Tile();
			tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/block1.png"));
			tile[2].collision = true;

			tile[3] = new Tile();
			tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/earth.png"));

			tile[4] = new Tile();
			tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/fireball_down_1.png"));
			tile[4].collision = true;

			
			tile[5] = new Tile();
			tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/door.png"));
			
			
			
			
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	//mapTileNum[][] will store all the numbers in the map.txt
	public void loadMap(String filePath) {
		
		try {
			InputStream is = getClass().getResourceAsStream(filePath); //import the txt file
			BufferedReader br = new BufferedReader(new InputStreamReader (is)); //read the txt file
			
			int col = 0;
			int row = 0;
			
			
			while(col < gp.maxScreenCol && row < gp.maxScreenRow) {
				
				String line = br.readLine(); //read a line of text and put it into the string line
				
				while(col < gp.maxScreenCol) {
					
					String numbers[] = line.split(" ");
					
					//convertir String en int
					int num = Integer.parseInt(numbers[col]); // use col as an index for numbers[] array
					//We store the extracted number int the mapTileNum[][]
					mapTileNum[col][row] = num;
					if(num == 5) {
						DoorY = row;
						DoorX = col;
						
					}
					/*System.out.print("mapTilenum x est " + col);
					System.out.print("mapTilenum y est " + row);
					System.out.println("mapTilenum est " + mapTileNum[col][row]);*/
					//continue this until everything in the numbers[] is stored int the mapTileNum[][]
					col++;
				}
				if(col == gp.maxScreenCol) {
					
					col = 0;
					row++;
				}
			}
			br.close();
			
		}catch(Exception e) {
			
		}
		
	}
	
	public void draw(Graphics2D g2) {

		/*
		// draw a tile for testing
		g2.drawImage(tile[0].image, 0, 0, gp.tileSize, gp.tileSize, null);
		g2.drawImage(tile[1].image, 48, 0, gp.tileSize, gp.tileSize, null);//decale dun tile = 48
		g2.drawImage(tile[2].image, 96, 0, gp.tileSize, gp.tileSize, null);
		*/
		
		//automating the tile drawing proces
		int col = 0;
		int row = 0;
		int x = 0;
		int y = 0;
		
		while(col < gp.maxScreenCol && row < gp.maxScreenRow) {
			
			//extract a tile number which is stored i mapTileNum[0][0]
			int tileNum = mapTileNum[col][row];
			
			//dessine en haut a gauche 
			g2.drawImage(tile[tileNum].image, x, y, gp.tileSize, gp.tileSize, null);
			// going to draw the next tile
			col++; 
			x += gp.tileSize;
			
			if(col == gp.maxScreenCol) {
				col = 0;
				x = 0;
				row++;
				y += gp.tileSize;
			}
		}
		
		
	}
}