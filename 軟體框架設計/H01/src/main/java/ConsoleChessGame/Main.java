package ConsoleChessGame;

public class Main {
    public static void main(String[] args) {
        ChessGame game = new ChessGame(); //Initial board & piece
        game.setPlayers(new Player("玩家1", ""), new Player("玩家2", ""));
        game.play(); //Start game loop
    }
}
