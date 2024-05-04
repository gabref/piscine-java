import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public class Program {
	public static void main(String[] args) {
		try {
			Scanner scanner = new Scanner(System.in);
			String input = scanner.nextLine();
			scanner.close();

			Signatures sigs = new Signatures();
			SignatureChecker sc = new SignatureChecker(input);
			sc.checkSignature(sigs.getSignatures());
		} catch (IOException e) {
			System.out.println("IOException: " + e.getMessage());
		}
	}

	public static void printMap(Map<String, byte[]> map) {
		for (String key: map.keySet()) {
			String value = byteArrayToString(map.get(key));
			System.out.println(key + "\t\t\t" + value);
		}
	}

	public static String byteArrayToString(byte[] bytes) {
		StringBuilder sb = new StringBuilder();
		sb.append("[ ");
		for (byte b : bytes) {
			sb.append(String.format("%02X ", b));
			// sb.append(String.format("0x%02X ", b));
		}
		sb.append("]");

		return sb.toString();
	}
}
