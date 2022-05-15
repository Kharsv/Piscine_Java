package ex00;

import java.util.UUID;

public class Transaction {
    private UUID identifier;
    private User recipient;
    private User sender;
    private Type transferCategory;
    private Integer amount;

    enum Type {
        CREDIT,
        DEBIT,
    }

    public Transaction( User recipient, User sender, Integer amount) {
        this.recipient = recipient;
        this.sender = sender;

        this.amount = amount;
        if ( amount >= 0)
            this.transferCategory = transferCategory.DEBIT;
        else
            this.transferCategory = transferCategory.CREDIT;
        identifier = UUID.randomUUID();
        System.out.println("Transaction from  "+sender.getName()+" to " +recipient.getName() +" Type="+transferCategory+" with amount "+amount+ " id = "+ identifier);
        sender.setBalance(sender.getBalance() - amount);
        recipient.setBalance(recipient.getBalance() + amount);
    }

    public static Transaction createTransaction(User recipient, User sender, Type transferCategory, Integer amount) {
        System.out.println("Try create transaction from  "+sender.getName()+" to " +recipient.getName() +" with amount "+amount +" "+ transferCategory);
        if ((transferCategory == Type.CREDIT && amount < 0 && recipient.getBalance() >= -amount)
                || (transferCategory == Type.DEBIT && amount > 0 && sender.getBalance() >= amount)) {
            return new Transaction(recipient, sender, amount);
        }
        else
            System.out.println("Transaction cannot be created");
        return null;
    }
    public String toString() {
        return String.format("%s -> %s, %s %s, %s, %s", sender.getName(), recipient.getName(), transferCategory, amount, transferCategory, identifier);
    }
}
