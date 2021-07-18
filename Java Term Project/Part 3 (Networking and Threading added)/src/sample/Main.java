package sample;

import Controllers.*;
import DTO.ClubLoginAuthentication;
import DataModel.Club;
import DataModel.Player;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import util.MyAlert;
import util.NetworkUtil;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.List;

public class Main extends Application {
    public Stage primaryStage;
    public static double screenHeight, screenWidth;
    InetAddress LocalAddress;
    Socket socket;
    int port = 44444;
    Club myClub;
    NetworkUtil myNetworkUtil;

    public void initiate() throws IOException {
        var screen = Screen.getPrimary().getBounds();
        screenHeight = screen.getHeight();
        screenWidth = screen.getWidth();
        LocalAddress = InetAddress.getLocalHost();
        showLoginPage();
    }

    public void ConnectToServer(String username, String password) throws IOException {
        socket = new Socket(LocalAddress, port);
        myNetworkUtil = new NetworkUtil(socket);
        new ReadThreadClient(this);
        myNetworkUtil.write(new ClubLoginAuthentication(username, password));
    }

    public void showAlertMessage(MyAlert myAlert){
        myAlert.show();
    }

    public void showLoadingPage() throws IOException {
        var loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/ViewFX/LoadingPageView.fxml"));
        Parent root = loader.load();
        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Loading.......");
        primaryStage.setResizable(false);
        primaryStage.centerOnScreen();
        primaryStage.show();
    }

    public void showLoginPage() throws IOException {
        var loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/ViewFX/ClubLoginView.fxml"));
        Parent root = loader.load();
        ClubLoginController controller = loader.getController();
        controller.setClubClient(this);
        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Login Page");
        primaryStage.setResizable(false);
        primaryStage.centerOnScreen();
        primaryStage.show();
    }

    public void showClubHomePage(Club c) throws IOException {
        var loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/ViewFX/ClubHomePageView.fxml"));
        Parent root = loader.load();
        ClubHomePageController clubPageController = loader.getController();
        clubPageController.initiate(c);
        clubPageController.setClubClient(this);
        var scene = new Scene(root, 700, 400);
        primaryStage.setTitle("Club Home Page");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
        primaryStage.show();
    }

    public void showSearchPage(Club c) throws IOException {
        var loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/ViewFX/PlayerSearchView.fxml"));
        Parent root = loader.load();
        PlayerSearchController searchController = loader.getController();
        searchController.setMain(this);
        searchController.initiate(c);
        var scene = new Scene(root);
        assert primaryStage != null;
        primaryStage.setTitle("Search Page");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
        primaryStage.show();
    }

    public void showCountryWiseCount(Club club) throws IOException {
        Stage stage = new Stage();
        var loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/ViewFX/CountryWiseResultView.fxml"));
        Parent root = loader.load();
        CountryWiseResultController countryWiseResultController = loader.getController();
        countryWiseResultController.setStage(stage);
        countryWiseResultController.setMain(this);
        countryWiseResultController.initiate(club.getCountryWisePlayerCount());
        stage.setScene(new Scene(root));
        stage.setTitle("Country Wise Result");
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();
    }

    public void displayList(List<Player> playerList, PlayerListViewController.PageType pageType) throws IOException {
        Stage stage = new Stage();
        var loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/ViewFX/PlayerListView.fxml"));
        Parent root = loader.load();
        PlayerListViewController playerListViewController = loader.getController();
        playerListViewController.setMain(this);
        playerListViewController.setStage(stage);
        playerListViewController.initiate(playerList, pageType);
        var scene = new Scene(root, 928, 550);
        stage.setScene(scene);
        stage.setTitle("Searched Players");
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        initiate();
    }
    public static void main(String[] args) {
        launch(args);
    }

    public void AskForTransferFee(SinglePlayerDetailController singlePlayerDetailController) throws IOException {
        Stage stage = new Stage();
        var loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/ViewFX/AskForTransferFee.fxml"));
        Parent root = loader.load();
        AskForTransferFeeController askForTransferFeeController = loader.getController();
        askForTransferFeeController.setMain(this);
        askForTransferFeeController.setStage(stage);
        askForTransferFeeController.initiate(singlePlayerDetailController);
        var scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Transfer Fee Input");
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();
    }
}
