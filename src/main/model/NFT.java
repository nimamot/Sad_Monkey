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

    // how are you gonna desplay?

    public NFT() {
        listOfNFTs = new ArrayList<>();

    }

    public NFT(String title, int price, String owner) {
        this.title = title;
        this.price = price;
        this.owner = owner;
    }



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
}
