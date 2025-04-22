package ConsoleChessGame;

class Chess {
    private String name;
    private int weight;
    private String side;
    private String loc; //Board position
    private boolean flipped; //Revealed or not

    public Chess(String name, int weight, String side, String loc) {
        this.name = name;
        this.weight = weight;
        this.side = side;
        this.loc = loc;
        this.flipped = false;
    }

    public void flip() { flipped = true; }
    public boolean isFlipped() { return flipped; }

    public String getLoc() { return loc; }
    public void setLoc(String loc) { this.loc = loc; }
    public int getWeight() { return weight; }
    public String getSide() { return side; }
    public String getName() { return name; }

    // Capture rule
    public boolean canCapture(Chess target) {
        if (!target.isFlipped()) return false;
        if (this.name.equals("兵") && target.getName().equals("將")) return true;
        if (this.name.equals("卒") && target.getName().equals("帥")) return true;
        if ((this.name.equals("將") && target.getName().equals("兵")) || (this.name.equals("帥") && target.getName().equals("卒"))) return false;
        return this.weight >= target.weight;
    }

    //Return piece name or X
    public String toString() { return flipped ? name : "X"; }
}
