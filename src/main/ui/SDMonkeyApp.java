package ui;

import java.util.Scanner;

public class SDMonkeyApp {

    private Scanner input;

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tw -> wallet"); // -> Transfer
        System.out.println("\tn -> NFT Section"); // NFT Section -> upload/but/add to watchlist
        System.out.println("\tt -> !!!");
        System.out.println("\tq -> quit");
    }
}
