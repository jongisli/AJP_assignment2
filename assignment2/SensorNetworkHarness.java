package assignment2;

import java.util.ArrayList;
import java.util.List;

public class SensorNetworkHarness {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/*ASensor sensor1 = new ASensor();
		AMonitor monitor1 = new AMonitor(1024, 1);
		List<Monitor> monitorList1 = new ArrayList<Monitor>();
		monitorList1.add(monitor1);
		sensor1.registerMonitor(monitorList1);
		ASubscriber subscriber1 = new ASubscriber(10);
		
		monitor1.registerSubscriber(1, subscriber1);
		monitor1.registerSubscriber(2, subscriber1);
		monitor1.registerSubscriber(3, subscriber1);
		monitor1.registerSubscriber(4, subscriber1);
		monitor1.registerSubscriber(5, subscriber1);
		
		Thread thread1 = new Thread(sensor1);
		thread1.start();
		Thread thread2 = new Thread(monitor1);
		thread2.start();
		Thread thread3 = new Thread(subscriber1);
		thread3.start();*/
		
		ASensor sensor1 = new ASensor();
		ASensor sensor2 = new ASensor();
		ASensor sensor3 = new ASensor();
		
		AMonitor monitor1 = new AMonitor(1024, 1);
		AMonitor monitor2 = new AMonitor(1024, 2);
		
		List<Monitor> monitorList1 = new ArrayList<Monitor>();
		monitorList1.add(monitor1);
		
		List<Monitor> monitorList2 = new ArrayList<Monitor>();
		monitorList1.add(monitor1);
		monitorList1.add(monitor2);
		
		List<Monitor> monitorList3 = new ArrayList<Monitor>();
		monitorList1.add(monitor2);
	
		sensor1.registerMonitor(monitorList1);
		sensor2.registerMonitor(monitorList2);
		sensor3.registerMonitor(monitorList3);
		
		ASubscriber subscriber1 = new ASubscriber(10);
		ASubscriber subscriber2 = new ASubscriber(10);
	
		monitor1.registerSubscriber(1, subscriber1);
		monitor1.registerSubscriber(2, subscriber1);
		monitor1.registerSubscriber(3, subscriber1);
		monitor1.registerSubscriber(4, subscriber1);
		monitor1.registerSubscriber(5, subscriber1);
		
		monitor2.registerSubscriber(1, subscriber1);
		monitor2.registerSubscriber(2, subscriber1);
		monitor2.registerSubscriber(3, subscriber1);
		monitor2.registerSubscriber(4, subscriber1);
		monitor2.registerSubscriber(5, subscriber1);
		monitor2.registerSubscriber(1, subscriber2);
		monitor2.registerSubscriber(2, subscriber2);
		monitor2.registerSubscriber(3, subscriber2);
		monitor2.registerSubscriber(4, subscriber2);
		monitor2.registerSubscriber(5, subscriber2);
		
		Thread thread1 = new Thread(sensor1);
		thread1.start();
		Thread thread2 = new Thread(sensor2);
		thread2.start();
		Thread thread3 = new Thread(sensor3);
		thread3.start();
		
		Thread thread4 = new Thread(monitor1);
		thread4.start();
		Thread thread5 = new Thread(monitor2);
		thread5.start();
		
		Thread thread6 = new Thread(subscriber1);
		thread6.start();
		Thread thread7 = new Thread(subscriber2);
		thread7.start();
		
		
	}

}
