package ex03;

public class Program {
    public static void main(String[] args) {
        User user1 = new User("John", 1100);
        User user2 = new User("Mike", 1200);
        User user3 = new User("Phil", -100);

        System.out.printf("%s %d \n", user1.getName(), user1.getId());
        System.out.printf("%s %d \n", user2.getName(), user2.getId());
        System.out.printf("%s %d \n", user3.getName(), user3.getId());
        TransactionsList list = new TransactionsLinkedList();
        user1.setTransactionsList(list);
        user2.setTransactionsList(list);
        user3.setTransactionsList(list);
        Transaction transaction1 = Transaction.createTransaction(user2, user1, Transaction.Type.DEBIT, 500);
        Transaction transaction2 = Transaction.createTransaction(user2, user1, Transaction.Type.CREDIT, -300);
        Transaction transaction3 = Transaction.createTransaction(user2, user1, Transaction.Type.CREDIT, -200);
        System.out.println(transaction1);
        list.add(transaction1);
        list.add(transaction2);
        list.add(transaction3);


        Transaction transaction4 = Transaction.createTransaction(user2, user1, Transaction.Type.CREDIT, -120);
        System.out.println(transaction4);

        System.out.println("-------------------");
        System.out.println("Transactions");
        for (Transaction t : list.toArray()) {
            System.out.println(t);
        }
        System.out.printf("%s %d\n", user1.getName(), user1.getBalance());
        System.out.printf("%s %d\n", user2.getName(), user2.getBalance());
        System.out.println("-------------------");
        System.out.println("delete");
        System.out.println(list.remove(transaction2.getIdentifier().toString()));
        System.out.println("-------------------");
        System.out.println("after");
        for (Transaction t : list.toArray()) {
            System.out.println(t);
        }
        System.out.printf("%s %d\n", user1.getName(), user1.getBalance());
        System.out.printf("%s %d\n", user2.getName(), user2.getBalance());
        System.out.println("-------------------");
       // list.remove("");
    }
}
