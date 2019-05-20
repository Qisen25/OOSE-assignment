package ecm.model;

import java.util.*;
/**
 *
 * @author beepbeep
 */
public class NotificationConfig
{
    private static NotificationConfig instance = null;
    
    public static NotificationConfig getInstance()
    {
        if(instance == null)
        {
            instance = new NotificationConfig();
        }
        return instance;
    }

    private String whenAddKeyword;
    private String whenKeywordTrends;
    private String whenTalkPointAdd;
    private Map<Integer, String> personAndPolicy;
    
    
    private NotificationConfig()
    {
        this.whenAddKeyword = "volunteer";
        this.whenKeywordTrends = "volunteer and candidates";
        this.whenTalkPointAdd = "Everyone";
        this.personAndPolicy = new HashMap<Integer, String>();
    }
    
    public void addPersonAndPolicy(int id, String policy)
    {
        personAndPolicy.put(id, policy);
    }
    
    public void removePersonAndPolicy(int id)
    {
        personAndPolicy.remove(id);
    }
    
    public void removeByPolicy(String policy)
    {
        for(Map.Entry<Integer, String> usrcfg : personAndPolicy.entrySet())
        {
            if(usrcfg.getValue().equalsIgnoreCase(policy))
            {
                personAndPolicy.remove(usrcfg.getKey());
            }
        }
    }

    public Map<Integer, String> getPersonAndPolicy()
    {
        return this.personAndPolicy;
    }

    public String getWhenAddKeyword()
    {
        return whenAddKeyword;
    }

    public String getWhenKeywordTrends()
    {
        return whenKeywordTrends;
    }

    public String getWhenTalkPointAdd()
    {
        return whenTalkPointAdd;
    }

    public void setWhenAddKeyword(String whenAddKeyword)
    {
        this.whenAddKeyword = whenAddKeyword;
    }

    public void setWhenKeywordTrends(String whenKeywordTrends)
    {
        this.whenKeywordTrends = whenKeywordTrends;
    }

    public void setWhenTalkPointAdd(String whenTalkPointAdd)
    {
        this.whenTalkPointAdd = whenTalkPointAdd;
    }
}
