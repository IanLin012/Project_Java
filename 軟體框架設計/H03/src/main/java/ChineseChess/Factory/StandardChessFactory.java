package ChineseChess.Factory;

import ChineseChess.StandardChess.StandardChessGUI;

import javax.swing.*;

/**
 * Implement standard chess game factory
 */
public class StandardChessFactory implements ChessFactory {
    @Override
    public JFrame createGUI() {
        return new StandardChessGUI();
    }
}
