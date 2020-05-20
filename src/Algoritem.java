import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Algoritem {

	Boolean isTime;
	Boolean isOpen;
	Board board;
	int Num;
	int Cost;
	long startTime;
	long endTime;
	long totalTime;
	double seconds;

	// for BDS:
	Hashtable<Node, String> Close_list;
	Hashtable<Node, String> Open_list;
	Queue<Node> Queue;


	public Algoritem(Board other_board, boolean time, boolean open) {
		this.isTime = time;
		this.isOpen = open;
		this.board = other_board;
		this.Close_list = new Hashtable<Node, String>(); 
		this.Open_list = new Hashtable<Node, String>(); 
		this.Queue = new LinkedList<Node>();
		this.Num = 0;
		this.Cost = 0;
		long startTime = (long) 0.0;
		long endTime = (long) 0.0;
		long totalTime = (long)0.0;
		double seconds = 0;
		
	}

	public String CreateState() {
		String State = "";

		for (int i = 0; i < this.board.getN(); i++) {
			for (int j = 0; j < this.board.getM(); j++) {
				State+=this.board.getMatBoard()[i][j];
			}
		}
		return State;
	}

	// create goal state
	public String CreateGoal() {
		String Goal = "";
		Piece emptyPiece = this.board.getEmptyPiece();
		int size_board = this.board.getN()*this.board.getM();
		this.board.getList_pieces().remove(emptyPiece);

		Collections.sort(this.board.getList_pieces(), 
				(o1, o2) -> o1.compareTo(o2));
		this.board.getList_pieces().add(emptyPiece);

		for (int i = 0; i < this.board.getList_pieces().size(); i++) {
			Goal+=this.board.getList_pieces().get(i);
		}
		return Goal;
	}


	public void initMatrix(Node n) {
		ArrayList<Piece> ans = new ArrayList<>();
		StringTokenizer multiTokenizer_full = new StringTokenizer(n.getData(),",");
		while (multiTokenizer_full.hasMoreTokens())
		{
			StringTokenizer multiTokenizer = new StringTokenizer(multiTokenizer_full.nextToken(),"_,");
			while (multiTokenizer.hasMoreTokens()) {
				String color = multiTokenizer.nextToken();
				int num = Integer.parseInt(multiTokenizer.nextToken());
				ans.add(new Piece(num,color));
			}

		}
		int size = 0;
		for (int i = 0; i < this.board.getN(); i++) {
			for (int j = 0; j < this.board.getM(); j++) {
				this.board.getMatBoard()[i][j] = ans.get(size);
				size++;
			}
		}



	}


	/**
	 * this function will check optional operation with this order: left -> up -> right - > down
	 * if, not find valid operation, the function will retrun null.
	 * @param n node that we need to check he's chlids.
	 * @return the node the the current state after doing operation, the data will be a string.
	 */
	public Node Operator(Node n,int index) {
		int[] ijempty = this.board.findEmptyPiece();
		int i = ijempty[0];
		int j = ijempty[1];

		if (index == 3) {
			if(Left(i,j)) {
				Piece left_piece = this.board.getMatBoard()[i][j-1];
				swap_pieces(i,j-1,i,j);
				Node ans = new_node_state(left_piece,"L");
				swap_pieces(i,j-1,i,j);
				return ans;
			}
		}
		else if(index == 4) {
			if(Up(i,j)) {
				Piece up_piece = this.board.getMatBoard()[i-1][j];
				swap_pieces(i-1,j,i,j);
				Node ans = new_node_state(up_piece,"U");
				swap_pieces(i-1,j,i,j);
				return ans;
			}
		}
		else if(index == 1) {
			if(Right(i,j)) {
				Piece right_piece = this.board.getMatBoard()[i][j+1];
				swap_pieces(i,j+1,i,j);
				Node ans = new_node_state(right_piece,"R");
				swap_pieces(i,j+1,i,j);
				return ans;
			}
		}
		else if(index == 2) {
			if(Down(i,j)) {
				Piece down_piece = this.board.getMatBoard()[i+1][j];
				swap_pieces(i+1,j,i,j);
				Node ans = new_node_state(down_piece,"D");
				swap_pieces(i+1,j,i,j);
				return ans;
			}
		}
		else if(index == 5) {
			return null;
		}
		return null;

	}	



	public boolean Left(int i, int j){
		if(j-1 >= 0 && !(this.board.getMatBoard()[i][j-1].getColor().equals("Black")))
			return true;
		return false;
	}

	public boolean Up(int i, int j){
		if(i-1>= 0 && !(this.board.getMatBoard()[i-1][j].getColor().equals("Black")))
			return true;
		return false;
	}

	public boolean Right(int i, int j){
		if(j+1 <this.board.getM() && !(this.board.getMatBoard()[i][j+1].getColor().equals("Black")))
			return true;
		return false;
	}

	public boolean Down(int i, int j){
		if(i+1<this.board.getN() && !(this.board.getMatBoard()[i+1][j].getColor().equals("Black")))
			return true;
		return false;
	}

	/**
	 * this function will swap between two pieces in the borad with the empty piece.
	 * @param piece 
	 * @param emptyPiece
	 */
	public void swap_pieces(int i , int j , int e_i , int e_j) {
		Piece temp = this.board.getMatBoard()[i][j];

		this.board.getMatBoard()[i][j] = this.board.getMatBoard()[e_i][e_j];

		this.board.getMatBoard()[e_i][e_j] = temp;

	}



	/**
	 * this function will return the new node with the current path the lead to him
	 * @param piece - the current piece that we change
	 * @param der - the deriaction from him L, U, R , D
	 * @returnn ew_node_state
	 */
	private Node new_node_state(Piece piece, String der) {
		String current_state = CreateState(); // create String of the current state
		Node new_node = new Node(current_state);
		switch (der) {
		case "L":
			new_node.path = piece.getNumber_Piece()+"R-";
			new_node.noBack = 1;
			break;
		case "U":
			new_node.path = piece.getNumber_Piece()+"D-";
			new_node.noBack = 2;
			break;
		case "R":
			new_node.path = piece.getNumber_Piece()+"L-";
			new_node.noBack = 3;
			break;
		case "D":
			new_node.path = piece.getNumber_Piece()+"U-";
			new_node.noBack = 4;
			break;
		}
		return new_node;
	}

	public int createPrice(String s) {
		int ans = 0;
		ArrayList<Integer> nodes = new ArrayList<>();
		StringTokenizer multiTokenizer = new StringTokenizer(s,"R,D,L,U-");
		while (multiTokenizer.hasMoreTokens())
		{
			nodes.add(Integer.parseInt(multiTokenizer.nextToken()));
		}
		int finalAns = 0;
		int j = 0;
		for (int i = 0; i < nodes.size(); i++) {
			for (int k = 0; k < this.board.getList_pieces().size(); k++) {
				if(nodes.get(i) == this.board.getList_pieces().get(k).getNumber_Piece()) {
					finalAns+=this.board.getList_pieces().get(k).getPrice();
				}
			}


		}
		System.out.println(finalAns);
		return finalAns;
	}

	public void countNum() {
		this.Num++;
	}

	public int getNum() {
		return Num;
	}

	public void setNum(int num) {
		Num = num;
	}

	public int getCost() {
		return Cost;
	}

	public void setCost(int cost) {
		Cost = cost;
	}




}
