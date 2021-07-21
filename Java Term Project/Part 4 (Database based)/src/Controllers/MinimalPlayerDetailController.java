package Controllers;

import DataModel.Club;
import DataModel.Player;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import sample.Main;

import java.io.InputStream;
import java.util.Objects;

public class MinimalPlayerDetailController {
    private PlayerListViewController.PageType pageType;
    private Player player;
    private Main main;

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    @FXML
    private Label playerName;

    @FXML
    private Label clubName;

    @FXML
    private Label countryName;

    @FXML
    private Label age;

    @FXML
    private Label position;

    @FXML
    private Label salary;

    @FXML
    private Label fee;

    @FXML
    public VBox playerPane;

    @FXML
    private ImageView transferLabel;

    public VBox initiate(Player p, PlayerListViewController.PageType pageType) {
        this.pageType = pageType;
        this.player = p;
        playerName.setText(p.getName());
        clubName.setText(p.getClubName());
        countryName.setText(p.getCountry());
        age.setText(String.valueOf(p.getAge()));
        position.setText(p.getPosition());
        salary.setText(String.valueOf(p.getWeeklySalary()));
        if (p.isTransferListed()){
            fee.setText(String.valueOf(p.getTransferFee()));
            transferLabel.setImage(new Image(Objects.requireNonNull(Main.class.getResourceAsStream("/Assets/Image/Stamp.png"))));
        }
        else {
            fee.setText("N/A");
            transferLabel.setImage(null);
        }
        return playerPane;
    }
}
