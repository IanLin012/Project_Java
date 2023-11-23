import java.util.Scanner;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[][][] Array = new String[2][3][3]; // create an Array
        Railway railway = new Railway(null, 0);
        Train train = new Train(null, 0, 0);
        HighSpeedRail highSpeedRail = new HighSpeedRail(null, 0, 0);
        for(int i=0; i<3; i++) {
            Random random = new Random();
            boolean b = random.nextBoolean(); // random generate object
            if(b) { // object = Train
                System.out.print("Input train conductorName: ");
                railway.setConductorName(scanner.next()); // input conductorName
                System.out.print("Input train passenger: ");
                railway.setPassenger(scanner.nextInt()); // input passenger
                System.out.print("Input train fare: ");
                train.setFare(scanner.nextDouble()); // input fare
                Array[0][i][0] = railway.getConductorName();
                Array[0][i][1] = Integer.toString(railway.getPassenger());
                Array[0][i][2] = Double.toString(train.getFare());
            }
            else { // object = HighSpeedRail
                System.out.print("Input high speed rail conductorName: ");
                railway.setConductorName(scanner.next()); // input conductorName
                System.out.print("Input high speed rail passenger: ");
                railway.setPassenger(scanner.nextInt()); // input passenger
                System.out.print("Input high speed rail fare: ");
                highSpeedRail.setTicket(scanner.nextInt()); // input ticket
                Array[1][i][0] = railway.getConductorName();
                Array[1][i][1] = Integer.toString(railway.getPassenger());
                Array[1][i][2] = Integer.toString(highSpeedRail.getTicket());
            }
        }
        System.out.println("\nAll conductor and passenger"); // print every conductor & passenger
        for(int i=0; i<2; i++) {
            for(int j=0; j<3; j++) {
                if(Array[i][j][0] != null) {
                    railway.setConductorName(Array[i][j][0]);
                    railway.setPassenger(Integer.parseInt(Array[i][j][1]));
                    System.out.println(railway.getInfo());
                }
            }
        }
        System.out.println("\nTrain passenger and price"); // print every passenger & price in train
        for(int i=0; i<3; i++) {
            if(Array[0][i][0] != null) {
                train.setPassenger(Integer.parseInt(Array[0][i][1]));
                train.setFare(Double.parseDouble(Array[0][i][2]));
                System.out.printf("train passenger: %d, train price:%.1f\n", train.getPassenger(), train.calcPrice());
            }
        }
    }
}