package telran.multithreading.consumers;

import java.util.concurrent.BlockingQueue;

import telran.multithreading.MessageBox;

public class Receiver extends Thread {
 private BlockingQueue<String> messageBox;

public Receiver(BlockingQueue<String> mesageBox) {
	this.messageBox = mesageBox;
	setDaemon(true);
}
 @Override
 public void run() {
	 while(true) {
		 try {
			String message = messageBox.take();
			 System.out.printf("thread: %s; received message: %s\n", getName(), message);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	 }
 }
}