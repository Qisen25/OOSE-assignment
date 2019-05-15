package ecm.model;

import java.util.*;

/*
 *Keywords
 */
public class Keywords implements TextData
{
    private Policy injectPolicy;
    private Set<String> allKeywords;
    private List<TextObserver> observers;
//    private Map<String, Set<String>> policyKeywords;
    
    public Keywords(Policy inject)
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
            ob.dataUpdate(this.allKeywords);
        }
    }
    /*
     *Add a keyword to map
     *IMPORT: related policy name and keyword
     */
    @Override
    public void addData(String polName, String data) throws PolicyNotFoundException
    {
        injectPolicy.addKeyword(polName, data);
        allKeywords = injectPolicy.getKeywords();
        notifyObservers();//use this for removeData aswell
    }
    
    @Override
    public void removeData(String data)
    {
        injectPolicy.removeKeyword(data);
        allKeywords = injectPolicy.getKeywords();
        notifyObservers();
    }

    @Override
    public Set<String> getPolicyData(String polName)
    {
//        return policyKeywords.get(polName);
        return null;
    }

    @Override
    public Set<String> getAllThisData()
    {
        return allKeywords;
    }
}
