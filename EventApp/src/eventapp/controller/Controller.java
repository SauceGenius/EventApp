package eventapp.controller;

/**
 * @author Christopher Forget
 */

import eventapp.model.Event;
import eventapp.xml.XMLParser;
import eventapp.xml.XMLSaver;
import eventapp.command.Command;
import eventapp.command.CommandAdd;
import eventapp.command.CommandController;
import eventapp.command.CommandModify;
import eventapp.command.CommandRemove;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import javax.xml.transform.TransformerException;
import org.w3c.dom.Document;

public class Controller extends CommandController implements Observer {
    
    private final XMLParser xmlParser;
    private DefaultTableModel model;
    private Document document;
    private Command command;
    private ArrayList<Event> events;

    public Controller(){
        xmlParser = new XMLParser();
    }   
    
    public void refreshModel(DefaultTableModel model){
        int rowCount = model.getRowCount();
        for(int i = 0; i < rowCount; i++){
            model.removeRow(0);
        } 
        for(int i = 0; i < events.size(); i++){
            String eventTitle = events.get(i).getTitle();
            String eventLink = events.get(i).getLink();
            String eventPubDate = events.get(i).getPubDate();
            model.addRow(new Object[]{eventTitle,eventLink,eventPubDate});
        }              
    }
    
    @Override
    public Event getEvent(int row){
        Event event = events.get(row);
        return event;
    }
       
    @Override
    public void notifyOpenRSS(String url) throws Exception{
        document = xmlParser.parse(url);
        events = xmlParser.getEventList();
        this.refreshModel(model);
    } 
    
    @Override
    public void notifySaveToXMLFile(String path) throws IOException, TransformerException{
        XMLSaver xmlSaver = new XMLSaver(events, document, path); 
    }
    
    @Override
    public void notifyModel(DefaultTableModel model){
        this.model = model;
    }
    
    @Override
    public void notifyAddEvent(Event event){
        command = new CommandAdd(this, event);
        command.execute();
    }
    
    @Override
    public void notifyModifyEvent(Event oldEvent, Event newEvent){
        command = new CommandModify(this, oldEvent, newEvent);
        command.execute();
    }
    
    @Override
    public void notifyRemoveEvent(Event event){
        command = new CommandRemove(this, event);
        command.execute();
    }
    
    @Override
    public void notifyUndo(){
        command.undo();
    }
    
    @Override
    public void notifyRedo(){
        command.execute();
    }  
    
    @Override
    public void addEvent(Event event){
        events.add(event);     
        this.refreshModel(model);
    }

    @Override
    public void modifyEvent(Event oldEvent, Event newEvent){   
        int index = events.indexOf(oldEvent);               
        events.get(index).setTitle(newEvent.getTitle());
        events.get(index).setLink(newEvent.getLink());
        events.get(index).setGuid(newEvent.getGuid());
        events.get(index).setPubDate(newEvent.getPubDate());
        events.get(index).setDescription(newEvent.getDescription());               
        this.refreshModel(model);
    }
    
    @Override
    public void removeEvent(Event event){
        events.remove(event);
        this.refreshModel(model);
    }
}
