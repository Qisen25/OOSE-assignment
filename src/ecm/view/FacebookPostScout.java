 package ecm.view;

import ecm.controller.NotificationHandler;
import ecm.model.KeywordObserver;
import ecm.model.PolicyAreas;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

/**
 * concrete class working with post monitor thread
 * @author Kei Sum Wang 19126089
 */
public class FacebookPostScout extends FacebookMessenger implements KeywordObserver
{
    private PolicyAreas pArea;
    
    public FacebookPostScout(PolicyAreas pArea)
    {
        super();
        this.pArea = pArea;
    }

    @Override
    protected void keywordsDetected(Map<String, Integer> keywords, long timestamp)
    {         
        for(Map.Entry<String, Integer> entry : keywords.entrySet())
        {
            System.out.println("keyword: \"" + entry.getKey() + "\" detected on Facebook " 
                                + entry.getValue() + " times at " + timestamp + " sec") ;
        }
        
        //this.notifHand.setFacebookTrend(keyMap);
    }

    @Override
    public void subscribe()
    {
        this.pArea.addKWObserver(this);
    }

    @Override
    public void unsubscribe()
    {
        this.pArea.removeKWObserver(this);
    }

    @Override
    public void keywordSetUpdate(Set<String> data, String recentPolicy, String keyword)
    {
        this.setKeywords(data);     
    }

    @Override
    public void removeKeywordSetUpdate(Set<String> data, String policy, String keyword)
    {
        this.setKeywords(data);
    }

    @Override
    public void keywordMapUpdate(Map<String, Set<String>> data)
    {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeKeywordMapUpdate(Map<String, Set<String>> data)
    {
        //hrow new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
