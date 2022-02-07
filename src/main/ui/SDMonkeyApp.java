package ui;

import model.Account;
import model.NFT;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SDMonkeyApp {
    private NFT collection;
    private Account account1;
    private NFT monkey1;
    private NFT monkey2;

    private Scanner input;

    // EFFECTS: runs the SDMonkey application
    public SDMonkeyApp() {
        runSDMonkey();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runSDMonkey() {
        boolean keepGoing = true;
        String command = null;

        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nGoodbye!");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        switch (command) {
            case "a1":
                doAddToWatchlist(monkey1);
                break;
            case "a2":
                doAddToWatchlist(monkey2);
                break;
            case "n":
                doListNFT();
                break;
            case "p":
                doMakePurchase();
                break;
            default:
                System.out.println("Selection not valid...");
                break;
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes accounts
    private void init() {
        account1 = new Account("Nima", "nima.motieifard@gmail.com");
        collection = new NFT();
        monkey1 = new NFT("monkey 1", 500, "Nima");
        monkey2 = new NFT("monkey 2", 200, "Dave");
        collection.addNFT(monkey1);
        collection.addNFT(monkey2);
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("-------SAD MONKEY-------");
        for (NFT nft: collection.getAllNFTs()
        ) {
            System.out.println(nft.getTitle() + "| price : $" + nft.getPrice() + "| Artist : " + nft.getOwner());
        }
        System.out.println("\nSelect from:");
        System.out.println("\ta1 -> add Monkey 1 to watchlist");
        System.out.println("\ta2 -> add Monkey 2 to watchlist ");
        System.out.println("\tn ->  List your NFT");
        System.out.println("\tp ->  Purchase a NFT");
        System.out.println("\tq ->  quit");

        // System.out.println("\nSelect from:");
        //System.out.println("\tw -> wallet"); // -> Transfer
       // System.out.println("\tn -> NFT Section"); // NFT Section -> upload/but/add to watchlist
       // System.out.println("\tq -> quit");
    }


    private void doAddToWatchlist(NFT nft) {
        if (!account1.getWatchList().contains(nft)) {
            account1.addToWatchlist(nft);
        } else {
            System.out.println(nft.getTitle() + " is already in your watchlist");
        }
        System.out.println("-----------------");
        System.out.println("Your watchlist: ");
        for (NFT eachNFT: account1.getWatchList()) {
            System.out.println(eachNFT.getTitle() + " | price : $" + eachNFT.getPrice() + " | Artist : "
                    + eachNFT.getOwner());
        }
        System.out.println();

    }

    public void doListNFT() {
        Scanner myInput = new Scanner(System.in);

        System.out.println("Enter the title");
        String newNftTitle = myInput.nextLine();
        System.out.println("Enter the price");
        int newNftPrice = myInput.nextInt();
        System.out.println("Enter your name");
        String newNftOwner = myInput.next();

        NFT newNftObject = new NFT(newNftTitle, newNftPrice, newNftOwner);
        collection.addNFT(newNftObject);
        System.out.println("successfully added " + newNftTitle + " for a price of " + newNftPrice);

        //System.out.println(" " + newNftTitle);  // Output user input
    }

    public void doMakePurchase() {
        List<String> titles = new ArrayList<String>();
        Scanner myInput = new Scanner(System.in);
        System.out.println("Enter the title of the desired NFT:");
        String title = myInput.nextLine();
        for (NFT nft: collection.getAllNFTs()
             ) {
            titles.add(nft.getTitle());
        }
        if (titles.contains(title)) {
            System.out.println("Enter your name:");
            String name = myInput.nextLine();
            NFT nft = collection.getNftByTitle(title);
            account1.getWallet().makePurchase(nft, name);
        } else {
            System.out.println(title + " DNE!");
        }


    }


}
