package ui.layouts;

import cards.Card;
import game.Hand;
import ui.CardSlot;
import ui.CardSpriteManager;

import java.util.ArrayList;
import java.util.List;

import static util.UIHelpers.place;

public abstract class Layout<T> {
    private List<CardSlot> slots;
    private CardSpriteManager cardSpriteManager;

    private final int overlapX = 80;

    public Layout(CardSpriteManager cardSpriteManager) {
        this.cardSpriteManager = cardSpriteManager;
    }

    public List<CardSlot> getSlots() {
        return slots;
    }

    public void initialize(T target) {
        List<Card> cards = getHand(target).getCards();

        slots = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            CardSlot slot = new CardSlot(cardSpriteManager);
            slots.add(slot);

            slot.setCard(cards.get(i), shouldHideCard(i));

            switch (i) {
                case 0 -> place(slot, getSlotOneX(), getSlotOneY());
                case 1 -> place(slot, getSlotTwoX(), getSlotTwoY());
            }
        }
    }

    public void update(T target) {
        List<Card> cards = getHand(target).getCards();

        if (slots == null) {
            initialize(target);
            return;
        }

        for (int i = 0; i < cards.size(); i++) {
            CardSlot slot = getOrCreateSlot(i);
            slot.setCard(cards.get(i), shouldHideCard(i));

            int indexFromRight = (cards.size() - 2) - i;

            int x = getSlotOneX() - (overlapX * indexFromRight);
            int y = getSlotOneY();

            if (i < cards.size() - 1) {
                place(slot, x, y);
            } else {
                place(slot, getSlotTwoX(), getSlotTwoY());
            }
        }
    }

    private CardSlot getOrCreateSlot(int i) {
        while (slots.size() <= i) {
            CardSlot slot = new CardSlot(cardSpriteManager);
            slots.add(slot);
        }
        return slots.get(i);
    }

    protected abstract Hand getHand(T target);

    protected abstract int getSlotOneX();
    protected abstract int getSlotOneY();
    protected abstract int getSlotTwoX();
    protected abstract int getSlotTwoY();

    protected boolean shouldHideCard(int i) {
        return false;
    }
}
