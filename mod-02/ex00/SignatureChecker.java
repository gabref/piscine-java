import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

public class SignatureChecker {
	String filepath;
	String outpath;
	byte[] buffer;

	public SignatureChecker(String filepath) {
		this.filepath = filepath;
		this.outpath = "result.txt";
		buffer = new byte[100];
	}

	private void writeFile(String output) throws IOException {
		OutputStream fos = new FileOutputStream(outpath, true);
		fos.write(output.getBytes());
		fos.flush();
		fos.close();
	}

	private void readFile() throws IOException {
		InputStream fis = new FileInputStream(filepath);
		fis.read(buffer);
		fis.close();
	}

	public void checkSignature(Map<String, byte[]> sig) throws IOException {
		Boolean found = false;
		readFile();
		// System.out.println(Program.byteArrayToString(buffer));
		for (String key : sig.keySet()) {
			if (compareSigs(sig.get(key))) {
				writeFile(key + "\n");
				found = true;
				break;
			}
		}
		if (found)
			System.out.println("PROCESSED");
		else
			System.out.println("UNDEFINED");
	}

	private Boolean compareSigs(byte[] candidateSignature) {
		int i = 0;
		while (i < candidateSignature.length && i < buffer.length) {
			if (candidateSignature[i] != buffer[i])
				return false;
			i++;
		}
		if (i != candidateSignature.length)
			return false;
		return true;
	}
}
