
public class BFS extends Algoritem{


	public BFS(Board other_board, boolean time, boolean open) {
		super(other_board,time,open);
		String S = CreateState();
		Node start = new Node(S);
		String G = CreateGoal();
		Node goal = new Node(G);
//		System.out.println(goal.getData());


		Run_BFS(start,goal);
		// TODO Auto-generated constructor stub
	}

	private boolean Run_BFS(Node start,Node Goal) {
		boolean valid_operation = true;
		Open_list.put(start, start.getData()); // inset the Start Node to the Queue
		Queue.add(start); // add the start to the Queue
		// have close list
		while (!Queue.isEmpty()) {		
			valid_operation = true;
			Node n = Queue.poll(); // get the front element of the queue.
			Close_list.put(n, n.getData());
			

			System.out.println(n.getData());
			initMatrix(n);
			System.out.println("****");
			this.board.printMat();
			System.out.println("****");
			System.out.println("emtpyplace ="+this.board.getEmptyPiece().getPlace()[0]+","+this.board.getEmptyPiece().getPlace()[1]);

			while(valid_operation) {
				int i = 1;
				while(i != 5) {
					Node temp = new Node(n);
					Node g = Operator(temp,i);
					if(g == null) {
						i++;
						continue;
					}
					if (!(Open_list.contains(g.getData())) && !(Close_list.contains(g.getData()))){


						if(g.getData().equals(Goal.getData())) {
							return true;
						}
						else {
							Open_list.put(g, g.getData());
							g.setPath(n.getPath()+g.getPath());
							Queue.add(g);
							i++;
						}
					}
					else {
						i++;
					}

				}
				valid_operation = false;
				break;

			}
		}
		return false;


	}

}