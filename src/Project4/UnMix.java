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
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.Objects;
import java.util.Scanner;

public class UnMix {

	/** Linked List that holds the message */
	private static LinkedList message = new LinkedList();

	/** Input message */
	private static String userMessage;

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
		UnMix.fileName = "Key";
		UnMix.userMessage = "";

		for (int i = 0; i < args.length; i++) {
			UnMix.userMessage += args[i] + " ";
		}
		
		System.out.print ("Encrypted Message:\n");
		for (int i = 0; i < userMessage.length(); i++) { 
			System.out.format ("%3d", i);
			message.append(userMessage.substring(i, i+1));
		}
		System.out.format ("\n");
		for (char c : userMessage.toCharArray()) 
			System.out.format("%3c",c);
		System.out.format ("\n");
		
		try {
			scanFile();
		} catch (FileNotFoundException e) {
			System.out.println("Error: File Not Found\nSystem Close");
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
		File file = new File(UnMix.fileName);
		Scanner scnr = new Scanner(file);
		BufferedReader reader = null;

		int scanIteration = 1;
		try {
			while (scnr.hasNextLine()) {
				if (scanIteration == 1)
					scnr.nextLine();
				else {
					unMix.command = scnr.nextLine();
					System.out.println(unMix.command);
					unMix.inputCommands();
				}
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

		Scanner scnr = new Scanner(this.command).useDelimiter("\\s");

		String command = scnr.next();

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
		String result = message.removeSection(startIndex, endIndex);
		if(result.length() == 0)
			System.out.println("Command could not be carried out.");
		message.display();
	}

	private void decrementAscii(char ascii) {
		message.changeAscii(ascii);
	}
	
	/******************************************************************
	 * This method removes the string "Ferguson" that is spaced out
	 * every other character
	 *****************************************************************/
	private void removeFerguson() {
		message.removeFerguson();
	}
	
	private void unswapHalves() {
		message.undoSwapHalf();
	}
	
	private void paste(int index, int clipboard) {
		
	}
	
	private void copy(int startIndex, int endIndex, int clipboard) {
		
	}
	
	private void cut(int startIndex, int endIndex, int clipboard) {
		
	}
}
