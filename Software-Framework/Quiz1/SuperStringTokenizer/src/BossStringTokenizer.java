import java.util.StringTokenizer;
import java.util.Scanner;

public class BossStringTokenizer {
    private StringTokenizer tokenizer;  // 委託對象

    public BossStringTokenizer(String str) {
        this.tokenizer = new StringTokenizer(str);
    }

    public BossStringTokenizer(String str, String delim) {
        this.tokenizer = new StringTokenizer(str, delim);
    }

    public BossStringTokenizer(String str, String delim, boolean returnDelims) {
        this.tokenizer = new StringTokenizer(str, delim, returnDelims);
    }

    public boolean hasMoreTokens() {
        return tokenizer.hasMoreTokens();
    }

    public String nextToken() {
        return tokenizer.nextToken().toUpperCase();
    }

    public int countTokens() {
        return tokenizer.countTokens();
    }

    public String[] getAllTokens() {
        java.util.List<String> tokens = new java.util.ArrayList<>();
        while (hasMoreTokens()) {
            tokens.add(nextToken());
        }
        return tokens.toArray(new String[0]);
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.print("Please input a string: ");
        String str = s.nextLine();
        BossStringTokenizer tokenizer = new BossStringTokenizer(str);

        while (tokenizer.hasMoreTokens()) {
            System.out.println(tokenizer.nextToken());
        }
    }
}
