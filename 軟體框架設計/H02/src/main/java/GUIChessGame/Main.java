package GUIChessGame;

import javax.swing.*;

/**
 * Start game
 */
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ChessGameGUI game = new ChessGameGUI();
                game.setVisible(true);
            }
        });
    }
}
