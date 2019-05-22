package ecm.view;

import java.util.*;

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
    }

    protected abstract void keywordsDetected(Map<String, Integer> keywords,
                                             long timestamp);
}
