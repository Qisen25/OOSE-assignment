package ecm.view;

import java.util.*;

//Given template class
public abstract class TwitterMessenger
{
    public TwitterMessenger()
    {
    }

    public void sendPrivateMessage(String id, String message)
    {
        System.out.println("Twitter private message sent to " + id + ": \"" + message + "\"");
    }

    public void setKeywords(Set<String> keywords)
    {
        PostMonitor mon = PostMonitor.getInstance();
        mon.setSearchTwitterMap(keywords);
        mon.connectTwitter(this);
    }

    protected abstract void keywordsDetected(Map<String, Integer> keywords,
                                             long timestamp);
}
