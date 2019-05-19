package ecm.view;

import java.util.*;

public abstract class FacebookMessenger
{
    protected Set<String> keywords;

    public FacebookMessenger()
    {
        this.keywords = null;
    }

    public void sendPrivateMessage(String id, String message)
    {
        System.out.println("Facebook private message sent to " + id + ": " + message);
    }

    public void setKeywords(Set<String> keywords)
    {
        this.keywords = keywords;
    }

    protected abstract void keywordsDetected(Map<String, Integer> keywords,
                                             long timestamp);
}
