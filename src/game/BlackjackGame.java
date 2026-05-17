package game;

import cards.Shoe;
import util.FisherYatesShuffle;

import java.util.List;

public class BlackjackGame {
    private final Shoe shoe;
    private final List<Player> players;
    private final Dealer dealer;

    private GameState gameState;
    private int currentPlayerIndex;

    private String tempStatus;

    public BlackjackGame(int decks, List<Player> players) {
        this.shoe = new Shoe(decks, new FisherYatesShuffle());
        this.players = players;
        this.dealer = new Dealer();
        this.gameState = GameState.WAITING_FOR_PLAYERS;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void startRound() {
        gameState = GameState.PLAYER_TURN;
        currentPlayerIndex = 0;

        dealer.reset();

        dealer.addCard(shoe.dealCard());
        dealer.addCard(shoe.dealCard());

        for (Player player : players) {
            player.getHand().clear();
            player.addCard(shoe.dealCard());
            player.addCard(shoe.dealCard());

            // temp, to be replaced with insurance handling for the dealer eventually
            if (player.getHand().isBlackjack() || dealer.getHand().isBlackjack()) {
                resolveRound();
            }
        }


    }

    public void handlePlayerTurn(PlayerAction action) {
        if (gameState != GameState.PLAYER_TURN) return;

        Player currentPlayer = players.get(currentPlayerIndex);

        if (currentPlayer.getHand().getState() != HandState.PLAYING) {
            moveToNextPlayer();
            return;
        }

        switch (action) {
            case HIT -> playerHit(currentPlayer);
            case STAND -> {
                currentPlayer.getHand().stand();
                moveToNextPlayer();
            }
        }
    }

    public void playerHit(Player player) {
        player.addCard(shoe.dealCard());

        if (player.getHand().getState() != HandState.PLAYING) {
            moveToNextPlayer();
        }
    }

    public void moveToNextPlayer() {
        currentPlayerIndex++;

        if (currentPlayerIndex >= players.size()) {
            gameState = GameState.DEALER_TURN;
        }
    }

    public void handleDealerTurn() {
        while (dealer.getHand().getValue() < 17) {
            dealer.addCard(shoe.dealCard());
        }
        gameState = GameState.ROUND_RESOLVING;
    }

    public void resolveRound() {
        int dealerValue = dealer.getHand().getValue();

        for (Player player : players) {
            int playerValue = player.getHand().getValue();

            if (player.getHand().isBlackjack()) {
                tempStatus = "Player Blackjack";
            } else if (dealer.getHand().isBlackjack()) {
                tempStatus = "Dealer Blackjack";
            } else if (player.getHand().getState() == HandState.BUST) {
                tempStatus = "Player busted";
            } else if (dealer.getHand().getState() == HandState.BUST) {
                tempStatus = "Dealer busted";
            } else if (playerValue > dealerValue) {
                tempStatus = "Player beat dealer";
            } else if (playerValue < dealerValue) {
                tempStatus = "Dealer beat player";
            } else {
                tempStatus = "Push";
            }
        }

        gameState = GameState.ROUND_OVER;
    }

    public String getTempStatus() {
        return tempStatus;
    }
}
