package character;

import data.Position;
import data.Size;

public class WildAnimals extends Character{

	public WildAnimals(String name, Size size, Position position, boolean movable, int lifePoint, int lifePointMax,
			int attackPoint, int attackPointMax, int speed, int aura) {
		super(name, size, position, movable, lifePoint, lifePointMax, attackPoint, attackPointMax, speed, aura);
	}

}
