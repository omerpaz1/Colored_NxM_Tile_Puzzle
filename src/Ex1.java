// Java Program to illustrate reading from FileReader 
// using BufferedReader 
import java.io.*; 
public class Ex1 
{ 
	public static void main(String[] args)throws Exception 
	{ 
		// We need to provide file path as the parameter: 
		// double backquote is to avoid compiler interpret words 
		// like \test as \t (ie. as a escape sequence) 
		File input = new File("src/input.txt"); 
		ReadFile GameInput = new ReadFile(input);
		Game myGame = GameInput.CreateGame();
//		myGame.print2D();
		
		switch (myGame.getAlgo()) {
		case "BFS":
			Algoritem BFS = new BFS(myGame.getMyBorad(),myGame.isTime(),myGame.isOpen());
			break;
		case "DFID":
			Algoritem DFID = new DFID(myGame.getMyBorad(),myGame.isTime(),myGame.isOpen());
		break;
		case "A*":
			Algoritem AStar = new AStar(myGame.getMyBorad(),myGame.isTime(),myGame.isOpen());
		break;
		case "IDA*":
			Algoritem IDAStar = new IDAStar(myGame.getMyBorad(),myGame.isTime(),myGame.isOpen());
		break;
		case "DFBnB":
			Algoritem DFBnB = new DFBnB(myGame.getMyBorad(),myGame.isTime(),myGame.isOpen());
		break;

		default:
			break;
		}
		
	}
}