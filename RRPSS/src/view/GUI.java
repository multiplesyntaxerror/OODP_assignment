package view;

import java.util.InputMismatchException;
import java.util.Scanner;

public class GUI {

	public void displayTitle(String title){
		System.out.println();
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
			System.out.println("(" + count + ") " + choice);
			count++;
		}
		System.out.println();
	}
	
	
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
