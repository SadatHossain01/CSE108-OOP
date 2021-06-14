import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class FileOperations {
    public static List<Player> readFromFile(String FILE_NAME) throws Exception {
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

    public static void writeToFile(String FILE_NAME, List<Player> playerList) throws Exception {
        BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME));
        for (Player p : playerList) {
            bw.write(p.getName() + "," + p.getCountry() + "," + Integer.toString(p.getAge()) + "," +  Double.toString(p.getHeight()) + "," + p.getClub() + "," + p.getPosition() + "," + Integer.toString(p.getNumber()) + "," + Double.toString(p.getWeeklySalary()));
            bw.write("\n");
        }
        bw.close();
    }
}
