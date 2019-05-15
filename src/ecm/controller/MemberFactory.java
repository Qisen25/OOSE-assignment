package ecm.controller;

import ecm.model.Member;
import ecm.model.Person;
import ecm.model.Candidate;
import ecm.model.Volunteer;
import ecm.model.Strategist;

/**
 *
 * @author beepbeep
 */
public class MemberFactory
{
    private int autoId;

    public MemberFactory()
    {
        this.autoId = 0;
    }
    
    public Member makeMember(String[] details)
    {
        long phoneno = Long.parseLong(details[2]);
        Member membo = null;
        
        if(details[0].equalsIgnoreCase("volunteer"))
        {
            membo = new Volunteer(autoId++, details[1], phoneno, details[3], details[4]);
        }
        else if(details[0].equalsIgnoreCase("candidate"))
        {
            membo = new Candidate(autoId++, details[1], phoneno, details[3], details[4]);    
        }
        else if(details[0].equalsIgnoreCase("strategist"))
        {
            membo = new Strategist(autoId++, details[1], phoneno, details[3], details[4]);    
        }
       
        return membo;
    }
}
