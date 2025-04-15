/**
 * 通用 getMax 方法
 */
class GeneralMax {
    public static Comparable getMax(Comparable[] data) {
        if (data == null || data.length == 0) {
            return null;
        }

        Comparable max = data[0];
        for (int i = 1; i < data.length; i++) {
            if (data[i].compare(max) > 0) {
                max = data[i];
            }
        }
        return max;
    }
}
