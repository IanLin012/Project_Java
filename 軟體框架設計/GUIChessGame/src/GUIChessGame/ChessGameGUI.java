package GUIChessGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Game GUI interface
 */
public class ChessGameGUI extends JFrame {
    private ChessGame game;
    private JButton[][] boardButtons;
    private JTextArea messageArea;
    private final int rows = 4, cols = 8; //Chess board size
    private String selectedFrom = null; //source location

    public ChessGameGUI() {
        game = new ChessGame();
        // Decide player side after the first chess flipped
        game.setPlayers(new Player("１號玩家", ""),
                new Player("２號玩家", ""));
        initUI();
    }

    private void initUI() {
        setTitle("台灣暗棋遊戲");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());

        // Construct board with row and col number
        JPanel boardPanel = new JPanel(new GridLayout(rows + 1, cols + 1));
        boardButtons = new JButton[rows][cols];

        // Display column label
        boardPanel.add(new JLabel(" "));
        for (int j = 0; j < cols; j++) {
            JLabel colLabel = new JLabel(String.valueOf(j + 1), SwingConstants.CENTER);
            boardPanel.add(colLabel);
        }
        // Display row label & board button
        for (int i = 0; i < rows; i++) {
            JLabel rowLabel = new JLabel(String.valueOf((char) ('A' + i)),
                    SwingConstants.CENTER);
            boardPanel.add(rowLabel);
            for (int j = 0; j < cols; j++) {
                JButton btn = new JButton();
                btn.setFont(new Font("Arial", Font.BOLD, 18));
                boardButtons[i][j] = btn;
                final int r = i, c = j;
                btn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        handleCellClick(r, c);
                    }
                });
                boardPanel.add(btn);
            }
        }
        add(boardPanel, BorderLayout.CENTER);

        // Display game message
        messageArea = new JTextArea();
        messageArea.setEditable(false);
        messageArea.setLineWrap(true);
        messageArea.setWrapStyleWord(true);
        JScrollPane sp = new JScrollPane(messageArea);
        sp.setPreferredSize(new Dimension(800, 150));
        add(sp, BorderLayout.SOUTH);

        refreshBoard();
    }

    // Chess click event
    private void handleCellClick(int row, int col) {
        // Convert row & col to location string
        String pos = "" + (char) ('A' + row) + (col + 1);
        Chess[][] board = game.getBoard();

        if (selectedFrom == null) {
            Chess piece = board[row][col];
            if (piece == null) {
                appendMessage("該位置沒有棋子！");
                return;
            }
            // Flip chess
            if (!piece.isFlipped()) {
                piece.flip();
                appendMessage(game.getCurrentPlayerName() + " 在 " + pos +
                        " 翻開了 " + piece.getName());
                // Decide player side with the first chess flipped
                if (game.getPlayer1().getSide() == null ||
                        game.getPlayer1().getSide().isEmpty()) {
                    game.getPlayer1().setSide(piece.getSide());
                    game.getPlayer2().setSide(piece.getSide().equals("Red") ?
                            "Black" : "Red");
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

            if (!piece.getSide().equals(game.getCurrentPlayerSide())) {
                appendMessage("這是對手的棋子！你是 " +
                        (game.getCurrentPlayerSide().equals("Red") ? "紅方" : "黑方"));
                return;
            }
            selectedFrom = pos;
            appendMessage("選擇來源位置：" + pos + "（" + piece.getName() + "）");
            // Display selected effect
            boardButtons[row][col].setBorder(BorderFactory.createLineBorder(
                    Color.YELLOW,3));
        } else { // Next click will be destination if source location valid
            String from = selectedFrom;
            String to = pos;
            // Remove selected effect
            int srcRow = from.charAt(0) - 'A';
            int srcCol = Integer.parseInt(from.substring(1)) - 1;
            boardButtons[srcRow][srcCol].setBorder(UIManager.getBorder("Button.border"));

            // Chess move & Add current game message to log area
            boolean result = game.move(from, to);
            String logMsg = game.getLogMessages();
            appendMessage(logMsg);
            if (result) {
                game.switchTurn();
            }
            selectedFrom = null;
            refreshBoard();
            checkGameOver();
        }
    }

    // Update chess board
    private void refreshBoard() {
        Chess[][] board = game.getBoard();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                JButton btn = boardButtons[i][j];
                Chess piece = board[i][j];
                if (piece == null) {
                    btn.setText("");
                } else {
                    String display = piece.toString();
                    btn.setText(display);
                    btn.setFont(new Font("微軟正黑體", Font.BOLD, 18));
                    if (piece.isFlipped()) {
                        if (piece.getSide().equals("Red"))
                            btn.setForeground(Color.RED);
                        else
                            btn.setForeground(Color.BLACK);
                    } else {
                        btn.setForeground(Color.GRAY);
                    }
                }
            }
        }
    }

    private void appendMessage(String msg) {
        messageArea.append(msg + "\n");
        messageArea.setCaretPosition(messageArea.getDocument().getLength());
    }

    private void checkGameOver() {
        if (game.gameOver()) {
            appendMessage("遊戲結束！\n" + game.getWinner());
            // Lock chess board
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    boardButtons[i][j].setEnabled(false);
                }
            }
        }
    }
}
