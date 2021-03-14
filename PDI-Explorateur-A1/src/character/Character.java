package character;

import data.MapObjects;
import data.Position;
import data.Size;

public abstract class Character extends MapObjects{
	
	private int lifePoint;
	private int lifePointMax;
	private int attackPoint;
	private int attackPointMax;
	private int speed;
	private int aura;
	private int dir;
	private boolean isDead;

	public Character(String name, Size size, Position position, boolean movable, int lifePoint, int lifePointMax,
			int attackPoint, int attackPointMax, int speed, int aura) {
		super(name, size, position, movable);
		this.lifePoint = lifePoint;
		this.lifePointMax = lifePointMax;
		this.attackPoint = attackPoint;
		this.attackPointMax = attackPointMax;
		this.speed = speed;
		this.aura = aura;
	}
	
	public int getLifePoint() {
		return lifePoint;
	}
	public int getLifePointMax() {
		return lifePointMax;
	}
	public int getAttackPoint() {
		return attackPoint;
	}
	public int getAttackPointMax() {
		return attackPointMax;
	}
	public int getSpeed() {
		return speed;
	}
	public int getAura() {
		return aura;
	}
	public int getDir() {
		return dir;
	}
	public boolean isDead() {
		return isDead;
	}
	
	public void setLifePoint(int lifePoint) {
		this.lifePoint = lifePoint;
	}
	public void setLifePointMax(int lifePointMax) {
		this.lifePointMax = lifePointMax;
	}
	public void setAttackPoint(int attackPoint) {
		this.attackPoint = attackPoint;
	}
	public void setAttackPointMax(int attackPointMax) {
		this.attackPointMax = attackPointMax;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public void setAura(int aura) {
		this.aura = aura;
	}
	public void setDir(int dir) {
		this.dir = dir;
	}
	public void setDead(boolean isDead) {
		this.isDead = isDead;
	}

}
