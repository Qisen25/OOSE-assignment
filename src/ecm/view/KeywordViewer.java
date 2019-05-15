package ecm.view;

import java.util.Set;
import ecm.model.Keywords;
import ecm.model.TextData;
import ecm.model.TextObserver;
/**
 *
 * @author beepbeep
 */
public class KeywordViewer implements TextObserver
{
    private Keywords keywordContainer;
    private Set<String> allKeywords;

    public KeywordViewer(Keywords keyword)
    {
        this.keywordContainer = keyword;
    }
    
    @Override
    public void subscribe()
    {
        keywordContainer.addObserver(this);
    }
        
    @Override
    public void dataUpdate(Set<String> data)
    {
        this.allKeywords = data;
    }
    
    public void display()
    {
        System.out.println("Keywords found in system");
        for(String str : allKeywords)
        {
            System.out.println(str);
        }
    }

}
