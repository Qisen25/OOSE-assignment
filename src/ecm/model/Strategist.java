package ecm.model;

/**
 * Strategist concrete class
 * @author Kei Sum Wang 19126089
 */
public class Strategist extends Person
{
    public Strategist(int id, String name, long mobileNum, String twitterID, String faceBookID) 
    {
        super(id, name, mobileNum, twitterID, faceBookID);
    }

    @Override
    public String toString()
    {
        return "Strategist " + super.toString();
    }

    /**
     *find method defined by Member interface
     * @param id
     * @return
     */
    @Override
    public Strategist find(int id)
    {
        Strategist strat = null;
        
        if(super.getId() == id)
        {
            strat = this;
        }
        
        return strat;
    }
}