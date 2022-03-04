package ui;


import model.Account;
import model.NFT;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
// Credits: TellerApp https://github.students.cs.ubc.ca/CPSC210/TellerApp

public class SDMonkeyApp {
    private final NFT collection = new NFT();
    private Account account1;
    String name;
    String email;
    private List<String> titleOfNFTs = new ArrayList<String>();
    private Scanner input;
    private static final String JSON_STORE = "./data/account.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private boolean saveAccount;



    // EFFECTS: runs the SDMonkey application
    public SDMonkeyApp() {
        //input = new Scanner(System.in);
        //workRoom = new WorkRoom("Alex's workroom");
        saveAccount = true;
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runSDMonkey();
    }

    // MODIFIES: this
    // EFFECTS: processes user input; get users name and email
    private void runSDMonkey() {
        boolean keepGoing = true;
        String command;
        //Scanner myInput = new Scanner(System.in);
        //System.out.println("Plea");
        //name = myInput.nextLine();
        name = "Alex Lee";
        //System.out.println("Enter your Email address");
        //email = myInput.nextLine();
        email = "Alex.lee@gmail.com";
        //if ()
        //System.out.println("successfully signed up");
        //if ()
        //name = myInput.nextLine();

        loadWorkRoom();
        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                handlePromptForSaveOrNotSave();
                if (saveAccount) {
                    saveAccount();
                }
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }
        System.out.println("\nGoodbye!");
    }

    // MODIFIES: saveAccount
    // EFFECTS: set saveAccount to false if user input n, otherwise true
    public void handlePromptForSaveOrNotSave() {
        Scanner myInput = new Scanner(System.in);
        String response;
        System.out.println("would you like to save the changes you made? (y/n)");
        response = myInput.nextLine();
        if (response.equals("y")) {
            saveAccount = true;
        } else if (response.equals("n")) {
            saveAccount = false;
        } else {
            System.out.println("wrong input, saving anyways lol");
        }
    }


    // EFFECTS: saves the workroom to file
    private void saveAccount() {
        try {
            jsonWriter.open();
            jsonWriter.write(account1);
            jsonWriter.close();
            System.out.println("Saved " + account1.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        switch (command) {
            case "1":
                viewWatchlist();
                break;
            case "2":
                doListNFT();
                break;
            case "3":
                doMakePurchase();
                break;
            case "4":
                showWallet();
            default:
                System.out.println("Selection not valid...");
                break;
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes accounts, makes 2 NFT objects and adds them to the collection
    private void init() {
        NFT monkey1 = new NFT("monkey 1", 500, "James");
        NFT monkey2 = new NFT("monkey 2", 200, "Dave");
        List<NFT> listOfNFTs;
        listOfNFTs = account1.getWallet().getOwnedNFT();
        getAllNftsTitleListVersion(listOfNFTs);

        if (getAllNftsTitleListVersion(listOfNFTs).contains("monkey 1")) {
            monkey1.setOwner(account1.getName());
            //account1.
        }

        if (getAllNftsTitleListVersion(listOfNFTs).contains("monkey 2")) {
            monkey2.setOwner(account1.getName());
        }
        collection.addNFT(monkey1);
        collection.addNFT(monkey2);
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("-------SAD MONKEY-------");
        for (NFT nft : collection.getAllNFTs()
        ) {
            System.out.println(nft.getTitle() + "| price : USDT" + nft.getPrice() + "| Artist : " + nft.getOwner());
        }
        System.out.println("\nSelect from:");
        System.out.println("\t1 -> Go to watchlist");
        System.out.println("\t2 ->  List your NFT");
        System.out.println("\t3 ->  Purchase a NFT");
        System.out.println("\t4 ->  view your wallet");
        System.out.println("\tq ->  save and quit");
    }


    // MODIFIES: this
    // EFFECTS: Add an NFT object to the watchlist array if it exits
    private void doAddToWatchlist() {
        NFT nft;
        Scanner myInput = new Scanner(System.in);
        List<String> titles = new ArrayList<>();
        for (NFT eachNft : collection.getAllNFTs()) {
            titles.add(eachNft.getTitle());
        }
        System.out.println("Enter the title of the NFT");
        String nftTitle = myInput.nextLine();

        if (!titles.contains(nftTitle)) {
            System.out.println("NFT does not exist!");
        } else {
            nft = collection.getNftByTitle(nftTitle);
            if (!getAllNftsTitleListVersionForWatchlist(account1.getWatchList()).contains(nftTitle)) {
                account1.addToWatchlist(nft);
                System.out.println("added " + nft.getTitle() + " To your watchlist");

            } else {
                System.out.println(nft.getTitle() + " is already in your watchlist");
            }
        }
        viewWatchlist();
    }

    // EFFECTS: Display the NFTs in the watchlist, if not empty
    public void viewWatchlist() {
        Scanner myInput = new Scanner(System.in);
        System.out.println("-----Watchlist-------");
        if (account1.getWatchList().isEmpty()) {
            System.out.println("Your watchlist is empty");
        } else {
            for (NFT eachNFT : account1.getWatchList()) {
                System.out.println(eachNFT.getTitle() + " | price : USDT" + eachNFT.getPrice() + " | Artist : "
                        + eachNFT.getOwner());

            }
        }
        showMenu();
        String selected = myInput.nextLine();
        switch (selected) {
            case "1":
                doAddToWatchlist();
                break;
            case "2":
                break;
            default:
                System.out.println("Wrong Input! Please try again!");
                viewWatchlist();
                break;
        }
    }

    // EFFECTS: display the menu for the viewWatchlist method
    public void showMenu() {
        System.out.println("\n Select from:");
        System.out.println("\t1 -> Watchlist an NFT");
        System.out.println("\t2 -> Return to menu!");

    }

    // EFFECTS: return a list of string. containing all the NFT titles in the clollection
    public List<String> getAllNftsTitle(NFT listOfNFts) {
        for (NFT nft : listOfNFts.getAllNFTs()) {
            if (!titleOfNFTs.contains(nft.getTitle())) {
                titleOfNFTs.add(nft.getTitle());
            }
        }
        return titleOfNFTs;
    }

    // EFFECTS: return a list of string. containing all the NFT titles in the clollection
    public List<String> getAllNftsTitleListVersion(List<NFT> listOfNFts) {
        for (NFT nft : listOfNFts) {
            if (!titleOfNFTs.contains(nft.getTitle())) {
                titleOfNFTs.add(nft.getTitle());
            }
        }
        return titleOfNFTs;
    }

    // EFFECTS: return a list of string. containing all the NFT titles in the clollection
    public List<String> getAllNftsTitleListVersionForWatchlist(List<NFT> listOfNFts) {
        List<String> listOfTitlesOfNFTs = new ArrayList<>();
        for (NFT nft : listOfNFts) {
            {
                listOfTitlesOfNFTs.add(nft.getTitle());
            }
        }
        return listOfTitlesOfNFTs;
    }

    // MODIFIES: this
    // EFFECTS: Add an NFT object to the existing listings on the marketplace if the user has enough balance,
    //          and the inputs are valid
    public void doListNFT() {
        int newNftPrice;
        Scanner myInput = new Scanner(System.in);
        System.out.println("Enter the title for the new NFT");
        String newNftTitle = myInput.nextLine();
        if (!getAllNftsTitle(collection).contains(newNftTitle)) {
            System.out.println("Enter the price");
            if (myInput.hasNextInt()) {
                newNftPrice = myInput.nextInt();
                if (newNftPrice > 0) {
                    String newNftOwner = account1.getName();
                    NFT newNftObject = new NFT(newNftTitle, newNftPrice, newNftOwner);
                    collection.addNFT(newNftObject);
                    account1.getWallet().getOwnedNFT().add(newNftObject);
                    System.out.println("successfully added " + newNftTitle + " for a price of " + newNftPrice);
                } else {
                    handleErrorForDoListNFT("the price has to positive! \n -----------");
                }
            } else {
                handleErrorForDoListNFT("please input a positive integer for price! \n -----------");
            }
        } else {
            handleErrorForDoListNFT("an NFT with that title already exists \n -----------");
        }
    }


    public void handleErrorForDoListNFT(String msg) {
        System.out.println(msg);
        doListNFT();
    }


    // MODIFIES: this
    // EFFECTS: make a purchase, change the owner of the nft if NFT exists
    //          and if the user is not already the owner
    public void doMakePurchase() {
        List<String> titles = new ArrayList<>();
        Scanner myInput = new Scanner(System.in);
        System.out.println("Enter the title of the NFT: ");
        String title = myInput.nextLine();
        for (NFT nft : collection.getAllNFTs()) {
            titles.add(nft.getTitle());
        }
        if (titles.contains(title)) {
            String name = account1.getName();
            NFT nft = collection.getNftByTitle(title);
            if (nft.getOwner().equals(name)) {
                System.out.println("You already own this NFT");
            } else if (nft.getPrice() > account1.getWallet().getBalance()) {
                notEnoughMoneyError();
            } else {
                account1.getWallet().makePurchase(nft, name);
                System.out.println("purchased " + nft.getTitle());
            }
        } else {
            System.out.println(title + " Does not exist!");
            doMakePurchase();
        }
    }

    // EFFECTS: Error handling for the case that the user doesn't have enough money when Making a purchase
    public void notEnoughMoneyError() {
        System.out.println("Not enough money in your wallet, you need to deposit first!");
        showWallet();
    }


    // EFFECTS: display users wallet including their balance, and an option to deposit more assets
    public void showWallet() {
        double currentBalance = account1.getWallet().getBalance();
        System.out.println("--------SD Monkey Wallet------" + "\n" + "Name: " + account1.getName() + "\n" + "Email: "
                + account1.getEmail());
        //System.out.println("Email: " + account1.getEmail());
        System.out.println("--------------------------" + "\n Your Current Balance: USDT" + currentBalance
                + "\n-------------------------- \n Your NFTs: ");
        if (account1.getWallet().getOwnedNFT().isEmpty()) {
            System.out.println("No NFTs to display");
        } else {
            for (NFT nft : account1.getWallet().getOwnedNFT()) {
                System.out.println(nft.getTitle());
            }
        }
        displayMenuForShowWalletMethod();

    }

    // EFFECTS: handle user input and display the menu for showWallet method
    public void displayMenuForShowWalletMethod() {
        Scanner myInput = new Scanner(System.in);
        System.out.println("--------------------------");
        System.out.println("\n Select from:");
        System.out.println("\t1 -> Deposit");
        System.out.println("\t2 -> Return to menu!");
        String selected = myInput.nextLine();
        switch (selected) {
            case "1":
                doDeposit();
                break;
            case "2":
                break;
            default:
                System.out.println("Wrong Input! Please try again!");
                showWallet();
                break;
        }
    }


    // MODIFIES: this
    // EFFECTS: increase users balance
    public void doDeposit() {
        Scanner myInput = new Scanner(System.in);
        System.out.println("Please enter the wallet address");
        String walletAddress = myInput.next();
        System.out.println("Please enter the amount you'd like to deposit");
        if (myInput.hasNextInt()) {
            double amount = myInput.nextInt();
            if (amount > 0) {
                account1.getWallet().deposit(walletAddress, amount);
                System.out.println("successfully deposited USDT" + amount);
                showWallet();
            } else {
                System.out.println("amount has to be positive!");
                doDeposit();
            }

        } else {
            System.out.println("amount has to be a double");
            doDeposit();
        }
    }


    // MODIFIES: this
    // EFFECTS: loads workroom from file
    private void loadWorkRoom() {
        try {
            account1 = jsonReader.read();
            for (NFT nft : JsonReader.getNfts()) {
                if (!nft.getTitle().equals("monkey 1") && !nft.getTitle().equals("monkey 2")) {
                    collection.addNFT(nft);
                }
            }
            System.out.println("Welcome back " +  account1.getName() + ", Your data has been loaded from "
                    + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

}
