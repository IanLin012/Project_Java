import java.util.Scanner;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);
        ThreeCProduct[] arr = new ThreeCProduct[3];
        String brand = null, userName = null;
        MobilePhone mobilePhone = new MobilePhone(brand);
        Computer computer = new Computer(brand, userName);
        for(int i=0; i<3; i++) {
            boolean b = random.nextBoolean();
            if(b) {
                System.out.print("input phone brand:");
                brand = scanner.next();
                arr[i] = new MobilePhone(brand);
            }
            else {
                System.out.print("input computer brand:");
                brand = scanner.next();
                System.out.print("input computer userName:");
                userName = scanner.next();
                arr[i] = new Computer(brand, userName);
            }
        }
        System.out.println("\n---All product info---");
        for(int i=0; i<3; i++) {
            if(arr[i] instanceof MobilePhone) {
                mobilePhone.turnOn();
                mobilePhone.displayInfo(((MobilePhone)arr[i]).getBrand());
                mobilePhone.turnOff();
            }
            else  {
                computer.turnOn();
                computer.displayInfo(((Computer)arr[i]).getBrand(), ((Computer)arr[i]).getUserName());
                computer.turnOff();
            }
        }
    }
}