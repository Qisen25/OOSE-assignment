package ecm.model;

import java.util.Set;
/**
 *
 * @author beepbeep
 */
public interface Policy
{
    public PolicyEntry find(String name);
    public Set<String> getKeywords();
    public Set<String> getTalkPoints();
    public void printKey();
    public void printTalk();
}
