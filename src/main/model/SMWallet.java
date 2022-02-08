package model;

import java.util.ArrayList;
import java.util.List;

// Represents a wallet having a 12-digit address, account id and balance (in dollars)
public class SMWallet {
    private String address;
    private final String accountID;
    private double balance;
    private List<NFT> ownedNFTs;

    // EFFECTS: account number is set to the account number
    public SMWallet(int accountNumber) {
        this.accountID = Integer.toString(accountNumber);
        // TODO:
        //  This has to be random and 12 digits long(alphabet + numbers)
        this.address = accountID;
        this.balance = 0;
        this.ownedNFTs = new ArrayList<NFT>();

    }

    // REQUIRES: amount > 0
    // EFFECTS: deposit money from a different account
    public void deposit(String walletAddress, double amount) {
        balance += amount;
    }


    // MODIFIES: this
    // EFFECTS: change the balance if there is enough balance for the purchase;
    //          change the owner of that NFT
    public void makePurchase(NFT nft, String newOwner) {
        if (balance >= nft.getPrice()) {
            balance -= nft.getPrice();
            nft.setOwner(newOwner);
            ownedNFTs.add(nft);
        }
    }

    public List<NFT> getOwnedNFT() {
        return ownedNFTs;
    }

    public double getBalance() {
        return balance;
    }



}
