import java.util.*;

abstract class AbstractGame {
    public abstract void setPlayers(Player p1, Player p2);
    public abstract boolean gameOver();
    public abstract boolean move(String from, String to);
}

class Player {
    private String name;
    private String side; //Chess side

    public Player(String name, String side) {
        this.name = name;
        this.side = side;
    }

    public String getName() { return name; }
    public String getSide() { return side; }
    public void setSide(String side) { this.side = side; }
}

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

public class ChessGame extends AbstractGame {
    private Chess[][] board;
    private List<Chess> chessPieces;
    private Player player1, player2;
    private final int rows = 4, cols = 8; //Chess board size
    private boolean isPlayer1Turn = true; //Player's turn

    public ChessGame() {
        board = new Chess[rows][cols];
        chessPieces = new ArrayList<>();
        generateChess();
    }

    public void generateChess() {
        //Chess side
        String[] redNames = {"帥", "仕", "仕", "相", "相", "傌", "傌", "俥", "俥", "炮", "炮", "兵", "兵", "兵", "兵", "兵"};
        String[] blackNames = {"將", "士", "士", "象", "象", "馬", "馬", "車", "車", "砲", "砲", "卒", "卒", "卒", "卒", "卒"};

        //Chess weight
        Map<String, Integer> weightMap = new HashMap<>();
        weightMap.put("兵", 1); weightMap.put("卒", 1);
        weightMap.put("炮", 2); weightMap.put("砲", 2);
        weightMap.put("傌", 3); weightMap.put("馬", 3);
        weightMap.put("俥", 4); weightMap.put("車", 4);
        weightMap.put("相", 5); weightMap.put("象", 5);
        weightMap.put("仕", 6); weightMap.put("士", 6);
        weightMap.put("帥", 7); weightMap.put("將", 7);

        List<String> positions = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            char rowChar = (char) ('A' + i);
            for (int j = 1; j <= cols; j++) {
                positions.add("" + rowChar + j);
            }
        }

