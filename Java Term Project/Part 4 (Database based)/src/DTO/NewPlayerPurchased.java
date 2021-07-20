package DTO;

import DataModel.Player;

import java.io.Serializable;

public class NewPlayerPurchased implements Serializable {
    private Player player;
    private String buyer, seller;

    public NewPlayerPurchased(Player player, String buyer, String seller) {
        this.player = player;
        this.buyer = buyer;
        this.seller = seller;
    }

    public Player getPlayer() {
        return player;
    }

    public String getBuyer() {
        return buyer;
    }

    public String getSeller() {
        return seller;
    }
}
