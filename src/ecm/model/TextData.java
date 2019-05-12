package ecm.model;

import java.util.*;

/**
 *Text interface
 * @author beepbeep
 */
public interface TextData
{ 
    public void addData(String polName, String data);
    public Set<String> getPolicyData(String polName);
    public Set<String> getAllThisData();
}
