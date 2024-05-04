import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Signatures {
	private String sigFile;
	private Map<String, byte[]> sigMap;
	private FileInputStream fis;

	public Signatures() {
		sigFile = "signatures.txt";
	}

	public Map<String, byte[]> getSignatures() throws IOException {
		init();
		String line;
		line = getNextLine();
		while (line != null) {
			parseLine(line);
			line = getNextLine();
		}
		close();
		return sigMap;
	}

	private void init() throws IOException {
		fis = new FileInputStream(sigFile);
		sigMap = new HashMap<String, byte[]>();
	}

	private void parseLine(String line) {
		String[] splitted = line.split(",");
		String key = splitted[0];
		String[] values = splitted[1].split(" ");
		byte[] valuesBytes = new byte[values.length - 1];
		for (int i = 1; i < values.length; i++) {
			valuesBytes[i - 1] = hexStringToByte(values[i]);
		}
		sigMap.put(key, valuesBytes);
	}

	private byte hexStringToByte(String s) {
		return (byte) ((Character.digit(s.charAt(0), 16) << 4) + (Character.digit(s.charAt(1), 16)));
	}

	private String getNextLine() throws IOException {
		int read;
		read = fis.read();
		if (read == -1)
			return null;
		String line = "";
		while (read != -1 && read != 10) {
			line += Character.toString((char) read);
			read = fis.read();
		}
		return line;
	}

	private void close() throws IOException {
		fis.close();
	}
}
