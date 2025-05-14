package ChineseChess.Interface;

/**
 * Chess information
 */
public class Chess {
    protected String name;
    protected String side;
    protected String loc;

    public Chess(String name, String side, String loc) {
        this.name = name;
        this.side = side;
        this.loc = loc;
    }

    public String getName() { return name; }
    public String getSide() { return side; }
    public String getLoc() { return loc; }
    public void setLoc(String loc) { this.loc = loc; }
}
