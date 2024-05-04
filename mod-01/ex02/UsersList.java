public interface UsersList {
	public void addUser(User user);
	public User getUserById(Integer id) throws Exception;
	public User getUserByIndex(Integer idx) throws Exception;
	public Integer getNumberOfUsers();
}
