import game.BlackjackGame;
import game.GameState;
import game.Player;
import ui.BlackjackFrame;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Game {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(BlackjackFrame::new);
    }
}
