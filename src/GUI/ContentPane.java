package GUI;

import company.CompanyData;
import files.XMLReader;
import files.XMLWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;


public class ContentPane extends Container implements FocusListener{

    private JButton checkButton;
    private JButton saveButton;
    private JButton loadButton;

    private JTextField regonField;
    private JTextField nameField;
    private JTextField cityField;
    private JTextField zipCodeField;
    private JTextField pkdField;

    private JTextArea msg;

    private CompanyData data;

    ContentPane(){
        //set buttons
        checkButton = new JButton("Sprawdz");
        saveButton = new JButton("Zapisz");
        loadButton = new JButton("Wczytaj");

        //set text fields
        regonField = new JTextField("", 15);
        nameField = new JTextField("", 15);
        cityField = new JTextField("", 15);
        zipCodeField = new JTextField("", 15);
        pkdField = new JTextField("", 15);

        //set labels
        JLabel regon = new JLabel("Numer REGON: ");
        JLabel name = new JLabel("Skrocona nazwa: ");
        JLabel city = new JLabel("Miejscowosc: ");
        JLabel zipCode = new JLabel("Kod pocztowy: ");
        JLabel pkd = new JLabel("Symbol PKD: ");

        msg = new JTextArea(2, 28);

        //set listeners
        checkButton.addFocusListener(this);
        saveButton.addFocusListener(this);
        loadButton.addFocusListener(this);
        regonField.addFocusListener(this);
        nameField.addFocusListener(this);
        cityField.addFocusListener(this);
        zipCodeField.addFocusListener(this);
        pkdField.addFocusListener(this);

        //set layout
        SpringLayout layout = new SpringLayout();
        setLayout((layout));

        //add fields
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
        add(loadButton);

        add(msg);
        msg.setLineWrap(true);
        msg.setWrapStyleWord(true);

        //set fields in columns
        layout.putConstraint(SpringLayout.WEST, regon, 5, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, regon, 5, SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.WEST, regonField, 25, SpringLayout.EAST, regon);
        layout.putConstraint(SpringLayout.NORTH, regonField, 5, SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.WEST, name, 5, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, name, 30, SpringLayout.NORTH, regon);

        layout.putConstraint(SpringLayout.WEST, nameField, 25, SpringLayout.EAST, regon);
        layout.putConstraint(SpringLayout.NORTH, nameField, 30, SpringLayout.NORTH, regonField);

        layout.putConstraint(SpringLayout.WEST, city, 5, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, city, 30, SpringLayout.NORTH, name);

        layout.putConstraint(SpringLayout.WEST, cityField, 25, SpringLayout.EAST, regon);
        layout.putConstraint(SpringLayout.NORTH, cityField, 30, SpringLayout.NORTH, nameField);

        layout.putConstraint(SpringLayout.WEST, zipCode, 5, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, zipCode, 30, SpringLayout.NORTH, city);

        layout.putConstraint(SpringLayout.WEST, zipCodeField, 25, SpringLayout.EAST, regon);
        layout.putConstraint(SpringLayout.NORTH, zipCodeField, 30, SpringLayout.NORTH, cityField);

        layout.putConstraint(SpringLayout.WEST, pkd, 5, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, pkd, 30, SpringLayout.NORTH, zipCode);

        layout.putConstraint(SpringLayout.WEST, pkdField, 25, SpringLayout.EAST, regon);
        layout.putConstraint(SpringLayout.NORTH, pkdField, 30, SpringLayout.NORTH, zipCodeField);

        layout.putConstraint(SpringLayout.WEST, checkButton, 5, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, checkButton, 30, SpringLayout.NORTH, pkd);

        layout.putConstraint(SpringLayout.WEST, loadButton, 5, SpringLayout.EAST, checkButton);
        layout.putConstraint(SpringLayout.NORTH, loadButton, 30, SpringLayout.NORTH, pkdField);

        layout.putConstraint(SpringLayout.WEST, saveButton, 5, SpringLayout.EAST, loadButton);
        layout.putConstraint(SpringLayout.NORTH, saveButton, 30, SpringLayout.NORTH, pkdField);

        layout.putConstraint(SpringLayout.WEST, msg, 5, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, msg, 30, SpringLayout.NORTH, saveButton);

        layout.putConstraint(SpringLayout.EAST, this, 10, SpringLayout.EAST, regonField);
        layout.putConstraint(SpringLayout.SOUTH, this, 48, SpringLayout.SOUTH, checkButton);


        saveButton.setVisible(false);

        data = new CompanyData();
    }


    private void saveToXml(){
        new XMLWriter(data);

    }

    private void loadXML(){
        XMLReader xmlReader = new XMLReader(data);

        regonField.setText(xmlReader.companyData.getRegon());
        nameField.setText(xmlReader.companyData.getShortName());
        cityField.setText(xmlReader.companyData.getCity());
        zipCodeField.setText(xmlReader.companyData.getZipCode());
        pkdField.setText(xmlReader.companyData.getPkd());

    }

    private Object prevSource;
    private boolean isSaved= false;
    @Override
    public void focusGained(FocusEvent e) {
        Object source = e.getSource();

        //System.out.println("\nfocus gained " + source);

        if (source == checkButton) {
            focusRequest();
            if(data.dataCorrectness){
                msg.setText("Dane poprawne");
                saveButton.setVisible(true);
            } else {
                msg.setText(data.dataCorrectnessMsg);
                data.clear();
            }
            prevSource = source;

        } else if (source == saveButton) {
            if(!isSaved) {
                saveToXml();
                isSaved = true;
            }
            prevSource = source;

        } else if (source == loadButton) {
            if(source != prevSource) {
                loadXML();
                prevSource = source;
            }
        }

    }

    @Override
    public void focusLost(FocusEvent e) {
        Object source = e.getSource();
        //System.out.println("\nfocus lost " + source);

        if (source == regonField) {
            data.setRegon(regonField.getText());

        } else if (source == nameField) {
            data.setShortName(nameField.getText());

        } else if (source == cityField){
            data.setCity(cityField.getText());

        } else if (source == zipCodeField){
            data.setZipCode(zipCodeField.getText());

        } else if (source == pkdField){
            data.setPkd(pkdField.getText());
        } else if( source == saveButton){
            isSaved = false;
        }
    }

    private void focusRequest(){
        data.checkDataCorrectness();
        regonField.requestFocus();
        nameField.requestFocus();
        zipCodeField.requestFocus();
        pkdField.requestFocus();
    }
}

