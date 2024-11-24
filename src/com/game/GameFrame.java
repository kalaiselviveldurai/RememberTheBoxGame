package com.game;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    public GameFrame() {
        setTitle("Remember the Box");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 500);
        setResizable(false);

        // Add Game Panel
        add(new GameLogic());
        setVisible(true);
    }
}
