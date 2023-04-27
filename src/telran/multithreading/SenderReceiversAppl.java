package telran.multithreading;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import telran.multithreading.consumers.Receiver;
import telran.multithreading.producers.Sender;

public class SenderReceiversAppl {

	private static final int N_MESSAGES = 20;
	private static final int N_RECEIVERS = 10;

	public static void main(String[] args) throws InterruptedException {
		BlockingQueue<String> messageBox = new LinkedBlockingQueue<>(1);
		Sender sender = new Sender(messageBox, N_MESSAGES);
		sender.start();
		for(int i = 0; i < N_RECEIVERS; i++) {
			new Receiver(messageBox).start();
		}
		Thread.sleep(100);
		sender.join();

	}

}