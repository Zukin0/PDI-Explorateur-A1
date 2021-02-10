package data;

public abstract class MapObjects {

	private String name;
	private Size size;
	private Position position;
	private boolean movable;
	
	public MapObjects(String name, Size size, Position position, boolean movable) {
		this.size = size;
		this.position = position;
		this.movable = movable;
		this.name = name;
	}
	
	//setters and getters 
	
	public String getName() {
		return name;
	}
	
	public Size getSize() {
		return size;
	}
	
	public Position getPosition() {
		return position;
	}
	
	public boolean getMovable() {
		return movable;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setSize (Size size) {
		this.size = size;
	}

	public void setPosition(Position position) {
		this.position = position;
	}
	
	public void setMovable(boolean movable) {
		this.movable = movable;
	}
	
}
