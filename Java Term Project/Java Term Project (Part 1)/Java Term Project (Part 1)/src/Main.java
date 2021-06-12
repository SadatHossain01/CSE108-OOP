import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final String FILE_NAME = "players.txt";
    private static final String[] MAIN_OPTION = {"Search Players", "Search Clubs", "Add Player", "Exit System"};
    private static String[][] SUB_OPTION = new String[4][];
    public static List<Player> readFromFile() throws Exception {
        List<Player> playerList = new ArrayList();
        BufferedReader br = new BufferedReader(new FileReader(FILE_NAME));
        while (true) {
            String line = br.readLine();
            if (line == null) break;
            String[] tokens = line.split(",");
            Player p = new Player(tokens[0], tokens[1], Integer.parseInt(tokens[2]), Double.parseDouble(tokens[3]), tokens[4], tokens[5], Integer.parseInt(tokens[6]), Double.parseDouble(tokens[7]));
            playerList.add(p);
        }
        br.close();
        return playerList;
    }

    public static void writeToFile(List<Player> playerList) throws Exception {
        BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME));
        for (Player p : playerList) {
            bw.write(p.getName() + "," + p.getCountry() + "," + Integer.toString(p.getAge()) + "," +  Double.toString(p.getHeight()) + "," + p.getClub() + "," + p.getPosition() + "," + Integer.toString(p.getNumber()) + "," + Double.toString(p.getSalary()));
            bw.write("\n");
        }
        bw.close();
    }

    public static void initiate(){
        SUB_OPTION[0] = new String[]{"By Player Name", "By Club and Country", "By Position", "By Salary Range", "Country-wise player count", "Back to Main Menu"};
        SUB_OPTION[1] = new String[]{"Player(s) with the maximum salary of a club", "Player(s) with the maximum age of a club", "Player(s) with the maximum height of a club", "Total yearly salary of a club", "Back to Main Menu"};
    }


    public static void main(String[] args) throws Exception{
        initiate();
        List<Player>CentralDatabase = readFromFile();
        League league = new League();
        for (var p:CentralDatabase){
            league.addPlayerDoingAllChecks(p);
        }
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            for (int i=0; i<MAIN_OPTION.length; i++){
                int j = i + 1;
                System.out.print("(" + j + ") ");
                System.out.println(MAIN_OPTION[i]);
            }
            System.out.println("Choose Your Option:");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice){
                case 1:
                    for (int i=0; i<SUB_OPTION[0].length; i++){
                        int j = i + 1;
                        System.out.print("(" + j + ") ");
                        System.out.println(SUB_OPTION[0][i]);
                    }
                    boolean done = true;
                    while (done) {
                        System.out.println("How do you want to search a player?");
                        int choice2 = Integer.parseInt(scanner.nextLine());
                        switch (choice2) {
                            case 1:
                                System.out.println("Enter Player Name:");
                                String name = scanner.nextLine();
                                var ans = league.SearchPlayerByName(name);
                                if (ans == null) System.out.println("No such player with this name");
                                else ans.showDetails();
                                break;
                            case 2:
                                System.out.println("Enter the country:");
                                String country = scanner.nextLine();
                                System.out.println("Enter the club:");
                                String club = scanner.nextLine();
                                var list = league.SearchPlayerByClubCountry(club, country);
                                if (list.isEmpty()) System.out.println("No such player with this country and club");
                                else {
                                    for (var p:list) p.showDetails();
                                }
                                break;
                            case 3:
                                System.out.println("Enter Player Position:");
                                String position = scanner.nextLine();
                                var pos = league.SearchPlayerByPosition(position);
                                if (pos.isEmpty()) System.out.println("No such player with this position");
                                else {
                                    for (var p:pos) p.showDetails();
                                }
                                break;
                            case 4:
                                System.out.println("Enter lower range of salary");
                                int low, high;
                                low = Integer.parseInt(scanner.nextLine());
                                System.out.println("Enter higher range of salary");
                                high = Integer.parseInt(scanner.nextLine());
                                var sal = league.SearchPlayerBySalary(low, high);
                                if (sal.isEmpty()) System.out.println("No such player with this weekly salary range");
                                else{
                                    for (var p:sal) p.showDetails();
                                }
                                break;
                            case 5:
                                //country count left to do
                               break;
                            case 6:
                                done = false;
                                break;
                            default:
                                System.out.println("You have to input a choice between 1 to 6");
                        }
                    }
                    break;
                case 2:
                    break;
                case 3:
                    Player p = new Player();
                    System.out.println("Name: ");
                    String name = scanner.nextLine();
                    if (league.SearchPlayerByName(name) != null){
                        System.out.println("There is already a player with this name!");
                        break;
                    }
                    else p.setName(name);
                    System.out.println("Country: ");
                    p.setCountry(scanner.nextLine());
                    System.out.println("Age: ");
                    p.setAge(Integer.parseInt(scanner.nextLine()));
                    System.out.println("Height: ");
                    p.setHeight(Double.parseDouble(scanner.nextLine()));
                    System.out.println("Club: ");
                    String club = scanner.nextLine();
                    var wantedClub = league.SearchClubByName(club);
                    if (wantedClub.getSize() >= 7){
                        System.out.println("This club already has 7 players, so no more players can be added!");
                        break;
                    }
                    System.out.println("Position: ");
                    p.setPosition(scanner.nextLine());
                    System.out.println("Number: ");
                    p.setNumber(Integer.parseInt(scanner.nextLine()));
                    System.out.println("Salary: ");
                    p.setSalary(Double.parseDouble(scanner.nextLine()));
                    break;
                case 4:
                    exit = true;
                    break;
                default:
                    System.out.println("You have to input a choice between 1 to 4");
            }
        }
    }
}
