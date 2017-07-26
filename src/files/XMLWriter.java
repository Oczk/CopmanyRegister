package files;

import company.CompanyData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

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

    public XMLWriter(CompanyData data){
        //CompanyData data1 = data;

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
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(data.getRegon() + ".xml"));

            transformer.transform(source, result);

            /*
            Frame a = new Frame ();
            FileDialog fd =new FileDialog(a,"Zapisz",FileDialog.SAVE);
            fd.setVisible(true);
            String directory=fd.getDirectory();
            String file=fd.getFile();


            StreamResult result = new StreamResult(new File(directory + "/" + file + ".xml"));
            transformer.transform(source, result);
            */


            System.out.println("file saved");



        } catch (ParserConfigurationException | TransformerException e ) {
            e.printStackTrace();
        }

    }
}
