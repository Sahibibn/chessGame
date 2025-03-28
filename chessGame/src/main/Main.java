
package main;

import javax.swing.*;
import java.awt.*;

//Hii everyone Today I am Presenting a Chess Game . lets Get Started
//so this is how it works bye bye.

public class Main {
    public static void main(String[] args) {

        JFrame frame = new JFrame("My game");
        frame.getContentPane().setBackground(Color.white);
        frame.setLayout(new GridBagLayout());
        frame.setLayout(new GridBagLayout());
        frame.setMaximumSize(new Dimension(1000,1000));

        frame.setLocationRelativeTo(null);

        Board board = new Board();
        frame.add(board);

        frame.setVisible(true);
    }
}


