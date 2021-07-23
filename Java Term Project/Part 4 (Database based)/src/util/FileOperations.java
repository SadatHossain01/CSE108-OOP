package util;

import DataModel.Club;
import DataModel.Country;
import DataModel.League;
import DataModel.Player;
import javafx.util.Pair;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


public class FileOperations {
    public static List<Player> readPlayerDataFromFile(String FILE_NAME) throws Exception {
        List<Player> playerList = new ArrayList<>();
        BufferedReader input = new BufferedReader(new InputStreamReader(new FileInputStream(FILE_NAME), "windows-1252"));
        while (true) {
            String line = input.readLine();
            if (line == null)
                break;
            String[] tokens = line.split(";");
            try {
                Player p = new Player(tokens[0], tokens[2], tokens[1], tokens[6], Integer.parseInt(tokens[3]), Double.parseDouble(tokens[8]), tokens[11], tokens[9], Integer.parseInt(tokens[5]), Club.decodeSalaryString(tokens[4]), Double.parseDouble(tokens[7]), Club.decodeSalaryString(tokens[10]));
                playerList.add(p);
            }
            catch (Exception e){
                System.out.println(e);
                System.out.println(Arrays.toString(tokens));
            }
        }
        input.close();
        return playerList;
    }

    public static HashMap<String, String> readCredentialsOfClubs(String FILE_NAME) throws IOException {
        HashMap<String, String> clubPasswords = new HashMap<>();
        BufferedReader input = new BufferedReader(new InputStreamReader(new FileInputStream(FILE_NAME), "windows-1252"));
        while (true){
            String line = input.readLine();
            if (line == null) break;
            try {
                String[] tokens = line.split(",");
                String username = tokens[0];
                String password = tokens[1];
                clubPasswords.put(username, password);
            }
            catch (Exception ignored){}
        }
        input.close();
        return clubPasswords;
    }

    public static ArrayList<Pair<String, String>> readFlagLinkOfCountries(String FILE_NAME) throws IOException {
        ArrayList<Pair<String, String>> countryFlagList = new ArrayList<>();
        BufferedReader input = new BufferedReader(new InputStreamReader(new FileInputStream(FILE_NAME)));
        while (true){
            String line = input.readLine();
            if (line == null) break;
            try {
                String[] tokens = line.split(";");
                String name = tokens[1];
                String flagLink = tokens[2];
                countryFlagList.add(new Pair<>(name, flagLink));
            } catch (Exception ignored) {}
        }
        input.close();
        return countryFlagList;
    }

    public static ArrayList<Pair<String, String>> readInformationOfClubs(String FILE_NAME, League league) throws IOException {
        ArrayList<Pair<String, String>> clubLogoList = new ArrayList<>();
        BufferedReader input = new BufferedReader(new InputStreamReader(new FileInputStream(FILE_NAME)));
        while (true){
            String line = input.readLine();
            if (line == null) break;
            try {
                String[] tokens = line.split(";");
                String name = tokens[1];
                double worth = Club.decodeSalaryString(tokens[2]);
                double budget = Club.decodeSalaryString(tokens[3]);
                String logoLink = tokens[4];
                clubLogoList.add(new Pair<>(name, logoLink));
                var club = league.FindClub(name);
                if (club != null) {
                    club.setWorth(worth);
                    club.setTransferBudget(budget);
                    club.setLogoLink(logoLink);
                }
            } catch (Exception ignored) {}
        }
        input.close();
        return clubLogoList;
    }

    public static void writePlayerDataToFile(String FILE_NAME, List<Player> playerList) throws Exception {
        BufferedWriter output = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(FILE_NAME)));
        for (Player p : playerList) {
            output.write(p.getName() + "," + p.getCountry() + "," + p.getAge() + "," + p.getHeight() + ","
                    + p.getClubName() + "," + p.getPosition() + "," + p.getNumber() + "," + p.getWeeklySalary());
            output.write("\n");
        }
        output.close();
    }

    public static void writeClubCredentialsToFile(String FILE_NAME, List<Club> clubList) throws Exception {
        BufferedWriter output = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(FILE_NAME)));
        for (Club c : clubList) {
            output.write(c.getName() + "," + c.getName().toLowerCase());
            System.out.println(c.getName());
            output.write("\n");
        }
        output.close();
    }
}
