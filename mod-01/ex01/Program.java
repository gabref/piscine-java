public class Program {
	public static void main(String[] args) {
		System.out.println("Create User galves-f");
         User var1 = new User("galves-f", -100);
         User var2 = new User("naruto", 800);
         User var3 = new User("minato", 700);

		printUser(var1);
		printUser(var2);
		printUser(var3);

         Transaction t1 = new Transaction(var2, var3, Transaction.Category.debit, -120);
         Transaction t2 = new Transaction(var2, var1, Transaction.Category.credit, 100);
         Transaction t3 = new Transaction(var3, var2, Transaction.Category.credit, 50);
         Transaction t4 = new Transaction(var3, var1, Transaction.Category.credit, 77);
         Transaction t5 = new Transaction(var1, var2, Transaction.Category.debit, -100);
         Transaction t6 = new Transaction(var1, var3, Transaction.Category.debit, -30);
         t1.makeTransaction();
         t2.makeTransaction();
         t3.makeTransaction();
         t4.makeTransaction();
         t5.makeTransaction();
         t6.makeTransaction();

		 printTransaction(t1);
		 printTransaction(t2);
		 printTransaction(t3);
		 printTransaction(t4);
		 printTransaction(t5);
		 printTransaction(t6);

		printUser(var1);
		printUser(var2);
		printUser(var3);
	}

	static void printUser(User user) {
		System.out.println(user.getIdentifier() + " - " + user.getName() + " has\t$" + user.getBalance() + " in the bank");
	}

	static void printTransaction(Transaction t) {
		if (!t.isValid())
		{
			System.out.println("\nInvalid transaction");
			return ;
		}
		if (t.getSender() == null)
		{
			System.out.println("\nInvalid user (negative balance)");
			return ;
		}
		System.out.println("\nTransaction: ");
		System.out.println("-> from:\t" + t.getSender().getIdentifier() + " - " + t.getSender().getName());
		System.out.println("-> to:\t\t" +  t.getRecipient().getIdentifier() + " - " + t.getRecipient().getName());
		System.out.println("-> value:\t$ " + t.getAmount());
	}
}
