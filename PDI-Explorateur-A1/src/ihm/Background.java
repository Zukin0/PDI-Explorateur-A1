package ihm;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Background {
	
	private BufferedImage image;
	private double x;
	private double y;
	private double dx;
	private double dy;
	
	private double moveScale;
	
	public Background(String s, double ms) {
		try {
			image = ImageIO.read(getClass().getResourceAsStream(s));
			moveScale = ms;
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Determine the position of the background on the screen
	 */
	public void setPosition(double x, double y) {
		//this.x = (x * moveScale) % Game.WIDTH;
		//this.dy = (y * moveScale) % Game.HEIGHT;
	}
	
	/*
	 * Determine the movement of the background 
	 */
	public void setVector(double dx, double dy) {
		this.dx = dx;
		this.dy = dy;
	}
	
	/*
	 * update the position of the background
	 */
	public void update() {
		x += dx;
		y += dy;
	}
	
	/*
	 * render the background
	 */
	public void draw(Graphics2D g) {
		g.drawImage(image, (int)x, (int)y, null);
		if(x < 0) {
		//	g.drawImage(image, (int)x + Game.WIDTH, (int)y, null);
		}
		if(x > 0) {
		//	g.drawImage(image, (int)x - Game.WIDTH, (int)y, null);
		}
	}
}