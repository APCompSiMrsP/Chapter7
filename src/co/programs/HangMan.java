package co.programs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.stream.events.Characters;

public class HangMan {
	
	/**
	 * 
	 * Write a program that plays a game of hangman. The program should randomly choose a
	word from an array of words that is initialized at the start of the program. Then the game
	should display a dash for each letter. Next the user is prompted to guess a letter. The
	program then searches the word for the letter. If the letter is there then the corresponding
	dash is replace by that letter. If the letter is not there then
	a piece of the hangman is drawn. Used letters should be
	displayed and the user should not be allowed to guess the
	same letter twice. The user wins if all the letters are
	guessed before the complete hangman is drawn. They
	lose if the hangman is completed before the user gets all
	the letters. The user may opt to guess the work early.
	Remember that there is no locate command so you have
	to plan your output carefully. Use many methods and at
	least one array.
	* 
	*/
	
	private final static double HANG_MAN_LENGTH = 8;
	
	public static void main(String[] args) {
		//header
		MixinMessage.header(HangMan.class, "17/15/2020");
		
		String[] words = null;
		do {
			words = MixinMessage.getInput("Please Enter a List of words, seperate using spaces: ").split(" ");
			if(words == null || words.length <= 0 || words[0].equalsIgnoreCase("")) 
				MixinMessage.print("Error getting words, please try again.");			
		} while(words == null || words.length <= 0 || words[0].equalsIgnoreCase(""));
		
		startGame(words[(int) (Math.random() * words.length)]);
	}
	
	//My answers
	//bye hi happy alex victor bella Mom Dad
	
	private static void startGame(String answer) {
		boolean isGameComplete = false;
		
		List<Character> unguessedLetters = new ArrayList<>();
		for(int i = 0, q = 65; i < 26; i++, q++) {
			unguessedLetters.add((char) q);
		}
		
		List<Character> guesses = new ArrayList<>();
		for(int i = 0; i < answer.length(); i++) {
			guesses.add('-');
		}
		
		//char[]
		
		// Runs the round 8 times
		// NEED-TO-ADD if the round is won? set i--;
		// 
		for(int i = 0; i < 8; i++) {
			MixinMessage.print("Letters Left: \n"
					+ Arrays.toString(unguessedLetters.toArray()));			
			MixinMessage.print("Current Found Letters: "
					+ Arrays.toString(guesses.toArray()));			
			
			String input = "";
			char value = (int) 0;
			do {
				input = MixinMessage.getInput("Please enter a letter:");
				 if(input == null || input.equalsIgnoreCase(""))
					 continue;
				 
				 value = Character.toUpperCase(input.charAt(0));
				 if(!Character.isLetter(value)) {
					 MixinMessage.print("Input is not a letter, please try again.");
					 continue;
				 }		
				 
				 if(!unguessedLetters.contains(value)) {
					 MixinMessage.print("Letter already used, please try again.");
					 continue;
				 }
				 
			} while(input == null || input.equalsIgnoreCase("") || !unguessedLetters.contains(value) || !Character.isLetter(value));				
			unguessedLetters.set(unguessedLetters.indexOf(value), '-');
			
			//Setting the actual string			
		}
	}
	
	private boolean isGameOver(String word) {
		for(char n : word.toCharArray()) {
			if(n == '-')
				return false;
		}
		return true;
	}
	
}
