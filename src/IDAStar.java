import java.util.Enumeration;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class IDAStar extends Algoritem {

	Stack<Node> myStack;
	Hashtable<Node, String> loopAvoidance;
	Queue<Node> myChlids;
	int minF;


	public IDAStar(Board other_board, boolean time, boolean open) {
		super(other_board, time, open);
		String S = CreateState(this.board.getMatBoard());
		String G = CreateGoal();
		Node start = new Node(S);
		goal = new Node(G);
		myStack = new Stack<Node>();
		loopAvoidance = new Hashtable<Node, String>();
		myChlids = new LinkedList<Node>();

		Run_IDAStar(start,goal);
	}

	private boolean Run_IDAStar(Node start, Node goal) {
		if(!Check_if_Solution(start, goal)) {
			CreateFile cf = new CreateFile(getNum());
			return false;
		}
		if (isTime) {
			this.startTime = System.nanoTime();
		}

		int tresh = ManhattanDis(start);

		while(tresh != Integer.MAX_VALUE) {
			minF = Integer.MAX_VALUE;
			myStack.push(start);
			loopAvoidance.put(start,start.getData());

			while(!myStack.isEmpty()) {
				Node n = myStack.pop();
				this.Num++;
				if(n.isOut()) {
					loopAvoidance.remove(n);
				}
				else {
					n.setOut(true);
     					myStack.push(n);
				}
				this.board.setMatBoard(MyinitMatrix(n,this.board.getMatBoard()));
				this.myChlids = getChilds(n);
				for (Node g : myChlids) {
					g.setPath(n.getPath()+g.getPath());
					set_F_of_N(g,goal);
					if(g.getF() > tresh) {
						minF = Math.min(g.getF(), minF);
						continue;
					}
					if(loopAvoidance.contains(g.getData()) && g.isOut()) {
						continue;
					}
					if(loopAvoidance.contains(g.getData()) && (!g.isOut())) {
						Node t = findNode(g);
						if(t.getF() > g.getF()) {
							// remove from stack
							System.out.println("here");
							// remove from loopavoidnace
						}
						else {
							continue;
						}
					}
					if(g.getData().equals(goal.getData())) {
						System.out.println("found!");
						int cost = createPrice(g.getPath());
						String nPath = g.getPath();
						if(isTime) {
							this.endTime   = System.nanoTime();
							this.totalTime = endTime - startTime;
							seconds = (double)totalTime / 1_000_000_000.0;
							g.setPath(nPath.substring(0,nPath.length()-1));
						}
						CreateFile cf = new CreateFile(g.getPath(), getNum(), isTime, cost,seconds);
						return true;
					}
					else {
						myStack.push(g);
						loopAvoidance.put(g, g.getData());
					}

			}
			this.myChlids.clear();

		}
		tresh = minF;

	}


	return false;
}

public Node findNode(Node n) {
	Enumeration enu = loopAvoidance.keys(); 
	int i = 0;
	while (enu.hasMoreElements()) { 

		Node temp =(Node) enu.nextElement(); 
		if(temp.getData().equals(n.getData())) {
			return temp;
		}
		i++;
	} 
	return n;
}


private void set_F_of_N(Node n,Node goal) {
	int h = ManhattanDis(n);
	int g = createPrice(n.getPath());
	// TODO Auto-generated method stub
	n.setF(h+g);

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
