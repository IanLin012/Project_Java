package ChineseChess;

import ChineseChess.Factory.ChessFactory;
import ChineseChess.Factory.DarkChessFactory;
import ChineseChess.Factory.StandardChessFactory;

import javax.swing.*;
import java.awt.*;

public class GameManager extends JFrame {
    public GameManager() {
        setTitle("選擇棋類模式");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300, 150);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 1, 10, 10));

        JButton btnStandard = new JButton("標準象棋");
        JButton btnDark     = new JButton("台灣暗棋");
        JButton btnExit     = new JButton("離開");

        btnStandard.addActionListener(e -> launchGUI(new StandardChessFactory()));
        btnDark    .addActionListener(e -> launchGUI(new DarkChessFactory()));
        btnExit    .addActionListener(e -> System.exit(0));

        add(new JLabel("請選擇遊戲模式：", SwingConstants.CENTER));
        add(btnStandard);
        add(btnDark);
        add(btnExit);
    }

    private void launchGUI(ChessFactory factory) {
        this.dispose();
        JFrame gui = factory.createGUI();
        gui.setVisible(true);
    }
    /**
    private void launchGUI(String s) {
        // 關閉或隱藏 Launcher
        this.dispose();
        // 初始化並顯示選擇的遊戲 GUI
        if(s.equals("StandardChess")){ new StandardChessGUI().setVisible(true); }
        else if(s.equals("DarkChess")){ new DarkChessGUI().setVisible(true); }
    }*/
}

