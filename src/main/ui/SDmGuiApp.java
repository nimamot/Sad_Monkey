package ui;

import model.Account;
import model.NFT;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;


public class SDmGuiApp {
    String sysLog;
    String sysLog2;
    String syslog3;
    String owner;
    double balance;
    JPanel mainPanel;
    Border border;
    Border border2;
    JFrame frame;
    JFrame accountFrame;
    JFrame watchlistFrame;
    JFrame ownedNFTsFrame;
    JFrame watchListAnNFtFrame;
    JFrame listNFtFrame;
    JFrame purchaseNFtFrame;
    JScrollPane scrPane;



    List<String> titlesList = new ArrayList<>();


    SDMonkeyApp smd = new SDMonkeyApp();
    Account ac = smd.getAccount();
    NFT collection = smd.getCollection();
    List<NFT> watchlist = ac.getWatchList();


    // EFFECTS: runs the SDmGuiApp application
    public SDmGuiApp() {
        frame = new JFrame();
        mainPanel = new JPanel();
        border = BorderFactory.createLineBorder(Color.green, 3);
        border2 = BorderFactory.createLineBorder(Color.red, 3);
        accountFrame = new JFrame();
        watchlistFrame = new JFrame();
        ownedNFTsFrame = new JFrame();
        watchListAnNFtFrame = new JFrame();
        listNFtFrame = new JFrame();
        purchaseNFtFrame = new JFrame();

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        scrPane = new JScrollPane(mainPanel);

        makeGame();
    }

    // EFFECTS: make the first frame and show the NFTs on the marketplace
    public void makeGame() {
        mainPanel.removeAll();
        setUp();

        scrPane.setHorizontalScrollBar(null);
        scrPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        JButton accountButton = new JButton("Account");
        accountButton.addActionListener(e -> accountView());
        accountButton.setPreferredSize(new Dimension(100, 40));

        frame.setTitle("Sad Monkey");
        mainPanel.setBackground(getColor());

        frame.add(scrPane); // a mainPanel
        frame.add(accountButton, BorderLayout.SOUTH);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(380, 680);
        frame.setVisible(true);
        frame.getContentPane().setBackground(getColor());

    }

    // EFFECTS: return the background color
    private Color getColor() {
        return new Color(123, 40, 250);
    }


    // EFFECTS: It adds the NFTs in the collection to the gui and also gives them a picture
    public void setUp() {
        forLoopForViewAccount(mainPanel, collection.getAllNFTs());
    }


    //MODIFIES: this
    // EFFECTS: set the profile picture
    public void setProfilePic(JPanel panel) {
        ImageIcon imageIcon2 = new ImageIcon("src/main/ui/pics/profile.png/profile.png");
        Image image2 = imageIcon2.getImage(); // transform it
        Image newimg2 = image2.getScaledInstance(130, 130, java.awt.Image.SCALE_SMOOTH);
        imageIcon2 = new ImageIcon(newimg2);  // transform it back
        JLabel label2 = new JLabel();
        label2.setIcon(imageIcon2);
        label2.setIconTextGap(2);
        label2.setBorder(BorderFactory.createEmptyBorder(25, 120, 25, 30));
        label2.setHorizontalAlignment(JLabel.CENTER);
        panel.add(label2);
    }


    // EFFECTS: make a frame for account view, it displays buttons for different actions
    //          labels for name, email and balance
    public void accountView() {
        accountFrame.setTitle("Sad Monkey/Account");
        JLabel balanceLabel = new JLabel();
        JPanel accountViewPannle = new JPanel();
        accountViewPannle.setLayout(new BoxLayout(accountViewPannle, BoxLayout.Y_AXIS));

        setProfilePic(accountViewPannle);
        JLabel nameLabel = new JLabel();
        JLabel emailLabel = new JLabel();
        balance = ac.getWallet().getBalance();
        setLables(balanceLabel, nameLabel, emailLabel);

        JButton goBackButton = getjButtonForAcountViewGoingBackButton();
        JButton viewWatchlistButton = getjButtonForViewAccount();
        JButton viewOwnedNFTs = getjButtonForAccountViewForViewOwnedNFTs();
        JButton saveAndExit = getjButtonForAccountViewForSaveAndLogOut();

        setUpFrameForAccountView(balanceLabel, accountViewPannle, nameLabel, emailLabel, goBackButton,
                viewWatchlistButton, viewOwnedNFTs, saveAndExit);
    }


