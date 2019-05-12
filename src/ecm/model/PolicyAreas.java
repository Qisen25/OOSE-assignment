package ecm.model;

import java.util.*;
/**
 *
 * @author beepbeep
 */
public class PolicyAreas implements Policy
{
    private List<Policy> policies;

    public PolicyAreas()
    {
        this.policies = new ArrayList<Policy>();
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

    public void addPolicy(String name)
    {
        this.policies.add(new PolicyEntry(name));
    }
    
    public void removePolicy(String name)
    {
        PolicyEntry policy = this.find(name);
        
        if(policy != null)
        {
            this.policies.remove(policy);
            System.out.println("remove success");
        }
       
    }

    public void addKeyword(String pName, String keyword)
    {
       PolicyEntry policy = this.find(pName); 
       if(policy != null)
       {
          policy.addKeyword(pName, keyword); 
       }    
    }

    public void addTalkingPoint(String pName, String talkPoint)
    {
       PolicyEntry policy = this.find(pName); 
       if(policy != null)
       {
           policy.addTalkingPoint(pName, talkPoint); 
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
