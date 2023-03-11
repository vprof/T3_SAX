import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class SAXParser extends  DefaultHandler {
    private City city;
    private Street street;
    private Building building;
    private String currentElement;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equalsIgnoreCase("city")) {
            String size = attributes.getValue("size");
            String name = qName;
            city = new City(name,size);
            currentElement = "city";
        } else if (qName.equalsIgnoreCase("street")) {
            String name = attributes.getValue("name");
            street = new Street(name);
            currentElement = "street";
        } else if (qName.equalsIgnoreCase("building")) {
            String number = attributes.getValue("number");
            building = new Building(Integer.parseInt(number));
            currentElement = "building";
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
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
            case "building":
                building.setNumber(Integer.parseInt(value));
                break;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase("city")) {
            currentElement = "";
        } else if (qName.equalsIgnoreCase("street")) {
            city.addStreet(street);
            currentElement = "city";
        } else if (qName.equalsIgnoreCase("building")) {
            street.addHouse(building);
            currentElement = "street";
        }
    }

    private static class City {
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

    private static class Street {
        private String name;
        private List<Building> houses = new ArrayList<>();

        public Street(String name) {
            this.name = name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void addHouse(Building house) {
            houses.add(house);
        }

        @Override
        public String toString() {
            return "Street [name= " + name + ", houses= " + houses + "]";
        }
    }

    private static class Building {
        private int number;

        public Building(int number) {
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
