package eventapp.xml;

/**
 * @author Christopher Forget
 */

import eventapp.model.Event;
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
    
    public ArrayList<Event> getEventList(){
        ArrayList<Event> events = new ArrayList<>();
        
        ArrayList<Element> items = getElementList("item");
        
        for(int i = 0; i < document.getElementsByTagName("item").getLength(); i++){
            Element item = items.get(i);  
        
            NodeList nlTitle = item.getElementsByTagName("title");
            Element title = (Element) nlTitle.item(0);
            String eventTitle = title.getFirstChild().getNodeValue();
            
            NodeList nlLink = item.getElementsByTagName("link");
            Element link = (Element) nlLink.item(0);
            String eventLink = link.getFirstChild().getNodeValue();
        
            NodeList nlGuid = item.getElementsByTagName("guid");
            Element guid = (Element) nlGuid.item(0);
            String eventGuid = guid.getFirstChild().getNodeValue();
            
            NodeList nlPubDate = item.getElementsByTagName("pubDate");
            Element pubDate = (Element) nlPubDate.item(0);
            String eventPubDate = pubDate.getFirstChild().getNodeValue();
        
            NodeList nlDesc = item.getElementsByTagName("description");
            Element desc = (Element) nlDesc.item(0);
            String eventDesc = desc.getFirstChild().getNodeValue();
        
            Event event = new Event(eventTitle, eventLink, eventGuid, eventPubDate, eventDesc);
            events.add(event);
        }
        return events;
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
