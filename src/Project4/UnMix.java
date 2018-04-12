/**********************************************************************
 * Project 4: UnMix
 * 
 * This class will decrypt a message that is entered through the
 * command line, by reading instructions from a file and mutating a 
 * Linked List.
 *
 * @author Randy Nguyen, Sam Ventocilla, and Jay Brunsting.
 * @version April 17th, 2018.
 *********************************************************************/

package Project4;

import java.io.*;
import java.util.Scanner;

public class UnMix {

	/** Linked List that holds the message */
	private static LinkedList message = new LinkedList();

	/** Input message */
	private static String userMessage = "";

	/** File name */
	private static String fileName;
	
	/** Instruction from the file */
	private String command;

	/******************************************************************
	 * This main method will read from the command line, the file name,
	 * and the encrypted message. The message will be saved into a
	 * Linked List that holds one character per node. Main will then
	 * proceed to call the scanFile method which will decrypt the 
	 * message.
	 *  
	 * @param args FIXME (file too)
	 * 	The encrypted message that is entered in the command line.
	 *****************************************************************/
	public static void main(String [] args) {
		
		//FIXME filename will not default to key
		UnMix.fileName = "Key";

		// Add the message with spaces between each character.
		for (int i = 0; i < args.length; i++)
			UnMix.userMessage += args[i] + " ";
		
		System.out.print ("Encrypted Message:\n");
		
		// Print a number line that is evenly spaced.
		for (int i = 0; i < userMessage.length(); i++) { 
			System.out.format ("%3d", i);
			message.append(userMessage.substring(i, i+1));
		}
		
		// Print each character evenly spaced below each number.
		System.out.format ("\n");
		for (char c : userMessage.toCharArray()) 
			System.out.format("%3c",c);
		System.out.format ("\n");
		
		// Proceed to read the file.
		try {
			scanFile();
		} 
		
		// Quit if there was an error reading the file.
		catch (FileNotFoundException e) {
			System.out.println("Error: File Not Found\nSystem Close");
		}
	}

	/******************************************************************
	 * This method scans in the file and calls inputCommands to 
	 * interpret the commands.
	 * 
	 * @throws FileNotFoundException Error loading the file.
	 *****************************************************************/
	public static void scanFile() throws FileNotFoundException {

		//FIXME
		UnMix unMix = new UnMix();
		File file = new File(UnMix.fileName);
		Scanner scnr = new Scanner(file);
		BufferedReader reader = null;

		// Use this variable to count the number of loops.
		int scansComplete = 0;
		
		try {
			
			// Continue if there is still information to read.
			while (scnr.hasNextLine()) {
				
				// If this is the first scan, skip to the next line.
				if (scansComplete == 0)
					scnr.nextLine();
				
				
				else {
					
					// Save and print the top line of the file.
					unMix.command = scnr.nextLine();
					System.out.println(unMix.command);
					
					// Complete the instruction of the command.
					unMix.inputCommands();
				}
				
				// Increment the number of completed scans.
				scansComplete++;
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

		// Finished with this scanner.
		scnr.close();

	}

	/******************************************************************
	 * This method reads the commands and calls methods depending on 
	 * the input commands.
	 *****************************************************************/
	private void inputCommands() {

		//FIXME
		UnMix unMix = new UnMix();

		// This scanner reads the command, stopping at each space.
		Scanner scnr = new Scanner(this.command).useDelimiter("\\s");

		// Read the first character of the command.
		String command = scnr.next();

		// The first character determines the method called.
		switch (command) {

		// Insert a section.
		case "b":
			unMix.insertString(scnr.next(), scnr.nextInt());
			break;

		// Remove a section.
		case "r":
			unMix.removeChars(Integer.valueOf(scnr.next()),
					Integer.valueOf(scnr.next()));
			break;

		// Decrement ascii values.
		case "a":
			String s = scnr.next();
			unMix.decrementAscii(s.charAt(0));
			break;
			
		// Remove ferguson.
		case "f":
			unMix.removeFerguson();
			break;
			
		// Unswap the message.
		case "s":
			unMix.unswapHalves();
			break;
		
		// Paste from clipboard.
		case "p":
			unMix.paste(scnr.nextInt(), scnr.nextInt());
			break;
			
		// Copy to clipboard.
		case "c":
			unMix.copy(scnr.nextInt(), scnr.nextInt(), scnr.nextInt());
			break;
			
		// Cut to clipboard.
		case "x":
			unMix.cut(scnr.nextInt(), scnr.nextInt(), scnr.nextInt());
			break;
		}

		// Done using the scanner.
		scnr.close();
	}
	
	/******************************************************************
	 * The insertString method is for the "b s #" command. This method
	 * will call the Linked List to insert a string at the location.
	 * 
	 * @param s   The string from the command.
	 * @param pos The starting node location.
	 *****************************************************************/
	public void insertString(String s, int pos) {
		
		// Linked List will return if the change was completed.
		boolean bool = message.insertAfter(pos, s);
		
		// The change was not complete. Print an error message.
		if (!bool)
			System.out.println("Command could not be carried out.");
		
		// Print the current message.
		message.display();
	}

	/******************************************************************
	 * The removeChars method is for the "r # #" command. This method
	 * will call the Linked List to remove a section of nodes.
	 *
	 * @param startIndex The starting node location.
	 * @param endIndex   The ending node location.
	 *****************************************************************/
	public void removeChars(int startIndex, int endIndex) {
		
		// Linked List will return a string if there was a cut.
		String result = message.removeSection(startIndex, endIndex);
		
		// There is no string. Print an error message.
		if (result == null)
			System.out.println("Command could not be carried out.");
		
		// Print the current message.
		message.display();
	}

	/******************************************************************
	 * FIXME
	 *****************************************************************/
	private void decrementAscii(char ascii) {
		message.changeAscii(ascii);
	}
	
	/******************************************************************
	 * FIXME
	 *****************************************************************/
	private void removeFerguson() {
		message.removeFerguson();
	}
	
	/******************************************************************
	 * FIXME
	 *****************************************************************/
	private void unswapHalves() {
		message.undoSwapHalf();
	}
	
	/******************************************************************
	 * FIXME
	 *****************************************************************/
	private void paste(int index, int clipboard) {
		
	}
	
	/******************************************************************
	 * FIXME
	 *****************************************************************/
	private void copy(int startIndex, int endIndex, int clipboard) {
		
	}
	
	/******************************************************************
	 * FIXME
	 *****************************************************************/
	private void cut(int startIndex, int endIndex, int clipboard) {
		
	}
}
