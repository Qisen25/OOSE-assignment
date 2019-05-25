package ecm.view;

import ecm.controller.NotificationHandler;
import ecm.controller.NotificationHandler;
import ecm.model.KeywordObserver;
import ecm.model.PolicyAreas;
import ecm.view.FacebookMessenger;
import ecm.controller.NotificationHandler;
import ecm.view.TwitterMessenger;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimerTask;


/**
 * class that is the monitor thread dealing keyword trend searches
 * @author Kei Sum Wang 19126089
 */
public class PostMonitor extends TimerTask
{
    private static PostMonitor monitor = null;
    
    public static PostMonitor getInstance()
    {
        if(monitor == null)
        {
            monitor = new PostMonitor();
        }
        
        return monitor;
    }
    
    private TwitterMessenger twitter;
    private FacebookMessenger facebook;
    private LinkedList<String> fpost;
    private LinkedList<String> tweets;
    private Map<String, Integer> fbMap;
    private Map<String, Integer> twitMap;
    private NotificationHandler notifHand;

    private PostMonitor()
    {
        this.fpost = new LinkedList<String>();
        this.tweets = new LinkedList<String>();
        this.fbMap = new HashMap<String, Integer>();
        this.twitMap = new HashMap<String, Integer>();
        //this.setFBPost();
    }
      
    //stub for posts
    public void setFBPost()
    {
        for(int i = 0; i < 500; i++)
        {
            this.fpost.add("wage increase");
        }
    }
    
    //stub for tweets
    public void setTweets()
    {
        for(int i = 0; i < 500; i++)
        {
            this.tweets.add("empire");
        }
    }

    @Override
    public void run()
    {   
        if(fpost.isEmpty())
            System.exit(0);
        
        this.searchFB();
        this.searchTwitter();
        
        this.twitter.keywordsDetected(this.twitMap, System.currentTimeMillis() / 1000);
        this.facebook.keywordsDetected(this.fbMap, System.currentTimeMillis() / 1000);        
        
        this.notifHand.setTwitterTrend(this.twitMap);
        this.notifHand.setFacebookTrend(this.fbMap);      

        this.notifHand.notifyTrend(System.currentTimeMillis() / 60000);
    }
    
    public void searchFB()
    {       
        String keypost = this.fpost.remove();
        
        for(Map.Entry<String, Integer> entry : fbMap.entrySet())
        {
            boolean canNotify = !this.notifHand.keywordOnHold(entry.getKey());
            //if post contains keyword and keyword is not marked hold off for user notification
            if(keypost.contains(entry.getKey()) && canNotify)
            {
                int count = entry.getValue();
                fbMap.put(entry.getKey(), count + 1);
            }
        } 
    }
    
    public void searchTwitter()
    {
        String keypost = this.tweets.remove();
        
        for(Map.Entry<String, Integer> entry : twitMap.entrySet())
        {
            boolean canNotify = !this.notifHand.keywordOnHold(entry.getKey());
            //if post contains keyword and keyword is not marked hold off for user notification
            if(keypost.contains(entry.getKey()) && canNotify)
            {
                int count = entry.getValue();
                twitMap.put(entry.getKey(), count + 1);
            }
        } 
    }

    public void connectFB(FacebookMessenger aThis)
    {
        this.facebook = aThis;
    }
    
    public void connectTwitter(TwitterMessenger aThis)
    {
        this.twitter = aThis;
    }
    
    public void connectNotification(NotificationHandler notifHand)
    {
        this.notifHand = notifHand;
    }

    void setSearchFBMap(Set<String> keys)
    {   
        this.fbMap.clear();
        
        for(String key : keys)
        {
            this.fbMap.put(key, 0);
        }
        
        this.fpost.clear();
        this.setFBPost();
    }   
    
    void setSearchTwitterMap(Set<String> keys)
    {    
        this.twitMap.clear();// clear current map to replace keywords to be searched
        
        for(String key : keys)
        {
            this.twitMap.put(key, 0);
        }
        
        this.tweets.clear();
        this.setTweets();
    } 
}
