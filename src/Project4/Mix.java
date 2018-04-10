package Project4;

import java.io.*;
import java.util.Collections;
import java.util.Scanner;

public class Mix {
	//FIXME
	//FIXME
	//FIXME
	private static LinkedList message = new LinkedList();

	private static String userMessage;
	
	private String commands = "";


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

	private static void display() {
		//FIXME:Check if works
		System.out.print ("Message:\n");
		for (int i = 0; i < message.lengthList(); i++) { 
			System.out.format ("%3d", i);

		}
		System.out.format ("\n");
		for (int i = 0; i < message.lengthList(); i++)  
			System.out.format("%3c", message.toString().charAt(i));
		System.out.format ("\n");
	}

	private static void inputCommands() {
		Scanner scnr = new Scanner(System.in).useDelimiter("\\s");
		String command = "";
		Mix mix = new Mix();
		do {
			command = scnr.next();
			switch(command) {
			//quit and save to file
			case "Q":
				System.out.println("Creating a file named 'KEY'.");
				try {
					System.out.println(mix.commands);
					mix.createFile(mix.commands);
				} catch (IOException e) {
					System.out.println("File could not be created");
				}
				break;
			//insert string
			case "b":
				mix.insertString(scnr.next(), scnr.nextInt());
				break;
			//remove section
			case "r":
				mix.removeChars(Integer.valueOf(scnr.next()), Integer.valueOf(scnr.next()));
				break;
			//display help page
			case "H":
				mix.helpPage();
				break;
			//increment ascii values 
			case "a":
				String s = scnr.next();
				mix.incrementAscii(s.charAt(0));
				break;
			//insert ferguson
			case "f":
			//	mix.insertFerguson();
				break;
			//swap message
			case "s":
			//	mix.swapMessage();
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
		b s # 		means, insert String �s�' at position #
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
	public void insertString(String s, int pos) {
		boolean bool = message.insertAfter(pos, s);
		if (!bool)
			System.out.println("Command could not be carried out.");
		saveCommands("r " + pos + " " + pos);
		message.display();
	}

	/******************************************************************
	 *For r # *
	 *Removes all of the characters from # to *
	 *****************************************************************/
	public void removeChars(int startIndex, int endIndex) {
		boolean bool = message.removeSection(startIndex, endIndex);
		if(!bool)
			System.out.println("Command could not be carried out.");
		saveCommands("")
		message.display();
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

	private void saveCommands(String str) {
		commands = "\n"+ str + commands;
	}
	
	private void createFile(String instruction) throws IOException  {
	    BufferedWriter writer = new BufferedWriter(new FileWriter("Key"));
	    writer.write(instruction);
	    writer.close();
	}
	
	private void incrementAscii(char ascii) {
		//message.changeAscii(ascii);
	}
	
	/******************************************************************
	 * This method removes the string "Ferguson" that is spaced out
	 * every other character
	 *****************************************************************/
	private void removeFerguson() {
		//message.removeFerguson();
	}
	
	private void reverseList() {
		for(int i = 0; i <= message.lengthList(); i++) {
			
		}
			
			
	}
	
	private void paste(int index, int clipboard) {
		
	}
	
	private void copy(int startIndex, int endIndex, int clipboard) {
		
	}
	
	private void cut(int startIndex, int endIndex, int clipboard) {
		
	}
}