    // EFFECTS: return a JButton
    private JButton getjButtonForAccountViewForViewOwnedNFTs() {
        // Button for ownedNFts
        JButton viewOwnedNFTs = new JButton("Owned NFTs");
        viewOwnedNFTs.addActionListener(e -> viewOwnedNFTs());
        return viewOwnedNFTs;
    }


    // EFFECTS: return a JButton
    private JButton getjButtonForAccountViewForSaveAndLogOut() {
        // Button for Save and Quit
        JButton saveAndExit = new JButton("Save and Exit");
        saveAndExit.addActionListener(e -> smd.saveAccount());

        return saveAndExit;
    }


    // EFFECTS: return a JButton
    private JButton getjButtonForAcountViewGoingBackButton() {
        JButton goBackButton = new JButton("Go back");
        goBackButton.addActionListener(e -> {
            goBackButtonForAccountView(accountFrame, false, watchlistFrame, ownedNFTsFrame, listNFtFrame,
                    purchaseNFtFrame, watchListAnNFtFrame);
            makeGame();
        });
        return goBackButton;
    }


    // EFFECTS: return a JButton
    private JButton getjButtonForViewAccount() {
        // Button for watchlist
        JButton viewWatchlistButton = new JButton("Watchlist");
        viewWatchlistButton.addActionListener(e -> viewWatchlist(true));
        return viewWatchlistButton;
    }


    // EFFECTS: set the visibility of the frames
    private void goBackButtonForAccountView(JFrame accountFrame, boolean b, JFrame watchlistFrame,
                                            JFrame ownedNFTsFrame, JFrame listNFtFrame, JFrame purchaseNFtFrame,
                                            JFrame watchListAnNFtFrame) {
        accountFrame.setVisible(b);
        watchlistFrame.setVisible(false);
        ownedNFTsFrame.setVisible(false);
        listNFtFrame.setVisible(false);
        purchaseNFtFrame.setVisible(false);
        watchListAnNFtFrame.setVisible(false);
    }


    // EFFECTS: set up the labels
    private void setLables(JLabel balanceLabel, JLabel nameLabel, JLabel emailLabel) {
        balanceLabel.setText("Balance: " + balance);

        nameLabel.setText("Name: " + ac.getName());

        emailLabel.setText("Email: " + ac.getEmail());

        emailLabel.setBorder(BorderFactory.createEmptyBorder(25, 15, 25, 30));
        nameLabel.setBorder(BorderFactory.createEmptyBorder(25, 15, 25, 30));
        balanceLabel.setBorder(BorderFactory.createEmptyBorder(25, 15, 25, 30));
    }


    // EFFECTS: set up the frame settings for accountFrame
    public void setUpFrameForAccountView(JLabel balanceLabel, JPanel accountViewPannle, JLabel nameLabel,
                                         JLabel emailLabel, JButton goBackButton, JButton viewWatchlistButton,
                                         JButton viewOwnedNFTs, JButton saveAndExit) {

        nameLabel.setFont(new Font("MV Boli", Font.PLAIN, 20));
        nameLabel.setForeground(Color.white);
        emailLabel.setFont(new Font("MV Boli", Font.PLAIN, 20));
        emailLabel.setForeground(Color.white);
        balanceLabel.setFont(new Font("MV Boli", Font.PLAIN, 20));
        balanceLabel.setForeground(Color.white);

        makeFrame(accountFrame);
        accountViewPannle.setBackground(getColor());

        accountViewPannle.add(nameLabel);
        accountViewPannle.add(emailLabel);
        accountViewPannle.add(balanceLabel);
        accountViewPannle.add(viewWatchlistButton);
        accountViewPannle.add(viewOwnedNFTs);
        accountViewPannle.add(saveAndExit);

        accountViewPannle.add(goBackButton, BorderLayout.SOUTH);


        accountFrame.getContentPane().setBackground(getColor());

        accountFrame.add(accountViewPannle);
        accountFrame.add(goBackButton, BorderLayout.SOUTH);

        setVisibilityForAccountView();
    }


