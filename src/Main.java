import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;

public class Main {
    public static void main(String[] args) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            MySAXParser handler = new MySAXParser();
            saxParser.parse(new File("file.xml"), handler);
            MyXMLWriter.MyWriter();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}