package character.builders.explorers;

import character.builders.explorers.core.ExBuilder;
import data.Position;
import data.Size;

//Here, we have class that create a Dora Explorer
public class DoraBuilder extends ExBuilder{
	
			public void upMapObject() {
				explorer.setSize(new Size(15, 20));
				explorer.setPosition(new Position(200,600));
				explorer.setMovable(true);
				explorer.setName("Dora");	
			}

			public void upCharacter() {
				explorer.setLifePoint(100);
				explorer.setLifePointMax(100);
				
				explorer.setAttackPoint(3);
				explorer.setAttackPointMax(10);
				
				explorer.setSpeed(6);
				explorer.setAura(100);
			}

			public void upExplorer() {
				explorer.setProbaCall(20);
				explorer.setProbaEscape(30);
				explorer.setProbaFight(50);
				
				explorer.setEquimentMax(2);
				
				explorer.setPrice(2000);
			}
			
}
