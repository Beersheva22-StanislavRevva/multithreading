package telran.multithreading;

public class Racer extends Thread {
int racerNumber;
int distance;
int delayTime;
int minDelay = 2;
int maxDelay = 5;



	public Racer(int racerNumber, int distance) {
		this.racerNumber = racerNumber;
		this.distance = distance;
		this.delayTime = rnd(minDelay, maxDelay);	
	}
			
	public static int rnd(int min, int max)	{
		max -= min;
		return (int) (Math.random() * ++max) + min;
	}
	
	public void run () {
		try {
			for (int i = 0; i < distance; i++) {
				sleep(delayTime);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		 
	}

}
