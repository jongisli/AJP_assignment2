package assignment2;

import java.util.List;
import java.util.Queue;

public class ASubscriber implements Subscriber, Runnable {

	private Queue<Integer> discomfortLevelList;
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
		while(discomfortLevelList.size() != 0)
		{
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		int discomfortWarning = discomfortLevelList.poll();
		notify();
		return discomfortWarning;
	}



}
