package ecm.model;

import java.util.*;

/**
 *Text interface
 * @author beepbeep
 */
public interface TextData
{ 
    public void addData(String polName, String data) throws PolicyNotFoundException;
    public void removeData(String data);
    public Set<String> getPolicyData(String polName);
    public Set<String> getAllThisData();
    public void addObserver(TextObserver obs);
    public void removeObserver(TextObserver obs);
    public void notifyObservers();
}
