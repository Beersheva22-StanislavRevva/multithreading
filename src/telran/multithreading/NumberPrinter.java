package telran.multithreading;

public class NumberPrinter extends Thread {

	private int quantity;
	private int portion;
	private int printNumber;
	private NumberPrinter nextNumberPrinter;
	
	public NumberPrinter(int quantity, int portion, int printNumber) {
		this.quantity = quantity;
		this.portion = portion;
		this.printNumber = printNumber;
	}
	
	public void setNextNumberPrinter (NumberPrinter nextNumberPrinter) {
		this.nextNumberPrinter = nextNumberPrinter;
	}
	
	@Override
	public void run() {
		for (int i = 0; i < quantity / portion; i++) {
			try {
				sleep(100);
			} catch (InterruptedException e) {
				System.out.println(String.format("%d", printNumber).repeat(portion));
				System.out.printf("\n");
				nextNumberPrinter.interrupt();
			}
		}
	}
	
	
}
