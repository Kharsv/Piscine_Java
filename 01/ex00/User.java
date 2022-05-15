package ex00;

public class User {
    private Integer identifier;
    private String name;
    private Integer balance;

    public User( Integer identifier, String name, Integer balance) {
        this.name = name;
        this.balance = balance;
        this.identifier = identifier;
        System.out.println("User: " +name+" with amount "+balance+" added");
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public Integer getBalance() {
        if(balance < 0)
            balance = 0;
        return balance;
    }
}
