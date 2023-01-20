package eventapp.command;

import eventapp.model.Event;

/**
 * @author Christopher Forget
 */

public abstract class CommandController {
    
    public abstract void addEvent(Event event);
    public abstract void modifyEvent(Event oldEvent, Event newEvent);
    public abstract void removeEvent(Event event);
}
