package ecm.model;

/**
 *
 * @author beepbeep
 */
public interface Member
{
    public Member find(int id);
    public String toString();
    public long getMobileNum();
    public int getId();
}