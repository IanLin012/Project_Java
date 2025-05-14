package ChineseChess.DarkChess;

import ChineseChess.Interface.Player;

import javax.swing.*;
import java.awt.*;

/**
 * Dark chess game GUI
 */
public class DarkChessGUI extends JFrame {
    private DarkChessGame game;
    private JButton[][] boardButtons;
    private JTextArea messageArea;
    private final int rows = 4, cols = 8;
    private String selectedFrom = null;

    // Picture location
    private static final String IMG_PATH_BACK = "/images/back.png";
    private static final String IMG_PATH_PIECE = "/images/";

    public DarkChessGUI() {
        game = new DarkChessGame();
        //Initial unassigned player side
        game.setPlayers(
            new Player("玩家1", ""), new Player("玩家2", ""));
        initUI();
    }

    private void initUI() {
        setTitle("台灣暗棋遊戲");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 800);
        setLayout(new BorderLayout());

        JPanel boardPanel = new JPanel(new GridLayout(rows + 1, cols + 1));
        boardButtons = new JButton[rows][cols];

        boardPanel.add(new JLabel(" "));
        // Insert 1~8 label
        for (int j = 0; j < cols; j++) {
            boardPanel.add(
                new JLabel(String.valueOf(j + 1), SwingConstants.CENTER)
            );
        }
        // Insert A~D label & chess board button
        for (int i = 0; i < rows; i++) {
            boardPanel.add(
                new JLabel(String.valueOf((char)('A'+i)), SwingConstants.CENTER)
            );
            for (int j = 0; j < cols; j++) {
                JButton btn = new JButton();
                btn.setPreferredSize(new Dimension(64,64));
                boardButtons[i][j] = btn;
                final int r = i, c = j;
                btn.addActionListener(e -> handleCellClick(r, c));
                boardPanel.add(btn);
            }
        }
        add(boardPanel, BorderLayout.CENTER);

        messageArea = new JTextArea();
        messageArea.setEditable(false);
        messageArea.setLineWrap(true);
        messageArea.setWrapStyleWord(true);
        JScrollPane sp = new JScrollPane(messageArea);
        sp.setPreferredSize(new Dimension(800,150));
        add(sp, BorderLayout.SOUTH);

        refreshBoard();
    }

    // Implement click event
    private void handleCellClick(int row, int col) {
        String pos = "" + (char)('A'+row) + (col+1);
        DarkChess[][] board = game.getBoard();

        if (selectedFrom == null) {
            DarkChess piece = board[row][col];
            if (piece == null) {
                appendMessage("該位置沒有棋子！");
                return;
            }
            if (!piece.isFlipped()) {
                piece.flip();
                appendMessage(game.getCurrentPlayerName() +
                    " 在 " + pos + " 翻開了 " + piece.getName());
                if (game.getPlayer1().getSide().isEmpty()) {
                    game.getPlayer1().setSide(piece.getSide());
                    game.getPlayer2().setSide(
                        piece.getSide().equals("Red") ? "Black" : "Red");
                    appendMessage("玩家1 為 " +
                        (game.getPlayer1().getSide().equals("Red") ? "紅方" : "黑方"));
                    appendMessage("玩家2 為 " +
                        (game.getPlayer2().getSide().equals("Red") ? "紅方" : "黑方"));
                }
                game.switchTurn();
                refreshBoard();
                checkGameOver();
                return;
            }
            // Chess selection validation
            if (!piece.getSide().equals(game.getCurrentPlayerSide())) {
                appendMessage("現在是 " +
                    (game.getCurrentPlayerSide().equals("Red") ? "紅方" : "黑方") +
                    " 的回合！");
                return;
            }
            selectedFrom = pos;
            appendMessage("選擇來源位置：" + pos + "（" + piece.getName() + "）");
            boardButtons[row][col].setBorder(
                    BorderFactory.createLineBorder(Color.YELLOW,3));
        } else { // Execute movement
            String from = selectedFrom, to = pos;
            int srcRow = from.charAt(0)-'A', srcCol = Integer.parseInt(from.substring(1))-1;
            boardButtons[srcRow][srcCol].setBorder(
                    UIManager.getBorder("Button.border"));
            boolean result = game.move(from, to);
            appendMessage(game.getLogMessages());
            if (result) game.switchTurn();
            selectedFrom = null;
            refreshBoard();
            checkGameOver();
        }
    }

    private void refreshBoard() {
        DarkChess[][] board = game.getBoard();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                JButton btn = boardButtons[i][j];
                DarkChess piece = board[i][j];
                btn.setIcon(null);
                btn.setText("");
                if (piece != null) {
                    String imgPath = piece.isFlipped()
                            ? IMG_PATH_PIECE + piece.getName() + ".png"
                            : IMG_PATH_BACK;
                    ImageIcon icon = new ImageIcon(getClass().getResource(imgPath));
                    btn.setIcon(icon);
                }
            }
        }
    }

    // Append game message in message area
    private void appendMessage(String msg) {
        messageArea.append(msg + "\n");
        messageArea.setCaretPosition(messageArea.getDocument().getLength());
    }

    private void checkGameOver() {
        if (game.gameOver()) {
            appendMessage("遊戲結束！\n" + game.getWinner());
            for (JButton[] row : boardButtons)
                for (JButton b : row)
                    b.setEnabled(false);
        }
    }
}
