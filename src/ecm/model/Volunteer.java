package ecm.model;

public class Volunteer extends Person
{
    public Volunteer(int id, String name, long mobileNum, String twitterID, String faceBookID) 
    {
        super(id, name, mobileNum, twitterID, faceBookID);
    }

    @Override
    public String toString()
    {
        return "Volunteer " + super.toString();
    }

    /**
     *find method defined by Member interface
     * @param id
     * @return
     */
    @Override
    public Volunteer find(int id)
    {
        Volunteer vol = null;
        
        if(super.getId() == id)
        {
            vol = this;
        } 
        
        return vol;
    }  
}