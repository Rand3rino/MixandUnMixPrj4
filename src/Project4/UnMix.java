package Project4;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Scanner;

public class UnMix {

	/** FIXME */
	private static LinkedList message = new LinkedList();

	/** This is the input message */
	// FIXME Should this be static?
	private static String userMessage = "";

	private String commands = "";

	public static void main(String [] args) {

		System.out.println("Reading in File \n");

		try {
			scanFile();
		} catch (FileNotFoundException e) {
			System.out.println("Error: File Not Found");
		}
	}

	/******************************************************************
	 * Scans in the file and calls the correct methods
	 * 
	 * @param filePath
	 * @throws FileNotFoundException 
	 *****************************************************************/
	public static void scanFile() throws FileNotFoundException {

		UnMix unMix = new UnMix();
		File file = new File("Key");
		Scanner scnr = new Scanner(file);
		BufferedReader reader = null;

		int scanIteration = 1;
		try {
			while (scnr.hasNextLine()) {
				
				// The encrypted method is the first line of the file.
				if (scanIteration == 1) {
					
					unMix.userMessage = scnr.nextLine();
					
					System.out.println(unMix.userMessage + " is the message");
					
					// A separate string variable is necessary.
					String words = unMix.userMessage;
					
					UnMix.userMessage = "";
					for (int i = 0; i < words.length(); i++)
						UnMix.userMessage +=  words.charAt(i) + "";
	
					System.out.print ("Message:\n");
					for (int i = 0; i < userMessage.length(); i++) { 
						System.out.format ("%3d", i);
						message.append(userMessage.substring(i, i+1));
					}
					
					System.out.format ("\n");
					for (char c : userMessage.toCharArray()) 
						System.out.format("%3c",c);
					System.out.format ("\n");
				}
				
				// These are the command statements.
				else {
					unMix.commands = scnr.nextLine();
					System.out.println(unMix.commands);
					unMix.inputCommands();
				}
				
				// Iterate count to the next line.
				scanIteration++;
			}
		} 
		finally {
			try {
				if (reader != null)
					reader.close();
			} 
			catch (IOException e) {
				// FIXME Something happens here?
			}
		}
		
		// Done using the scanner.
		scnr.close();

	}

	private void inputCommands() {
		UnMix unMix = new UnMix();
		
		Scanner scnr = new Scanner(this.commands).useDelimiter("\\s*");
		
		String command = scnr.next();
				
		switch (command) {
		
		// quit and save to file
		case "Q":
			System.out.println("Shutting Down.");
			// System.exit();
			break;
			
		// insert string
		case "b":
			unMix.insertString(scnr.next(), scnr.nextInt());
			break;
			
		// remove section
		case "r":
			unMix.removeChars(Integer.valueOf(scnr.next()), 
				Integer.valueOf(scnr.next()));
			break;
			
		// display help page
		case "H":
			unMix.helpPage();
			break;
			
		// increment ascii values
		case "a":
			String s = scnr.next();
			unMix.incrementAscii(s.charAt(0));
			break;
			
		// insert ferguson
		case "f":
			// mix.insertFerguson();
			break;
			
		// swap message
		case "s":
			// mix.swapMessage();
			break;
		}
		
		// Done using the scanner.
		scnr.close();
	}
	
	private static void display() {
		System.out.print ("\nMessage:\n");
		for (int i = 0; i < userMessage.length(); i++) { 
			System.out.format ("%3d", i);
			message.append(userMessage.substring(i, i+1));
		}
		
		System.out.format ("\n");
		for (char c : userMessage.toCharArray()) 
			System.out.format("%3c",c);
		System.out.format ("\n");
	}
	
	/**********************************************************************
	 *Displays a page to help users learn how to enter their string 
	 **********************************************************************/
	public void helpPage() {
		System.out.println("\n-------Help Page-------");
		System.out.println("Q: Quits program");
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
		message.display();
	}

	/******************************************************************
	 *Displays a page to help users learn how to enter their string 
	 *****************************************************************/
	public void H() {
		System.out.println("Helpful message");

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
}
