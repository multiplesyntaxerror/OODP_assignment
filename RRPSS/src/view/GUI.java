package view;

import java.util.InputMismatchException;
import java.util.Scanner;

public class GUI {

	private Scanner scanner;

	public GUI() {
		scanner = new Scanner(System.in);
	}

	public void displayTitle(String title){
		System.out.println("-----------------------------------------------------------------------");
		System.out.println(title);
		System.out.println("-----------------------------------------------------------------------");
	}

	public void displayStringsB(String text) {
		System.out.println(text);
	}

	public void displayStrings(String text) {
		System.out.print(text);
	}
	
	public void displayOptions(String[] options) {
		
		int count = 1;
		
		for (String choice : options) {
			System.out.println("(" + (count) + ") " + choice);
			count++;
		}
		System.out.println();
	}
	
	
	public int detectChoice(String[] options) {
		
		displayOptions(options);
		
		while (true) {
			try {
				System.out.print("Choice >>> ");
				int input = scanner.nextInt();
				scanner.nextLine();
				if (input >= 1 && input <= options.length) {
					return input;
				}
			} catch (InputMismatchException e) {
				scanner.nextLine();
			}
			System.out.println("ERROR: Please enter a valid option!");
		}
	}

	public Scanner getScanner() {
		return scanner;
	}
	
}
