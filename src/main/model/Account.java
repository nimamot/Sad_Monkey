package model;

import java.util.ArrayList;
import java.util.List;

// Represents an account having an id, owner name, Email Address and a SMWallet
public class Account {
    private static int nextAccountId = 1;
    private int id;
    private String name;
    private SMWallet wallet;
    private String email;
    private List<NFT> watchList;

    // REQUIRES: accountName has a non-zero length
    // EFFECTS: name on account is set to name; account id is a
    //          positive integer not assigned to any other account;
    //          Any created account automatically gets a new SMWallet
    public Account(String name, String email) {
        this.name = name;
        this.email = email;
        id = nextAccountId++;
        wallet = new SMWallet(id);
        this.watchList = new ArrayList<NFT>();

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<NFT> getWatchList() {
        return watchList;
    }

    public void addToWatchlist(NFT nft) {
        watchList.add(nft);

    }

    public SMWallet getWallet() {
        return wallet;
    }



}
