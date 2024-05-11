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
	private Boolean valid;

	public Transaction(User recipient, User sender, Category category, Integer amount) {
		this.identifier = UUID.randomUUID();
		this.recipient = recipient;
		this.setSender(sender);
		this.category = category;
		this.valid = true;
		if ((category == Category.debit && amount < 0) || 
			(category == Category.credit && amount > 0))
			this.setAmount(amount);
		else
			this.valid = false;
	}

	@Override
	public String toString() {
		if (!valid)
			return "\n{ error: invalid transaction, invalid amount }";
		if (sender == null)
			return "\n{ error: invalid transaction, user null }";
		return "\n{ amount: " + amount + ", " + "from: " + sender.getName() + 
			", " + "to: " + recipient.getName() + ", " + " }";
	}

	public UUID getUUID() {
		return identifier;
	}

	public void makeTransaction() {
		if (!valid || sender == null)
			return ;
		sender.setBalance(sender.getBalance() + amount);
		recipient.setBalance(recipient.getBalance() - amount);
	}

	private void setSender(User sender) {
		if (sender.getBalance() > 0)
			this.sender = sender;
	}

	private void setAmount(Integer amount) {
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
