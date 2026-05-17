package ui;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class TableBackgroundPanel extends JPanel {
    private final Image backgroundImage;

    public TableBackgroundPanel(TablePanel tablePanel) {
        backgroundImage = new ImageIcon(
                Objects.requireNonNull(getClass().getResource("/assets/table/tablebackgroundimage.png"))
        ).getImage();

        setLayout(new BorderLayout());
        add(tablePanel, BorderLayout.CENTER);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}
