import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class UrlFiles {
	private FileInputStream fis;
	private String filepath;

	UrlFiles(String filepath) {
		this.filepath = filepath;
	}

	public void init() throws FileNotFoundException {
		fis = new FileInputStream(filepath);
	}

	public void close() throws IOException {
		fis.close();
	}

	public String getNextLine() throws IOException {
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
}
