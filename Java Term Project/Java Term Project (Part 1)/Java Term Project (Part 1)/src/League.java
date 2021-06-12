import java.util.ArrayList;
import java.util.List;

public class League {
    private List<Club>clubs;
    private List<Player>players;
    private List<Country>countries;

    public League(){
        clubs = new ArrayList<>();
        players = new ArrayList<>();
        countries = new ArrayList<>();
    }

    public Club SearchClubByName(String name){
        for (var club:clubs){
            if (club.getName().equalsIgnoreCase(name)) return club;
        }
        return null;
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

    public void addPlayerDoingAllChecks(Player p){
        var wantedClub = SearchClubByName(p.getClub());
        if (wantedClub == null){
            Club c = new Club();
            c.setName(p.getClub());
            addClub(c);
            c.addPlayerToClub(p);
        }
        else wantedClub.addPlayerToClub(p);
        addPlayerToLeague(p);
    }

    public void addClub(Club club){
        clubs.add(club);
    }

    public void addPlayerToLeague(Player p){
        players.add(p);
    }
}
