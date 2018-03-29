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
		if (top == null)
			addAtTop(s);

		// Case 1: at Least one item in list
		else {

			// code is not needed if using a tail
			Node temp = top;
			while (temp.getNext() != null)
				temp = temp.getNext();
		}

	}

	public boolean remove(String s) {
		return false;
	}

	public void removeAll(String s) {
		while (remove(s))
			;
	}

	// This method needs to be changed to handle the tail pointer
	public boolean insertAfter(int pos, String s) {
		return false;
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
