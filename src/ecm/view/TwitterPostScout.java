package ecm.view;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author beepbeep
 */
public class TwitterPostScout extends TwitterMessenger
{
    protected Map<String, Integer> keyMap;
    
    public TwitterPostScout()
    {
        super();
        this.keyMap = new HashMap<String, Integer>();
    }
    
    @Override
    public void setKeywords(Set<String> keywords)
    {
        for(String key : keywords)
        {
            this.keyMap.put(key, 0);
        }
    }
    
    @Override
    protected void keywordsDetected(Map<String, Integer> keywords, long timestamp)
    {
        for(Map.Entry<String, Integer> entry : keywords.entrySet())
        {
            System.out.println("keyword: \"" + entry.getKey() + "\" detected on Twitter " 
                                + entry.getValue() + " times at " + timestamp + " sec") ;
        }      
    }  
}
