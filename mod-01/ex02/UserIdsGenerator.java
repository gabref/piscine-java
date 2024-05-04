public class UserIdsGenerator {
	private Integer lastId = 42;
	private static UserIdsGenerator instance = null;

	private UserIdsGenerator() {
	}

	public static UserIdsGenerator getInstance() {
		if (instance == null)
			instance = new UserIdsGenerator();
		return instance;
	}

	public Integer generateId() {
		return this.lastId++;
	}
}
