package persistence;

import model.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.json.*;
import ui.SDMonkeyApp;

// Represents a reader that reads account from JSON data stored in file
public class JsonReader {
    private final String source;
    private static final List<NFT> nfts = new ArrayList<NFT>();
    private final List<String> titles = new ArrayList<String>();
    private List<String> owners;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads account from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Account read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseAccount(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses account from JSON object and returns it
    //          add NFTs to watchlist, if they are owned by this user then also add it to their wallet
    private Account parseAccount(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String email = jsonObject.getString("Email");
        Account ac = new Account(name, email);
        double prevBalance =  jsonObject.getDouble("Current Balance");
        ac.getWallet().deposit(" ", prevBalance);

        JSONArray jsonArray = jsonObject.getJSONArray("watchlist");
        for (Object json : jsonArray) {
            JSONObject nextNFT = (JSONObject) json;
            addNewNFTsAndAddWatchlistedNFTs(ac, nextNFT);
        }

        JSONArray jsonArrayForWallet = jsonObject.getJSONArray("owned nfts");
        for (Object json : jsonArrayForWallet) {
            JSONObject nextNFT = (JSONObject) json;
            handleWallet(ac, nextNFT);
        }
        return ac;
    }

    // MODIFIES: ac
    // EFFECTS: add the watchlist nfts to the watchlist
    //          get the prevoiuse balance form the json file and add it to the balance
    private void addNewNFTsAndAddWatchlistedNFTs(Account ac, JSONObject jsonObject) {
        String title = jsonObject.getString("title");
        String owner = jsonObject.getString("owner");
        int price = jsonObject.getInt("price");
        NFT newNFT = new NFT(title, price, owner);
        nfts.add(newNFT);
        titles.add(title);
        ac.addToWatchlist(newNFT);
        //if (!title.equals("monkey 1") && !title.equals("monkey 2")) {
        //    SDMonkeyApp.addToCollection();
        //}
        // put the nft in user's wallet, if they own it
       // if (owner.equals(name) && ) {
         //   ac.getWallet().addNFT(newNFT);
        //}
    }


    //MODIFIES: ac
    //EFFECTS:  add NFTs to the wallet
    public void handleWallet(Account ac, JSONObject jsonObject) {
        String title = jsonObject.getString("title");
        String owner = jsonObject.getString("owner");
        int price = jsonObject.getInt("price");
        NFT newNFT = new NFT(title, price, owner);
        ac.getWallet().addNFT(newNFT);
        if (!titles.contains(title)) {
            nfts.add(newNFT);
        }




    }

    // EFFECTS: return List of NFTs
    public static List<NFT> getNfts() {
        return nfts;
    }

}
