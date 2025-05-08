package ChineseChess.StandardChess;

import ChineseChess.Interface.AbstractGame;
import ChineseChess.Interface.Chess;
import ChineseChess.Interface.Player;

import java.util.*;

public class StandardChessGame extends AbstractGame {
    private Chess[][] board;
    private List<Chess> pieces;
    private Player p1, p2;
    private boolean p1Turn = true;
    private final int rows = 10, cols = 9;

    public StandardChessGame() {
        board = new Chess[rows][cols];
        pieces = new ArrayList<>();
        setup();
    }

    private void setup() {
        String[][] layout = {
                {"車","馬","象","士","將","士","象","馬","車"},
                {null,null,null,null,null,null,null,null,null},
                {null,"砲",null,null,null,null,null,"砲",null},
                {"卒",null,"卒",null,"卒",null,"卒",null,"卒"},
                {null,null,null,null,null,null,null,null,null},
                {null,null,null,null,null,null,null,null,null},
                {"兵",null,"兵",null,"兵",null,"兵",null,"兵"},
                {null,"炮",null,null,null,null,null,"炮",null},
                {null,null,null,null,null,null,null,null,null},
                {"俥","傌","相","仕","帥","仕","相","傌","俥"}
        };
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                String name = layout[i][j];
                if (name != null) {
                    String side = i < 5 ? "Black" : "Red";
                    StandardChess p = new StandardChess(name, side, "" + (char)('A'+i) + (j+1));
                    board[i][j] = p;
                    pieces.add(p);
                }
            }
        }
    }

    @Override
    public void setPlayers(Player p1, Player p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    @Override
    public boolean move(String from, String to) {
        int[] f = convert(from), t = convert(to);
        if (f[0]<0||t[0]<0) return false;
        Chess sel = board[f[0]][f[1]];
        Chess tgt = board[t[0]][t[1]];
        if (sel == null || !sel.getSide().equals(getCurrentSide())) return false;
        StandardChess xp = (StandardChess) sel;
        if (!xp.canMove(f, t, board)) return false;
        if (tgt != null && tgt.getSide().equals(sel.getSide())) return false;
        // move
        board[t[0]][t[1]] = sel;
        board[f[0]][f[1]] = null;
        sel.setLoc(to);
        if (tgt != null) pieces.remove(tgt);
        return true;
    }

    private int[] convert(String p) {
        return new int[]{p.charAt(0)-'A', Integer.parseInt(p.substring(1))-1};
    }

    public void switchTurn() { p1Turn = !p1Turn; }

    @Override
    public boolean gameOver() {
        boolean redGeneral = false, blackGeneral = false;
        for (Chess c : pieces) {
            if (c.getName().equals("帥") && c.getSide().equals("Red")) redGeneral = true;
            if (c.getName().equals("將") && c.getSide().equals("Black")) blackGeneral = true;
        }
        return !(redGeneral && blackGeneral);
    }

    public String getWinner() {
        boolean redGeneral = false, blackGeneral = false;
        for (Chess c : pieces) {
            if (c.getName().equals("帥") && c.getSide().equals("Red")) redGeneral = true;
            if (c.getName().equals("將") && c.getSide().equals("Black")) blackGeneral = true;
        }
        if (!redGeneral) return p2.getName() + "（黑方）勝利";
        if (!blackGeneral) return p1.getName() + "（紅方）勝利";
        return "";
    }

    public boolean gameOverWithoutLog() { return gameOver(); }

    public String getCurrentSide() { return p1Turn? p1.getSide():p2.getSide(); }

    public Chess[][] getBoard() { return board; }
}
