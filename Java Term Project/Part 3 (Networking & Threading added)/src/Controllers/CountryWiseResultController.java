package Controllers;

import DataModel.Country;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.Main;

import java.util.HashMap;

public class CountryWiseResultController {
    private Main main;
    private Stage stage;
    private HashMap<String, Integer> countryWiseCount;

    @FXML
    private TableView<Country> tableView;

    @FXML
    private TableColumn<Country, String> countryName;

    @FXML
    private TableColumn<Country, Integer> playerCount;


    public void setMain(Main main) {
        this.main = main;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public ObservableList<Country> getObservableList(){
        ObservableList<Country> countryCounts = FXCollections.observableArrayList();
        for (var p : countryWiseCount.entrySet()){
            countryCounts.add(new Country(p.getKey(), p.getValue()));
        }
        return countryCounts;
    }

    public void initiate(HashMap<String, Integer> countryWiseCount){
        this.countryWiseCount = countryWiseCount;
        countryName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        playerCount.setCellValueFactory(new PropertyValueFactory<>("Count"));
        tableView.setItems(getObservableList());
    }
}
