package ui;

import cards.Card;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class CardSlot extends JPanel {
    private Image img;
    private Card card;
    private final CardSpriteManager cardSpriteManager;
    private boolean faceDown = false;

    public CardSlot(CardSpriteManager cardSpriteManager) {
        this.cardSpriteManager = cardSpriteManager;

        setOpaque(false);
        setPreferredSize(new Dimension(165, 222));
    }

    public void setCard(Card card, boolean faceDown) {
        this.card = card;
        this.faceDown = faceDown;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (card == null) {
            img = new ImageIcon(
                    Objects.requireNonNull(getClass().getResource("/assets/table/cardslot.png"))
            ).getImage();
        } else if (faceDown) {
            img = cardSpriteManager.getBackImage(card);
        } else {
            img = cardSpriteManager.getFrontImage(card);
        }

        g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
    }
}
