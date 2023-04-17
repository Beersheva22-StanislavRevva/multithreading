package telran.multithreading;

import java.util.Scanner;

public class SymbolPrinterController {

	public static void main(String[] args) throws InterruptedException {
		SymbolPrinter symbolPrinter = new SymbolPrinter(50, 2000);
		Scanner scanner = new Scanner(System.in);
		symbolPrinter.start();
		while (!(scanner.nextLine().equals("q"))) {
				symbolPrinter.interrupt();

	}

}
}
