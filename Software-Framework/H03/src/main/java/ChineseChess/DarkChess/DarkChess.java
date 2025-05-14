package ChineseChess.DarkChess;

import ChineseChess.Interface.Chess;

/**
 * Dark chess rule
 */
public class DarkChess extends Chess {
    private int weight;
    private boolean flipped;

    public DarkChess(String name, String side, String loc, int weight) {
        super(name, side, loc);
        this.weight = weight;
        this.flipped = false;
    }

    public void flip() { flipped = true; }
    public boolean isFlipped() { return flipped; }

    public int getWeight() { return weight; }

    // Capture rule
    public boolean canCapture(Chess target) {
        if (!(target instanceof DarkChess)) return false;

        DarkChess targetDarkChess = (DarkChess) target;
        if (!targetDarkChess.isFlipped()) return false;

        if (this.name.equals("兵") && target.getName().equals("將")) return true;
        if (this.name.equals("卒") && target.getName().equals("帥")) return true;

        if ((this.name.equals("將") && target.getName().equals("兵")) ||
                (this.name.equals("帥") && target.getName().equals("卒")))
            return false;

        return this.weight >= targetDarkChess.weight;
    }
}
