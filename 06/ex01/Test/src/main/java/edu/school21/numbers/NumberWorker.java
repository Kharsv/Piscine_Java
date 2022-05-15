package edu.school21.numbers;

public class NumberWorker {
    public boolean isPrime(int input)
    {
        float summ;
        float num = 2;
        boolean prime = true;
        if (input <= 1) {
            throw new IllegalNumberException("Not natural number!");
        }
        if (input == 2 || input == 3) {
            return true;
        }
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

    public int digitsSum(int input) {
        int summ = 0;

        while (input != 0) {
            summ += (input % 10);
            input /= 10;
        }
        return summ;
    }
}

class IllegalNumberException extends RuntimeException {
    public IllegalNumberException(String message) {
        super(message);
    }
}
