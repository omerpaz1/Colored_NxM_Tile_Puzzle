import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Algoritem {

	Boolean isTime;
	Boolean isOpen;
	Board board;

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


	/**
	 * this function will check optional operation with this order: left -> up -> right - > down
	 * if, not find valid operation, the function will retrun null.
	 * @param n node that we need to check he's chlids.
	 * @return the node the the current state after doing operation, the data will be a string.
	 */
	public Node Operator(Node n,int index) {
		Piece emptyPiece = this.board.getEmptyPiece();
		int i = emptyPiece.getPlace()[0];
		int j = emptyPiece.getPlace()[1];

		if (index == 1) {
			if(Left(i,j,emptyPiece)) {
				Piece left_piece = this.board.getMatBoard()[i][j-1];
				swap_pieces(left_piece,emptyPiece);
				return new_node_state(left_piece,"L");
			}
		}
		else if(index == 2) {
			if(Up(i,j,emptyPiece)) {
				Piece up_piece = this.board.getMatBoard()[i-1][j];
				swap_pieces(up_piece,emptyPiece);
				return new_node_state(up_piece,"U");
			}
		}
		else if(index == 3) {
			if(Right(i,j,emptyPiece)) {
				Piece right_piece = this.board.getMatBoard()[i][j+1];
				swap_pieces(right_piece,emptyPiece);
				return new_node_state(right_piece,"R");
			}
		}
		else if(index == 4) {
			if(Down(i,j,emptyPiece)) {
				Piece down_piece = this.board.getMatBoard()[i+1][j];
				swap_pieces(down_piece,emptyPiece);
				return new_node_state(down_piece,"D");
			}
		}
		else if(index == 5) {
			return null;
		}
		return null;

	}	



	public boolean Left(int i, int j , Piece p){
		if(j-1 >= 0 && !(this.board.getMatBoard()[i][j-1].getColor().equals("Black")))
			return true;
		return false;
	}

	public boolean Up(int i, int j , Piece p){
		if(i-1>= 0 && !(this.board.getMatBoard()[i-1][j].getColor().equals("Black")))
			return true;
		return false;
	}

	public boolean Right(int i, int j , Piece p){
		if(j+1<this.board.getM() && !(this.board.getMatBoard()[i][j+1].getColor().equals("Black")))
			return true;
		return false;
	}

	public boolean Down(int i, int j , Piece p){
		if(i+1<this.board.getN() && !(this.board.getMatBoard()[i+1][j].getColor().equals("Black")))
			return true;
		return false;
	}

	/**
	 * this function will swap between two pieces in the borad with the empty piece.
	 * @param piece 
	 * @param emptyPiece
	 */

	private void swap_pieces(Piece piece, Piece emptyPiece) {

		int l_i = piece.getPlace()[0];
		int l_j = piece.getPlace()[1];

		int e_i = emptyPiece.getPlace()[0];
		int e_j = emptyPiece.getPlace()[1];

		Piece temp = this.board.getMatBoard()[l_i][l_j];

		this.board.getMatBoard()[l_i][l_j].setPlace(e_i, e_j);
		this.board.getMatBoard()[l_i][l_j] = this.board.getMatBoard()[e_i][e_j];

		this.board.getMatBoard()[e_i][e_j].setPlace(l_i, l_j);
		this.board.getMatBoard()[e_i][e_j] = temp;

		this.board.getEmptyPiece().setPlace(l_i, l_j);




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
			new_node.path = piece.getNumber_Piece()+"L-";
			break;
		case "U":
			new_node.path = piece.getNumber_Piece()+"U-";
			break;
		case "R":
			new_node.path = piece.getNumber_Piece()+"R-";
			break;
		case "D":
			new_node.path = piece.getNumber_Piece()+"D";
			break;
		}
		return new_node;
	}



	//
	//		hasRight(int i,int j, mat){
	//		return j+1<mat[i][j].lenght;
	//		}
	//
	//		hasUp(int i,int j, mat){
	//		return i-1>0;
	//		}
	//
	//
	//		hasDown(int i,int j, mat){
	//		return i+1<mat[i][j].lenght;
	//		}



	//	public hasLeft()




}
