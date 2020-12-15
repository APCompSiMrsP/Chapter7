package co.programs;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Lockers {

	/**
	 * Back in 1976 LHS had 100 lockers and 100 students. On the f irst day of school, all lockers
	are closed. As the students enter, 
	the first student, S1, opens all the lockers. 
	Then the second student, S2, closes every other locker beginning with the second locker, L2. 
	Student S3 begins with locker L3 and changes every third locker (opens it if its closed and closes it if
	it’s open). 
	Student S4 begins with locker L4 and changes every fourth locker, 
	Student S5 starts with L5 and changes every fifth locker. 
	This continues through S100 changing L100.

	After all the students have passed through the building and changed the lockers which lockers
	are open? Write a program to find out and display all open locker numbers. Each locker
	number should be separated by exactly one space.
	Hint: Use an array of 100 Boolean elements, each of which indicates whether a locker is open
	(true) or closed (false). Initially all lockers are closed.
	 * @param args
	 */
	
	private static boolean[] lockers = new boolean[100];
	
	public static void main(String[] args) {
		//header
		MixinMessage.header(Lockers.class, "12/11/2020");
		
		//Message
		MixinMessage.print("Welcome to the Lancer Locker Room\n"
				+ "Each student has gone in and messed with the locker doors\n"
				+ "Here are the results of the chaos\n");
		
		//Runs for every student
		for(int i = 0; i < lockers.length; i++) {
			//Runs with two variables
			// q = space index of the running
			// p = How many time something will run
			for(int q = i, p = i; p < (100/(i+1)); q+=(i+1), p++) {
				//Sets the locker to close if its open and open if its closed
				lockers[q] = (lockers[q] ? false : true);
			}
			
		}
		//Prints output
		MixinMessage.print("Open Lockers "+ MixinMessage.toString(getNumbers().toArray()));		
	}
	
	//Message to get the numbers of the output
	private static List<Integer> getNumbers() {
		List<Integer> numbers = new ArrayList<>();
		for(int i = 0; i < lockers.length; i++) {
			if(lockers[i]) 
				numbers.add((i+1));
		}
		return numbers;
	}
	
}
