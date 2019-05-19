package ecm.view;

import java.util.List;
import java.util.Collection;
import ecm.model.Policy;
import ecm.model.PolicyAreas;
import ecm.model.KeywordObserver;
/**
 *
 * @author beepbeep
 */
public class PolicyViewer
{
    private PolicyAreas polAreas;

    public PolicyViewer(PolicyAreas polAreas)
    {
        this.polAreas = polAreas;
    }

    public void display()
    {
        System.out.println("Policies found in system");
        for(Policy pol : polAreas.getPolicies())
        {
            System.out.println(pol.toString());
        }
    }
}
