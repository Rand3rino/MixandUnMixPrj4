/**********************************************************************
 * Project 4: LinkedList
 * 
 * This is the linked list class that contains all the commands to 
 * the message to encrypt and decrypt it.
 *
 * @author Randy Nguyen, Sam Ventocilla, and Jay Brunsting.
 * @version April 17th, 2018.
 *********************************************************************/

package Project4;

public class LinkedList {
	
	/** The top node of the linked list. */
	private Node top;

	/******************************************************************
	 * Constructor for LinkedList that sets top to null.
	 *****************************************************************/
	public LinkedList() {
		top = null;
	}

	/******************************************************************
	 * Get method that returns the top node.
	 * 
	 * @return The top node of the linked list.
	 *****************************************************************/
	public Node getTop() {
		return top;
	}

	/******************************************************************
	 * Method to add to the top of the linked list.
	 * 
	 * @param s Data to set the top as.
	 *****************************************************************/
	public void addAtTop(String s) {
		top = new Node(s, top);

	}

	/******************************************************************
	 * Method to print out the linked list in a formatted version with
	 * the number of the position above each node.
	 *****************************************************************/
	public void display() {
		Node temp = top;

		System.out.print ("Message:\n");
		for (int i = 0; i < lengthList(); i++) { 
			System.out.format ("%3d", i);

		}
		System.out.format ("\n");
		for (int i = 0; i < lengthList(); i++)  {
			System.out.format("%3c", temp.getData().charAt(0));
			temp = temp.getNext();
		}
		System.out.format ("\n");
	}

	/******************************************************************
	 * Method to add a string to the end of the linked list.
	 * 
	 * @param s Data of type string to be added to end of the linked 
	 * 			list.
	 *****************************************************************/
	public void append(String s) {

		// Case 0: The list does not exist.
		if (top == null) {
			addAtTop(s);
			return;
		}

		// Case 1: at Least one item in list
		else {
			Node temp = top;
			while (temp.getNext() != null) {
				temp = temp.getNext();
			}
			temp.setNext(new Node(s, null));
		}

	}

	/******************************************************************
	 * This method will remove a node that is the first occurrence of 
	 * the given string.
	 * 
	 * @param s The input string used for the search.
	 * @return True if the removal is complete, false if not.
	 *****************************************************************/
	public boolean remove(String s) {
		//String to hold the removed characters
		String removed;

		// Case 0: There is no list.
		if(top == null)
			return false;

		// Case 1: There is 1 item and s is found.
		if(top.getData().equals(s) && top.getNext() == null) {
			top = null;
			return true;
		}

		// Case 2: There is 1 item and s is not found.
		if(!top.getData().equals(s) && top.getNext() == null)
			return false;

		// Case 3: There are multiple items and s is on top.
		if(top.getData().equals(s) && top.getNext() != null) {
			top = top.getNext();
			return true;
		}

		// Case 4: There are multiple items and s is not on top.
		Node temp = top;
		while(temp.getNext() != null) {
			if(temp.getNext().getData().equals(s)) {
				temp.setNext(temp.getNext().getNext());
				return true;
			}
			else
				temp = temp.getNext();
		}

		// Default: A node could not be removed.
		return false;
	}

