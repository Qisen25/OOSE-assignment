package ecm.view;

import java.util.Collection;
import java.util.Set;
import ecm.model.PolicyAreas;
import ecm.model.TalkingPointObserver;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 *
 * @author beepbeep
 */
public class TalkingPointViewer implements TalkingPointObserver, Viewer
{
    private PolicyAreas pArea;
    private Set<String> allTalk;
    private Map<String, Set<String>> policyWithTalk;

    public TalkingPointViewer(PolicyAreas pArea)
    {
        this.pArea= pArea;
        this.allTalk= new HashSet<String>();
        this.policyWithTalk = new HashMap<String, Set<String>>();
    }
        
    @Override
    public void subscribe()
    {
        pArea.addTPObserver(this);
    }

    @Override
    public void unsubscribe()
    {
        pArea.removeTPObserver(this);
    }
    
    @Override
    public void talkingPointSetUpdate(Set<String> data, String policyName, String recentKeyword)
    {
        this.allTalk = data;
    }

    @Override
    public void display()
    {
        System.out.println("++Talking points found in system++");
        this.displayMap();
    }
    
    public void displaySet()
    {      
        for(String str : allTalk)
        {
            System.out.println(str);
        }
    }
    
    public void displayMap()
    {
        for(Map.Entry<String, Set<String>> ent : this.policyWithTalk.entrySet())
        {
            for(String key : ent.getValue())
            {
                System.out.println("Talking point: \"" + key + "\" from policy: " + ent.getKey());
            }
        }
    }
    
    public void displayMapGivenPolicy(String policy)
    {       
        for(String key : this.policyWithTalk.get(policy))
        {
            System.out.println("Keyword: \"" + key + "\" from policy: " + policy);
        }
    }

    @Override
    public void removeTalkingPointSetUpdate(Set<String> data, String policyName, String recentTalkPoint)
    {
        this.allTalk = data;
    }

    @Override
    public void talkingPointMapUpdate(Map<String, Set<String>> data)
    {
        this.policyWithTalk = data;
    }

    @Override
    public void removeTalkingPointMapUpdate(Map<String, Set<String>> data)
    {
        this.policyWithTalk = data;
    }
}
