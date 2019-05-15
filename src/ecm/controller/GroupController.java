package ecm.controller;

import ecm.model.Group;
import ecm.model.Member;
import java.util.List;
/**
 *
 * @author beepbeep
 */
public class GroupController
{
    private MemberFactory maker;
    private Group grp;

    public GroupController(MemberFactory maker, Group grp)
    {
        this.maker = maker;
        this.grp = grp;
    }
    
    public void addMember(String[] details) throws InvalidMemberRoleException
    {
       Member newMem = maker.makeMember(details);
       
       if(newMem == null)
           throw new InvalidMemberRoleException("Invalid role given: ");
       
       grp.addMember(newMem);
    }
    
    public void removeMember(int id)
    {
        grp.removeMember(id);
    }
    
    public List<Member> getAllMembers()
    {
        return grp.getMembers();
    }
}
