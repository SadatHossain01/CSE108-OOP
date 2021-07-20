package Controllers;

import DTO.BuyRequest;
import DataModel.Club;
import DataModel.Player;
import com.jfoenix.controls.JFXButton;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import sample.Main;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class SinglePlayerDetailController {

    private PlayerListViewController.PageType pageType;
    private Player player;
    private Main main;

    public void setMain(Main main) {
        this.main = main;
    }

    public Player getPlayer() {
        return player;
    }

    public Label getTransferLabel1() {
        return TransferLabel1;
    }

    public Label getTransferLabel2() {
        return TransferLabel2;
    }

    public ImageView getTransferTag() {
        return transferTag;
    }

    public JFXButton getTransferStatusButton() {
        return TransferStatusButton;
    }

    @FXML
    public VBox playerCard;

    @FXML
    private ImageView playerImage;

    @FXML
    private Label Name;

    @FXML
    private Label club;

    @FXML
    private Label Country;

    @FXML
    private Label Age;

    @FXML
    private Label Height;

    @FXML
    private Label Position;

    @FXML
    private Label Number;

    @FXML
    private Label Salary;

    @FXML
    private ImageView transferTag;

    @FXML
    private JFXButton TransferStatusButton;

    @FXML
    private Label TransferLabel1;

    @FXML
    private Label TransferLabel2;

    public VBox initiate(Player p, PlayerListViewController.PageType pageType) {
        this.pageType = pageType;
        this.player = p;
        System.out.println(p.getName());
//        var loaded = Main.class.getResourceAsStream("/Assets/Image/Player Image/" + p.getName() + ".jpeg");
//        if (loaded == null)
//            loaded = Main.class.getResourceAsStream("/Assets/Image/Player Image/" + p.getName() + ".jpg");
        InputStream loaded = null;
        try {
            Image img = new Image(player.getImageSource(), true);
            img.progressProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                    System.out.println("Progress: " + Math.rint(newValue.doubleValue() * 100) + "%");
                }
            });
            playerImage.setImage(img);
        } catch (Exception e){
            loaded = Main.class.getResourceAsStream("/Assets/Image/Player Image/Anonymous.jpg");
            playerImage.setImage(new Image(loaded));
        }
//        playerImage.setImage(new Image(loaded, 440, 220, false, true));
//        if (p.isTransferListed())
//            transferTag.setImage(new Image(Objects.requireNonNull(Main.class.getResourceAsStream("/Assets/Image/Rotated Seal.png"))));
        if (p.isTransferListed())
            transferTag.setImage(new Image(Objects.requireNonNull(Main.class.getResourceAsStream("/Assets/Image/Rotated Seal.png"))));
        Name.setText(p.getName());
        club.setText(p.getClubName());
        Country.setText(p.getCountry());
        Age.setText(p.getAge() + " Years");
        Height.setText(p.getHeight() + " m");
        Position.setText(p.getPosition());
        Number.setText(String.valueOf(p.getNumber()));
        Salary.setText(Club.showSalary(p.getWeeklySalary()));
        if (pageType == PlayerListViewController.PageType.SimpleList) {
            TransferStatusButton.setText("Add to Transfer List");
            if (player.isTransferListed()) TransferStatusButton.setDisable(true);
        } else if (pageType == PlayerListViewController.PageType.TransferList) {
            TransferStatusButton.setText("Buy Player");
            if (player.getClubName().equalsIgnoreCase(main.myClub.getName())) TransferStatusButton.setDisable(true);
        }
        if (player.isTransferListed()) {
            TransferLabel1.setText("Transfer Fee:");
            TransferLabel2.setText(Club.showSalary(player.getTransferFee()));
        }
        return playerCard;
    }

    @FXML
    void doTransferAction(ActionEvent event) throws IOException {
        if (pageType == PlayerListViewController.PageType.SimpleList) {
            main.AskForTransferFee(this);
            System.out.println("Close the dialog box and send the transfer request to server");
        } else {
            main.myNetworkUtil.write(new BuyRequest(player.getName(), player.getClubName(), main.myClub.getName()));
            System.out.println("Send buying request to server");
        }
    }

}
