package ecm.view;

import ecm.controller.NotificationHandler;
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
    private LinkedList<String> fpost;
    private Map<String, Integer> kMap;
    private NotificationHandler notifHand;

    public FBPostMonitor(FacebookMessenger facebook, PolicyAreas pArea, NotificationHandler notifHand)
    {
        this.pArea = pArea;
        this.facebook = facebook;
        this.fpost = new LinkedList<String>();
        this.kMap  = ((FacebookPostScout)this.facebook).keyMap;
        this.notifHand = notifHand;
    }
        
    public void setFBPost()
    {
        for(int i = 0; i < 10; i++)
        {
            this.fpost.add("wage increase");
        }
    }

    @Override
    public void run()
    {        
//        String keypost = this.post.remove();
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
        this.facebook.keywordsDetected(kMap, System.currentTimeMillis() / 1000);

        this.notifHand.notifyTrend();
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
    
    public void setNotificationHandler()
    {
        ((FacebookPostScout)facebook).setNotificationHandler(this.notifHand);
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
