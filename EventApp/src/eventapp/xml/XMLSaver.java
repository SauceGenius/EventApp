package eventapp.xml;

import eventapp.model.Event;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * @author Christopher Forget
 */

public class XMLSaver {
    
    
    public XMLSaver(ArrayList<Event> events, Document document, String path) throws IOException, TransformerException{
        convert(events, document, path);
    }
    
    private void convert(ArrayList<Event> events, Document document, String path) throws IOException, TransformerException{     
        NodeList nlChannel = document.getElementsByTagName("channel");
        Element channel = (Element) nlChannel.item(0);     
        NodeList nlItems = document.getElementsByTagName("item");      
        int nlLength = nlItems.getLength();     
        for(int i = 0; i < nlLength; i++){
            Element item = (Element) nlItems.item(0);
            channel.removeChild(item);
        }   
        for(int i = 0; i < events.size(); i++){
            //Create new node
            Element item = document.createElement("item");
            channel.appendChild(item);
            
            Element title = document.createElement("title");
            Element link = document.createElement("link");
            Element guid = document.createElement("guid");
            Element pubDate = document.createElement("pubDate");
            Element description = document.createElement("description");  
            
            title.appendChild(document.createTextNode(events.get(i).getTitle()));
            link.appendChild(document.createTextNode(events.get(i).getLink()));
            guid.appendChild(document.createTextNode(events.get(i).getGuid()));
            pubDate.appendChild(document.createTextNode(events.get(i).getPubDate()));
            description.appendChild(document.createTextNode(events.get(i).getDescription()));  
            
            item.appendChild(title);
            item.appendChild(link);
            item.appendChild(guid);
            item.appendChild(pubDate);
            item.appendChild(description);                    
        }                 
        save(document, path);
    }
    
    private void save(Document document, String path) throws IOException, TransformerException{
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
