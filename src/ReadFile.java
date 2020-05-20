import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.StringTokenizer;

public class ReadFile {

	File inputGameFile;
	Game myGame;
	ArrayList<String> values;
	ArrayList<String> tempSubString;

	public ReadFile(File input) throws IOException{
		this.inputGameFile = input;
		this.myGame = new Game();
		this.values = new ArrayList<>();
		this.tempSubString = new ArrayList<>();
		this.values = convert();
	}


	public ArrayList<String> convert() {

		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(inputGameFile));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		String st;
		int i = 0;
		try {
			while ((st = br.readLine()) != null) {
				values.add(st);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return values; 

	}

	public Game CreateGame() {
		for (int j = 0; j < this.values.size(); j++) {
			init_values(this.values,j);
		}
		ConvertToPices();
		myGame.getMyBorad().init();

		return myGame;
	}

	public void init_values(ArrayList<String> s , int i) {
		switch (i) {
		case 0: // Algoritem
			myGame.setAlgo(s.get(0));
			break;
		case 1: // with_time or no_time
			if(s.get(1).equals("with time"))
				myGame.setTime(true);
			else
				myGame.setTime(false);
			break;

		case 2: // with open or no open
			if(s.get(2).equals("with open"))
				myGame.setOpen(true);
			else
				myGame.setOpen(false);
			break;


		case 3: // NxM matrix parameters
			tempSubString = new ArrayList<>();
			tempSubString = SplitByToken(s.get(3),"x");
			myGame.getMyBorad().setN(Integer.parseInt(tempSubString.get(0)));
			myGame.getMyBorad().setM(Integer.parseInt(tempSubString.get(1)));
			break;

		case 4: // Black Numbers
			tempSubString = new ArrayList<>();
			tempSubString = SplitByToken(s.get(4),"Black:, ");
			for (String blackNum : tempSubString) {
				myGame.getBlack().add(Integer.parseInt(blackNum));
			}
			break;

		case 5: // Red numbers
			tempSubString = new ArrayList<>();
			tempSubString = SplitByToken(s.get(5),"Red:, ");
			for (String redNum : tempSubString) {
				myGame.getRed().add(Integer.parseInt(redNum));
			}
			break;

		default: // init the array with the order numbers.
			tempSubString = new ArrayList<>();
			tempSubString = SplitByToken(s.get(i),", ");
			for (String numInRow : tempSubString) {
				if (numInRow.equals("_")) {
					myGame.getNumbers().add(-1);	
				}else {
					myGame.getNumbers().add((Integer.parseInt(numInRow)));	
				}
			}
			break;
		}


	}

	public void ConvertToPices() {

		for (int j = 0; j < myGame.getNumbers().size(); j++) {
			int cur_num = myGame.getNumbers().get(j);
			if(myGame.getBlack().contains(cur_num)) {
				myGame.getMyBorad().getList_pieces().add(new Piece(cur_num,"Black"));
			}
			else if(myGame.getRed().contains(cur_num)) {
				myGame.getMyBorad().getList_pieces().add(new Piece(cur_num,"Red"));

			}
			else if(cur_num == -1) {
				myGame.getMyBorad().getList_pieces().add(new Piece(cur_num,"Empty"));


			}
			else {
				myGame.getMyBorad().getList_pieces().add(new Piece(cur_num,"Green"));

			}

		}

	}



	public ArrayList<String> SplitByToken(String s , String token) {
		ArrayList<String> ans = new ArrayList<>();
		StringTokenizer multiTokenizer = new StringTokenizer(s,token);
		while (multiTokenizer.hasMoreTokens())
		{
			ans.add(multiTokenizer.nextToken());
		}
		return ans;
	}

}