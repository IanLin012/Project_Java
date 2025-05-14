// 1.6 練習3：利用Math類別進行數學運算
import java.util.Scanner;

public class MathCount {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a number: ");
        int n = scanner.nextInt();
        System.out.println(Math.sqrt(n));
        System.out.println(Math.pow(n, 3));
    }
}
