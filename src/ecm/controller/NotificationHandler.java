
package ecm.controller;

import ecm.model.Group;
import ecm.model.KeywordObserver;
import ecm.model.Member;
import ecm.model.NotificationConfig;
import ecm.model.PolicyAreas;
import ecm.model.TalkingPointObserver;
import ecm.model.Volunteer;
import ecm.view.FacebookMessenger;
import ecm.view.SMS;
import ecm.view.TwitterMessenger;
import java.util.ArrayList;
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

    public NotificationHandler(PolicyAreas pAreas, Group grp, SMS sms, TwitterMessenger tMsg, 
                                FacebookMessenger fbMsg)
    {
        this.pAreas = pAreas;
        this.grp = grp;
        this.sms = sms;
        this.tMsg = tMsg;
        this.fbMsg = fbMsg;
        this.notCfg = NotificationConfig.getInstance();
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
            //System.out.println("Notifying");
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
    
    public Map<Integer, String> getUsrConfig()
    {
        return this.notCfg.getPersonAndPolicy();   
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
