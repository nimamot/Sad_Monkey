package ui;

import javax.swing.*;
import java.awt.*;


public class Main {
    public static void main(String[] args) {



        //FrameTemp frame = new FrameTemp();

        JFrame frame = new JFrame(); // create a frame

        ImageIcon image = new ImageIcon("src/main/ui/pics/logo1.png"); //"project_b8m2i/src/main/ui/logo1.png");


        JLabel label = new JLabel();
        label.setText("SAD MONKEY");
        label.setIcon(image);
        frame.setTitle("Sad Monkey");
        frame.add(label);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(380, 680);
        frame.setVisible(true); // make frame visible

        //ImageIcon image = new ImageIcon("ui/pics/logo.webp");
        //this.setIconImage(image.getImage());
        frame.getContentPane().setBackground(new Color(123, 40, 250));





        //new SDMonkeyApp();

    }
}
