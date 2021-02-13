package game;

import data.*;

public class SimulationUtility {
	/**
	 * Simulates the unit time (for an iteration) defined for the simulation. 
	 */
	public static void unitTime() {
		try {
			Thread.sleep(Constant.SIMULATION_SPEED);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static boolean isInstance(Object object, Class<?> type) {
	    return type.isInstance(object);
	}
}
