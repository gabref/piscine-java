import java.util.UUID;

public class Transaction {
	public enum Category {
		debit,
		credit
	}

	private UUID identifier;
	private User recipient;
	private User sender;
	private Category category;
	private Integer amount;

	public Transaction(User recipient, User sender, Category category, Integer amount) {
		this.recipient = recipient;
		this.setSender(sender);
		this.category = category;
		this.setAmount(amount);
	}

	private void setSender(User sender) {
		if (sender.getBalance() > 0)
			this.sender = sender;
	}

	private void setAmount(Integer amount) {
		if (amount < 0)
			this.amount = amount;
	}

	public Integer getAmount() {
		return this.amount;
	}

	public User getRecipient() {
		return this.recipient;
	}

	public User getSender() {
		return this.sender;
	}
}
