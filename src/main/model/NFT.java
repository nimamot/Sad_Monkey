package model;

import java.util.ArrayList;
import java.util.List;

public class NFT {
    private String owner;
    private int price;
    private String title;
    private String history;
    private List<String> listOfNFTs; // list of all NFTs on the site

    // how are you gonna desplay?

    public NFT() {
        listOfNFTs = new ArrayList<String>();
    }


    public void addNFT(String title) {

    }


    public void getOwner() {

    }

    public void setOwner(String name) {

    }

    public List<String> getAllNFTs() {
        return listOfNFTs;
    }
}
