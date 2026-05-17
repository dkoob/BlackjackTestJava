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

        dealer.getHand().clear();

        for (Player player : players) {
            player.getHand().clear();
            player.addCard(shoe.dealCard());
            player.addCard(shoe.dealCard());
        }

        dealer.addCard(shoe.dealCard());
        dealer.addCard(shoe.dealCard());
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
            if (player.getHand().getState() == HandState.BUST) {
                System.out.println("Player busted");
            } else if (dealer.getHand().getState() == HandState.BUST) {
                System.out.println("Dealer busted");
            } else if (playerValue > dealerValue) {
                System.out.println("Player beat dealer");
            } else if (playerValue < dealerValue) {
                System.out.println("Dealer beat player");
            } else {
                System.out.println("Push");
            }
        }

        gameState = GameState.ROUND_OVER;
    }
}
