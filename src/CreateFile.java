import java.io.PrintWriter;

public class CreateFile {
	String Path; // 1
	int Num; // 2
	boolean time;
	int Cost; // 3
	String runtime;

	public CreateFile(String path, int num, boolean time, int cost, double runtime) {
		super();
		Path = path;
		Num = num;
		this.time = time;
		Cost = cost;
		this.runtime = runtime+"";

		init_file();
	}

	public CreateFile(int num) {
		super();
		Num = num;
		init_file_no_sovled();
	}

	private void init_file_no_sovled() {
		try {
			PrintWriter writer = new PrintWriter("output.txt", "UTF-8");
			writer.println("no path");
			writer.println("Num: "+this.Num+"");

			writer.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}		


	private void init_file() {

		try {
			PrintWriter writer = new PrintWriter("output.txt", "UTF-8");
			writer.println(this.Path);
			writer.println("Num: "+this.Num+"");
			writer.println("Cost: "+this.Cost+"");
			if(time) {
				writer.println(this.runtime+" seconds");
			}

			writer.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}



}
