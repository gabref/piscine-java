public class User {
	private Integer identifier;
	private String name;
	private Integer balance;

	public User(String name, Integer balance) {
		this.setName(name);
		this.setBalance(balance);
	}

	public void setIdentifier(Integer identifier) {
		this.identifier = identifier;
	}

	public Integer getIdentifier() {
		return this.identifier;
	}

	public void setName(String name) {
		this.name = name;
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
