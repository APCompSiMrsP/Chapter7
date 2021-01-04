package co.programs;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.IllegalFormatCodePointException;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class GaltonBox {

	/**
	 * The Galton Box is a device for statistics experiments named after English scientist Sir Francis
	Galton. It consists of an upright board with evenly spaced pegs in a triangular form as show
	on the right.
	Balls are dropped from the opening of the board. Every time a
	ball hits a peg, it had a 50% change of falling to the left or to the
	right. The piles of balls are accumulated in the slots at the
	bottom of the board.
	Write a program that simluates the bean machine. Your
	program should primpt the user to enter the number of ball and
	the number of slots. Simulate the falling of each ball by printing
	its path. For example, the path of a ball might be: RLLRLRR.
	Then at the end display the final build up of balls using “O” for
	each ball.
	Output might appear as follows:
	LRLRLRR
	RRLLLRR
	LLRLLRR
	RRLLLLL
	LRLRRLR
	Final Ball Distribution:
	O
	O	
	O O O
	*/
	
	/**
	 *       /
	 *      / /
	 *     / / / 
	 *    / / / /
	 *   / / / / /
	 *  / / / / / /  
	 */

	
	
	public static void main(String[] args) {
		//header
		MixinMessage.header(GaltonBox.class, "12/22/2020");
		
		//Number of times a program will run
		int slots = 0;
		
		//Gets the amount of rows a user would like.
		do {
			try {
				slots = Integer.parseInt(MixinMessage.getInput("Please enter the number of slots you'd like: "));
				if(slots <= 0)
					throw new IllNumberException();
				
			} catch (Exception e) {
				MixinMessage.print("You must enter a number that is greater than 0");
				slots = -1;
			}
		} while(slots <= 0);
		
		//number of balls
		int numberOfBalls = 0;
		
		do {
			try {
			
				numberOfBalls = Integer.parseInt(MixinMessage.getInput("Please enter the number of balls you'd like to drop in the Galton Box: "));
				if(numberOfBalls <= 0)
					throw new IllNumberException();
			} catch (Exception e) {
				MixinMessage.print("You must enter a number that is greater than 0");
				numberOfBalls = -1;
			}
		} while(numberOfBalls <= 0);
		
		int[] stack = new int[slots];
		for(int i = 0; i < stack.length; i++) {
			stack[i] = 0;
		}
		
		//Shoots a ball
		for(int i = 0; i < numberOfBalls; i++) {
			MixinMessage.printOne("Shooting ball #%s down the Galton Box", ""+(i+1));
			MixinMessage.printOne("Path: %s", shootBall(stack, (slots-1)));			
			MixinMessage.pause();
		}
		
		//final output
		MixinMessage.print(Arrays.toString(stack)+ "\nFinal Collection \n" +  finalCollection(stack));		
	}
	//7
	//8
	
	public static String finalCollection(int[] stack) {
		StringBuilder builder = new StringBuilder();
		
		//Creates a duplicate array of stack
		int[] stack2 = new int[stack.length];
		for(int i = 0; i < stack.length; i++) {
			stack2[i] = stack[i];
		}
		
		//Max number in the stack
		OptionalInt max = IntStream.of(stack).max();
				
		if(!max.isPresent()) 
			return "";
		//Line marking the bottom
		builder.append("----------------\n");
		//Adds all the Strings to the builder and prints out the O's
		for(int i = 0; i < max.getAsInt(); i++) {
			for(int q = 0; q < stack2.length; q++) {
				builder.append((stack2[q] > 0) ? "O " : "  ");
				if(stack2[q] > 0) {
					stack2[q] = stack2[q]-1;
				}
			}
			builder.append("\n");
		}
		
		//Returns the built string
		return builder.toString();
	}
	
	
	public static String shootBall(int[] stack, int rows) {
		
		StringBuilder stringBuilder = new StringBuilder();
		//int lastSpace = (stack.length/2);		
		int numOfRights= 0;
		//System.out.println("Starting Space " + (stack.length/2));
		
		for(int i = 0; i < rows; i++) {
			int num = ThreadLocalRandom.current().nextInt(0, 2);
			//lastSpace = num == 0 ? lastSpace-1 : lastSpace+1;
			stringBuilder.append(num == 0 ? 'L' : 'R');
			numOfRights += num == 0 ? 0 : 1;
		}
		
		/*
		if(lastSpace >= stack.length) {
			lastSpace = stack.length-1;
		}
		
		if(lastSpace < 0) {
			lastSpace = 0;
		}*/
		
		stack[numOfRights] = stack[numOfRights]+1;
		return stringBuilder.toString();
	}
	
}

/**
 1*       4
 2*      3 5
 3*     2 4 6
 4*    1 3 5 7
 5*   0 2 4 6 9
 6* -1 1 3 5 7 10
 7*-2 0 2 4 6 9 11
    
 *| | | | | | | | |
 */

class IllNumberException extends Exception {
	
	public IllNumberException() {
		super("Ill number");
	}
}
