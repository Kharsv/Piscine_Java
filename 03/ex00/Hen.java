package ex00;

public class Hen extends Thread {
    int count;

    public Hen(int count) {
        this.count = count;
    }

    @Override
    public void run() {
        for (int i = 0; i < this.count; i++)
            System.out.println("HEN");
    }
}