package sample;

import DTO.*;
import DataModel.Club;
import DataModel.League;
import DataModel.Player;
import util.NetworkUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class ReadThreadServer implements Runnable{
    private Thread t;
    private NetworkUtil networkUtil;
    private League league;
    private Club club;
    private HashMap<String, NetworkUtil> clubNetworkUtilMap;
    private ArrayList<Player> transferListedPlayers;
    private boolean isThreadOn = true;

    public ReadThreadServer(NetworkUtil networkUtil, League league, Club club, ArrayList<Player> transferListedPlayers, HashMap<String, NetworkUtil> clubNetworkUtilMap) {
        this.networkUtil = networkUtil;
        this.league = league;
        this.club = club;
        this.transferListedPlayers = transferListedPlayers;
        this.clubNetworkUtilMap = clubNetworkUtilMap;
        t = new Thread(this, "Read Thread Server");
        t.start();
    }

    @Override
    public void run() {
        while (isThreadOn){
            Object next = null;
            while (isThreadOn){
                try {
                    next = networkUtil.read();
                    break;
                } catch (IOException | ClassNotFoundException ignored){}
            }
            if (next instanceof BuyRequest){
                System.out.println(((BuyRequest) next).getPotentialBuyerClub() + " wants to buy " + ((BuyRequest) next).getPlayerName() + " from " + ((BuyRequest) next).getPlayerCurrentClub());
                var p = league.FindPlayer(((BuyRequest) next).getPlayerName());
                var buyer = league.FindClub(((BuyRequest) next).getPotentialBuyerClub());
                var seller = league.FindClub(((BuyRequest) next).getPlayerCurrentClub());
                if (p.isTransferListed()){
                    System.out.println(((BuyRequest) next).getPlayerName() + " is currently indeed up for sale");
                    if (buyer.getTransferBudget() >= p.getTransferFee()){
                        System.out.println("Sufficient budget. Processing buying request....");
                        league.transferPlayerToNewClub(p, seller, buyer);
                        transferListedPlayers.remove(p);
                        System.out.println("Player purchase successful. All budget update done");
                        try {
                            networkUtil.write(new NewPlayerPurchased(p));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    else{
                        System.out.println("Insufficient transfer fee");
                        try {
                            networkUtil.write(new RequestResponse(RequestResponse.Type.InsufficientTransferFee));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                else{
                    System.out.println(p.getName() + " has already been bought by " + p.getClubName());
                    try {
                        networkUtil.write(new RequestResponse(RequestResponse.Type.AlreadyBought));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            else if (next instanceof Request){
                if (((Request) next).requestType == Request.Type.UpdatedListQuery) {
                    System.out.println("Received a request from " + ((Request) next).getFrom() + " to avail the updated transfer list");
                    try {
                        networkUtil.write(new UpdatedTransferList(transferListedPlayers, ((Request) next).getFrom()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Updated list has been delivered on request to " + ((Request) next).getFrom());
                }
                else if (((Request) next).requestType == Request.Type.LogOut){
                    clubNetworkUtilMap.remove(((Request) next).getFrom());
                    System.out.println(((Request) next).getFrom() + " has been successfully logged out from the network");
                    System.out.println("Quitting from this read thread server");
                    isThreadOn = false;
                }
            }
        }
    }
}
