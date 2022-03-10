package ui;

import javax.swing.*;
import java.awt.*;

public class FrameTemp extends JFrame {

    public FrameTemp() {
        //JFrame frame = new JFrame(); // create a frame
        this.setTitle("Sad Monkey");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(380, 680);
        this.setVisible(true); // make frame visible

        //ImageIcon image = new ImageIcon("ui/pics/logo.webp");
        //this.setIconImage(image.getImage());
        this.getContentPane().setBackground(new Color(123, 40, 250));
    }

}
