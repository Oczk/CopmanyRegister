package GUI;

import company.CompanyData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonPanel extends JPanel implements ActionListener {

    private static final int HEIGHT = 128;
    private static final int WIDTH = 1024;

    private JButton checkButton;
    private JButton saveButton;

    private JTextField regonField;
    private JTextField nameField;
    private JTextField cityField;
    private JTextField zipCodeField;
    private JTextField pkdField;

    private JLabel msg;

    private CompanyData data;

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
        JLabel regon = new JLabel("Numer REGON:");
        JLabel name = new JLabel("Skrocona nazwa: ");
        JLabel city = new JLabel("Miejscowosc: ");
        JLabel zipCode = new JLabel("Kod pocztowy: ");
        JLabel pkd = new JLabel("Symbol PKD: ");
        msg = new JLabel();

        //set listeners
        checkButton.addActionListener(this);
        saveButton.addActionListener(this);
        regonField.addActionListener(this);
        nameField.addActionListener(this);
        cityField.addActionListener(this);
        zipCodeField.addActionListener(this);
        pkdField.addActionListener(this);

        //set layout
        setLayout(new GridLayout(6, 2));
        setPreferredSize(new Dimension(WIDTH, HEIGHT));

        add(regon);
        add(regonField);
        add(name);
        add(nameField);
        add(city);
        add(cityField);
        add(zipCode);
        add(zipCodeField);
        add(pkd);
        add(pkdField);

        add(checkButton);
        add(saveButton);


        add(msg);

        saveButton.setVisible(false);

        data = new CompanyData();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == checkButton) {
            data.checkDataCorrectness();
            if(data.dataCorrectness){
                msg.setText("Dane poprawne");
                saveButton.setVisible(true);
            } else {
                msg.setText(data.dataCorrectnessMsg);
                data.clear();
            }

        } else if (source == saveButton) {
            saveToXml();
        } else if (source == regonField) {
            data.setRegon(regonField.getText());

        } else if (source == nameField) {
            data.setShortName(nameField.getText());

        } else if (source == cityField){
            data.setCity(cityField.getText());

        } else if (source == zipCodeField){
            data.setZipCode(zipCodeField.getText());

        } else if (source == pkdField){
            data.setPkd(pkdField.getText());
        }
    }

    private void saveToXml(){
        //todo class XMLWriter and new XMLWriter(CompanyData data)

    }
}
