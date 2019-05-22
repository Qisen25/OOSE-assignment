package ecm.view;

import ecm.controller.NotificationHandler;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author beepbeep
 */
public class TwitterPostScout extends TwitterMessenger
{
    protected Map<String, Integer> keyMap;
    private LinkedList<String> tpost;
    private NotificationHandler notifHand;
    
    public TwitterPostScout()
    {
        super();
        this.keyMap = new HashMap<String, Integer>();
        this.tpost = new LinkedList<String>();
        this.setTwitPost();
    }
    
    @Override
    public void setKeywords(Set<String> keywords)
    {    
        for(String key : keywords)
        {
            this.keyMap.put(key, 0);
        }
    }
    
    @Override
    protected void keywordsDetected(Map<String, Integer> keywords, long timestamp)
    {
        if(tpost.isEmpty() || keyMap.isEmpty())
            System.exit(0);
        
        String tweet = this.tpost.remove();
        
        for(Map.Entry<String, Integer> entry : keywords.entrySet())
        {
            if(tweet.contains(entry.getKey()))
            {
                int count = entry.getValue();
                keywords.put(entry.getKey(), count + 1);
            }
            System.out.println("keyword: \"" + entry.getKey() + "\" detected on Twitter " 
                                + entry.getValue() + " times at " + timestamp + " sec") ;
        }  
        
        this.notifHand.setTwitterTrend(keywords);               
    } 
    
    public void setTwitPost()
    {
        for(int i = 0; i < 10; i++)
        {
            this.tpost.add("empire is here");
        }
    }
    
    public void setNotificationHandler(NotificationHandler not)
    {
        this.notifHand = not;
    }
}
