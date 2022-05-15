package ex01;

public class User {
    private Integer identifier;
    private String name;
    private Integer balance;

        public User( String name, Integer balance) {
        identifier = UserIdsGenerator.getInstance().generateId();
        this.name = name;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return identifier;
    }

    public Integer getBalance() {
        if(balance < 0)
            balance = 0;
        return balance;
    }



    public String toString() {
        return name + " (" + identifier + ") balance " + balance;
    }

}
