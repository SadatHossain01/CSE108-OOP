package sample;

import DataModel.Player;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.util.List;

public class PlayerListViewController {
    Main main;

    public void setMain(Main main) {
        this.main = main;
    }

    @FXML
    private ListView<HBox> listView;

    void initiate(List<Player> players) throws IOException {
        for (var p : players) {
            var newLoader = new FXMLLoader();
            newLoader.setLocation(getClass().getResource("/ViewFX/SinglePlayerDetailView.fxml"));
            newLoader.load();
            SinglePlayerDetailController pDetail = newLoader.getController();
            listView.getItems().add(pDetail.initiate(p));
        }
    }

}
