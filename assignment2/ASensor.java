package assignment2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ASensor implements Sensor, Runnable {
	List<Monitor> monitors = new ArrayList<Monitor>(); //A sensor can push readings to one or many monitors
	
	private 		SensorReading sr = new SensorReading();
	
	private Random rand = new Random();
	public SensorReading generateSensorReading() {


	    float randomNumTem = rand.nextInt((80 - 10) + 1) + 10;
	    float randomNumHum = rand.nextInt((95 - 50) + 1) + 50;

		sr.setHumidity(randomNumHum);
		sr.setTemperature(randomNumTem);
		
		return sr;
	}

	public void run() {
		SensorReading reading = null;
		while(true) {
			reading = this.generateSensorReading();
			for (Monitor sm : monitors) {
				sm.pushReading(reading);
			}
			//Thread.sleep() ??
		}
		
	}

	@Override
	public int registerMonitor(List<Monitor> sm) {
		for(int i=0; i < sm.size(); i++){
			monitors.add(sm.get(i));
		}
			
		return 0;
	}

}
