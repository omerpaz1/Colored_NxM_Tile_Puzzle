
class Node { 
	String data; 
	int price;
	String path;
	int noBack;

	// Constructor to create a new node 
	// Next is by default initialized 
	// as null 
	Node(String d) { 
		data = d; 
		price = 0;
		path = "";
		noBack = 0;
	}
	public int getNoBack() {
		return noBack;
	}
	Node(Node n) { 
		this.data = n.data; 
		this.price = this.price+n.price;
		this.path = this.path+=n.path+"";
		this.noBack = n.noBack;
		}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	} 
	
} 