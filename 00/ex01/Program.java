package ex01;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        int i = 0;
        float summ = 0;
        float num = 2;
        int prime = 1;
        int k;

        Scanner input = new Scanner(System.in);
        System.out.print("-> ");
        k = 2;
        if (!input.hasNextInt()) {
            System.err.println("Illegal Argument");
            System.exit(-1);
        }
//        try {
            k = input.nextInt();
//        } catch (Exception e) {
//            System.err.println("Illegal Argument");
//            System.exit(-1);
//        } finally {
////            System.out.println("Finally");
//        }
        if (k <= 1) {
            System.err.println("Illegal Argument");
            System.exit(-1);
        }
        num = 2;
        while (num < (float) Math.sqrt(k)) {
            summ = k % num;
            if (summ == 0)
                prime = 0;
            num++;
            i++;
        }
        if (prime == 0)
            System.out.print("False ");
        else
            System.out.print("True ");
        System.out.println(i);
        return;
    }
}
