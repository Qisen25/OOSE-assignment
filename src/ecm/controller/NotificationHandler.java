
package ecm.controller;

import ecm.view.TwitterMessenger;
import ecm.view.SMS;
import ecm.view.FacebookMessenger;
import ecm.model.Candidate;
import ecm.model.Group;
import ecm.model.KeywordObserver;
import ecm.model.Member;
import ecm.model.NotificationConfig;
import ecm.model.PolicyAreas;
import ecm.model.Strategist;
import ecm.model.TalkingPointObserver;
import ecm.model.Volunteer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
/**
 *
 * @author beepbeep
 */
public class NotificationHandler implements KeywordObserver, TalkingPointObserver
{
    private PolicyAreas pAreas;
    private Group grp;
    private SMS sms;
    private TwitterMessenger tMsg;
    private FacebookMessenger fbMsg;
    private NotificationConfig notCfg;
    private Map<String, Set<String>> keywordMap;
    private Map<String, Set<String>> talkMap;
    private Map<String, Integer> twitTrends;
    private Map<String, Integer> fbTrends;
    private Map<String, Integer> socialMediaTrends;
    private Map<String, Long> searchTimes;
    private Map<String, Boolean> holdOffNotif;
    private Map<String, Integer> initialTrend;
    private long firstScanMins;

    public NotificationHandler(PolicyAreas pAreas, Group grp, SMS sms, TwitterMessenger tMsg, 
                                FacebookMessenger fbMsg)
    {
        this.pAreas = pAreas;
        this.grp = grp;
        this.sms = sms;
        this.tMsg = tMsg;
        this.fbMsg = fbMsg;
        this.notCfg = NotificationConfig.getInstance();
        this.socialMediaTrends = new HashMap<String, Integer>();
        this.twitTrends = new HashMap<String, Integer>();
        this.fbTrends = new HashMap<String, Integer>();
        this.searchTimes = new HashMap<String, Long>();
        this.holdOffNotif = new HashMap<String, Boolean>();
        this.initialTrend = new HashMap<String, Integer>();
        this.firstScanMins = 0;
    }
            
    @Override
    public void subscribe()
    {
        this.pAreas.addKWObserver(this);
        this.pAreas.addTPObserver(this);
    }

    @Override
    public void unsubscribe()
    {
        this.pAreas.removeKWObserver(this);
        this.pAreas.removeTPObserver(this);
    }

    @Override
    public void keywordSetUpdate(Set<String> data, String policyName, String keyword)
    {        
        for(Member m : grp.getMembers())
        {
            if(m instanceof Volunteer || this.userWhiteListCheck(m.getId(), policyName))
            {
                if(m.getMobileNum() != 0)
                {
                    sms.sendSMS(m.getMobileNum(), "Keyword added: " + keyword);
                }
                if(!m.getTwitterID().isEmpty())
                {
                    tMsg.sendPrivateMessage(m.getTwitterID(), "Keyword added: " + keyword);
                }
                if(!m.getFacebookID().isEmpty())
                {
                    fbMsg.sendPrivateMessage(m.getFacebookID(), "Keyword added: " + keyword);
                }
            }
        }
    }

    @Override
    public void removeKeywordSetUpdate(Set<String> data, String policyName, String recentKeyword)
    {
        for(Member m : grp.getMembers())
        {
            if(userWhiteListCheck(m.getId(), policyName))
            {
                if(m.getMobileNum() != 0)
                {
                    sms.sendSMS(m.getMobileNum(), "Keyword remove: " + recentKeyword);
                }
                if(!m.getTwitterID().isEmpty())
                {
                    tMsg.sendPrivateMessage(m.getTwitterID(), "Keyword remove: " + recentKeyword);
                }
                if(!m.getFacebookID().isEmpty())
                {
                    fbMsg.sendPrivateMessage(m.getFacebookID(), "Keyword remove: " + recentKeyword);
                }
            }
        }
    }

