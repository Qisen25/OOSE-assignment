package ecm.controller;

import ecm.model.DuplicateException;
import ecm.view.KeywordViewer;
import ecm.model.Policy;
import ecm.model.PolicyAreas;
import ecm.model.PolicyEntry;
import ecm.model.PolicyNotFoundException;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 *
 * @author beepbeep
 */
public class PolicyAreaController
{
    private PolicyFactory maker;
    private PolicyAreas pAreas;

    public PolicyAreaController(PolicyFactory maker, PolicyAreas pAreas)
    {
        this.maker = maker;
        this.pAreas = pAreas;
    }
    
    public void addPolicy(String policyName)
    {
        pAreas.addPolicy(maker.makePolicy(policyName));
    }
    
    public void removePolicy(String policyName)
    {
        pAreas.removePolicy(policyName);
    }
    
    public void addKeyword(String policyName, String key) throws PolicyNotFoundException, DuplicateException
    {
        pAreas.addKeyword(policyName, key);
    }
    
    public void addTalkingPoint(String policyName, String key) throws PolicyNotFoundException, DuplicateException
    {
        pAreas.addTalkingPoint(policyName, key);
    }
    
    public void removeKeyword(String policyName, String key) throws PolicyNotFoundException, NoSuchElementException
    {
        pAreas.removeKeyword(policyName, key);
    }
    
    public void removeTalkingPoint(String policyName, String key) throws PolicyNotFoundException, NoSuchElementException
    {
        pAreas.removeTalkingPoint(policyName, key);
    }
    
    public void find(String polName) throws PolicyNotFoundException
    {
        if(pAreas.find(polName) == null)
        {
            throw new PolicyNotFoundException("Policy " + polName + " not found");
        }
    }
    
    public void loadPolicies(EcmIO source) throws InvalidMemberRoleException
    {
        pAreas.clearPolicies();
        
        String policies[] = source.fetchPolicy().split("\n");
        
        for(String details : policies)
        {
            addPolicy(details);
        }
    }
    
    public void loadKeywords(EcmIO source) throws InvalidMemberRoleException, PolicyNotFoundException, DuplicateException
    {
        String keywords[] = source.fetchKeywords().split("\n");
        
        for(String keyw : keywords)
        {
            String keywordDetails[] = keyw.split(":");
            addKeyword(keywordDetails[0], keywordDetails[1]);
        }
    }
    
        
    public void loadTalkingPoints(EcmIO source) throws InvalidMemberRoleException, PolicyNotFoundException, DuplicateException
    {       
        String talks[] = source.fetchTalkingPoints().split("\n");
        
        for(String talk : talks)
        {
            String talkDetails[] = talk.split(":");
            addTalkingPoint(talkDetails[0], talkDetails[1]);
        }
    }
}
