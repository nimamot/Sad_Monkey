package model;

import java.util.ArrayList;
import java.util.List;

// Credits: TellerApp https://github.students.cs.ubc.ca/CPSC210/TellerApp
// Represents an account having an id, owner name, Email Address and a SMWallet
public class Account {
    private static int nextAccountId = 1;  // tracks id of next account created
    private int id;                        // account id
    private String name;                   // the account owner name
    private SMWallet wallet;
    private String email;
    private List<NFT> watchList;

    // REQUIRES: accountName has a non-zero length
    // EFFECTS: name on account is set to name; account id is a
    //          positive integer not assigned to any other account;
    //          the email on account is set to email;
    //          Any created account automatically gets a new SMWallet and a new empty watchlist
    public Account(String name, String email) {
        this.name = name;
        this.email = email;
        id = nextAccountId++;
        wallet = new SMWallet(id);
        this.watchList = new ArrayList<>();
    }

    // EFFECTS: return the account ID
    public int getId() {
        return id;
    }

    // EFFECTS: return the name on the account
    public String getName() {
        return name;
    }

    // EFFECTS: return users email
    public String getEmail() {
        return email;
    }

    // EFFECTS: return users watchlist
    public List<NFT> getWatchList() {
        return watchList;
    }

    // MODIFIES: this
    // EFFECTS: add the given nft to the watchlist
    public void addToWatchlist(NFT nft) {
        watchList.add(nft);
    }

    //EFFECTS:  Return user's wallet
    public SMWallet getWallet() {
        return wallet;
    }
}
