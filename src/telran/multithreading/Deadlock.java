package telran.multithreading;

public class Deadlock {

		public static void main3(String[] args) throws InterruptedException {
			X x1 = new X();
			X x2 = new X();
			x1.start();
			x2.start();
		}
}
