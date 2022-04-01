package model;

import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// Represents a wallet having a 12-digit address, account id and balance (in dollars)
public class SMWallet {
    private final String accountID;
    private double balance;
    private final List<NFT> ownedNFTs;

    // EFFECTS: account number is set to the account number;
    //          Balance is initialized to 0
    //          accounts address is set to account ID
    public SMWallet(int accountNumber) {
        this.accountID = Integer.toString(accountNumber);
        String address = accountID;
        this.balance = 0;
        this.ownedNFTs = new ArrayList<>();

    }

    // REQUIRES: amount > 0
    // MODIFIES: this, balance
    // EFFECTS: deposit money from a different account
    public void deposit(String walletAddress, double amount) {
        balance += amount;
    }


    // MODIFIES: this, balance
    // EFFECTS: change the balance if there is enough balance for the purchase;
    //          change the owner of that NFT
    public void makePurchase(NFT nft, String newOwner) {
        if (balance >= nft.getPrice()) {
            balance -= nft.getPrice();
            nft.setOwner(newOwner);
            ownedNFTs.add(nft);
            EventLog.getInstance().logEvent(new Event(nft.getTitle() + " was purchased"));

        }
    }


    // EFFECTS: return list of all owned NFTs
    public List<NFT> getOwnedNFT() {
        return ownedNFTs;
    }

    // EFFECTS: Return the current balance
    public double getBalance() {
        return balance;
    }

    // EFFECTS: Return users account ID
    public String getAccountNumber() {
        return accountID;
    }


    // MODIFIES: this
    // EFFECTS: nft objects to the wallet, only used for JsonReader
    public void addNFT(NFT nft) {
        ownedNFTs.add(nft);
    }

}

