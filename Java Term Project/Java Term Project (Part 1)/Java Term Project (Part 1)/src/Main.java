import java.util.Scanner;

public class Main {
    public static String FILE_NAME = "players.txt";

    public static void main(String[] args) throws Exception {
        League FiveASideLeague = new League();
        var loaded = FileOperations.readFromFile(FILE_NAME);
        for (var p : loaded){
            FiveASideLeague.addPlayerToLeague(p);
        }
        int choice;
        Scanner scanner = new Scanner(System.in);
        while (true){
            UserInput.showMainOption();
            try {
                choice = Integer.parseInt(scanner.nextLine());
            }catch (Exception e){
                System.out.println("You must enter a choice between 1 to 4");
                continue;
            }
            if (choice == 1){
                int response;
                while (true){
                    UserInput.showSubOption(0);
                    try {
                        response = Integer.parseInt(scanner.nextLine());
                    }catch (Exception e){
                        System.out.println("You must enter a choice between 1 to 6");
                        continue;
                    }
                    if (response == 1){
                        System.out.print("Enter Player Name: ");
                        String name = scanner.nextLine();
                        var found = FiveASideLeague.SearchByName(name);
                        if (found == null) System.out.println("No such player with this name");
                        else found.showDetails();
                    }
                    else if (response == 2){
                        String club, country;
                        System.out.print("Enter Country Name: ");
                        country = scanner.nextLine();
                        System.out.print("Enter Club Name (Enter \"ANY\" for club independent choice): ");
                        club = scanner.nextLine();
                        var found = FiveASideLeague.SearchPlayerByClubCountry(club, country);
                        if (found.isEmpty()) System.out.println("No such player with this country and club");
                        else{
                            int i = 1;
                            for (var p : found){
                                System.out.println(i++ + ".");
                                p.showDetails();
                            }
                            System.out.println();
                        }
                    }
                    else if (response == 3){
                        String position;
                        System.out.print("Enter Position: ");
                        position = scanner.nextLine();
                        var found = FiveASideLeague.SearchPlayerByPosition(position);
                        if (found.isEmpty()) System.out.println("No such player with this position");
                        else{
                            int i = 1;
                            for (var p : found){
                                System.out.println(i++ + ".");
                                p.showDetails();
                            }
                            System.out.println();
                        }
                    }
                    else if (response == 4){
                        double low = 0, high = 0;
                        boolean done = true;
                        while (done) {
                            System.out.print("Enter lower range of salary: ");
                            try {
                                low = Double.parseDouble(scanner.nextLine());
                                done = false;
                            } catch (Exception e) {
                                System.out.println("You must input a proper floating value!");
                                done = true;
                            }
                        }
                        done = true;
                        while (done) {
                            System.out.print("Enter upper range of salary: ");
                            try {
                                high = Double.parseDouble(scanner.nextLine());
                                done = false;
                            } catch (Exception e) {
                                System.out.println("You must input a proper floating value!");
                                done = true;
                            }
                        }
                        var found = FiveASideLeague.SearchPlayerBySalary(low, high);
                        if (found.isEmpty()) System.out.println("No such player with this weekly salary range.");
                        else{
                            int i = 1;
                            for (var p : found){
                                System.out.println(i++ + ".");
                                p.showDetails();
                            }
                            System.out.println();
                        }
                    }
                    else if (response == 5){
                        FiveASideLeague.showCountryWisePlayerCount();
                    }
                    else if (response == 6) break;
                    else System.out.println("You must enter a choice between 1 to 6.");
                }
            }
            else if (choice == 2){
                int response;
                while (true) {
                    UserInput.showSubOption(1);
                    try {
                        response = Integer.parseInt(scanner.nextLine());
                    }catch (Exception e){
                        System.out.println("You must enter a choice between 1 to 6");
                        continue;
                    }
                    String club;
                    int ID = 0;
                    if (response >= 1 && response <= 4){
                        System.out.println("Enter Club Name: ");
                        club = scanner.nextLine();
                        ID = FiveASideLeague.FindClubID(club);
                        if (ID == -1){
                            System.out.println("No such club with this name");
                            continue;
                        }
                    }
                    if (response == 1){
                        var found = FiveASideLeague.getClub(ID).SearchMaximumSalary();
                        int i = 1;
                        for (var p : found){
                            System.out.println(i++ + ".");
                            p.showDetails();
                        }
                        System.out.println();
                    }
                    else if (response == 2){
                        var found = FiveASideLeague.getClub(ID).SearchMaximumAge();
                        int i = 1;
                        for (var p : found){
                            System.out.println(i++ + ".");
                            p.showDetails();
                        }
                        System.out.println();
                    }
                    else if (response == 3){
                        var found = FiveASideLeague.getClub(ID).SearchMaximumHeight();
                        int i = 1;
                        for (var p : found){
                            System.out.println(i++ + ".");
                            p.showDetails();
                        }
                        System.out.println();
                    }
                    else if (response == 4){
                        System.out.printf("%.10f\n", FiveASideLeague.getClub(ID).TotalYearlySalary());
                    }
                    else if (response == 5) break;
                    else System.out.println("You must enter a choice between 1 to 5.");
                }
            }
            else if (choice == 3){
                var pNew = UserInput.InputNewPlayerInformation(scanner, FiveASideLeague);
                if (pNew != null){
                    FiveASideLeague.addPlayerToLeague(pNew);
                    System.out.println("Player has been added to the league database!!!");
                }
            }
            else if (choice == 4){
                FileOperations.writeToFile(FILE_NAME, FiveASideLeague.CentralPlayerDatabase);
                break;
            }
            else System.out.println("You must enter a choice between 1 to 4");
        }
        scanner.close();
    }
}