    @Override
    public void talkingPointSetUpdate(Set<String> data, String policyName, String recentTalk)
    {
        for(Member m : grp.getMembers())
        {
            if(m.getMobileNum() != 0)
            {
                sms.sendSMS(m.getMobileNum(), "Talking point added: " + recentTalk);
            }
            if(!m.getTwitterID().isEmpty())
            {
                tMsg.sendPrivateMessage(m.getTwitterID(), "Talking point added: " + recentTalk);
            }
            if(!m.getFacebookID().isEmpty())
            {
                fbMsg.sendPrivateMessage(m.getFacebookID(), "Talking point added: " + recentTalk);
            }
        }    
    }
    
    @Override
    public void removeTalkingPointSetUpdate(Set<String> data, String policyName, String recentTalk)
    {   
        for(Member m : grp.getMembers())
        {
            if(userWhiteListCheck(m.getId(), policyName))
            {
                if(m.getMobileNum() != 0)
                {
                    sms.sendSMS(m.getMobileNum(), "Talking point removed: " + recentTalk);
                }
                if(!m.getTwitterID().isEmpty())
                {
                    tMsg.sendPrivateMessage(m.getTwitterID(), "Talking point removed: " + recentTalk);
                }
                if(!m.getFacebookID().isEmpty())
                {
                    fbMsg.sendPrivateMessage(m.getFacebookID(), "Talking point removed: " + recentTalk);
                }
            }
        }    
    }
  
    public void notifyTrend(long scheduleMins)
    {
        this.mergeTrends();
        
        if(firstScanMins == 0)
            this.firstScanMins = scheduleMins;
        
        for(Map.Entry<String, Integer> entry : this.socialMediaTrends.entrySet())
        {   
            //set default for search map
            if(!this.searchTimes.containsKey(entry.getKey()))
                this.searchTimes.put(entry.getKey(), scheduleMins);
            
            //set the hold off notification map default
            if(!this.holdOffNotif.containsKey(entry.getKey()))
                this.holdOffNotif.put(entry.getKey(), false);
             
            System.out.println(timeSinceSearch(entry.getKey()));
            if(this.isTrending(entry.getKey()) && timeSinceSearch(entry.getKey()) <= 60)//only trend if greater than 50 and within 1hr 
                                                                                                 // since first search
            {
                for(String policy : this.trendRelatedPolicy(entry.getKey()))//find policies with related keywords
                {
                    for(Member m : grp.getMembers())
                    {                   
                        if(m instanceof Volunteer || m instanceof Strategist || userWhiteListCheck(m.getId(), policy))
                        {
                            if(m.getMobileNum() != 0)
                            {
                                sms.sendSMS(m.getMobileNum(), "Keyword \"" + entry.getKey() + "\" trending in " + policy);
                            }
                            if(!m.getTwitterID().isEmpty())
                            {
                                tMsg.sendPrivateMessage(m.getTwitterID(), "Keyword \"" + entry.getKey() + "\" trending in " + policy);
                            }
                            if(!m.getFacebookID().isEmpty())
                            {
                                fbMsg.sendPrivateMessage(m.getFacebookID(), "Keyword \"" + entry.getKey() + "\" trending in " + policy);
                            }
                        }
                    } 
                }
                
                this.initialTrend.put(entry.getKey(), entry.getValue());
            }
            else if(this.isTrending(entry.getKey()) && timeSinceSearch(entry.getKey()) > 60)
            {    
                this.searchTimes.put(entry.getKey(), scheduleMins);// store trending keyword and time if 1 hour period reached  
                this.holdOffNotif.put(entry.getKey(), true);// set keyword to hold off
            }
        }
    }
    
    public boolean isTrending(String keyword)
    {
        if(!this.initialTrend.containsKey(keyword))
            this.initialTrend.put(keyword, 0);//add to map that keeps track of keyword previous trend number
        
        checkAfterHoldPeriod(keyword);
        
        boolean trending = this.socialMediaTrends.get(keyword) - this.initialTrend.get(keyword) >= 50;
        
        return trending && !this.holdOffNotif.get(keyword);
    }
    
