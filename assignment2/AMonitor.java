package assignment2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

public class AMonitor implements Monitor, Runnable {
	
	private BlockingQueue<SensorReading> queue;
	private ConcurrentHashMap<Integer, List<Subscriber>> chm;
	private float avgTemp = 0;
	private float avgHum = 0;
	private int n = 0;
	
	//private Queue<Float> windowTemp = new <Float>(10);

	public AMonitor(int QueueSize, int ID) {
		queue = new ArrayBlockingQueue<SensorReading>(QueueSize);
		chm = new ConcurrentHashMap<Integer, List<Subscriber>>();
		chm.put(1, new ArrayList<Subscriber>());
		chm.put(2, new ArrayList<Subscriber>());
		chm.put(3, new ArrayList<Subscriber>());
		chm.put(4, new ArrayList<Subscriber>());
		chm.put(5, new ArrayList<Subscriber>());
	}

	@Override
	public int pushReading(SensorReading sensorInput) {
		queue.offer(sensorInput);
		return 0;
	}

	@Override
	public synchronized void processReading(SensorReading sensorInput) {
		int disLevel = 0;
		n++;
		
		avgTemp = (n-1)/(float)n * avgTemp + sensorInput.getTemperature()/(float)n;
		avgHum = (n-1)/(float)n * avgHum + sensorInput.getTemperature()/(float)n;
		
		if((avgTemp >=10 && avgTemp <= 19) || (avgHum >= 50 && avgHum <=59)) {
			disLevel = 1;
		}
		else if((avgTemp >=20 && avgTemp <= 29) || (avgHum >= 60 && avgHum <=69)) {
			disLevel = 2;
		}
		else if((avgTemp >=30 && avgTemp <= 39) || (avgHum >= 70 && avgHum <=79)) {
			disLevel = 3;
		}
		else if((avgTemp >=40 && avgTemp <= 49) || (avgHum >= 80 && avgHum <=89)) {
			disLevel = 4;
		}
		else if(avgTemp >=50 || avgHum >= 90) {
			disLevel = 5;
		}
		
		//Note that you need to push computed discomfort levels to the registered
		//subscribers using the registerDiscomfortLevel method in Subscriber interface
		
		if (disLevel != 0)
		{
			List<Subscriber> subscribers = chm.get(disLevel);
			for(int i = 0; i < subscribers.size(); i++) {
				subscribers.get(i).pushDiscomfortWarning(disLevel);
			}
		}
	}

	@Override
	public int registerSubscriber(int discomfortLevel, Subscriber subscriber) {
		chm.get(discomfortLevel).add(subscriber);
		return 0;
	}

	@Override
	public SensorReading getSensorReading() {
		SensorReading sr = null;
		try {
			//The head of the queue is that element that has been on the queue the longest time
			sr = queue.take();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return sr;
	}

	public void run() {
		SensorReading sensorInput = null;
		while(true) {
			sensorInput = getSensorReading();
			this.processReading(sensorInput);
		}
	}

}
