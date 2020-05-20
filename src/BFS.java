import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Hashtable;
import java.util.concurrent.TimeUnit;

public class BFS extends Algoritem{

	public BFS(Board other_board, boolean time, boolean open)  {
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
		
		if (isTime) {
			this.startTime = System.nanoTime();
		}
		boolean valid_operation = true;
		Open_list.put(start, start.getData()); // inset the Start Node to the Queue
		Queue.add(start); // add the start to the Queue
		// have close list
		
		while (!Queue.isEmpty()) {		
			valid_operation = true;
			
			if(isOpen) {
				Hashtable<Integer, Integer>	 h1 = (Hashtable<Integer, Integer>)Open_list.clone();
				System.out.println(h1);
			}
			
			Node n = Queue.remove(); // get the front element of the queue
			Close_list.put(n, n.getData());
			this.Num++;
			if(n.getData().equals(Goal.getData())) {		
					int cost = createPrice(n.getPath());
					if(isTime) {
						this.endTime   = System.nanoTime();
						this.totalTime = endTime - startTime;
						seconds = (double)totalTime / 1_000_000_000.0;
						System.out.println(seconds);
						System.out.println(getNum());
						System.out.println(n.getPath());
					}
					CreateFile cf = new CreateFile(n.getPath(), getNum(), isTime, cost,seconds);
					return true;
				}
				else {
					initMatrix(n);

					
				}
			
			while(valid_operation) {
				int i = 1;
				while(i != 5) {
					Node temp = new Node(n);
					if(temp.getNoBack() == i) {
						i++;
						continue;
					}
					Node g = Operator(temp,i);
					if(g == null) {
						i++;
						continue;
					}
//					System.out.println("Num="+num+"| path: "+n.getPath()+g.getPath()+" state:"+g.getData());
					if (!(Close_list.contains(g.getData()))){
						Open_list.put(g, g.getData());
						g.setPath(n.getPath()+g.getPath());
						Queue.add(g);
						i++;
					}
					else {
						i++;
					}

				}
				valid_operation = false;
				break;

			}
		}
		CreateFile cf = new CreateFile(getNum());
		return false;


	}

}