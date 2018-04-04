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
