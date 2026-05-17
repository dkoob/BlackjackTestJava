package ui.layouts;

import game.Dealer;
import game.Hand;
import ui.CardSpriteManager;

public class DealerLayout extends Layout<Dealer> {
    private boolean hideHoleCard = true;

    public DealerLayout(CardSpriteManager cardSpriteManager) {
        super(cardSpriteManager);
    }

    public void setHideHoleCard(boolean hideHoleCard) {
        this.hideHoleCard = hideHoleCard;
    }

    @Override
    protected Hand getHand(Dealer dealer) {
        return dealer.getHand();
    }

    @Override
    protected int getSlotOneX() {
        return 611;
    }

    @Override
    protected int getSlotOneY() {
        return 107;
    }

    @Override
    protected int getSlotTwoX() {
        return 808;
    }

    @Override
    protected int getSlotTwoY() {
        return 107;
    }

    @Override
    protected boolean shouldHideCard(int index) {
        return hideHoleCard && index == 1;
    }
}
