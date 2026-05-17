package ui;

import cards.Card;
import game.*;
import ui.views.DealerView;
import ui.views.PlayerView;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static util.UIHelpers.*;

public class TablePanel extends JPanel {
    private final CardSpriteManager cardSpriteManager = new CardSpriteManager();
    private final BlackjackGame game;
    private List<PlayerView> playerViews;
    private DealerView dealerView;

    List<Player> players;

    CardSlot initialDealerSlot1 = new CardSlot(cardSpriteManager);
    CardSlot initialDealerSlot2 = new CardSlot(cardSpriteManager);

    CardSlot initialPlayerSlot1 = new CardSlot(cardSpriteManager);
    CardSlot initialPlayerSlot2 = new CardSlot(cardSpriteManager);

    // all temp stuff
    JButton dealButton = new JButton("Deal Cards");
    JButton hitButton = new JButton("Hit");
    JButton standButton = new JButton("Stand");
    JButton resetButton = new JButton("Reset");
    JLabel statusLabel = new JLabel("Init");

    public TablePanel() throws IOException {
        players = new ArrayList<>();
        game = new BlackjackGame(1, players);

        initializeTablePanel();

        setLayout(null);
        setOpaque(false);

        statusLabel.setForeground(Color.WHITE);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 24));
        statusLabel.setOpaque(true);
        statusLabel.setHorizontalAlignment(JLabel.CENTER);

        dealButton.setBackground(Color.RED);
        dealButton.setForeground(Color.WHITE);
        dealButton.setOpaque(true);
        dealButton.setFocusable(false);
        dealButton.setFont(new Font("Arial", Font.BOLD, 16));
        dealButton.addActionListener(e -> {
            game.startRound();
            updateFromGame();
        });

        hitButton.setBackground(Color.BLACK);
        hitButton.setForeground(Color.WHITE);
        hitButton.setOpaque(true);
        hitButton.setFocusable(false);
        hitButton.setFont(new Font("Arial", Font.BOLD, 16));
        hitButton.addActionListener(e -> {
            game.handlePlayerTurn(PlayerAction.HIT);
            updateFromGame();
            checkGameProgression();
        });

        standButton.setBackground(Color.BLUE);
        standButton.setForeground(Color.WHITE);
        standButton.setOpaque(true);
        standButton.setFocusable(false);
        standButton.setFont(new Font("Arial", Font.BOLD, 16));
        standButton.addActionListener(e -> {
            game.handlePlayerTurn(PlayerAction.STAND);
            updateFromGame();
            checkGameProgression();
        });

        resetButton.setBackground(Color.GREEN);
        resetButton.setForeground(Color.WHITE);
        resetButton.setOpaque(true);
        resetButton.setFocusable(false);
        resetButton.setFont(new Font("Arial", Font.BOLD, 16));
        resetButton.addActionListener(e -> {
            clearCards();
            initializeTablePanel();
            updateFromGame();
        });

        initializeCardSlots();

        add(dealButton);
        add(hitButton);
        add(standButton);
        add(resetButton);
        add(statusLabel);
        placeCentered(dealButton, 800, 800);
        placeCentered(hitButton, 900, 800);
        placeCentered(standButton, 700, 800);
        placeCentered(resetButton, 1000, 800);
        placeCentered(statusLabel, 800, 450);
    }

    private void checkGameProgression() {
        if (game.getGameState() == GameState.DEALER_TURN) {
            dealerView.revealHoleCard();
            game.handleDealerTurn();
            updateFromGame();
            game.resolveRound();
            updateFromGame();
        }
    }

    private void updateFromGame() {
        for (PlayerView view : playerViews) {
            view.update();
            for (CardSlot slot : view.getSlots().reversed()) {
                add(slot);
            }
        }

        dealerView.update();
        for (CardSlot slot : dealerView.getSlots().reversed()) {
            add(slot);
        }

        refreshTable();
    }

    private void refreshTable() {
        repaint();
    }

    /* Initializers
    initializeCardSlots creates the first 4 card slots and populates them with the placeholder sprite.
     */
    private void initializeCardSlots() {
        add(initialDealerSlot1);
        add(initialDealerSlot2);
        add(initialPlayerSlot1);
        add(initialPlayerSlot2);

        place(initialDealerSlot1, 611, 107);
        place(initialDealerSlot2, 808, 107);
        place(initialPlayerSlot1, 611, 485);
        place(initialPlayerSlot2, 808, 485);
    }

    private void initializeTablePanel() {
        if (players != null) {
            players.clear();
        }
        players.add(new Player());

        if (playerViews != null) {
            playerViews.clear();
        }

        playerViews = new ArrayList<>();
        for (Player p : players) {
            playerViews.add(new PlayerView(p, cardSpriteManager));
        }

        dealerView = null;
        dealerView = new DealerView(game.getDealer(), cardSpriteManager);
        dealerView.hideHoleCard();
    }

    private void clearCards() {
        for (PlayerView view : playerViews) {
            for (CardSlot slot : view.getSlots()) {
                remove(slot);
            }
        }

        for (CardSlot slot : dealerView.getSlots()) {
            remove(slot);
        }

        repaint();
        revalidate();
    }
}
