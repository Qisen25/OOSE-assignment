package ecm.model;

/**
 * member interface component for composite pattern
 * @author Kei Sum Wang 19126089
 */
public interface Member
{
    public Member find(int id);
    public String toString();
    public long getMobileNum();
    public int getId();
    public String getTwitterID();
    public String getFacebookID();
}
