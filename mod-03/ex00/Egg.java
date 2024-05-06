public class Egg implements Runnable {
	private Integer count;
	public Egg(Integer count) {
		this.count = count;
	}

	public void run() {
		for (int i = 0; i < count; i++)
			System.out.println("Egg");
	}
}
