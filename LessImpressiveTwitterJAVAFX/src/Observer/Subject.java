package Observer;

import Data.TreeElement;

import java.util.ArrayList;
import java.util.List;

public abstract class Subject implements TreeElement {
    private List<Observer> observers = new ArrayList<Observer>();

    public void attach (Observer o){
        observers.add(o);
    }

    public void detach (Observer o){
        observers.remove(o);
    }

    public void notifyObservers(){
        for(Observer o : observers){
            o.update(this);
        }
    }
}
