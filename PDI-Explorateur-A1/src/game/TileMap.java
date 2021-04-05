package game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;
import javax.imageio.ImageIO;

/**
 * @brief Class that initializes the tilemap and tileset
 * @author Chabot Yohan, De Sousa Julia, Gastebois Emma and Hang Alexandre
 *
 */

public class TileMap {

	private int x;
	private int y;
	
	private int xmin;
	private int xmax;
	private int ymin;
	private int ymax;
	
	public static int[][] map;
	private int tileSize=32;
	private int nbRows ;
	private int nbCols ;
	private int width;
	private int height;
	
	/**Tileset*/
	private BufferedImage tileset;
	private static int numTilesAcross;
	private static Tile[][] tiles;
	
	public static int blockTile[] = {1,2,3,4,5,8,9,10,11,12};
	
	public TileMap() {}
	
	/**
	 * @brief Method that loads the tiles from the tileset file
	 * @param s : tileset's file path
	 */
	public void loadTiles (String s) {
		
		try {
			tileset = ImageIO.read(getClass().getResource(s));
			numTilesAcross = tileset.getWidth()/tileSize;
			tiles = new Tile[3][numTilesAcross];
			BufferedImage subImage;
			for(int col = 0; col < numTilesAcross; col++) {
				subImage = tileset.getSubimage(col * tileSize, 0, tileSize, tileSize);
				tiles[0][col] = new Tile(subImage, Tile.NORMAL);
				subImage = tileset.getSubimage(col * tileSize, tileSize, tileSize, tileSize);
				tiles[1][col] = new Tile(subImage, Tile.NORMAL);
				subImage = tileset.getSubimage(col * tileSize, tileSize, tileSize, tileSize);
				tiles[2][col] = new Tile(subImage, Tile.NORMAL);
			}
		} 
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	 
	/**
	 * @brief Method that loads the map corresponding to the text map
	 * @param s : map text's file path
	 */
	public void loadMap (String s) {
		
		try {
		 	InputStream in = getClass().getResourceAsStream(s);
			BufferedReader br = new BufferedReader (new InputStreamReader(in));
			
			nbRows = Integer.parseInt(br.readLine());
			nbCols = Integer.parseInt(br.readLine());
			map = new int[nbRows][nbCols];
			width = nbCols*tileSize;
			height = nbRows*tileSize;
			
			String delimiters = "\\s+";
			for (int row = 0; row<nbRows; row++) {
				String line = br.readLine();
				String[] tokens = line.split(delimiters);
				for (int col = 0; col<nbCols; col++) {
					map[row][col] = Integer.parseInt(tokens[col]);
				}
			}
			
			for (int row = 0; row<nbRows;row++) {
				for (int col = 0; col<nbCols; col++) {
					Random random = new Random();
					int nb;
					nb = random.nextInt(20);
					if (map[row][col] == 7) {
						if (nb == 0) {
							map[row][col] = 6;
						}
						else if (nb == 1) {
							map[row][col] = 13;
						}
						else {
							map[row][col] = 7;
						}
					}
				}
			}	
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int getNbCols() {
		return nbCols;
	}
	
	public int getNbRows() {
		return nbRows;
	}
	
	public int getTileSize() {
		return tileSize;
	}
	
	public void setTileSize(int i) {
		tileSize=i;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getPosition(int x, int y) {
		return map[x][y];
	}
	
	public int getType(int row, int col) {
		int rc = map[row][col];
		int r = rc/numTilesAcross;
		int c = rc%numTilesAcross;
		return tiles[r][c].getType();
	}
	
	public void setPosition(int x, int y) {
		this.x=x;
		this.y=y;
		fixBounds();
	}
	
	public void fixBounds() {
		if (x<xmin) {
			x = xmin;
		}
		if (x>xmax) {
			x = xmax;
		}
		if (y<ymin) {
			y = ymin;
		}
		if (y>ymax) {
			y = ymax;
		}
	}
	
	/**
	 * @brief Method that draws the map
	 * @param g
	 */
	public void draw(Graphics g) {
		for (int row = 0; row<nbRows; row++) {
			for (int col = 0; col<nbCols; col++) {
				if (col > nbCols){
					break;
				}
				if (row > nbRows){
					break;
				}
				if (map[row][col]==0) {
					continue;
				}

				int rc = map[row][col];
				int r = rc / numTilesAcross;
				int c = rc % numTilesAcross;
				
				g.drawImage(tiles[r][c].getImage(), x+col*tileSize+5, y+row*tileSize+5,tileSize, tileSize, null);
			}
		}
	}	
	
}
