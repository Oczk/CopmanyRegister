package GUI;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame{

    public Frame(){
        super("Rejestracja firmy");

        Container contentPane = new ContentPane();
        add(contentPane);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        pack();
        setVisible(true);
    }
}
