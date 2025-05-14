import java.util.Enumeration;
import java.util.Iterator;
import java.util.Vector;

public class Main {
    public static void main(String[] args) {
        Vector<Integer> group = new Vector<>();
        group.add(85);
        group.add(90);
        group.add(75);

        Enumeration<Integer> enumeration = group.elements();

        Iterator<Integer> iterator = new EnumerationIterator<>(enumeration);

        School school = new School();
        double average = school.getAverage(iterator);

        System.out.printf("Group average grade: %.2f", average);
    }
}
