package game;

import cards.Card;

public class Player {
    private final Hand hand = new Hand();

    public void addCard(Card card) {
        hand.addCard(card);
    }

    public Hand getHand() {
        return hand;
    }

    @Override
    public String toString() {
        return "┌────────── PLAYER HAND ──────────\n" + hand.toString(false) + "└──────────────────────────";
    }
}
