public class FilesDownload {
	private final int id;
	private final String url;

	public FilesDownload(int id, String url) {
		this.id = id;
		this.url = url;
	}

	public int getId() {
		return id;
	}

	public String getUrl() {
		return url;
	}
}
