package telran.multithreading;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Racer extends Thread {
int racerNumber;
int distance;
int delayTime;
int minDelay = 2;
int maxDelay = 5;
Instant start;
long resTime;
static AtomicInteger isFinish = new AtomicInteger(-1);
static List<Racer> list = new ArrayList<>(); 



	public Racer(int racerNumber, int distance) {
		this.racerNumber = racerNumber;
		this.distance = distance;
	}
			
	public static int rnd(int min, int max)	{
		max -= min;
		return (int) (Math.random() * ++max) + min;
	}
	
	public void setStart(Instant start) {
		this.start = start;
	}
	
	public void run () {
		try {
			for (int i = 0; i < distance; i++) {
				Thread.sleep((long) rnd(minDelay, maxDelay));
		}
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		this.resTime = resTime();
			System.out.printf("Racer # %d finished running, time %d \n", racerNumber, resTime);
			isFinish.compareAndExchange(-1, racerNumber);
		}

	synchronized private long resTime() {
		return ChronoUnit.MILLIS.between(start, Instant.now());
	}
		 
	}


