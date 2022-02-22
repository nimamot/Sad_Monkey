package model;

import exceptions.AccountExistsException;
import jdk.nashorn.internal.parser.JSONParser;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

// Credits: TellerApp https://github.students.cs.ubc.ca/CPSC210/TellerApp
// Represents an account having an id, owner name, Email Address and a SMWallet
public class Account implements Writable {
    private static int nextAccountId = 1;  // tracks id of next account created
    private final int id;                        // account id
    private final String name;                   // the account owner name
    private final SMWallet wallet;
    private final String email;
    private final List<NFT> watchList;
    private final List<Account> allAccounts;


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
        allAccounts = new ArrayList<>();
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


    // EFFECTS: add the new account to the list of accounts, if the already exists
    //          throw exception
    public void addAccountToAllAccounts(Account acc) throws AccountExistsException, FileNotFoundException {
        JSONObject json = new JSONObject();

        String userEmail = acc.getEmail();
        if (allAccounts.isEmpty()) {
            allAccounts.add(acc);
        } else {
            for (Account account : allAccounts) {
                if (userEmail.equals(account.getEmail())) {
                    throw new AccountExistsException();
                } else {
                    allAccounts.add(acc);
                }
            }
        }
    }

    // EFFECTS: return all accounts
    public List<Account> getAllAccounts() {
        return allAccounts;
    }

    // EFFECTS: change the owner of an NFT in the watchlist
    public void changeWhatchlistedNftOewner() {

    }


    // TODO
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        //json.put("Email", email);
        json.put("watchlist", watchList);
        //json.put("Wallet", wallet);
        json.put("Current Balance", wallet.getBalance());
        json.put("owned nfts", wallet.getOwnedNFT());
        json.put("name", name);
        json.put("Email", email); // accountToJson()
        return json;
    }

    // EFFECTS: returns NFTs in this workroom as a JSON array
    private JSONArray accountToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Account a : allAccounts) {
            jsonArray.put(a.getEmail());
            jsonArray.put(a.getName());
        }

        return jsonArray;
    }


}
