package ChineseChess.StandardChess;

import ChineseChess.Interface.Chess;
import ChineseChess.Interface.Player;

import javax.swing.*;
import java.awt.*;

/**
 * Standard chess game GUI
 */
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
        setSize(800, 800);
        setLayout(new BorderLayout());

        JPanel boardPanel = new JPanel(new GridLayout(rows + 2, cols + 1)); // +2: 因為插入了一行楚河漢界
        buttons = new JButton[rows][cols];

        // Insert 1~9 label
        boardPanel.add(new JLabel());
        for (int j = 1; j <= cols; j++) {
            boardPanel.add(new JLabel(String.valueOf(j), SwingConstants.CENTER));
        }

        // Insert chess board button & (楚河 漢界)
        for (int i = 0; i < rows; i++) {
            //Insert (楚河 漢界) between E & F
            if (i == 5) { //Complete E row
                boardPanel.add(new JLabel()); //Left side blank row
                for (int j = 0; j < cols; j++) {
                    if (j == 0) {
                        JLabel chu = new JLabel("楚河", SwingConstants.LEFT);
                        chu.setFont(new Font("Serif", Font.BOLD, 30));
                        chu.setForeground(Color.BLACK);
                        boardPanel.add(chu);
                    } else if (j == cols - 1) {
                        JLabel han = new JLabel("漢界", SwingConstants.RIGHT);
                        han.setFont(new Font("Serif", Font.BOLD, 30));
                        han.setForeground(Color.RED);
                        boardPanel.add(han);
                    } else {
                        boardPanel.add(new JLabel()); //Blank col
                    }
                }
            }

            // Insert A~J label
            boardPanel.add(new JLabel(String.valueOf((char) ('A' + i)), SwingConstants.CENTER));
            for (int j = 0; j < cols; j++) {
                JButton btn = new JButton();
                btn.setPreferredSize(new Dimension(60, 60));
                final int r = i, c = j;
                btn.addActionListener(e -> onClick(r, c));
                buttons[i][j] = btn;
                boardPanel.add(btn);
            }
        }

        add(boardPanel, BorderLayout.CENTER);

        statusBar = new JLabel("", SwingConstants.CENTER);
        statusBar.setPreferredSize(new Dimension(getWidth(), 30));
        add(statusBar, BorderLayout.SOUTH);

        refreshBoard();
    }

    private void onClick(int r, int c) {
        String pos = "" + (char)('A'+r) + (c+1);
        if (selected == null) {
            if (game.getBoard()[r][c] == null) {
                statusBar.setText("該位置沒有棋子");
                return;
            }
            if (!game.getBoard()[r][c].getSide().equals(game.getCurrentSide())) {
                statusBar.setText("現在是" + (game.getCurrentSide().equals("Red") ? "紅方" : "黑方") + "的回合");
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
                statusBar.setText("移動失敗: " + selected + "→" + pos);
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
}
