package ecm.view;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author beepbeep
 */
public class FacebookPostScout extends FacebookMessenger
{
    protected Map<String, Integer> keyMap;

    //should set up set fo strings here
    public FacebookPostScout()
    {
        super();
        this.keyMap = new HashMap<String, Integer>();
    }
    
    @Override
    public void setKeywords(Set<String> keywords)
    {
        System.out.println("setting keywords for post scout");
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
            System.out.println("keyword: \"" + entry.getKey() + "\" detected on Facebook " 
                                + entry.getValue() + " times at " + timestamp + " sec") ;
        }
    }
        
}
