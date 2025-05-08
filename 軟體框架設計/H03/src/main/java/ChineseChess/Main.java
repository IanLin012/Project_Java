package ChineseChess;

import javax.swing.*;

/**
 * Start chess game mode select GUI with Swing
 */
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new GameManager().setVisible(true);
        });
    }
}
