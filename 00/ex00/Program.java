package ex00;

public class Program {
    public static void main(String[] args) {
        int summ = 0;
        int num;
        int k = 125504;
        num = k % 10;
        summ = summ + num;
        k = (k - num)/10;
        num = k % 10;
        summ = summ + num;
        k = (k - num)/10;
        num = k % 10;
        summ = summ + num;
        k = (k - num)/10;
        num = k % 10;
        summ = summ + num;
        k = (k - num)/10;
        num = k % 10;
        summ = summ + num;
        k = (k - num)/10;
        num = k % 10;
        summ = summ + num;
        k = (k - num)/10;
        System.out.println(summ);
    }
}