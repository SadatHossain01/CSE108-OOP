package Controllers;

import DTO.Request;
import DataModel.Club;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import sample.Main;

import java.io.IOException;
import java.util.Objects;

public class ClubDashboardController {
    private Main main;
    private Club club;

    public void setMain(Main main) {
        this.main = main;
    }

    @FXML
    private Label clubName;

    @FXML
    private ImageView clubLogo;

    @FXML
    public JFXButton myPlayerButton;

    @FXML
    public JFXButton searchPlayerButton;

    @FXML
    public JFXButton marketplaceButton;

    @FXML
    public JFXButton logoutButton;

    @FXML
    public Label budget;

    @FXML
    private AnchorPane anchorPane;

    public void initiate(Club club){
        main.mainPane = anchorPane;
        main.dashboardController = this;
        this.club = club;
        clubName.setText(club.getName());
        budget.setText(Club.showSalary(club.getTransferBudget()));
//        clubLogo.setImage(new Image(Objects.requireNonNull(Main.class.getResourceAsStream("/Assets/Image/Club Logo/" + club.getName() + ".png")), 150, 150, false, true));
        try{
            Image img = new Image(club.getLogo(), true);
            clubLogo.setImage(img);
//            clubLogo.setImage(new Image(Objects.requireNonNull(Main.class.getResourceAsStream(club.getLogo())), 150, 150, true, true));
        } catch (Exception e){
            clubLogo.setImage(new Image(Objects.requireNonNull(Main.class.getResourceAsStream("/Assets/Image/No_Image.png")), 150, 150, true, true));
        }
    }

    @FXML
    void LogOut(ActionEvent event) throws IOException {
        main.myNetworkUtil.write(new Request(main.myClub.getName(), Request.Type.LogOut));
        if (main.tempStage.isShowing()) main.tempStage.close();
        main.showLoginPage();
    }

    @FXML
    void ShowBuyablePlayers(ActionEvent event) throws IOException {
        main.showBuyablePlayers();
        System.out.println("Show buyable players");
    }

    @FXML
    void ShowMyPlayers(ActionEvent event) throws IOException {
        main.showMyPlayers();
    }

    @FXML
    void ShowSearchOptions(ActionEvent event) throws IOException {
        main.showSearchPage();
    }

}
