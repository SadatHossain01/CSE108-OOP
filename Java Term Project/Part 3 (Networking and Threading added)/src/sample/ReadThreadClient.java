package sample;

import DTO.RequestResponse;
import DataModel.Club;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import util.MyAlert;
import util.NetworkUtil;

import java.io.IOException;

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
                    System.out.println("Login successful");
                } else if (type == RequestResponse.Type.AlreadyLoggedIn) {
                    Platform.runLater(() -> {
                        main.showAlertMessage(new MyAlert(Alert.AlertType.ERROR, "Already Logged In", "Sorry, this club is already logged in to the system"));
                    });
                } else if (type == RequestResponse.Type.UsernameNotRegistered) {
                    Platform.runLater(()->{
                        main.showAlertMessage(new MyAlert(Alert.AlertType.ERROR, "Unregistered club", "Sorry, this club is not registered to the system"));
                    });
                } else if (type == RequestResponse.Type.IncorrectPassword) {
                    Platform.runLater(()->{
                        main.showAlertMessage(new MyAlert(Alert.AlertType.ERROR, "Incorrect password", "Sorry, the password you entered is incorrect"));
                    });
                } else if (type == RequestResponse.Type.InsufficientTransferBudget) {
                    Platform.runLater(()->{
                        main.showAlertMessage(new MyAlert(Alert.AlertType.ERROR, "Insufficient budget", "Sorry, you do not have sufficient budget to buy this player"));
                    });
                } else if (type == RequestResponse.Type.AlreadyBought) {
                    Platform.runLater(()->{
                        main.showAlertMessage(new MyAlert(Alert.AlertType.ERROR, "Player not for sale anymore", "Sorry, this player has been already bought"));
                    });
                    System.out.println("Remove this player from transfer list");
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
            }
            if (!isThreadOn) System.out.println("Quitting from this read thread client");
        }
    }
}
