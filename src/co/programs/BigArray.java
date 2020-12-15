package co.programs;

import java.security.KeyStore.Entry;
import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;
import java.util.stream.Stream;

//Type of values
enum EntryType {
	MEAN("mean"),
	LARGEST("largest"),
	SMALLEST("smallest"),
	MODE("mode"),
	MEDIAN("median"),
	SPECIFIC("specific"),
	SECTION("section"),
	QUIT("quit");
	
	private String text;
	
	EntryType(String string) {
		text = string;
	}
	
	public String getText() {
		return text;
	}
}

public class BigArray {

	/**
	 * Competency Assessment: Write a program that creates a sorted array of 10000
	randomly generated numbers from 1 to 5000. Then have the program repeatedly be able to
	do the following using a menu:
	1. find the mean
	2. find the largest and smallest
	3. find the median
	4. find the mode
	5. find a specific number
	6. print a given section of the list
	Each of the options should be separate method and may need to call other methods.
	Remember a method can only return one item. Creating and sorting the list should be
	separate methods.
	 */
	
	//Default array of 10000 size
	private static int[] array = new int[10000];
	
	public static void main(String[] args) {
		//header
		MixinMessage.header(BigArray.class, "12/11/2020");
		for(int i = 0; i < array.length; i++) {
			//Sets the array values
			array[i] = ThreadLocalRandom.current().nextInt(1, 5001);
		}
		//MixinMessage.print(mode().stream().findFirst().get());	
		
		//Message
		MixinMessage.print("Welcome to the BigArray program\n"
				+ "In this program you will be given an array where you can select certian values from it\n"
				+ "The values you can select are | mean , largest , smallest , mode , specific , section , quit |\n"
				+ "Some values may require one or two inputs while others just print out a number.\n"
				+ "Have fun!\n");
		//Runs until quit
		boolean contint = true;
		while(contint) {
			contint = ask();
		}
	}
	
	/**
	 * 
	 * @return boolean continue the program or not
	 */
	
	public static boolean ask() {
		//Anwser of question
		String answer = "";
		EntryType entryType = null;
		//Asks the user for their input type until the input is valid
		do {
		MixinMessage.print("Options ( mean , largest , smallest , mode , specific , section , quit ) "); 
		answer = MixinMessage.getInput("Please enter what type of operation you'd like to do : ");
		for(EntryType nString : EntryType.values()) {
			if(nString.getText().equalsIgnoreCase(answer)) {
				entryType = nString;
				break;
			}			
		}
		
		if(entryType == null) {
			MixinMessage.print("Value not found, please try again ");
		}
		
		} while(entryType == null);
		
				
		//Switches the input type and displays what the user asked
		switch(entryType) {
		
		case MEAN: {
			MixinMessage.print("Mean: "+getMean());
			return true;
		}
		
		case LARGEST: {
			MixinMessage.print("Largest: "+largest());
			return true;
		}
		
		case SMALLEST: {
			MixinMessage.print("Smallest: "+smallest());
			return true;
		}
		
		case MODE: {
			MixinMessage.print("Mode: "+mode().stream().findFirst().orElse(-1));
			return true;
		}
		
		case MEDIAN: {
			MixinMessage.print("Median: "+median());
			return true;
		}
		
		case SPECIFIC: {
			int number = MixinMessage.numFromString(MixinMessage.getInput("Please enter the number you're looking for : "));
			MixinMessage.print("Specific Number Lookup: "+getSpaceOfNumber(number));
			return true;
		}
		
		case SECTION: {
			
			int num1 = MixinMessage.numFromString(MixinMessage.getInput("Please enter lower bounding number : "));
			int num2 = MixinMessage.numFromString(MixinMessage.getInput("Please enter the higher bounding number : "));

			MixinMessage.printMultiple("Numbers Inbetween %a and %b : "+""
					+ "\n%c", 
					new String[] { ""+num1, ""+num2, MixinMessage.toString(selectionOfArray(num1, num2).toArray()) });
			return true;
		}
		//Quits
		case QUIT: {
			return false;	
		}
		
		}
		return false;
	}
	
	
	public static IntStream getStream(int[] type) {
		//Returns an Intsream from an int array
		return IntStream.of(type);
	}
	
	public static int getMean() {
		//Get's average
		return (int) getStream(array).average().orElse(Double.NaN);
	}
	
	public static int largest() {
		//Get's largest
		return (int) getStream(array).sorted().max().getAsInt();
	}
	
	public static int smallest() {
		//Get's smallest
		return (int) getStream(array).sorted().min().getAsInt();
	}
	
	public static int median() {
		//Get's the middle number
		return array[(array.length/2)];
	}
	
	public static HashSet<Integer> mode() {
		//Gets the most active number(s)
		
		//Map of the most common recurring numbers
		Map<Integer,Integer> modeMap = new HashMap<>();
		getStream(array).forEach(n -> {
			if(modeMap.containsKey(n)) {
				modeMap.put(n, modeMap.get(n)+1);
			} else {
				modeMap.put(n, 1);
			}
		});
		
		HashSet<Integer> maxNumbers = new HashSet<>();
		AtomicInteger currentMax = new AtomicInteger(0);
		
		//Gets the most occuring number(s) and adds it to maxNumbers
		modeMap.forEach((n,v) -> {
			if(currentMax.get() < v) {
				maxNumbers.clear();
				maxNumbers.add(n);
				currentMax.set(v);
			} else if(currentMax.get() == v) {
				maxNumbers.add(n);
			}
		});
		
		return maxNumbers;
	}
	
	public static int getSpaceOfNumber(int number) {
		//Returns the space of a number
		for(int i = 0; i < array.length; i++) {
			if(array[i] == number) {
				return i;
			}
		}
		//Returns -1 if the array does not contain the number
		return -1;
	}
	
	public static List<Integer> selectionOfArray(int start, int end) {
		//Get's a certian portian of the array, aka y -> x 
		List<Integer> list = new ArrayList<Integer>();
		for(int i = 0; i < array.length; i++) {
			if(i >= start && i <= end) {
				list.add(array[i]);
			}
		}
		return list;
	}
	
}
