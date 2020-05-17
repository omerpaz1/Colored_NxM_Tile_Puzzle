/**
 * This class represent a piece in the borad game, this piece have: color, and position
 * @author Omer
 *
 */


public class Piece {
	
	String Color;
	int Number_Piece;
	
	
	public Piece(int Number_Piece,String Color) {
		this.Number_Piece = Number_Piece;
		this.Color = Color;
	}


	@Override
	public String toString() {
		return "("+Color+ ","+Number_Piece+")";
	}
	
}
