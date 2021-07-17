package DTO;

import DataModel.Player;

import java.io.Serializable;
import java.util.List;

public class UpdatedTransferList implements Serializable {
    List<Player> playerList;
    String toWhichClub;

    public UpdatedTransferList(List<Player> playerList, String toWhichClub) {
        this.playerList = playerList;
        this.toWhichClub = toWhichClub;
    }

    public List<Player> getPlayerList() {
        return playerList;
    }

    public String getToWhichClub() {
        return toWhichClub;
    }
}
