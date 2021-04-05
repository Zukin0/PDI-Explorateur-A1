package data;

/**
 * @brief Data Class of the Position of each Instance 
 * This class initializes the position (x and y), we will use it for every object
 * 
 * @author Chabot Yohan, De Sousa Julia, Gastebois Emma and Hang Alexandre
 */
public class Position {
	
	private int x;
	private int y;
	
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public String toString() {
		return "X : " + x + ", Y : " + y;
	}
}
