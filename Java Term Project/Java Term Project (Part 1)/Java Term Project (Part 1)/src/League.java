import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class League {
    public List<Player> CentralPlayerDatabase;
    private List<Club> ClubList;
    HashMap<Name, Integer> CountryWiseCount;
    HashMap<Name, Integer> ClubID;

    public League(){
        CentralPlayerDatabase = new ArrayList<>();
        ClubList = new ArrayList<>();
        CountryWiseCount = new HashMap<>();
        ClubID = new HashMap<>();
    }

    public static String FormatName(String name){
        StringBuilder str = new StringBuilder(name.trim());
        str.setCharAt(0, Character.toUpperCase(str.charAt(0)));
        for (int i=1; i<str.length(); i++){
            if (str.charAt(i-1) == ' '){
                if (str.charAt(i) == ' ') {
                    str.deleteCharAt(i);
                    i--;
                }
                else str.setCharAt(i, Character.toUpperCase(str.charAt(i)));
            }
        }
        return str.toString();
    }

    public int FindClubID(String ClubName){ //will return -1 if club is not registered yet
        int ID = -1;
        String FormattedClubName = FormatName(ClubName);
        if (ClubID.containsKey(new Name(FormattedClubName))) ID = ClubID.get(new Name(FormattedClubName));
        return ID;
    }

    public void addPlayerToClub(int ID, Player p){
        ClubList.get(ID).addPlayerToClub(p);
    }

    public void addPlayerToLeague(Player p){
        //size check and player existence check is done in main
        int ID = FindClubID(p.getClub());
        if (ID == -1){
            ID = ClubList.size();
            ClubList.add(new Club(p.getClub()));
            ClubID.put(new Name(p.getClub()), ID);
        }
        CentralPlayerDatabase.add(p);
        addPlayerToClub(ID, p);
        String CountryName = FormatName(p.getCountry());
        Integer val = CountryWiseCount.get(new Name(CountryName));
        if (val == null) val = 0;
        CountryWiseCount.put(new Name(CountryName), val + 1);
    }

    public Player SearchByName(String name){
        String FormattedName = FormatName(name);
        for (var p : CentralPlayerDatabase){
            if (p.getName().equalsIgnoreCase(FormattedName))  return p;
        }
        return null;
    }

    public List<Player> SearchPlayerByClubCountry(String club, String country){
        String FormattedClubName = FormatName(club);
        String FormattedCountryName = FormatName(country);
        List<Player>wantedPlayers = new ArrayList<>();
        for (var p : CentralPlayerDatabase){
            if (p.getCountry().equalsIgnoreCase(FormattedCountryName) && (club.equalsIgnoreCase("ANY") || p.getClub().equalsIgnoreCase(FormattedClubName))){
                wantedPlayers.add(p);
            }
        }
        return wantedPlayers;
    }

    public List<Player> SearchPlayerByPosition(String position){
        List<Player>wantedPlayers = new ArrayList<>();
        for (var p : CentralPlayerDatabase){
            if (p.getPosition().equalsIgnoreCase(position)){
                wantedPlayers.add(p);
            }
        }
        return wantedPlayers;
    }

    public List<Player> SearchPlayerBySalary(double lowRange, double highRange){
        List<Player>wantedPlayers = new ArrayList<>();
        for (var p : CentralPlayerDatabase){
            double salary = p.getWeeklySalary();
            if (lowRange <= salary && salary <= highRange){
                wantedPlayers.add(p);
            }
        }
        return wantedPlayers;
    }

    public void showCountryWisePlayerCount(){
        System.out.println("The country wise player count is shown below:");
        for (Map.Entry mapElement : CountryWiseCount.entrySet()){
            System.out.println(mapElement.getKey() + ": " + mapElement.getValue());
        }
    }

    public Club getClub(int index){
        return ClubList.get(index);
    }

    public int getClubSize(int index){
        return ClubList.get(index).getSize();
    }
}
