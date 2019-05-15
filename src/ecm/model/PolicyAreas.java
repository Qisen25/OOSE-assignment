package ecm.model;

import java.util.*;
/**
 *
 * @author beepbeep
 */
public class PolicyAreas implements Policy
{
    private List<Policy> policies;
    private List<PolicyObserver> observers;

    public PolicyAreas()
    {
        this.policies = new ArrayList<Policy>();
        this.observers = new ArrayList<PolicyObserver>();
    }
    
    public void addObserver(PolicyObserver obs)
    {
        observers.add(obs);
        notifyObservers();
    }
    
    public void removeObserver(TextObserver obs)
    {
        observers.remove(obs);
        notifyObservers();
    }
    
    public void notifyObservers()
    {
        for(PolicyObserver ob : observers)
        {
            ob.dataUpdate(this.policies);
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
        }
       
    }

    @Override
    public void addKeyword(String pName, String keyword) throws PolicyNotFoundException
    {
       PolicyEntry policy = this.find(pName); 
       if(policy != null)
       {
          policy.addKeyword(pName, keyword); 
       } 
       else
       {
           throw new PolicyNotFoundException("Policy does not exist: " + pName);
       }
    }

    @Override
    public void addTalkingPoint(String pName, String talkPoint) throws PolicyNotFoundException
    {
       PolicyEntry policy = this.find(pName); 
       if(policy != null)
       {
           policy.addTalkingPoint(pName, talkPoint); 
       }
       else
       {
           throw new PolicyNotFoundException("Policy does not exist: " + pName);
       }
    }
    
    @Override
    public void removeKeyword(String keyword)
    {
       for(Policy p : policies)
       {
          p.removeKeyword(keyword); 
       }    
    }

    @Override
    public void removeTalkingPoint(String talkPoint)
    {
       for(Policy p : policies)
       {
           p.removeTalkingPoint(talkPoint); 
       }
    }
    
    public Set<String> getPolicyKeywords(String pName)
    {
        PolicyEntry policy = this.find(pName);
        if(policy != null)
        {
            return policy.getKeywords();
        }
        
        return null;
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
    
    @Override
    public void printKey()
    {
        for(Policy pol : policies)
        {
            pol.printKey();
        }
    }

    @Override
    public void printTalk()
    {
        for(Policy pol : policies)
        {
            pol.printTalk();
        }
    }
}
