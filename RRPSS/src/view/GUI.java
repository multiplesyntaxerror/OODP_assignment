package view;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * The Class GUI.
 */
public class GUI {

	/**
	 * Display title.
	 *
	 * @param title the title
	 */
	public void displayTitle(String title){
		System.out.println();
		System.out.println("-----------------------------------------------------------------------");
		System.out.println(title);
		System.out.println("-----------------------------------------------------------------------");
	}

	/**
	 * Display strings with new line.
	 *
	 * @param text the text to display 
	 */
	public void displayStringsB(String text) {
		System.out.println(text);
	}

	/**
	 * Display strings without new line.
	 *
	 * @param text the text to display
	 */
	public void displayStrings(String text) {
		System.out.print(text);
	}
	
	/**
	 * Display a list of options.
	 *
	 * @param options the list of options
	 */
	public void displayOptions(String[] options) {
		
		int count = 1;
		
		for (String choice : options) {
			System.out.println("(" + count + ") " + choice);
			count++;
		}
		System.out.println();
	}
	
	
	/**
	 * Detect user's input from a list of options.
	 *
	 * @param options the list of options
	 * @return the choice indicated by the user
	 */
	public int detectChoice(String[] options) {
		
		Scanner sc = new Scanner(System.in);
		
		displayOptions(options);
		int input;
		while (true) { 
			try {
				System.out.print("Choice >>> ");
				input = sc.nextInt();
				if (input >= 1 && input <= options.length) {
					return input;
				}
			} catch (InputMismatchException e) {
				sc.nextLine();
			}
			System.out.println("ERROR: Please enter a valid option!");
		}
	}
}
