package Controllers;

import DataModel.Club;
import DataModel.Player;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import sample.Main;

import java.io.InputStream;
import java.util.Objects;

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
        var cName = player.getCountry();
        var club_name = player.getClubName();

        try {
            Image pImage = new Image(player.getImageSource(), true);
            playerImage.setImage(pImage);
        } catch (Exception e) {
            var loaded = Main.class.getResourceAsStream("/Assets/Image/Player Image/Anonymous.jpg");
            playerImage.setImage(new Image(loaded));
        }

        Image flagImage = null;
        try {
            var what = main.countryFlagMap.get(player.getCountry());
            if (what != null) flagImage = new Image(what, true);
            else throw new Exception("");
        } catch (Exception e) {
            flagImage = new Image("file:/src/Assets/Image/Flags/" + countryName + ".png", true);
        }
        flag.setImage(flagImage);

        try {
            logo.setImage(new Image(Objects.requireNonNull(Main.class.getResourceAsStream("/Assets/Image/Club Logo/" + clubName + ".png"))));
        } catch (Exception e) {
            logo.setImage(new Image(Objects.requireNonNull(Main.class.getResourceAsStream("/Assets/Image/No_Image.png")), 150, 150, true, true));
        }

        countryName.setText(cName);
        clubName.setText(club_name);
        pName.setText(player.getName());
        pDoB.setText(player.getDoB());
        pClub.setText(club_name);
        pCountry.setText(player.getCountry());
        pHeight.setText(String.valueOf(player.getHeight()));
        pWeight.setText(String.valueOf(player.getWeight()));
        pPosition.setText(player.getPosition());
        pNumber.setText(String.valueOf(player.getNumber()));
        pFoot.setText(String.valueOf(player.getPreferredFoot()));
        pSalary.setText(Club.showSalary(player.getWeeklySalary()));
        if (player.isTransferListed()) {
            pStatus.setText("Up for sale");
            pFee.setText(Club.showSalary(player.getTransferFee()));
        }
        else {
            pStatus.setText("Not for sale");
            pFee.setText("N/A");
        }
    }
}