    //EFFECTS: sets Visibility For AccountView
    private void setVisibilityForAccountView() {
        goBackButtonForAccountView(accountFrame, true, frame, watchlistFrame, ownedNFTsFrame, listNFtFrame,
                purchaseNFtFrame);
        watchListAnNFtFrame.setVisible(false);
    }


    // EFFECTS: View users watchlist
    public void viewWatchlist(boolean visibility) {
        watchlistFrame.setTitle("Sad Monkey/Account/Watchlist");

        JPanel watchlistedNFTsPanel = new JPanel();
        accountFrame.setVisible(false);
        frame.setVisible(false);

        JLabel sysLogLabel = new JLabel();
        JTextField nftTitleTextField = makeJtextField();

        JButton addButton = getjButton(watchlistedNFTsPanel, sysLogLabel, nftTitleTextField);

        watchlistedNFTsPanel.add(nftTitleTextField);
        watchlistedNFTsPanel.add(addButton);
        watchlistedNFTsPanel.add(sysLogLabel);

        // Button for going back
        JButton goBackButton = new JButton("Go back");
        goBackButton.addActionListener(e -> {
            accountFrame.setVisible(true);
            frame.setVisible(false);
            watchlistFrame.setVisible(false);
        });

        forLoopForViewWatchlist(watchlistedNFTsPanel, watchlist);
        setUpForViewWatchlist(watchlistedNFTsPanel, goBackButton, watchlistFrame, visibility);
        watchlistedNFTsPanel.setBackground(getColor());
    }


    // EFFECTS: make a new JButton for ViewWatchlist.AddNFTtoWatchlis
    private JButton getjButton(JPanel watchlistedNFTsPanel, JLabel sysLogLabel, JTextField nftTitleTextField) {
        JButton addButton = new JButton("Add To Watchlist");
        addButton.addActionListener(e -> {
            String nftTitle = nftTitleTextField.getText();
            boolean newNFT = doAddToWatchlist(nftTitle);
            sysLogLabel.setText(sysLog);
            if (newNFT) {
                helperForAddButtonInViewWatchlist(watchlistedNFTsPanel, nftTitle);
            }
        });
        return addButton;
    }


    // REQUIRES: nftPriceTextField contains only integer
    // EFFECTS: make a new JButton for ViewWatchlist.AddNFTtoWatchlis
    private JButton getjButtonForMinting(JPanel panel, JLabel sysLogForThis, JTextField nftTitleTextField,
                                         JTextField nftPriceTextField) {
        JButton button = new JButton("   Mint  ");
        button.addActionListener(e -> {
            String nftTitle = nftTitleTextField.getText();
            int nftPrice = parseInt(nftPriceTextField.getText());
            boolean newNFT = doListNFT(nftTitle, nftPrice);
            sysLogForThis.setText(syslog3);
            if (newNFT) {
                helperForAddButtonInViewWatchlist(panel, nftTitle);
            }
        });
        return button;
    }


    // EFFECTS: preforms the for loop for ViewWatchlist
    private void forLoopForViewWatchlist(JPanel watchlistedNFTsPanel, List<NFT> watchlist) {
        for (NFT nft : watchlist) {
            ImageIcon imageIcon2 = new ImageIcon("src/main/ui/pics/logo"
                    + (nft.getTitle().substring(nft.getTitle().length() - 2)) + ".png");
            Image image2 = imageIcon2.getImage(); // transform it
            Image newimg2 = image2.getScaledInstance(170, 110, Image.SCALE_SMOOTH); // scale it the smooth way
            imageIcon2 = new ImageIcon(newimg2);  // transform it back

            owner = nft.getOwner();
            JLabel label2 = new JLabel("<html>Title: " + nft.getTitle() + "<br/> Price: " + nft.getPrice()
                    + "<br/> Owner: " + owner + " <br/>",
                    SwingConstants.CENTER);
            label2.setIcon(imageIcon2);
            label2.setHorizontalTextPosition(JLabel.CENTER);
            label2.setVerticalTextPosition(JLabel.BOTTOM);
            //label.setForeground(new Color(0x0));
            label2.setFont(new Font("MV Boli", Font.PLAIN, 12));
            label2.setForeground(Color.WHITE);
            label2.setIconTextGap(2);
            label2.setBorder(border);
            watchlistedNFTsPanel.add(label2);

        }
    }


