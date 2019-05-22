package ecm.view;

import ecm.model.KeywordObserver;
import ecm.model.PolicyAreas;
import ecm.view.FacebookMessenger;
import ecm.view.TwitterMessenger;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimerTask;


/**
 *
 * @author beepbeep
 */
public class FBPostMonitor extends TimerTask implements KeywordObserver
{
    private PolicyAreas pArea;
    private TwitterMessenger twitter;
    private FacebookMessenger facebook;
    private LinkedList<String> post;
    private Map<String, Integer> kMap;

    public FBPostMonitor(FacebookMessenger facebook, PolicyAreas pArea)
    {
        this.pArea = pArea;
        this.facebook = facebook;
        this.post = new LinkedList<String>();
        this.kMap  = ((FacebookPostScout)this.facebook).keyMap;
    }
    
    public void setPost()
    {
        for(int i = 0; i < 10; i++)
        {
            this.post.add("empire is here");
        }
    }

    @Override
    public void run()
    {        
        String keypost = this.post.remove();
        
        for(Map.Entry<String, Integer> entry : kMap.entrySet())
        {
            if(keypost.contains(entry.getKey()))
            {
                int count = entry.getValue();
                kMap.put(entry.getKey(), count + 1);
            }
        }
        
        this.facebook.keywordsDetected(kMap, System.currentTimeMillis() / 1000);
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
        this.facebook.setKeywords(data);
    }

    @Override
    public void removeKeywordSetUpdate(Set<String> data, String policy, String keyword)
    {
        this.facebook.setKeywords(data);
    }

    @Override
    public void keywordMapUpdate(Map<String, Set<String>> data)
    {
  
    }

    @Override
    public void removeKeywordMapUpdate(Map<String, Set<String>> data)
    {
        
    }
    
}
