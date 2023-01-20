package eventapp.controller;

import eventapp.model.Event;
import java.io.IOException;
import javax.swing.table.DefaultTableModel;
import javax.xml.transform.TransformerException;

/**
 * @author Christopher Forget
 */

public interface Observer {
    
    public void notifyOpenRSS(String url) throws Exception;
    public void notifyModel(DefaultTableModel model);
    public void notifyAddEvent(Event event);
    public void notifyModifyEvent(Event oldEvent, Event newEvent);
    public void notifyRemoveEvent(Event event);
    public void notifyUndo();
    public void notifyRedo();
    public void notifySaveToXMLFile(String path)throws IOException, TransformerException; 
    public Event getEvent(int row);
}
