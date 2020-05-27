
class Node { 

	String data; 
	int price;
	String path;
	int noBack;
	boolean cutoff;
	int f;
	boolean out;

	// Constructor to create a new node 
	// Next is by default initialized 
	// as null 
	Node(String d) { 
		data = d; 
		price = 1;
		path = "";
		noBack = 0;
		cutoff = false;
		f = Integer.MAX_VALUE;
		this.out = false;
	}
	Node(Node n) { 
		this.data = n.data; 
		this.price = this.price+n.price;
		this.path = this.path+=n.path+"";
		this.noBack = n.noBack;
		this.f = n.f;
		}


	public int getF() {
		return f;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public void setF(int f) {
		this.f = f;
	}
	public boolean isOut() {
		return out;
	}
	public void setOut(boolean out) {
		this.out = out;
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
	public boolean isCutoff() {
		return cutoff;
	}
	public void setCutoff(boolean cutoff) {
		this.cutoff = cutoff;
	}
	public int getNoBack() {
		return noBack;
	}
    public int compareTo(Node other) {
        return (this.f - other.f);
    }
	
} 