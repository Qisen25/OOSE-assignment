package ecm.controller;

import ecm.model.Policy;
import ecm.model.PolicyEntry;
/**
 * factory responsible for creation of policy entries
 * @author Kei Sum Wang 19126089
 */
public class PolicyFactory
{
    public Policy makePolicy(String policyName)
    {
        Policy pEntry = new PolicyEntry(policyName);
        
        return pEntry;
    }
}
