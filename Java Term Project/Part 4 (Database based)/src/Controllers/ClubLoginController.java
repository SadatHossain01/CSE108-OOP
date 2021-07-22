package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import sample.Main;

import java.io.IOException;

public class ClubLoginController {
    private Main main;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    public void SignIn() throws IOException {
        String name = username.getText();
        String pass = password.getText();
        main.showLoadingScreen();
        main.ConnectToServer(name, pass);
    }

    @FXML
    void DoSignIn(ActionEvent event) throws IOException {
        SignIn();
    }

    public void setClubClient(Main main) {
        this.main = main;
    }
}
