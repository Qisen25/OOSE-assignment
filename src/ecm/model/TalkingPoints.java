package ecm.model;

import java.util.*;
/*
 * talk points
 */
public class TalkingPoints implements TextData
{
    private Policy injectPolicy;
    private Set<String> allTalkPoints;
    
    public TalkingPoints(Policy inject)
    {
        this.injectPolicy = inject;
    }

    @Override
    public void addData(String polName, String data)
    {
        injectPolicy.addTalkingPoint(polName, data);
        
//        TODO updateObservers();
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
