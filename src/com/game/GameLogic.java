package com.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class GameLogic extends JPanel {
    private static final int GRID_SIZE = 4;
    private static final int TOTAL_SQUARES = GRID_SIZE * GRID_SIZE;
    private JButton[] buttons = new JButton[TOTAL_SQUARES];
    private List<Integer> sequence = new ArrayList<>();
    private int level = 1;
    private int currentIndex = 0;

    public GameLogic() {
        setLayout(new GridLayout(GRID_SIZE, GRID_SIZE));

        for (int i = 0; i < TOTAL_SQUARES; i++) {
            buttons[i] = new JButton();
            buttons[i].setBackground(Color.LIGHT_GRAY);
            buttons[i].setEnabled(false);
            final int index = i;
            buttons[i].addActionListener(e -> handleUserClick(index));
            add(buttons[i]);
        }

        startLevel();
    }

    private void startLevel() {
        sequence.clear();
        currentIndex = 0;

        int blinkCount = level * 4;
        for (int i = 0; i < blinkCount; i++) {
            sequence.add((int) (Math.random() * TOTAL_SQUARES));
        }

        blinkSequence(0);
    }

    private void blinkSequence(int step) {
        if (step < sequence.size()) {
            int index = sequence.get(step);
            buttons[index].setBackground(Color.YELLOW);
            Timer timer = new Timer(500, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    buttons[index].setBackground(Color.LIGHT_GRAY);
                    ((Timer) e.getSource()).stop();
                    blinkSequence(step + 1);
                }
            });
            timer.setRepeats(false);
            timer.start();
        } else {
            enableButtons();
        }
    }

    private void enableButtons() {
        for (JButton button : buttons) {
            button.setEnabled(true);
        }
    }

    private void disableButtons() {
        for (JButton button : buttons) {
            button.setEnabled(false);
        }
    }

    private void handleUserClick(int index) {
        if (index == sequence.get(currentIndex)) {
            currentIndex++;
            if (currentIndex == sequence.size()) {
                disableButtons();
                if (level == 3) {
                    JOptionPane.showMessageDialog(this, "Congratulations! You won the game!");
                    System.exit(0);
                } else {
                    JOptionPane.showMessageDialog(this, "Level " + level + " complete!");
                    level++;
                    startLevel();
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Game Over! You clicked the wrong box.");
            System.exit(0);
        }
    }
}
