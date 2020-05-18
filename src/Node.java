
class Node { 
	String data; 
	Node next;
	String path;

	// Constructor to create a new node 
	// Next is by default initialized 
	// as null 
	Node(String d) { 
		data = d; 
		next = null;
		path = "";
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Node getNext() {
		return next;
	}

	public void setNext(Node next) {
		this.next = next;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	} 
	
} 