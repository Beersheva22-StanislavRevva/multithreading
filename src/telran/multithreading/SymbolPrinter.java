package telran.multithreading;

import java.time.LocalTime;
import java.util.Random;

public class SymbolPrinter extends Thread {

private int targetStringLength;
private long timeout;
String targetString = generateRandomString(targetStringLength);

public SymbolPrinter(int targetStringLength, long timeout) {
		setDaemon(true);
		this.targetStringLength = targetStringLength;
		this.targetString = generateRandomString(targetStringLength);
		this.timeout = timeout;
	}

@Override
public void run() {
	char [] targetCharArray = targetString.toCharArray();
	int i = 0;
	while (true) {
		System.out.println(targetCharArray[i]);
		try {
			sleep(timeout);
		} catch (InterruptedException e) {
			i++;
			if (i == targetCharArray.length) {
				i = 0;
			}
					
		}
	}
}



private String generateRandomString(int targetStringLength) {
	int leftLimit = 48; // number '0'
    int rightLimit = 122; // letter 'z'
    targetStringLength = 7;
    Random random = new Random();
    String randomString = random.ints(leftLimit, rightLimit + 1)
    	      .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
    	      .limit(targetStringLength)
    	      .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
    	      .toString(); // filter deletes special chars
	return randomString;
}


}
