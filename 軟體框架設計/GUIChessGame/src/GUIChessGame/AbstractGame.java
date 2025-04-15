package GUIChessGame;

/**
 * Definite game basic method
 */
abstract class AbstractGame {
    public abstract void setPlayers(Player p1, Player p2);
    public abstract boolean move(String from, String to);
    public abstract boolean gameOver();
}
