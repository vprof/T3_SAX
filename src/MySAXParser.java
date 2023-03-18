import org.xml.sax.*;
import org.xml.sax.helpers.*;
import java.util.ArrayList;
import java.util.List;

public class MySAXParser extends DefaultHandler {

    private City city;
    private Street street;
    private House house;
    private String currentElement;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if (qName.equalsIgnoreCase("city")) {
            String size = attributes.getValue("size");
            city = new City(qName,size);
            currentElement = "city";
        } else if (qName.equalsIgnoreCase("street")) {
            street = new Street(qName);
            currentElement = "street";
        } else if (qName.equalsIgnoreCase("house")) {
            house = new House(0);
            currentElement = "house";
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        String value = new String(ch, start, length).trim();
        if (value.length() == 0) {
            return;
        }
        switch (currentElement) {
            case "city":
                city.setName(value);
                break;
            case "street":
                street.setName(value);
                break;
            case "house":
                house.setNumber(Integer.parseInt(value));
                break;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (qName.equalsIgnoreCase("city")) {
            currentElement = "";
        } else if (qName.equalsIgnoreCase("street")) {
            city.addStreet(street);
            currentElement = "city";
        } else if (qName.equalsIgnoreCase("house")) {
            street.addHouse(house);
            currentElement = "street";
        }
    }

    public static class City {
        private String name;
        private String size;
        private List<Street> streets = new ArrayList<>();

        public City(String name, String size) {
            this.name = name;
            this.size = size;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void addStreet(Street street) {
            streets.add(street);
        }

        @Override
        public String toString() {
            return "City [name=" + name + ", size=" + size + ", streets=" + streets + "]";
        }
    }

    public static class Street {
        private String name;
        private List<House> houses = new ArrayList<>();

        public Street(String name) {
            this.name = name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void addHouse(House house) {
            houses.add(house);
        }

        @Override
        public String toString() {
            return "Street [name= " + name + ", houses= " + houses + "]";
        }
    }

    public static class House {
        private int number;

        public House(int number) {
            this.number = number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        @Override
        public String toString() {
            return "House [number= " + number + "]";
        }
    }
}