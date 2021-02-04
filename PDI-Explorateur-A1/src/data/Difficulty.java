package data;

public class Difficulty {

	private int id;
	private int treasureNB;
	private int animalsNB;
	private int money;
	
	public Difficulty (int id, int treasureNB, int animalsNB, int money) {
		this.id = id;
		this.treasureNB = treasureNB;
		this.animalsNB= animalsNB;
		this.money = money;
	}
	
	public int getID() {
		return id;
	}
	public int getTreasureNB() {
		return treasureNB;
	}
	public int getAnimalsNB() {
		return animalsNB;
	}
	public int getMoney() {
		return money;
	}
	
	public void setID(int id) {
		this.id = id;
	}
	public void setTreasureNB(int treasureNB) {
		this.treasureNB = treasureNB;
	}
	public void setAnimalsNB(int animalsNB) {
		this.animalsNB = animalsNB;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	
}
