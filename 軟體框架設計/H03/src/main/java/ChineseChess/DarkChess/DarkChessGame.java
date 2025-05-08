package ChineseChess.DarkChess;

import ChineseChess.Interface.AbstractGame;
import ChineseChess.Interface.Player;

import java.util.*;

/**
 * Dark chess game flow
 */
public class DarkChessGame extends AbstractGame {
    protected DarkChess[][] board;
    protected List<DarkChess> chessPieces;
    protected Player player1, player2;
    protected final int rows = 4, cols = 8; //Board size
    protected boolean isPlayer1Turn = true;

    //Game message
    private StringBuilder logMessages = new StringBuilder();

    private void log(String msg) { logMessages.append(msg).append("\n"); }

    public DarkChessGame() {
        board = new DarkChess[rows][cols];
        chessPieces = new ArrayList<>();
        generateChess();
    }

    public void generateChess() {
        // ChessPiece name
        String[] redNames = {"帥", "仕", "仕", "相", "相",
                "傌", "傌", "俥", "俥", "炮", "炮",
                "兵", "兵", "兵", "兵", "兵"};
        String[] blackNames = {"將", "士", "士", "象", "象",
                "馬", "馬", "車", "車", "砲", "砲",
                "卒", "卒", "卒", "卒", "卒"};

        // ChessPiece weight
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
        // Random chess arrangement
        Collections.shuffle(positions);
        for (String name : redNames) {
            String pos = positions.remove(0);
            DarkChess chess = new DarkChess(
                name, "Red", pos, weightMap.get(name)
            );
            chessPieces.add(chess);
            int[] rc = convertPosToRC(pos);
            board[rc[0]][rc[1]] = chess;
        }
        for (String name : blackNames) {
            String pos = positions.remove(0);
            DarkChess chess = new DarkChess(
                name, "Black", pos, weightMap.get(name)
            );
            chessPieces.add(chess);
            int[] rc = convertPosToRC(pos);
            board[rc[0]][rc[1]] = chess;
        }
    }

    // Convert location string to row & column
    private int[] convertPosToRC(String pos) {
        if (pos == null || pos.length() < 2) return new int[]{-1, -1};
        char r = pos.charAt(0);
        if (r < 'A' || r > ('A' + rows - 1)) return new int[]{-1, -1};
        try {
            int c = Integer.parseInt(pos.substring(1)) - 1;
            if (c < 0 || c >= cols) return new int[]{-1, -1};
            return new int[]{r - 'A', c};
        } catch (NumberFormatException e) {
            return new int[]{-1, -1};
        }
    }

    // Calculate chess between source & destination
    private int countPiecesBetween(int[] fromRC, int[] toRC) {
        int count = 0;
        if (fromRC[0] == toRC[0]) {
            int row = fromRC[0];
            for (int j = Math.min(fromRC[1], toRC[1]) + 1;
                 j < Math.max(fromRC[1], toRC[1]); j++) {
                if (board[row][j] != null) count++;
            }
        } else if (fromRC[1] == toRC[1]) {
            int col = fromRC[1];
            for (int i = Math.min(fromRC[0], toRC[0]) + 1;
                 i < Math.max(fromRC[0], toRC[0]); i++) {
                if (board[i][col] != null) count++;
            }
        } else return -1;
        return count;
    }

    @Override
    public void setPlayers(Player p1, Player p2) {
        this.player1 = p1;
        this.player2 = p2;
    }

    @Override
    public boolean gameOver() {
        boolean redExists = false, blackExists = false;
        for (DarkChess c : chessPieces) {
            if (c.getSide().equals("Red")) redExists = true;
            if (c.getSide().equals("Black")) blackExists = true;
        }
        return !(redExists && blackExists);
    }