        //Random sort
        Collections.shuffle(positions);
        for (String name : redNames) {
            String pos = positions.remove(0);
            Chess chess = new Chess(name, weightMap.get(name), "Red", pos);
            chessPieces.add(chess);
            int[] rc = convertPosToRC(pos);
            board[rc[0]][rc[1]] = chess;
        }
        for (String name : blackNames) {
            String pos = positions.remove(0);
            Chess chess = new Chess(name, weightMap.get(name), "Black", pos);
            chessPieces.add(chess);
            int[] rc = convertPosToRC(pos);
            board[rc[0]][rc[1]] = chess;
        }
    }

    // Convert position to [row, col]
    private int[] convertPosToRC(String pos) {
        if (pos == null || pos.length() < 2) return new int[]{-1, -1}; //Invalid input
        char r = pos.charAt(0);
        if (r < 'A' || r > ('A' + rows - 1)) return new int[]{-1, -1}; //Invalid position
        try {
            int c = Integer.parseInt(pos.substring(1)) - 1;
            if (c < 0 || c >= cols) return new int[]{-1, -1};
            return new int[]{r - 'A', c};
        } catch (NumberFormatException e) {
            return new int[]{-1, -1};
        }
    }

    // Count pieces between positions (rule for cannon)
    private int countPiecesBetween(int[] fromRC, int[] toRC) {
        int count = 0;
        if (fromRC[0] == toRC[0]) {
            int row = fromRC[0];
            for (int j = Math.min(fromRC[1], toRC[1]) + 1; j < Math.max(fromRC[1], toRC[1]); j++) {
                if (board[row][j] != null) count++;
            }
        } else if (fromRC[1] == toRC[1]) {
            int col = fromRC[1];
            for (int i = Math.min(fromRC[0], toRC[0]) + 1; i < Math.max(fromRC[0], toRC[0]); i++) {
                if (board[i][col] != null) count++;
            }
        } else return -1;
        return count;
    }

    // Display current board state
    public void showAllChess() {
        System.out.println("棋盤:");
        System.out.print("   ");
        for (int j = 1; j <= cols; j++) {
            System.out.print(" " + j + " ");
        }
        System.out.println();
        for (int i = 0; i < rows; i++) {
            System.out.print((char)('A' + i) + "  ");
            for (int j = 0; j < cols; j++) {
                System.out.print((board[i][j] == null ? " _ " : " " + board[i][j].toString() + " "));
            }
            System.out.println();
        }
    }

    // Override AbstractGame
    @Override
    public void setPlayers(Player p1, Player p2) {
        this.player1 = p1;
        this.player2 = p2;
    }
    @Override
    public boolean gameOver() {
        boolean redExists = false, blackExists = false;
        for (Chess c : chessPieces) {
            if (c.getSide().equals("Red")) redExists = true;
            if (c.getSide().equals("Black")) blackExists = true;
        }
        return !(redExists && blackExists); //Game over when one side no chess on board
    }
    @Override
    public boolean move(String from, String to) {
        int[] fromRC = convertPosToRC(from);
        int[] toRC = convertPosToRC(to);
        if (fromRC[0] < 0 || fromRC[0] >= rows || fromRC[1] < 0 || fromRC[1] >= cols ||
                toRC[0] < 0 || toRC[0] >= rows || toRC[1] < 0 || toRC[1] >= cols) {
            System.out.println("輸入位置不在棋盤內!");
            return false;
        }
        Chess selected = board[fromRC[0]][fromRC[1]];
        if (selected == null) {
            System.out.println("沒有棋子在 " + from + "!");
            return false;
        }
        if (from.equals(to)) {
            System.out.println("目標與初始位置不可相同!");
            return false;
        }
        Chess target = board[toRC[0]][toRC[1]];
        int dr = Math.abs(fromRC[0] - toRC[0]);
        int dc = Math.abs(fromRC[1] - toRC[1]);
        boolean isAdjacent = (dr + dc == 1);
        // Cannon move & capture
        if (selected.getName().equals("炮") || selected.getName().equals("砲")) {
            if (target == null) {
                if (!isAdjacent) {
                    System.out.println(selected.getName() + " 只能移動到相鄰位置!");
                    return false;
                }
                board[toRC[0]][toRC[1]] = selected;
                board[fromRC[0]][fromRC[1]] = null;
                selected.setLoc(to);
                System.out.println((isPlayer1Turn ? player1.getName() : player2.getName()) + " 將 " + selected.getName() + " 從 " + from + " 移動到 " + to);
                return true;
            } else {
                if (fromRC[0] != toRC[0] && fromRC[1] != toRC[1]) {
                    System.out.println(selected.getName() + " 只能吃同欄或同列的棋子!");
                    return false;
                }
                if (countPiecesBetween(fromRC, toRC) != 1) {
                    System.out.println(selected.getName() + " 必須間隔一顆棋子吃子!");
                    return false;
                }
                if (selected.getSide().equals(target.getSide())) {
                    System.out.println("不能吃自己的棋子!");
                    return false;
                }
                board[toRC[0]][toRC[1]] = selected;
                board[fromRC[0]][fromRC[1]] = null;
                selected.setLoc(to);
                chessPieces.remove(target);
                System.out.println((isPlayer1Turn ? player1.getName() : player2.getName()) + " 用 " + selected.getName() + " 吃了 " + target.getName() + " 並移動到 " + to);
                return true;
            }
        } else {
            // Normal piece move & capture
            if (!isAdjacent) {
                System.out.println("移動或吃子必須為相鄰位置!");
                return false;
            }
            if (target == null) {
                board[toRC[0]][toRC[1]] = selected;
                board[fromRC[0]][fromRC[1]] = null;
                selected.setLoc(to);
                System.out.println((isPlayer1Turn ? player1.getName() : player2.getName()) + " 將 " + selected.getName() + " 從 " + from + " 移動到 " + to);
                return true;
            } else {
                if (selected.getSide().equals(target.getSide())) {
                    System.out.println("不能吃自己的棋子!");
                    return false;
                }
                if (selected.canCapture(target)) {
                    board[toRC[0]][toRC[1]] = selected;
                    board[fromRC[0]][fromRC[1]] = null;
                    selected.setLoc(to);
                    chessPieces.remove(target);
                    System.out.println((isPlayer1Turn ? player1.getName() : player2.getName()) + " 用 " + selected.getName() + " 吃了 " + target.getName() + " 並移動到 " + to);
                    return true;
                } else {
                    System.out.println("違反吃子規則!");
                    return false;
                }
            }
        }
    }

    private String getWinner() {
        boolean redExists = false, blackExists = false;
        for (Chess c : chessPieces) {
            if(c.getSide().equals("Red")) redExists = true;
            if(c.getSide().equals("Black")) blackExists = true;
        }
        if(redExists && !blackExists) {
            return "贏家是 " + (player1.getSide().equals("Red") ? player1.getName() : player2.getName());
        } else if(blackExists && !redExists) {
            return "贏家是 " + (player1.getSide().equals("Black") ? player1.getName() : player2.getName());
        } else {
            return "平局";
        }
    }

    // Switch current player's turn
    private void switchTurn() {
        isPlayer1Turn = !isPlayer1Turn;
    }

    // Game loop
    public void play() {
        Scanner scanner = new Scanner(System.in);
        while (!gameOver()) {
            showAllChess();
            System.out.println("目前回合: " + (isPlayer1Turn ? player1.getName() + " (" + player1.getSide() + ")" : player2.getName() + " (" + player2.getSide() + ")"));
            System.out.print("請輸入來源位置 (EX. A2): ");
            String from = scanner.nextLine().trim().toUpperCase();
            int[] fromRC = convertPosToRC(from);
            if (fromRC[0] < 0 || fromRC[0] >= rows || fromRC[1] < 0 || fromRC[1] >= cols || board[fromRC[0]][fromRC[1]] == null) {
                System.out.println("來源位置錯誤，請重新輸入!");
                continue;
            }
            Chess piece = board[fromRC[0]][fromRC[1]];
            //Flip piece
            if (!piece.isFlipped()) {
                piece.flip();
                System.out.println((isPlayer1Turn ? player1.getName() : player2.getName()) + " 在 " + from + " 翻開了 " + piece.getName());
                //Player side set
                if (isPlayer1Turn && (player1.getSide() == null || player1.getSide().isEmpty())) {
                    player1.setSide(piece.getSide());
                    player2.setSide(piece.getSide().equals("Red") ? "Black" : "Red");
                    System.out.println("玩家1 陣營為 " + (player1.getSide().equals("Red") ? "紅" : "黑"));
                    System.out.println("玩家2 陣營為 " + (player2.getSide().equals("Red") ? "紅" : "黑"));
                }
                switchTurn();
            } else {
                //Ensure flipped piece belongs to current player
                if (!piece.getSide().equals(isPlayer1Turn ? player1.getSide() : player2.getSide())) {
                    System.out.println("這是對手的棋子!");
                    continue;
                }
                System.out.print("請輸入目標位置 (EX. B3): ");
                String to = scanner.nextLine().trim().toUpperCase();
                if (move(from, to))
                    switchTurn();
                else
                    System.out.println("動作無效，請重新輸入!");
            }
        }
        System.out.println("遊戲結束! " + getWinner());
        scanner.close();
    }

    public static void main(String[] args) {
        ChessGame game = new ChessGame(); //Initial board & piece
        game.setPlayers(new Player("玩家1", ""), new Player("玩家2", ""));
        game.play(); //Start game loop
    }
}
