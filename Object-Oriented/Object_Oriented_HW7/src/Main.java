import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GeneralGame generalGame = new GeneralGame(); // generate object
        String gesture;
        Gesture[] user = new Gesture[3];

        System.out.println("Please enter 3 gestures:");
        for(int i=0; i<3; i++) { // input 3 strings
            gesture = scanner.nextLine();
            if((gesture.toUpperCase().equals(Gesture.STONE.name())) || (gesture.toUpperCase().equals(Gesture.PAPER.name())) || (gesture.toUpperCase().equals(Gesture.SCISSORS.name()))) { // convert string to enum
                user[i] = Gesture.valueOf(gesture.toUpperCase());
            }
            else {
                System.out.println("wrong input, please re-enter gesture");
                i -= 1;
            }
        }

        System.out.println("\n---All system gestures---");
        String gameResult = generalGame.play(user); // invoke play function
        for(int i=0; i<3;i++) { // print all sysGestures
            System.out.println(generalGame.getSysGesture()[i].name().toLowerCase());
        }
        System.out.println(gameResult); // print game result
    }
}