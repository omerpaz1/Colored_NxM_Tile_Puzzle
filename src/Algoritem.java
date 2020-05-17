import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Algoritem {
	
	Boolean isTime;
	Boolean isOpen;
	Board board;
	
	public Algoritem(Board other_board, boolean time, boolean open) {
		this.isTime = time;
		this.isOpen = open;
		this.board = other_board;
	}

	public String CreateState() {
		String State = "[";
		
		for (int i = 0; i < this.board.getN(); i++) {
			for (int j = 0; j < this.board.getM(); j++) {
				State+=this.board.getMatBoard()[i][j];
			}
		}
		State+="]";
		return State;
	}
	
	public String CreateGoal() {
		String Goal = "[";
		Piece emptyPiece = this.board.getEmptyPiece();
		int size_board = this.board.getN()*this.board.getM();
		this.board.getList_pieces().remove(emptyPiece);
		
		Collections.sort(this.board.getList_pieces(), 
                (o1, o2) -> o1.compareTo(o2));
		this.board.getList_pieces().add(emptyPiece);
		
		/// create String of the goal from the boardlist
		return Goal;
	}

//	public hasLeft()
	
	
	

}
