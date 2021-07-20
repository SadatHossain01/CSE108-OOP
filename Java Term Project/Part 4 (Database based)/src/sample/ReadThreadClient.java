package sample;

import DTO.CountryList;
import DTO.NewPlayerPurchased;
import DTO.RequestResponse;
import DTO.UpdatedTransferList;
import DataModel.Club;
import DataModel.Country;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import util.CurrentPage;
import util.MyAlert;
import util.NetworkUtil;

import java.io.IOException;
import java.util.List;

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
                if (c.getName().equalsIgnoreCase(buyerName)) {
                    c.addPlayerToClub(p);
                    p.setClubName(c.getName());
                    p.setTransferListed(false);
                    c.decreaseTransferBudget(p.getTransferFee());
                    Platform.runLater(() -> main.dashboardController.budget.setText(Club.showSalary(c.getTransferBudget()))
                    );
                    Platform.runLater(() -> {
                                try {
                                    main.refreshPage();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                    );
                } else if (c.getName().equalsIgnoreCase(sellerName)) {
                    var playerInSellingClubList = c.FindPlayerInList(p.getName(), c.getPlayerList());
                    c.getPlayerList().remove(playerInSellingClubList);
                    c.increseTransferBudget(p.getTransferFee());
                    Platform.runLater(() -> main.dashboardController.budget.setText(Club.showSalary(c.getTransferBudget()))
                    );
                    Platform.runLater(() -> {
                                try {
                                    main.refreshPage();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                    );
                }
            } else if (next instanceof UpdatedTransferList) {
                String to = ((UpdatedTransferList) next).getToWhichClub();
                if (to.equalsIgnoreCase("all") || to.equalsIgnoreCase(c.getName())) {
                    main.TransferListedPlayers = ((UpdatedTransferList) next).getPlayerList();
                }
                Platform.runLater(() -> {
                            try {
                                if (main.pageType == CurrentPage.Type.ShowMarketPlayers) main.refreshPage();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                );
            } else if (next instanceof CountryList) {
                var countryList = ((CountryList) next).getCountryList();
                for (var c : countryList) {
                    main.countryFlagMap.put(c.getName(), c.getFlagSource());
                }
            }
        }
    }
}
