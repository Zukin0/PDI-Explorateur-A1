package game;

import java.awt.image.BufferedImage;

/**
 * @brief Method that initialize the tile's parameters
 * @author Chabot Yohan, De Sousa Julia, Gastebois Emma and Hang Alexandre
 *
 */

public class Tile {
	private BufferedImage image;
	private int type;
	

	public static final int NORMAL = 0;
	public static final int BLOCKED = 1;
	
	
	public Tile(BufferedImage image, int type) {
		this.image=image;
		this.type=type;  
	}
	
	public BufferedImage getImage() {
		return image;
	}
	
	public int getType() {
		return type;
	}
}
