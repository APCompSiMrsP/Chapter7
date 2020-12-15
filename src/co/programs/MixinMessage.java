package co.programs;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Currency;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class MixinMessage {
	public static final DecimalFormat DC_DECIMAL_FORMAT;
	public static final NumberFormat NUMBER_FORMAT;
	public static final Scanner SCANNER;
		
		static {
			DC_DECIMAL_FORMAT = new DecimalFormat("0.##");
			NUMBER_FORMAT = NumberFormat.getInstance();
			NUMBER_FORMAT.setCurrency(Currency.getInstance(Locale.US));
			SCANNER = new Scanner(System.in);
		}
		
		public static void printOne(String message, Object value) {
			print(message.replaceFirst("%s", value.toString()));
		}
		
		
		/*
		 * 
		 * Multiple goes from %a (first value in obj list) to %z
		 * A-Z
		 * 
		 */
		
		public static void printMultiple(String message, Object[] objsList) {
			/*
			Iterator iterator = objsList.iterator();
			for(int i = 0; i < message.length(); i++) {
				//if(!message.contains("%s"))
				//	break;
				if(!iterator.hasNext())
					break;
				message.replaceFirst("%s", ""+iterator.next().toString());
			}*/
			
			String test = message;
			
			for(int i = 0; i < objsList.length; i++) {
				String keyWord = getKeyWord(i);
				if(!message.contains(keyWord))
					break;
				test = test.replaceFirst(keyWord, ""+objsList[i].toString());
			}
			
			print(test);
		}
		
		private static String getKeyWord(int num) {
			switch(num) {
			case 0 : return "%a"; 
			case 1 : return "%b"; 
			case 2 : return "%c"; 
			case 3 : return "%d"; 
			case 4 : return "%e"; 
			case 5 : return "%f"; 
			case 6 : return "%g"; 
			case 7 : return "%h"; 
			case 8 : return "%i"; 
			case 9 : return "%j"; 
			case 10 : return "%k"; 
			case 11 : return "%l"; 
			case 12 : return "%m"; 
			case 13 : return "%n"; 
			case 14 : return "%o"; 
			case 15 : return "%p"; 
			case 16 : return "%q"; 
			case 17 : return "%r"; 
			case 18 : return "%s"; 
			case 19 : return "%t"; 
			case 20 : return "%u"; 
			case 21 : return "%v";
			case 22 : return "%w"; 
			case 23 : return "%x"; 
			case 24 : return "%y"; 
			case 25 : return "%z"; 
			}
			
			return "%finished";
		}
		
		public static String format(double number) {
			return DC_DECIMAL_FORMAT.format(number);
		}
		
		public static void header(Class<?> projectClass, String DATE) {
			System.out.println("Alex Elguezabal");
			System.out.println("AP Computer Science B Period");
			System.out.println(projectClass.getSimpleName());
			System.out.println(DATE);
			System.out.println();
		}
		
		public static void printNoLine(String text) {
			System.out.print(text);
		}
		
		public static void print(String text) {
			System.out.println(text);
		}
		
		public static void print(boolean text) {
			System.out.println(text);
		}
		
		public static void print(double text) {
			System.out.println(""+text);
		}
		
		public static void print(int text) {
			System.out.println(""+text);
		}
		
		public static void print(Object text) {
			System.out.println(text.toString());
		}
		
		public static void print() {
			System.out.println("");
		}
		
		public static void print(Collection<?> text) {
			text.forEach(n -> {
				print(n.toString());
			}); 
		}
		
		public static void print(String[] text) {
			for(int i = 0; i < text.length; i++) {
				print(text[i]);
			}	
		}
		
		public static void print(int[] text) {
			for(int i = 0; i < text.length; i++) {
				print(""+text[i]);
			}
		}
		
		public static String getInput(String string) {
			printNoLine(string);
			String inputString = SCANNER.nextLine();
			print("");
			return inputString;
		}
		
		public static void pause() {
		  printNoLine("Please enter a value to continue: ");
		  SCANNER.next();
		  print("");
		}
		
		//DOES NOT Use Scanner
		public static int numFromString(String input) {
	        return Integer.parseInt(input.replaceAll("[^-0-9]", ""));
	    }
		
		public static String getPercentFormatted(double number) {
			return ""+format((number*10));
		}
		
		public static String toString(Object[] numbers) {
			String output = "";
			for(Object n : numbers) {
				output += ""+n.toString()+" ";
			}
			return output;
		}
		
		public static String toString(boolean[] values) {
			String output = "";
			for(Object n : values) {
				output += ""+n+" ";
			}
			return output;
		}
		
		public static String getCommandAnswer(String message, String errorMessage, Object[] arrayOfAnswers) {
			String output = "";
			List<Object> objects = Arrays.asList(arrayOfAnswers);
			
			do {
				output = getInput(message);
				
				if(!objects.contains(output))
					print(errorMessage);
				
			} while(!objects.contains(output.toString()));
			return output.toString();
		}
		
		//public static String currencyFormat(double number) {
		//	return NumberFormat.
		//}
}
