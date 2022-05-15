package ex03;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        String str = "";
        int count = 0;
        System.out.print("-> ");
        while (input.hasNextLine()) {

            String line = input.next();

            if (line.equals("42")) {
                break;
            }

            if (!"Week".equals(line) || !input.hasNextInt()) {
                System.err.println("Illegal Argument");
                System.exit(-1);
            }

            if (count == 18 || count + 1 != input.nextInt()) {
                System.err.println("Illegal Argument");
                System.exit(-1);
            }

            if (!"".equals(input.nextLine())) {
                System.err.println("Illegal Argument");
                System.exit(-1);
            }

            int min = 9;
            int i;
            System.out.print("-> ");
            for (i = 0; i < 5; i++) {
                if (!input.hasNextInt()) {
                    break;
                }
                int num = input.nextInt();

                if (num > 9 || num < 1) {
                    break;
                }
                if (num < min) {
                    min = num;
                }
            }

            if (i != 5 || !"".equals(input.nextLine())) {
                System.err.println("Illegal Argument");
                System.exit(-1);
            }

            str += "Week ";
            str += (count + 1);
            str += " ";
            while (min > 0)
            {
                str += "=";
                min--;
            }
            str += ">\n";

            count++;
        }
        System.out.println(str);
    }
}
