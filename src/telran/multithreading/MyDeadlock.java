package telran.multithreading;

public class MyDeadlock {
 public static Object mutex1 = new Object();
 public static Object mutex2 = new Object();
 
 public static void gotoSleep() {
	 try {
		 Thread.sleep(1000);
	 } catch (Exception e) {
		e.printStackTrace();
	}
 }
 
 public static class Thread1 extends Thread {
	 @Override
	 public void run() {
		 synchronized (mutex1) {
			 gotoSleep();
			 synchronized (mutex2) {
				 System.out.println("Thread 1 got mutex2 and finished");
			}
		 }
	 }
 }
 
 public static class Thread2 extends Thread {
	 @Override
	 public void run() {
		 synchronized (mutex2) {
			 gotoSleep();
			 synchronized (mutex1) {
				 System.out.println("Thread 2 got mutex1 and finished");
			}
		 }
	 }
 }
 
 public static void main(String[] args) throws Exception {
	Thread thread1 = new Thread1();
	Thread thread2 = new Thread2();
	thread1.start();
	thread2.start();
	thread1.join();
	thread2.join();
	System.out.println("no deadlock");	 
 }
 
}
