public class Program {
	public static void main(String[] args) {
		System.out.println("Create User galves-f");
		User galves = new User("galves-f", -100);
		User naruto = new User("naruto", 200);
		User minato = new User("minato", 700);

		printUser(galves);
		printUser(naruto);
		printUser(minato);

		Transaction t1 = new Transaction(galves, minato, Transaction.Category.debit, 12);
		Transaction t2 = new Transaction(naruto, minato, Transaction.Category.debit, -12);
		printTransaction(t1);
		printTransaction(t2);
	}

	static void printUser(User user) {
		System.out.println(user.getName() + " has\t$" + user.getBalance() + " in the bank");
	}

	static void printTransaction(Transaction t) {
		System.out.println("\nTransaction: ");
		System.out.println("-> from:\t" + t.getSender().getName());
		System.out.println("-> to:\t\t" + t.getRecipient().getName());
		System.out.println("-> value:\t$ " + t.getAmount());
	}
}
