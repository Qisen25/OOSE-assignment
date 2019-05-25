package ecm.model;

import java.util.*;
/**
 * policy area class the composite node
 * @author Kei Sum Wang 19126089
 */
public class PolicyAreas implements Policy
{
    private List<Policy> policies;
    private List<KeywordObserver> keywordObs;
    private List<TalkingPointObserver> talkObs;

    public PolicyAreas()
    {
        this.policies = new ArrayList<Policy>();
        this.keywordObs = new ArrayList<KeywordObserver>();
        this.talkObs = new ArrayList<TalkingPointObserver>();
    }
    
    public void addKWObserver(KeywordObserver obs)
    {
        keywordObs.add(obs);
    }
    
    public void removeKWObserver(KeywordObserver obs)
    {
        keywordObs.remove(obs);
    }
    
    public void notifyKWObservers(String relatedPolicy, String recentKeyword)
    {
        for(KeywordObserver ob : keywordObs)
        {
            ob.keywordSetUpdate(this.getKeywords(), relatedPolicy, recentKeyword);
            ob.keywordMapUpdate(this.getPolicyWithKeywords());
        }
    }
    
    public void notifyKWRemoval(String relatedPolicy, String recentKeyword)
    {
        for(KeywordObserver ob : keywordObs)
        {
            ob.removeKeywordSetUpdate(this.getKeywords(), relatedPolicy, recentKeyword);
            ob.removeKeywordMapUpdate(this.getPolicyWithKeywords());
        }
    }
    
    public void addTPObserver(TalkingPointObserver obs)
    {
        talkObs.add(obs);
    }
    
    public void removeTPObserver(TalkingPointObserver obs)
    {
        talkObs.remove(obs);
    }
    
    public void notifyTPObservers(String relatedPolicy, String recentTalkPoint)
    {
        for(TalkingPointObserver ob : talkObs)
        {
            ob.talkingPointSetUpdate(this.getTalkPoints(), relatedPolicy, recentTalkPoint);
            ob.talkingPointMapUpdate(this.getPolicyWithTalk());
        }
    }
    
    public void notifyTPRemoval(String relatedPolicy, String recentTalkPoint)
    {
        for(TalkingPointObserver ob : talkObs)
        {
            ob.removeTalkingPointSetUpdate(this.getTalkPoints(), relatedPolicy, recentTalkPoint);
            ob.removeTalkingPointMapUpdate(this.getPolicyWithTalk());
        }
    }
    
    @Override
    public PolicyEntry find(String name)
    {
       for(Policy p : policies)
       {
           PolicyEntry thePolicy = p.find(name);
           if(thePolicy != null)
           {
               return thePolicy;
           }
       }
       
       return null;
    }

    public void addPolicy(Policy pol)
    {
        this.policies.add(pol);
    }
    
    public void removePolicy(String name)
    {
        PolicyEntry policy = this.find(name);
        
        if(policy != null)
        {
            this.policies.remove(policy);
            System.out.println("remove policy success");
            this.notifyKWRemoval(name, "All Keywords removed");
            this.notifyTPRemoval(name, "All Talking points remove");
        }
       
    }

    @Override
    public void addKeyword(String pName, String keyword) throws PolicyNotFoundException, DuplicateException
    {
       PolicyEntry policy = this.find(pName); 
       if(policy != null)
       {
          policy.addKeyword(pName, keyword);
          this.notifyKWObservers(pName, keyword);
       } 
       else
       {
           throw new PolicyNotFoundException("Policy does not exist: " + pName);
       }
    }

    @Override
    public void addTalkingPoint(String pName, String talkPoint) throws PolicyNotFoundException, DuplicateException
    {
       PolicyEntry policy = this.find(pName); 
       if(policy != null)
       {
           policy.addTalkingPoint(pName, talkPoint); 
           this.notifyTPObservers(pName, talkPoint);
       }
       else
       {
           throw new PolicyNotFoundException("Policy does not exist: " + pName);
       }
    }
    
    @Override
    public void removeKeyword(String polName, String keyword) throws PolicyNotFoundException, NoSuchElementException
    {
        Policy pol = this.find(polName);
        
        if(pol == null)
            throw new PolicyNotFoundException("Policy " + polName + " not found");
        
        pol.removeKeyword(polName, keyword);
        
        this.notifyKWRemoval(polName, keyword);
    }

    @Override
    public void removeTalkingPoint(String polName, String talkPoint) throws PolicyNotFoundException, NoSuchElementException
    {
        Policy pol = this.find(polName);
        
        if(pol == null)
            throw new PolicyNotFoundException("Policy " + polName + " not found");
        
        pol.removeTalkingPoint(polName, talkPoint); 
        
        this.notifyTPRemoval(polName, talkPoint);
    }
    
    public Map<String, Set<String>> getPolicyWithKeywords()
    {
        Map<String, Set<String>> policyKeyMap = new HashMap<String, Set<String>>();
        
        for(Policy policy : this.policies)
        {
            PolicyEntry pol = (PolicyEntry)policy;
            policyKeyMap.put(pol.getName(), pol.getKeywords());
        }
        
        return policyKeyMap;
    }
    
    public Map<String, Set<String>> getPolicyWithTalk()
    {
        Map<String, Set<String>> policyKeyMap = new HashMap<String, Set<String>>();
        
        for(Policy policy : this.policies)
        {
            PolicyEntry pol = (PolicyEntry)policy;
            policyKeyMap.put(pol.getName(), pol.getTalkPoints());
        }
        
        return policyKeyMap;
    }

    public List<Policy> getPolicies()
    {
        return policies;
    }

    @Override
    public Set<String> getKeywords()
    {
        Set<String> allKeywords = new HashSet<String>();
        
        for(Policy p : policies)
        {
            allKeywords.addAll(p.getKeywords());
        }
        
        return allKeywords;
    }

    @Override
    public Set<String> getTalkPoints()
    {
        Set<String> allTalkpoints = new HashSet<String>();
        
        for(Policy p : policies)
        {
            allTalkpoints.addAll(p.getTalkPoints());
        }
        
        return allTalkpoints;        
    }
    
    public void clearPolicies()
    {
        policies.clear();
    }
}