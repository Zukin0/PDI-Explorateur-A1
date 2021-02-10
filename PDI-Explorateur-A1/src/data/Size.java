package data;

/**
 * This class initializes the size (width and height), we will use it for every object
 */
public class Size {
	
	private int width;
	private int height;
	
	public Size (int width, int height) {
		this.width = width;
		this.height = height;	
	}
	
	public String toString() {
		return " L : " + getWidth() + " H : " + getHeight() ;
	}
	
	public int getHeight () {
		return height;
	}
	
	public int getWidth() {
		return width;
	}

}
