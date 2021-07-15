package sample;

import DataModel.Player;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.util.Objects;

public class SinglePlayerDetailController {

    @FXML
    public HBox playerCard;

    @FXML
    private ImageView playerImage;

    @FXML
    private Label Name;

    @FXML
    private Label Club;

    @FXML
    private Label Country;

    @FXML
    private Label Age;

    @FXML
    private Label Height;

    @FXML
    private Label Position;

    @FXML
    private Label Number;

    @FXML
    private Label Salary;

    @FXML
    private ImageView transferTag;

    HBox initiate(Player p){
        System.out.println(p.getName());
        var loaded = Main.class.getResourceAsStream("/Assets/Image/Player Image/" + p.getName() + ".jpeg");
        if (loaded == null) loaded = Main.class.getResourceAsStream("/Assets/Image/Player Image/" + p.getName() + ".jpg");
        assert loaded != null;
        playerImage.setImage(new Image(loaded, 440, 220, false, true));
        if (p.isTransferListed()) transferTag.setImage(new Image(Objects.requireNonNull(Main.class.getResourceAsStream("/Assets/Image/Seal.png"))));
        Name.setText(p.getName());
        Club.setText(p.getClubName());
        Country.setText(p.getCountry());
        Age.setText(p.getAge() + " Years");
        Height.setText(p.getHeight() + " m");
        Position.setText(p.getPosition());
        Number.setText(String.valueOf(p.getNumber()));
        String salary = "";
        if (p.getWeeklySalary() >= 1e9) salary = "$" + p.getWeeklySalary()/(1e9) + " Billion";
        else if (p.getWeeklySalary() >= 1e6) salary = "$" + p.getWeeklySalary()/(1e6) + " Million";
        else if (p.getWeeklySalary() >= 1e3) salary = "$" + p.getWeeklySalary()/(1e3) + "k";
        else salary = "$" + p.getWeeklySalary();
        Salary.setText(salary);
        return playerCard;
    }

}
