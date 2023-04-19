package telran.multithreading;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Racer extends Thread {
int racerNumber;
int distance;
int delayTime;
int minDelay = 2;
int maxDelay = 5;
static AtomicInteger isFinish = new AtomicInteger(-1);



	public Racer(int racerNumber, int distance) {
		this.racerNumber = racerNumber;
		this.distance = distance;
	}
			
	public static int rnd(int min, int max)	{
		max -= min;
		return (int) (Math.random() * ++max) + min;
	}
	
	public void run () {
		Instant start = Instant.now();
		try {
			for (int i = 0; i < distance; i++) {
				Thread.sleep((long) rnd(minDelay, maxDelay));
		}
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Object res = ChronoUnit.MILLIS.between(start, Instant.now());
		System.out.printf("Racer # %d finished running \n", racerNumber);
		isFinish.compareAndExchange(-1, racerNumber);
		}
		 
	}


