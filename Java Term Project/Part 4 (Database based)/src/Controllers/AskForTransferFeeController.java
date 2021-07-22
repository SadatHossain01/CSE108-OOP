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
import util.CurrentPage;
import util.MyAlert;

import java.io.IOException;
import java.util.Objects;

public class AskForTransferFeeController {
    private Main main;
    private MinimalPlayerDetailController minimalPlayerDetailController;
    private Stage stage;

    public void setMain(Main main) {
        this.main = main;
    }

    @FXML
    private AnchorPane Pane;

    @FXML
    private TextField AskedTransferFee;

    public void initiate(MinimalPlayerDetailController minimalPlayerDetailController){
        this.minimalPlayerDetailController = minimalPlayerDetailController;
    }

    public void confirmListing() throws IOException {
        double transferFee;
        try {
            transferFee = Double.parseDouble(AskedTransferFee.getText()) * 1e6;
            if (transferFee <= 0) throw new Exception();
        } catch (Exception e){
            new MyAlert(Alert.AlertType.ERROR, MyAlert.MessageType.InvalidSalaryInput).show();
            return;
        }
        var player = minimalPlayerDetailController.getPlayer();
        var transferLabel = minimalPlayerDetailController.getTransferLabel();
        var transferButton = minimalPlayerDetailController.getTransferButton();
        var fee = minimalPlayerDetailController.getFee();
        player.setTransferFee(transferFee);
        player.setTransferListed(true);
        transferLabel.setImage(new Image(Objects.requireNonNull(Main.class.getResourceAsStream("/Assets/Image/Stamp.png"))));
        transferButton.setDisable(true);
        fee.setText(Club.showSalary(transferFee));
        main.TransferListedPlayers.add(player);
        main.myNetworkUtil.write(new SellRequest(player.getName(), main.myClub.getName(), transferFee));
        stage.close();
    }

    @FXML
    void confirmTransferListing(ActionEvent event) throws IOException {
        confirmListing();
        if (main.isMainListUpdatePending){
            main.refreshPage(CurrentPage.Type.ShowMyPlayers);
            main.isMainListUpdatePending = false;
        }
        else main.currentPageType = CurrentPage.Type.ShowMyPlayers;
    }
    
    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
