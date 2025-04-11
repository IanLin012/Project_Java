package org.example;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please input a number: ");
        int num = scanner.nextInt();
        if(isPrime(num)) {
            System.out.println("It is a prime");
        } else {
            System.out.println("It is not a prime");
        }
    }
    private static boolean isPrime(int num) {
        if(num > 100) {
            throw new NumberFormatException("BigNumberException");
        } else if (num < 0) {
            throw new NumberFormatException("SmallNumberException");
        }
        boolean b = true;
        for(int i=2; i<num; i++) {
            if(num%i==0) {
                b = false;
            }
        }
        return b;
    }
}