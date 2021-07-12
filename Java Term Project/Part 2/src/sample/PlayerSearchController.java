package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class PlayerSearchController {
    League league = Main.FiveASideLeague;
    @FXML
    private TextField PlayerName;

    @FXML
    private TextField CountryName;

    @FXML
    private TextField ClubName;

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
            var a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Fatal error");
            a.setContentText("Salary must be a decimal value");
            a.show();
            return answer;
        }
        String pName = PlayerName.getText(), countryName = CountryName.getText(), clubName = ClubName.getText(), pChoice = PositionChoice.getText();
        if (pName.isEmpty() && countryName.isEmpty() && clubName.isEmpty() && minSalary == -1 && maxSalary == -1 && pChoice.equalsIgnoreCase("Position")){
            new Alert(Alert.AlertType.ERROR, "Input Your Choices Man!").show();
            return answer;
        }
        if (!pName.isEmpty()) answer = league.SearchByName(pName);
        else {
            if (clubName.isEmpty()) clubName = "any";
            if (countryName.isEmpty()) countryName = "any";
            answer = league.SearchPlayerByClubCountry(clubName, countryName);
            if (!pChoice.isEmpty() && !pChoice.equalsIgnoreCase("Position") && !pChoice.equalsIgnoreCase("Any")) answer = getIntersectionOfLists(answer, league.SearchPlayerByPosition(pChoice));
            if (minSalary != -1 || maxSalary != -1){
                answer = getIntersectionOfLists(answer, league.SearchPlayerBySalary(minSalary, maxSalary));
            }
        }
        processList(answer);
        return answer;
    }

    public void processList(List<Player>list){
        if (list == null || list.isEmpty()) new Alert(Alert.AlertType.INFORMATION, "No such player found").show();
        else {
            list.forEach(Player::showDetails);
            Stage stage = new Stage();
            stage.show();
        }
    }

    @FXML
    public void resetInput() {
        PlayerName.setText("");
        CountryName.setText("");
        ClubName.setText("");
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
