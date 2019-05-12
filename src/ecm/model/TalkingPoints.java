package ecm.model;

import java.util.*;
/*
 * talk points
 */
public class TalkingPoints implements TextData
{
    private Map<String, Set<String>> policyTalk;
    
    public TalkingPoints()
    {
        this.policyTalk = new HashMap<String, Set<String>>();
    }

    @Override
    public void addData(String polName, String data)
    {
        Set<String> talkPts = new HashSet<String>();
        talkPts.add(data);
        policyTalk.put(polName, talkPts);
    }

    @Override
    public Set<String> getPolicyData(String polName)
    {
        return policyTalk.get(polName);
    }

    @Override
    public Set<String> getAllThisData()
    {
        Set<String> mergeSet = new HashSet<String>();
        
        for(Set<String> talkSets : policyTalk.values())
        {
            mergeSet.addAll(talkSets);
        }
        
        return mergeSet;
    }
}
