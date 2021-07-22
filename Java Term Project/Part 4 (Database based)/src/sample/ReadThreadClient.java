package sample;

import DTO.*;
import DataModel.Club;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import util.CurrentPage;
import util.MyAlert;
import util.NetworkUtil;

import java.io.IOException;
import java.util.Objects;

public class ReadThreadClient implements Runnable {
    private Thread t;
    private String username, password;
    private Club c;
    private Main main;
    private NetworkUtil clientNetworkUtil;
    private boolean isThreadOn = true;

    public ReadThreadClient(Main main) {
        this.main = main;
        clientNetworkUtil = main.myNetworkUtil;
        t = new Thread(this, "Read Thead Client");
        t.start();
    }

    @Override
    public void run() {
        while (isThreadOn) {
            Object next = null;
            while (isThreadOn) {
                try {
                    next = clientNetworkUtil.read();
                    break;
                } catch (IOException | ClassNotFoundException ignored) {
                }
            }
            if (next instanceof RequestResponse) {
                var type = ((RequestResponse) next).type;
                if (type == RequestResponse.Type.LoginSuccessful) {
                } else if (type == RequestResponse.Type.AlreadyLoggedIn) {
                    Platform.runLater(() -> main.showAlertMessage(new MyAlert(Alert.AlertType.ERROR, "Already Logged In", "Sorry, this club is already logged in to the system")));
                } else if (type == RequestResponse.Type.UsernameNotRegistered) {
                    Platform.runLater(() -> main.showAlertMessage(new MyAlert(Alert.AlertType.ERROR, "Unregistered club", "Sorry, this club is not registered to the system")));
                } else if (type == RequestResponse.Type.IncorrectPassword) {
                    Platform.runLater(() -> main.showAlertMessage(new MyAlert(Alert.AlertType.ERROR, "Incorrect password", "Sorry, the password you entered is incorrect")));
                } else if (type == RequestResponse.Type.InsufficientTransferBudget) {
                    Platform.runLater(() -> main.showAlertMessage(new MyAlert(Alert.AlertType.ERROR, "Insufficient budget", "Sorry, you do not have sufficient budget to buy this player")));
                } else if (type == RequestResponse.Type.AlreadyBought) {
                    Platform.runLater(() -> main.showAlertMessage(new MyAlert(Alert.AlertType.ERROR, "Player not for sale anymore", "Sorry, this player has been already bought")));
                }
            } else if (next instanceof Club) {
                c = (Club) next;
                main.myClub = c;
                try {
                    System.out.println(main.myClub.getLogoLink());
                    System.out.println("starts");
                    main.cLogo = new Image(main.myClub.getLogoLink(), true);
                    System.out.println("success");
//                    main.cLogo = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("/Assets/Image/Club Logo/" + c.getName() + ".png")));
                } catch (Exception e) {
                    main.cLogo = new Image("https://www.shopinimizaj.com/frontend/web/images/no-image.png", true);
//                    main.cLogo = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("/Assets/Image/No_Image.png")));
                }
                Platform.runLater(() ->
                {
                    try {
                        main.showClubHomePage(c);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            } else if (next instanceof NewPlayerPurchased) {
                var p = ((NewPlayerPurchased) next).getPlayer();
                String buyerName = ((NewPlayerPurchased) next).getBuyer();
                String sellerName = ((NewPlayerPurchased) next).getSeller();
                System.out.println(p.getName() + " has been bought by " + buyerName + " from " + sellerName);
                if (c.getName().equalsIgnoreCase(buyerName)) {
                    System.out.println("I am the buyer");
                    c.addPlayerToClub(p);
                    var toBeRemovedFromTransferList = c.FindPlayerInList(p.getName(), main.TransferListedPlayers);
                    main.TransferListedPlayers.remove(toBeRemovedFromTransferList);
                    p.setClubName(c.getName());
                    p.setTransferListed(false);
                    c.decreaseTransferBudget(p.getTransferFee());
                    Platform.runLater(() -> main.dashboardController.budget.setText(Club.showSalary(c.getTransferBudget()))
                    );
                    var currentPageType = main.currentPageType;
                    if (currentPageType == CurrentPage.Type.AskForTransferFee){
                        //so the previous page was MyPlayers, and to that list one new player has been added because of buying
                        main.isMainListUpdatePending = true;
                        System.out.println("Current page is \"asking for fee\" and isMainListUpdatePending set to true");
                    }
                    //if the current page is ShowPlayerDetail, two cases can happen
                    //if a third party buys or sells this player? we will deal with that in UpdatedList
                    else if (currentPageType == CurrentPage.Type.ShowAPlayerDetail){
                        if (main.previousPageType == CurrentPage.Type.ShowMyPlayers) {
                            System.out.println("Previous page type was ShowMyPlayers and isMainListUpdatePending set to true");
                            main.isMainListUpdatePending = true;
                        }
                        else if (main.previousPageType == CurrentPage.Type.ShowMarketPlayers) {
                            System.out.println("Previous page type was ShowMarketPlayers and isTransferListPending set to true");
                            main.isTransferListUpdatePending = true;
                        }
                    }
                    else {
                        Platform.runLater(() -> {
                                    try {
                                        System.out.println("Refresh asked for");
                                        main.refreshPage(main.currentPageType);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                        );
                    }
                }
                else if (c.getName().equalsIgnoreCase(sellerName)) {
                    var playerInSellingClubList = c.FindPlayerInList(p.getName(), c.getPlayerList());
                    c.getPlayerList().remove(playerInSellingClubList);
                    main.TransferListedPlayers.remove(playerInSellingClubList);
                    c.increseTransferBudget(p.getTransferFee());
                    Platform.runLater(() -> main.dashboardController.budget.setText(Club.showSalary(c.getTransferBudget()))
                    );
                    var currentPageType = main.currentPageType;
                    if (currentPageType == CurrentPage.Type.AskForTransferFee){
                        //so the previous page was MyPlayers, and from that list one player has to be removed as a result of selling
                        System.out.println("Current page is \"asking for fee\" and isMainListUpdatePending set to true");
                        main.isMainListUpdatePending = true;
                    }
                    //if the current page is ShowPlayerDetail, two cases can happen
                    else if (currentPageType == CurrentPage.Type.ShowAPlayerDetail){
                        if (main.previousPageType == CurrentPage.Type.ShowMyPlayers) {
                            if (p.getName().equalsIgnoreCase(main.latestDetailedPlayer.getName())){
                                Platform.runLater(()->{
                                    main.tempStage.close();
                                    try {
                                        System.out.println("My Player asked to show");
                                        main.refreshPage(CurrentPage.Type.ShowMyPlayers);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                });
                            }
                            else main.isMainListUpdatePending = true;
                        }
                        else if (main.previousPageType == CurrentPage.Type.ShowMarketPlayers) main.isTransferListUpdatePending = true;
                    }
                    else {
                        Platform.runLater(() -> {
                                    try {
                                        main.refreshPage(main.currentPageType);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                        );
                    }
                }
            } else if (next instanceof UpdatedTransferList) {
                main.TransferListedPlayers = ((UpdatedTransferList) next).getPlayerList();
                var currentPageType = main.currentPageType;
                var previousPageType = main.previousPageType;
                if (currentPageType == CurrentPage.Type.ShowAPlayerDetail){
                    if (previousPageType == CurrentPage.Type.ShowMarketPlayers){
                        var pp = c.FindPlayerInList(main.latestDetailedPlayer.getName(), main.TransferListedPlayers);
                        if (pp == null){
                            //this player has been bought, so you should not be able to see it any more
                            Platform.runLater(()->{
                                main.tempStage.close();
                                try {
                                    main.refreshPage(CurrentPage.Type.ShowMarketPlayers);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            });
                        }
                        else main.isTransferListUpdatePending = true;
                    }
                }
                else if (currentPageType == CurrentPage.Type.ShowMarketPlayers){
                    Platform.runLater(()->{
                        try {
                            main.refreshPage(CurrentPage.Type.ShowMarketPlayers);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                }
            } else if (next instanceof ClubCountryImageData) {
                var countryList = ((ClubCountryImageData) next).getCountryFlagList();
                for (var c : countryList) {
                    main.countryFlagMap.put(c.getKey(), c.getValue());
                }
                var clubList = ((ClubCountryImageData) next).getClubLogoList();
                for (var c : clubList) {
                    main.clubLogoMap.put(c.getKey(), c.getValue());
                }
            }
        }
    }
}
