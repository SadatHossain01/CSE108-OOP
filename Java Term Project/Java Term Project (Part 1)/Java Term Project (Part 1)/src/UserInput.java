import java.util.Scanner;

public class UserInput {
    private static final String[] MAIN_OPTION = {"Search Players", "Search Clubs", "Add Player", "Exit System"};
    private static final String[][] SUB_OPTION = {{"By Player Name", "By Club and Country", "By Position", "By Salary Range", "Country-wise player count", "Back to Main Menu"}, {"Player(s) with the maximum salary of a club", "Player(s) with the maximum age of a club", "Player(s) with the maximum height of a club", "Total yearly salary of a club", "Back to Main Menu"}, {}, {}};

    public static void showMainOption(){
        System.out.println("What are you interested to do?");
        for (int i=0; i<MAIN_OPTION.length; i++){
            int j = i + 1;
            System.out.println("(" + j + ") " + MAIN_OPTION[i]);
        }
    }
    public static void showSubOption(int index){
        if (index != 0 && index != 1) return;
        if (index == 0){
            System.out.println("How do you want to search for a player?");
        }
        else {
            System.out.println("How do you want to search by a club?");
        }
        for (int i=0; i<SUB_OPTION[index].length; i++){
            int j = i + 1;
            System.out.print("(" + j + ") ");
            System.out.println(SUB_OPTION[index][i]);
        }
    }
    public static Player InputNewPlayerInformation(Scanner scanner, League l){
        System.out.println("Please enter information as instructed:");
        Player p = new Player();
        System.out.print("Name: ");
        String name = scanner.nextLine();
        if (l.SearchByName(name) != null){
            System.out.println("This player is already registered in the database!");
            return null;
        }
        else p.setName(name);
        System.out.print("Club: ");
        String club = scanner.nextLine();
        var c = l.FindClub(club);
        p.setClubName(club);
        if (c != null && c.getSize() >= 7){
            var sz = c.getSize();
            System.out.println("Sorry, this club already has " + sz + " players registered.");
            return null;
        }
        System.out.print("Country: ");
        p.setCountry(scanner.nextLine());
        System.out.print("Age: ");
        try{
            p.setAge(Integer.parseInt(scanner.nextLine()));
        } catch (Exception e) {
            System.out.println("Age must be a positive integer!");
            return null;
        }
        System.out.print("Height(in meter): ");
        try {
            p.setHeight(Double.parseDouble(scanner.nextLine()));
        } catch (Exception e){
            System.out.println("Height must be a positive real number!");
            return null;
        }
        System.out.print("Playing Position: ");
        p.setPosition(scanner.nextLine());
        System.out.print("Jersey Number: ");
        try {
            p.setNumber(Integer.parseInt(scanner.nextLine()));
        } catch (Exception e){
            System.out.println("Number must be a positive integer!");
            return null;
        }
        System.out.print("Weekly Salary: ");
        try {
            p.setWeeklySalary(Double.parseDouble(scanner.nextLine()));
        } catch (Exception e){
            System.out.println("Weekly Salary must be a positive real number!");
            return null;
        }
        return p;
    }
}
