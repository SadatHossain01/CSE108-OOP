import java.util.ArrayList;
import java.util.List;

public class League {
    public List<Player> CentralPlayerDatabase;
    private List<Club> ClubList;
    private List<Country> CountryList;
    private List<Position> PositionList;

    public League() {
        CentralPlayerDatabase = new ArrayList<>();
        ClubList = new ArrayList<>();
        CountryList = new ArrayList<>();
        PositionList = new ArrayList<>();
    }

    public static String FormatName(String name) {
        StringBuilder str = new StringBuilder(name.trim());
        str.setCharAt(0, Character.toUpperCase(str.charAt(0)));
        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i - 1) == ' ') {
                if (str.charAt(i) == ' ') {
                    str.deleteCharAt(i);
                    i--;
                } else str.setCharAt(i, Character.toUpperCase(str.charAt(i)));
            }
        }
        return str.toString();
    }

    public Club FindClub(String ClubName) { //will return null if club is not registered yet
        Club wanted = null;
        String FormattedClubName = FormatName(ClubName);
        for (var c : ClubList) {
            if (c.getName().equalsIgnoreCase(FormattedClubName)) {
                wanted = c;
                break;
            }
        }
        return wanted;
    }

    public Country FindCountry(String CountryName) { //will return null if club is not registered yet
        Country wanted = null;
        String FormattedCountryName = FormatName(CountryName);
        for (var c : CountryList) {
            if (c.getName().equalsIgnoreCase(FormattedCountryName)) {
                wanted = c;
                break;
            }
        }
        return wanted;
    }

    public Position FindPosition(String PositionName) { //will return null if club is not registered yet
        Position wanted = null;
        String FormattedPositionName = FormatName(PositionName);
        for (var c : PositionList) {
            if (c.getName().equalsIgnoreCase(FormattedPositionName)) {
                wanted = c;
                break;
            }
        }
        return wanted;
    }

    public void addPlayerToLeague(Player p) {
        //size check and player existence check is done in main
        var c = FindClub(p.getClubName());
        if (c == null) {
            c = new Club(p.getClubName());
            ClubList.add(c);
        }
        String CountryName = FormatName(p.getCountry());
        var country = FindCountry(CountryName);
        if (country == null) {
            country = new Country(CountryName);
            CountryList.add(country);
        }
        String PositionName = FormatName(p.getPosition());
        var position = FindPosition(PositionName);
        if (position == null) {
            position = new Position(PositionName);
            PositionList.add(position);
        }
        CentralPlayerDatabase.add(p);
        c.addPlayerToClub(p);
        country.incrementCount();
        position.incrementCount();
    }

    public Player SearchByName(String name) {
        String FormattedName = FormatName(name);
        for (var p : CentralPlayerDatabase) {
            if (p.getName().equalsIgnoreCase(FormattedName)) return p;
        }
        return null;
    }

    public List<Player> SearchPlayerByClubCountry(String club, String country) {
        String FormattedClubName = FormatName(club);
        String FormattedCountryName = FormatName(country);
        List<Player> wantedPlayers = new ArrayList<>();
        for (var p : CentralPlayerDatabase) {
            if ((country.equalsIgnoreCase("ANY") || p.getCountry().equalsIgnoreCase(FormattedCountryName)) && (club.equalsIgnoreCase("ANY") || p.getClubName().equalsIgnoreCase(FormattedClubName))) {
                wantedPlayers.add(p);
            }
        }
        return wantedPlayers;
    }

    public List<Player> SearchPlayerByPosition(String position) {
        List<Player> wantedPlayers = new ArrayList<>();
        for (var p : CentralPlayerDatabase) {
            if (p.getPosition().equalsIgnoreCase(position)) {
                wantedPlayers.add(p);
            }
        }
        return wantedPlayers;
    }

    public List<Player> SearchPlayerBySalary(double lowRange, double highRange) {
        List<Player> wantedPlayers = new ArrayList<>();
        for (var p : CentralPlayerDatabase) {
            double salary = p.getWeeklySalary();
            if (lowRange <= salary && salary <= highRange) {
                wantedPlayers.add(p);
            }
        }
        return wantedPlayers;
    }

    public void showCountryWisePlayerCount() {
        System.out.println("The country wise player count is shown below:");
        for (var country : CountryList) {
            int len = country.getName().length();
            System.out.print(country);
            for (int i = 1; i <= 15 - len; i++) System.out.print(' ');
            System.out.println(": " + country.getCount());
        }
    }

    public void showClubWisePlayerCount(){
        System.out.println("The club wise player count is shown below:");
        for (var club : ClubList) {
            int len = club.getName().length();
            System.out.print(club);
            for (int i = 1; i <= 20 - len; i++) System.out.print(' ');
            System.out.println(": " + club.getPlayerCount());
        }
    }

    public void showPositionWisePlayerCount() {
        System.out.println("The position wise player count is shown below:");
        for (var position : PositionList) {
            int len = position.getName().length();
            System.out.print(position);
            for (int i = 1; i <= 15 - len; i++) System.out.print(' ');
            System.out.println(": " + position.getCount());
        }
    }

    public void showPositionWisePlayerCount2() {
        System.out.println("The position wise player count is shown below:");
        for (var p : new String[]{"Goalkeeper", "Midfielder", "Defender", "Forward"}){
            System.out.println(p + ": " + SearchPlayerByPosition(p).size());
        }
    }
}
