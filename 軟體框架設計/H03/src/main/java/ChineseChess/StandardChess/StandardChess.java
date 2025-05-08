package ChineseChess.StandardChess;

import ChineseChess.Interface.Chess;

/**
 * Standard chess rule
 */
public class StandardChess extends Chess {
    public StandardChess(String name, String side, String loc) {
        super(name, side, loc);
    }

    // Chess move rule
    public boolean canMove(int[] from, int[] to, Chess[][] board) {
        //Up & down movement
        int dr = to[0] - from[0];
        //Left & right movement
        int dc = to[1] - from[1];
        Chess target = board[to[0]][to[1]];
        String n = getName();

        // Rook
        if (n.equals("車") || n.equals("俥")) {
            //Straight movement
            if (dr != 0 && dc != 0) return false;
            //Obstacle on route
            if (countBetween(from, to, board) != 0) return false;
            return true;
        }

        // Horse
        if (n.equals("馬") || n.equals("傌")) {
            //日字
            if (Math.abs(dr)==2 && Math.abs(dc)==1) {
                int br = from[0] + dr/2;
                int bc = from[1];
                //絆馬腳
                return board[br][bc] == null;
            } else if (Math.abs(dr)==1 && Math.abs(dc)==2) {
                int br = from[0];
                int bc = from[1] + dc/2;
                //絆馬腳
                return board[br][bc] == null;
            }
            return false;
        }

        // Elephant
        if (n.equals("象") || n.equals("相")) {
            //田字
            if (Math.abs(dr)==2 && Math.abs(dc)==2) {
                int br = from[0] + dr/2;
                int bc = from[1] + dc/2;
                //塞象眼
                if (board[br][bc] != null) return false;
                // After river invalid
                if (getSide().equals("Red") && to[0] < 5) return false;
                if (getSide().equals("Black") && to[0] > 4) return false;
                return true;
            }
            return false;
        }

        // Advisor
        if (n.equals("士") || n.equals("仕")) {
            //Diagonal 1 block movement
            if (Math.abs(dr)==1 && Math.abs(dc)==1) {
                int r = to[0], c = to[1];
                //Inside 3×3 grid
                if (getSide().equals("Red") && r >= 7 && c >= 3 && c <= 5)
                    return true;
                if (getSide().equals("Black") && r <= 2 && c >= 3 && c <= 5)
                    return true;
            }
            return false;
        }

        // General
        if (n.equals("帥") || n.equals("將")) {
            //Straight 1 block movement
            if (Math.abs(dr)+Math.abs(dc)==1) {
                int r = to[0], c = to[1];
                //Inside 3×3 grid
                if (getSide().equals("Red") && r >= 7 && c >= 3 && c <= 5)
                    return true;
                if (getSide().equals("Black") && r <= 2 && c >= 3 && c <= 5)
                    return true;
            }
            //Fly General
            if (dc==0) {
                Chess other = board[to[0]][to[1]];
                if (other != null &&
                    (other.getName().equals("帥")||other.getName().equals("將"))) {
                    if (countBetween(from, to, board)==0) return true;
                }
            }
            return false;
        }

        // Pawn
        if (n.equals("兵") || n.equals("卒")) {
            //Straight 1 block movement
            int dir = getSide().equals("Red") ? -1 : 1;
            if (dr==dir && dc==0) return true;
            //After river
            boolean crossed = getSide().equals("Red") ?
                    from[0] <= 4 : from[0] >= 5;
            //Horizontal 1 block movement
            if (crossed && dr==0 && Math.abs(dc)==1) return true;
            return false;
        }

        // Cannon
        if (n.equals("炮") || n.equals("砲")) {
            //Straight movement
            if (dr!=0 && dc!=0) return false;
            int between = countBetween(from, to, board);
            //Obstacle on route
            if (target == null) return between == 0;
            //Capture
            else return between == 1;
        }
        return false;
    }

    // Movement(Rook, Cannon) & capture(Cannon) validation
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
