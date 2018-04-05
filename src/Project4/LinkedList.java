package Project4;

public class LinkedList {
	private Node top;

	public LinkedList() {
		top = null;
	}

	public void addAtTop(String s) {
		top = new Node(s, top);

	}

	public void display() {
		Node temp = top;
		while (temp != null) {
			System.out.println(temp.getData());
			temp = temp.getNext();
		}
	}

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
	 * @return true if the removal is complete, false if not.
	 *****************************************************************/
	public boolean remove(String s) {
		
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
	public boolean removeSection(int start, int end) {
		
		// Invalid input. End cannot be before start.
		if (start > end)
			return false;
		
		// Invalid input. Section cannot be negative.
		if (start < 0 || end < 0) 
			return false;
		
		// Case 0: The list does not exist.
		if (top == null)
			return false;
		
		// Case 1: The list is not long enough.
		if (lengthList() <= start)
			return false;
		
		// Case 2: The list is only one and the insert is at zero.
		if (lengthList() == 1 && start == 0) {
			top = null;
			return true;
		}
		
		// Case 3: The list can be cut but there is no end section.
		if (lengthList() > start && lengthList() - 1 <= end) {
			
			// This node will be used to move through the list.
			Node temp = top;
			
			// Assign temp to the last position before the cut.
			for (int step = 0; step < start - 1; step++) {
				temp = temp.getNext();
			}
			
			// Set the end of the list.
			temp.setNext(null);
			
			return true;
		}

		// Case 4: The list can be cut and there is an end section.
		if (lengthList() > start && lengthList() - 1 > end) {

			// This node will be used to move through the list.
			Node firstNode = top;

			// This node is the top of the second part of the list.
			Node secondNode;

			// Assign firstNode to the position before the cut.
			for (int step = 0; step < start - 1; step++) {
				firstNode = firstNode.getNext();
			}

			// Start secondNode after firstNode.
			secondNode = firstNode.getNext();
			
			// Assign secondNode to the position after the cut.
			for (int step = 0; step < end - start + 1; step++) {
				secondNode = secondNode.getNext();
			}

			// Direct the firstNode to the Second Node, 
			// removing all in between.
			firstNode.setNext(secondNode);
			System.out.println("Here 160");
			return true;
		}
		
		// Case 5: Remove a section that includes the first node.
		if (start == 0) {
			
			// This node will be used to move through the list.
			Node firstNode = top;
			
			// Assign the firstNode to the node after the cut.
			for (int step = 0; step < end + 1; step++) {
				firstNode = firstNode.getNext();
			}
			
			// Assign the top of the list to the end of the cut.
			top = firstNode;
		}
		
		// Default: A section could not be removed.
		return false;
	}

	public void removeAll(String s) {
		while (remove(s))
			;
	}
	
	/******************************************************************
	 * This method is used to insert a node into the list. If the node
	 * that is to be inserted is not within the reaches of the list,
	 * it will not be added. If the node can be added, it will shift 
	 * all nodes after it by one.
	 * 
	 * @param pos The position where the node will be inserted.
	 * @param s The string data for the inserted node.
	 * @return true if the insert can be complete, false if not.
	 *****************************************************************/
	public boolean insertAfter(int pos, String s) {
		
		// Case 0: The list does not exist.
		if (top == null)
			return false;
		
		// Case 1: The list is not long enough.
		if (lengthList() < pos - 2)
			return false;
		
		// Case 2: The list is only one and the insert is at one.
		if (lengthList() == 2 && pos == 1) {
			
			// Set the second position.
			top.setNext(new Node(s, null));
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
			
			// Add to the end of the list.
			temp.setNext(new Node(s, null));
			
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
			
			// Assign secondNode to the position after the firstNode.
			secondNode = firstNode.getNext();
			
			// Create the insertNode to point to the secondNode.
			insertNode = new Node(s, secondNode);
			
			// Redirect the firstNode to point to the insertNode.
			firstNode.setNext(insertNode);

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
	private int lengthList() {
		
		int length = 0;
		Node temp = top;
		while (temp != null) {
			length++;
			temp = temp.getNext();
		}
		
		return length;
	}
	
	/******************************************************************
	 * This method is used to remove Ferguson from the list
	 *****************************************************************/
	public void removeFerguson() {
		//addFerguson
				int length = lengthList();
				Node prev = null;
				Node next = top;
				Node temp = null;
				String name = "Ferguson";
				for(int i = 0; i <= length; i++) {
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
	
	/******************************************************************
	 * This method increments the ascii value of the character in each
	 * node.
	 *****************************************************************/
	public void incrAscii() {
		int length = lengthList();
		Node temp = top;
		for(int i = 0; i <= length; i++) {
			char letter = (char)((char) temp.getData().charAt(0) + 1);
			temp.setData(Character.toString(letter));
			if(i < length)
				temp = temp.getNext();
		}
	}
	
	/******************************************************************
	 * This method undoes the reverse swap
	 *****************************************************************/
	public void undoReverseSwap() {
		
	}
}
