package data;

/**
 * @brief Class of CONSTANT variables for Time managment and the Speed of the program
 * @author Chabot Yohan, De Sousa Julia, Gastebois Emma and Hang Alexandre
 *
 */
public class Constant {
	
	public static final int SLEEP_CHRONOMETER = 1000;
	
	/**
	 * Speed of the whole program, the smaller the value, the faster the simulation will be
	 */
	public static final int SIMULATION_SPEED = 100;
	public static final int INTERVAL_DIRECTION_CHANGE = 3000;
	public static final int TIME_ESCAPING = 5000;
	public static final int TIME_WAITING = 10000;
	public static final int TIME_HELPING = 10000;
	
	/**
	 * Number of movements characters move before changing direction
	 * Ex : 1000/100 = 10 actions
	 */
	public static final int NUMBER_EXPLORE_ITERATIONS = INTERVAL_DIRECTION_CHANGE / SIMULATION_SPEED;
	public static final int NUMBER_ESCAPE_ITERATIONS = TIME_ESCAPING / SIMULATION_SPEED; 
	public static final int NUMBER_WAIT_ITERATIONS = TIME_WAITING / SIMULATION_SPEED;
	public static final int NUMBER_HELP_ITERATIONS = TIME_HELPING / SIMULATION_SPEED;
	
}
