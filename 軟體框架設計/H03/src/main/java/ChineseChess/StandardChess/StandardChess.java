package ChineseChess.StandardChess;

import ChineseChess.Interface.Chess;

public class StandardChess extends Chess {
    public StandardChess(String name, String side, String loc) {
        super(name, side, loc);
    }

    public boolean canMove(int[] from, int[] to, Chess[][] board) {
        int dr = to[0] - from[0];
        int dc = to[1] - from[1];
        Chess target = board[to[0]][to[1]];
        String n = getName();
        // 車 (Rook)
        if (n.equals("車") || n.equals("俥")) {
            if (dr != 0 && dc != 0) return false;
            if (countBetween(from, to, board) != 0) return false;
            return true;
        }
        // 馬 (Horse)
        if (n.equals("馬") || n.equals("傌")) {
            if (Math.abs(dr)==2 && Math.abs(dc)==1) {
                // 馬腿
                int br = from[0] + dr/2;
                int bc = from[1];
                return board[br][bc] == null;
            } else if (Math.abs(dr)==1 && Math.abs(dc)==2) {
                int br = from[0];
                int bc = from[1] + dc/2;
                return board[br][bc] == null;
            }
            return false;
        }
        // 相/象 (Elephant)
        if (n.equals("象") || n.equals("相")) {
            // 田字走兩格
            if (Math.abs(dr)==2 && Math.abs(dc)==2) {
                // 相眼
                int br = from[0] + dr/2;
                int bc = from[1] + dc/2;
                if (board[br][bc] != null) return false;
                // 不可過河
                if (getSide().equals("Red") && to[0] < 5) return false;
                if (getSide().equals("Black") && to[0] > 4) return false;
                return true;
            }
            return false;
        }
        // 士 (Advisor)
        if (n.equals("士") || n.equals("仕")) {
            if (Math.abs(dr)==1 && Math.abs(dc)==1) {
                int r = to[0], c = to[1];
                if (getSide().equals("Red") && r >= 7 && c >= 3 && c <= 5) return true;
                if (getSide().equals("Black") && r <= 2 && c >= 3 && c <= 5) return true;
            }
            return false;
        }
        // 帥/將 (General)
        if (n.equals("帥") || n.equals("將")) {
            // 一格直走或橫走
            if (Math.abs(dr)+Math.abs(dc)==1) {
                int r = to[0], c = to[1];
                if (getSide().equals("Red") && r >= 7 && c >= 3 && c <= 5) return true;
                if (getSide().equals("Black") && r <= 2 && c >= 3 && c <= 5) return true;
            }
            // 飛將
            if (dc==0) {
                Chess other = board[to[0]][to[1]];
                if (other != null && (other.getName().equals("帥")||other.getName().equals("將"))) {
                    if (countBetween(from, to, board)==0) return true;
                }
            }
            return false;
        }
        // 兵/卒 (Pawn)
        if (n.equals("兵") || n.equals("卒")) {
            int dir = getSide().equals("Red") ? -1 : 1;
            // 直進一格
            if (dr==dir && dc==0) return true;
            // 過河後可平走
            boolean crossed = getSide().equals("Red") ? from[0] <= 4 : from[0] >= 5;
            if (crossed && dr==0 && Math.abs(dc)==1) return true;
            return false;
        }
        // 炮 (Cannon)
        if (n.equals("炮") || n.equals("砲")) {
            if (dr!=0 && dc!=0) return false;
            int between = countBetween(from, to, board);
            if (target == null) {
                return between == 0;
            } else {
                return between == 1;
            }
        }
        return false;
    }

    private int countBetween(int[] from, int[] to, Chess[][] board) {
        int count = 0;
        if (from[0]==to[0]) {
            for (int j=Math.min(from[1], to[1])+1; j<Math.max(from[1], to[1]); j++)
                if (board[from[0]][j] != null) count++;
        } else if (from[1]==to[1]) {
            for (int i=Math.min(from[0], to[0])+1; i<Math.max(from[0], to[0]); i++)
                if (board[i][from[1]] != null) count++;
        }
        return count;
    }
}
