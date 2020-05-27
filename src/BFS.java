import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

public class BFS extends Algoritem{

	Hashtable<Node, String> Close_list;
	Hashtable<Node, String> Open_list;
	Queue<Node> Queue;
	Queue<Node> myChlids;


	public BFS(Board other_board, boolean time, boolean open)  {
		super(other_board,time,open);
		String S = CreateState(this.board.getMatBoard());
		Node start = new Node(S);
		String G = CreateGoal();
		Node goal = new Node(G);
		this.Close_list = new Hashtable<Node, String>(); 
		this.Open_list = new Hashtable<Node, String>(); 
		this.Queue = new LinkedList<Node>();
		this.myChlids = new LinkedList<Node>();


		//		System.out.println(goal.getData());


		Run_BFS(start,goal);
		// TODO Auto-generated constructor stub
	}

	private boolean Run_BFS(Node start,Node Goal) {

		if (isTime) {
			this.startTime = System.nanoTime();
		}
		Open_list.put(start, start.getData()); // inset the Start Node to the Queue
		Queue.add(start); // add the start to the Queue
		// have close list

		while (!Queue.isEmpty()) {		

			if(isOpen) {
				Hashtable<Integer, Integer>	 h1 = (Hashtable<Integer, Integer>)Open_list.clone();
				System.out.println(h1);
			}

			Node n = Queue.remove(); // get the front element of the queue
			Close_list.put(n, n.getData());
			this.Num++;
			if(n.getData().equals(Goal.getData())) {		
				int cost = createPrice(n.getPath());
				if(isTime) {
					this.endTime   = System.nanoTime();
					this.totalTime = endTime - startTime;
					seconds = (double)totalTime / 1_000_000_000.0;
					n.setPath(n.getPath().substring(0,n.getPath().length()-1));
				}
				CreateFile cf = new CreateFile(n.getPath(), getNum(), isTime, cost,seconds);
				return true;
			}
			this.board.setMatBoard(MyinitMatrix(n,this.board.getMatBoard()));
			this.myChlids = getChilds(n);
			
			for (Node g : this.myChlids) {
				if (!(Close_list.contains(g.getData()))){
					Open_list.put(g, g.getData());
					g.setPath(n.getPath()+g.getPath());
					Queue.add(g);
				}
			}
			this.myChlids.clear();
		}	
		CreateFile cf = new CreateFile(getNum());
		return false;
	}



/**
 * this function will return Queue with the chlid of the n node.
 * @param n the node that we want to made chlids.
 * @return queue with n chlids
 */
public Queue<Node> getChilds(Node n) {

	for (int i = 1; i <= 5; i++) {
		Node temp = new Node(n);
		if(temp.getNoBack() == i) {
			continue;
		}
		Node g = Operator(temp,i,this.board.getMatBoard());
		if(g == null) {
			continue;
		}
		this.myChlids.add(g);
	}
	return this.myChlids;
}

}