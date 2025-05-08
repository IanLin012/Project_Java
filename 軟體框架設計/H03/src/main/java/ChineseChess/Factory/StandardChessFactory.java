package ChineseChess.Factory;

import ChineseChess.Interface.*;
import ChineseChess.StandardChess.StandardChessGUI;
import ChineseChess.StandardChess.StandardChessGame;

import javax.swing.*;

public class StandardChessFactory implements ChessFactory {
    @Override
    public AbstractGame createGame() {
        return new StandardChessGame();
    }

    @Override
    public JFrame createGUI() {
        return new StandardChessGUI();
    }
}
