import java.util.Arrays;

public class Program {
	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.println("Error! No flags passed");
			System.err.println("Usage: <java Program --count=50>");
			System.exit(1);
		}
		String[] flagValue = args[0].split("=");
		if (!flagValue[0].equals("--count")) {
			System.err.println("Error! Wrong flag");
			System.err.println("Usage: <java Program --count=50>");
			System.exit(1);
		}
		try {
			Integer count = Integer.parseInt(flagValue[1]);
			Egg egg = new Egg(count);
			Hen hen = new Hen(count);
			Thread eggThread = new Thread(egg);
			Thread henThread = new Thread(hen);
			eggThread.start();
			henThread.start();
			eggThread.join();
			henThread.join();

			for (int i = 0; i < count; i++)
				System.out.println("Human");
		} catch (ArrayIndexOutOfBoundsException e) {
			System.err.println("Error: " + e.getMessage());
		} catch (NumberFormatException e) {
			System.err.println("Error: " + e.getMessage());
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
	}
}
