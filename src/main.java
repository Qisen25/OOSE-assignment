import ecm.view.PolicyViewer;
import java.util.*;
import ecm.model.*;
import ecm.view.*;
import ecm.controller.*;
/**
 *
 * @author beepbeep
 */
public class main
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        PolicyAreas p = new PolicyAreas();
        Group grp = new Group();
        SMS sms = new SMS();
        TwitterMessenger twit = new TwitterPostScout(p);
        FacebookMessenger fb = new FacebookPostScout(p);
        
        PolicyFactory pMaker = new PolicyFactory();
        MemberFactory maker = new MemberFactory();
        PolicyAreaController pACtrl = new PolicyAreaController(pMaker, p);
        GroupController grpCtrl = new GroupController(maker, grp);
        
        PolicyViewer polObs = new PolicyViewer(p);
        MemberViewer memObs = new MemberViewer(grp);
        KeywordViewer keyObs = new KeywordViewer(p);
        TalkingPointViewer talkObs = new TalkingPointViewer(p);
        NotificationHandler notifHand = new NotificationHandler(p, grp, sms, twit, fb);
        
        PostMonitor monitor = PostMonitor.getInstance();
        
        notifHand.subscribe();
        
        keyObs.subscribe();
        
        talkObs.subscribe();
        
        ((KeywordObserver)fb).subscribe();
        ((KeywordObserver)twit).subscribe();
        
        monitor.connectNotification(notifHand);
       
        
        Menu m = new Menu(grpCtrl, pACtrl, notifHand, monitor);
        //MenuController menuz = new MenuController(m);
        
        m.displayMenu(polObs, memObs, keyObs, talkObs);
                
    }  
}
