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
    public void addKeyword(String pName, String key) throws PolicyNotFoundException;
    public void removeKeyword(String key);
    public void addTalkingPoint(String pName, String key) throws PolicyNotFoundException;
    public void removeTalkingPoint(String talk);
    public void printKey();//print functions to be removed
    public void printTalk();
}
