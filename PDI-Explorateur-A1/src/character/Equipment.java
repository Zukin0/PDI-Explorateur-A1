package character;

public class Equipment {

	private String name;
	private String power;
	private int price;
	
	public Equipment(String name, String power, int price) {
		this.name = name;
		this.power = power;
		this.price = price;
	}
	
	public Equipment() {
		name = "";
		power = "";
		price = 0;
	}
	
	public String getName() {
		return name;
	}
	public String getPower() {
		return power;
	}
	public int getPrice() {
		return price;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public void setPower(String power) {
		this.power = power;
	}
	public void setPrice(int price) {
		this.price = price;
	}
}
