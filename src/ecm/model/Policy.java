package ecm.model;

/**
 *
 * @author beepbeep
 */
public interface Policy
{
    public PolicyEntry find(String name);
    public void printKey();
    public void printTalk();
}
