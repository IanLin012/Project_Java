import java.util.*;

// 抽象遊戲類別，宣告基本遊戲方法
abstract class AbstractGame {
    public abstract void setPlayers(Player p1, Player p2);
    public abstract boolean gameOver();
    public abstract boolean move(String from, String to);
}

// 玩家類別
class Player {
    private String name;
    private String side; // 例如 "Red" 或 "Black"

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
}

// 棋子類別
class Chess {
    private String name;   // 棋子名稱，如「兵」、「炮」、「帥」等
    private int weight;    // 階級：兵/卒=1，仕/士=2，相/象=3，傌/馬=4，俥/車=5，炮/砲=6，帥/將=7
    private String side;   // 所屬陣營，例如 "Red" 或 "Black"
    private String loc;    // 棋盤上的位置，例如 "A2"
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
        // 若目標未翻開，不允許捕捉
        if (!target.isFlipped()) return false;
        // 特例：兵／卒可吃將／帥
        if(this.name.equals("兵") && target.getName().equals("將")) return true;
        if(this.name.equals("卒") && target.getName().equals("帥")) return true;
        // 將／帥不可吃兵／卒
        if((this.name.equals("將") && target.getName().equals("兵"))
                || (this.name.equals("帥") && target.getName().equals("卒"))) return false;
        // 一般情況下，僅能吃同等或較低階級的棋子
        return this.weight >= target.weight;
    }

    // 若未翻開則顯示 "X"，翻開則顯示棋子名稱
    public String toString() {
        return flipped ? name : "X";
    }
}

// 棋局遊戲類別，繼承 AbstractGame
public class ChessGame extends AbstractGame {
    private Chess[][] board;
    private List<Chess> chessPieces;
    private Player player1, player2;
    private final int rows = 4;
    private final int cols = 8;

    public ChessGame() {
        board = new Chess[rows][cols];
        chessPieces = new ArrayList<>();
        generateChess();
    }

    // 利用預先定義的棋子名稱及階級，隨機放置 32 個棋子於 4x8 棋盤上
    public void generateChess() {
        String[] redNames = {"帥", "仕", "仕", "相", "相", "傌", "傌", "俥", "俥", "炮", "炮", "兵", "兵", "兵", "兵", "兵"};
        String[] blackNames = {"將", "士", "士", "象", "象", "馬", "馬", "車", "車", "砲", "砲", "卒", "卒", "卒", "卒", "卒"};
        Map<String, Integer> weightMap = new HashMap<>();
        weightMap.put("兵", 1);
        weightMap.put("卒", 1);
        weightMap.put("仕", 2);
        weightMap.put("士", 2);
        weightMap.put("相", 3);
        weightMap.put("象", 3);
        weightMap.put("傌", 4);
        weightMap.put("馬", 4);
        weightMap.put("俥", 5);
        weightMap.put("車", 5);
        weightMap.put("炮", 6);
        weightMap.put("砲", 6);
        weightMap.put("帥", 7);
        weightMap.put("將", 7);

        // 建立所有棋盤位置，如 A1, A2, …, D8
        List<String> positions = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            char rowChar = (char) ('A' + i);
            for (int j = 1; j <= cols; j++) {
                positions.add("" + rowChar + j);
            }
        }
        Collections.shuffle(positions);

