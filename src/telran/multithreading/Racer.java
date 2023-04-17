package telran.multithreading;

public class Racer extends Thread {
int racerNumber;
int distance;
int delayTime;
int minDelay = 2;
int maxDelay = 5;
static int isFinish = -1;



	public Racer(int racerNumber, int distance) {
		this.racerNumber = racerNumber;
		this.distance = distance;
	}
			
	public static int rnd(int min, int max)	{
		max -= min;
		return (int) (Math.random() * ++max) + min;
	}
	
	public void run () {
		try {
			for (int i = 0; i < distance; i++) {
				Thread.sleep((long) rnd(minDelay, maxDelay));
		}
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.printf("Racer # %d finished running \n", racerNumber);
		if (isFinish == -1) {
			isFinish = racerNumber;
		}
		 
	}

}
