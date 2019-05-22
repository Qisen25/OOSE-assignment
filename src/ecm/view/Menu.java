package ecm.view;

import ecm.controller.EcmIO;
import java.util.*;
import ecm.controller.GroupController;
import ecm.controller.PolicyAreaController;
import ecm.controller.TextDataController;
import ecm.controller.InvalidMemberRoleException;
import ecm.controller.MemberNotFoundException;
import ecm.controller.NotificationHandler;
import ecm.model.DuplicateException;
import ecm.model.PolicyNotFoundException;

/**
 *TODO clean up this messy class maybe move viewers to each respective Controller
 * @author beepbeep
 */
public class Menu
{

    private PolicyAreaController pCtrl;
    private GroupController grpCtrl;
    private TextDataController tCtrl;
    private NotificationHandler notifHand;
    private String mainMenuMsg;
    private String viewDataMsg;
    private String addDataMsg;
    private String removeDataMsg;
    private boolean loadStatus;
    

    public Menu(GroupController grpContr, PolicyAreaController pCtrl, NotificationHandler notifHand)
    {
        this.pCtrl = pCtrl;
        this.grpCtrl = grpContr;
        this.notifHand = notifHand;
        this.mainMenuMsg = "++Menu++ \n1. add data \n2. view data \n3. remove data" + 
                            "\n4. Notification settings \n0. Exit";
        this.viewDataMsg = "++View data++\n1. policy\n2. People \n3. keywords\n4. talking points";
        this.addDataMsg = "++Add data++\n1. policy\n2. people\n3. keywords\n4. talking points" + 
                          "\n5. load all data from file ";
        this.removeDataMsg = "++Remove data++\n1. policy\n2. people\n3. keywords\n4. talking points";
        this.loadStatus = false;
    }
       
    public void displayMenu(PolicyViewer p , MemberViewer m, KeywordViewer k , TalkingPointViewer t)
    {
        int choice;
        Scanner sc = new Scanner(System.in);
        
        do
        {
            System.out.println(this.mainMenuMsg);
            System.out.print("choice:> ");
            choice = this.intInput();
            
            switch(choice)
            {
                case 1:
                    addData();
                    break;
                    
                case 2:
                    viewData(p, m, k, t);
                    break;
                   
                case 3:
                    removeData();
                    break;
                    
                case 4:
                    notificationSettings();
                    break;
                    
                case 0:
                    System.out.println("Exit");
                    break;
                    
                default:
                    System.out.println("Please try again: incorrect choice");
                    break;
            }
        }while(choice != 0);
    }
 
    private void viewData(PolicyViewer pV , MemberViewer mV, KeywordViewer kV , TalkingPointViewer tV)
    {
        int op;
        Scanner sc = new Scanner(System.in);
        Viewer view = null;
        
        System.out.println(this.viewDataMsg + "\n0. Back to main menu");
        System.out.print("choice:> ");
        op = this.intInput();
        switch (op)
        {
            case 1:
                view = pV;
                break;
                
            case 2:
                view = mV;
                break;
                
            case 3:
                view = kV;
                break;
                
            case 4:
                view = tV;
                break;
                
            default:
                break;
        }
        
        if(view !=null)
            displayView(view);
    }
    
    private void displayView(Viewer view)
    {
        view.display();
    }
    
    private void loadData()
    {
        try
        {   
            EcmIO source = new EcmIO();
            grpCtrl.loadMembers(source);
            pCtrl.loadPolicies(source);
            pCtrl.loadKeywords(source);
            pCtrl.loadTalkingPoints(source);
            notifHand.clearUsrConfig();
        }
        catch(InvalidMemberRoleException | PolicyNotFoundException | DuplicateException e)
        {
            System.out.println(e.getMessage());
        }
    }
    private void addData()
    {
        int op;
        String loadChoice = "y";
        
        System.out.println(this.addDataMsg + "\n0. Back to main menu");
        System.out.print("choice:> ");
        op = this.intInput();
        switch (op)
        {
            case 1:
                addPolicy();
                break;
                
            case 2:
                addPeople();
                break;
                
            case 3:
                addKeyword();
                break;
                
            case 4:
                addTalkingPoint();
                break;
                                            
            case 5:
                if(loadStatus)
                {
                    System.out.println("Are you sure want to reload data? current data and settings will be wiped. y/n");
                    loadChoice = this.strInput();
                }
                if(loadChoice.equalsIgnoreCase("y"))
                {
                    loadData();
                }
                break;     
                
            default:
                break;
        }
    }
    
    public void notificationSettings()
    {
        System.out.println("1. add per-person per-policy setting");
        System.out.println("2. remove per-person per-policy setting");
        System.out.println("3. show current settings \n0. Back to main menu");
        System.out.print("choice:> ");
        int op = this.intInput();
        if(op == 1)
        {
            addUserSetting();
        }
        else if(op == 2)
        {
            removeUserSetting();
        }
        else if(op == 3)
        {
            showUsrSettings();
        }
    }
    
