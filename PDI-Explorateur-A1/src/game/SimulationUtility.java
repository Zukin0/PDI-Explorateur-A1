package game;

import data.*;

/**
 * @brief Utility Class, used for sleep the thread of the game and for other purposes
 * 
 * @author Chabot Yohan, De Sousa Julia, Gastebois Emma and Hang Alexandre
 *
 */
public class SimulationUtility {
	/**
	 * @brief Simulates the unit time (for an iteration) defined for the simulation. 
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
