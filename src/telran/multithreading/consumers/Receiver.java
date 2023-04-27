package telran.multithreading.producers;

import telran.multithreading.MessageBox;

public class Reciever extends Thread {
	private MessageBox messageBox;
	public Reciever(MessageBox messageBox) {
		this.messageBox = messageBox;
		setDaemon(true);
	}
	@Override
	public void run() {
		while(true) {
			try {
				String message = messageBox.take();
				System.out.printf("thread: %s; recieved message: %s\n", getName(), message);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		} 
		
	}

	
}
