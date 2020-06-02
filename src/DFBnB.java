import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;


public class DFBnB extends Algoritem{

	Stack<Node> myStack;
	
	Hashtable<Node, String> loopAvoidance;
	PriorityQueue<Node> PQmyChlids;
	String result;

	public DFBnB(Board other_board, boolean time, boolean open) {
		super(other_board, time, open);
		// TODO Auto-generated constructor stub
		String S = CreateState(this.board.getMatBoard());
		String G = CreateGoal();
		Node start = new Node(S);
		goal = new Node(G);
		myStack = new Stack<Node>();
		loopAvoidance = new Hashtable<Node, String>();
		this.PQmyChlids = new PriorityQueue<Node>((o1, o2) -> o1.compareTo(o2));

		Run_DFBnB(start,goal);
	}

	private boolean Run_DFBnB(Node start, Node goal) {
		if(!Check_if_Solution(start, goal)) {
			CreateFile cf = new CreateFile(getNum());
			return false;
		}
		if (isTime) {
			this.startTime = System.nanoTime();
		}
		myStack.add(start);
		loopAvoidance.put(start, start.getData());
		int thresh = Integer.MAX_VALUE;
		result = null;
		ArrayList<Node> ListCurrentChlids = new ArrayList<Node>();
		this.Num++;
		
		while(!myStack.isEmpty()) {
			Node n = myStack.pop();
			if(n.isOut()) {
				loopAvoidance.remove(n);
			}
			else {
				n.setOut(true);
				myStack.push(n);
				
				this.board.setMatBoard(MyinitMatrix(n,this.board.getMatBoard()));
				getChilds(n);
				setNum(this.Num+this.PQmyChlids.size());
				for (Node g : this.PQmyChlids) { // start foreach
					if(g.getF() >= thresh) {
						this.PQmyChlids.clear();
						break;
					}
					else if(loopAvoidance.contains(g.getData()) && g.isOut()) {
						this.PQmyChlids.remove(g);
						continue;
					}
					else if(loopAvoidance.contains(g.getData()) && !(g.isOut())) {
						Node t = findNode(g);
//						System.out.println("t.getF() = "+t.getF());
//						System.out.println("g.getF() = "+g.getF());
//						System.out.println("t.getF() <= g.getF() ?"+(t.getF() <= g.getF()));
//						System.out.println("thresh = "+thresh);
						if(t.getF() <= g.getF()) {
							continue;
						}
						else {
							int index = myStack.indexOf(t);
							myStack.remove(index);
							this.loopAvoidance.remove(t);
						}
					}
					else if(g.getData().equals(goal.getData())) {
						result = g.getPath();
						thresh = g.getF();
						PQmyChlids.clear();
						break;
						
					}
					else {
						ListCurrentChlids.add(g);	
					}

				} // end for each
				Collections.reverse(ListCurrentChlids);
				for (Node item : ListCurrentChlids) {
					myStack.push(item);
					loopAvoidance.put(item,item.getData());

				}
				ListCurrentChlids.clear();
				PQmyChlids.clear();

			} // else if n not out


		}
		if(result != null) { // there is sulution
			int cost = createPrice(result);
			if(isTime) {
				this.endTime   = System.nanoTime();
				this.totalTime = endTime - startTime;
				seconds = (double)totalTime / 1_000_000_000.0;
			}
			result = result.substring(0,result.length()-1);
			CreateFile cf = new CreateFile(result, getNum(), isTime, cost,seconds);
			return true;
		}

		return false;	
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

	public void getChilds(Node n) {

		for (int i = 1; i <= 5; i++) {
			Node temp = new Node(n);
			if(temp.getNoBack() == i) {
				continue;
			}
			Node g = Operator(temp,i,this.board.getMatBoard());
			if(g == null) {
				continue;
			}
			g.setPath(n.getPath()+g.getPath());
			set_F_of_N(g,goal);
			this.PQmyChlids.add(g);
		}
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

}
