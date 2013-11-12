package assignment2;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ASubscriber implements Subscriber, Runnable {

	private BlockingQueue<Integer> discomfortLevelQueue;
	
	public ASubscriber(int maxBufferSize)
	{
		discomfortLevelQueue = new ArrayBlockingQueue<Integer>(maxBufferSize);
	}
	
	public void run() {
		while (true) {
			int discomfortLevel = this.getDiscomfortWarning();
			this.processDiscomfortWarning(discomfortLevel);
		}
	}

	@Override
	public void pushDiscomfortWarning(int discomfortlevel) {
		try {
			discomfortLevelQueue.put(discomfortlevel);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void processDiscomfortWarning(int discomfortLevel) {
		switch (discomfortLevel)
		{
			case 1: 
				System.out.println("(1) Getting warm!");
				break;
			case 2: 
				System.out.println("(2) Getting hot!");
				break;
			case 3: 
				System.out.println("(3) Getting very hot!");
				break;
			case 4: 
				System.out.println("(4) Getting inferno!");
				break;
			case 5: 
				System.out.println("(5) Getting death!");
				break;
			default: 
				System.out.println("Unknown discomfort level");
				break; 
		}
		
	}

	@Override
	public int getDiscomfortWarning() {
		int discomfortWarning = 0;
		try {
			discomfortWarning = discomfortLevelQueue.take();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return discomfortWarning;
	}



}
