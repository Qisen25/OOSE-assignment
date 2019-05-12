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
    public void addKeyword(String pName, String key);
    public void addTalkingPoint(String pName, String key);
    public void printKey();
    public void printTalk();
}
