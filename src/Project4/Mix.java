/**********************************************************************
 * Project 4: Mix
 * 
 * This class will encrypt a message that is entered through the
 * command line, by reading instructions from the user input and 
 * mutating a Linked List.
 *
 * @author Randy Nguyen, Sam Ventocilla, and Jay Brunsting.
 * @version April 17th, 2018.
 *********************************************************************/

package Project4;

import java.io.*;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Mix {

	/** Variable for the linked list. */
	private static LinkedList message = new LinkedList();
	
	/**Variable for the new linked list. */
	private static NewLinkedList newMessage;

	/** String to hold the users message from the command prompt. */
	private static String userMessage;

	/** String to take in user commands. */
	private String commands = "";


	/******************************************************************
	 * Main method to read in the users message, add it to the linked
	 * list and then print it and call method to read in user input for
	 * encryption commands.
	 * 
	 * @param args String array that holds message from the command 
	 * 				line.
	 *****************************************************************/
	public static void main(String [] args) {
		
		Mix.userMessage = "";

		//add command message to string userMessage
		for (int i = 0; i < args.length; i++) {
			Mix.userMessage += args[i] + " ";
		}

		//adds string userMessage to linked list
		for (int i = 0; i < userMessage.length() - 1; i++)
			message.append(userMessage.substring(i, i+1));

		newMessage = new NewLinkedList(message.getTop());
		message.display();
		inputCommands();
	}

	/******************************************************************
	 * Method to read in commands from user to encrypt message.
	 *****************************************************************/
	private static void inputCommands() {
		Scanner scnr = new Scanner(System.in).useDelimiter("\\s");
		String command, words, filename = "";
		int toNum, toNum2;
		Mix mix = new Mix();
		do {
			command = scnr.next();
			switch(command) {
			//quit and save to file
			case "Q":
				try {
					filename = scnr.next();
					mix.createFile(filename, mix.commands);
				} catch (IOException e) {
					System.out.println("File could not be created");
				}
				break;
				//insert string
			case "b":
				try {
					words = scnr.next();
					toNum = scnr.nextInt();
				}
				catch (NoSuchElementException e) {
					System.out.println("Incorrect command, please ente"
							+ "r 'b' followed by a string and the inde"
							+ "x at which you would like the string in"
							+ "erted.\nNote that the index must be "
							+ "within the length of the string");
					break;

				}
				mix.insertString(words, toNum);
				break;
				//remove section
			case "r":

				try {
					toNum = scnr.nextInt();
					toNum2 = scnr.nextInt();
				}
				catch (NoSuchElementException e) {
					System.out.println("Incorrect command. Please"
							+ " enter 'r' followed by separate numbers"
							+ " that are within 0 and the length of"
							+ "the string");
					break;
				}
				mix.removeChars(toNum, toNum2);
				break;
				//display help page
			case "H":
				mix.helpPage();
				break;
				//increment ascii values 
			case "a":
				String s;

				s = scnr.next();
				try {
					//checkfor false
					if(!(mix.changeAscii(s.charAt(0))))
						throw new StringIndexOutOfBoundsException();
				}
				catch(StringIndexOutOfBoundsException e) {
					System.out.println("Incorrect command. Please "
							+ "enter 'a' followed by either '+' "
							+ "or '-'");
					break;
				}
				break;
				//insert ferguson
			case "f":
				message.addFerguson();
				mix.saveCommands("f");
				break;
				//swap message
			case "s":
				message.SwapHalf();
				mix.saveCommands("s");
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
			default:
				System.out.println("Please enter a valid command. "
						+ "Press 'H' for Help");
				break;
			}

			scnr.nextLine();
			message.display();
		}while(!command.equals("Q"));
		
		System.out.print("Copy this into UnMix: " + filename + " '");
		message.displayMessage();
		System.out.print("' ");
	}

	/******************************************************************
	 *Method for the b command which inserts a string at the given 
	 *position.
	 *
	 *@param s String to be inserted.
	 *@param pos Position to insert string into linked list.
	 *****************************************************************/
	public void insertString(String s, int pos) {
		boolean bool = message.insertAfter(pos, s);
		if (!bool) {
			System.out.println("Command could not be carried out.");
			return;
		}
		saveCommands("r " + pos + " " + (pos + s.length() - 1));
	}

	/******************************************************************
	 *Method to handle the r command that removes a section of the 
	 *linked list starting and ending at a given index.
	 *
	 *@param startIndex Index to begin cutting out.
	 *@param endIndex Index to end cutting out.
	 *****************************************************************/
	public void removeChars(int startIndex, int endIndex) {
		String removed = message.removeSection(startIndex, endIndex);
		if(removed.length() == 0) {
			System.out.println("Command could not be carried out.");
			return;
		}
		saveCommands("b ///" + removed + "///" + startIndex + " ");
	}

	/******************************************************************
	 *Displays a page to help users learn how to enter their encryption
	 *commands.
	 *****************************************************************/
	public void helpPage() {
		System.out.println("\n-------Help Page-------");
		System.out.println("Q filename: Quits program and saves undo "
				+ "commands.");
		System.out.println("b s #: Inserts a string(s) at "
				+ "location(#).");
		System.out.println("r # *: Deletes from index(#) to "
				+ "index(*).");
		System.out.println("H: Displays help page.");
		System.out.println("a *: Increments or Decrements(*) the ascii"
				+ " value of each element in the Linked List.");
		System.out.println("f: Surprise!");
		System.out.println("s: Swaps the message around.");
		System.out.println("p # &: Pastes at location(#) from "
				+ "clipboard(&)");
		System.out.println("c # % &: Copies from index(#) to index(%) "
				+ "into clipboard(&).");
		System.out.println("x # % &: Cuts from index(#) to index(%) "
				+ "into clipboard(&).\n");
	}

	/******************************************************************
	 *Method to save the encryption commands to be later used in the 
	 *UnMix program.
	 *
	 *@param str String to hold the encryption commands.
	 *****************************************************************/
	private void saveCommands(String str) {
		commands = "\n"+ str + commands;
	}

	/******************************************************************
	 *Method that creates the file to hold encryption commands.
	 *
	 *@param instruction Encryption commands to be stored.
	 *****************************************************************/
	private void createFile(String filename, String instruction)
			throws IOException  {
		File file = new File(filename);
		BufferedWriter writer = new BufferedWriter(new FileWriter
			(file));
		writer.write(instruction);
		writer.close();
	}

	/******************************************************************
	 * Method to increment the ascii value of the character sent.
	 *
	 * @param ascii Character to be incremented
	 * @return boolean Returns true if action performed else false.
	 *****************************************************************/
	private boolean changeAscii(char ascii) {
		if(ascii == '+')
			saveCommands("a -");
		else
			saveCommands("a +");
		return message.changeAscii(ascii);
	}
	
	/******************************************************************
	 * Method to paste a series of nodes into the list.
	 *
	 * @param index The starting location of the paste.
	 * @param clipboardNum Where the series is from.
	 *****************************************************************/
	private void paste(int index, int clipboardNum) {
		
		// Pasted String
		String temp = newMessage.paste(index, clipboardNum);
		
		// If there is not string, then command incomplete.
		if(temp.length() == 0)
			System.out.println("Command could not be carried out.");
		else
			saveCommands("r " + index + " " + (index + temp.length()));
	}
	
	/******************************************************************
	 * Method to copy a series of nodes from the list.
	 *
	 * @param startIndex The starting location of the copy.
	 * @param endIndex The end location of the copy.
	 * @param clipboardNum Where the series is from.
	 *****************************************************************/
	private void copy(int startIndex, int endIndex, int clipboardNum) {
		newMessage.copy(startIndex, endIndex, clipboardNum);
	}
	
	/******************************************************************
	 * Method to cut a series of nodes from the list.
	 *
	 * @param startIndex The starting location of the copy.
	 * @param endIndex The end location of the copy.
	 * @param clipboardNum Where the series is from.
	 *****************************************************************/
	private void cut(int startIndex, int endIndex, int clipboardNum) {
		String temp = "";
		temp = newMessage.cut(startIndex, endIndex, clipboardNum);
		
		if(temp.length() == 0)
			System.out.println("Command could not be carried out.");
		else
			saveCommands("b ///" + temp + "///" + startIndex + " ");
	}
}
