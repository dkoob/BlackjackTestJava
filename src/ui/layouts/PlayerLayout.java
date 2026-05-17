package ui.layouts;

import game.Hand;
import game.Player;
import ui.CardSpriteManager;

import java.awt.*;

public class PlayerLayout extends Layout<Player>{
    public PlayerLayout(CardSpriteManager cardSpriteManager) {
        super(cardSpriteManager);
    }

    @Override
    protected Hand getHand(Player player) {
        return player.getHand();
    }

    @Override
    protected int getSlotOneX() {
        return 619;
    }

    @Override
    protected int getSlotOneY() {
        return 485;
    }

    @Override
    protected int getSlotTwoX() {
        return 816;
    }

    @Override
    protected int getSlotTwoY() {
        return 485;
    }
}
