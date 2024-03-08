import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class IDS16 {
    /**
     * Safely generates an XML order with user-provided quantity,
     * preventing XML injection through input validation.
     *
     * @param description  The item's description.
     * @param price       The item's price.
     * @param quantity    The desired quantity (supplied by user).
     * @return  A well-formed XML order fragment.
     * @throws IllegalArgumentException If quantity input is invalid.
    */
    public String generateOrderXML(String description, double price, String quantity) {
        if (!quantity.matches("\\d+")) { // Strict validation: only digits allowed
            throw new IllegalArgumentException("Invalid quantity format");
        }

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        // Configure factory securely if needed (disable DTDs, etc.)

        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();

            Element item = doc.createElement("item");

            Element quantityElement = doc.createElement("quantity");
            quantityElement.appendChild(doc.createTextNode(quantity));
            item.appendChild(quantityElement);

            // XML Serialization
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = null;
            try {
                transformer = transformerFactory.newTransformer();
            } catch (TransformerException e) {
                e.printStackTrace();
            }
            DOMSource source = new DOMSource(doc);
            StringWriter writer = new StringWriter();
            StreamResult result = new StreamResult(writer);

            try {
                transformer.transform(source, result);
            } catch (TransformerException e) {
                e.printStackTrace();
            }
            return writer.toString(); 
        } catch (ParserConfigurationException e) {
            System.err.println("Error creating DocumentBuilder: " + e.getMessage());
            return null;
        } 
    }

    public static void main(String[] args) {
        IDS16 generator = new IDS16();
        String description = "Cool Gadget";
        double price = 19.99;
        String quantity = "2"; // Valid quantity

        String orderXML = generator.generateOrderXML(description, price, quantity);
        System.out.println(orderXML);
    }
}
