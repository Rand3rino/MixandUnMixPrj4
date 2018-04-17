/**********************************************************************
 * Project 4: NewNode
 * 
 * This class contains information for each NewNode, and sets the data
 * for the current node, sets the next node, sets the clipboard number
 * for the current node, sets the node list, and the top node for the 
 * Linked list.
 *
 * @author Randy Nguyen, Sam Ventocilla, and Jay Brunsting.
 * @version April 17th, 2018.
 *********************************************************************/

package Project4;

public class NewNode {
	
	/** String for message to be pasted into clipboard. */
	private String data;

	/** Clipboard number for pasted information. */
	private int clipboardNumber;
	
	/** A NewNode for the next element in the Linked List. */
	private NewNode next;
	
	/** First node in New Linked List. */
	private NewNode newTop;

	/** Reference for the top of each clipboard. */
	private Node top;
	
	/******************************************************************
	 * Constructor for the NewNode class that instantiates all 
	 * instance variables.
	 * 
	 * @param data String for the contents of the clipboards.
	 * @param clipboardNumber Integer for the current clipboard number.
	 * @param next A NewNode for the next clipboard in the clipboard 
	 * 				Linked List.
	 * @param list FIXME
	 * @param top A Node for the current clipboard number Linked List.
	 ******************************************************************/
	public NewNode(int clipboardNumber, NewNode next, Node top) {
		this.clipboardNumber = clipboardNumber;
		this.next = next;
		this.top = top;
	}
	
	/******************************************************************
	 * Get method to return the top node.
	 * 
	 * @return The top node of the clipboard linked list.
	 *****************************************************************/
	public Node getTop() {
		return top;
	}
	
	/******************************************************************
	 * Set method to set the top Node of the Linked List.
	 * 
	 * @param top Sets the top node of the clipboard.
	 *****************************************************************/
	public void setTop(Node top) {
		this.top = top;
	}
	
	/******************************************************************
	 * Get method to return the data of the Linked List.
	 * 
	 * @return Data in the Linked List.
	 *****************************************************************/
	public String getData() {
		return data;
	}
	
	/******************************************************************
	 * Set method for the data in the Linked List.
	 * 
	 * @param data Sets the data in the Linked List to the parameter.
	 *****************************************************************/
	public void setData(String data) {
		this.data = data;
	}
	
	/******************************************************************
	 * Get method for the clipboard number in the clipboard Linked 
	 * List.
	 * 
	 * @return An integer the represents the current clipboard.
	 *****************************************************************/
	public int getClipboardNumber() {
		return clipboardNumber;
	}
	
	/******************************************************************
	 * Set method for the clipboard number in the clipboard Linked 
	 * List.
	 * 
	 * @param clipboardNumber Integer that represents the current 
	 * 							clipboard.
	 *****************************************************************/
	public void setClipboardNumber(int clipboardNumber) {
		this.clipboardNumber = clipboardNumber;
	}
	
	/******************************************************************
	 * Get method for the next node in the clipboard Linked List.
	 * 
	 * @return A NewNode that is the next NewNode in the Linked List.
	 *****************************************************************/
	public NewNode getNext() {
		return next;
	}
	
	/******************************************************************
	 * Set method for the next NewNode in the Linked List.
	 * 
	 * @param next The next NewNode in the Linked List.
	 *****************************************************************/
	public void setNext(NewNode next) {
		this.next = next;
	}
	
	/******************************************************************
	 * Get method for NewNode top which is the first node in New 
	 * Linked List.
	 * 
	 * @return The top node in New Linked List
	 *****************************************************************/
	public NewNode getNewTop() {
		return newTop;
	}

	/******************************************************************
	 * Set method for the New Node top.
	 * 
	 * @param newTop A NewNode that is the first node in New Linked 
	 * 					List.
	 *****************************************************************/
	public void setNewTop(NewNode newTop) {
		this.newTop = newTop;
	}
}
