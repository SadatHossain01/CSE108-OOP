package Controllers;

import DataModel.Club;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sample.Main;

import java.io.IOException;
import java.util.Objects;

public class ClubHomePageController {
    private Club club;
    private Main main;

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }

    public void setClubClient(Main main) {
        this.main = main;
    }

    @FXML
    private ImageView logo;

    @FXML
    private Label welcomeMessage;

    public void initiate(Club club){
        System.out.println(club.getName());
        this.club = club;
        welcomeMessage.setText("Welcome to " + club.getName());
        logo.setImage(new Image(Objects.requireNonNull(Main.class.getResourceAsStream("/Assets/Image/Club Logo/" + club.getName() + ".png")), 200, 200, false, true));
    }

    @FXML
    void SearchPlayers(ActionEvent event) throws IOException {
        main.showSearchPage();
    }

    @FXML
    void showBuyablePlayers(ActionEvent event) {
        System.out.println("Show buyable players");
    }

    @FXML
    void showMyPlayers(ActionEvent event) throws IOException {
        main.displayList(club.getPlayerList(), PlayerListViewController.PageType.SimpleList);
    }

}
