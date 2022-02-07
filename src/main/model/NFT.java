package model;

import java.util.ArrayList;
import java.util.List;

// Represents a single NFT
public class NFT {
    private String owner;
    private int price;
    private String title;
    private String history;
    private List<NFT> listOfNFTs; // list of all NFTs on the site


    // !!!
    public NFT() {
        listOfNFTs = new ArrayList<>();
    }

    // REQUIRES: title and owner have a non-zero length and price > 0
    // EFFECTS: Construct an NFT object with title, price and owner's name
    public NFT(String title, int price, String owner) {
        this.title = title;
        this.price = price;
        this.owner = owner;
    }

    // MODIFIES: this
    // EFFECTS: add a new nft object to the listOfNFTs
    public void addNFT(NFT nft) {
        listOfNFTs.add(nft);
    }

    public String getOwner() {
        return this.owner;
    }

    public void setOwner(String name) {
        this.owner = name;
    }

    public List<NFT>  getAllNFTs() {
        return listOfNFTs;
    }

    public String getTitle() {
        return this.title;
    }

    public int getPrice() {
        return this.price;
    }

    // REQUIRES: title must exist in the listOfNFTs
    // EFFECTS: return the NFT object with the given title
    public NFT getNftByTitle(String title) {
        NFT wantedNft = new NFT();
        for (NFT eachNft : listOfNFTs) {
            if (eachNft.title.equals(title)) {
                wantedNft = eachNft;
            }
        }
        return wantedNft;
    }
}
