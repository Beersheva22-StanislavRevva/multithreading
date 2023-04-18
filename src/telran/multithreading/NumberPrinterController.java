package telran.multithreading;

import java.util.ArrayList;
import java.util.List;

public class NumberPrinterController {

	public static void main(String[] args) {
		int numberOfPrinters = 4;
		int quantity = 8;
		int portion = 2;
		List <NumberPrinter> list = new ArrayList<NumberPrinter>();
		NumberPrinter numberPrinter = new NumberPrinter(quantity, portion, 1);
		list.add(numberPrinter);
		for (int i = 1; i < numberOfPrinters; i++) {
			numberPrinter = new NumberPrinter(quantity, portion, i+1);
			list.add(numberPrinter);
			list.get(i-1).setNextNumberPrinter(list.get(i));
		}
		list.get(list.size() - 1).setNextNumberPrinter(list.get(0));
		for (NumberPrinter n : list) {
			n.start();
		}
		
		list.get(0).interrupt();

	}

}
