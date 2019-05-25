package ecm.model;

/**
 * Candidate concrete class
 * @author Kei Sum Wang 19126089
 */
public class Candidate extends Person
{
    public Candidate(int id, String name, long mobileNum, String twitterID, String faceBookID) 
    {
        super(id, name, mobileNum, twitterID, faceBookID);
    }

    @Override
    public String toString()
    {
        return "Candidate " + super.toString();
    } 

    /**
     *find method defined by Member interface
     * @param id
     * @return
     */
    @Override
    public Candidate find(int id)
    {
        Candidate cand = null;
        
        if(super.getId() == id)
        {
            cand = this;
        }
        
        return cand;
    }

}