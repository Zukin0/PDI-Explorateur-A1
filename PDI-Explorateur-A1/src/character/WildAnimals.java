package character;
import java.util.ArrayList;
import data.Position;
import data.Size;

public class WildAnimals extends Character{

	public WildAnimals(String name, Size size, Position position, boolean movable, int lifePoint, int lifePointMax,
			int attackPoint, int attackPointMax, int speed, int aura) {
		super(name, size, position, movable, lifePoint, lifePointMax, attackPoint, attackPointMax, speed, aura);
	}
	
	public WildAnimals() {
		super("", new Size(0, 0), new Position(0,0), true, 0, 0, 0, 0, 0, 0);
	}

}