    // MODIFIES: this
    // EFFECTS: preforms the for loop for ViewWatchlist
    private void forLoopForViewAccount(JPanel watchlistedNFTsPanel, List<NFT> watchlist) {
        for (NFT nft : watchlist) {
            ImageIcon imageIcon2 = new ImageIcon("src/main/ui/pics/logo"
                    + (nft.getTitle().substring(nft.getTitle().length() - 2)) + ".png");
            Image image2 = imageIcon2.getImage(); // transform it
            Image newimg2 = image2.getScaledInstance(170, 110, Image.SCALE_SMOOTH); // scale it the smooth way
            imageIcon2 = new ImageIcon(newimg2);  // transform it back

            owner = nft.getOwner();
            JLabel label2 = new JLabel("<html>Title: " + nft.getTitle() + "<br/> Price: " + nft.getPrice()
                    + "<br/> Owner: " + owner + " <br/>",
                    SwingConstants.CENTER);
            label2.setIcon(imageIcon2);
            label2.setHorizontalTextPosition(JLabel.CENTER);
            label2.setVerticalTextPosition(JLabel.BOTTOM);
            //label.setForeground(new Color(0x0));
            label2.setFont(new Font("MV Boli", Font.PLAIN, 12));
            label2.setForeground(Color.WHITE);
            label2.setIconTextGap(2);
            label2.setBorder(border);
            watchlistedNFTsPanel.add(label2);

        }
    }

    // EFFECTS: sets up the visibility and appearance of a given JPanel
    private void setUpForViewWatchlist(JPanel panel, JButton goBackButton, JFrame watchlistFrame,
                                       boolean b) {
        watchlistFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        watchlistFrame.setResizable(false);
        watchlistFrame.setSize(380, 680);
        watchlistFrame.getContentPane().setBackground(getColor());
        watchlistFrame.add(goBackButton, BorderLayout.SOUTH);
        watchlistFrame.add(panel);

        accountFrame.setVisible(false);
        frame.setVisible(false);
        watchlistFrame.setVisible(b);
    }


    // MODIFIES: this
    // EFFECTS: sets up the visibility, button positions and the panel settings for a given JFrame
    private void setUpForOwned(JPanel panel, JButton goBackButton, JFrame setFrame) {
        setFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setFrame.setResizable(false);
        setFrame.setSize(380, 680);
        setFrame.getContentPane().setBackground(getColor());
        setFrame.add(goBackButton, BorderLayout.SOUTH);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JScrollPane scrPane = new JScrollPane(panel);
        scrPane.setHorizontalScrollBar(null);
        scrPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        setFrame.add(scrPane);

        accountFrame.setVisible(false);
        frame.setVisible(false);
        setFrame.setVisible(false);
    }



    // MODIFIES: this
    // EFFECTS: adds an NFT to the given JPanel with its picture
    public void helperForAddButtonInViewWatchlist(JPanel panel, String nftTitle) {
        ImageIcon imageIcon2 = new ImageIcon("src/main/ui/pics/logo"
                + (nftTitle.substring(nftTitle.length() - 2)) + ".png");
        Image image2 = imageIcon2.getImage(); // transform it
        Image newimg2 = image2.getScaledInstance(170, 110, java.awt.Image.SCALE_SMOOTH);
        imageIcon2 = new ImageIcon(newimg2);  // transform it back
        NFT nft = collection.getNftByTitle(nftTitle);
        owner = nft.getOwner();
        JLabel label2 = new JLabel("<html>Title: " + nft.getTitle() + "<br/> Price: " + nft.getPrice()
                + "<br/> Owner: " + owner + " <br/>",
                SwingConstants.CENTER);
        label2.setIcon(imageIcon2);
        label2.setHorizontalTextPosition(JLabel.CENTER);
        label2.setVerticalTextPosition(JLabel.BOTTOM);
        label2.setFont(new Font("MV Boli", Font.PLAIN, 12));
        label2.setForeground(Color.WHITE);
        label2.setIconTextGap(2);
        label2.setBorder(border);
        panel.add(label2);
    }


