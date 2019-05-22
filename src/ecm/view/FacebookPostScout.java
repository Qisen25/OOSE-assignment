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
public class FacebookPostScout extends FacebookMessenger
{
    protected Map<String, Integer> keyMap;
    private LinkedList<String> fpost;
    private NotificationHandler notifHand;

    //should set up set fo strings here
    public FacebookPostScout()
    {
        super();
        this.keyMap = new HashMap<String, Integer>();
        this.fpost = new LinkedList<String>();
        this.setFBPost();
    }
    
    @Override
    public void setKeywords(Set<String> keywords)
    {
        System.out.println("setting keywords for post scout");
        for(String key : keywords)
        {
            this.keyMap.put(key, 0);
        }
    }

    @Override
    protected void keywordsDetected(Map<String, Integer> keywords, long timestamp)
    { 
        if(fpost.isEmpty() || keyMap.isEmpty())
            System.exit(0);
                
        String fbpost = this.fpost.remove();
        
        for(Map.Entry<String, Integer> entry : keywords.entrySet())
        {
            if(fbpost.contains(entry.getKey()))
            {
                int count = entry.getValue();
                keyMap.put(entry.getKey(), count + 1);
            }
            System.out.println("keyword: \"" + entry.getKey() + "\" detected on Facebook " 
                                + entry.getValue() + " times at " + timestamp + " sec") ;
        }
        
        this.notifHand.setFacebookTrend(keyMap);
    }
    
    public void setFBPost()
    {
        for(int i = 0; i < 10; i++)
        {
            this.fpost.add("wage increase");
        }
    }
    
    public void setNotificationHandler(NotificationHandler not)
    {
        this.notifHand = not;
    }
}
