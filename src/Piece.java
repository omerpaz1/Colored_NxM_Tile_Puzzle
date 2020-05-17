/**
 * This class represent a piece in the borad game, this piece have: color, and position
 * @author Omer
 *
 */


public class Piece {
	
	String Color;
	int Number_Piece;
	int Price;
	
	
	public Piece(int Number_Piece,String Color) {
		this.Number_Piece = Number_Piece;
		this.Color = Color;
		if (Color.equals("Green")) {this.Price = 1;}
		else if (Color.equals("Red")) {this.Price = 30;}
		else this.Price = 0;


	}


	public String getColor() {
		return Color;
	}


	public void setColor(String color) {
		Color = color;
	}


	public int getNumber_Piece() {
		return Number_Piece;
	}


	public void setNumber_Piece(int number_Piece) {
		Number_Piece = number_Piece;
	}


	public int getPrice() {
		return Price;
	}


	public void setPrice(int price) {
		Price = price;
	}


	@Override
	public String toString() {
		return "("+Color+ ","+Number_Piece+")";
	}
	
}
