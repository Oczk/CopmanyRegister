package GUI;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame{

    public Frame(){
        super("Rejestracja firmy");

        JPanel buttonPanel = new ButtonPanel();
        add(buttonPanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        pack();
        setVisible(true);
    }
}
