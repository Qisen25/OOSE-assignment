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

    public void addPolicyKeyword(String keyword, String pName)
    {
       PolicyEntry policy = this.find(pName); 
       if(policy != null)
       {
          policy.addKeyword(keyword); 
       }    
    }

    public void addPolicyTalkPoint(String talkPoint, String pName)
    {
       PolicyEntry policy = this.find(pName); 
       if(policy != null)
       {
           policy.addTalkingPoint(talkPoint); 
       }
    }
   
    public void setPolicyKeywords(Keywords keys)
    {
        for(Policy p : policies)
        {
            PolicyEntry pol = (PolicyEntry)p;
            
            Set<String> relatedKeyw = keys.getPolicyData(pol.getName());
            if(relatedKeyw != null)
            {
                pol.setKeywords(relatedKeyw);
            }
        }
    }

    public void setPolicyTalkPoints(TalkingPoints points)
    {
        for(Policy p : policies)
        {
            PolicyEntry pol = (PolicyEntry)p;
            
            Set<String> relatedPts = points.getPolicyData(pol.getName());
            
            if(relatedPts != null)
            {
                pol.setTalkingPoints(relatedPts);
            }
        }
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
