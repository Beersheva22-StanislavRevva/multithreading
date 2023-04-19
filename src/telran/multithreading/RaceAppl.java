package telran.multithreading;


import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.TreeSet;

import telran.view.*;

public class RaceAppl {
	
	public static void main (String [] args) {
		
		InputOutput io = new StandardInputOutput();
		Menu menu = new Menu("Racing", Item.of("Start Racing", RaceAppl::startRace), Item.of("Exit", n -> n.writeLine("Good buy"), true));
		menu.perform(io);
	}
	
	static void startRace(InputOutput io) {
		int quantity = Integer.parseInt(io.readString("Enter number of racers (2-10)"));
		if (quantity < 2 || quantity > 10) {
			io.writeLine("Quantity of racers must be between 2-10");
			quantity = Integer.parseInt(io.readString("Enter number of racers (2-10)"));
		}
		int distance = Integer.parseInt(io.readString("Enter distance (100-1000)"));
		if (distance < 100 || distance > 1000) {
			io.writeLine("Distance of race must be between 100-1000");
			distance = Integer.parseInt(io.readString("Enter distance (100-1000)"));
		}
		List<Racer> racerList = new ArrayList<Racer>();
		long res;
		for (int i = 1; i <= quantity; i++) {
			Racer racer = new Racer(i, distance);
			racerList.add(racer);
			racer.start();
					}
		for (Racer r : racerList) {
			try {
				r.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
			
		io.writeLine(String.format("Winner is - racer #%d", Racer.isFinish.get()));
	}

}
