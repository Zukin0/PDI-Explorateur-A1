package time;

/**
 * This method increments the value of time 
 * 
 * @author Chabot Yohan, De Sousa Julia, Gastebois Emma and Hang Alexandre
 */
public class Time {

	private int value;
	private int max;
	private int min;
	
	public Time(int value, int max, int min) {
		this.value = value;
		this.max = max;
		this.min = min;
	}
	
	public void increment() {
		if (value < max) {
			value ++;
		} else {
			setValue(getMin());
		}
	}
	
	public int getValue() {
		return value;
	}
	
	public int getMax() {
		return max;
	}
	
	public int getMin() {
		return min;
	}
	
	protected void setValue(int value) {
		this.value = value;
	}


}
