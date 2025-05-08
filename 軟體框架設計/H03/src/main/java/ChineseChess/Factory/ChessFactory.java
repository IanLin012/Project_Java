package ChineseChess.Factory;

import ChineseChess.Interface.AbstractGame;

import javax.swing.*;

public interface ChessFactory {
    AbstractGame createGame();
    JFrame createGUI();
}
