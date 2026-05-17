package ui;

import cards.Card;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class CardSpriteManager {
    private final BufferedImage frontSheet;
    private final BufferedImage backSheet;

    private final int CARD_WIDTH = 456;
    private final int CARD_HEIGHT = 712;

    public CardSpriteManager() throws IOException {
        frontSheet = ImageIO.read(
                Objects.requireNonNull(getClass().getResource("/assets/cards/playingcardsheet.png"))
        );

        backSheet = ImageIO.read(
                Objects.requireNonNull(getClass().getResource("/assets/cards/playingcardbackssheet.png"))
        );
    }

    public BufferedImage getFrontImage(Card card) {
        int row = card.getSuit().ordinal();
        int col = card.getRank().ordinal();

        return frontSheet.getSubimage(
                col * CARD_WIDTH,
                row * CARD_HEIGHT,
                CARD_WIDTH,
                CARD_HEIGHT
        );
    }

    public BufferedImage getBackImage(Card card) {
        int col = card.getSuit().ordinal();

        return backSheet.getSubimage(
                col * CARD_WIDTH,
                0,
                CARD_WIDTH,
                CARD_HEIGHT
        );
    }
}
