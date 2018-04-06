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

		System.out.print ("Message:\n");
		for (int i = 0; i < userMessage.length(); i++) 
			System.out.format ("%3d", i);
		System.out.format ("\n");
		for (char c : userMessage.toCharArray()) 
			System.out.format("%3c",c);
		System.out.format ("\n");

		scanFile();
	}

	/******************************************************************
	 * Scans in the file and calls the correct methods
	 * 
	 * @param filePath
	 *****************************************************************/
	public static void scanFile() {

		// Path filePath = Paths.get("Key.txt");
		String cmd = "r 8 8";
		UnMix unmix = new UnMix();
		Scanner scnr = new Scanner("Key");
		File file = new File("Key");
		BufferedReader reader = null;

		try {
			FileReader fileReader = new FileReader("Key");

			// Always wrap FileReader in BufferedReader.
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			while ((bufferedReader.readLine()) != null) {

				// reader = new BufferedReader(new FileReader(file));
				// cmd = null;
				// // while (scnr.hasNextLine()) {
				// cmd = scnr.nextLine();

				// if(cmd.charAt(0) == 'A')
				// //FIXME needs to call ascii increment method
				// H();
				// if(Objects.equals(cmd, new String("b s #")))
				// insertString(cmd);

				// FIXME
				if (cmd.charAt(0) == 'r') {
					// else if(Objects.equals(cmd, new String("r # #")))
					// int x = Integer.valueOf(scnr.next());
					// int y = Integer.valueOf(scnr.next());
					unmix.removeChars(Integer.valueOf(cmd.charAt(2)), Integer.valueOf(cmd.charAt(4)));
				}
				// else if(Objects.equals(cmd, new String("f")))
				// removeFerguson();
				// else if(Objects.equals(cmd, new String("s")))
				// undoReverseSwap();
				// scnr.close();
				// }
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
			} catch (IOException e) {
			}
		}

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
