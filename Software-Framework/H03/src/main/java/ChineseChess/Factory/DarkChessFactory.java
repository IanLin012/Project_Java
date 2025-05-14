package ChineseChess.Factory;

import ChineseChess.DarkChess.DarkChessGUI;

import javax.swing.*;

/**
 * Implement dark chess game factory
 */
public class DarkChessFactory implements ChessFactory {
    @Override
    public JFrame createGUI() {
        return new DarkChessGUI();
    }
}
