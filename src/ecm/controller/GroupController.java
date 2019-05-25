package ecm.controller;

import ecm.model.Group;
import ecm.model.Member;
import java.util.List;
/**
 * controller responsible for manipulation group data
 * @author Kei Sum Wang 19126089
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
    
    public Member find(int id) throws MemberNotFoundException
    {
        if(grp.find(id) == null)
        {
            throw new MemberNotFoundException("Member " + id + " not found");
        }
        
        return grp.find(id);
    }
    
    public void loadMembers(EcmIO source) throws InvalidMemberRoleException
    {
        grp.clearMembers();
        
        String detailOfMembers[] = source.fetchMembers().split("\n");
        
        for(String details : detailOfMembers)
        {
            String field[] = details.split(",", -1);
            addMember(field);
        }
    }
}
