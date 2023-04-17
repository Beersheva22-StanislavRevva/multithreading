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
		Menu menu = new Menu("Racing", Item.of("Start Racing", RaceAppl::startRace), Item.of("Exit", n -> {}, true));
		menu.perform(io);
	}
	
	static void startRace(InputOutput io) {
		int quantity = Integer.parseInt(io.readString("Enter number of racers"));
		int distance = Integer.parseInt(io.readString("Enter distance"));
		TreeMap<Integer, Integer> treeMap = new TreeMap<>();
		long res;
		for (int i = 1; i <= quantity; i++) {
			Racer racer = new Racer(i, distance);
			Instant start = Instant.now();
			racer.start();
			res = ChronoUnit.MILLIS.between(start, Instant.now());
			Integer raceNum = i;
			Integer resNum = (int) res;
			treeMap.put(resNum, raceNum);
			}
							
		io.writeLine(" Wining number is - " + treeMap.get(treeMap.firstKey())
		+ "time is - " + treeMap.firstKey() + "ms");	
		
		
	}

}
