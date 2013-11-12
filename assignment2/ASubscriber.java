package assignment2;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ASubscriber implements Subscriber, Runnable {

	private List<Integer> discomfortLevelList;
	private int maxBufferSize;
	
	public ASubscriber(int maxBufferSize)
	{
		this.maxBufferSize = maxBufferSize;
	}
	
	public void run() {
		while (true) {
			int discomfortLevel = this.getDiscomfortWarning();
			this.processDiscomfortWarning(discomfortLevel);
		}
	}

	@Override
	public void pushDiscomfortWarning(int discomfortlevel) {
		while (!(discomfortLevelList.size() < maxBufferSize))
		{
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		discomfortLevelList.add(discomfortlevel);
		notify();
	}

	@Override
	public void processDiscomfortWarning(int discomfortLevel) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getDiscomfortWarning() {
		// TODO Auto-generated method stub
		return 0;
	}



}