    private void addPolicy()
    {
        System.out.print("Enter policy name:> ");
        String name = this.strInput();
        pCtrl.addPolicy(name);
    }
    
    private void addPeople()
    {
        String[] details = new String[5];   
        try
        {
            System.out.print("Enter their role:> ");
            details[0] = this.strInput();
            System.out.print("Enter their name:> ");
            details[1] = this.strInput();
            System.out.print("Enter their phone number:> ");
            details[2] = this.strInput();
            System.out.print("Enter their twitter id:> ");
            details[3] = this.strInput();
            System.out.print("Enter their facebook id:> ");
            details[4] = this.strInput();

            grpCtrl.addMember(details);
        }
        catch(InvalidMemberRoleException e)
        {
            System.out.println(e.getMessage() + details[0]);
        }
    }
    
    private void addKeyword()
    {
        try
        {
            System.out.print("Enter policy name:> ");
            String name = this.strInput();
            System.out.print("Enter a keyword:> ");
            String key = this.strInput();
            pCtrl.addKeyword(name, key);
        }
        catch(PolicyNotFoundException | DuplicateException e)
        {
            System.out.println(e.getMessage());
        }
    }
    
    private void addTalkingPoint()
    {
        try
        {
            System.out.print("Enter policy name:> ");
            String name = this.strInput();
            System.out.print("Enter a talking point:> ");
            String talk = this.strInput();
            pCtrl.addTalkingPoint(name, talk);
        }
        catch(PolicyNotFoundException | DuplicateException e)
        {
            System.out.println(e.getMessage());
        }
    }
    
    public void addUserSetting()
    {
        try
        {
            System.out.print("Enter member id:> ");
            int id = this.intInput();
            grpCtrl.find(id);
            System.out.print("Enter policy name:> ");
            String pName = this.strInput();
            pCtrl.find(pName);
            
            notifHand.addUsrSetting(id, pName);
        }
        catch(PolicyNotFoundException | MemberNotFoundException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void removeUserSetting()
    {
        try
        {
            System.out.print("Enter member id:> ");
            int id = this.intInput();
            grpCtrl.find(id);
            System.out.print("Enter policy name:> ");
            String pName = this.strInput();
            pCtrl.find(pName);
            
            notifHand.removeUsrSetting(id);
        }
        catch(PolicyNotFoundException | MemberNotFoundException e)
        {
            System.out.println(e.getMessage());
        }
    }
    
    private void removeData()
    {
        System.out.println(this.removeDataMsg + "\n0. Back to main menu");
        System.out.print("choice:> ");
        int choice = this.intInput();
        switch (choice)
        {
            case 1:
                removePolicy();
                break;
            case 2:
                removeMember();
                break;
            case 3:
                removeKeyword();
                break;
            case 4:
                removeTalkPoint();
                break;
            default:
                break;
        }
    }
    
    private void removePolicy()
    {
        System.out.print("Enter name of policy:> ");
        String name = this.strInput();
        pCtrl.removePolicy(name);
        notifHand.removeUsrSettingByPolicy(name);
    }
    
    private void removeMember()
    {
        System.out.print("Enter id of member:> ");
        int id = this.intInput();
        grpCtrl.removeMember(id);
        notifHand.removeUsrSetting(id);
    }
    
    private void removeKeyword()
    {
        try
        {
            System.out.print("Enter name of policy:> ");
            String policy = this.strInput();
            System.out.print("Enter a keyword of policy given:> ");
            String keyw = this.strInput();
            pCtrl.removeKeyword(policy, keyw);
        }
        catch(PolicyNotFoundException | NoSuchElementException e)
        {
            System.out.println(e.getMessage());
        }
    }
    
    private void removeTalkPoint()
    {
        try
        {
            System.out.print("Enter name of policy:> ");
            String policy = this.strInput();
            System.out.print("Enter a talking point of policy given:> ");
            String talk = this.strInput();
            pCtrl.removeTalkingPoint(policy, talk);
        }
        catch(PolicyNotFoundException | NoSuchElementException e)
        {
            System.out.println(e.getMessage());
        }
    }
    
    private void showUsrSettings()
    {
        System.out.println("++Current settings configured++");
        Map<Integer, String> personAndPolicy = this.notifHand.getUsrConfig();
        
        for(Map.Entry<Integer, String> pp : personAndPolicy.entrySet())
        {
            System.out.println("Member ID: " + pp.getKey() + " | related policy: " + pp.getValue());
        }  
    }
    
    private String strInput()
    {
        Scanner sc = new Scanner(System.in);
        
        String str = sc.nextLine();
        
        return str;
    }
    
    private int intInput()
    {
        Scanner sc = new Scanner(System.in);
        
        int num = sc.nextInt();
        
        return num;
    }

}
