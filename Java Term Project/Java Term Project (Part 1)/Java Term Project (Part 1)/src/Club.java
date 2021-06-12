import java.util.ArrayList;
import java.util.List;

public class Club {
    private int ID;
    private String name;
    private double MAXIMUM_SALARY;
    private int MAXIMUM_AGE;
    private double MAXIMUM_HEIGHT;
    private double TOTAL_SALARY;
    private List<Player> players;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Player SearchPlayerByName(String name){
        for (var player:players){
            if (player.getName().equalsIgnoreCase(name)){
                return player;
            }
        }
        return null;
    }

    public List<Player> SearchPlayerByClubCountry(String club, String country){
        List<Player>wantedPlayers = new ArrayList<>();
        for (var player:players){
            if (player.getCountry().equalsIgnoreCase(country) && (club.equalsIgnoreCase("ANY") || player.getClub().equalsIgnoreCase(club))){
                wantedPlayers.add(player);
            }
        }
        return wantedPlayers;
    }

    public List<Player> SearchPlayerByPosition(String position){
        List<Player>wantedPlayers = new ArrayList<>();
        for (var player:players){
            if (player.getPosition().equalsIgnoreCase(position)){
                wantedPlayers.add(player);
            }
        }
        return wantedPlayers;
    }

    public List<Player> SearchPlayerBySalary(double lowRange, double highRange){
        List<Player>wantedPlayers = new ArrayList<>();
        for (var player:players){
            double salary = player.getSalary();
            if (lowRange <= salary && salary <= highRange){
                wantedPlayers.add(player);
            }
        }
        return wantedPlayers;
    }

    public List<Player> SearchMaximumSalary(){
        List<Player>wantedPlayers = new ArrayList<>();
        for (var player:players){
            if (player.getSalary() == MAXIMUM_SALARY){
                wantedPlayers.add(player);
            }
        }
        return wantedPlayers;
    }

    public List<Player> SearchMaximumAge(){
        List<Player>wantedPlayers = new ArrayList<>();
        for (var player:players){
            if (player.getAge() == MAXIMUM_AGE){
                wantedPlayers.add(player);
            }
        }
        return wantedPlayers;
    }

    public List<Player> SearchMaximumHeight(){
        List<Player>wantedPlayers = new ArrayList<>();
        for (var player:players){
            if (player.getHeight() == MAXIMUM_HEIGHT){
                wantedPlayers.add(player);
            }
        }
        return wantedPlayers;
    }

    public double TotalSalary(){
        return TOTAL_SALARY;
    }

    public void addPlayer(Player p){

    }
}
