package telran.multithreading;

public class TimerTestAppl {

	public static void main(String[] args) throws InterruptedException {
		Timer timer = new Timer();
		timer.start();
		//running imitation
		Thread.sleep(5000);
		timer.interrupt();
		Thread.sleep(5000);
		Thread mainThread = Thread.currentThread();
		mainThread.interrupt();
		Thread.sleep(100000000000000l);
	}

}
