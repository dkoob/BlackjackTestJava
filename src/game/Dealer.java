package game;

import cards.Card;

public class Dealer {
    private final Hand hand = new Hand();

    public void addCard(Card card) {
        hand.addCard(card);
    }

    public Hand getHand() {
        return hand;
    }

    public String peekHand() {
        return "┌────────── DEALER HAND ──────────\n" + hand.toString(true) + "└──────────────────────────";
    }

    public String revealHand() {
        return "┌────────── DEALER HAND ──────────\n" + hand.toString(false) + "└──────────────────────────";
    }
}
