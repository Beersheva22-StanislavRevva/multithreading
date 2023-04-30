package telran.multithreading.autorepair;

public class Truck {

	int repairTime;

	public Truck(int repairTime) {
		this.repairTime = 1 + (int)Math.random() * 479;
	}

}
