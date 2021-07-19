package DataModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static DataModel.League.FormatName;

public class Club implements Serializable {
    private String name;
    private double MAXIMUM_SALARY;
    private int MAXIMUM_AGE;
    private double MAXIMUM_HEIGHT;
    private double TOTAL_WEEKLY_SALARY;
    private double TransferBudget = 120000000;
    private List<Player> PlayerList;
    public List<Integer> NumberTaken;

    public Club(String name) {
        setName(name);
        PlayerList = new ArrayList<>();
        NumberTaken = new ArrayList<>();
    }

    public int getSize() {
        return PlayerList.size();
    }

    public void setName(String name) {
        this.name = FormatClubName(name);
    }

    public String getName() {
        return name;
    }

    public int getPlayerCount() {
        return PlayerList.size();
    }

    public double getTransferBudget() {
        return TransferBudget;
    }

    public void setTransferBudget(double transferBudget) {
        TransferBudget = transferBudget;
    }

    public void increseTransferBudget(double increment){
        TransferBudget += increment;
    }

    public void decreaseTransferBudget(double decrement){
        TransferBudget -= decrement;
    }

    public List<Player> getPlayerList(){return PlayerList;}

    public List<Player> SearchMaximumSalary() {
        List<Player> wantedPlayers = new ArrayList<>();
        for (var player : PlayerList) {
            if (player.getWeeklySalary() == MAXIMUM_SALARY) {
                wantedPlayers.add(player);
            }
        }
        return wantedPlayers;
    }

    public List<Player> SearchMaximumAge() {
        List<Player> wantedPlayers = new ArrayList<>();
        for (var player : PlayerList) {
            if (player.getAge() == MAXIMUM_AGE) {
                wantedPlayers.add(player);
            }
        }
        return wantedPlayers;
    }

    public List<Player> SearchMaximumHeight() {
        List<Player> wantedPlayers = new ArrayList<>();
        for (var player : PlayerList) {
            if (player.getHeight() == MAXIMUM_HEIGHT) {
                wantedPlayers.add(player);
            }
        }
        return wantedPlayers;
    }

    public double TotalYearlySalary() {
        return TOTAL_WEEKLY_SALARY * 52;
    }

    public void addPlayerToClub(Player p) {
        //assuming the club size check is done in main
        MAXIMUM_SALARY = Math.max(p.getWeeklySalary(), MAXIMUM_SALARY);
        MAXIMUM_AGE = Math.max(p.getAge(), MAXIMUM_AGE);
        MAXIMUM_HEIGHT = Math.max(p.getHeight(), MAXIMUM_HEIGHT);
        TOTAL_WEEKLY_SALARY += p.getWeeklySalary();
        PlayerList.add(p);
        NumberTaken.add(p.getNumber());
    }

    public HashMap<String, Integer> getCountryWisePlayerCount() {
        HashMap<String, Integer> countryWiseCount = new HashMap<>();
        for (var p : PlayerList){
            var c = p.getCountry();
            if (countryWiseCount.containsKey(c)){
                countryWiseCount.put(p.getCountry(), countryWiseCount.get(p.getCountry()) + 1);
            }
            else{
                countryWiseCount.put(p.getCountry(), 1);
            }
        }
        return countryWiseCount;
    }

    public List<Player> SearchByNameInClub(String name) {
        List<Player> ans = new ArrayList<>();
        String FormattedName = FormatName(name);
        for (var p : PlayerList) {
            if (p.getName().equalsIgnoreCase(FormattedName)) {
                ans.add(p);
                return ans;
            }
        }
        return null;
    }

    public List<Player> SearchPlayerByCountryInClub(String country) {
        String FormattedCountryName = FormatName(country);
        List<Player> wantedPlayers = new ArrayList<>();
        for (var p : PlayerList) {
            if ((country.equalsIgnoreCase("ANY") || p.getCountry().equalsIgnoreCase(FormattedCountryName))) {
                wantedPlayers.add(p);
            }
        }
        return wantedPlayers;
    }

    public List<Player> SearchPlayerBySalaryInClub(double lowRange, double highRange) {
        List<Player> wantedPlayers = new ArrayList<>();
        for (var p : PlayerList) {
            double salary = p.getWeeklySalary();
            if ((lowRange == -1 || lowRange <= salary) && (highRange == -1 || salary <= highRange)) {
                wantedPlayers.add(p);
            }
        }
        return wantedPlayers;
    }

    public List<Player> SearchPlayerByPositionInClub(String position) {
        List<Player> wantedPlayers = new ArrayList<>();
        for (var p : PlayerList) {
            if (p.getPosition().equalsIgnoreCase(position)) {
                wantedPlayers.add(p);
            }
        }
        return wantedPlayers;
    }

    public static String showSalary(double salaryNumber){
        String salary = "";
        if (salaryNumber >= 1e9) salary = "$" + salaryNumber/(1e9) + " Billion";
        else if (salaryNumber >= 1e6) salary = "$" + salaryNumber/(1e6) + " Million";
        else if (salaryNumber >= 1e3) salary = "$" + salaryNumber/(1e3) + "k";
        else salary = "$" + salaryNumber;
        return salary;
    }

    public Player FindPlayerInList(String PlayerName, List<Player> list) { //will return null if club is not registered yet
        Player wanted = null;
        String FormattedPlayerName = FormatName(PlayerName);
        for (var p : list) {
            if (p.getName().equalsIgnoreCase(FormattedPlayerName)) {
                wanted = p;
                break;
            }
        }
        return wanted;
    }

    public String FormatClubName(String name) {
        return FormatName(name);
    }

    @Override
    public String toString() {
        return name;
    }
}
