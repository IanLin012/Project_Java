import java.util.Iterator;

public class School {
    public double getAverage(Iterator<Integer> scores) {
        int sum = 0;
        int count = 0;
        while (scores.hasNext()) {
            sum += scores.next();
            count++;
        }
        return count == 0 ? 0.0 : (double) sum / count;
    }
}
