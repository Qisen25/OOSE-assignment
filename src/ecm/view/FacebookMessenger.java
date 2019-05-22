package ecm.view;

import java.util.*;
//TODO REMEMBER TO to notify user of keywords and talkpoints when removing policy
public abstract class FacebookMessenger
{
    //constructor could also be template   
    public FacebookMessenger()
    {
    }

    //template method
    public void sendPrivateMessage(String id, String message)
    {
        System.out.println("Facebook private message sent to " + id + ": \"" + message + "\"");
    }

    
    //template method this most likely to call hook
    public void setKeywords(Set<String> keywords)
    {
        Map<String, Integer> keyMap = new HashMap<String, Integer>();
                
        for(String key : keywords)
        {
            keyMap.putIfAbsent(key, 0);
        }
        
        keywordsDetected(keyMap, System.currentTimeMillis()/1000);
    }

    //#hook method
    protected abstract void keywordsDetected(Map<String, Integer> keywords,
                                             long timestamp);
}
