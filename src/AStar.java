import java.util.Comparator;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class AStar extends Algoritem {

	Hashtable<Node, String> Close_list;
	Hashtable<Node, String> Open_list;
	Queue<Node> myChlids;
	Node goal;
	PriorityQueue<Node> pQueue;

	public AStar(Board other_board, boolean time, boolean open) {
		super(other_board, time, open);
		String S = CreateState(this.board.getMatBoard());
		String G = CreateGoal();
		Node start = new Node(S);
		goal = new Node(G);
		this.Close_list = new Hashtable<Node, String>();
		this.Open_list = new Hashtable<Node, String>(); 
		this.pQueue = new PriorityQueue<Node>((o1, o2) -> o1.compareTo(o2));
		this.myChlids = new LinkedList<Node>();



		Run_AStar(start,goal);

	}

	private boolean Run_AStar(Node start, Node goal) {	
		if(!Check_if_Solution(start, goal)) {
			CreateFile cf = new CreateFile(getNum());
			return false;
		}
		if (isTime) {
			this.startTime = System.nanoTime();
		}
		Open_list.put(start,start.getData()); // inset the Start Node to the Queue
		pQueue.add(start); // add the start to the Queue

		this.Num++;
		while (!pQueue.isEmpty()) {
			if(isOpen) {
				Hashtable<Node, String>	 h1 = (Hashtable<Node, String>)Open_list.clone();
				System.out.println(h1);
			}

			Node n_f = pQueue.remove(); // get the front element of the queue
			Close_list.put(n_f, n_f.getData());
			if(n_f.getData().equals(goal.getData())) { // its mean we in the goal 	
				String nPath = n_f.getPath();
				int cost = createPrice(nPath);
				if(isTime) {
					this.endTime   = System.nanoTime();
					this.totalTime = endTime - startTime;
					seconds = (double)totalTime / 1_000_000_000.0;
					n_f.setPath(nPath.substring(0,nPath.length()-1));
				}
				CreateFile cf = new CreateFile(n_f.getPath(), getNum(), isTime, cost,seconds);
				return true;
			}
			this.board.setMatBoard(MyinitMatrix(n_f,this.board.getMatBoard()));
			this.myChlids = getChilds(n_f);

			for (Node g : this.myChlids) {
				if (!(Close_list.contains(g.getData()))){
					Open_list.put(g,g.getData());
					g.setPath(n_f.getPath()+g.getPath());
					set_F_of_N(g,goal);
					pQueue.add(g);
					this.Num++;
				}
				else if(Open_list.contains(g.getData())) {
					Node temp = findNode(g);
					int g_price =  createPrice(g.getPath());
					int t_price =  createPrice(temp.getPath());
					if(t_price > g_price) {
						Open_list.remove(temp);
						Open_list.put(g,g.getData());
						set_F_of_N(g,goal);
					}
				}

			}
			this.myChlids.clear();

		}
		return false;

	}

	public Node findNode(Node n) {
		Enumeration enu = Open_list.keys(); 
		while (enu.hasMoreElements()) { 

			Node temp =(Node) enu.nextElement(); 
			if(temp.getData().equals(n.getData())) {
				return temp;
			}
		} 
		return n;
	}

	private void set_F_of_N(Node start,Node goal) {
		int h = ManhattanDis(start);
		int g = createPrice(start.getPath());
		// TODO Auto-generated method stub
		start.setF(h+g);

	}
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

	public int ManhattanDis(Node start) {
		int sumManattanDis = 0;
		int[] goal_place = new int[2];
		Piece[][] startMat = MyinitMatrix(start, this.board.getMatBoard());
		Piece[][] goalMat = MyinitMatrix(goal, this.board.getMatBoard());
		for (int i = 0; i < startMat.length; i++) {
			for (int j = 0; j < startMat[0].length; j++) {
				int StateNum = startMat[i][j].getNumber_Piece();
				int GoalNum = goalMat[i][j].getNumber_Piece();
				if(StateNum != GoalNum && StateNum != -1) {
					Piece temp = GoalHash.get(StateNum); 
					goal_place = temp.getPlace(); 
					sumManattanDis+= (Math.abs(goal_place[0]-i)+Math.abs(goal_place[1]-j))*temp.getPrice();					
				}
			}
		}


		return sumManattanDis;
	}





}



