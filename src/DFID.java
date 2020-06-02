import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class DFID extends Algoritem{

	Hashtable<Node, String> loopAvoidance;
	Queue<Node> Queue;
	Board theBoard;


	public DFID(Board other_board, boolean time, boolean open) {
		super(other_board, time, open);
		Board theBoard = new Board(this.board);
		String S = CreateState(theBoard.getMatBoard());
		String G = CreateGoal();
		Node start = new Node(S);
		Node goal = new Node(G);
		this.Queue = new LinkedList<Node>();


		DFID(start,goal,theBoard);

	}

	private boolean DFID(Node start, Node goal,Board theBoard) {
		if (isTime) {
			this.startTime = System.nanoTime();
		}
		
		for (int depth = 1; depth < 1000; depth++) {
			this.loopAvoidance = new Hashtable<Node, String>(); 
			Node result = Limited_DFS(start,goal,depth,this.loopAvoidance,theBoard.getMatBoard(),"");
			if (!result.isCutoff()) {
				if(result.getData().equals(goal.getData())) {
					int cost = createPrice(result.getPath());
					if(isTime) {
						this.endTime   = System.nanoTime();
						this.totalTime = endTime - startTime;
						seconds = (double)totalTime / 1_000_000_000.0;
						result.setPath(result.getPath().substring(0,result.getPath().length()-1));	
					}
					CreateFile cf = new CreateFile(result.getPath(), getNum(), isTime, cost,seconds);
					return true;
				}
			}
		}
		CreateFile cf = new CreateFile(getNum());
		return false;

	}

	private Node Limited_DFS(Node n, Node goal, int limit, Hashtable<Node, String> loopAvoidance,Piece[][] matBoard,String path) {
		path+=n.getPath();
		if(n.getData().equals(goal.getData())) {
			this.Num++;
			n.setPath(path);
			n.setCutoff(false); 
			return n;

		}
		else {
			if (limit == 0) {
				n.setCutoff(true);
				return n;
			}
			loopAvoidance.put(n, n.getData());
			boolean isCutOff = false;

			for (int i = 1 ;i <=5 ; i++) {
				Node temp = new Node(n);
				if(temp.getNoBack() == i) {
					continue;
				}
				Node g = Operator(temp,i,matBoard);
				if(g == null) {
					continue;
				}
				if(loopAvoidance.contains(g.getData())) {
					continue;
				}
				Node result = Limited_DFS(g,goal,limit-1,this.loopAvoidance,MyinitMatrix(g,(new Piece[matBoard.length][matBoard[0].length])),path);
				this.Num++;

				if (result.isCutoff()) {
					isCutOff = true;
				}
				else if(!result.isCutoff()) {
					return result;
				}
			}
			
			MyinitMatrix(n,matBoard);
			loopAvoidance.remove(n);

			if (isCutOff) {
				n.setCutoff(true);
				return n; // 
			}
			else {
				n.setCutoff(false);
				return n;	

			}
		}
	}
	
	public Piece[][] MyinitMatrix(Node n, Piece[][] BoardPiece) {
		ArrayList<Piece> ans = new ArrayList<>();
		StringTokenizer multiTokenizer_full = new StringTokenizer(n.getData(),",");
		while (multiTokenizer_full.hasMoreTokens())
		{
			StringTokenizer multiTokenizer = new StringTokenizer(multiTokenizer_full.nextToken(),"_,");
			while (multiTokenizer.hasMoreTokens()) {
				String color = multiTokenizer.nextToken();
				int num = Integer.parseInt(multiTokenizer.nextToken());
				ans.add(new Piece(num,color));
			}

		}
		int size = 0;
		for (int i = 0; i < this.board.getN(); i++) {
			for (int j = 0; j < this.board.getM(); j++) {
				BoardPiece[i][j] = ans.get(size);
				size++;
			}
		}

		return BoardPiece;

	}
	


}