	/******************************************************************
	 * This method will remove a section of the list. This will not
	 * work if the list is outside the domain of the section.
	 * 
	 * @param start The beginning location of the cut.
	 * @param end The final location of the cut.
	 * @return true if the removal is complete, false if not.
	 *****************************************************************/
	public String removeSection(int start, int end) {
		String removed = "";

		// Invalid input. End cannot be before start.
		if (start > end)
			return removed;

		// Invalid input. Section cannot be negative.
		if (start < 0 || end < 0) 
			return removed;

		// Case 0: The list does not exist.
		if (top == null)
			return removed;

		// Case 1: The list is not long enough.
		if (lengthList() <= start)
			return removed;

		// Case 2: The list is only one and the insert is at zero.
		if (lengthList() == 1 && start == 0) {
			removed = top.getData();
			top = null;
			return removed;
		}

		// Case 3: User enters 0 0. Cut the top node.
		if (start == 0 && end == 0) {
			removed = top.getData();
			top = top.getNext();
			return removed;
		}

		//Case 4: Remove a section starting at 0 and having no end
		if (start == 0 && lengthList() - 1 <= end) {
			top = null;
			return removed;
		}			

		// Case 5: The list can be cut but there is no end section.
		if(lengthList() > start && lengthList() - 1 <= end) {

			// This node will be used to move through the list.
			Node temp = top;

			// Assign temp to the last position before the cut.
			for (int step = 0; step < start - 1; step++) {
				temp = temp.getNext();
			}

			Node delete = temp;

			//Get the value of the last positions
			for(int step = start; step <= end; step++) {
				removed = removed + temp.getData();
				temp = temp.getNext();
			}

			// Set the end of the list.
			delete.setNext(null);

			return removed;
		}

		// Case 6: Remove a section starting at first node 
		//and has an end.
		if (start == 0) {

			// This node will be used to move through the list.
			Node firstNode = top;

			// Assign the firstNode to the node after the cut.
			for (int step = 0; step < end+1; step++) {
				firstNode = firstNode.getNext();
			}

			// Assign the top of the list to the end of the cut.
			top = firstNode;
		}

		// Case 7: The list can be cut and there is an end section.
		if (lengthList() > start && lengthList() - 1 > end) {

			// This node will be used to move through the list.
			Node firstNode = top;

			// This node is the top of the second part of the list.
			Node secondNode;

			// Assign firstNode to the position before the cut.
			for (int step = 0; step < start - 1; step++) {
				removed = removed + firstNode.getData();
				firstNode = firstNode.getNext();
			}

			// Start secondNode after firstNode.
			secondNode = firstNode.getNext();

			// Assign secondNode to the position after the cut.
			for (int step = 0; step < end - start + 1; step++) {
				removed = removed + secondNode.getData();
				secondNode = secondNode.getNext();
			}

			// Direct the firstNode to the Second Node, 
			// removing all in between.
			firstNode.setNext(secondNode);
			return removed;
		}

		// Default: A section could not be removed.
		return removed;
	}

	/******************************************************************
	 * This method is used to insert a node into the list. If the node
	 * that is to be inserted is not within the reaches of the list,
	 * it will not be added. If the node can be added, it will shift 
	 * all nodes after it by the length of s.
	 * 
	 * @param pos The position where the node will be inserted.
	 * @param s The string data for the inserted node.
	 * @return True if the insert can be complete, false if not.
	 *****************************************************************/
	public boolean insertAfter(int pos, String s) {

		// Case 0: The list does not exist.
		if (top == null)
			return false;

		// Case 1: The list is not long enough.
		if (lengthList() < pos - 2)
			return false;

		// Case 2: Insert at position 0.
		if (pos == 0) {
			Node temp = top;
			Node temp1;

			//add first node
			addAtTop(s.substring(0, 1));
			temp1 = top;

			// add s to end of list
			for(int i = 1; i < s.length(); i++) {
				temp1.setNext(new Node(s.substring(i, i+1), null));
				temp1 = temp1.getNext();
			}

			temp1.setNext(temp);
			return true;
		}

		// Case 3: The list is one short of the position.
		if (lengthList() == pos) {

			// This node will be used to move through the list.
			Node temp = top;

			// Assign temp to the last position of the list.
			for (int step = 0; step < pos - 1; step++) {
				temp = temp.getNext();
			}

			//insert string at end of list
			for(int i = 0; i < s.length(); i++) {
				// Add to the end of the list.
				temp.setNext(new Node(s.substring(i, i + 1), null));
				temp = temp.getNext();
			}

			return true;
		}

		// Case 4: The length matches or is greater than the position.
		if (lengthList() > pos) {

			// This node will be used to move through the list.
			Node firstNode = top;

			// This node will be the top of the second part of the list.
			Node secondNode;

			// This node will be inserted into the list.
			Node insertNode;

			// Assign firstNode to the position before the insert.
			for (int step = 0; step < pos - 1; step++) {
				firstNode = firstNode.getNext();
			}

			//secondNode = firstNode.getNext();
			// Assign secondNode to the end position of insert.
			for(int i = 0; i < s.length(); i++) {
				secondNode = firstNode.getNext();	

				// Create the insertNode to point to the secondNode.
				insertNode = new Node(s.substring(i, i + 1), secondNode);

				// Redirect the firstNode to point to the insertNode.
				firstNode.setNext(insertNode);
				firstNode = firstNode.getNext();
			}

			return true;
		}

		// Default: Unable to insert node.
		return false;
	}

