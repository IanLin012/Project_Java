package ChineseChess.Factory;

import ChineseChess.Interface.*;
import ChineseChess.DarkChess.DarkChessGUI;
import ChineseChess.DarkChess.DarkChessGame;

import javax.swing.*;

public class DarkChessFactory implements ChessFactory {
    @Override
    public AbstractGame createGame() {
        return new DarkChessGame();
    }

    @Override
    public JFrame createGUI() {
        return new DarkChessGUI();
    }
}
