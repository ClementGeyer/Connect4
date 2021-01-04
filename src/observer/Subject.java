package observer;

import observer.Observer;

import java.util.ArrayList;
import java.util.List;

public abstract class Subject {
    List<Observer> myObservers = new ArrayList<>();

    protected void notifyObservers()
    {
        for(Observer o : myObservers)
        {
            o.update();
        }
    }

    public void addObserver(Observer o)
    {
        myObservers.add(o);
    }

    public void removeObserver(Observer o)
    {
        myObservers.remove(o);
    }
}
