package game;

import cards.Card;
import cards.Rank;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private final List<Card> cards = new ArrayList<>();
    private HandState state= HandState.PLAYING;

    public void addCard(Card card) {
        cards.add(card);
        updateState();
    }

    public List<Card> getCards() {
        return cards;
    }

    public int getValue() {
        int total = 0;
        int aces = 0;

        for (Card card: cards) {
            total += card.getValue();
            if (card.getRank() == Rank.ACE) {
                aces++;
            }
        }

        while (total > 21 && aces > 0) {
            total -= 10;
            aces--;
        }

        return total;
    }

    public boolean isBust() {
        return getValue() > 21;
    }

    public boolean isBlackjack() {
        return cards.size() == 2 && getValue() == 21;
    }

    public void updateState() {
        if (isBust()) {
            state = HandState.BUST;
        } else if (isBlackjack()) {
            state = HandState.BLACKJACK;
        }
    }

    public void stand() {
        state = HandState.STANDING;
    }

    public HandState getState() {
        return state;
    }

    public void clear() {
        cards.clear();
    }

    public String toString(boolean hideFirstCard) {
        StringBuilder sb = new StringBuilder();

        int value = 0;

        for (int i = 0; i < cards.size(); i++) {
            if (hideFirstCard && i == 0) {
                sb.append("│ [HIDDEN]\n");
            } else {
                sb.append("│ ").append(cards.get(i));
                value += cards.get(i).getValue();
            }
        }

        sb.append("│\n");
        sb.append("│ Value: ").append(value).append("\n");
        sb.append("│ State: ").append(state).append("\n");

        return sb.toString();
    }
}
