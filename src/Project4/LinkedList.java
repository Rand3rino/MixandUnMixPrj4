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
			while (temp.getNext() != null)
				temp = temp.getNext();
				temp.setNext(new Node(s, temp.getNext()));
		}

	}

	public boolean remove(String s) {
		//case 1: no list
		if(top == null)
			return false;
		
		//case 2: 1 item and s is found
		if(top.getData().equals(s) && top.getNext() == null) {
			top = null;
			return true;
		}
		
		//case 3: 1 item and s is not found
		if(!top.getData().equals(s) && top.getNext() == null)
			return false;
		
		//case 4: multi-item and s is on top
		if(top.getData().equals(s) && top.getNext() != null) {
			top = top.getNext();
			return true;
		}
		
		//case 5: multi-item and s is not on top
		Node temp = top;
		while(temp.getNext() != null) {
			if(temp.getNext().getData().equals(s)) {
				temp.setNext(temp.getNext().getNext());
				return true;
			}
			else
				temp = temp.getNext();
		}
		
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
	 * @return true if the insert can be complete, false if not.
	 *****************************************************************/
	public boolean insertAfter(int pos, String s) {
		
		// Case 0: The list does not exist.
		if (top == null)
			return false;
		
		// Case 1: The list is not long enough.
		if (lengthList() < pos - 1)
			return false;
		
		// Case 2: The list is only one and the insert is at one.
		if (lengthList() == 1 && pos == 1) {
			
			// Set the second position.
			top.setNext(new Node(s, null));
			return true;
		}
		
		// Case 3: The list is one short of the position.
		if (lengthList() == pos - 1) {
			
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
		if (lengthList() >= pos) {
			
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

	public static void main(String[] args) {
		LinkedList list = new LinkedList();

		list.append("pizza0");
		list.append("donuts1");
		list.append("chocolate2");
		list.append("pizza3");
		list.append("donuts4");
		list.append("chocolate5");
		list.append("pizza3");

		list.display();
	}
}
