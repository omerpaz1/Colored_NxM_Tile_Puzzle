import java.util.ArrayList;

/**
 * 
 * @author Omer
 * This Borad Class represent The Borad Game of the tile-puzzle.
 * this object will have Pieces objects in the borad.
 * 
 */



public class Board {
	private int N; // size of Rows
	private int M; // size of Colums
	private ArrayList<Piece> List_pieces;
	private Piece[][] MatBoard;

	public Board() {
		this.N = 0;
		this.M = 0;
		this.List_pieces = new ArrayList<Piece>();
		
	}
	
	public Board(Board other_board) {
		this.N = other_board.N;
		this.M = other_board.M;
		this.List_pieces = other_board.List_pieces;
		this.MatBoard = other_board.MatBoard;
		
	}
	
	public int getN() {
		return N;
	}


	public void setN(int n) {
		N = n;
	}


	public int getM() {
		return M;
	}


	public void setM(int m) {
		M = m;
	}


	public ArrayList<Piece> getList_pieces() {
		return List_pieces;
	}




	public void setList_pieces(ArrayList<Piece> list_pieces) {
		List_pieces = list_pieces;
	}




	public Piece[][] getMatBoard() {
		return MatBoard;
	}




	public void setMatBoard(Piece[][] matBoard) {
		MatBoard = matBoard;
	}

	public void init() {
		int Piece_index = 0;
		this.MatBoard = new Piece[N][M];
		for (int i = 0; i < this.N; i++) {
			for (int j = 0; j < this.M; j++) {
				this.MatBoard[i][j] = this.List_pieces.get(Piece_index);
				Piece_index++;
			}
		}
	}

}
