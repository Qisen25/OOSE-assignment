package ecm.controller;

import ecm.model.Policy;
import ecm.model.PolicyEntry;
/**
 *
 * @author beepbeep
 */
public class PolicyFactory
{
    public Policy makePolicy(String policyName)
    {
        Policy pEntry = new PolicyEntry(policyName);
        
        return pEntry;
    }
}
