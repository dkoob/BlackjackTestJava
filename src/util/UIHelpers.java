package util;

import javax.swing.*;
import java.awt.*;

public class UIHelpers {
    public static void place(JComponent component, int x, int y) {
        Dimension dimension = component.getPreferredSize();
        component.setBounds(x, y, dimension.width, dimension.height);
    }

    public static void placeCentered(JComponent component, int centerX, int y) {
        Dimension dimension = component.getPreferredSize();
        component.setBounds(centerX - dimension.width / 2, y, dimension.width, dimension.height);
    }
}
