// 1.8 練習1
public class Generics {
    public class Pair<T, U> {
        private T first;
        private U second;

        public T getFirst() {
            return first;
        }
        public U getSecond() {
            return second;
        }

        Pair(T first, U second) {
            this.first = first;
            this.second = second;
        }
    }

    public static void main(String[] args) {
        System.out.println();
    }
}
