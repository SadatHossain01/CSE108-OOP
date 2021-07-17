package sample;

import DTO.ClubLoginAuthentication;
import DTO.RequestResponse;
import DataModel.Club;
import DataModel.League;
import DataModel.Player;
import util.FileOperations;
import util.NetworkUtil;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class Server {
    private ServerSocket serverSocket;
    private League FiveASideLeague;
    private HashMap<String, String> clubPasswordList;
    private HashMap<String, NetworkUtil> clubNetworkUtilMap;
    private ArrayList<Player> transferListedPlayers;

    public Server(int port) throws Exception {
        FiveASideLeague = new League();
        clubPasswordList = FileOperations.readCredentialsOfClubs("src/Assets/Text/ClubAuthentications.txt");
        var loaded = FileOperations.readPlayerDataFromFile("src/Assets/Text/players.txt"); //file name path tree starts from one step back of src, but others all start from src
        for (var p : loaded) FiveASideLeague.addPlayerToLeague(p);
        serverSocket = new ServerSocket(port);
    }

    public void serve(Socket clientSocket) throws IOException, ClassNotFoundException {
        var networkUtil = new NetworkUtil(clientSocket);
        var next = networkUtil.read();
        if (next instanceof ClubLoginAuthentication){
            String username = ((ClubLoginAuthentication) next).getUsername();
            String password = ((ClubLoginAuthentication) next).getPassword();
            System.out.println("New login request:\nUsername: " + username + " Password: " + password);
            if (clubNetworkUtilMap.containsKey(username)){
                networkUtil.write(new RequestResponse(RequestResponse.Type.AlreadyLoggedIn));
                System.out.println("This user is already logged in to the network");
            }
            else if (clubPasswordList.containsKey(username)){
                if (clubPasswordList.get(username).equals(password)) {
                    networkUtil.write(new RequestResponse(RequestResponse.Type.LoginSuccessful));
                    clubNetworkUtilMap.put(username, networkUtil);
                    Club c = FiveASideLeague.FindClub(username);
                    new ReadThreadServer(networkUtil, FiveASideLeague, c, transferListedPlayers, clubNetworkUtilMap);
                    System.out.println("Login successful. New Read Thread Server opened");
                }
                else{
                    networkUtil.write(new RequestResponse(RequestResponse.Type.IncorrectPassword));
                    System.out.println("Incorrect password");
                }
            }
            else{
                networkUtil.write(new RequestResponse(RequestResponse.Type.UsernameNotRegistered));
                System.out.println("Username not registered on server");
            }
        }
    }

    public static void main(String[] args) throws Exception {
        int port = 44444;
        Server server = new Server(port);
        while (true){
            var cs = server.serverSocket.accept();
            server.serve(cs);
        }
    }
}
