import java.util.Scanner;

public class APP {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LotteryGenerator lotteryGenerator = new LotteryGenerator();

        int totalNumbers; // generate object

        while(true) { // keep running if totalNumbers < lotteryNumbers
            System.out.print("請輸入可以抽出的號碼: ");
            totalNumbers = scanner.nextInt(); // input totalNumbers
            if(totalNumbers >= LotteryGenerator.lotteryNumbers) {
                int[] arr = lotteryGenerator.generateLottery(totalNumbers); // generate lottery numbers
                System.out.printf("樂透號碼為: %d %d %d %d %d\n", arr[0], arr[1], arr[2], arr[3], arr[4]); // print lottery numbers
                System.out.printf("樂透號碼平均值: %.2f\n", LotteryGenerator.average(arr)); // print lottery numbers average
                break;
            }
        }
    }
}