    // EFFECTS: show all the NFTs under the current user's name
    public void viewOwnedNFTs() {
        ownedNFTsFrame.setTitle("Sad Monkey/Account/OwnedNFTs");

        JPanel ownedNFTs = new JPanel();

        JLabel sysLogLabel2 = new JLabel();
        JTextField nftTitleTextField = makeJtextField();

        JButton purchaseButton = getPurchaseButton(ownedNFTs, sysLogLabel2, nftTitleTextField);

        JLabel sysLogLabel3 = new JLabel();
        JTextField newNftTitle = makeJtextField();
        JTextField newNftPrice = makeJtextField();
        // Button for listYourNFtButton
        JButton listYourNFtButton = getjButtonForMinting(ownedNFTs, sysLogLabel2, newNftTitle, newNftPrice);


        ownedNFTs.add(nftTitleTextField);
        ownedNFTs.add(purchaseButton);
        ownedNFTs.add(sysLogLabel2);

        ownedNFTs.add(newNftTitle);
        ownedNFTs.add(newNftPrice);
        ownedNFTs.add(listYourNFtButton);
        ownedNFTs.add(sysLogLabel3);


        JButton goBackButton = getjButtonForViewOwnedNFTs();
        forLoopForViewOwnedNFts(ownedNFTs);


        setUpForOwned(ownedNFTs, goBackButton, ownedNFTsFrame);
        ownedNFTsFrame.setVisible(true);
    }


    // EFFECTS: return the JButton for making a purchase
    private JButton getPurchaseButton(JPanel ownedNFTs, JLabel sysLogLabel2, JTextField nftTitleTextField) {
        JButton purchaseButton = new JButton("  purchase!  ");

        purchaseButton.addActionListener(e -> {
            String nftTitle = nftTitleTextField.getText();
            boolean newNFT = doMakePurchase(nftTitle);
            sysLogLabel2.setText(sysLog2);
            if (newNFT) {
                helperForAddButtonInViewWatchlist(ownedNFTs, nftTitle);
            }
        });
        return purchaseButton;
    }

    // EFFECTS: return a JTextField
    private JTextField makeJtextField() {
        JTextField nftTitleTextField = new JTextField(30);
        nftTitleTextField.setColumns(10);
        nftTitleTextField.setBounds(50, 100, 200, 30);
        return nftTitleTextField;
    }

    // EFFECTS: return a JButton
    private JButton getjButtonForViewOwnedNFTs() {
        // Button for going back
        JButton goBackButton = new JButton("Go back");
        goBackButton.addActionListener(e -> {
            //accountFrame.setVisible(true);
            frame.setVisible(false);
            watchlistFrame.setVisible(false);
            ownedNFTsFrame.setVisible(false);
            accountFrame.getContentPane().removeAll();
            ownedNFTsFrame.getContentPane().removeAll();
            accountView();
        });
        return goBackButton;
    }

    // EFFECTS: display all the nfts owned by the current user
    private void forLoopForViewOwnedNFts(JPanel ownedNFTs) {
        ownedNFTs.setBackground(getColor());
        for (NFT nft : ac.getWallet().getOwnedNFT()) {
            ImageIcon imageIcon2 = new ImageIcon("src/main/ui/pics/logo"
                    + (nft.getTitle().substring(nft.getTitle().length() - 2)) + ".png");
            Image image2 = imageIcon2.getImage(); // transform it
            Image newimg2 = image2.getScaledInstance(170, 110, Image.SCALE_SMOOTH);
            imageIcon2 = new ImageIcon(newimg2);  // transform it back

            JLabel label2 = new JLabel("<html>Title: " + nft.getTitle() + "<br/> Price: " + nft.getPrice()
                    + "<br/> Owner: " + nft.getOwner() + " <br/>", SwingConstants.CENTER);
            //System.out.println(nft.getOwner());
            label2.setIcon(imageIcon2);
            label2.setHorizontalTextPosition(JLabel.CENTER);
            label2.setVerticalTextPosition(JLabel.BOTTOM);

            label2.setFont(new Font("MV Boli", Font.PLAIN, 12));
            label2.setForeground(Color.WHITE);
            label2.setIconTextGap(2);
            label2.setBorder(border);
            ownedNFTs.add(label2); // a mainPanel
        }
    }

