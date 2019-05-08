package ecm.model;

public class Strategist extends Person
{
    public Strategist(int id, String name, String mobileNum, String twitterID, String faceBookID) 
    {
        super(id, name, mobileNum, twitterID, faceBookID);
    }

    @Override
    public String toString()
    {
        return "Strategist " + super.toString();
    }

}