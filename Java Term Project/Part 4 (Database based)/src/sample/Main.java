package sample;

import Controllers.*;
import DTO.ClubLoginAuthentication;
import DataModel.Club;
import DataModel.Player;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import util.CurrentPage;
import util.MyAlert;
import util.NetworkUtil;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main extends Application {
    public Stage primaryStage, tempStage;
    public Parent RootOfAll;
    public AnchorPane mainPane;
    public ClubDashboardController dashboardController;
    private boolean isFirstTime = true;
    public static double screenHeight, screenWidth;
    public InetAddress LocalAddress;
    public Socket socket;
    public int port = 44444;
    public Club myClub, latestCountryWiseCountClub;
    public Image cLogo;
    public Player latestDetailedPlayer;
    public CurrentPage.Type currentPageType, previousPageType;
    public List<Player>TransferListedPlayers, latestSearchedPlayers;
    public HashMap<String, String> countryFlagMap = new HashMap<>();
    public NetworkUtil myNetworkUtil;

    public void initiateApplication() throws IOException {
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

    public void showAlertMessage(MyAlert myAlert) {
        myAlert.show();
    }

    public void showLoginPage() throws IOException {
        currentPageType = CurrentPage.Type.LoginPage;
        var loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/ViewFX/ClubLoginView.fxml"));
        Parent root = loader.load();
        ClubLoginController controller = loader.getController();
        controller.setClubClient(this);
        var scene = new Scene(root);
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                try {
                    controller.SignIn();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        primaryStage.setScene(scene);
        primaryStage.setTitle("Login Page");
        primaryStage.setResizable(false);
        if (isFirstTime) {
            primaryStage.centerOnScreen();
            isFirstTime = false;
        }
        primaryStage.show();
    }

    public void showClubHomePage(Club c) throws IOException {
        var loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/ViewFX/ClubDashboardView.fxml"));
        RootOfAll = loader.load();
        primaryStage.setScene(new Scene(RootOfAll));
        dashboardController = loader.getController();
        dashboardController.setMain(this);
        dashboardController.initiate(c);
        showMyPlayers();
    }

    public void resetAllButtons() {
        dashboardController.myPlayerButton.setStyle("-fx-background-color: #FED45F; -fx-background-radius: 15 15 15 15");
        dashboardController.marketplaceButton.setStyle("-fx-background-color: #FED45F; -fx-background-radius: 15 15 15 15");
        dashboardController.searchPlayerButton.setStyle("-fx-background-color: #FED45F; -fx-background-radius: 15 15 15 15");
    }

    public void showMyPlayers() throws IOException {
        resetAllButtons();
        mainPane.getChildren().clear();
        dashboardController.myPlayerButton.setStyle("-fx-background-color: #DA3A34; -fx-background-radius: 15 15 15 15");
        currentPageType = CurrentPage.Type.ShowMyPlayers;
        displayList(myClub.getPlayerList(), PlayerListViewController.PageType.SimpleList);
    }

    public void showSearchPage() throws IOException {
        currentPageType = CurrentPage.Type.ShowSearchOptions;
        resetAllButtons();
        mainPane.getChildren().clear();
        dashboardController.searchPlayerButton.setStyle("-fx-background-color: #DA3A34; -fx-background-radius: 15 15 15 15");
        var loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/ViewFX/PlayerSearchView.fxml"));
        Parent root = loader.load();
        mainPane.getChildren().add(root);
        PlayerSearchController searchController = loader.getController();
        searchController.setMain(this);
        searchController.initiate(myClub);
        primaryStage.setTitle("Player Search Options");
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public void showCountryWiseCount(Stage stage, Club club) throws IOException {
        previousPageType = currentPageType;
        currentPageType = CurrentPage.Type.ShowCountryWiseCount;
        latestCountryWiseCountClub = club;
        tempStage = stage;
        var loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/ViewFX/CountryListView.fxml"));
        Parent root = loader.load();
        CountryListViewController c = loader.getController();
        c.setStage(stage);
        c.setMain(this);
        c.initiate(club.getCountryWisePlayerCount());
        stage.setScene(new Scene(root));
        stage.setTitle("Country Wise Player Count");
        stage.setResizable(false);
        if (!stage.isShowing()) stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    public void showPlayerDetail(Stage stage, Player player) throws IOException {
        latestDetailedPlayer = player;
        tempStage = stage;
        previousPageType = currentPageType;
        currentPageType = CurrentPage.Type.ShowAPlayerDetail;
        var loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/ViewFX/PlayerCardView.fxml"));
        Parent root = loader.load();
        PlayerCardController p = loader.getController();
        p.setMain(this);
        p.setStage(stage);
        p.initiate(player);
        stage.setScene(new Scene(root));
        stage.setTitle("Player Detail");
        stage.setResizable(false);
        if (!stage.isShowing()) stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    public void displayList(List<Player> playerList, PlayerListViewController.PageType pageType) throws IOException {
        mainPane.getChildren().clear();
        var loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/ViewFX/PlayerListView.fxml"));
        Parent root = loader.load();
        mainPane.getChildren().add(root);
        PlayerListViewController playerListViewController = loader.getController();
        playerListViewController.setMain(this);
        playerListViewController.initiate(playerList, pageType);
        primaryStage.setTitle("Player List Display");
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public void AskForTransferFee(MinimalPlayerDetailController singlePlayerDetailController) throws IOException {
        Stage stage = new Stage();
        tempStage = stage;
        previousPageType = currentPageType;
        currentPageType = CurrentPage.Type.AskForTransferFee;
        var loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/ViewFX/AskForTransferFee.fxml"));
        Parent root = loader.load();
        AskForTransferFeeController askForTransferFeeController = loader.getController();
        askForTransferFeeController.setMain(this);
        askForTransferFeeController.setStage(stage);
        askForTransferFeeController.initiate(singlePlayerDetailController);
        var scene = new Scene(root);
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                try {
                    askForTransferFeeController.confirmListing();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        stage.setScene(scene);
        stage.setTitle("Transfer Fee Input");
        stage.setResizable(false);
        stage.centerOnScreen();
        if (!stage.isShowing()) stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    public void showBuyablePlayers() throws IOException {
        currentPageType = CurrentPage.Type.ShowMarketPlayers;
        resetAllButtons();
        mainPane.getChildren().clear();
        dashboardController.marketplaceButton.setStyle("-fx-background-color: #DA3A34; -fx-background-radius: 15 15 15 15");
        displayList(TransferListedPlayers, PlayerListViewController.PageType.TransferList);
    }

    public void refreshPage() throws IOException {
        if (currentPageType == CurrentPage.Type.ShowMyPlayers) showMyPlayers();
        else if (currentPageType == CurrentPage.Type.ShowMarketPlayers) showBuyablePlayers();
        else if (currentPageType == CurrentPage.Type.ShowSearchedPlayers){
            //refining the list
            List<Player> newList = new ArrayList<>();
            List<Player> clubPlayerList = myClub.getPlayerList();
            for (var p : latestSearchedPlayers){
                if (clubPlayerList.contains(p)) newList.add(p);
            }
            latestSearchedPlayers = newList;
            displayList(newList, PlayerListViewController.PageType.SimpleList);
        }
        else if (currentPageType == CurrentPage.Type.ShowAPlayerDetail) showPlayerDetail(tempStage, latestDetailedPlayer);
        else if (currentPageType == CurrentPage.Type.ShowCountryWiseCount){
            this.showCountryWiseCount(tempStage, latestCountryWiseCountClub);
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        initiateApplication();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
