package sample;

import DataModel.Club;
import DataModel.League;
import DataModel.Player;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import util.MyAlert;

import java.util.ArrayList;
import java.util.List;

public class PlayerSearchController {
    private Main main;
    private Club club;

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }

    League league = Main.FiveASideLeague;

    @FXML
    private TextField PlayerName;

    @FXML
    private TextField CountryName;

    @FXML
    private MenuButton PositionChoice;

    @FXML
    private TextField MinSalary;

    @FXML
    private TextField MaxSalary;

    public static List<Player> getIntersectionOfLists(List<Player>list1, List<Player>list2){
        List<Player>answer = new ArrayList<>();
        for (var p : list1){
            if (list2.contains(p)) answer.add(p);
        }
        return answer;
    }

    @FXML
    public List<Player> showSearchResults() {
        double minSalary = -1, maxSalary = -1;
        List<Player> answer = new ArrayList<>();
        try {
            if (!MinSalary.getText().isEmpty()) minSalary = Double.parseDouble(MinSalary.getText());
            if (!MaxSalary.getText().isEmpty()) maxSalary = Double.parseDouble(MaxSalary.getText());
        } catch (Exception e) {
            new MyAlert(Alert.AlertType.ERROR, MyAlert.MessageType.InvalidSalaryInput).show();
            return answer;
        }
        String pName = PlayerName.getText(), countryName = CountryName.getText(), clubName = club.getName(), pChoice = PositionChoice.getText();
        if (pName.isEmpty() && countryName.isEmpty() && clubName.isEmpty() && minSalary == -1 && maxSalary == -1 && pChoice.equalsIgnoreCase("Position")){
            new MyAlert(Alert.AlertType.ERROR, MyAlert.MessageType.NoInput).show();
            return answer;
        }
        if (!pName.isEmpty()) answer = club.SearchByNameInClub(pName);
        else {
            if (clubName.isEmpty()) clubName = "any";
            if (countryName.isEmpty()) countryName = "any";
            answer = club.SearchPlayerByCountryInClub(countryName);
            if (!pChoice.isEmpty() && !pChoice.equalsIgnoreCase("Position") && !pChoice.equalsIgnoreCase("Any"))
                answer = getIntersectionOfLists(answer, club.SearchPlayerByPositionInClub(pChoice));
            if (minSalary != -1 || maxSalary != -1){
                answer = getIntersectionOfLists(answer, club.SearchPlayerBySalaryInClub(minSalary, maxSalary));
            }
        }
        processList(answer);
        return answer;
    }

    public void processList(List<Player>list){
        if (list == null || list.isEmpty()){ new MyAlert(Alert.AlertType.INFORMATION, MyAlert.MessageType.NoPlayerFound).show(); }
        else list.forEach(Player::showDetails);
    }

    @FXML
    public void resetInput() {
        PlayerName.setText("");
        CountryName.setText("");
        PositionChoice.setText("Position");
        MinSalary.setText("");
        MaxSalary.setText("");
    }

    @FXML
    public void setAnyPosition(){ PositionChoice.setText("Any");}
    @FXML
    public void setForward(){ PositionChoice.setText("Forward");}
    @FXML
    public void setMidfielder(){ PositionChoice.setText("Midfielder");}
    @FXML
    public void setDefender(){ PositionChoice.setText("Defender");}
    @FXML
    public void setGoalkeeper(){ PositionChoice.setText("Goalkeeper");}
}
