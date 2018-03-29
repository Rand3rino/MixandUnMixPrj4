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
}
