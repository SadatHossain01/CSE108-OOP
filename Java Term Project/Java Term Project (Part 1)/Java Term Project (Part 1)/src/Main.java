import java.util.Scanner;

public class Main {
    public static String FILE_NAME = "players.txt";

    public static void main(String[] args) throws Exception {
        League FiveASideLeague = new League();
        var loaded = FileOperations.readFromFile(FILE_NAME);
        for (var p : loaded) {
            FiveASideLeague.addPlayerToLeague(p);
        }
        int MainChoice;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            UserInteraction.showMainOption();
            try {
                MainChoice = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("You must enter a choice between 1 to 4");
                continue;
            }
            if (MainChoice == 1) {
                int response;
                while (true) {
                    UserInteraction.showSubOption(0);
                    try {
                        response = Integer.parseInt(scanner.nextLine());
                    } catch (Exception e) {
                        System.out.println("You must enter a choice between 1 to 7");
                        continue;
                    }
                    if (response == 1) {
                        System.out.print("Enter Player Name: ");
                        String name = scanner.nextLine();
                        var found = FiveASideLeague.SearchByName(name);
                        if (found == null) System.out.println("No such player with this name");
                        else found.showDetails();
                    } else if (response == 2) {
                        String club, country;
                        System.out.print("Enter Country Name (Enter \"ANY\" for country independent choice): ");
                        country = scanner.nextLine();
                        System.out.print("Enter Club Name (Enter \"ANY\" for club independent choice): ");
                        club = scanner.nextLine();
                        var found = FiveASideLeague.SearchPlayerByClubCountry(club, country);
                        if (found.isEmpty()) System.out.println("No such player with this country and club");
                        else {
                            int i = 1;
                            for (var p : found) {
                                System.out.println(i++ + ".");
                                p.showDetails();
                            }
                            System.out.println();
                        }
                    } else if (response == 3) {
                        String position;
                        System.out.print("Enter Position: ");
                        position = scanner.nextLine();
                        while (!(position.equalsIgnoreCase("FORWARD") || position.equalsIgnoreCase("MIDFIELDER") || position.equalsIgnoreCase("DEFENDER") || position.equalsIgnoreCase("GOALKEEPER"))) {
                            System.out.print("Position must be one of the following:\nForward Midfielder Defender Goalkeeper\nPlease input a proper playing position: ");
                            position = scanner.nextLine();
                        }
                        var found = FiveASideLeague.SearchPlayerByPosition(position);
                        if (found.isEmpty()) System.out.println("No such player with this position");
                        else {
                            int i = 1;
                            for (var p : found) {
                                System.out.println(i++ + ".");
                                p.showDetails();
                            }
                            System.out.println();
                        }
                    } else if (response == 4) {
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
                        else {
                            int i = 1;
                            for (var p : found) {
                                System.out.println(i++ + ".");
                                p.showDetails();
                            }
                            System.out.println();
                        }
                    } else if (response == 5) {
                        FiveASideLeague.showCountryWisePlayerCount();
                    } else if (response == 6){
                        FiveASideLeague.showPositionWisePlayerCount2();
                    }
                    else if (response == 7) break;
                    else System.out.println("You must enter a choice between 1 to 7.");
                }
            } else if (MainChoice == 2) {
                int response;
                while (true) {
                    UserInteraction.showSubOption(1);
                    try {
                        response = Integer.parseInt(scanner.nextLine());
                    } catch (Exception e) {
                        System.out.println("You must enter a choice between 1 to 6");
                        continue;
                    }
                    String club;
                    Club c = null;
                    if (response >= 1 && response <= 4) {
                        System.out.print("Enter Club Name: ");
                        club = scanner.nextLine();
                        c = FiveASideLeague.FindClub(club);
                        if (c == null) {
                            System.out.println("No such club with this name");
                            continue;
                        }
                    }
                    if (response == 1) {
                        var found = c.SearchMaximumSalary();
                        int i = 1;
                        for (var p : found) {
                            System.out.println(i++ + ".");
                            p.showDetails();
                        }
                        System.out.println();
                    } else if (response == 2) {
                        var found = c.SearchMaximumAge();
                        int i = 1;
                        for (var p : found) {
                            System.out.println(i++ + ".");
                            p.showDetails();
                        }
                        System.out.println();
                    } else if (response == 3) {
                        var found = c.SearchMaximumHeight();
                        int i = 1;
                        for (var p : found) {
                            System.out.println(i++ + ".");
                            p.showDetails();
                        }
                        System.out.println();
                    } else if (response == 4) {
                        var TotalYearlySalary = c.TotalYearlySalary();
                        if (TotalYearlySalary >= 1e9) System.out.printf("%.3f billion\n", TotalYearlySalary / 1e9);
                        else if (TotalYearlySalary >= 1e6) System.out.printf("%.3f million\n", TotalYearlySalary / 1e6);
                        else System.out.printf("%.3f\n", c.TotalYearlySalary());
                    } else if (response == 5) break;
                    else System.out.println("You must enter a choice between 1 to 5.");
                }
            } else if (MainChoice == 3) {
                var pNew = UserInteraction.InputNewPlayerInformation(scanner, FiveASideLeague);
                if (pNew != null) {
                    FiveASideLeague.addPlayerToLeague(pNew);
                    System.out.println("Player has been added to the league database!!!");
                }
            } else if (MainChoice == 4) {
                FileOperations.writeToFile(FILE_NAME, FiveASideLeague.CentralPlayerDatabase);
                break;
            } else System.out.println("You must enter a choice between 1 to 4");
        }
        scanner.close();
    }
}
