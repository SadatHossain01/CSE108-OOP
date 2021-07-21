package Controllers;

import DataModel.Player;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import sample.Main;

public class PlayerCardController {
    private Main main;
    private Stage stage;
    private Player p;

    @FXML
    private ImageView playerImage;

    @FXML
    private ImageView flag;

    @FXML
    private ImageView logo;

    @FXML
    private Label countryName;

    @FXML
    private Label clubName;

    @FXML
    private Label pName;

    @FXML
    private Label pDoB;

    @FXML
    private Label pClub;

    @FXML
    private Label pCountry;

    @FXML
    private Label pHeight;

    @FXML
    private Label pWeight;

    @FXML
    private Label pPosition;

    @FXML
    private Label pNumber;

    @FXML
    private Label pFoot;

    @FXML
    private Label pSalary;

    @FXML
    private Label pStatus;

    @FXML
    private Label pFee;

    public void setMain(Main main) {
        this.main = main;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void initiate(Player player) {
        this.p = player;
    }
}
