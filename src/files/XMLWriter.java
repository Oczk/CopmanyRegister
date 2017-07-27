package files;

import company.CompanyData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class XMLWriter {

    private Transformer transformer;
    private DOMSource source;
    private StreamResult result;
    private CompanyData data;

    public XMLWriter(CompanyData data) {
        this.data = data;
        writeFile();
    }

    public void writeFile() {

        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("Firma");
            doc.appendChild(rootElement);

            Element datas = doc.createElement("Dane");
            rootElement.appendChild(datas);

            Element regon = doc.createElement("REGON");
            regon.appendChild(doc.createTextNode(data.getRegon()));
            datas.appendChild(regon);

            Element shortName = doc.createElement("SkroconaNazwa");
            shortName.appendChild(doc.createTextNode(data.getShortName()));
            datas.appendChild(shortName);

            Element city = doc.createElement("Miasto");
            city.appendChild(doc.createTextNode(data.getCity()));
            datas.appendChild(city);

            Element zipCode = doc.createElement("KodPocztowy");
            zipCode.appendChild(doc.createTextNode(data.getZipCode()));
            datas.appendChild(zipCode);

            Element pkd = doc.createElement("PKD");
            pkd.appendChild(doc.createTextNode(data.getPkd()));
            datas.appendChild(pkd);


            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            transformer = transformerFactory.newTransformer();
            source = new DOMSource(doc);

            //saving file
            File file = new File("Data/" + data.getRegon() + ".xml");
            result = new StreamResult(file);

            if (file.exists()) {

                System.out.println("plik istnieje");
                createDialogue();

            } else {
                saveFile();
            }

        } catch (ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
        }
    }

    private void saveFile() {
        try {
            transformer.transform(source, result);

            System.out.println("file saved");

        } catch (TransformerException e) {
            e.printStackTrace();
        }

    }

    private void createDialogue() {
        JDialog.setDefaultLookAndFeelDecorated(true);
        int response = JOptionPane.showConfirmDialog(null, "override?", "confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.NO_OPTION) {
            System.out.println("plik nie zostal nadpisany");
        } else if (response == JOptionPane.YES_OPTION) {
            saveFile();
            System.out.println("plik nadpisany");
        } else if (response == JOptionPane.CLOSED_OPTION) {
            System.out.println("okno zamkniete");
        }
    }

}