    /**
     *function to measure time since previous search and current search
     * @param keyword
     * @return
     */
    public long timeSinceSearch(String keyword)
    {
       return (System.currentTimeMillis() / 60000) - this.searchTimes.get(keyword);
    }
    
    public void setTwitterTrend(Map<String, Integer> data)
    {
        this.twitTrends = data;
    }
    
    public void setFacebookTrend(Map<String, Integer> data)
    {
        this.fbTrends = data;
    }
    
    public void mergeTrends()
    {
        this.socialMediaTrends.putAll(twitTrends);
        
        for(Map.Entry<String, Integer> entry : fbTrends.entrySet())
        {
            socialMediaTrends.put(entry.getKey(), socialMediaTrends.get(entry.getKey()) + entry.getValue());
        }
    }
    
    public void addUsrSetting(Integer id, String policy)
    {
        notCfg.addPersonAndPolicy(id, policy);
    }
    
    public void removeUsrSetting(Integer id)
    {
        notCfg.removePersonAndPolicy(id);
    }
    
    public void removeUsrSettingByPolicy(String policy)
    {
        notCfg.removeByPolicy(policy);
    }
    
    /**
     * if a user is allowed to receive any notification from selected policy
     * this is found and set up from user via NotificatonConfig
     * @param id
     * @param policyName
     * @return boolean
     */
    public boolean userWhiteListCheck(int id, String policyName)
    {
        boolean inList = false;
        
        Map<Integer, String> personAndPolicy = this.notCfg.getPersonAndPolicy();
        
        for(Map.Entry<Integer, String> pp : personAndPolicy.entrySet())
        {
            inList = pp.getKey().equals(id) && pp.getValue().equals(policyName);
            
            if(inList)
            {
                break;
            }
        }
        
        return inList;
    }
    
    public Set<String> trendRelatedPolicy(String trendKey)
    {
        Set<String> trendPolicies = new HashSet<String>();
        
        for(Map.Entry<String, Set<String>> keywRelatePolicy : pAreas.getPolicyWithKeywords().entrySet())
        {
            for(String str : keywRelatePolicy.getValue())
            {
                if(str.equals(trendKey))
                    trendPolicies.add(keywRelatePolicy.getKey());
            }
        }
        
        return trendPolicies;
    }
    
    /**
     * check if 24 hours has passed if it has passed set hold of period
     * to false if keyword is in holdOff map
     * @param keyword
     * @param currentSchedMins
     * @return long telling whether to notify or not
     */
    public void checkAfterHoldPeriod(String keyword)
    {
        boolean notifyOrNot;
        
        long firstSearch = this.searchTimes.get(keyword);
        long currentSchedMins = System.currentTimeMillis() / 60000;

        notifyOrNot = (currentSchedMins - firstSearch >= 1440);

        if(notifyOrNot) // if more than 24 hours has passed then set the update last search time
        {
            this.holdOffNotif.put(keyword, false);//, currentSchedMins);
        }
                    
    }
    
    public boolean keywordOnHold(String keyword)
    {
        if(!this.holdOffNotif.isEmpty())
        {
            return this.holdOffNotif.get(keyword);
        }
        
        return false;
    }
    
    public Map<Integer, String> getUsrConfig()
    {
        return this.notCfg.getPersonAndPolicy();   
    }

    public Map<String, Long> getHoldOffNotify()
    {
        return searchTimes;
    }

    @Override
    public void keywordMapUpdate(Map<String, Set<String>> data)
    {
        this.keywordMap = data;
    }

    @Override
    public void removeKeywordMapUpdate(Map<String, Set<String>> data)
    {
        this.keywordMap = data;
    }

    @Override
    public void talkingPointMapUpdate(Map<String, Set<String>> data)
    {
        this.talkMap = data;
    }

    @Override
    public void removeTalkingPointMapUpdate(Map<String, Set<String>> data)
    {
        this.talkMap = data;
    }
    
    public void clearUsrConfig()
    {
        this.notCfg.clearSettings();
    }
}
