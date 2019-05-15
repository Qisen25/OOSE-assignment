package ecm.controller;

import ecm.view.KeywordViewer;
import ecm.model.Policy;
import ecm.model.PolicyAreas;
import ecm.model.PolicyEntry;
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
}
