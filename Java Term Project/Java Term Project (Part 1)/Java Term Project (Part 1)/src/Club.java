import java.util.ArrayList;
import java.util.List;

public class Club {
    private int ID = -1;
    private String name;
    private double MAXIMUM_SALARY;
    private int MAXIMUM_AGE;
    private double MAXIMUM_HEIGHT;
    private double TOTAL_SALARY;
    private List<Player> players;

    public Club(){
        players = new ArrayList<>();
    }

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

    public int getSize(){
        return players.size();
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

    public void addPlayerToClub(Player p){
        if (players.size() >= 7) return;
        MAXIMUM_SALARY = Math.max(p.getSalary(), MAXIMUM_SALARY);
        MAXIMUM_AGE = Math.max(p.getAge(), MAXIMUM_AGE);
        MAXIMUM_HEIGHT = Math.max(p.getHeight(), MAXIMUM_HEIGHT);
        TOTAL_SALARY += p.getSalary();
        players.add(p);
    }
}
