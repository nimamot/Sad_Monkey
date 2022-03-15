package ui;

import model.Account;
import model.NFT;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;


public class SDmGuiApp {
    JFrame frame;
    JPanel mainPanel;
    Border border;



    // EFFECTS: runs the SDMonkey application
    public SDmGuiApp() {
        frame = new JFrame(); // create a frame
        mainPanel = new JPanel();
        border = BorderFactory.createLineBorder(Color.green, 3);
        makeGame();
    }

    public void makeGame() {
        setUp();
        //// make a new frame for account page
        //   when the button is clicked the new frame should come up
        JFrame accountFrame = new JFrame(); // create a frame


        JButton accountButton = new JButton("Account");
        accountButton.setPreferredSize(new Dimension(100, 40));


        frame.setTitle("Sad Monkey");
        mainPanel.setBackground(new Color(123, 40, 250));
        frame.add(mainPanel); // a mainPanel
        frame.add(accountButton, BorderLayout.SOUTH);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(380, 680);
        frame.setVisible(true); // make frame visible

        frame.getContentPane().setBackground(new Color(123, 40, 250));

    }


    // this could be its own function
    // It adds the NFTs in the collection to the gui and also gives them a picture
    public void setUp() {
        SDMonkeyApp smd = new SDMonkeyApp();
        Account ac = smd.getAccount();
        NFT collection = smd.getCollection();

        for (NFT nft : collection.getAllNFTs()) {
            ImageIcon imageIcon2 = new ImageIcon("src/main/ui/pics/logo1.png"); // load the image to a imageIcon
            Image image2 = imageIcon2.getImage(); // transform it
            Image newimg2 = image2.getScaledInstance(170, 110, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
            imageIcon2 = new ImageIcon(newimg2);  // transform it back

            JLabel label2 = new JLabel("<html>Title: " + nft.getTitle() + "<br/> Price: " + nft.getPrice()
                    + "<br/> Owner: " + nft.getOwner() + " <br/>",
                    SwingConstants.CENTER);
            label2.setIcon(imageIcon2);
            label2.setHorizontalTextPosition(JLabel.CENTER);
            label2.setVerticalTextPosition(JLabel.BOTTOM);
            //label.setForeground(new Color(0x0));
            label2.setFont(new Font("MV Boli", Font.PLAIN, 12));
            label2.setIconTextGap(2);
            label2.setBorder(border);
            mainPanel.add(label2); // a mainPanel

        }
    }
}
