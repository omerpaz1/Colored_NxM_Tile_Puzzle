import java.util.Hashtable;

public class try1 {
	
	public static void main(String[] args) {
		   Hashtable<Node, String> hash_table = new Hashtable<Node, String>(); 
		   String s= "(Green,2)(Empty,-1)(Green,3)(Green,4)(Green,1)(Green,6)(Red,11)(Green,7)(Green,5)(Green,9)(Green,10)(Green,8)";
		   Node a = new Node(s);
		   hash_table.put(a, a.getData());
		   
		   if(hash_table.contains(s)) {
			   System.out.println("yes");
		   }
		   else {
			   System.out.println("no");
		   }
		   
	}

}
