package sample;

import DataModel.Club;
import DataModel.League;
import DataModel.Player;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import util.FileOperations;

import java.io.IOException;
import java.util.List;

public class Main extends Application {
    public static League FiveASideLeague;
    public Stage primaryStage;

    public void initiate() throws Exception {
        FiveASideLeague = new League();
        var loaded = FileOperations.readFromFile("src/Assets/Text/players.txt"); //file name path tree starts from one step back of src, but others all start from src
        int i = 1;
        for (Player p : loaded) {
            p.setPlayerID(i++);
            FiveASideLeague.addPlayerToLeague(p);
        }
        showLoginPage();
    }

    public void showLoginPage() throws IOException {
        String club = "Manchester City";
        club = League.FormatName(club);
        showClubHomePage(club);
    }

    public void showClubHomePage(String club) throws IOException {
        Club c = FiveASideLeague.FindClub(club);
        assert c != null;
        var loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/ViewFX/ClubHomePageView.fxml"));
        Parent root = loader.load();
        ClubHomePageController clubPageController = loader.getController();
        clubPageController.initiate(c);
        clubPageController.setMain(this);
        var scene = new Scene(root, 700, 400);
        assert primaryStage != null;
        primaryStage.setTitle("Club Home Page");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void showSearchPage(Club c) throws IOException {
        var loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/ViewFX/PlayerSearchView.fxml"));
        Parent root = loader.load();
        PlayerSearchController searchController = loader.getController();
        searchController.setMain(this);
        searchController.setClub(c);
        var scene = new Scene(root);
        assert primaryStage != null;
        primaryStage.setTitle("Search Page");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void displayList(List<Player> playerList) throws IOException {
        Stage stage = new Stage();
        var loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/ViewFX/PlayerListView.fxml"));
        Parent root = loader.load();
        PlayerListViewController playerListViewController = loader.getController();
        playerListViewController.setMain(this);
        playerListViewController.initiate(playerList);
        var scene = new Scene(root, 928, 550);
        stage.setScene(scene);
        stage.setTitle("Searched Players");
        stage.setResizable(false);
        stage.showAndWait();
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;
        initiate();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
