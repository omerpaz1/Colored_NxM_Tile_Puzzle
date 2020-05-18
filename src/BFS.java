
public class BFS extends Algoritem{


	public BFS(Board other_board, boolean time, boolean open) {
		super(other_board,time,open);
		String S = CreateState();
		Node start = new Node(S);
		String G = CreateGoal();
		Node goal = new Node(G);


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

			while(valid_operation) {
				int i = 1;
				while(i != 5) {
					Node g = Operator(n,i);
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
							Queue.add(g);
							i=1;
							System.out.println("****");
							this.board.printMat();
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