package eventapp;


import java.util.ArrayList;

/**
 * @author Christopher Forget
 */

public interface Subject {
    
    ArrayList<Observer> observers = new ArrayList<>();
    
    void addObserver(Observer o);
    void removeObserver(Observer o);
    void notify(Object o);
}
