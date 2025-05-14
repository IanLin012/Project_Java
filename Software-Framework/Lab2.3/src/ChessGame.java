import java.util.ArrayList;
import java.util.List;

// 定義棋子類別
class Chess {
    private String name; // 棋子名稱
    private int weight;  // 階級：兵/卒=1，仕/士=2，相/象=3，傌/馬=4，俥/車=5，炮/砲=6，帥/將=7
    private String side; // 所屬陣營，例如 "Red" 或 "Black"
    private String loc;  // 棋盤上的位置，例如 "A2"
    private boolean flipped; // 是否已翻開

    public Chess(String name, int weight, String side, String loc) {
        this.name = name;
        this.weight = weight;
        this.side = side;
        this.loc = loc;
        this.flipped = false; // 初始為未翻開狀態
    }

    // 翻面
    public void flip() {
        flipped = true;
    }

    public boolean isFlipped() {
        return flipped;
    }

    public String getLoc() {
        return loc;
    }
    public void setLoc(String loc) {
        this.loc = loc;
    }

    public int getWeight() {
        return weight;
    }

    public String getSide() {
        return side;
    }

    public String getName() {
        return name;
    }

    // 對於非炮棋，根據規則判斷是否能吃掉目標棋子
    public boolean canCapture(Chess target) {
        if (!target.isFlipped()) return false; // 目標棋子未翻開不可吃
        if (this.weight < target.weight) return false; // 低階棋子無法吃高階棋子
        return true;
    }

    // 若未翻開則顯示 "X"，翻開則顯示棋子名稱
    public String toString() {
        return flipped ? name : "X";
    }
}

// 定義棋盤類別
class ChessBoard {
    private Chess[][] board;
    private List<Chess> pieces;

    public ChessBoard() {
        board = new Chess[10][9]; // 10x9 的棋盤
        pieces = new ArrayList<>();
        setupBoard();
    }

    // 初始化棋盤，放置棋子
    private void setupBoard() {
        // Red side pieces
        pieces.add(new Chess("帥", 7, "Red", "A1"));
        pieces.add(new Chess("仕", 2, "Red", "B1"));
        pieces.add(new Chess("士", 2, "Red", "C1"));
        pieces.add(new Chess("象", 3, "Red", "D1"));
        pieces.add(new Chess("馬", 4, "Red", "E1"));
        pieces.add(new Chess("車", 5, "Red", "F1"));
        pieces.add(new Chess("砲", 6, "Red", "G1"));
        pieces.add(new Chess("兵", 1, "Red", "H1"));
        // Black side pieces
        pieces.add(new Chess("將", 7, "Black", "A10"));
        pieces.add(new Chess("士", 2, "Black", "B10"));
        pieces.add(new Chess("士", 2, "Black", "C10"));
        pieces.add(new Chess("象", 3, "Black", "D10"));
        pieces.add(new Chess("馬", 4, "Black", "E10"));
        pieces.add(new Chess("車", 5, "Black", "F10"));
        pieces.add(new Chess("砲", 6, "Black", "G10"));
        pieces.add(new Chess("卒", 1, "Black", "H10"));

        // 將棋子放置在棋盤上
        for (Chess piece : pieces) {
            int row = 10 - Integer.parseInt(piece.getLoc().substring(1)); // 計算行數（從下往上數）
            int col = piece.getLoc().charAt(0) - 'A'; // 計算列數（從A到I）
            board[row][col] = piece; // 將棋子放置到棋盤上
        }
    }

    // 顯示棋盤
    public void displayBoard() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == null) {
                    System.out.print("  ");
                } else {
                    System.out.print(board[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    // 根據位置獲取棋子
    public Chess getPieceAt(String loc) {
        int row = 10 - Integer.parseInt(loc.substring(1)); // 計算行數（從下往上數）
        int col = loc.charAt(0) - 'A'; // 計算列數（從A到I）
        return board[row][col];
    }

    // 進行棋子移動
    public boolean movePiece(String from, String to) {
        Chess piece = getPieceAt(from);
        if (piece == null || !piece.isFlipped()) {
            System.out.println("無法移動未翻開的棋子或位置無棋子！");
            return false;
        }

        Chess target = getPieceAt(to);
        if (target != null && piece.canCapture(target)) {
            // 捕獲棋子
            board[10 - Integer.parseInt(to.substring(1))][to.charAt(0) - 'A'] = piece;
            board[10 - Integer.parseInt(from.substring(1))][from.charAt(0) - 'A'] = null;
            piece.setLoc(to);
            System.out.println(piece.getName() + " 吃了 " + target.getName());
            return true;
        }

        // 移動棋子
        if (target == null || piece.canCapture(target)) {
            board[10 - Integer.parseInt(to.substring(1))][to.charAt(0) - 'A'] = piece;
            board[10 - Integer.parseInt(from.substring(1))][from.charAt(0) - 'A'] = null;
            piece.setLoc(to);
            System.out.println(piece.getName() + " 移動到 " + to);
            return true;
        }

        System.out.println("無法移動或吃子不符合規則！");
        return false;
    }

    // 翻開棋子
    public void flipPiece(String loc) {
        Chess piece = getPieceAt(loc);
        if (piece != null && !piece.isFlipped()) {
            piece.flip();
            System.out.println(piece.getName() + " 已翻開");
        }
    }
}

// 主程式
public class Main {
    public static void main(String[] args) {
        ChessBoard board = new ChessBoard();

        // 顯示棋盤初始狀態
        board.displayBoard();

        // 玩家進行移動
        board.flipPiece("A1");  // 翻開帥
        board.movePiece("A1", "A2");  // 移動帥
        board.displayBoard();  // 顯示棋盤

        board.flipPiece("A10");  // 翻開將
        board.movePiece("A10", "A9");  // 移動將
        board.displayBoard();  // 顯示棋盤
    }
}
