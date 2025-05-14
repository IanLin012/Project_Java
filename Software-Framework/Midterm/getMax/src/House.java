/**
 * 房屋取最高高度
 */
class House implements Comparable {
    private String name;
    private int height;

    public House(String name, int height) {
        this.name = name;
        this.height = height;
    }

    @Override
    public int compare(Comparable other) {
        if (other instanceof House) {
            House h = (House) other;
            return this.height - h.height;
        }
        return 0;
    }

    @Override
    public String toString() {
        return name + ", Height=" + height;
    }
}
