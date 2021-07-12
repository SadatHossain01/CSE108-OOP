package basics;

import java.util.ArrayList;
import java.util.List;

public class Club {
    private String name;
    private double MAXIMUM_SALARY;
    private int MAXIMUM_AGE;
    private double MAXIMUM_HEIGHT;
    private double TOTAL_WEEKLY_SALARY;
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

    public String FormatClubName(String name) {
        return League.FormatName(name);
    }

    @Override
    public String toString() {
        return name;
    }
}
