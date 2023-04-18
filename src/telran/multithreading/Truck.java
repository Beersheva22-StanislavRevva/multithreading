package telran.multithreading;

public class Truck extends Thread {
	private int load;
	private static long elevator1;
	private static long elevator2;
	private final static Object mutex = new Object();
	private int nRuns;

	@Override
	public void run() {
		for (int i = 0; i < nRuns; i++) {
			loadElevator1(load);
			loadElevator2(load);
		}
	}
		synchronized static void loadElevator1(int load) {
			elevator1+=load;
	}
		synchronized static void loadElevator2(int load) {
			synchronized (Truck.class) {
				elevator2 += load;
			}
		}
	public static long getElevator1() {
		return elevator1;
	}
	public static long getElevator2() {
		return elevator2;
	}

	public Truck(int load, int nRuns) {
		
		this.load = load;
		this.nRuns = nRuns;
	}
}