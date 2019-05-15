package ecm.view;

import ecm.model.Member;
import ecm.controller.GroupController;
import java.util.List;

/**
 *
 * @author beepbeep
 */
public class MemberViewer
{ 
    public void display(GroupController grpC)
    {
        List<Member> mem = grpC.getAllMembers();
        
        System.out.println("All members found on system");
        for(Member m : mem)
        {
            System.out.println(m.toString());
        }
    }
}
