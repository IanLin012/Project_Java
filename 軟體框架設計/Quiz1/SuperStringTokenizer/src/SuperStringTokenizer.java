import java.util.StringTokenizer;
import java.util.Scanner;

public class SuperStringTokenizer extends StringTokenizer {

    public SuperStringTokenizer(String str) {
        super(str);
    }

    public SuperStringTokenizer(String str, String delim) {
        super(str, delim);
    }

    public SuperStringTokenizer(String str, String delim, boolean returnDelims) {
        super(str, delim, returnDelims);
    }

    @Override
    public String nextToken() {
        return super.nextToken().toUpperCase();
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
        SuperStringTokenizer tokenizer = new SuperStringTokenizer(str);

        while (tokenizer.hasMoreTokens()) {
            System.out.println(tokenizer.nextToken());
        }
    }
}
