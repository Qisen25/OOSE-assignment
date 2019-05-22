package ecm.view;

import ecm.model.KeywordObserver;
import ecm.model.PolicyAreas;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.TimerTask;

/**
 *
 * @author beepbeep
 */
public class TweetMonitor extends TimerTask implements KeywordObserver
{
    private PolicyAreas pArea;
    private TwitterMessenger twitter;
    private LinkedList<String> post;
    private Map<String, Integer> kMap;

    public TweetMonitor(TwitterMessenger twitter, PolicyAreas pArea)
    {
        this.pArea = pArea;
        this.twitter = twitter;
        this.post = new LinkedList<String>();
        this.kMap = ((TwitterPostScout)this.twitter).keyMap;
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
        
        this.twitter.keywordsDetected(kMap, System.currentTimeMillis() / 1000);
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
        this.twitter.setKeywords(data);
    }

    @Override
    public void removeKeywordSetUpdate(Set<String> data, String policy, String keyword)
    {
        this.twitter.setKeywords(data);
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
