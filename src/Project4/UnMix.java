package Project4;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Scanner;

public class UnMix {

	/** FIXME */
	private LinkedList message = new LinkedList();

	/** This is the input message */
	private static String userMessage;



	public static void main(String [] args) {
		UnMix.userMessage = "";

		for (int i = 0; i < args.length; i++) {
			UnMix.userMessage += args[i] + " ";
		}

		System.out.println("Reading in File");
//		System.out.print ("Message:\n");
//		for (int i = 0; i < userMessage.length(); i++) 
//			System.out.format ("%3d", i);
//		System.out.format ("\n");
//		for (char c : userMessage.toCharArray()) 
//			System.out.format("%3c",c);
//		System.out.format ("\n");

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

		UnMix unmix = new UnMix();
		File file = new File("Key");
		Scanner scnr = new Scanner(file);
		BufferedReader reader = null;

		int scanIteration = 1;
		try {
			while (scnr.hasNextLine()) {
				
				// The encrypted method is the first line of the file.
				if (scanIteration == 1) {
					//FIXME Handle where the message goes.
					// usermessage = scnr.nextLine();
					scnr.nextLine();
				}
				
				// These are the command statements.
				else {
				
				// FIXME: We should try a method that parses out the command.
				// parseCommand(scnr.nextLine());
				System.out.println(scnr.nextLine());
				}
				
				// Iterate count to the next line.
				scanIteration++;
			}
		} 
		finally {
			try {
				if (reader != null) {
					reader.close();
				}
			} catch (IOException e) {
			}
		}
		
		// Done using the scanner.
		scnr.close();

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

	/******************************************************************
	 * This method increments the ascii value of the character stored
	 * in each node
	 *****************************************************************/
	public void incrAscii() {
	//	message.incrAscii();
	}

	/******************************************************************
	 * This method removes Ferguson from the string
	 *****************************************************************/
	public void removeFerguson() {
		message.removeFerguson();

		//		int length = message.lengthList();
		//		for(int i = 0; i < length; i++) {
		//			//alternate letters
		//			if(i % 2 == 0) {
		//				removeSection(i,i);
		//			}
		//		}

		
	}


	/******************************************************************
	 * This method undoes the reverse swap
	 *****************************************************************/
	public void undoReverseSwap() {
		//message.undoReverseSwap();
	}
}
