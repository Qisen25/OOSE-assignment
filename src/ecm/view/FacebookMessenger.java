package ecm.view;

import java.util.*;
//given template class
public abstract class FacebookMessenger
{ 
    public FacebookMessenger()
    {
    }

    public void sendPrivateMessage(String id, String message)
    {
        System.out.println("Facebook private message sent to " + id + ": \"" + message + "\"");
    }

    public void setKeywords(Set<String> keywords)
    {
        PostMonitor mon = PostMonitor.getInstance();
        mon.setSearchFBMap(keywords);
        mon.connectFB(this);
    }

    //#hook method
    protected abstract void keywordsDetected(Map<String, Integer> keywords,
                                             long timestamp);
}
