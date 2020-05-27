import java.util.ArrayList;
import java.util.Hashtable;

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
	private Hashtable<Integer,Integer> pieces_prices;
	private Piece[][] MatBoard;
	private Piece EmptyPiece;
	
	public Board() {
		this.N = 0;
		this.M = 0;
		this.List_pieces = new ArrayList<Piece>();
		this.EmptyPiece = new Piece(-1,"Empty");
		
	}
	
	public Piece getEmptyPiece() {
		return EmptyPiece;
	}

	public void setEmptyPiece(Piece emptyPiece) {
		EmptyPiece = emptyPiece;
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

	public Hashtable<Integer, Integer> getPieces_prices() {
		// TODO Auto-generated method stub
		return pieces_prices;
	}

	public void setPieces_prices(Hashtable<Integer, Integer> pieces_prices) {
		this.pieces_prices = pieces_prices;
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
				Piece is_empty = this.List_pieces.get(Piece_index);
				if (is_empty.getNumber_Piece() == -1) {
					this.setEmptyPiece(is_empty);
					this.getEmptyPiece().getPlace()[0] = i;
					this.getEmptyPiece().getPlace()[1] = j;

				}
				this.MatBoard[i][j] = this.List_pieces.get(Piece_index);
				int[] spot = new int[2];
				spot[0] = i;
				spot[1] = j;
				this.List_pieces.get(Piece_index).getPlace()[0] =i;
				this.List_pieces.get(Piece_index).getPlace()[1] =j;
				Piece_index++;
			}
		}
	}
	
	
    public void printMat() 
    { 
        // Loop through all rows 
        for (int i = 0; i < N; i++) {
            // Loop through all elements of current row 
            for (int j = 0; j < M; j++) {
            	System.out.print("("+this.MatBoard[i][j].toString()+")");
            }
        	System.out.println();
        }

    }




}
