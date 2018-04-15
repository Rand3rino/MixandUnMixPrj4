/**********************************************************************
 * Project 4: Node
 * 
 * This class contains information for each node, and sets the data for
 * the current node and sets the next node.
 *
 * @author Randy Nguyen, Sam Ventocilla, and Jay Brunsting.
 * @version April 17th, 2018.
 *********************************************************************/

package Project4;

public class Node {
	
	/** String to hold the data for the node. */
	private String data;
	
	/** A node to set the next node in the linked list. */
	private Node next;

	/******************************************************************
	*Constructor for the Node class that initializes data and next.
	*
	*@param data Variable for the Nodes data.
	*@param next Sets the next node in the linked list.
	******************************************************************/
	public Node(String data, Node next) {
		this.data = data;
		this.next = next;
	}

	/******************************************************************
	*Get method that returns the data of the current node.
	*
	*@return A string that is the data of the current node.
	******************************************************************/
	public String getData() {
		return data;
	}
	
	/******************************************************************
	*Set method to set the data for the current node.
	*
	*@param data String to set the data for the current node.
	******************************************************************/
	public void setData(String data) {
		this.data = data;
	}
	
	/******************************************************************
	*Set method to set the next node in the linked list.
	*
	*@param next Node to set the next node in the linked list.
	******************************************************************/
	public void setNext(Node next) {
		this.next = next;
	}
	
	/******************************************************************
	*Get method the return the next node in the linked list.
	*
	*@return The next node in the linked list.
	******************************************************************/
	public Node getNext() {
		return next;
	}
}
