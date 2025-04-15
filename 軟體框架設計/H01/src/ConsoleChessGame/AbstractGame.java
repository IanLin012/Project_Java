package ConsoleChessGame;

abstract class AbstractGame {
    public abstract void setPlayers(Player p1, Player p2);
    public abstract boolean gameOver();
    public abstract boolean move(String from, String to);
}
