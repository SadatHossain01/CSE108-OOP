package Controllers;

import DTO.SellRequest;
import DataModel.Club;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.Main;
import util.MyAlert;

import java.io.IOException;
import java.util.Objects;

public class AskForTransferFeeController {
    private Main main;
    private SinglePlayerDetailController singlePlayerDetailController;
    private Stage stage;

    public void setMain(Main main) {
        this.main = main;
    }

    @FXML
    private AnchorPane Pane;

    @FXML
    private TextField AskedTransferFee;

    public void initiate(SinglePlayerDetailController singlePlayerDetailController){
        this.singlePlayerDetailController = singlePlayerDetailController;
    }

    @FXML
    void confirmTransferListing(ActionEvent event) throws IOException {
        double transferFee;
        try {
            transferFee = Double.parseDouble(AskedTransferFee.getText()) * 1e6;
            if (transferFee <= 0) throw new Exception();
        } catch (Exception e){
            new MyAlert(Alert.AlertType.ERROR, MyAlert.MessageType.InvalidSalaryInput).show();
            return;
        }
        var player = singlePlayerDetailController.getPlayer();
        var transferTag = singlePlayerDetailController.getTransferTag();
        var transferButton = singlePlayerDetailController.getTransferStatusButton();
        var transferLabel1 = singlePlayerDetailController.getTransferLabel1();
        var transferLabel2 = singlePlayerDetailController.getTransferLabel2();
        player.setTransferFee(transferFee);
        player.setTransferListed(true);
        transferTag.setImage(new Image(Objects.requireNonNull(Main.class.getResourceAsStream("/Assets/Image/Rotated Seal.png"))));
        transferButton.setDisable(true);
        transferLabel1.setText("Transfer Fee:");
        transferLabel2.setText(Club.showSalary(transferFee));
        main.TransferListedPlayers.add(player);
        main.myNetworkUtil.write(new SellRequest(player.getName(), main.myClub.getName(), transferFee));
        stage.close();
    }
    
    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
