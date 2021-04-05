package time;

import data.Constant;

/**
 * @brief Implements Time thread of chronometer
 * 
 * @author Chabot Yohan, De Sousa Julia, Gastebois Emma and Hang Alexandre
 *
 */
public class RealTime implements Runnable{
	private Time hour = new Time(0, 1, 0);
	private Time minute = new Time(0, 15, 0);
	private Time second = new Time(0, 59, 0);
	
	private boolean running = true;
	
	public void init() {
		hour.setValue(0);
		minute.setValue(0);
		second.setValue(0);
	}
	
	/**
	 * @brief increment each second
	 */
	public void increment() {
		second.increment();
		if (second.getValue() == 0) {
			minute.increment();
			if (minute.getValue() == 0) {
				hour.increment();
			}
		}
	}
	
	public void run() {
		
		while(running) {
			try {
				Thread.sleep(Constant.SLEEP_CHRONOMETER);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			increment();
		}

	}
	
	public static String transform(int value) {
		String result = "";
		if (value < 10) {
			result = "0" + value;
		} else {
			result = String.valueOf(value);
		}
		return result;
	}
	
	public void setRunning(boolean running) {
		this.running = running;
	}
	
	public String toString() {
		//System.out.println("\n"+RealTime.transform(getHour().getValue()) + " : "+ RealTime.transform(getMinute().getValue()) + " : " + RealTime.transform(getSecond().getValue())); 
		return "l'heure" ;
	}
	
	public Time getHour() {
		return hour;
	}
	
	public Time getMinute() {
		return minute;
	}
	
	public Time getSecond() {
		return second;
	}
	
	public String getToString() {
		String heure = RealTime.transform(getHour().getValue()) + " : "+ RealTime.transform(getMinute().getValue()) + " : " + RealTime.transform(getSecond().getValue());
		return heure;
	}
}
