/**********************************************************************
 * Project 4: NewLinkedList
 * 
 * This class will execute commands for clipboards such as copy, cut,
 * and paste. 
 *
 * @author Randy Nguyen, Sam Ventocilla, and Jay Brunsting.
 * @version April 17th, 2018.
 *********************************************************************/

package Project4;

public class NewLinkedList {

	/** The top node of the linked list. */
	private Node top;

	/** The current node in the new linked list */
	private NewNode clipboard;
	
	/** The top node of the new linked list. */
	private NewNode newTop;

	/******************************************************************
	 * Constructor for LinkedList that sets top to null.
	 *****************************************************************/
	public NewLinkedList() {
		top = null;
		clipboard = null;
		newTop = null;
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
	 * This method will remove a section of the list. This will not
	 * work if the list is outside the domain of the section.
	 * 
	 * @param start The beginning location of the cut.
	 * @param end The final location of the cut.
	 * @return true if the removal is complete, false if not.
	 *****************************************************************/
	public String removeSection(int start, int end) {
		
		// Variable for the data that was removed.
		String removed = "";

		// Invalid input. End cannot be before the start.
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
			for (int step = 0; step < start - 1; step++)
				temp = temp.getNext();

			Node delete = temp;

			// Get the string data of the end positions.
			for(int step = start; step <= end; step++) {
				removed += temp.getData();
				temp = temp.getNext();
			}

			// Set the end of the list.
			delete.setNext(null);

			// Return the string that was cut out.
			return removed;
		}

		// Case 6: Remove a section starting at first node 
		//and has an end.
		if (start == 0) {

			// This node will be used to move through the list.
			Node firstNode = top;

			// Assign the firstNode to the node after the cut.
			for (int step = 0; step < end+1; step++)
				firstNode = firstNode.getNext();

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
	 * Method to copy a given section from the Linked List.
	 * 
	 * @param start Position to begin copying from.
	 * @param end Position to end copying form
	 * @return A string with the section copied.
	 *****************************************************************/
	public String copySection(int start, int end) {
		String removed = "";
		Node temp = top;

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

		// Case 2: The list is only one and the copy is at zero.
		if (lengthList() == 1 && start == 0) {
			removed = top.getData();
			return removed;
		}

		// Case 3: User enters 0 0. Copy the top node.
		if (start == 0 && end == 0) {
			removed = top.getData();
			return removed;
		}

		//Case 4: Copy a section starting at 0 and having no end
		if (start == 0 && lengthList() - 1 <= end) {
			temp = top;
			removed += temp.getData();

			//loop through linked list and copy message
			for(int i = 0; i < lengthList(); i++) {
				temp = temp.getNext();
				removed += temp.getData();
			}
			return removed;
		}			

		// Case 5: The list can be copied but there is no end section.
		if(lengthList() > start && lengthList() - 1 <= end) {

			// This node will be used to move through the list.
			temp = top;

			// Assign temp to the last position before the copy.
			for (int step = 0; step < start - 1; step++) {
				temp = temp.getNext();
			}

			//Get the value of the last positions
			for(int step = start; step <= end; step++) {
				removed += temp.getData();
				temp = temp.getNext();
			}

			return removed;
		}

		// Case 6: Copy a section starting at first node 
		// and has an end.
		if (start == 0) {

			// Assign the firstNode to the node after the copy.
			for (int step = 0; step < end+1; step++) {
				removed = temp.getData();
				temp = temp.getNext();
			}

			return removed;
		}

		// Case 7: The list can be copied and there is an end section.
		if (lengthList() > start && lengthList() - 1 > end) {

			// This node will be used to move through the list.
			temp = top;

			// Assign temp to the beginning of the section to be copied
			for (int step = 0; step < start - 1; step++)
				temp = temp.getNext();

			// Assign secondNode to the position after the copy.
			for (int step = 0; step < end - start + 1; step++) {
				removed += temp.getData();
				temp = temp.getNext();
			}

			return removed;
		}

		// Default: A section could not be copied.
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
			for (int step = 0; step < pos - 1; step++)
				temp = temp.getNext();

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
			for (int step = 0; step < pos - 1; step++)
				firstNode = firstNode.getNext();

			//secondNode = firstNode.getNext();
			// Assign secondNode to the end position of insert.
			for(int i = 0; i < s.length(); i++) {
				secondNode = firstNode.getNext();	

				// Create the insertNode to point to the secondNode.
				insertNode = new Node(s.substring(i, i + 1), 
						secondNode);

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
	 *Method to paste given clipboard into linked list at given index.
	 *
	 *@param index Location to paste clipboard into.
	 *@param clipboard Clipboard to be pasted into linked list.
	 ******************************************************************/
	public String paste(int index, int clipboardNum) {
		
		//take message from the given clipboard and using insertAfter method
		//paste the message into Linked List
		String removed = "";
		NewNode temp = newTop;
		Node temp1;

		//Case 0: No clip board.
		if(temp == null)
			return removed;

		//Case 1: If the first clipboard is the desired clipboard.
		if(temp.getClipboardNumber() == clipboardNum) {
			removed += temp.getTop().getData();
			temp1 = temp.getTop();

			//loop through rest of linked list and get data
			while(temp1.getNext().getData() != null)
				removed += temp1.getNext().getData();

			if(!insertAfter(index, removed))
				return "";
		}

		//Case 2: ClipboardNum is not the first node.
		while(temp.getNext() != null) {
			if(temp.getClipboardNumber() == clipboardNum) {
				removed += temp.getTop().getData();
				temp1 = temp.getTop();

				//loop through rest of linked list and get data
				while(temp1.getNext().getData() != null)
					removed += temp1.getNext().getData();

				if(!insertAfter(index, removed))
					return "";
			}
			temp = newTop;
		}

		return removed;
	}

	/******************************************************************
	 *Method to copy from linked list into a clipboard.
	 *
	 *@param startIndex Index in linked list to begin copying from.
	 *@param endIndex Index in linked list to end copying from.
	 *@param clipboard Clipboard to paste message into.
	 ******************************************************************/
	public void copy(int startIndex, int endIndex, int clipboardNum) {
		
		String temp = copySection(startIndex, endIndex);
		Node temp1 = top;		
		NewNode end = newTop;

		//if the New Linked List is empty
		if(end == null)
			end = new NewNode(clipboardNum, null, top);
		
		//if the New Linked List is one element long
		else if(end.getNext() == null)
			end.setNext(new NewNode(clipboardNum, null, top));
		
		//if the New Linked List is more than one element
		else {

			//loop to end of NewLinkedList
			while(end.getNext() != null) 
				end = end.getNext();
			end.setNext(new NewNode(clipboardNum, null, top));
		}

		//add copied message to New Linked List
		top.setData(temp.substring(0, 1));
		temp1 = top;
		for(int i = 0; i < temp.length(); i++) {
			temp1.setNext(new Node(temp.substring(i, i+1), null));
			temp1 = temp1.getNext();
		}
	}

	/******************************************************************
	 *Method to cut section from linked list and paste it into the 
	 *clipboard.
	 *
	 *@param startIndex Index to begin cutting from in linked list.
	 *@param endIndex Index to end cutting from in linked list.
	 *@param clipboard Clipboard to paste cut section in.
	 ******************************************************************/
	public String cut(int startIndex, int endIndex, int clipboardNum) {
		copy(startIndex, endIndex, clipboardNum);
		return removeSection(startIndex, endIndex);	
	}
}
