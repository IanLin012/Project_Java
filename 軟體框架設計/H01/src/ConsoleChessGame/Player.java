package ConsoleChessGame;

class Player {
    private String name;
    private String side; //Chess side

    public Player(String name, String side) {
        this.name = name;
        this.side = side;
    }

    public String getName() {
        return name;
    }
    public String getSide() {
        return side;
    }
    public void setSide(String side) {
        this.side = side;
    }
}
