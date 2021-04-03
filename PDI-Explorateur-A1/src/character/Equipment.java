package character;

public class Equipment {

	private String name;
	private int power;
	private int price;
	
	public Equipment(String name, int power, int price) {
		this.name = name;
		this.power = power;
		this.price = price;
	}
	
	public Equipment() {
		name = "";
		power = 0;
		price = 0;
	}
	
	public String getName() {
		return name;
	}
	public int getPower() {
		return power;
	}
	public int getPrice() {
		return price;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public void setPower(int power) {
		this.power = power;
	}
	public void setPrice(int price) {
		this.price = price;
	}
}
