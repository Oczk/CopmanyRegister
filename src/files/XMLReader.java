package files;

import company.CompanyData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class XMLReader {

    public CompanyData companyData;

    private String directory;
    private FileDialog fd;

    public XMLReader(CompanyData data) {
        Frame a = new Frame();

        //system window to load file
        this.fd = new FileDialog(a, "Wczytaj", FileDialog.LOAD);
        fd.setVisible(true);

        this.directory = fd.getDirectory();

        if (directory == null) {
            //if no new directory is chosen, don't change loaded data
            this.companyData = data;

        } else {

            loadFile();
        }
    }

    private void loadFile(){
        companyData = new CompanyData();

        String file = fd.getFile();
        File xmlFile = new File(directory + file);

        //loading data
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(xmlFile);

            document.getDocumentElement().normalize();

            NodeList nodeList = document.getElementsByTagName("Dane");


            for (int i = 0; i < nodeList.getLength(); ++i) {

                Node node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    if (element.getElementsByTagName("REGON").item(0).getTextContent() != null) {
                        companyData.setRegon(element.getElementsByTagName("REGON").item(0).getTextContent());
                        companyData.setShortName(element.getElementsByTagName("SkroconaNazwa").item(0).getTextContent());
                        companyData.setCity(element.getElementsByTagName("Miasto").item(0).getTextContent());
                        companyData.setZipCode(element.getElementsByTagName("KodPocztowy").item(0).getTextContent());
                        companyData.setPkd(element.getElementsByTagName("PKD").item(0).getTextContent());
                    }
                }

            }


        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

        if (companyData.getPkd() != null && companyData.getRegon() != null && companyData.getZipCode() != null && companyData.getShortName() != null) {
            System.out.println("Dane wczytane pomyslnie");
        }
        System.out.println("Wybrano plik: " + xmlFile.getAbsolutePath());
        System.out.println("w katalogu: " + directory);
        System.out.println("Ścieżka: " + directory + file);
    }
}

