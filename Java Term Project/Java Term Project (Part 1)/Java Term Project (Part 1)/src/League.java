import java.util.List;

public class League {
    private List<Club>clubs;
    public int SearchClubByName(String name){
        for (var club:clubs){
            if (club.getName().equalsIgnoreCase(name)) return club.getID();
        }
        return -1;
    }
}
