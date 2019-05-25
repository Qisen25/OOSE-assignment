package ecm.view;

import ecm.model.Member;
import ecm.model.Group;
import ecm.controller.GroupController;
import java.util.Collection;
import java.util.List;
import ecm.model.KeywordObserver;

/**
 * class to view Members
 * @author Kei Sum Wang 19126089
 */
public class MemberViewer implements Viewer
{
    private Group grp;

    public MemberViewer(Group grp)
    {
        this.grp = grp;
    }

    @Override
    public void display()
    {   
        System.out.println("++All members found on system++");
        for(Member m : grp.getMembers())
        {
            System.out.println(m.toString());
        }
    }
}
