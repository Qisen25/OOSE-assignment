package ecm.model;

import java.util.NoSuchElementException;
import java.util.Set;
/**
 * policy interface component for composite pattern-
 * @author Kei Sum Wang 19126089
 */
public interface Policy
{
    public PolicyEntry find(String name);
    public Set<String> getKeywords();
    public Set<String> getTalkPoints();
    public void addKeyword(String pName, String key) throws PolicyNotFoundException, DuplicateException;
    public void removeKeyword(String polName, String key) throws PolicyNotFoundException, NoSuchElementException;
    public void addTalkingPoint(String pName, String key) throws PolicyNotFoundException, DuplicateException;
    public void removeTalkingPoint(String polName, String talk) throws PolicyNotFoundException, NoSuchElementException;
}
