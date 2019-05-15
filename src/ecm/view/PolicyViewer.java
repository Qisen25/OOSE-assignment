package ecm.view;

import java.util.List;
import ecm.model.Policy;
import ecm.model.PolicyAreas;
import ecm.model.TextObserver;
import ecm.model.PolicyObserver;
/**
 *
 * @author beepbeep
 */
public class PolicyViewer implements PolicyObserver
{
    private PolicyAreas polAreas;
    private List<Policy> allPolicies;

    public PolicyViewer(PolicyAreas polAreas)
    {
        this.polAreas = polAreas;
    }

    @Override
    public void subscribe()
    {
        polAreas.addObserver(this);
    }
    
    @Override
    public void dataUpdate(List<Policy> data)
    {
        this.allPolicies = data;// maybe change policies to sets since they should be unique
    }
    
    public void display()
    {
        System.out.println("Policies found in system");
        for(Policy pol : allPolicies)
        {
            System.out.println(pol.toString());
        }
    }
}
