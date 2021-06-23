import java.util.Scanner;

public class UserInteraction {
    private static final String[] MAIN_OPTION = {"Search Players", "Search Clubs", "Add Player", "Exit System"};
    private static final String[][] SUB_OPTION = {{"By Player Name", "By Club and Country", "By Position", "By Salary Range", "Country-wise player count", "Position-wise player count", "Back to Main Menu"}, {"Player(s) with the maximum salary of a club", "Player(s) with the maximum age of a club", "Player(s) with the maximum height of a club", "Total yearly salary of a club", "Back to Main Menu"}, {}, {}};

    public static void showMainOption() {
        System.out.println("Main Menu:");
        for (int i = 0; i < MAIN_OPTION.length; i++) {
            int j = i + 1;
            System.out.println("(" + j + ") " + MAIN_OPTION[i]);
        }
        System.out.print("Choose an option [1-4]: ");
    }

    public static void showSubOption(int index) {
        if (index != 0 && index != 1) return;
        if (index == 0) {
            System.out.println();
            System.out.println("Player Searching Menu:");
            for (int i = 0; i < SUB_OPTION[0].length; i++) {
                int j = i + 1;
                System.out.print("(" + j + ") ");
                System.out.println(SUB_OPTION[0][i]);
            }
            System.out.print("Choose an option [1-7]: ");
        } else {
            System.out.println();
            System.out.println("Club Searching Menu:");
            for (int i = 0; i < SUB_OPTION[1].length; i++) {
                int j = i + 1;
                System.out.print("(" + j + ") ");
                System.out.println(SUB_OPTION[1][i]);
            }
            System.out.print("Choose an option [1-5]: ");
        }
    }

    public static Player InputNewPlayerInformation(Scanner scanner, League l) {
        NegativeInputException NE = new NegativeInputException();
        System.out.println("Please enter the following information:");
        Player p = new Player();
        System.out.print("Name               : ");
        String name = scanner.nextLine();
        if (l.SearchByName(name) != null) {
            System.out.println("This player is already registered in the database!");
            return null;
        } else p.setName(name);
        System.out.print("Club               : ");
        String club = scanner.nextLine();
        var c = l.FindClub(club);
        p.setClubName(club);
        if (c != null && c.getSize() >= 7) {
            var sz = c.getSize();
            System.out.println("Sorry, this club already has " + sz + " players registered.");
            return null;
        }
        System.out.print("Country            : ");
        p.setCountry(scanner.nextLine());
        System.out.print("Age                : ");
        boolean done = false;
        while (true) {
            try {
                int age = Integer.parseInt(scanner.nextLine());
                if (age <= 0) throw NE;
                p.setAge(age);
                done = true;
            } catch (Exception e) {
                System.out.println("Age must be a positive integer.");
                System.out.print("Age                : ");
            } finally {
                if (done) break;
            }
        }
        System.out.print("Height(meter)      : ");
        done = false;
        while (true) {
            try {
                double height = Double.parseDouble(scanner.nextLine());
                if (height <= 0) throw NE;
                p.setHeight(height);
                done = true;
            } catch (Exception e) {
                System.out.println("Height must be a positive real number.");
                System.out.print("Height(meter)      : ");
            } finally {
                if (done) break;
            }
        }
        System.out.print("Playing Position   : ");
        String position = scanner.nextLine();
        while (!(position.equalsIgnoreCase("FORWARD") || position.equalsIgnoreCase("MIDFIELDER") || position.equalsIgnoreCase("DEFENDER") || position.equalsIgnoreCase("GOALKEEPER"))) {
            System.out.println("Invalid Position. Valid Positions: Forward, Midfielder, Defender, Goalkeeper.");
            System.out.print("Playing Position   : ");
            position = scanner.nextLine();
        }
        p.setPosition(position);
        System.out.print("Jersey Number      : ");
        done = false;
        while (true) {
            try {
                int number = Integer.parseInt(scanner.nextLine());
                if (number <= 0) throw NE;
                if (c != null && c.NumberTaken.contains(number)) {
                    System.out.println("Sorry, this club already has a player registered at number " + number + ". You should register him in another number");
                    System.out.print("Jersey Number      : ");
                } else {
                    p.setNumber(number);
                    done = true;
                }
            } catch (Exception e) {
                System.out.println("Jersey Number must be a positive integer.");
                System.out.print("Jersey Number           : ");
            } finally {
                if (done) break;
            }
        }
        System.out.print("Weekly Salary      : ");
        done = false;
        while (true) {
            try {
                double salary = Double.parseDouble(scanner.nextLine());
                if (salary <= 0) throw NE;
                p.setWeeklySalary(salary);
                done = true;
            } catch (Exception e) {
                System.out.println("Weekly Salary must be a positive real number.");
                System.out.print("Weekly Salary      : ");
            } finally {
                if (done) break;
            }
        }
        return p;
    }
}
