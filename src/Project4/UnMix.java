package Project4;

import java.io.*;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.Objects;
import java.util.Scanner;

public class UnMix {

	/** FIXME */
	private static LinkedList message = new LinkedList();

	/** This is the input message */
	// FIXME Should this be static?
	private static String userMessage = "";

	private String commands = "";

	public static void main(String [] args) {
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
				if (scanIteration == 1)
					scnr.nextLine();
				else {
					unMix.commands = scnr.nextLine();
					System.out.println(unMix.commands);
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
		
		Scanner scnr = new Scanner(this.commands).useDelimiter("\\s");
		
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
	
//	private static void display() {
//		System.out.print ("\nMessage:\n");
//		for (int i = 0; i < userMessage.length(); i++) { 
//			System.out.format ("%3d", i);
//			message.append(userMessage.substring(i, i+1));
//		}
//		
//		System.out.format ("\n");
//		for (char c : userMessage.toCharArray()) 
//			System.out.format("%3c",c);
//		System.out.format ("\n");
//	}
	
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
