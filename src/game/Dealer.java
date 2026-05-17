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

    public void reset() {
        hand.clear();
        hand.setState(HandState.PLAYING);
    }

    public String peekHand() {
        return "┌────────── DEALER HAND ──────────\n" + hand.toString(true) + "└──────────────────────────";
    }

    public String revealHand() {
        return "┌────────── DEALER HAND ──────────\n" + hand.toString(false) + "└──────────────────────────";
    }
}
