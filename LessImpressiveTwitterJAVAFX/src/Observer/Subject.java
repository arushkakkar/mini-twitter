package Observer;

import Data.TreeElement;

import java.util.ArrayList;
import java.util.List;

public abstract class Subject implements TreeElement {
    private List<Observer> followers = new ArrayList<Observer>();

    public void attach (Observer o){
        followers.add(o);
    }

    public void detach (Observer o){
        followers.remove(o);
    }

    public void notifyObservers(){
        for(Observer o : followers){
            o.update(this);
        }
    }
}
