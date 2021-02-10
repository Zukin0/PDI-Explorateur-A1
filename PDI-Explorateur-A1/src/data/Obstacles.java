package data;

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
