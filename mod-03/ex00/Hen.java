public class Hen implements Runnable {
	private Integer count;
	public Hen(Integer count) {
		this.count = count;
	}

	public void run() {
		for (int i = 0; i < count; i++)
			System.out.println("Hen");
	}
}
