package fcu.main;

import fcu.oop.traffic.Taxi;
import fcu.oop.traffic.Train;
import fcu.oop.traffic.Transportation;

import java.util.Scanner;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        String driver;
        double price, road;
        int way;
        Transportation[] arr = new Transportation[3]; // Array with length = 3
        for(int i=0; i<3; i++) {
            boolean b = random.nextBoolean(); // random generate 3 objects
            if(b) { // object = Train
                System.out.print("Input train driver: ");
                driver = scanner.next();
                System.out.print("Input train price: ");
                price = scanner.nextDouble();
                System.out.print("Input train road: ");
                road = scanner.nextDouble();
                arr[i] = new Train(driver, price, road);
            }
            else { // object = Taxi
                System.out.print("Input taxi driver: ");
                driver = scanner.next();
                System.out.print("Input taxi price: ");
                price = scanner.nextDouble();
                System.out.print("Input taxi way: ");
                way = scanner.nextInt();
                arr[i] = new Taxi(driver, price, way);
            }
        }
        System.out.println("\nAll driver and price");
        for(int i=0; i<3; i++) { // print all objects of driver and calcPrice
            System.out.printf("%s %.1f\n", arr[i].getDriver(), arr[i].calcPrice());
        }
    }
}