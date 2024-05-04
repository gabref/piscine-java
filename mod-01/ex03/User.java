public class User {
	private Integer identifier;
	private String name;
	private Integer balance;

	public User(String name, Integer balance) {
		this.name = name;
		this.setBalance(balance);
		this.identifier = UserIdsGenerator.getInstance().generateId();
	}

	public Integer getIdentifier() {
		return this.identifier;
	}

	public String getName() {
		return this.name;
	}

	public void setBalance(Integer balance) {
		if (balance < 0)
			this.balance = 0;
		else
			this.balance = balance;
	}

	public Integer getBalance() {
		return this.balance;
	}
}
