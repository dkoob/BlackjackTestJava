package ui.views;

import game.Player;
import ui.CardSlot;
import ui.CardSpriteManager;
import ui.layouts.PlayerLayout;

import java.util.List;

public class PlayerView {
    private final Player player;
    private final PlayerLayout playerLayout;

    public PlayerView(Player player, CardSpriteManager cardSpriteManager) {
        this.player = player;
        this.playerLayout = new PlayerLayout(cardSpriteManager);
    }

    public Player getPlayer() {
        return player;
    }

    public void update() {
        playerLayout.update(player);
    }

    public List<CardSlot> getSlots() {
        return playerLayout.getSlots();
    }
}
