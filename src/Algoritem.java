
public class Algoritem {
	
	Boolean isTime;
	Boolean isOpen;
	Board Algo_board;
	
	public Algoritem(Board other_board, boolean time, boolean open) {
		this.isTime = time;
		this.isOpen = open;
		this.Algo_board = new Board(other_board);
	}

	public Boolean getIsOpen() {
		return isOpen;
	}

	public void setIsOpen(Boolean isOpen) {
		this.isOpen = isOpen;
	}

	public Board getAlgo_board() {
		return Algo_board;
	}

	public void setAlgo_board(Board algo_board) {
		Algo_board = algo_board;
	}

	public Boolean getIsTime() {
		return isTime;
	}

	public void setIsTime(Boolean isTime) {
		this.isTime = isTime;
	}
	

}
