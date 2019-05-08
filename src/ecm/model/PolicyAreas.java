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
}
