//File created by Tanner Davis

/*
 * Rule name: Do not load trusted classes after allowing untrusted code to load arbitrary classes
 */

 import java.io.FileInputStream;
 import java.io.IOException;
 import javax.xml.parsers.DocumentBuilder;
 import javax.xml.parsers.DocumentBuilderFactory;
 import javax.xml.parsers.ParserConfigurationException;
 import org.w3c.dom.Document;
 import org.xml.sax.SAXException;
 
 class SEC03
 {
    protected static final Document webDocument = init();
 
    protected static Document init()
    {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try
        {
            builder = factory.newDocumentBuilder();
            return builder.parse(new FileInputStream("web.xml"));
        }
        catch (ParserConfigurationException | SAXException | IOException e)
        {
            e.printStackTrace();
            return null;
        }
     }
 
     public static void main(String[] args) {
        try
        {
            loadArbitraryClasses();
        }
        catch(SecurityException e)
        {
            System.out.println("SecurityException: " + e.getMessage());
        }
 
        Document result = SEC03.webDocument;
        System.out.println("Successfully accessed the trusted Document: " + result);
    }
 
    protected static void loadArbitraryClasses()
    {
        throw new SecurityException("Loading arbitrary classes is not allowed.");
    }
 }
