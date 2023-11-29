import java.util.Arrays;

public class LotteryGenerator {
    int totalNumbers; // a variable
    static int lotteryNumbers = 5; // a static variable
    public LotteryGenerator() {}; // empty constructor

    public LotteryGenerator(int totalNumbers) {
        this.totalNumbers = totalNumbers;
    }

    int[] generateLottery(int num) { // random generated lottery number
        int temp, b;
        int[] arr = new int [lotteryNumbers];
        int[] tempArr = new int [lotteryNumbers];
        for(int i=0; i<lotteryNumbers; i++) {
            b = 1;
            temp = (int)(Math.random() * num) + 1;
            tempArr[i] = temp;
            for(int j=0; j<i; j++) {
                if(tempArr[j] == tempArr[i]) {
                    b = 0;
                    i -= 1;
                }
            }
            if(b == 1)
                arr[i] = temp;
        }
        Arrays.sort(arr);
        return arr;
    }

    static double average(int[] arr) { // calculate lottery number average
        double ave = 0;
        for(int i=0; i<lotteryNumbers; i++)
            ave += arr[i];
        ave /= 5;
        return ave;
    }
}