// 1.1 練習3：物件vs.資料結構
import java.util.Scanner;

public class DataStructure {
    static String[] studentName = {"Alice", "Bob", "C"};
    static double[] studentScore = {99, 46, 84};
    public static void displayScore(String name) {
        boolean found = false;
        for(int i=0; i<studentName.length; i++) {
            if(name.equals(studentName[i])) {
                System.out.println(name + " 的分數是 " + studentScore[i]);
                found = true;
                break;
            }
        }
        if(!found) {
            System.out.println("查無此學生");
        }
    }

    public static double calculateAverage() {
        double sum = 0;
        for (int i = 0; i < studentScore.length; i++) {
            sum += studentScore[i];
        }
        return sum / studentScore.length;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Please enter student name: ");
        String inputName = scanner.nextLine();
        displayScore(inputName);

        System.out.println("All students' score average is " + calculateAverage());
    }
}
