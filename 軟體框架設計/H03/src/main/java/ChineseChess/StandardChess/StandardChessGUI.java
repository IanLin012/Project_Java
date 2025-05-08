package ChineseChess.StandardChess;

import ChineseChess.Interface.Chess;
import ChineseChess.Interface.Player;

import javax.swing.*;
import java.awt.*;

public class StandardChessGUI extends JFrame {
    private StandardChessGame game;
    private JButton[][] buttons;
    private JLabel statusBar;
    private String selected = null;
    private final int rows = 10, cols = 9;
    private static final String IMG_PATH = "/images/";

    public StandardChessGUI() {
        game = new StandardChessGame();
        game.setPlayers(new Player("玩家1", "Red"), new Player("玩家2", "Black"));
        initUI();
    }

    private void initUI() {
        setTitle("標準象棋");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 900);
        setLayout(new BorderLayout());

        JPanel boardPanel = new JPanel(new GridLayout(rows+1, cols+1));
        buttons = new JButton[rows][cols];

        boardPanel.add(new JLabel());
        for (int j=1; j<=cols; j++) boardPanel.add(new JLabel(String.valueOf(j), SwingConstants.CENTER));
        for (int i=0; i<rows; i++) {
            boardPanel.add(new JLabel(String.valueOf((char)('A'+i)), SwingConstants.CENTER));
            for (int j=0; j<cols; j++) {
                JButton btn = new JButton();
                btn.setPreferredSize(new Dimension(60,60));
                final int r=i, c=j;
                btn.addActionListener(e -> onClick(r, c));
                buttons[i][j] = btn;
                boardPanel.add(btn);
            }
        }
        add(boardPanel, BorderLayout.CENTER);

        statusBar = new JLabel("" , SwingConstants.CENTER);
        statusBar.setPreferredSize(new Dimension(getWidth(), 30));
        add(statusBar, BorderLayout.SOUTH);

        refreshBoard();
    }

    private void onClick(int r, int c) {
        String pos = "" + (char)('A'+r) + (c+1);
        if (selected == null) {
            if (game.getBoard()[r][c] == null) {
                statusBar.setText("無棋子");
                return;
            }
            if (!game.getBoard()[r][c].getSide().equals(game.getCurrentSide())) {
                statusBar.setText("非輪到方");
                return;
            }
            selected = pos;
            buttons[r][c].setBorder(BorderFactory.createLineBorder(Color.YELLOW, 3));
            statusBar.setText("已選擇: " + pos);
        } else {
            int sr = selected.charAt(0)-'A', sc = Integer.parseInt(selected.substring(1))-1;
            buttons[sr][sc].setBorder(UIManager.getBorder("Button.border"));
            boolean ok = game.move(selected, pos);
            if (ok) {
                game.switchTurn();
                statusBar.setText("移動成功: " + selected + "→" + pos);
            } else {
                statusBar.setText("非法走法: " + selected + "→" + pos);
            }
            selected = null;
            refreshBoard();
            if (game.gameOver()) {
                JOptionPane.showMessageDialog(this, game.getWinner());
                disableAll();
            }
        }
    }

    private void refreshBoard() {
        Chess[][] b = game.getBoard();
        for (int i=0; i<rows; i++) {
            for (int j=0; j<cols; j++) {
                JButton btn = buttons[i][j];
                btn.setIcon(null);
                Chess p = b[i][j];
                if (p != null) {
                    ImageIcon icon = new ImageIcon(getClass().getResource(IMG_PATH + p.getName() + ".png"));
                    btn.setIcon(icon);
                }
            }
        }
    }

    private void disableAll() {
        for (JButton[] row : buttons)
            for (JButton btn : row)
                btn.setEnabled(false);
    }

    /*
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new StandardChessGUI().setVisible(true));
    }
    */
}
