package model;

// Represents a wallet having a 12-digit address, account id and balance (in dollars)
public class SMWallet {
    private String address;
    private String accountID;
    private double balance;

    // EFFECTS: account number is set to the account number
    public SMWallet(int accountNumber) {
        this.accountID = Integer.toString(accountNumber);
        // TODO:
        //  This has to be random and 12 digits long(alphabet + numbers)
        this.address = accountID;
        this.balance = 0;

    }

    // REQUIRES: amount > 0
    public void transfer(int walletAddress, int amount) {
        //stub
    }

    // MODIFIES: this
    // EFFECTS: change the balance if there is enough balance for the purchase;
    //          change the owner of that NFT
    public void buyNFT(NFT nft) {

    }


}
