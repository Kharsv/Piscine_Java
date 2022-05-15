package ex00;
public class Program {
    public static void main(String[] args) {
        User user1 = new User(1,"John", 10000);
        User user2 = new User(2,"Mike", 100000);
        User user3 = new User(3,"Minus", -100000);

        System.out.printf("%s %d\n", user1.getName(), user1.getBalance());
        System.out.printf("%s %d\n", user2.getName(), user2.getBalance());

        Transaction transaction1 = Transaction.createTransaction(user2, user1, Transaction.Type.CREDIT, -500);
        System.out.println(transaction1);
        System.out.printf("%s %d\n", user1.getName(), user1.getBalance());
        System.out.printf("%s %d\n", user2.getName(), user2.getBalance());
        System.out.printf("%s %d\n", user3.getName(), user3.getBalance());
        Transaction transaction2 = Transaction.createTransaction(user2, user1, Transaction.Type.DEBIT, 500);
        System.out.println(transaction2);
        System.out.printf("%s %d\n", user1.getName(), user1.getBalance());
        System.out.printf("%s %d\n", user2.getName(), user2.getBalance());

        Transaction transaction3 = Transaction.createTransaction(user2, user1, Transaction.Type.DEBIT, 12000);
        System.out.println(transaction3);
        Transaction transaction4 = Transaction.createTransaction(user2, user1, Transaction.Type.CREDIT, -12000);
        System.out.println(transaction4);
        System.out.printf("%s %d\n", user1.getName(), user1.getBalance());
        System.out.printf("%s %d\n", user2.getName(), user2.getBalance());
    }
}
