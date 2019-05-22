package ecm.view;

import ecm.controller.NotificationHandler;
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
    private NotificationHandler notifHand;
    private TwitterMessenger twitter;
    private FacebookMessenger facebook;
    private LinkedList<String> tpost;
    private LinkedList<String> fpost;
    private Map<String, Integer> kMap;

    public TweetMonitor(TwitterMessenger twitter, PolicyAreas pArea, NotificationHandler notifHand)
    {
        this.pArea = pArea;
        this.notifHand = notifHand;
        this.twitter = twitter;
        //this.facebook = facebook;
        this.tpost = new LinkedList<String>();
        this.fpost = new LinkedList<String>();
        this.kMap = ((TwitterPostScout)this.twitter).keyMap;
    }
    

    @Override
    public void run()
    {  
//        if(kMap.isEmpty() || tpost.isEmpty())
//            System.exit(0);
//                
//        String keypost = this.tpost.remove();
//        
//       
//        
//        for(Map.Entry<String, Integer> entry : kMap.entrySet())
//        {
//            if(keypost.contains(entry.getKey()))
//            {
//                int count = entry.getValue();
//                kMap.put(entry.getKey(), count + 1);
//            }
//        }
//        
       // this.notifHand.setTwitterTrend(kMap);
                
        this.twitter.keywordsDetected(kMap, System.currentTimeMillis() / 1000);
        this.notifHand.notifyTrend();
    }

    public void searchTweets()
    {               
        String keypost = this.tpost.remove();
     
        for(Map.Entry<String, Integer> entry : kMap.entrySet())
        {
            if(keypost.contains(entry.getKey()))
            {
                int count = entry.getValue();
                kMap.put(entry.getKey(), count + 1);
            }
        }        
    }
    
    public void searchFBPosts()
    {
        
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

    public void setNotificationHandler()
    {
        ((TwitterPostScout)twitter).setNotificationHandler(this.notifHand);
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
