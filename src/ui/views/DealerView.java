package ui.views;

import game.Dealer;
import ui.CardSlot;
import ui.CardSpriteManager;
import ui.layouts.DealerLayout;
import ui.layouts.Layout;

import java.util.List;

public class DealerView {
    private final Dealer dealer;
    private final DealerLayout dealerLayout;

    public DealerView(Dealer dealer, CardSpriteManager cardSpriteManager) {
        this.dealer = dealer;
        this.dealerLayout = new DealerLayout(cardSpriteManager);
    }

    public Dealer getDealer() {
        return dealer;
    }

    public void update() {
        dealerLayout.update(dealer);
    }

    public List<CardSlot> getSlots() {
        return dealerLayout.getSlots();
    }

    public void hideHoleCard() {
        dealerLayout.setHideHoleCard(true);
    }

    public void revealHoleCard() {
        dealerLayout.setHideHoleCard(false);
    }
}
