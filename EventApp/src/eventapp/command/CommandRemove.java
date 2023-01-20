package eventapp.command;

import eventapp.model.Event;

/**
 * @author Christopher Forget
 */

public class CommandRemove implements Command {
    
    private CommandController commandController;
    private Event event;
    
    public CommandRemove(CommandController commandController, Event event){
        this.commandController = commandController;
        this.event = event;
    }

    @Override
    public void execute() {        
        commandController.removeEvent(event);
    }

    @Override
    public void undo() {        
        commandController.addEvent(event);
    }  
}