        // 建立紅方棋子
        for (int i = 0; i < redNames.length; i++) {
            String pos = positions.remove(0);
            Chess chess = new Chess(redNames[i], weightMap.get(redNames[i]), "Red", pos);
            chessPieces.add(chess);
            int[] rc = convertPosToRC(pos);
            board[rc[0]][rc[1]] = chess;
        }
        // 建立黑方棋子
        for (int i = 0; i < blackNames.length; i++) {
            String pos = positions.remove(0);
            Chess chess = new Chess(blackNames[i], weightMap.get(blackNames[i]), "Black", pos);
            chessPieces.add(chess);
            int[] rc = convertPosToRC(pos);
            board[rc[0]][rc[1]] = chess;
        }
    }

    // 輔助方法：將 "A2" 轉成 [row, col] 索引（例如 A 對應 row 0，數字轉換成 col index）
    private int[] convertPosToRC(String pos) {
        int row = pos.charAt(0) - 'A';
        int col = Integer.parseInt(pos.substring(1)) - 1;
        return new int[]{row, col};
    }

    // 輔助方法：計算同一行或同一列上兩個位置之間的棋子數量
    private int countPiecesBetween(int[] fromRC, int[] toRC) {
        int count = 0;
        if(fromRC[0] == toRC[0]) {
            int row = fromRC[0];
            int start = Math.min(fromRC[1], toRC[1]) + 1;
            int end = Math.max(fromRC[1], toRC[1]);
            for (int j = start; j < end; j++) {
                if(board[row][j] != null) count++;
            }
        } else if(fromRC[1] == toRC[1]) {
            int col = fromRC[1];
            int start = Math.min(fromRC[0], toRC[0]) + 1;
            int end = Math.max(fromRC[0], toRC[0]);
            for (int i = start; i < end; i++) {
                if(board[i][col] != null) count++;
            }
        } else {
            // 非同一行或列，返回 -1 表示無效
            return -1;
        }
        return count;
    }

    // 顯示目前棋盤狀態，空位以 "." 表示
    public void showAllChess() {
        System.out.println("目前棋盤狀態：");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if(board[i][j] == null)
                    System.out.print(" _ ");
                else
                    System.out.print(" " + board[i][j].toString() + " ");
            }
            System.out.println();
        }
    }

    @Override
    public void setPlayers(Player p1, Player p2) {
        this.player1 = p1;
        this.player2 = p2;
    }

    // 遊戲結束條件：當任一方的將/帥被捕獲（從 chessPieces 移除）時結束
    @Override
    public boolean gameOver() {
        boolean redGeneralAlive = false;
        boolean blackGeneralAlive = false;
        for (Chess chess : chessPieces) {
            if(chess.getName().equals("帥")) redGeneralAlive = true;
            if(chess.getName().equals("將")) blackGeneralAlive = true;
        }
        return !(redGeneralAlive && blackGeneralAlive);
    }


    // 移動方法：
    // 1. 若來源位置的棋子未翻開，則僅翻面；
    // 2. 若已翻開，則依據目的位置是否為空、是否為炮、是否為捕捉，並檢查相鄰（非炮捕捉除外）或炮台條件後移動／吃子。
    @Override
    public boolean move(String from, String to) {
        int[] fromRC = convertPosToRC(from);
        int[] toRC = convertPosToRC(to);

        // 檢查位置是否在棋盤範圍內
        if(fromRC[0] < 0 || fromRC[0] >= rows || fromRC[1] < 0 || fromRC[1] >= cols ||
                toRC[0] < 0 || toRC[0] >= rows || toRC[1] < 0 || toRC[1] >= cols) {
            System.out.println("輸入位置超出棋盤範圍！");
            return false;
        }

        Chess selected = board[fromRC[0]][fromRC[1]];
        if(selected == null) {
            System.out.println("位置 " + from + " 沒有棋子！");
            return false;
        }
        // 若棋子未翻開，僅翻面
        if(!selected.isFlipped()) {
            selected.flip();
            System.out.println("翻開棋子：" + selected.getName() + " (" + from + ")");
            return true;
        } else {
            // 避免來源與目的相同
            if(from.equals(to)) {
                System.out.println("目的位置與來源相同！");
                return false;
            }

            Chess target = board[toRC[0]][toRC[1]];

            // 判斷是否為相鄰移動（上下左右，距離和為1）
            int dr = Math.abs(fromRC[0] - toRC[0]);
            int dc = Math.abs(fromRC[1] - toRC[1]);
            boolean isAdjacent = (dr + dc == 1);

            // 若棋子是炮
            if(selected.getName().equals("炮") || selected.getName().equals("砲")) {
                // 若目標為空：炮非捕捉移動僅能走相鄰
                if(target == null) {
                    if(!isAdjacent) {
                        System.out.println("炮非捕捉時只能移動到相鄰空格！");
                        return false;
                    }
                    board[toRC[0]][toRC[1]] = selected;
                    board[fromRC[0]][fromRC[1]] = null;
                    selected.setLoc(to);
                    System.out.println("炮從 " + from + " 移動到 " + to);
                    return true;
                } else {
                    // 捕捉動作：必須在同一行或列
                    if(fromRC[0] != toRC[0] && fromRC[1] != toRC[1]) {
                        System.out.println("炮捕捉必須在同一行或同一列！");
                        return false;
                    }
                    int between = countPiecesBetween(fromRC, toRC);
                    if(between != 1) {
                        System.out.println("炮捕捉必須有且僅有一個炮台在中間！");
                        return false;
                    }
                    if(selected.getSide().equals(target.getSide())) {
                        System.out.println("不能吃己方棋子！");
                        return false;
                    }
                    // 捕捉成功（不受階級限制）
                    board[toRC[0]][toRC[1]] = selected;
                    board[fromRC[0]][fromRC[1]] = null;
                    selected.setLoc(to);
                    chessPieces.remove(target);
                    System.out.println("炮從 " + from + " 吃掉 " + target.getName() + " (" + to + ") via 炮台跳越");
                    return true;
                }
            } else {
                // 非炮棋：移動或捕捉均必須移動到相鄰位置
                if(!isAdjacent) {
                    System.out.println("移動/吃子必須移動到相鄰位置！");
                    return false;
                }
                if(target == null) {
                    board[toRC[0]][toRC[1]] = selected;
                    board[fromRC[0]][fromRC[1]] = null;
                    selected.setLoc(to);
                    System.out.println(selected.getName() + " 從 " + from + " 移動到 " + to);
                    return true;
                } else {
                    if(selected.getSide().equals(target.getSide())) {
                        System.out.println("不能吃己方棋子！");
                        return false;
                    }
                    if(selected.canCapture(target)) {
                        board[toRC[0]][toRC[1]] = selected;
                        board[fromRC[0]][fromRC[1]] = null;
                        selected.setLoc(to);
                        chessPieces.remove(target);
                        System.out.println(selected.getName() + " 從 " + from + " 吃掉 " + target.getName() + " (" + to + ")");
                        return true;
                    } else {
                        System.out.println("捕捉規則不符，無法吃子！");
                        return false;
                    }
                }
            }
        }
    }

    // 主迴圈：每次讀取使用者輸入，依據棋盤狀態進行翻棋或移動／吃子
    public void play() {
        Scanner scanner = new Scanner(System.in);
        while(!gameOver()) {
            showAllChess();
            System.out.println("請輸入來源位置 (例如 A2)：");
            String from = scanner.nextLine().trim().toUpperCase();
            int[] fromRC = convertPosToRC(from);
            if(fromRC[0] < 0 || fromRC[0] >= rows || fromRC[1] < 0 || fromRC[1] >= cols || board[fromRC[0]][fromRC[1]] == null) {
                System.out.println("位置 " + from + " 沒有棋子，請重新輸入！");
                continue;
            }
            if(!board[fromRC[0]][fromRC[1]].isFlipped()) {
                board[fromRC[0]][fromRC[1]].flip();
                System.out.println("翻開棋子：" + board[fromRC[0]][fromRC[1]].getName() + " (" + from + ")");
            } else {
                System.out.println("請輸入目的位置 (例如 B3)：");
                String to = scanner.nextLine().trim().toUpperCase();
                boolean result = move(from, to);
                if(!result) {
                    System.out.println("動作失敗，請重試。");
                }
            }
        }
        System.out.println("遊戲結束！");
        scanner.close();
    }

    public static void main(String[] args) {
        ChessGame game = new ChessGame();
        // 設定玩家（此示例中僅用於標示紅黑方）
        game.setPlayers(new Player("Player1", "Red"), new Player("Player2", "Black"));
        game.play();
    }
}
