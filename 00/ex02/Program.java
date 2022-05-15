package ex02;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        int k;
        int coffeeRequests = 0;
        Scanner input = new Scanner(System.in);
        System.out.print("-> ");
        k = 2;
        try {
            k = input.nextInt();
        } catch (Exception e) {
            System.err.println("Illegal Argument");
            System.exit(-1);
        } finally {
//            System.out.println("Finally");
        }
        if (k < 0) {
            System.err.println("Illegal Argument");
            System.exit(-1);
        }

        while (k != 42)	{
            if (isPrime(sumOfDigits(k)))	{
                coffeeRequests++;
            }
            System.out.print("-> ");
            try {
               k = input.nextInt();
            } catch (Exception e) {
                System.err.println("Illegal Argument");
                System.exit(-1);
            } finally {
//            System.out.println("Finally");
            }
            if (k < 0) {
                System.err.println("Illegal Argument");
                System.exit(-1);
            }
        }
        System.out.println("Count of coffee-request - " + coffeeRequests);

    }
    private static boolean isPrime(int input)
    {
        float summ;
        float num = 2;
        boolean prime = true;
        while (num < (float) Math.sqrt(input)) {
            summ = input % num;
            if (summ == 0)
                prime = false;
            num++;
        }
        if (prime == true)
            return true;
        else
            return false;

    }

    private  static  int sumOfDigits(int input)
    {
        int i = 0;
        float summ = 0;
        float num = 2;
        float j = input;
        while (i < 16) {
            num = input % 10;
            summ = summ + num;
            j = j - num;
            if (j == 0) {
               // System.out.println(summ);
                return ((int)summ);
            }
            j = j / 10;
            i++;
        }
//        System.out.println(i);
        return ((int)summ);
    }


}
