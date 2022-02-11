package ui;

import model.Account;
import model.NFT;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
// Credits: TellerApp https://github.students.cs.ubc.ca/CPSC210/TellerApp

public class SDMonkeyApp {
    private NFT collection;
    private Account account1;

    private Scanner input;

    // EFFECTS: runs the SDMonkey application
    public SDMonkeyApp() {
        runSDMonkey();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runSDMonkey() {
        boolean keepGoing = true;
        String command;
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
        account1 = new Account("Nima", "nima.motieifard@gmail.com");
        collection = new NFT();
        NFT monkey1 = new NFT("monkey 1", 500, "James");
        NFT monkey2 = new NFT("monkey 2", 200, "Dave");
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
        //System.out.println("\t2 -> add Monkey 2 to watchlist ");
        System.out.println("\t2 ->  List your NFT");
        System.out.println("\t3 ->  Purchase a NFT");
        System.out.println("\t4 ->  view your wallet");
        System.out.println("\tq ->  quit");
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
            if (!account1.getWatchList().contains(nft)) {
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

    // MODIFIES: this
    // EFFECTS: Add an NFT object to the existing listings on the marketplace if the user has enough balance,
    //          and the inputs are valid
    public void doListNFT() {
        int newNftPrice;
        Scanner myInput = new Scanner(System.in);
        System.out.println("Enter the title for the new NFT");
        String newNftTitle = myInput.nextLine();
        System.out.println("Enter the price");
        if (myInput.hasNextInt()) {
            newNftPrice = myInput.nextInt();
            if (newNftPrice > 0) {
                //System.out.println("Enter your name");
                String newNftOwner = account1.getName();
                NFT newNftObject = new NFT(newNftTitle, newNftPrice, newNftOwner);
                collection.addNFT(newNftObject);
                account1.getWallet().getOwnedNFT().add(newNftObject);
                System.out.println("successfully added " + newNftTitle + " for a price of " + newNftPrice);
            } else {
                System.out.println("the price has to positive!");
                System.out.println("-----------");
                doListNFT();
            }
        } else {
            System.out.println("please input a positive integer for price!");
            System.out.println("-----------");
            doListNFT();
        }
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
                doDeposit(); // wishlists
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
}
