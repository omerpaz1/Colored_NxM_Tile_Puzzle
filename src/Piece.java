/**
 * This class represent a piece in the borad game, this piece have: color, and position
 * @author Omer
 *
 */


public class Piece {
	
	String Color;
	int Number_Piece;
	int Price;
	int[] Place;
	
	
	public Piece(int Number_Piece,String Color) {
		this.Number_Piece = Number_Piece;
		this.Color = Color;
		this.Place = new int[2];
		
		if (Color.equals("Green")) {this.Price = 1;}
		else if (Color.equals("Red")) {this.Price = 30;}
		else {this.Price = 0;}


	}

	public int[] getPlace() {
		return Place;
	}

	public void setPlace(int i, int j) {
		int[] newarr = new int[2];
		newarr[0] = i;
		newarr[1] = j;
		this.Place = newarr;
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
	
    public int compareTo(Piece other) {
        return (this.Number_Piece - other.Number_Piece);
    }

	@Override
	public String toString() {
		return Color+"_"+Number_Piece+",";
	}
	
	
}
