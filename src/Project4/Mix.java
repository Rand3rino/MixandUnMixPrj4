package Project4;

import java.util.Collections;
import java.util.Scanner;

public class Mix {
	//FIXME
	//FIXME
	//FIXME
	private static LinkedList message = new LinkedList();

	private static String userMessage;


	public static void main(String [] args) {
		Mix.userMessage = "";

		for (int i = 0; i < args.length; i++) {
			Mix.userMessage += args[i] + " ";
		}
		
		System.out.print ("Message:\n");
		for (int i = 0; i < userMessage.length(); i++) { 
			System.out.format ("%3d", i);
			message.append(userMessage.substring(i, i+1));
		}
		System.out.format ("\n");
		for (char c : userMessage.toCharArray()) 
			System.out.format("%3c",c);
		System.out.format ("\n");
		
		
		
		inputCommands();

	}

	private static void inputCommands() {
		Scanner scnr = new Scanner(System.in);
		String command = "";
		Mix mix = new Mix();
		do {
			command = scnr.next();
			switch(command) {
			//quit and save to file
			case "Q":
				saveCommands();
				break;
			//insert string
			case "b":
				mix.insertString(scnr.next(), scnr.nextInt());
				break;
			//remove section
			case "r":
				mix.removeChars(scnr.nextInt(), scnr.nextInt());
				break;
			//display help page
			case "H":
				mix.helpPage();
				break;
			//increment ascii values 
			case "a":
				mix.incrementAscii(scnr.next(), scnr.nextInt());
				break;
			//insert ferguson
			case "f":
				mix.insertFerguson();
				break;
			//swap message
			case "s":
				mix.swapMessage();
				break;
			//paste from clipboard
			case "p":
				mix.paste(scnr.nextInt(), scnr.nextInt());
				break;
			//copy to clipboard
			case "c":
				mix.copy(scnr.nextInt(), scnr.nextInt(), scnr.nextInt());
				break;
			//cut to clipboard
			case "x":
				mix.cut(scnr.nextInt(), scnr.nextInt(), scnr.nextInt());
				break;
			}

		}while(!command.equals("Q"));
	}

	/******************************************************************
		Q filename    means, quit! (Important, please print to the screen the final mixed up
		message when the program quits.) Also it means, to save off the set of 
			undo commands into text file named "filename".  
		b s # 		means, insert String “s”' at position #
				(For example, b abc 3 would insert abc starting at position 3)
		r # *		means, remove all the characters within the message, range # *
				(for example: r 3 5  would start at position 3 and remove 3,4,5)	
		H		Display a help page. 
  		x #		You create a new command that does something to the message.
		y # *		You create a new command that does something to the message.
		z # * s		You create a new command that does something to the message.
		(For your three new creations, do not make them trivial, please be innovative.)
	 *****************************************************************/

	/******************************************************************
	 *For b s #:
	 *Inserts the string "s" at position # 
	 *****************************************************************/
	public void insertString(String s, int position) {
		message.insertAfter(position, s);
		//FIXME:Display message if returns false
	}

	/******************************************************************
	 *For r # *
	 *Removes all of the characters from # to *
	 *****************************************************************/
	public void removeChars(int startIndex, int endIndex) {
		
	}

	/**********************************************************************
	 *Displays a page to help users learn how to enter their string 
	 **********************************************************************/
	public void helpPage() {
		System.out.println("\n-------Help Page-------");
		System.out.println("Q: Quits program and saves undo "
				+ "commands.");
		System.out.println("b s #: Inserts a string(s) at "
				+ "location(#).");
		System.out.println("r # *: Deletes from index(#) to "
				+ "index(*).");
		System.out.println("H: Displays help page.");
		System.out.println("a * #: Increments or Decrements(*) (#) "
				+ "number of times where (#) is less than 5.");
		System.out.println("f: Inserts ferguson between alternating "
				+ "characters.");
		System.out.println("s: Swaps the message around.");
		System.out.println("p # &: Pastes from clipboard(&) at "
				+ "location(#)");
		System.out.println("c # % &: Copies from index(#) to index(%) "
				+ "into clipboard(&).");
		System.out.println("x # % &: Cuts from index(#) to index(%) "
				+ "into clipboard(&).\n");
	}

	private static void saveCommands() {
		// TODO Auto-generated method stub
		
	}
	
	private void incrementAscii(String ascii, int num) {
		// TODO Auto-generated method stub
		
	}
	
	private void insertFerguson() {
		
	}
	
	private void swapMessage() {
		for(int i = 0; i < message.listLength/2; i++)
			Collections.swap(message, i, message.listLength - i);
	}
	
	private void paste(int index, int clipboard) {
		
	}
	
	private void copy(int startIndex, int endIndex, int clipboard) {
		
	}
	
	private void cut(int startIndex, int endIndex, int clipboard) {
		
	}
}
