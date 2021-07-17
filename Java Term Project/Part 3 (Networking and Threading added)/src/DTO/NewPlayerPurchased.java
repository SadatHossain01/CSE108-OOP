package DTO;

import DataModel.Player;

import java.io.Serializable;

public class NewPlayerPurchased implements Serializable {
    private Player player;

    public NewPlayerPurchased(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }
}
