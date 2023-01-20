
package eventapp.command;

/**
 * @author Christopher Forget
 */

public interface Command {
    
    public void execute();
    public void undo();
    
}