    // MODIFIES: this
    // EFFECTS: Add an NFT object to the watchlist array if it exits
    //          set the sysLog, returns true if it added the NFT, false otherwise
    public boolean doAddToWatchlist(String nftTitle) {
        NFT nft;
        List<String> titles = new ArrayList<>();
        for (NFT eachNft : collection.getAllNFTs()) {
            titles.add(eachNft.getTitle());
        }

        if (!titles.contains(nftTitle)) {
            sysLog = nftTitle + " does not exist on this collection!";
            return false;

        } else {
            nft = collection.getNftByTitle(nftTitle);
            if (!getAllNftsTitleListVersionForWatchlist(watchlist).contains(nftTitle)) {
                watchlist.add(nft);
                sysLog = "added " + nft.getTitle() + " To your watchlist";
                return true;
            } else {
                sysLog = nft.getTitle() + " is already in your watchlist";
                return false;
            }
        }
    }

    // EFFECTS: return a list of string. containing all the NFT titles in the collection
    public List<String> getAllNftsTitleListVersionForWatchlist(List<NFT> listOfNFts) {
        List<String> listOfTitlesOfNFTs = new ArrayList<>();
        for (NFT nft : listOfNFts) {
            {
                listOfTitlesOfNFTs.add(nft.getTitle());
            }
        }
        return listOfTitlesOfNFTs;
    }


    // EFFECTS: Set some criteria for the panel
    public void makeFrame(JFrame frame) {

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(380, 680);
        frame.setVisible(true);
        frame.getContentPane().setBackground(getColor());

    }

    // EFFECTS: check if all the criteria are met for a purchase
    public boolean doMakePurchase(String title) {
        List<String> titles = new ArrayList<>();

        for (NFT nft : collection.getAllNFTs()) {
            titles.add(nft.getTitle());
        }
        if (titles.contains(title)) {
            String name = ac.getName();
            NFT nft = collection.getNftByTitle(title);
            if (nft.getOwner().equals(name)) {
                sysLog2 = nft.getTitle() + " is already in your SDM Wallet";
                return false;
            } else if (nft.getPrice() > ac.getWallet().getBalance()) {
                sysLog2 = "Not enough money in your wallet, you need to deposit first!";
                return false;
            } else {
                clearPurchase(name, nft);
                return true;
            }
        } else {
            sysLog2 = title + " Does not exist on SDM collection!";
            return false;
        }
    }


    // MODIFIES: this, ac
    // EFFECTS: make a purchase
    private void clearPurchase(String name, NFT nft) {
        ac.getWallet().makePurchase(nft, name);
        //ac.getWallet().addNFT(nft);
        sysLog2 = "purchased " + nft.getTitle() + " and it is now in your wallet";
        owner = ac.getName();
        balance = ac.getWallet().getBalance();

    }



    // MODIFIES: this
    // EFFECTS: Add an NFT object to the existing listings on the marketplace if the user has enough balance,
    //          and the inputs are valid
    public boolean doListNFT(String newNftTitle, int newNftPrice) {
        if (!getAllNftsTitle(collection).contains(newNftTitle)) {
            if (newNftPrice > 0) {
                String newNftOwner = ac.getName();
                NFT newNftObject = new NFT(newNftTitle, newNftPrice, newNftOwner);
                collection.addNFT(newNftObject);
                ac.getWallet().getOwnedNFT().add(newNftObject);
                syslog3 = "successfully added " + newNftTitle + " for a price of " + newNftPrice;
                //System.out.println("successfully added " + newNftTitle + " for a price of " + newNftPrice);
                return true;

            } else {
                syslog3 = "the price has to be a positive number!";
                return false;
            }
        } else {
            syslog3 = "an NFT with that title already exists";
            return false;
        }
    }

    // EFFECTS: return a list of string. containing all the NFT titles in the collection
    public List<String> getAllNftsTitle(NFT listOfNFts) {
        for (NFT nft : listOfNFts.getAllNFTs()) {
            titlesList.add(nft.getTitle());
        }
        return titlesList;
    }
}
