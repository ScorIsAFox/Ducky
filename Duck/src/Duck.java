
public class Duck {

	public static void main(String[] args) {
		DuckMove ducky = new DuckMove();
		ducky.duckAppear();
		boolean walk = true;
		while (walk) {
			ducky.duckMove();
		}
	}

}
