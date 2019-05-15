package ecm.view;

import ecm.model.TalkingPoints;
import ecm.model.TextData;
import java.util.Set;
import ecm.model.TextObserver;

/**
 *
 * @author beepbeep
 */
public class TalkingPointViewer implements TextObserver
{
    private TalkingPoints talkPointContainer;
    private Set<String> allTalk;

    public TalkingPointViewer(TalkingPoints talkPoint)
    {
        this.talkPointContainer = talkPoint;
    }
    
    @Override
    public void subscribe()
    {
        talkPointContainer.addObserver(this);
    }
    
    @Override
    public void dataUpdate(Set<String> data)
    {
        this.allTalk = data;
    }
    
    public void display()
    {
        System.out.println("Talking points found in system");
        for(String str : allTalk)
        {
            System.out.println(str);
        }
    }
}
