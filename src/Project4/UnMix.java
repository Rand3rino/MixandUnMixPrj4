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
import java.util.regex.Pattern;

public class UnMix {

	/** Linked List that holds the message. */
	private static LinkedList message = new LinkedList();

	/** Input message from command prompt. */
	private static String userMessage = "";

	/** File name where decryption codes are saved. */
	private static String fileName = "";
	
	/** Instruction from the file. */
	private String command;
	
	/** UnMix object to call methods from the main with. */
	private static UnMix unMix = new UnMix();

	/******************************************************************
	 * This main method will read from the command line, the file name,
	 * and the encrypted message. The message will be saved into a
	 * Linked List that holds one character per node. Main will then
	 * proceed to call the scanFile method which will decrypt the 
	 * message.
	 *  
	 * @param args This is the input from the command line that 
	 * 	includes the filename and the encrypted message in the format:
	 *  filename 'encrypted message'.
	 *****************************************************************/
	public static void main(String [] args) { 

		// The filename is the first string of args.
		UnMix.fileName += args[0];

		// Add the message with spaces between each character.
		for (int i = 1; i < args.length; i++)
			UnMix.userMessage += args[i] + " ";

		// Remove the ' at the beginning, ' and space at the end.
		UnMix.userMessage = UnMix.userMessage.substring
				(1, UnMix.userMessage.length() - 2) + " ";

		// Display the initial information.
		display();

		// Proceed to read the file.
		try {
			scanFile();
		}
		
		// Quit if there was an error reading the file.
		catch (FileNotFoundException e) {
			System.out.println("Error: File Not Found\nSystem Close");
		}
		
		// Decryption finished.
		finally {
			System.out.println("\nDone!");
		}
	}

	/******************************************************************
	 * This display method helps the main method by displaying all 
	 * necessary information when the program begins.
	 *****************************************************************/
	private static void display() {
		
		// Print out the filename.
		System.out.println("This is the filename: " + UnMix.fileName);
		
		// Print out the encrypted message.
		System.out.print("Encrypted Message:\n");

		// Print the correct amount of numbers evenly spaced.
		for (int num = 0; num < userMessage.length() - 1; num++) {
			System.out.format("%3d", num);
			message.append(userMessage.substring(num, num + 1));
		}

		// Print each character evenly spaced below each number.
		System.out.format("\n");
		for (char c : userMessage.toCharArray())
			System.out.format("%3c", c);
		System.out.format("\n");
	}

	/******************************************************************
	 * This method scans in the file and calls inputCommands to 
	 * interpret the commands.
	 * 
	 * @throws FileNotFoundException Error loading the file.
	 *****************************************************************/
	public static void scanFile() throws FileNotFoundException {

		// Instantiate a new scanner that will read from the file.
		Scanner scnr = new Scanner(new File(UnMix.fileName));
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
					System.out.println("\nCommand: " + unMix.command);
					
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
				System.out.println("File not read.");
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

		// This scanner reads the command, stopping at each space.
		Scanner scnr = new Scanner(this.command).useDelimiter("\\s");

		// Read the first character of the command.
		String command = scnr.next();

		// The first character determines the method called.
		switch (command) {

		// Insert a section.
		case "b":
			scnr.useDelimiter("///");
			scnr.next();
			String word = scnr.next();
			scnr.useDelimiter("\\s");
			unMix.insertString(word, Integer.parseInt(scnr.next().substring(3)));
			break;

		// Remove a section.
		case "r":
			unMix.removeChars(Integer.valueOf(scnr.next()),
					Integer.valueOf(scnr.next()));
			break;

		// Decrement ascii values.
		case "a":
			String s = scnr.next();
			unMix.undoAscii(s.charAt(0));
			break;
			
		// Remove the name Ferguson characters at every other node.
		case "f":
			message.removeFerguson();
			break;
			
		// Swap the first half of the list with the second.
		case "s":
			message.UnSwapHalf();
			break;
		}

		// Print the current message.
		message.display();
		
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
		// Insert not complete. Print an error message.
		if (!message.insertAfter(pos, s))
			System.out.println("Command Incomplete.");
	}

	/******************************************************************
	 * The removeChars method is for the "r # #" command. This method
	 * will call the Linked List to remove a section of nodes.
	 *
	 * @param startIndex The starting node location.
	 * @param endIndex   The ending node location.
	 *****************************************************************/
	public void removeChars(int startIndex, int endIndex) {
	
		// There is no string. Print an error message.
		if (message.removeSection(startIndex, endIndex) == null)
			System.out.println("Command Incomplete");
	}

	/******************************************************************
	 * Increments or decrements character to undo previous command.
	 * 
	 * @param ascii Character to increment or decrement.
	 *****************************************************************/
	private void undoAscii(char ascii) {
		message.changeAscii(ascii);
	}
}
