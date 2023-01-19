package eventapp;

import java.io.IOException;
import javax.swing.table.DefaultTableModel;
import javax.xml.transform.TransformerException;


/**
 *
 * @author Christopher Forget
 */
public interface Observer {
    
    public void openRSS(String url) throws Exception;
    public void loadTreeToInterface(DefaultTableModel model);
    public Event getEvent(int row);
    public void addEvent(Event event);
    public void modifyEvent(int row, Event event);
    public void removeEvent(int row);
    public void saveToXMLFile(String path)throws IOException, TransformerException; 
}
