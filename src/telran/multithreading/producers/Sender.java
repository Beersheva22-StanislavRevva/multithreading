package telran.multithreading.producers;

import javax.management.NotCompliantMBeanException;

import telran.multithreading.MessageBox;

public class Sender extends Thread {
private MessageBox messageBox1;
private MessageBox messageBox2;
private int nMessages;

public Sender(MessageBox messageBox1, MessageBox messageBox2, int nMessages) {
	this.messageBox1 = messageBox1;
	this.messageBox2 = messageBox2;
	this.nMessages = nMessages;
}

@Override
public void run() {
	for(int i = 1; i <= nMessages; i++) {
		if (i % 2 == 0) {
			messageBox2.put("message " + i);
			try {
				sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} 
		} else {
			messageBox1.put("message " + i);
			try {
				sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
}
