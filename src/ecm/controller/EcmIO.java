package ecm.controller;

/**
 * Class to load and save data from somewhere
 * (Stub)
 * @author beepbeep
 */
public class EcmIO
{
    public String fetchMembers()
    {
        String data = "Volunteer,Bob,614241312,Bob@twitter.com,Bob@facebook.com,\n" +
                      "Volunteer,Jokic,24141242, , ,\n" +
                      "Candidate,Sheev Palpatine,66,Sidious@twitter.com,Sidious@facebook.com,\n" +
                      "Candidate,Pong,900,pong@twitter.com, ,\n" +
                      "Strategist,Grand Moff,623, ,GrandMoff@facebook.com,\n" +
                      "Strategist,Zhan Musi Hadeng,13,JHArden13@twitter.com,JHarden13@facebook.com,";
        
        return data;
    }
    
    public String fetchPolicy()
    {
        String data = "Education\nDefence\nWorkplace relations";
        
        return data;
    }
    
    public String fetchKeywords()
    {
        String data = "Defence:empire\nEducation:sport\nWorkplace relations:wage";
        
        return data;
    }
    
    public String fetchTalkingPoints()
    {
        String data = "Defence:empire expanding Death Star\nEducation:NBA scholarship given by James Harden\n" +
                "Workplace relations:minimum wage to increase";
        
        return data;
    }
}
