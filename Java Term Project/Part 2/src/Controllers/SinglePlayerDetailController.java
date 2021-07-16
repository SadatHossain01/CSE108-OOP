package Controllers;

import DataModel.Club;
import DataModel.Player;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import sample.Main;

import java.util.Objects;

public class SinglePlayerDetailController {

    private PlayerListViewController.PageType pageType;
    private Player player;

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

    public VBox initiate(Player p, PlayerListViewController.PageType pageType){
        this.pageType = pageType;
        this.player = p;
        System.out.println(p.getName());
        var loaded = Main.class.getResourceAsStream("/Assets/Image/Player Image/" + p.getName() + ".jpeg");
        if (loaded == null) loaded = Main.class.getResourceAsStream("/Assets/Image/Player Image/" + p.getName() + ".jpg");
        assert loaded != null;
        playerImage.setImage(new Image(loaded, 440, 220, false, true));
        if (p.isTransferListed()) transferTag.setImage(new Image(Objects.requireNonNull(Main.class.getResourceAsStream("/Assets/Image/Rotated Seal.png"))));
        Name.setText(p.getName());
        club.setText(p.getClubName());
        Country.setText(p.getCountry());
        Age.setText(p.getAge() + " Years");
        Height.setText(p.getHeight() + " m");
        Position.setText(p.getPosition());
        Number.setText(String.valueOf(p.getNumber()));
        Salary.setText(Club.showSalary(p.getWeeklySalary()));
        if (pageType == PlayerListViewController.PageType.SimpleList){
            if (p.isTransferListed()) TransferStatusButton.setText("Remove From Transfer List");
            else TransferStatusButton.setText("Add to Transfer List");
        }
        else{
            TransferStatusButton.setText("Buy Player");
        }
        return playerCard;
    }

    @FXML
    void doTransferAction(ActionEvent event) {
        if (pageType == PlayerListViewController.PageType.SimpleList){
            if (!player.isTransferListed()){
                TransferStatusButton.setText("Remove from Transfer List");
                player.setTransferListed(true);
                transferTag.setImage(new Image(Objects.requireNonNull(Main.class.getResourceAsStream("/Assets/Image/Rotated Seal.png"))));
                System.out.println("Ask for the asking price");
            }
            else {
                TransferStatusButton.setText("Add to Transfer List");
                player.setTransferListed(false);
                transferTag.setImage(null);
            }
        }
        else{
            System.out.println("Send transfer request to server");
        }
    }

}
