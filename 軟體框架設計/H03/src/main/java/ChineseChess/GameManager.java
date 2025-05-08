package ChineseChess;

import ChineseChess.Factory.ChessFactory;
import ChineseChess.Factory.DarkChessFactory;
import ChineseChess.Factory.StandardChessFactory;

import javax.swing.*;
import java.awt.*;

/**
 * Chess game mode select GUI
 */
public class GameManager extends JFrame {
    public GameManager() {
        setTitle("遊戲模式選擇");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300, 150);
        setLocationRelativeTo(null);
        //Arrange component vertical
        setLayout(new GridLayout(3, 1, 10, 10));

        JButton btnStandard = new JButton("標準象棋");
        JButton btnDark = new JButton("台灣暗棋");

        //Build chess game factory after button click
        btnStandard.addActionListener(
                e -> launchGUI(new StandardChessFactory()));
        btnDark.addActionListener(
                e -> launchGUI(new DarkChessFactory()));

        add(new JLabel("請選擇遊戲模式：", SwingConstants.CENTER));
        add(btnStandard);
        add(btnDark);
    }

    // Start chess game GUI with factory
    private void launchGUI(ChessFactory factory) {
        this.dispose(); //Close game mode select GUI
        JFrame gui = factory.createGUI();
        gui.setVisible(true);
    }
}

