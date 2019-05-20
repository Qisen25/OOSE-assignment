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

    private Map<Integer, String> personAndPolicy;
    
    
    private NotificationConfig()
    {
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
    
    public void clearSettings()
    {
        personAndPolicy.clear();
    }
}
