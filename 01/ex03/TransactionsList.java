package ex03;

public abstract class TransactionsList {
    abstract void add(Transaction transaction);
    abstract Transaction remove(String uuid);
    abstract Transaction[] toArray();
}
