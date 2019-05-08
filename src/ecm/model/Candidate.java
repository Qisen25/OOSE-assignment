package ecm.model;

public class Candidate extends Person
{
    public Candidate(int id, String name, String mobileNum, String twitterID, String faceBookID) 
    {
        super(id, name, mobileNum, twitterID, faceBookID);
    }

    @Override
    public String toString()
    {
        return "Candidate " + super.toString();
    }
    
}