import java.io.File;
import java.util.ArrayList;

public class Game {
	
	String algo; // 1
	boolean time; //2
	boolean open; // 3
	int rows; // 4
	int colums; // 4
	ArrayList<Integer> Black; // 5
	ArrayList<Integer> Red; // 6
	ArrayList<Integer> Numbers;
	Board myBorad;
	ArrayList<Piece> myPieces;
	
	

	public Game() {
		this.algo = ""; // 1
		this.time = false;
		this.open = false; 
		this.rows = 0;
		this.colums = 0;
		this.Black = new ArrayList<>();
		this.Red = new ArrayList<>();
		this.Numbers = new ArrayList<>();
		this.myBorad = new Board();
		this.myPieces = new ArrayList<>();
	}
	
	public Board getMyBorad() {return myBorad;}
	
	public void setMyBorad(Board myBorad) {this.myBorad = myBorad;}
	
	public String getAlgo() {return algo;}
	
	public void setAlgo(String algo) {this.algo = algo;}
	
	public boolean isTime() {return time;}
	
	public void setTime(boolean time) {this.time = time;}
	
	public boolean isOpen() {return open;}
	
	public void setOpen(boolean open) {this.open = open;}
	
	public int getRows() {return rows;}
	
	public void setRows(int rows) {this.rows = rows;}
	
	public int getColums() {return colums;}
	
	public void setColums(int colums) {this.colums = colums;}
	
	public ArrayList<Integer> getBlack() {return Black;}
	
	public void setBlack(ArrayList<Integer> black) {Black = black;}
	
	public ArrayList<Integer> getRed() {return Red;}
	
	public void setRed(ArrayList<Integer> red) {Red = red;}
	
	public ArrayList<Integer> getNumbers() {
		return Numbers;
	}
	public void setNumbers(ArrayList<Integer> numbers) {
		Numbers = numbers;
	}


	
    public void print2D() 
    { 
        // Loop through all rows 
        for (int i = 0; i < this.getMyBorad().getN(); i++) {
            // Loop through all elements of current row 
            for (int j = 0; j < this.getMyBorad().getM(); j++) {
            	System.out.print(this.getMyBorad().getMatBoard()[i][j].toString() + " ");
            }
        	System.out.println();
        }

    } 


}
