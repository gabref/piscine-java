import java.util.UUID;

public interface TransactionsList {
	public void addTransaction(Transaction t);
	public void removeTransactionById(UUID id) throws RuntimeException;
	public Transaction[] toArray();
}