	/******************************************************************
	 * This method is used to determine the length of the list.
	 * 
	 * @return length The number of nodes in this Linked List.
	 *****************************************************************/
	public int lengthList() {

		int length = 0;
		Node temp = top;
		while (temp != null) {
			length++;
			temp = temp.getNext();
		}

		return length;
	}

	/******************************************************************
	 * This method is used to add "Ferguson" to the list at every other
	 * node
	 *****************************************************************/
	public void addFerguson() {
		
		//addFerguson
		int length = lengthList();
		Node prev = null;
		Node next = top;
		Node temp = null;
		String name = "Ferguson";
		for(int i = 0; i <= length; i++) {
			//if length is twice as long as name
			if(i < 8) {
				//alternate letters
				if(i == 0) {
					top = new Node(name.substring(0, 1), next);
				}
				else{
					temp = new Node(name.substring(i, i+1), next);
					prev.setNext(temp);
				}

				if(i < length) {
					prev = next;
					next = next.getNext();
				}
			}
		}
	}

	/******************************************************************
	 * This method removes the string Ferguson, which is alternated
	 * every other node, from the list of nodes
	 *****************************************************************/
	public void removeFerguson() {
		for(int i = 0; i <= lengthList(); i++) {
			//alternate letters
			if(i > 15)
				break;
			if(i == 0) {
				removeSection(0, 0);
			}
			else{
				removeSection(i, i);
			}
		}
	}

	/******************************************************************
	 * This method increments the ascii value of the character in each
	 * node.
	 * 
	 * @param check Character value to be incremented
	 * @return A boolean that is true if ascii value is incremented and
	 * 			false if it was unable to increment.
	 ******************************************************************/
	public boolean changeAscii(char check) {
		int length = lengthList();
		Node temp = top;
		int incrdecr;
		if(check == '+') {
			incrdecr = 1;

			//check if list is past ascii index
			for(int i = 0; i < this.lengthList(); i++) {
				if((int)temp.getData().charAt(0) > 125) 
					return false;
				temp = temp.getNext();
			}
		}
		else{
			incrdecr = -1;

			//check if list is past ascii index
			for(int i = 0; i < this.lengthList(); i++) {
				if((int)temp.getData().charAt(0) != 32) {
					if((int)temp.getData().charAt(0) == 33) 
						return false;
				}
				temp = temp.getNext();
			}
		}

		Node temp1 = top;

		//increment each node that is not a space
		for(int i = 0; i <= length-1; i++) {
			if((int)temp1.getData().charAt(0) != 32) {
				char letter = (char)((char) temp1.getData().charAt(0) + incrdecr);
				temp1.setData(Character.toString(letter));
			}
			if(i < length)
				temp1 = temp1.getNext();
		}

		return true;
	}

	/******************************************************************
	 * This method takes the last half of the list and sets it as the
	 * first half
	 *****************************************************************/
	public void SwapHalf() {
		int length = lengthList();
		int half = length / 2;
		Node temp = top;
		Node delete;
		Node swap;
		
		//sets temp as the middle node
		for(int i = 0; i < half - 1; i++) {
			temp = temp.getNext();
		}

		delete = temp;
		temp = temp.getNext();
		swap = top;
		top = temp;	

		for(int j = 0; j < length - half-1; j++) {
			temp = temp.getNext();	
		}
		temp.setNext(swap);
		delete.setNext(null);
	}

	/******************************************************************
	 * This method takes the last half of the mixed up list and sets it
	 * as the first half so that it undoes the method SwapHalf, which
	 * set the last part of the list as the first part
	 *****************************************************************/
	public void UnSwapHalf() {
		int length = lengthList();
		
		//gets the middle node
		int half = (int)Math.ceil((double)length / 2);
		Node temp = top;
		Node delete;
		Node swap;
		for(int i = 0; i < half - 1; i++) {
			temp = temp.getNext();
		}

		delete = temp;
		temp = temp.getNext();
		swap = top;
		top = temp;	

		for(int j = 0; j < length - half-1; j++) {
			temp = temp.getNext();	
		}
		temp.setNext(swap);
		delete.setNext(null);
	}
}
