public class UsersArrayList implements UsersList {
	private User[] list;
	private Integer capacity;
	private Integer size;
	private final Integer initialCapacity = 10;
	public UsersArrayList() {
		list = new User[initialCapacity];
		capacity = initialCapacity;
		size = 0;
	}

	public void addUser(User user) {
		if (size == capacity) {
			capacity += 10;
			User[] tempList = new User[capacity];
			for (int i = 0; i < size; i++)
				tempList[i] = list[i];
			list = tempList;
		}
		list[size++] = user;
	}

	public User getUserById(Integer id) throws Exception {
		User u = null;
		for (int i = 0; i < size; i++)
			if (list[i].getIdentifier() == id)
				u = list[i];
		if (u == null)
			throw new UserNotFoundException("User with ID " + id + " not found");
		return u;
	}

	public User getUserByIndex(Integer idx) throws Exception {
		if (idx >= size || idx < 0)
			throw new UserNotFoundException("Index out of bounds");
		return list[idx];
	}

	public Integer getNumberOfUsers() {
		return size;
	}
}

class UserNotFoundException extends Exception {
	public UserNotFoundException(String message) {
		super(message);
	}
}
