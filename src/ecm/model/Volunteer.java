package ecm.model;

public class Volunteer extends Person
{
    public Volunteer(int id, String name, String mobileNum, String twitterID, String faceBookID) 
    {
        super(id, name, mobileNum, twitterID, faceBookID);
    }

    @Override
    public String toString()
    {
        return "Volunteer " + super.toString();
    }
}