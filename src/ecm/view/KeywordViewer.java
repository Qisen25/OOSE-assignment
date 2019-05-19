package ecm.view;

import java.util.Set;
import java.util.HashSet;
import ecm.model.PolicyAreas;
import ecm.model.KeywordObserver;
import java.util.HashMap;
import java.util.Map;
/**
 *
 * @author beepbeep
 */
public class KeywordViewer implements KeywordObserver
{
    private PolicyAreas pArea;
    private Set<String> allKeywords;
    private Map<String, Set<String>> policyWithKeyw;

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

    public void displaySet()
    {
        System.out.println("Keywords found in system");
        for(String str : allKeywords)
        {
            System.out.println(str);
        }
    }
    
    public void displayMap()
    {
        System.out.println("Keywords found in system");
        for(Map.Entry<String, Set<String>> ent : this.policyWithKeyw.entrySet())
        {
            for(String key : ent.getValue())
            {
                System.out.println("Keyword: \"" + key + "\" from policy: " + ent.getKey());
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
