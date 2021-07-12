package sample;

import basics.League;
import basics.Player;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import util.FileOperations;

import java.util.Objects;

public class Main extends Application {
    public static League FiveASideLeague;

    @Override
    public void start(Stage primaryStage) throws Exception{
        FiveASideLeague = new League();
        var loaded = FileOperations.readFromFile("assets/players.txt");
        for (Player p : loaded) FiveASideLeague.addPlayerToLeague(p);

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("sample.fxml")));
        primaryStage.setTitle("Football Manager 2021");
        primaryStage.setScene(new Scene(root, 1280, 720));
        primaryStage.setResizable(true);
        primaryStage.show();
    }

    public static void showResults(){
        Stage stage = new Stage();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
