/**
 * 球員取最高打擊率
 */
class Player implements Comparable {
    private String name;
    private int score;

    public Player(String name, int score) {
        this.name = name;
        this.score = score;
    }

    @Override
    public int compare(Comparable other) {
        if (other instanceof Player) {
            Player p = (Player) other;
            return this.score - p.score;
        }
        return 0;
    }

    @Override
    public String toString() {
        return name + ", score=" + score;
    }
}
