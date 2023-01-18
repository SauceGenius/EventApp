package eventapp;

/**
 * @author Christopher Forget
 */

import java.util.ArrayList;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class XMLParser {
    
    private Document document;
    
    public Document parse(String url) throws Exception{
        // Loads an XML file and builds a DOM tree document.
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(false);
        DocumentBuilder parser = factory.newDocumentBuilder();
        document = parser.parse(url);
        return document;
    }
    
    public Element getElement(String elementName){
        //Access channel
        NodeList nl = document.getElementsByTagName(elementName);
        Element element = (Element) nl.item(0);      
        return element;
    }
    
    public ArrayList<Element> getElementList(String elementsTag){
        ArrayList<Element> items = new ArrayList<>();
        NodeList nl = document.getElementsByTagName(elementsTag);

        //Pour chaque Node item of va chercher le titre, la date, le lien
        for (int i = 0; i < nl.getLength(); i++){
            Element element = (Element) nl.item(i);
            items.add(element);
        } 
        return items;
    }   
}
