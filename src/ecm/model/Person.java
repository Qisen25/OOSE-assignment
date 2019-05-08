package ecm.model;

public abstract class Person
{

    private int id;
    private String name;
    private String mobileNum;
    private String twitterID;
    private String facebookID;

    public Person(int id, String name, String mobileNum, String twitterID, String facebookID)
    {
        this.id = id;
        this.name = name;
        this.mobileNum = mobileNum;
        this.twitterID = twitterID;
        this.facebookID = facebookID;
    }

    public int getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public String getMobileNum()
    {
        return mobileNum;
    }

    public String getTwitterID()
    {
        return twitterID;
    }

    public String getFacebookID()
    {
        return facebookID;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setMobileNum(String mobileNum)
    {
        this.mobileNum = mobileNum;
    }

    public void setTwitterID(String twitterID)
    {
        this.twitterID = twitterID;
    }

    public void setFacebookID(String faceBookID)
    {
        this.facebookID = faceBookID;
    }

    @Override
    public String toString()
    {
        return "id=" + id + ", name=" + name;
    }

}
