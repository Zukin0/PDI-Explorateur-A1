package data;

/**
 * @brief Data Class of an Obstacle
 * @author Chabot Yohan, De Sousa Julia, Gastebois Emma and Hang Alexandre
 *
 */
public class Obstacles extends MapObjects{

	private String type;
	
	public Obstacles(String name, Size size, Position position, boolean movable, String type) {
		super(name, size, position, movable);
		this.type = type;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}

}
