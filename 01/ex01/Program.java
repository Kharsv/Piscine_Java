package ex01;

public class Program {
    public static void main(String[] args) {
        User user1 = new User("John", 100);
        User user2 = new User("Mike", 200);
        User user3 = new User("Phil", -100);

        System.out.printf("%s %d \n", user1.getName(), user1.getId());
        System.out.printf("%s %d \n", user2.getName(), user2.getId());
        System.out.printf("%s %d %d \n", user3.getName(), user3.getId(), user3.getBalance() );
        System.out.println(user1);
        System.out.println(user2);
        System.out.println(user3);
    }
}
