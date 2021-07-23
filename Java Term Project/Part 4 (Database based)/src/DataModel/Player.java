package DataModel;

import java.io.Serializable;

public class Player implements Serializable {
    private String name;
    private String country;
    private String ClubName;
    private final String position;
    private final int age;
    private String DoB = "";
    private double weight;
    private String ImageSource;
    public enum PreferredFoot {Left, Right, Both};
    private PreferredFoot preferredFoot;
    private int number;
    private double estimatedValue;
    private final double height;
    private final double WeeklySalary;
    private boolean isTransferListed = false;
    private double TransferFee = 0;

    public Player(String name, String country, int age, double height, String ClubName, String position, int number, double WeeklySalary) {
        setName(name);
        setCountry(country);
        this.age = age;
        this.height = height;
        setClubName(ClubName);
        this.position = position;
        this.number = number;
        this.WeeklySalary = WeeklySalary;
    }

    public Player(String name, String country, String clubName, String position, int age, String doB, double weight, String image_source, int number, double height, double weeklySalary) {
        this.name = name;
        this.country = country;
        ClubName = clubName;
        this.position = position;
        this.age = age;
        DoB = doB;
        this.weight = weight;
        ImageSource = image_source;
        this.number = number;
        this.height = height;
        WeeklySalary = weeklySalary;
    }

    public Player(String name, String country, String clubName, String position, int age, double weight, String imageSource, String pFoot, int number, double estimatedValue, double height, double weeklySalary) {
        this.name = name;
        this.country = country;
        ClubName = clubName;
        this.position = position;
        this.age = age;
        this.weight = weight;
        ImageSource = imageSource;
        if (pFoot.equalsIgnoreCase("Left")) this.preferredFoot = PreferredFoot.Left;
        else if (pFoot.equalsIgnoreCase("Right")) this.preferredFoot = PreferredFoot.Right;
        else this.preferredFoot = PreferredFoot.Both;
        this.number = number;
        this.estimatedValue = estimatedValue;
        this.height = height;
        WeeklySalary = weeklySalary;
    }

    public Player(Player player) {
        this.name = player.name;
        this.country = player.country;
        this.ClubName = player.ClubName;
        this.position = player.position;
        this.age = player.age;
        this.number = player.number;
        this.height = player.height;
        this.WeeklySalary = player.WeeklySalary;
        this.isTransferListed = player.isTransferListed;
        this.TransferFee = player.TransferFee;
        this.preferredFoot = player.preferredFoot;
        this.weight = player.weight;
        this.DoB = player.DoB;
        this.estimatedValue = player.estimatedValue;
        this.ImageSource = player.ImageSource;
    }

    public double getEstimatedValue() {
        return estimatedValue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = League.FormatName(name);
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = League.FormatName(country);
    }

    public String getClubName() {
        return ClubName;
    }

    public void setClubName(String ClubName) {
        this.ClubName = League.FormatName(ClubName);
    }

    public String getPosition() {
        return position;
    }

    public int getAge() {
        return age;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public double getHeight() {
        return height;
    }

    public double getWeeklySalary() {
        return WeeklySalary;
    }

    public double getTransferFee() {
        return TransferFee;
    }

    public void setTransferFee(double transferFee) {
        TransferFee = transferFee;
    }

    public boolean isTransferListed() {
        return isTransferListed;
    }

    public void setTransferListed(boolean transferListed) {
        isTransferListed = transferListed;
    }

    public String getDoB() {
        return DoB;
    }

    public double getWeight() {
        return weight;
    }

    public String getImageSource() {
        return ImageSource;
    }

    public PreferredFoot getPreferredFoot() {
        return preferredFoot;
    }

    public void setPreferredFoot(PreferredFoot preferredFoot) {
        this.preferredFoot = preferredFoot;
    }

    public synchronized int isTransferPossible(Club buyer) { //returns 0 on success, 1 on already bought, 2 on insufficient budget
        if (!isTransferListed) return 1;
        if (buyer.getTransferBudget() < TransferFee) return 2;
        return 0;
    }

    public void showDetails() {
        System.out.println("Name: " + name);
        System.out.println("Country: " + country);
        System.out.println("Age: " + age + " years");
        System.out.println("Height: " + height + "cm");
        System.out.println("Weight: " + weight + "kg");
        System.out.println("Club: " + ClubName);
        System.out.println("Country: " + country);
        System.out.println("Position: " + position);
        System.out.println("Number: " + number);
        System.out.println("Preferred Foot: " + preferredFoot);
        System.out.println("Weekly Salary: " + Club.showSalary(WeeklySalary));
        System.out.println("Value: " + Club.showSalary(estimatedValue));
    }
}
