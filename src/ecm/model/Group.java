package ecm.model;

import java.util.*;
/**
 *
 * @author beepbeep
 */
public class Group implements Member
{
    private List<Member> members;

    public Group()
    {
        this.members = new ArrayList<Member>();
    }
    
    public void addMember(Member mem)
    {
        this.members.add(mem);    
    }
    
    public void removeMember(int id)
    {
        Member mem = this.find(id);
        
        if(mem != null)
        {
            this.members.remove(mem);
            System.out.println("removed member " + id);
        }
       
    }

    @Override
    public Member find(int id)
    {
        for(Member m : members)
        {
            Member memFound = m.find(id);
            if(memFound != null)
            {
                return memFound;
            }
        }
        
        return null;
    }  

    public List<Member> getMembers()
    {
        return members;
    }
      
    public void printDetails()
    {
        for(Member m : members)
        {
            Person p = (Person)m;
            System.out.println(p.toString());
        }
    }
}
