package ecm.view;

import java.util.Set;
import java.util.HashSet;
import ecm.model.PolicyAreas;
import ecm.model.KeywordObserver;
import java.util.HashMap;
import java.util.Map;
/**
 * class to view keywords
 * @author Kei Sum Wang 19126089
 */
public class KeywordViewer implements KeywordObserver, Viewer
{
    private PolicyAreas pArea;
    private Set<String> allKeywords;
    private Map<String, Set<String>> policyWithKeyw;//map holding policies with set of keywords

    public KeywordViewer(PolicyAreas pArea)
    {
        this.pArea = pArea;
        this.allKeywords = new HashSet<String>();
        this.policyWithKeyw = new HashMap<String, Set<String>>();
    }   

    @Override
    public void subscribe()
    {
        pArea.addKWObserver(this);
    }

    @Override
    public void unsubscribe()
    {
        pArea.removeKWObserver(this);
    }
        
    @Override
    public void keywordSetUpdate(Set<String> data, String policyName, String eyword)
    {
        this.allKeywords = data;
    }

    @Override
    public void display()
    {
        System.out.println("++Keywords found in system++");
        this.displayMap();
    }

    public void displaySet()
    {
        for(String str : allKeywords)
        {
            System.out.println(str);
        }
    }
    
    public void displayMap()
    {       
        for(Map.Entry<String, Set<String>> ent : this.policyWithKeyw.entrySet())
        {
            for(String key : ent.getValue())
            {
                System.out.println("Keyword: \"" + key + "\" from policy: " + ent.getKey());
            }
        }
    }
    
    public void displayMapGivenPolicy(String policy)
    { 
        if(!this.policyWithKeyw.isEmpty())
        {
            for(String key : this.policyWithKeyw.get(policy))
            {
                System.out.println("Keyword: \"" + key + "\" from policy: " + policy);
            }
        }
    }

    @Override
    public void removeKeywordSetUpdate(Set<String> data, String policy, String keyword)
    {
        this.allKeywords = data;
    }

    @Override
    public void keywordMapUpdate(Map<String, Set<String>> data)
    {
        this.policyWithKeyw = data;
    }

    @Override
    public void removeKeywordMapUpdate(Map<String, Set<String>> data)
    {
        this.policyWithKeyw = data;
    }

}
