import javax.xml.parsers.*;
import org.w3c.dom.*;
import java.io.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;

public class MyXMLWriter {

    public static void MyWriter() {

        try {

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // Створення кореневого елемента
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("address");
            doc.appendChild(rootElement);

            // Створення міста з атрибутом size
            Element city = doc.createElement("city");
            city.setAttribute("size", "big");
            city.appendChild(doc.createTextNode("Kyiv"));
            rootElement.appendChild(city);

            // Створення вулиці
            Element street = doc.createElement("street");
            street.appendChild(doc.createTextNode("Shevchenka"));
            city.appendChild(street);

            // Створення будинку
            Element house = doc.createElement("house");
            house.appendChild(doc.createTextNode("10"));
            street.appendChild(house);

            // Запис XML-файлу
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("address.xml"));
            transformer.transform(source, result);

            System.out.println("XML-файл успішно створено!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
