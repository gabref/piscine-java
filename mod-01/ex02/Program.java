public class Program {
	public static void main(String[] args) {
		try {
			System.out.println("Create User galves-f");
			User galves = new User("galves-f", -100);
			User naruto = new User("naruto", 200);
			User minato = new User("minato", 700);

			User[] l = {galves, naruto, minato};

			UsersArrayList list = new UsersArrayList();

			int max = 22;
			for (int i = 0; i < max; i++)
				list.addUser(l[i % l.length]);

			System.out.println("\nList:");
			System.out.println("Users:\t" + list.getNumberOfUsers());
			System.out.println("Users[9]:\t" + printUserOneline(list.getUserByIndex(9)));
			System.out.println("Users[10]:\t" + printUserOneline(list.getUserByIndex(10)));
			System.out.println("Users[11]:\t" + printUserOneline(list.getUserByIndex(11)));
			System.out.println("User.id = 44\t" + printUserOneline(list.getUserById(44)));
			System.out.println("User.id = 47\t" + printUserOneline(list.getUserById(47)));

		} catch (Exception e) {
			System.out.println("An Exception occurred\n========================");
			System.out.println(e.getMessage());
		}
	}

	static String printUserOneline(User user) {
		return user.getIdentifier() + " - " + user.getName();
	}

	static void printUser(User user) {
		System.out.println(user.getIdentifier() + " - " + user.getName() + " has\t$" + user.getBalance() + " in the bank");
	}

	static void printTransaction(Transaction t) {
		System.out.println("\nTransaction: ");
		System.out.println("-> from:\t" + t.getSender().getIdentifier() + " - " + t.getSender().getName());
		System.out.println("-> to:\t\t" +  t.getRecipient().getIdentifier() + " - " + t.getRecipient().getName());
		System.out.println("-> value:\t$ " + t.getAmount());
	}
}
