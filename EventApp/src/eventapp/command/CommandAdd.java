package eventapp.command;

import eventapp.model.Event;

/**
 * @author Christopher Forget
 */

public class CommandAdd implements Command {

    private CommandController commandController;
    private Event event;
    
    public CommandAdd(CommandController commandController, Event event){
        this.commandController = commandController;
        this.event = event;
    }
    
    @Override
    public void execute() {              
        commandController.addEvent(event);
    }

    @Override
    public void undo() {    
        commandController.removeEvent(event);
    } 
}
