package telran.multithreading;

import telran.multithreading.producers.Reciever;
import telran.multithreading.producers.Sender;

public class SenderRecieversAppl {

	private static final int N_MESSAGES = 21;
	private static final int N_RECIEVERS = 10;

	public static void main(String[] args) throws InterruptedException {
		MessageBox messageBox1 = new MessageBox();
		MessageBox messageBox2 = new MessageBox();
		Sender sender = new Sender(messageBox1, messageBox2, N_MESSAGES);
		sender.start();
		for(int i = 0; i < N_RECIEVERS; i++) {
			new Reciever(i % 2 == 0 ? messageBox2 : messageBox1).start();
		}
		sender.join();

	}

}
