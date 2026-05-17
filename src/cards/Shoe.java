package cards;

import util.ShuffleMethod;

import java.util.ArrayList;
import java.util.List;

public class Shoe {
    private final List<Card> shoe = new ArrayList<>();
    private final ShuffleMethod shuffleMethod;

    public Shoe(int decks, ShuffleMethod shuffleMethod) {
        this.shuffleMethod = shuffleMethod;
        populate(decks);
        shuffle();
    }

    private void populate(int decks) {
        for (int i = 0; i < decks; i++) {
            for (Suit suit : Suit.values()) {
                for (Rank rank : Rank.values()) {
                    shoe.add(new Card(rank, suit));
                }
            }
        }
    }

    public void shuffle() {
        shuffleMethod.shuffle(shoe);
    }

    public Card dealCard() {
        return shoe.removeFirst();
    }

    public int remainingCards() {
        return shoe.size();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Card card : shoe) {
            stringBuilder.append(card.toString());
        }
        return stringBuilder.toString();
    }
}
