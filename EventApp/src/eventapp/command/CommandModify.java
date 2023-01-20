package eventapp.command;

import eventapp.model.Event;
import eventapp.controller.Observer;

/**
 * @author Christopher Forget
 */

public class CommandModify implements Command {

    private CommandController commandController;
    private Event newEvent;
    private Event oldEvent;
    private Event savedEvent;
    
    public CommandModify(CommandController commandController, Event oldEvent, Event newEvent){
        this.commandController = commandController;
        this.newEvent = newEvent;
        this.oldEvent = oldEvent;
    }
    
    @Override
    public void execute() {  
        savedEvent = new Event(oldEvent.getTitle(),oldEvent.getLink(),oldEvent.getLink(),oldEvent.getPubDate(),oldEvent.getDescription());
        commandController.modifyEvent(oldEvent, newEvent);
    }

    @Override
    public void undo() {     
        commandController.modifyEvent(oldEvent, savedEvent);
    }    
}
