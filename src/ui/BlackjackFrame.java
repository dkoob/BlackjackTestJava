package ui;

import javax.swing.*;

public class BlackjackFrame extends JFrame {
    private final int width = 1600;
    private final int height = 900;

    public BlackjackFrame() {
        setTitle("Blackjack Game");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        try {
            setContentPane(new TableBackgroundPanel(new TablePanel()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
