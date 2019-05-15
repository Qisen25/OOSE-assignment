package ecm.model;

import java.util.*;
/*
 * talk points
 */
public class TalkingPoints implements TextData
{
    private Policy injectPolicy;
    private Set<String> allTalkPoints;
    private List<TextObserver> observers;
    
    public TalkingPoints(Policy inject)
    {
        this.injectPolicy = inject;
        this.observers = new ArrayList<TextObserver>();
    }

    public void addObserver(TextObserver obs)
    {
        observers.add(obs);
    }
    
    public void removeObserver(TextObserver obs)
    {
        observers.remove(obs);
    }
    
    public void notifyObservers()
    {
        for(TextObserver ob : observers)
        {
            ob.dataUpdate(this.allTalkPoints);
        }
    }
    
    @Override
    public void addData(String polName, String data) throws PolicyNotFoundException
    {
        injectPolicy.addTalkingPoint(polName, data);
        allTalkPoints = injectPolicy.getTalkPoints();
        this.notifyObservers();
    }
    
    @Override
    public void removeData(String data)
    {
        injectPolicy.removeTalkingPoint(data);
        allTalkPoints = injectPolicy.getTalkPoints();
        this.notifyObservers();
    }

    @Override
    public Set<String> getPolicyData(String polName)
    {
        return null;
    }

    @Override
    public Set<String> getAllThisData()
    { 
        allTalkPoints = injectPolicy.getTalkPoints();
        
        return allTalkPoints;
    }
}