    // ChessPiece movement valid judgement & Record game message with log
    @Override
    public boolean move(String from, String to) {
        int[] fromRC = convertPosToRC(from);
        int[] toRC = convertPosToRC(to);
        if (fromRC[0] < 0 || fromRC[0] >= rows || fromRC[1] < 0 ||
                fromRC[1] >= cols || toRC[0] < 0 || toRC[0] >= rows ||
                toRC[1] < 0 || toRC[1] >= cols) {
            log("輸入位置不在棋盤內！");
            return false;
        }
        DarkChess selected = board[fromRC[0]][fromRC[1]];
        if (selected == null) {
            log("沒有棋子在 " + from + "！");
            return false;
        }
        if (from.equals(to)) {
            log("目標與初始位置不可相同！");
            return false;
        }
        DarkChess target = board[toRC[0]][toRC[1]];
        int dr = Math.abs(fromRC[0] - toRC[0]);
        int dc = Math.abs(fromRC[1] - toRC[1]);
        boolean isAdjacent = (dr + dc == 1);

        if (selected.getName().equals("炮") || selected.getName().equals("砲")) {
            if (target == null) {
                if (!isAdjacent) {
                    log(selected.getName() + " 只能移動到相鄰位置！");
                    return false;
                }
                board[toRC[0]][toRC[1]] = selected;
                board[fromRC[0]][fromRC[1]] = null;
                selected.setLoc(to);
                log(getCurrentPlayerName() + " 將 " + selected.getName() +
                        " 從 " + from + " 移動到 " + to);
                return true;
            } else {
                if (fromRC[0] != toRC[0] && fromRC[1] != toRC[1]) {
                    log(selected.getName() + " 只能吃同欄或同列的棋子！");
                    return false;
                }
                if (countPiecesBetween(fromRC, toRC) != 1) {
                    log(selected.getName() + " 必須間隔一顆棋子吃子！");
                    return false;
                }
                if (selected.getSide().equals(target.getSide())) {
                    log("不能吃自己的棋子！");
                    return false;
                }
                board[toRC[0]][toRC[1]] = selected;
                board[fromRC[0]][fromRC[1]] = null;
                selected.setLoc(to);
                chessPieces.remove(target);
                log(getCurrentPlayerName() + " 用 " + selected.getName() +
                        " 吃了 " + target.getName() + " 並移動到 " + to);
                return true;
            }
        } else {
            if (!isAdjacent) {
                log("移動或吃子必須為相鄰位置！");
                return false;
            }
            if (target == null) {
                board[toRC[0]][toRC[1]] = selected;
                board[fromRC[0]][fromRC[1]] = null;
                selected.setLoc(to);
                log(getCurrentPlayerName() + " 將 " + selected.getName() +
                        " 從 " + from + " 移動到 " + to);
                return true;
            } else {
                if (selected.getSide().equals(target.getSide())) {
                    log("不能吃自己的棋子！");
                    return false;
                }
                if (selected.canCapture(target)) {
                    board[toRC[0]][toRC[1]] = selected;
                    board[fromRC[0]][fromRC[1]] = null;
                    selected.setLoc(to);
                    chessPieces.remove(target);
                    log(getCurrentPlayerName() + " 用 " + selected.getName() +
                            " 吃了 " + target.getName() + " 並移動到 " + to);
                    return true;
                } else {
                    log("違反吃子規則：" + selected.getName() +
                            " 不能吃 " + target.getName() + "！");
                    return false;
                }
            }
        }
    }

    // Get current game message
    public String getLogMessages() {
        String msg = logMessages.toString();
        logMessages.setLength(0);
        return msg;
    }

    // Player turn judgement
    public void switchTurn() { isPlayer1Turn = !isPlayer1Turn; }

    // Getter for GUI
    public DarkChess[][] getBoard() { return board; }
    public String getCurrentPlayerName() {
        return isPlayer1Turn ? player1.getName() : player2.getName();
    }
    public String getCurrentPlayerSide() {
        return isPlayer1Turn ? player1.getSide() : player2.getSide();
    }
    public Player getPlayer1() { return player1; }
    public Player getPlayer2() { return player2; }

    // Get winner information
    public String getWinner() {
        boolean redExists = false, blackExists = false;
        for (DarkChess c : chessPieces) {
            if(c.getSide().equals("Red")) redExists = true;
            if(c.getSide().equals("Black")) blackExists = true;
        }
        if(redExists && !blackExists) {
            return (player1.getSide().equals("Red") ?
                    player1.getName() : player2.getName()) + "（紅方）獲勝！";
        } else if(blackExists && !redExists) {
            return (player1.getSide().equals("Black") ?
                    player1.getName() : player2.getName()) + "（黑方）獲勝！";
        } else {
            return "平局";
        }
    }
}

