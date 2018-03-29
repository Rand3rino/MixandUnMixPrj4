package Project4;

public class UnMix {

	/** FIXME */
	private LinkedList message = new LinkedList();
	
	/** This is the input message */
	private static String userMessage;



	public void main(String [] args) {
		UnMix.userMessage = "";

		for (int i = 0; i < args.length; i++) {
			UnMix.userMessage += args[i] + "";
		}
		
		System.out.print ("Message:\n");
		for (int i = 0; i < userMessage.length(); i++) 
			System.out.format ("%3d", i);
		System.out.format ("\n");
		for (char c : userMessage.toCharArray()) 
			System.out.format("%3c",c);
		System.out.format ("\n");

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
	public void insertString() {

	}

	/******************************************************************
	 *For r # *
	 *Removes all of the characters from # to *
	 *****************************************************************/
	public void removeChars() {

	}

	/******************************************************************
	 *Displays a page to help users learn how to enter their string 
	 *****************************************************************/
	public void H() {
		System.out.println("Helpful message");

	}
}
