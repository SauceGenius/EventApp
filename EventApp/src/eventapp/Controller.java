package eventapp;

/**
 * @author Christopher Forget
 */

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class Controller {
    
    private final XMLParser xmlParser;
    private Document document;
    private Element channel;

    public Controller(){
        xmlParser = new XMLParser();
    }   
    
    public void openRSS(String url) throws Exception{
        document = xmlParser.parse(url);
        channel = xmlParser.getElement("channel");
    } 
    
    public void loadTreeToInterface(DefaultTableModel model){
        int rowCount = model.getRowCount();
        for(int i = 0; i < rowCount; i++){
            model.removeRow(0);
        }
        
        ArrayList<Element> items = xmlParser.getElementList("item");
        for(int i = 0; i < items.size(); i++){
            Element item = items.get(i);
            
            NodeList nlTitle = item.getElementsByTagName("title");
            Element title = (Element) nlTitle.item(0);
            String eventTitle = title.getFirstChild().getNodeValue();
            
            NodeList nlLink = item.getElementsByTagName("link");
            Element link = (Element) nlLink.item(0);
            String eventLink = link.getFirstChild().getNodeValue();
            
            NodeList nlPubDate = item.getElementsByTagName("pubDate");
            Element pubDate = (Element) nlPubDate.item(0);
            String eventPubDate = pubDate.getFirstChild().getNodeValue();
            
            model.addRow(new Object[]{eventTitle,eventLink,eventPubDate});
        }
    }
    
    public Event getEvent(int row){
        ArrayList<Element> items = xmlParser.getElementList("item");
        Element item = items.get(row);  
        
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
        return event;
    }
    
    public void addEvent(Event event){
        //events.add(event);
        Element item = document.createElement("item");
        channel.appendChild(item);
        
        Element title = document.createElement("title");
        title.appendChild(document.createTextNode(event.getTitle()));
        Element link = document.createElement("link");
        link.appendChild(document.createTextNode(event.getLink()));
        Element guid = document.createElement("guid");
        guid.appendChild(document.createTextNode(event.getGuid()));
        Element pubDate = document.createElement("pubDate");
        pubDate.appendChild(document.createTextNode(event.getPubDate()));
        Element description = document.createElement("description");
        description.appendChild(document.createTextNode(event.getDescription()));  
        
        item.appendChild(title);
        item.appendChild(link);
        item.appendChild(guid);
        item.appendChild(pubDate);
        item.appendChild(description);
    }
    
    public void modifyEvent(int row, Event event){
        ArrayList<Element> items = xmlParser.getElementList("item");
        Element item = items.get(row);        
        
        NodeList nlTitle = item.getElementsByTagName("title");
        Element title = (Element) nlTitle.item(0);
        title.getFirstChild().setNodeValue(event.getTitle());
        
        NodeList nlLink = item.getElementsByTagName("link");
        Element link = (Element) nlLink.item(0);
        link.getFirstChild().setNodeValue(event.getLink());
        
        NodeList nlGuid = item.getElementsByTagName("guid");
        Element guid = (Element) nlGuid.item(0);
        guid.getFirstChild().setNodeValue(event.getGuid());       
        
        NodeList nlPubDate = item.getElementsByTagName("pubDate");
        Element pubDate = (Element) nlPubDate.item(0);
        pubDate.getFirstChild().setNodeValue(event.getPubDate());
        
        NodeList nlDesc = item.getElementsByTagName("description");
        Element desc = (Element) nlDesc.item(0);
        desc.getFirstChild().setNodeValue(event.getDescription());
    }
    
    public void removeEvent(int row){
        NodeList nl = channel.getElementsByTagName("item");
        Element element = (Element) nl.item(row); 
        channel.removeChild(element);  
    }
    
    public void saveToXMLFile(String path) throws IOException, TransformerException{
        TransformerFactory tfact = TransformerFactory.newInstance();
        Transformer transformer = tfact.newTransformer();
        transformer.setOutputProperty("encoding", "UTF-8");
        DOMSource source = new DOMSource(document);
        String dest = path.concat("\\RSSFeed.xml");
        FileWriter fw = new FileWriter(dest);
        StreamResult result = new StreamResult(fw);
        transformer.transform(source, result);
    }
}
