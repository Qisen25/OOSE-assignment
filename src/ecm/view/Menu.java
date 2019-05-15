package ecm.view;

import java.util.*;
import ecm.controller.GroupController;
import ecm.controller.PolicyAreaController;
import ecm.controller.TextDataController;
import ecm.controller.InvalidMemberRoleException;
import ecm.model.PolicyNotFoundException;

/**
 *TODO clean up this messy class maybe move viewers to each respective Controller
 * @author beepbeep
 */
public class Menu
{
    private KeywordViewer k;
    private TalkingPointViewer t;
    private PolicyViewer p;
    private PolicyAreaController pCtrl;
    private GroupController grpCtrl;
    private TextDataController tCtrl;
    private MemberViewer m;
    private String mainMenuMsg;
    private String viewDataMsg;
    private String addDataMsg;
    private String removeDataMsg;
    

    public Menu(KeywordViewer k, TalkingPointViewer t, PolicyViewer p, 
            GroupController grpContr, PolicyAreaController pCtrl, TextDataController tCtrl)
    {
        this.k = k;
        this.t = t;
        this.p = p;
        this.pCtrl = pCtrl;
        this.m = new MemberViewer();
        this.grpCtrl = grpContr;
        this.tCtrl = tCtrl;
        this.mainMenuMsg = "++Menu++ \n1. add data \n2. view data \n3. remove data \n0. Exit";
        this.viewDataMsg = "++View data++\n1. policy\n2. People \n3. keywords\n4. talking points";
        this.addDataMsg = "++Add data++\n1. policy\n2. people\n3. keywords\n4. talking points";
        this.removeDataMsg = "++Remove data++\n1. policy\n2. people\n3. keywords\n4. talking points";
    }
       
    public void displayMenu()
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
                    viewData();
                    break;
                   
                case 3:
                    removeData();
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
 
    private void viewData()
    {
        int op;
        Scanner sc = new Scanner(System.in);
        
        System.out.println(this.viewDataMsg);
        System.out.print("operation:>");
        op = this.intInput();
        switch (op)
        {
            case 1:
                p.display();
                break;
                
            case 2:
                m.display(grpCtrl);
                break;
                
            case 3:
                k.display();
                break;
                
            case 4:
                t.display();
                break;
                
            default:
                break;
        }
    }
    
    private void addData()
    {
        int op;
        
        System.out.println(this.addDataMsg);
        System.out.print("operation:>");
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
                
            default:
                break;
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
            tCtrl.addKeyword(name, key);
        }
        catch(PolicyNotFoundException e)
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
            tCtrl.addKeyword(name, talk);
        }
        catch(PolicyNotFoundException e)
        {
            System.out.println(e.getMessage());
        }
    }

    private void removeData()
    {
        System.out.println(this.removeDataMsg);
        System.out.print("choice:>");
        int choice = this.intInput();
        if(choice == 1)
        {
            removePolicy();
        }
        else if(choice == 2)
        {
            removeMember();
        }
    }
    
    private void removePolicy()
    {
        System.out.print("Enter name of policy:> ");
        String name = this.strInput();
        pCtrl.removePolicy(name);
    }
    
    private void removeMember()
    {
        System.out.print("Enter id of member:> ");
        int id = this.intInput();
        grpCtrl.removeMember(id);
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
