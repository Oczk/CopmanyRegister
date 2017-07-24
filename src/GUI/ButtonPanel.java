package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonPanel extends JPanel implements ActionListener {

    private static final int HEIGHT = 64;
    private static final int WIDTH = 512;

    private JButton checkButton;
    private JButton saveButton;

    private JTextField regonField;
    private JTextField nameField;
    private JTextField cityField;
    private JTextField zipCodeField;
    private JTextField pkdField;

    private JLabel regon;
    private JLabel name;
    private JLabel city;
    private JLabel zipCode;
    private JLabel pkd;

    ButtonPanel() {
        //set buttons
        checkButton = new JButton("Sprawdz");
        saveButton = new JButton("Zapisz");

        //set text fields
        regonField = new JTextField();
        nameField = new JTextField();
        cityField = new JTextField();
        zipCodeField = new JTextField();
        pkdField = new JTextField();

        //set labels
        regon = new JLabel("Numer REGON:");
        name = new JLabel("Skrocona nazwa: ");
        city = new JLabel("Miejscowosc: ");
        zipCode = new JLabel("Kod pocztowy: ");
        pkd = new JLabel("Symbol PKD: ");

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == checkButton) {
            //todo with check button

        } else if (source == saveButton) {
            //todo with save button
        } else if (source == regonField) {

        } else if (source == nameField) {

        } else if (source == cityField){

        } else if (source == zipCodeField){

        } else if (source == pkdField){

        }
    }
}
