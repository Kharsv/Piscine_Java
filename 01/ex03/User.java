package ex03;

public class User {
    private Integer identifier;
    private String name;
    private Integer balance;
    private TransactionsList list;

    public User( String name, Integer balance) {
        identifier = UserIdsGenerator.getInstance().generateId();
        this.name = name;
        this.balance = balance;
    }

    public void setTransactionsList(TransactionsList list) {
        this.list = list;
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return identifier;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public String toString() {
        return name + " (" + identifier + ") balance" + balance;
    }
}
